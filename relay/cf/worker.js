// Exiled Kingdoms online relay on Cloudflare Workers (deobf/RELAY_SPEC.md "v2 host", "v3 hibernation").
// One Durable Object per room code pipes WebSocket bytes between the host and the players joining by code.
//
// v3: WebSocket Hibernation API. An open room with nobody joining costs nothing: the host's keep-alive PING is
// answered by Cloudflare itself (setWebSocketAutoResponse) without waking the object, and every piece of pairing
// state lives on the sockets (tags + serialized attachments) so it survives the object being evicted.
// Sockets:  host   tags ["host"]            attachment {role:"host", dev}
//           join   tags ["join", token]     attachment {role:"join", token, t0, paired, early:[base64...]}
//           accept tags ["accept", token]   attachment {role:"accept", token}
const CODE_RE = /^[A-HJ-NP-Z2-9]{6}$/;
const JOIN_WAIT_MS = 15000;
const EARLY_MAX = 1500;           // bytes kept in the joiner's attachment until the host's accept socket arrives
// v4 public rooms: one extra object (the "lobby", same class, name LOBBY_NAME) keeps code -> {name, t}. A room whose
// host connected with public=1 registers there, re-registers every REFRESH_MS (alarm) and unregisters on close.
const LOBBY_NAME = "__lobby__";
const REFRESH_MS = 10 * 60 * 1000;
const STALE_MS = 25 * 60 * 1000;
const LIST_MAX = 50;

function b64(buf) {
  const u = new Uint8Array(buf);
  let s = "";
  for (let i = 0; i < u.length; i++) s += String.fromCharCode(u[i]);
  return btoa(s);
}
function unb64(s) {
  const bin = atob(s);
  const u = new Uint8Array(bin.length);
  for (let i = 0; i < bin.length; i++) u[i] = bin.charCodeAt(i);
  return u.buffer;
}
function att(ws) {
  try { return ws.deserializeAttachment() || {}; } catch (e) { return {}; }
}
function closeQuietly(ws, code, why) {
  try { ws.close(code, why); } catch (e) {}
}

export class Room {
  constructor(state, env) {
    this.state = state;
    this.env = env;
    this.early = new Map();       // token -> [message] (in memory; the attachment keeps a small copy)
    state.setWebSocketAutoResponse(new WebSocketRequestResponsePair("PING", "PONG"));
  }

  sockets(tag, role) {
    return this.state.getWebSockets(tag).filter((w) => att(w).role === role);
  }

  host() {
    const h = this.sockets("host", "host");
    return h.length ? h[0] : null;
  }

  lobby() {
    return this.env.ROOMS.get(this.env.ROOMS.idFromName(LOBBY_NAME));
  }

  async publish(on) {
    const pub = await this.state.storage.get("pub");
    if (!pub) return;
    const q = on ? "role=register&code=" + pub.code + "&name=" + encodeURIComponent(pub.name) : "role=unregister&code=" + pub.code;
    try { await this.lobby().fetch("https://lobby/?" + q); } catch (e) {}
    if (!on) await this.state.storage.delete("pub");
  }

  async lobbyFetch(url, role) {                // runs in the lobby object only
    let rooms = (await this.state.storage.get("rooms")) || {};
    const now = Date.now();
    const code = (url.searchParams.get("code") || "").toUpperCase();
    let changed = false;
    if (role === "register" && CODE_RE.test(code)) {
      rooms[code] = { name: (url.searchParams.get("name") || "Player").slice(0, 24), t: now };
      changed = true;
    } else if (role === "unregister" && rooms[code]) {
      delete rooms[code];
      changed = true;
    }
    for (const c of Object.keys(rooms)) if (now - rooms[c].t > STALE_MS) { delete rooms[c]; changed = true; }
    if (changed) await this.state.storage.put("rooms", rooms);
    if (role !== "list") return new Response("ok");
    const lines = Object.keys(rooms).sort((a, b) => rooms[b].t - rooms[a].t).slice(0, LIST_MAX)
      .map((c) => c + "\t" + encodeURIComponent(rooms[c].name));
    return new Response(lines.join("\n"), { status: 200, headers: { "Cache-Control": "no-store" } });
  }

