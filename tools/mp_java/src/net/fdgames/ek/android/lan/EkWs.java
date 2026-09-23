package net.fdgames.ek.android.lan;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.security.SecureRandom;

/**
 * Minimal WebSocket client (RFC 6455) for the online relay (deobf/RELAY_SPEC.md). No libraries: TLS via the
 * platform's default SSLSocketFactory for wss://, plain sockets for ws:// (tests). Client frames are masked
 * as the RFC requires; fragmented messages are reassembled; pings are answered.
 */
final class EkWs {
    static final int TEXT = 1;
    static final int BINARY = 2;

    private static final SecureRandom RND = new SecureRandom();
    private final Socket sock;
    private final DataInputStream in;
    private final OutputStream out;
    volatile int closeCode;

    private EkWs(Socket s, InputStream in) throws IOException {
        this.sock = s;
        this.in = new DataInputStream(in);
        this.out = s.getOutputStream();
    }

    static EkWs connect(String url, int timeoutMs) throws IOException {
        URI u = URI.create(url);
        boolean tls = "wss".equalsIgnoreCase(u.getScheme());
        int port = u.getPort() > 0 ? u.getPort() : (tls ? 443 : 80);
        Socket raw = new Socket();
        raw.connect(new InetSocketAddress(u.getHost(), port), timeoutMs);
        Socket s = raw;
        if (tls) {
            s = ((javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault())
                    .createSocket(raw, u.getHost(), port, true);
            ((javax.net.ssl.SSLSocket) s).startHandshake();
        }
        s.setTcpNoDelay(true);
        s.setSoTimeout(timeoutMs);
        byte[] k = new byte[16];
        RND.nextBytes(k);
        String path = (u.getRawPath() == null || u.getRawPath().length() == 0 ? "/" : u.getRawPath())
                + (u.getRawQuery() != null ? "?" + u.getRawQuery() : "");
        String req = "GET " + path + " HTTP/1.1\r\nHost: " + u.getHost() + (u.getPort() > 0 ? ":" + port : "")
                + "\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Key: " + b64(k)
                + "\r\nSec-WebSocket-Version: 13\r\nUser-Agent: ExiledKingdoms\r\n\r\n";
        OutputStream o = s.getOutputStream();
        o.write(req.getBytes("US-ASCII"));
        o.flush();
        InputStream i = s.getInputStream();
        String status = readHttpLine(i);
        if (status == null || !status.contains(" 101")) {
            s.close();
            throw new IOException("relay said: " + status);
        }
        while (true) {
            String h = readHttpLine(i);
            if (h == null) {
                s.close();
                throw new IOException("relay closed during handshake");
            }
            if (h.length() == 0) {
                break;
            }
        }
        s.setSoTimeout(0);
        return new EkWs(s, i);
    }

    void setReadTimeout(int ms) {
        try {
            sock.setSoTimeout(ms);
        } catch (IOException e) {
            // ignore
        }
    }

    private static String readHttpLine(InputStream i) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = i.read()) != -1) {
            if (c == '\n') {
                int n = sb.length();
                if (n > 0 && sb.charAt(n - 1) == '\r') {
                    sb.setLength(n - 1);
                }
                return sb.toString();
            }
            if (sb.length() > 8192) {
                throw new IOException("header too long");
            }
            sb.append((char) c);
        }
        return null;
    }

    void sendText(String t) throws IOException {
        byte[] b = t.getBytes("UTF-8");
        send(TEXT, b, 0, b.length);
    }

    void sendBinary(byte[] b, int off, int len) throws IOException {
        send(BINARY, b, off, len);
    }

    private synchronized void send(int op, byte[] b, int off, int len) throws IOException {
        ByteArrayOutputStream f = new ByteArrayOutputStream(len + 14);
        f.write(0x80 | op);
        if (len < 126) {
            f.write(0x80 | len);
        } else if (len < 65536) {
            f.write(0x80 | 126);
            f.write(len >>> 8);
            f.write(len);
        } else {
            f.write(0x80 | 127);
            for (int s = 56; s >= 0; s -= 8) {
                f.write((int) ((long) len >>> s));
            }
        }
        byte[] m = new byte[4];
        RND.nextBytes(m);
        f.write(m, 0, 4);
        byte[] p = new byte[len];
        for (int k = 0; k < len; k++) {
            p[k] = (byte) (b[off + k] ^ m[k & 3]);
        }
        f.write(p, 0, len);
        out.write(f.toByteArray());
        out.flush();
    }

    /** Next data message: {opcode, payload}; null when the socket closed (closeCode set). */
    Object[] read() throws IOException {
        int msgOp = 0;
        ByteArrayOutputStream acc = null;
        while (true) {
            int b0 = in.read();
            if (b0 < 0) {
                return null;
            }
            int b1 = in.readUnsignedByte();
            boolean fin = (b0 & 0x80) != 0;
            int op = b0 & 0x0f;
            long len = b1 & 0x7f;
            if (len == 126) {
                len = in.readUnsignedShort();
            } else if (len == 127) {
                len = in.readLong();
            }
            byte[] mask = null;
            if ((b1 & 0x80) != 0) {
                mask = new byte[4];
                in.readFully(mask);
            }
            if (len > 16 * 1024 * 1024) {
                throw new IOException("frame too large");
            }
            byte[] p = new byte[(int) len];
            in.readFully(p);
            if (mask != null) {
                for (int k = 0; k < p.length; k++) {
                    p[k] ^= mask[k & 3];
                }
            }
            if (op == 8) {                       // close
                closeCode = p.length >= 2 ? ((p[0] & 0xff) << 8) | (p[1] & 0xff) : 1005;
                try {
                    send(8, p, 0, Math.min(2, p.length));
                } catch (IOException e) {
                    // ignore
                }
                close();
                return null;
            }
            if (op == 9) {                       // ping -> pong
                send(10, p, 0, p.length);
                continue;
            }
            if (op == 10) {
                continue;
            }
            if (op == 1 || op == 2) {
                msgOp = op;
                if (fin) {
                    return new Object[] {op, p};
                }
                acc = new ByteArrayOutputStream();
                acc.write(p, 0, p.length);
                continue;
            }
            if (op == 0 && acc != null) {        // continuation
                acc.write(p, 0, p.length);
                if (fin) {
                    return new Object[] {msgOp, acc.toByteArray()};
                }
            }
        }
    }

    void close() {
        try {
            sock.close();
        } catch (IOException e) {
            // ignore
        }
    }

    private static String b64(byte[] d) {
        String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < d.length; i += 3) {
            int n = (d[i] & 0xff) << 16 | (i + 1 < d.length ? (d[i + 1] & 0xff) << 8 : 0)
                    | (i + 2 < d.length ? (d[i + 2] & 0xff) : 0);
            sb.append(a.charAt(n >>> 18 & 63)).append(a.charAt(n >>> 12 & 63));
            sb.append(i + 1 < d.length ? a.charAt(n >>> 6 & 63) : '=');
            sb.append(i + 2 < d.length ? a.charAt(n & 63) : '=');
        }
        return sb.toString();
    }
}
