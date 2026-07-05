package com.badlogic.gdx.graphics.glutils;

import a0.j;
import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.n0;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ETC1 {
    public static int ETC1_RGB8_OES = 36196;
    public static int PKM_HEADER_SIZE = 16;

    public static Pixmap decodeImage(ETC1Data eTC1Data, Pixmap.Format format) {
        int widthPKM;
        int i2;
        int heightPKM;
        if (eTC1Data.hasPKMHeader()) {
            widthPKM = getWidthPKM(eTC1Data.compressedData, 0);
            heightPKM = getHeightPKM(eTC1Data.compressedData, 0);
            i2 = 16;
        } else {
            widthPKM = eTC1Data.width;
            i2 = 0;
            heightPKM = eTC1Data.height;
        }
        int pixelSize = getPixelSize(format);
        Pixmap pixmap = new Pixmap(widthPKM, heightPKM, format);
        decodeImage(eTC1Data.compressedData, i2, pixmap.getPixels(), 0, widthPKM, heightPKM, pixelSize);
        return pixmap;
    }

    private static native void decodeImage(ByteBuffer byteBuffer, int i2, ByteBuffer byteBuffer2, int i3, int i4, int i5, int i6);

    public static ETC1Data encodeImage(Pixmap pixmap) {
        ByteBuffer byteBufferEncodeImage = encodeImage(pixmap.getPixels(), 0, pixmap.getWidth(), pixmap.getHeight(), getPixelSize(pixmap.getFormat()));
        BufferUtils.i(byteBufferEncodeImage);
        return new ETC1Data(pixmap.getWidth(), pixmap.getHeight(), byteBufferEncodeImage, 0);
    }

    private static native ByteBuffer encodeImage(ByteBuffer byteBuffer, int i2, int i3, int i4, int i5);

    public static ETC1Data encodeImagePKM(Pixmap pixmap) {
        ByteBuffer byteBufferEncodeImagePKM = encodeImagePKM(pixmap.getPixels(), 0, pixmap.getWidth(), pixmap.getHeight(), getPixelSize(pixmap.getFormat()));
        BufferUtils.i(byteBufferEncodeImagePKM);
        return new ETC1Data(pixmap.getWidth(), pixmap.getHeight(), byteBufferEncodeImagePKM, 16);
    }

    private static native ByteBuffer encodeImagePKM(ByteBuffer byteBuffer, int i2, int i3, int i4, int i5);

    public static native void formatHeader(ByteBuffer byteBuffer, int i2, int i3, int i4);

    public static native int getCompressedDataSize(int i2, int i3);

    static native int getHeightPKM(ByteBuffer byteBuffer, int i2);

    private static int getPixelSize(Pixmap.Format format) {
        if (format == Pixmap.Format.RGB565) {
            return 2;
        }
        if (format == Pixmap.Format.RGB888) {
            return 3;
        }
        throw new m("Can only handle RGB565 or RGB888 images");
    }

    static native int getWidthPKM(ByteBuffer byteBuffer, int i2);

    static native boolean isValidPKM(ByteBuffer byteBuffer, int i2);

    public static final class ETC1Data implements i {
        public final ByteBuffer compressedData;
        public final int dataOffset;
        public final int height;
        public final int width;

        public ETC1Data(int i2, int i3, ByteBuffer byteBuffer, int i4) {
            this.width = i2;
            this.height = i3;
            this.compressedData = byteBuffer;
            this.dataOffset = i4;
            checkNPOT();
        }

        private void checkNPOT() {
            if (j.e(this.width) && j.e(this.height)) {
                return;
            }
            System.out.println("ETC1Data warning: non-power-of-two ETC1 textures may crash the driver of PowerVR GPUs");
        }

        @Override // com.badlogic.gdx.utils.i
        public void dispose() {
            BufferUtils.f(this.compressedData);
        }

        public boolean hasPKMHeader() {
            return this.dataOffset == 16;
        }

        public String toString() {
            if (!hasPKMHeader()) {
                return "raw [" + this.width + "x" + this.height + "], compressed: " + (this.compressedData.capacity() - ETC1.PKM_HEADER_SIZE);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ETC1.isValidPKM(this.compressedData, 0) ? "valid" : "invalid");
            sb.append(" pkm [");
            sb.append(ETC1.getWidthPKM(this.compressedData, 0));
            sb.append("x");
            sb.append(ETC1.getHeightPKM(this.compressedData, 0));
            sb.append("], compressed: ");
            sb.append(this.compressedData.capacity() - ETC1.PKM_HEADER_SIZE);
            return sb.toString();
        }

        public void write(a aVar) throws Throwable {
            DataOutputStream dataOutputStream;
            byte[] bArr = new byte[GL20.GL_TEXTURE_MAG_FILTER];
            this.compressedData.position(0);
            ByteBuffer byteBuffer = this.compressedData;
            byteBuffer.limit(byteBuffer.capacity());
            DataOutputStream dataOutputStream2 = null;
            try {
                try {
                    dataOutputStream = new DataOutputStream(new GZIPOutputStream(aVar.write(false)));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            try {
                dataOutputStream.writeInt(this.compressedData.capacity());
                int i2 = 0;
                while (i2 != this.compressedData.capacity()) {
                    int iMin = Math.min(this.compressedData.remaining(), GL20.GL_TEXTURE_MAG_FILTER);
                    this.compressedData.get(bArr, 0, iMin);
                    dataOutputStream.write(bArr, 0, iMin);
                    i2 += iMin;
                }
                n0.a(dataOutputStream);
                this.compressedData.position(this.dataOffset);
                ByteBuffer byteBuffer2 = this.compressedData;
                byteBuffer2.limit(byteBuffer2.capacity());
            } catch (Exception e3) {
                e = e3;
                throw new m("Couldn't write PKM file to '" + aVar + "'", (Throwable) e);
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream2 = dataOutputStream;
                n0.a(dataOutputStream2);
                throw th;
            }
        }

        public ETC1Data(a aVar) throws Throwable {
            DataInputStream dataInputStream;
            byte[] bArr = new byte[GL20.GL_TEXTURE_MAG_FILTER];
            DataInputStream dataInputStream2 = null;
            try {
                try {
                    dataInputStream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(aVar.read())));
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                this.compressedData = BufferUtils.h(dataInputStream.readInt());
                while (true) {
                    int i2 = dataInputStream.read(bArr);
                    if (i2 != -1) {
                        this.compressedData.put(bArr, 0, i2);
                    } else {
                        this.compressedData.position(0);
                        ByteBuffer byteBuffer = this.compressedData;
                        byteBuffer.limit(byteBuffer.capacity());
                        n0.a(dataInputStream);
                        this.width = ETC1.getWidthPKM(this.compressedData, 0);
                        this.height = ETC1.getHeightPKM(this.compressedData, 0);
                        int i3 = ETC1.PKM_HEADER_SIZE;
                        this.dataOffset = i3;
                        this.compressedData.position(i3);
                        checkNPOT();
                        return;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                dataInputStream2 = dataInputStream;
                throw new m("Couldn't load pkm file '" + aVar + "'", (Throwable) e);
            } catch (Throwable th2) {
                th = th2;
                dataInputStream2 = dataInputStream;
                n0.a(dataInputStream2);
                throw th;
            }
        }
    }
}