  async fetch(req) {
    const url = new URL(req.url);
    const role = url.searchParams.get("role");
    if (role === "list" || role === "register" || role === "unregister") {
      return this.lobbyFetch(url, role);
    }
    if (role === "status") {                  // friends list: is this room's host online? (no join started)
      return new Response(this.host() ? "online" : "offline", { status: 200, headers: { "Cache-Control": "no-store" } });
    }
    if (req.headers.get("Upgrade") !== "websocket") {
      return new Response("Exiled Kingdoms relay", { status: 200 });
    }
    const pair = new WebSocketPair();
    const client = pair[0], ws = pair[1];

    if (role === "host") {
      const dev = url.searchParams.get("dev") || "";
      const cur = this.host();
      if (cur && att(cur).dev !== dev) {
        ws.accept();
        ws.close(4409, "TAKEN");
      } else {
        if (cur) closeQuietly(cur, 4000, "replaced");
        this.state.acceptWebSocket(ws, ["host"]);
        ws.serializeAttachment({ role: "host", dev });
        ws.send("OK");
        const code = (url.searchParams.get("code") || "").toUpperCase();
        if (url.searchParams.get("public") === "1") {
          const name = (url.searchParams.get("name") || "Player").slice(0, 24);
          await this.state.storage.put("pub", { code, name });
          await this.publish(true);
          await this.state.storage.setAlarm(Date.now() + REFRESH_MS);
        } else {
          await this.publish(false);
        }
      }
    } else if (role === "join") {
      const host = this.host();
      if (!host) {
        ws.accept();
        ws.close(4404, "NOROOM");
      } else {
        const token = crypto.randomUUID().replace(/-/g, "");
        this.state.acceptWebSocket(ws, ["join", token]);
        ws.serializeAttachment({ role: "join", token, t0: Date.now(), paired: false, early: [] });
        this.early.set(token, []);
        await this.state.storage.setAlarm(Date.now() + JOIN_WAIT_MS + 500);
        // friend handshake (pairwise token, joiner's own room code, name) goes to the host with the request
        const q = (k) => encodeURIComponent((url.searchParams.get(k) || "").slice(0, 100)) || "-";
        try { host.send("CONN " + token + " " + q("tok") + " " + q("mycode") + " " + q("name")); }
        catch (e) { closeQuietly(ws, 4404, "NOROOM"); }
      }
    } else if (role === "accept") {
      const token = url.searchParams.get("token") || "";
      const j = this.sockets(token, "join").find((w) => !att(w).paired);
      if (!j) {
        ws.accept();
        ws.close(4410, "NOJOIN");
      } else {
        this.state.acceptWebSocket(ws, ["accept", token]);
        ws.serializeAttachment({ role: "accept", token });
        const ja = att(j);
        const mem = this.early.get(token);
        const early = mem && mem.length ? mem : (ja.early || []).map((m) => (m.t === "s" ? m.d : unb64(m.d)));
        this.early.delete(token);
        j.serializeAttachment({ role: "join", token, t0: ja.t0, paired: true, early: [] });
        try { j.send("OK"); } catch (e) {}
        for (const m of early) { try { ws.send(m); } catch (e) {} }
      }
    } else {
      ws.accept();
      ws.close(4400, "BADROLE");
    }
    return new Response(null, { status: 101, webSocket: client });
  }

  async webSocketMessage(ws, msg) {
    const a = att(ws);
    if (a.role === "join") {
      if (a.paired) {
        for (const p of this.sockets(a.token, "accept")) { try { p.send(msg); } catch (e) {} }
        return;
      }
      let mem = this.early.get(a.token);
      if (!mem) { mem = []; this.early.set(a.token, mem); }
      mem.push(msg);
      // small copy on the socket, in case the object is evicted before the host's accept socket arrives
      const early = a.early || [];
      const size = early.reduce((n, m) => n + m.d.length, 0);
      const item = typeof msg === "string" ? { t: "s", d: msg } : { t: "b", d: b64(msg) };
      if (size + item.d.length <= EARLY_MAX) {
        early.push(item);
        a.early = early;
        ws.serializeAttachment(a);
      }
    } else if (a.role === "accept") {
      for (const j of this.sockets(a.token, "join")) { try { j.send(msg); } catch (e) {} }
    }
    // host: PING is answered by the auto-response; anything else is ignored
  }

  async webSocketClose(ws, code, reason, wasClean) {
    await this.gone(ws);
  }

  async webSocketError(ws, err) {
    await this.gone(ws);
  }

  async gone(ws) {
    const a = att(ws);
    if (a.role === "host") {
      if (this.sockets("host", "host").some((w) => w !== ws)) return;   // a newer host socket replaced this one
      await this.publish(false);
      for (const j of this.state.getWebSockets("join")) {
        if (!att(j).paired) closeQuietly(j, 4404, "NOROOM");
      }
    } else if (a.role === "join") {
      this.early.delete(a.token);
      for (const p of this.sockets(a.token, "accept")) closeQuietly(p, 1000, "peer closed");
    } else if (a.role === "accept") {
      for (const j of this.sockets(a.token, "join")) closeQuietly(j, 1000, "peer closed");
    }
  }

  async alarm() {
    const now = Date.now();
    let waiting = false;
    for (const j of this.state.getWebSockets("join")) {
      const a = att(j);
      if (a.paired) continue;
      if (now - (a.t0 || 0) >= JOIN_WAIT_MS) {
        this.early.delete(a.token);
        closeQuietly(j, 4408, "TIMEOUT");
      } else {
        waiting = true;
      }
    }
    let next = waiting ? now + 2000 : 0;
    if (this.host() && (await this.state.storage.get("pub"))) {
      await this.publish(true);                // keep the public listing fresh
      if (!next) next = now + REFRESH_MS;
    }
    if (next) await this.state.storage.setAlarm(next);
  }
}

export default {
  async fetch(req, env) {
    const url = new URL(req.url);
    if (url.searchParams.get("role") === "list") {
      return env.ROOMS.get(env.ROOMS.idFromName(LOBBY_NAME)).fetch(req);
    }
    const code = (url.searchParams.get("code") || "").toUpperCase();
    if (!CODE_RE.test(code)) {
      return new Response("Exiled Kingdoms relay: ok", { status: 200 });
    }
    return env.ROOMS.get(env.ROOMS.idFromName(code)).fetch(req);
  },
};
