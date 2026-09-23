#!/usr/bin/env python3
"""Exiled Kingdoms online relay (deobf/RELAY_SPEC.md).

Players on any network connect OUT to this server; it pipes the game's TCP stream between the host and the
people joining by room code. It only forwards bytes. Python 3.6+ standard library only.
"""
import asyncio
import os
import random
import sys
import time

PORT = int(os.environ.get("EKR_PORT", "32200"))
ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"
MAX_ROOMS = 500
MAX_WAITING = 8
JOIN_WAIT = 15.0
IDLE = 30 * 60.0
PING_EVERY = 25.0

rooms = {}      # code -> Room
waiting = {}    # token -> (future, room)


def log(*a):
    print(time.strftime("%Y-%m-%d %H:%M:%S"), *a, flush=True)


class Room:
    def __init__(self, code, writer):
        self.code = code
        self.writer = writer
        self.pending = set()


def new_code():
    for _ in range(1000):
        c = "".join(random.choice(ALPHABET) for _ in range(6))
        if c not in rooms:
            return c
    return None


async def pipe(reader, writer):
    try:
        while True:
            data = await asyncio.wait_for(reader.read(65536), timeout=IDLE)
            if not data:
                break
            writer.write(data)
            await writer.drain()
    except Exception:
        pass
    finally:
        try:
            writer.close()
        except Exception:
            pass


async def bridge(r1, w1, r2, w2):
    await asyncio.gather(pipe(r1, w2), pipe(r2, w1))


async def send(writer, line):
    writer.write((line + "\n").encode())
    await writer.drain()


async def handle(reader, writer):
    peer = writer.get_extra_info("peername")
    try:
        first = await asyncio.wait_for(reader.readline(), timeout=20)
    except Exception:
        writer.close()
        return
    if len(first) > 65536:
        writer.close()
        return
    parts = first.decode("utf-8", "replace").strip().split()
    if len(parts) < 2 or parts[0] != "EKR1":
        writer.close()
        return
    cmd = parts[1].upper()
    arg = parts[2].upper() if len(parts) > 2 else ""

    if cmd == "HOST":
        if len(rooms) >= MAX_ROOMS:
            await send(writer, "ERR FULL")
            writer.close()
            return
        code = arg if (len(arg) == 6 and all(ch in ALPHABET for ch in arg) and arg not in rooms) else new_code()
        if code is None:
            await send(writer, "ERR FULL")
            writer.close()
            return
        room = Room(code, writer)
        rooms[code] = room
        log("room", code, "opened by", peer, "rooms:", len(rooms))
        await send(writer, "OK " + code)

        async def pinger():
            while rooms.get(code) is room:
                await asyncio.sleep(PING_EVERY)
                try:
                    await send(writer, "PING")
                except Exception:
                    break

        pt = asyncio.ensure_future(pinger())
        try:
            while True:
                line = await asyncio.wait_for(reader.readline(), timeout=PING_EVERY * 4)
                if not line:
                    break
        except Exception:
            pass
        finally:
            pt.cancel()
            if rooms.get(code) is room:
                del rooms[code]
            for tok in list(room.pending):
                w = waiting.pop(tok, None)
                if w and not w[0].done():
                    w[0].set_result(None)
            log("room", code, "closed", "rooms:", len(rooms))
            try:
                writer.close()
            except Exception:
                pass
        return

    if cmd == "JOIN":
        room = rooms.get(arg)
        if room is None:
            await send(writer, "ERR NOROOM")
            writer.close()
            return
        if len(room.pending) >= MAX_WAITING:
            await send(writer, "ERR FULL")
            writer.close()
            return
        token = "%016x" % random.getrandbits(64)
        fut = asyncio.get_event_loop().create_future()
        waiting[token] = (fut, room)
        room.pending.add(token)
        try:
            await send(room.writer, "CONN " + token)
            got = await asyncio.wait_for(fut, timeout=JOIN_WAIT)
        except Exception:
            got = None
        finally:
            waiting.pop(token, None)
            room.pending.discard(token)
        if got is None:
            try:
                await send(writer, "ERR TIMEOUT")
            except Exception:
                pass
            writer.close()
            return
        hr, hw, done = got
        try:
            await send(writer, "OK")
            await send(hw, "OK")
            log("room", room.code, "joined by", peer)
            await bridge(reader, writer, hr, hw)
        finally:
            if not done.done():
                done.set_result(True)
        return

    if cmd == "ACCEPT":
        w = waiting.get(arg.lower())
        if w is None or w[0].done():
            await send(writer, "ERR NOJOIN")
            writer.close()
            return
        done = asyncio.get_event_loop().create_future()
        w[0].set_result((reader, writer, done))
        await done  # the JOIN handler pipes this connection; stay alive until it's finished
        return

    writer.close()


def main():
    loop = asyncio.get_event_loop()
    server = loop.run_until_complete(asyncio.start_server(handle, "0.0.0.0", PORT))
    log("Exiled Kingdoms relay listening on port", PORT)
    try:
        loop.run_forever()
    finally:
        server.close()


if __name__ == "__main__":
    sys.exit(main())
