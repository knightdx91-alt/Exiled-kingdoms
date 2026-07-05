package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.e;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.n0;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PixmapIO {

    private static class CIM {
        private static final int BUFFER_SIZE = 32000;
        private static final byte[] writeBuffer = new byte[BUFFER_SIZE];
        private static final byte[] readBuffer = new byte[BUFFER_SIZE];

        private CIM() {
        }

        /* JADX WARN: Not initialized variable reg: 1, insn: 0x0059: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]) (LINE:90), block:B:16:0x0059 */
        public static Pixmap read(a aVar) throws Throwable {
            Exception e2;
            Closeable closeable;
            Closeable closeable2 = null;
            try {
                try {
                    DataInputStream dataInputStream = new DataInputStream(new InflaterInputStream(new BufferedInputStream(aVar.read())));
                    try {
                        Pixmap pixmap = new Pixmap(dataInputStream.readInt(), dataInputStream.readInt(), Pixmap.Format.fromGdx2DPixmapFormat(dataInputStream.readInt()));
                        ByteBuffer pixels = pixmap.getPixels();
                        pixels.position(0);
                        pixels.limit(pixels.capacity());
                        synchronized (readBuffer) {
                            while (true) {
                                try {
                                    byte[] bArr = readBuffer;
                                    int i2 = dataInputStream.read(bArr);
                                    if (i2 > 0) {
                                        pixels.put(bArr, 0, i2);
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        }
                        pixels.position(0);
                        pixels.limit(pixels.capacity());
                        n0.a(dataInputStream);
                        return pixmap;
                    } catch (Exception e3) {
                        e2 = e3;
                        throw new m("Couldn't read Pixmap from file '" + aVar + "'", (Throwable) e2);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable2 = closeable;
                    n0.a(closeable2);
                    throw th;
                }
            } catch (Exception e4) {
                e2 = e4;
            } catch (Throwable th3) {
                th = th3;
                n0.a(closeable2);
                throw th;
            }
        }

        public static void write(a aVar, Pixmap pixmap) throws Throwable {
            DataOutputStream dataOutputStream;
            DataOutputStream dataOutputStream2 = null;
            try {
                try {
                    dataOutputStream = new DataOutputStream(new DeflaterOutputStream(aVar.write(false)));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            try {
                dataOutputStream.writeInt(pixmap.getWidth());
                dataOutputStream.writeInt(pixmap.getHeight());
                dataOutputStream.writeInt(Pixmap.Format.toGdx2DPixmapFormat(pixmap.getFormat()));
                ByteBuffer pixels = pixmap.getPixels();
                pixels.position(0);
                pixels.limit(pixels.capacity());
                int iCapacity = pixels.capacity() % BUFFER_SIZE;
                int iCapacity2 = pixels.capacity() / BUFFER_SIZE;
                synchronized (writeBuffer) {
                    for (int i2 = 0; i2 < iCapacity2; i2++) {
                        try {
                            byte[] bArr = writeBuffer;
                            pixels.get(bArr);
                            dataOutputStream.write(bArr);
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                    byte[] bArr2 = writeBuffer;
                    pixels.get(bArr2, 0, iCapacity);
                    dataOutputStream.write(bArr2, 0, iCapacity);
                }
                pixels.position(0);
                pixels.limit(pixels.capacity());
                n0.a(dataOutputStream);
            } catch (Exception e3) {
                e = e3;
                throw new m("Couldn't write Pixmap to file '" + aVar + "'", (Throwable) e);
            } catch (Throwable th3) {
                th = th3;
                dataOutputStream2 = dataOutputStream;
                n0.a(dataOutputStream2);
                throw th;
            }
        }
    }

    public static class PNG implements i {
        private static final byte COLOR_ARGB = 6;
        private static final byte COMPRESSION_DEFLATE = 0;
        private static final byte FILTER_NONE = 0;
        private static final int IDAT = 1229209940;
        private static final int IEND = 1229278788;
        private static final int IHDR = 1229472850;
        private static final byte INTERLACE_NONE = 0;
        private static final byte PAETH = 4;
        private static final byte[] SIGNATURE = {-119, 80, 78, 71, 13, 10, 26, 10};
        private final ChunkBuffer buffer;
        private e curLineBytes;
        private final Deflater deflater;
        private boolean flipY;
        private int lastLineLen;
        private e lineOutBytes;
        private e prevLineBytes;

        static class ChunkBuffer extends DataOutputStream {
            final ByteArrayOutputStream buffer;
            final CRC32 crc;

            ChunkBuffer(int i2) {
                this(new ByteArrayOutputStream(i2), new CRC32());
            }

            public void endChunk(DataOutputStream dataOutputStream) throws IOException {
                flush();
                dataOutputStream.writeInt(this.buffer.size() - 4);
                this.buffer.writeTo(dataOutputStream);
                dataOutputStream.writeInt((int) this.crc.getValue());
                this.buffer.reset();
                this.crc.reset();
            }

            private ChunkBuffer(ByteArrayOutputStream byteArrayOutputStream, CRC32 crc32) {
                super(new CheckedOutputStream(byteArrayOutputStream, crc32));
                this.buffer = byteArrayOutputStream;
                this.crc = crc32;
            }
        }

        public PNG() {
            this(GL20.GL_COLOR_BUFFER_BIT);
        }

        @Override // com.badlogic.gdx.utils.i
        public void dispose() {
            this.deflater.end();
        }

        public void setCompression(int i2) {
            this.deflater.setLevel(i2);
        }

        public void setFlipY(boolean z2) {
            this.flipY = z2;
        }

        public void write(a aVar, Pixmap pixmap) {
            OutputStream outputStreamWrite = aVar.write(false);
            try {
                write(outputStreamWrite, pixmap);
            } finally {
                n0.a(outputStreamWrite);
            }
        }

        public PNG(int i2) {
            this.flipY = true;
            this.buffer = new ChunkBuffer(i2);
            this.deflater = new Deflater();
        }

        public void write(OutputStream outputStream, Pixmap pixmap) throws IOException {
            byte[] bArrA;
            byte[] bArrA2;
            byte[] bArrA3;
            boolean z2;
            int i2;
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(this.buffer, this.deflater);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.write(SIGNATURE);
            this.buffer.writeInt(IHDR);
            this.buffer.writeInt(pixmap.getWidth());
            this.buffer.writeInt(pixmap.getHeight());
            this.buffer.writeByte(8);
            this.buffer.writeByte(6);
            int i3 = 0;
            this.buffer.writeByte(0);
            this.buffer.writeByte(0);
            this.buffer.writeByte(0);
            this.buffer.endChunk(dataOutputStream);
            this.buffer.writeInt(IDAT);
            this.deflater.reset();
            int width = pixmap.getWidth() * 4;
            e eVar = this.lineOutBytes;
            if (eVar == null) {
                e eVar2 = new e(width);
                this.lineOutBytes = eVar2;
                bArrA = eVar2.f1783a;
                e eVar3 = new e(width);
                this.curLineBytes = eVar3;
                bArrA2 = eVar3.f1783a;
                e eVar4 = new e(width);
                this.prevLineBytes = eVar4;
                bArrA3 = eVar4.f1783a;
            } else {
                bArrA = eVar.a(width);
                bArrA2 = this.curLineBytes.a(width);
                bArrA3 = this.prevLineBytes.a(width);
                int i4 = this.lastLineLen;
                for (int i5 = 0; i5 < i4; i5++) {
                    bArrA3[i5] = 0;
                }
            }
            this.lastLineLen = width;
            ByteBuffer pixels = pixmap.getPixels();
            int iPosition = pixels.position();
            int i6 = 1;
            boolean z3 = pixmap.getFormat() == Pixmap.Format.RGBA8888;
            int height = pixmap.getHeight();
            int i7 = 0;
            while (i7 < height) {
                int i8 = this.flipY ? (height - i7) - i6 : i7;
                if (z3) {
                    pixels.position(i8 * width);
                    pixels.get(bArrA2, i3, width);
                    i2 = i3;
                    z2 = z3;
                } else {
                    int i9 = i3;
                    int i10 = i9;
                    while (i9 < pixmap.getWidth()) {
                        int pixel = pixmap.getPixel(i9, i8);
                        bArrA2[i10] = (byte) ((pixel >> 24) & 255);
                        int i11 = i8;
                        bArrA2[i10 + 1] = (byte) ((pixel >> 16) & 255);
                        int i12 = i10 + 3;
                        bArrA2[i10 + 2] = (byte) ((pixel >> 8) & 255);
                        i10 += 4;
                        bArrA2[i12] = (byte) (pixel & 255);
                        i9++;
                        z3 = z3;
                        i8 = i11;
                    }
                    z2 = z3;
                    i2 = 0;
                }
                bArrA[i2] = (byte) (bArrA2[i2] - bArrA3[i2]);
                bArrA[1] = (byte) (bArrA2[1] - bArrA3[1]);
                bArrA[2] = (byte) (bArrA2[2] - bArrA3[2]);
                bArrA[3] = (byte) (bArrA2[3] - bArrA3[3]);
                int i13 = 4;
                while (i13 < width) {
                    int i14 = i13 - 4;
                    int i15 = bArrA2[i14] & 255;
                    int i16 = bArrA3[i13] & 255;
                    int i17 = bArrA3[i14] & 255;
                    int i18 = (i15 + i16) - i17;
                    int i19 = i18 - i15;
                    if (i19 < 0) {
                        i19 = -i19;
                    }
                    byte[] bArr = bArrA3;
                    int i20 = i18 - i16;
                    if (i20 < 0) {
                        i20 = -i20;
                    }
                    int i21 = i18 - i17;
                    if (i21 < 0) {
                        i21 = -i21;
                    }
                    bArrA[i13] = (byte) (bArrA2[i13] - ((i19 > i20 || i19 > i21) ? i20 <= i21 ? i16 : i17 : i15));
                    i13++;
                    bArrA3 = bArr;
                }
                byte[] bArr2 = bArrA3;
                deflaterOutputStream.write(4);
                i3 = 0;
                deflaterOutputStream.write(bArrA, 0, width);
                i7++;
                bArrA3 = bArrA2;
                z3 = z2;
                bArrA2 = bArr2;
                i6 = 1;
            }
            pixels.position(iPosition);
            deflaterOutputStream.finish();
            this.buffer.endChunk(dataOutputStream);
            this.buffer.writeInt(IEND);
            this.buffer.endChunk(dataOutputStream);
            outputStream.flush();
        }
    }

    public static Pixmap readCIM(a aVar) {
        return CIM.read(aVar);
    }

    public static void writeCIM(a aVar, Pixmap pixmap) throws Throwable {
        CIM.write(aVar, pixmap);
    }

    public static void writePNG(a aVar, Pixmap pixmap, int i2, boolean z2) {
        try {
            PNG png = new PNG((int) (pixmap.getWidth() * pixmap.getHeight() * 1.5f));
            try {
                png.setFlipY(z2);
                png.setCompression(i2);
                png.write(aVar, pixmap);
            } finally {
                png.dispose();
            }
        } catch (IOException e2) {
            throw new m(a.a.i(aVar, "Error writing PNG: "), (Throwable) e2);
        }
    }

    public static void writePNG(a aVar, Pixmap pixmap) {
        writePNG(aVar, pixmap, -1, false);
    }
}
