# Internet play without extra apps — automatic router port opening (UPnP IGD), v49

Owner request: friends in other states, each on home Wi-Fi, should connect with no Tailscale and no
manual port forwarding, for free. The approach: while you host, the game asks your home router to
forward the game port to your device (UPnP Internet Gateway Device, the same thing consoles use),
then shows the address friends type.

## Reversed: what the game's networking gives us (LanSessionManager, MP mod smali)
- `startHosting(String name, int maxPlayers)`: the host always listens on TCP `CHAT_PORT` =
  0x7d7c = **32124**, bound to 0.0.0.0 (`HOST ready port=32124 bind=0.0.0.0` in the owner's log).
  The field `hostPort` is set to 32124 there.
- UDP 32123 (`DISCOVERY_PORT`) is LAN broadcast discovery only; it isn't needed over the internet.
- `joinHost(String name, String host, int port)`: sets `hostPort = port`, then
  `socket.connect(new InetSocketAddress(host, hostPort), 10000)`. Plain TCP, 10 s timeout.
- The lobby's "Join by IP" (`lambda$promptJoinByIp$0`) always passes 32124; friends saved by
  `EkFriends` go through `joinHostAsync` → `joinHost` with the stored host string.
- `stopAll()` → `stopAllInternal(true,true)`. `stopAllInternal` is also called from inside
  `startHosting` (restart), `joinHost` and error paths, so the router mapping is tied to whether we're
  actually hosting (checked on a timer), not to one call site.
- Hosts already have join approval (shared-world phase A), so an open port doesn't let strangers in
  without the host accepting them.

## Design
New `EkNat` (lan package, plain Java, no libraries). All router work runs on one background worker
thread, in order.
1. **Discover**: SSDP `M-SEARCH` to 239.255.255.250:1900 for `InternetGatewayDevice:1`, `:2` and
   `WANIPConnection:1`, three rounds of 2 s each; take the first `LOCATION:` header.
2. **Describe**: GET the XML; find a `<service>` whose type is `WANIPConnection:1|2` or
   `WANPPPConnection:1`; resolve its `controlURL` against `URLBase` / the location.
3. **Local address**: open a TCP socket to the router's control host and read `getLocalAddress()`, so
   the mapping points at the interface the router actually sees (Wi-Fi, not cellular or a VPN).
4. **Map**: SOAP `AddPortMapping` TCP external 32124 → local:32124, description "Exiled Kingdoms",
   lease 3600 s. If the router rejects leases (error 725), use lease 0. If external 32124 is taken
   (error 718, e.g. another device in the house hosting), try 32125..32134. Renewed every 20 min
   while hosting.
5. **Public address**: SOAP `GetExternalIPAddress`. If it's private (10/8, 172.16/12, 192.168/16) or
   carrier-shared (100.64/10), the internet provider puts you behind a shared address (CGNAT or a
   second router) and direct connections can't work; the lobby says so and points to Tailscale. No
   third-party "what's my IP" service is contacted.
6. **Close**: every 60 s the worker checks `LanSessionManager.isHosting()`; when hosting has stopped it
   sends `DeletePortMapping`. `stopAll()` also queues the delete right away. If the app is killed, the
   one-hour lease makes the router drop the mapping on its own.

## UI
- "My address" gets an **Internet (friends anywhere)** line first: `73.12.34.56` (or
  `73.12.34.56:32125` when a fallback port was needed), or a plain-language reason it isn't
  available (router didn't answer / UPnP off, shared address from the provider, still working on it,
  not hosting).
- Joining accepts `host:port`: the `joinHost` hook splits a trailing `:port` off the host string, so
  friends type exactly what the host's lobby shows. A bare IP still uses 32124.

## Can't be verified from the build machine
There's no real home router here, only a fake router run offline. Whether it works depends on the
owner's router (UPnP on) and provider (no CGNAT). Tested offline against a fake router (SSDP responder
+ SOAP) written in Python: discovery, description parsing, mapping, conflict fallback, lease-725
fallback, CGNAT detection and delete.
