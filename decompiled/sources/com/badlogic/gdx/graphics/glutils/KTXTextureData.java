package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.CubemapData;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.ETC1;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.n0;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class KTXTextureData implements TextureData, CubemapData {
    private static final int GL_TEXTURE_1D = 4660;
    private static final int GL_TEXTURE_1D_ARRAY_EXT = 4660;
    private static final int GL_TEXTURE_2D_ARRAY_EXT = 4660;
    private static final int GL_TEXTURE_3D = 4660;
    private ByteBuffer compressedData;
    private a file;
    private int glBaseInternalFormat;
    private int glFormat;
    private int glInternalFormat;
    private int glType;
    private int glTypeSize;
    private int imagePos;
    private int numberOfArrayElements;
    private int numberOfFaces;
    private int numberOfMipmapLevels;
    private boolean useMipMaps;
    private int pixelWidth = -1;
    private int pixelHeight = -1;
    private int pixelDepth = -1;

    public KTXTextureData(a aVar, boolean z2) {
        this.file = aVar;
        this.useMipMaps = z2;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public void consumeCubemapData() {
        consumeCustomData(GL20.GL_TEXTURE_CUBE_MAP);
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int i2) {
        boolean z2;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z3;
        if (this.compressedData == null) {
            throw new m("Call prepare() before calling consumeCompressedData()");
        }
        IntBuffer intBufferG = BufferUtils.g(16);
        int i7 = this.glType;
        int i8 = 1;
        if (i7 != 0 && this.glFormat != 0) {
            z2 = false;
        } else {
            if (i7 + this.glFormat != 0) {
                throw new m("either both or none of glType, glFormat must be zero");
            }
            z2 = true;
        }
        if (this.pixelHeight > 0) {
            i4 = 3553;
            i3 = 2;
        } else {
            i3 = 1;
            i4 = 4660;
        }
        if (this.pixelDepth > 0) {
            i3 = 3;
            i4 = 4660;
        }
        int i9 = this.numberOfFaces;
        if (i9 == 6) {
            if (i3 != 2) {
                throw new m("cube map needs 2D faces");
            }
            i4 = 34067;
        } else if (i9 != 1) {
            throw new m("numberOfFaces must be either 1 or 6");
        }
        if (this.numberOfArrayElements > 0) {
            if (i4 != 4660 && i4 != 3553) {
                throw new m("No API for 3D and cube arrays yet");
            }
            i3++;
            i4 = 4660;
        }
        if (i4 == 4660) {
            throw new m("Unsupported texture format (only 2D texture are supported in LibGdx for the time being)");
        }
        int i10 = GL20.GL_TEXTURE_CUBE_MAP_POSITIVE_X;
        if (i9 != 6 || i2 == 34067) {
            if (i9 != 6 || i2 != 34067) {
                if (i2 != i4 && (34069 > i2 || i2 > 34074 || i2 != 3553)) {
                    throw new m("Invalid target requested : 0x" + Integer.toHexString(i2) + ", expecting : 0x" + Integer.toHexString(i4));
                }
                i10 = i2;
            }
            i5 = -1;
        } else {
            if (34069 > i2 || i2 > 34074) {
                throw new m("You must specify either GL_TEXTURE_CUBE_MAP to bind all 6 faces of the cube or the requested face GL_TEXTURE_CUBE_MAP_POSITIVE_X and followings.");
            }
            i5 = i2 - GL20.GL_TEXTURE_CUBE_MAP_POSITIVE_X;
        }
        Gdx.gl.glGetIntegerv(GL20.GL_UNPACK_ALIGNMENT, intBufferG);
        int i11 = intBufferG.get(0);
        int i12 = 4;
        if (i11 != 4) {
            Gdx.gl.glPixelStorei(GL20.GL_UNPACK_ALIGNMENT, 4);
        }
        int i13 = this.glInternalFormat;
        int i14 = this.glFormat;
        int i15 = this.imagePos;
        int i16 = 0;
        while (i16 < this.numberOfMipmapLevels) {
            int iMax = Math.max(i8, this.pixelWidth >> i16);
            int iMax2 = Math.max(i8, this.pixelHeight >> i16);
            Math.max(i8, this.pixelDepth >> i16);
            this.compressedData.position(i15);
            int i17 = this.compressedData.getInt();
            int i18 = (i17 + 3) & (-4);
            i15 += i12;
            int i19 = 0;
            while (i19 < this.numberOfFaces) {
                this.compressedData.position(i15);
                i15 += i18;
                if (i5 == -1 || i5 == i19) {
                    ByteBuffer byteBufferSlice = this.compressedData.slice();
                    byteBufferSlice.limit(i18);
                    i6 = i5;
                    if (i3 != 1 && i3 == 2) {
                        int i20 = this.numberOfArrayElements;
                        if (i20 > 0) {
                            iMax2 = i20;
                        }
                        if (!z2) {
                            z3 = z2;
                            Gdx.gl.glTexImage2D(i10 + i19, i16, i13, iMax, iMax2, 0, i14, this.glType, byteBufferSlice);
                        } else if (i13 == ETC1.ETC1_RGB8_OES) {
                            z3 = z2;
                            if (Gdx.graphics.supportsExtension("GL_OES_compressed_ETC1_RGB8_texture")) {
                                Gdx.gl.glCompressedTexImage2D(i10 + i19, i16, i13, iMax, iMax2, 0, i17, byteBufferSlice);
                            } else {
                                Pixmap pixmapDecodeImage = ETC1.decodeImage(new ETC1.ETC1Data(iMax, iMax2, byteBufferSlice, 0), Pixmap.Format.RGB888);
                                Gdx.gl.glTexImage2D(i10 + i19, i16, pixmapDecodeImage.getGLInternalFormat(), pixmapDecodeImage.getWidth(), pixmapDecodeImage.getHeight(), 0, pixmapDecodeImage.getGLFormat(), pixmapDecodeImage.getGLType(), pixmapDecodeImage.getPixels());
                                pixmapDecodeImage.dispose();
                            }
                        } else {
                            z3 = z2;
                            Gdx.gl.glCompressedTexImage2D(i10 + i19, i16, i13, iMax, iMax2, 0, i17, byteBufferSlice);
                        }
                    }
                    i19++;
                    i5 = i6;
                    z2 = z3;
                } else {
                    i6 = i5;
                }
                z3 = z2;
                i19++;
                i5 = i6;
                z2 = z3;
            }
            i16++;
            z2 = z2;
            i8 = 1;
            i12 = 4;
        }
        if (i11 != i12) {
            Gdx.gl.glPixelStorei(GL20.GL_UNPACK_ALIGNMENT, i11);
        }
        if (useMipMaps()) {
            Gdx.gl.glGenerateMipmap(i10);
        }
        disposePreparedData();
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new m("This TextureData implementation does not return a Pixmap");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        throw new m("This TextureData implementation does not return a Pixmap");
    }

    public void disposePreparedData() {
        ByteBuffer byteBuffer = this.compressedData;
        if (byteBuffer != null) {
            BufferUtils.f(byteBuffer);
        }
        this.compressedData = null;
    }

    public ByteBuffer getData(int i2, int i3) {
        int i4 = this.imagePos;
        for (int i5 = 0; i5 < this.numberOfMipmapLevels; i5++) {
            int i6 = (this.compressedData.getInt(i4) + 3) & (-4);
            i4 += 4;
            if (i5 == i2) {
                for (int i7 = 0; i7 < this.numberOfFaces; i7++) {
                    if (i7 == i3) {
                        this.compressedData.position(i4);
                        ByteBuffer byteBufferSlice = this.compressedData.slice();
                        byteBufferSlice.limit(i6);
                        return byteBufferSlice;
                    }
                    i4 += i6;
                }
            } else {
                i4 = (i6 * this.numberOfFaces) + i4;
            }
        }
        return null;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap.Format getFormat() {
        throw new m("This TextureData implementation directly handles texture formats.");
    }

    public int getGlInternalFormat() {
        return this.glInternalFormat;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.pixelHeight;
    }

    public int getNumberOfFaces() {
        return this.numberOfFaces;
    }

    public int getNumberOfMipMapLevels() {
        return this.numberOfMipmapLevels;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public TextureData.TextureDataType getType() {
        return TextureData.TextureDataType.Custom;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getWidth() {
        return this.pixelWidth;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isManaged() {
        return true;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return this.compressedData != null;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void prepare() throws Throwable {
        DataInputStream dataInputStream;
        if (this.compressedData != null) {
            throw new m("Already prepared");
        }
        a aVar = this.file;
        if (aVar == null) {
            throw new m("Need a file to load from");
        }
        if (aVar.name().endsWith(".zktx")) {
            byte[] bArr = new byte[GL20.GL_TEXTURE_MAG_FILTER];
            DataInputStream dataInputStream2 = null;
            try {
                try {
                    dataInputStream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(this.file.read())));
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
                    if (i2 == -1) {
                        break;
                    } else {
                        this.compressedData.put(bArr, 0, i2);
                    }
                }
                this.compressedData.position(0);
                ByteBuffer byteBuffer = this.compressedData;
                byteBuffer.limit(byteBuffer.capacity());
                n0.a(dataInputStream);
            } catch (Exception e3) {
                e = e3;
                dataInputStream2 = dataInputStream;
                throw new m("Couldn't load zktx file '" + this.file + "'", (Throwable) e);
            } catch (Throwable th2) {
                th = th2;
                dataInputStream2 = dataInputStream;
                n0.a(dataInputStream2);
                throw th;
            }
        } else {
            this.compressedData = ByteBuffer.wrap(this.file.readBytes());
        }
        if (this.compressedData.get() != -85) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 75) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 84) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 88) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 32) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 49) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 49) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != -69) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 13) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 10) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 26) {
            throw new m("Invalid KTX Header");
        }
        if (this.compressedData.get() != 10) {
            throw new m("Invalid KTX Header");
        }
        int i3 = this.compressedData.getInt();
        if (i3 != 67305985 && i3 != 16909060) {
            throw new m("Invalid KTX Header");
        }
        if (i3 != 67305985) {
            ByteBuffer byteBuffer2 = this.compressedData;
            ByteOrder byteOrderOrder = byteBuffer2.order();
            ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
            if (byteOrderOrder == byteOrder) {
                byteOrder = ByteOrder.LITTLE_ENDIAN;
            }
            byteBuffer2.order(byteOrder);
        }
        this.glType = this.compressedData.getInt();
        this.glTypeSize = this.compressedData.getInt();
        this.glFormat = this.compressedData.getInt();
        this.glInternalFormat = this.compressedData.getInt();
        this.glBaseInternalFormat = this.compressedData.getInt();
        this.pixelWidth = this.compressedData.getInt();
        this.pixelHeight = this.compressedData.getInt();
        this.pixelDepth = this.compressedData.getInt();
        this.numberOfArrayElements = this.compressedData.getInt();
        this.numberOfFaces = this.compressedData.getInt();
        int i4 = this.compressedData.getInt();
        this.numberOfMipmapLevels = i4;
        if (i4 == 0) {
            this.numberOfMipmapLevels = 1;
            this.useMipMaps = true;
        }
        this.imagePos = this.compressedData.position() + this.compressedData.getInt();
        if (this.compressedData.isDirect()) {
            return;
        }
        int i5 = this.imagePos;
        for (int i6 = 0; i6 < this.numberOfMipmapLevels; i6++) {
            i5 += (((this.compressedData.getInt(i5) + 3) & (-4)) * this.numberOfFaces) + 4;
        }
        this.compressedData.limit(i5);
        this.compressedData.position(0);
        ByteBuffer byteBufferH = BufferUtils.h(i5);
        byteBufferH.order(this.compressedData.order());
        byteBufferH.put(this.compressedData);
        this.compressedData = byteBufferH;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return this.useMipMaps;
    }
}
