// Exiled Kingdoms online relay on Cloudflare Workers (deobf/RELAY_SPEC.md "v2 host").
// One Durable Object per room code pipes WebSocket bytes between the host and the players joining by code.
const CODE_RE = /^[A-HJ-NP-Z2-9]{6}$/;
const JOIN_WAIT_MS = 15000;

export class Room {
  constructor(state, env) {
    this.state = state;
    this.host = null;        // control socket
    this.hostDev = null;
    this.pending = new Map(); // token -> { sock, buf: [], timer }
  }

  async fetch(req) {
    if (req.headers.get("Upgrade") !== "websocket") {
      return new Response("Exiled Kingdoms relay", { status: 200 });
    }
    const url = new URL(req.url);
    const role = url.searchParams.get("role");
    const pair = new WebSocketPair();
    const client = pair[0], ws = pair[1];
    ws.accept();

    if (role === "host") {
      const dev = url.searchParams.get("dev") || "";
      if (this.host && this.hostDev !== dev) {
        ws.close(4409, "TAKEN");
      } else {
        if (this.host) { try { this.host.close(4000, "replaced"); } catch (e) {} }
        this.host = ws; this.hostDev = dev;
        ws.send("OK");
        ws.addEventListener("message", (ev) => {
          if (ev.data === "PING") { try { ws.send("PONG"); } catch (e) {} }
        });
        const gone = () => {
          if (this.host === ws) { this.host = null; this.hostDev = null; }
          for (const [t, p] of this.pending) { try { p.sock.close(4404, "NOROOM"); } catch (e) {} clearTimeout(p.timer); }
          this.pending.clear();
        };
        ws.addEventListener("close", gone);
        ws.addEventListener("error", gone);
      }
    } else if (role === "join") {
      if (!this.host) {
        ws.close(4404, "NOROOM");
      } else {
        const token = crypto.randomUUID().replace(/-/g, "");
        const p = { sock: ws, buf: [], timer: null };
        p.timer = setTimeout(() => {
          if (this.pending.get(token) === p) { this.pending.delete(token); try { ws.close(4408, "TIMEOUT"); } catch (e) {} }
        }, JOIN_WAIT_MS);
        ws.addEventListener("message", (ev) => { if (p.buf) p.buf.push(ev.data); });
        ws.addEventListener("close", () => { this.pending.delete(token); clearTimeout(p.timer); });
        this.pending.set(token, p);
        try { this.host.send("CONN " + token); } catch (e) { ws.close(4404, "NOROOM"); }
      }
    } else if (role === "accept") {
      const token = url.searchParams.get("token") || "";
      const p = this.pending.get(token);
      if (!p) {
        ws.close(4410, "NOJOIN");
      } else {
        this.pending.delete(token);
        clearTimeout(p.timer);
        const j = p.sock;
        const early = p.buf; p.buf = null;
        const fwd = (from, to) => {
          from.addEventListener("message", (ev) => { try { to.send(ev.data); } catch (e) {} });
          from.addEventListener("close", (ev) => { try { to.close(1000, "peer closed"); } catch (e) {} });
          from.addEventListener("error", () => { try { to.close(1011, "peer error"); } catch (e) {} });
        };
        fwd(j, ws);
        fwd(ws, j);
        try { j.send("OK"); } catch (e) {}
        for (const m of early) { try { ws.send(m); } catch (e) {} }
      }
    } else {
      ws.close(4400, "BADROLE");
    }
    return new Response(null, { status: 101, webSocket: client });
  }
}

export default {
  async fetch(req, env) {
    const url = new URL(req.url);
    const code = (url.searchParams.get("code") || "").toUpperCase();
    if (!CODE_RE.test(code)) {
      return new Response("Exiled Kingdoms relay: ok", { status: 200 });
    }
    return env.ROOMS.get(env.ROOMS.idFromName(code)).fetch(req);
  },
};
