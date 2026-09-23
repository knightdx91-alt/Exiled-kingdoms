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
