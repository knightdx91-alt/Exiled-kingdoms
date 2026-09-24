# Online play through a relay server (owner: Option B)

Two phones that can't reach each other directly (both on mobile data, or a host whose home internet blocks
incoming connections) both connect *out* to a small relay server, which pipes the game's TCP stream between
them. Players join by a short room code. The host's join approval still applies.

## Server (`web/relay/ekrelay.py`, Python 3 stdlib only, asyncio; runs on Oracle Cloud Always Free)
One TCP port, **32200**. Every connection starts with one text line:
- `EKR1 HOST [code]` → `OK <code>`: this connection is the host's **control** line for a room. A code the host
  asks for is reused if free (so a friend's saved code keeps working); otherwise a new 6-character code from
  `ABCDEFGHJKLMNPQRSTUVWXYZ23456789` (no 0/O/1/I). The room lives as long as this connection.
  The relay sends `PING` every 25 s. When a joiner arrives it sends `CONN <token>`.
- `EKR1 JOIN <code>` → the relay tells the host `CONN <token>` and waits up to 15 s for the host's data line.
  `ERR NOROOM` / `ERR TIMEOUT` / `ERR FULL` on failure. On success `OK`, then raw bytes both ways.
- `EKR1 ACCEPT <token>` (host's data line for that joiner) → `OK`, then raw bytes both ways with the joiner.
Limits: 500 rooms, 8 waiting joiners per room, 30 min without traffic closes a pipe, 64 KB first-line cap.
The relay only forwards bytes; it doesn't read or store game data.

## Setup (`web/relay/setup.sh`, run by the VM's cloud-init)
Installs `ekrelay.py` to `/opt/ekrelay`, a systemd service `ekrelay` (restart always), and opens TCP 32200 in
the VM's own firewall (firewalld on Oracle Linux, iptables + netfilter-persistent on Ubuntu). The cloud firewall
(VCN Security List ingress rule, TCP 32200 from 0.0.0.0/0) is added by the owner in the console.

## Game side (next step, after the server's address is known)
- Host: "Host online" → `EkRelay` opens the control line, shows the code; for each `CONN` it opens an `ACCEPT`
  line and a local connection to 127.0.0.1:32124 (the game's own host socket) and pipes them.
- Joiner: "Join by code" → `EkRelay` opens a local listener on 127.0.0.1, the game's normal `joinHost` connects
  to it, and the listener pipes to a `JOIN <code>` line.
So the MP engine itself is untouched: to it the relay looks like a local connection.


## v2 host: Cloudflare Workers + Durable Objects (owner: Oracle's free tier was out of capacity)
Why: Cloudflare's free plan needs no card, has no capacity lottery and doesn't sleep. Workers speak HTTP/WebSocket
(not raw TCP), so the relay moves to WebSockets; the Python TCP relay stays in `web/relay/` as a fallback.
- `relay/cf/worker.js` + `wrangler.toml`: one Durable Object (`Room`, SQLite-backed as the free plan requires) per
  room code (`idFromName(code)`). URL `wss://ek-relay.<account>.workers.dev/?code=<CODE>&role=<role>`:
  - `role=host&dev=<deviceId>`: the host's control socket. A second host with another device id gets close code
    4409 (code taken → the phone picks a new code). Text `CONN <token>` per joiner; the host may send `PING`.
  - `role=join`: joiner. No host → close 4404. Else `CONN` goes to the host; the joiner's bytes are buffered until
    the host's `accept` socket arrives (15 s, else close 4408); then text `OK` and binary pass-through both ways.
  - `role=accept&token=<t>`: the host's data socket for that joiner.
  Binary WebSocket messages carry the game's TCP bytes unchanged.
- Deploy: `.github/workflows/relay.yml` runs `wrangler deploy` with repo secrets `CLOUDFLARE_API_TOKEN` +
  `CLOUDFLARE_ACCOUNT_ID` (skips cleanly while they're missing).
- Free-plan limits (Durable Objects): 100k requests/day, WebSocket messages count 20:1, 13k GB-s/day. A 2-player
  session is roughly 7k requests/hour, so ~14 hours of play a day for a small group.

## Game side (`EkRelay`, lan package)
Minimal RFC 6455 client over TLS (no libraries). Host online: control socket; per `CONN` an `accept` socket
bridged to 127.0.0.1:32124 (the game's own host socket). Join by code: `join` socket, wait for `OK`, then a local
listener on 127.0.0.1 that the game's normal `joinHost` connects to. The MP engine is unchanged; relay joiners
appear as 127.0.0.1, so loopback is never auto-approved nor saved as a friend.

## Friends over the relay (owner: "once they have played together once" no codes)
- Each phone has a private secret and a stable room code. Pairwise token `T(A→B) = sha256(secretA + ":" + codeB)`
  (first 16 bytes, hex): what A presents when joining B's room. It can't be reused against anyone else.
- The join URL carries `tok`, `mycode`, `name`. The Worker passes them to the host as `CONN <token> <tok> <mycode>
  <name>` (URL-encoded). The host maps its bridge socket's local port to the joiner's info (`EkRelay.joinerFor`),
  so `approveJoin` (which sees 127.0.0.1) can recognise a relay friend: a saved `relay:<code>` entry whose token
  matches is auto-approved. "Allow + add friend" saves the joiner as `relay:<their code>` with their token.
- The host's bridge sends `HI <T(host→joiner code)> <host code> <host name>` on the accept socket. The joiner saves
  the host as a relay friend automatically (as it does for LAN hosts). Next time either side joins the other from the
  Friends list, with no code and no approval prompt.
- Friends list: relay friends show online/offline from `GET /?code=X&role=status` (doesn't start a join); Join goes
  by code.
- Relay address baked in: `wss://ek-relay.knightdx91.workers.dev` (the owner's workers.dev subdomain).
Tested against the Worker in wrangler dev: status goes offline → online → offline; the host learns the joiner's
name/code/token (token matches); the joiner learns host token = T(host→joiner code); a different device's token
differs.

## Deployed (2026-09-23)
Owner added the two repo secrets; `relay.yml` deployed the Worker. Live checks against
`https://ek-relay.knightdx91.workers.dev`: `/` → "Exiled Kingdoms relay: ok"; `role=status` → offline for an
empty room; host socket gets `OK`; a join delivers `CONN <token> - - <name>` to the host; the accept socket pairs,
the joiner's early bytes arrive, and a 300 KB binary round trip matches.

## v3 — hibernation: an open room costs nothing (v66)
Owner: "do both" (idle rooms free, and the area-loading race). v2 held every socket with `ws.accept()` and kept pairing
state in memory, so a Durable Object stayed resident (billed duration) for as long as a room was open; always-open
rooms per player would have used the free plan's daily allowance.
- `relay/cf/worker.js` uses the WebSocket Hibernation API: `state.acceptWebSocket(ws, tags)` +
  `webSocketMessage/Close/Error`; the host's `PING` is answered by `setWebSocketAutoResponse(PING→PONG)` without
  waking the object. State lives on the sockets: host `["host"]` {dev}; joiner `["join", token]` {t0, paired, early
  bytes ≤ 1500 as a fallback copy}; host data socket `["accept", token]`. `status` = a `host` socket exists. The 15 s
  join timeout is a storage alarm. Protocol unchanged (OK / CONN token tok mycode name / 4404 / 4408 / 4409 / 4410),
  so v58–v65 phones keep working.
- Tested in wrangler dev (workerd): status offline→online→offline, PING→PONG auto-response, CONN carries tok/code/name,
  the joiner's early bytes arrive after pairing, 300 KB round trip, other device → 4409, wrong code → 4404, joiner
  after host left → 4404, a paired pipe survives the host's control socket closing; the game's own `EkRelay`
  (HostSession + openJoin) end to end through a local echo "game". (Local dev delivered the alarm's `close(4408)` as
  no close frame; the phone's own 20 s read timeout covers it, and `openJoin` now accepts only a text `OK`.)
- Game side: **Open to friends** (pref `ek_open_room`, default ON; lobby button). While you play, the auto-host opens
  the room quietly (`EkAuto.tick` → `EkRelay.openRoom(a, quiet)`), so friends see you online and join from their list.
  Host shows the code; Close room there switches the setting off (stays closed until opened again). Joining someone
  else closes it (`EkNat.onHostStop` → `closeRoom`). Reconnect backoff up to 5 min.
- v68 fix (owner: "I was already loaded in a game, I just had not clicked Host; once I clicked Host it changed"):
  the room was opened only by `EkAuto.tick` on the game thread, which pauses while the lobby activity is open, and
  only after the background host had started (a second tick). The lobby's 1 s tick now opens it too
  (`EkLobby.autoOpen`: a save is loaded, Open to friends ON, no room yet, not joined to someone else), and
  `openRoom` starts the background host on its own thread (never the UI thread).

## v4 — public rooms / Browse rooms (v70)
Owner: "can we have a scan for open rooms thing for the multiplayer? Not everyone has friends they can play with."
- Worker: one more object of the same class, named `__lobby__`, keeps `{code: {name, t}}` in storage. A host that
  connects with `public=1&name=…` stores `pub` on its room and registers with the lobby; the room's alarm re-registers
  every 10 min while the host is there (one wake-up per 10 min per public room); closing the host socket unregisters
  (the closing socket is excluded from the "was it replaced?" check). The lobby drops entries older than 25 min.
  `GET /?role=list` → newest first, up to 50 lines `CODE⇥urlencoded name`; only `list` reaches the lobby from outside.
- Game: **Public room** (pref `ek_public_room`, default OFF, opt-in because it shows your multiplayer name to
  strangers) adds `&public=1&name=` to the host URL; toggling reconnects the room so it's listed/unlisted at once.
  **Browse rooms** lists "<name>'s world" (your own code left out); tap → `joinCode` (the host still approves).
- Tested in wrangler dev: public room listed / private not / public joinable / unlisted when its host leaves; the
  game's `HostSession` with `extra` + `listRooms` end to end; the v3 regression suite unchanged.
