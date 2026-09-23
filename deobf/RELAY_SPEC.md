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
