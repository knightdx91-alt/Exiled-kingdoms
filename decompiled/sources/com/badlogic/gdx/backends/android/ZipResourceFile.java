package com.badlogic.gdx.backends.android;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.google.android.gms.drive.DriveFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ZipResourceFile {
    static final boolean LOGV = false;
    static final String LOG_TAG = "zipro";
    static final int kCDECRC = 16;
    static final int kCDECommentLen = 32;
    static final int kCDECompLen = 20;
    static final int kCDEExtraLen = 30;
    static final int kCDELen = 46;
    static final int kCDELocalOffset = 42;
    static final int kCDEMethod = 10;
    static final int kCDEModWhen = 12;
    static final int kCDENameLen = 28;
    static final int kCDESignature = 33639248;
    static final int kCDEUncompLen = 24;
    static final int kCompressDeflated = 8;
    static final int kCompressStored = 0;
    static final int kEOCDFileOffset = 16;
    static final int kEOCDLen = 22;
    static final int kEOCDNumEntries = 8;
    static final int kEOCDSignature = 101010256;
    static final int kEOCDSize = 12;
    static final int kLFHExtraLen = 28;
    static final int kLFHLen = 30;
    static final int kLFHNameLen = 26;
    static final int kLFHSignature = 67324752;
    static final int kMaxCommentLen = 65535;
    static final int kMaxEOCDSearch = 65557;
    static final int kZipEntryAdj = 10000;
    private HashMap<String, ZipEntryRO> mHashMap = new HashMap<>();
    public HashMap<File, ZipFile> mZipFiles = new HashMap<>();
    ByteBuffer mLEByteBuffer = ByteBuffer.allocate(4);

    public static final class ZipEntryRO {
        public long mCRC32;
        public long mCompressedLength;
        public final File mFile;
        public final String mFileName;
        public long mLocalHdrOffset;
        public int mMethod;
        public long mOffset = -1;
        public long mUncompressedLength;
        public long mWhenModified;
        public final String mZipFileName;

        public ZipEntryRO(String str, File file, String str2) {
            this.mFileName = str2;
            this.mZipFileName = str;
            this.mFile = file;
        }

        public AssetFileDescriptor getAssetFileDescriptor() {
            if (this.mMethod != 0) {
                return null;
            }
            try {
                return new AssetFileDescriptor(ParcelFileDescriptor.open(this.mFile, DriveFile.MODE_READ_ONLY), getOffset(), this.mUncompressedLength);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public long getOffset() {
            return this.mOffset;
        }

        public File getZipFile() {
            return this.mFile;
        }

        public String getZipFileName() {
            return this.mZipFileName;
        }

        public boolean isUncompressed() {
            return this.mMethod == 0;
        }

        public void setOffsetFromFile(RandomAccessFile randomAccessFile, ByteBuffer byteBuffer) {
            long j2 = this.mLocalHdrOffset;
            try {
                randomAccessFile.seek(j2);
                randomAccessFile.readFully(byteBuffer.array());
                if (byteBuffer.getInt(0) != ZipResourceFile.kLFHSignature) {
                    Log.w(ZipResourceFile.LOG_TAG, "didn't find signature at start of lfh");
                    throw new IOException();
                }
                this.mOffset = j2 + 30 + ((long) (byteBuffer.getShort(ZipResourceFile.kLFHNameLen) & 65535)) + ((long) (byteBuffer.getShort(28) & 65535));
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public ZipResourceFile(String str) throws IOException {
        addPatchFile(str);
    }

    private static int read4LE(RandomAccessFile randomAccessFile) {
        return swapEndian(randomAccessFile.readInt());
    }

    private static int swapEndian(int i2) {
        return ((i2 & 255) << 24) + ((65280 & i2) << 8) + ((16711680 & i2) >>> 8) + ((i2 >>> 24) & 255);
    }

    void addPatchFile(String str) throws IOException {
        String str2 = str;
        File file = new File(str2);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long length = randomAccessFile.length();
        if (length < 22) {
            randomAccessFile.close();
            throw new IOException();
        }
        long j2 = 65557 > length ? length : 65557L;
        randomAccessFile.seek(0L);
        int i2 = read4LE(randomAccessFile);
        if (i2 == kEOCDSignature) {
            Log.i(LOG_TAG, "Found Zip archive, but it looks empty");
            throw new IOException();
        }
        if (i2 != kLFHSignature) {
            Log.v(LOG_TAG, "Not a Zip archive");
            throw new IOException();
        }
        randomAccessFile.seek(length - j2);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) j2);
        byte[] bArrArray = byteBufferAllocate.array();
        randomAccessFile.readFully(bArrArray);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        int length2 = bArrArray.length - 22;
        while (length2 >= 0 && (bArrArray[length2] != 80 || byteBufferAllocate.getInt(length2) != kEOCDSignature)) {
            length2--;
        }
        if (length2 < 0) {
            Log.d(LOG_TAG, "Zip: EOCD not found, " + str2 + " is not zip");
        }
        short s = byteBufferAllocate.getShort(length2 + 8);
        long j3 = ((long) byteBufferAllocate.getInt(length2 + 12)) & 4294967295L;
        long j4 = ((long) byteBufferAllocate.getInt(length2 + 16)) & 4294967295L;
        if (j4 + j3 > length) {
            Log.w(LOG_TAG, "bad offsets (dir " + j4 + ", size " + j3 + ", eocd " + length2 + ")");
            throw new IOException();
        }
        if (s == 0) {
            Log.w(LOG_TAG, "empty archive?");
            throw new IOException();
        }
        MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, j4, j3);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        map.order(byteOrder);
        short s2 = 65535;
        byte[] bArr = new byte[65535];
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(30);
        byteBufferAllocate2.order(byteOrder);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < s) {
            if (map.getInt(i5) != kCDESignature) {
                Log.w(LOG_TAG, "Missed a central dir sig (at " + i5 + ")");
                throw new IOException();
            }
            int i6 = map.getShort(i5 + 28) & s2;
            int i7 = map.getShort(i5 + 30) & s2;
            int i8 = map.getShort(i5 + 32) & s2;
            map.position(i5 + kCDELen);
            map.get(bArr, i3, i6);
            map.position(i3);
            String str3 = new String(bArr, i3, i6);
            ZipEntryRO zipEntryRO = new ZipEntryRO(str2, file, str3);
            zipEntryRO.mMethod = map.getShort(i5 + 10) & 65535;
            zipEntryRO.mWhenModified = ((long) map.getInt(i5 + 12)) & 4294967295L;
            zipEntryRO.mCRC32 = map.getLong(i5 + 16) & 4294967295L;
            zipEntryRO.mCompressedLength = map.getLong(i5 + 20) & 4294967295L;
            zipEntryRO.mUncompressedLength = map.getLong(i5 + 24) & 4294967295L;
            zipEntryRO.mLocalHdrOffset = ((long) map.getInt(i5 + kCDELocalOffset)) & 4294967295L;
            byteBufferAllocate2.clear();
            zipEntryRO.setOffsetFromFile(randomAccessFile, byteBufferAllocate2);
            this.mHashMap.put(str3, zipEntryRO);
            i5 += i6 + kCDELen + i7 + i8;
            i4++;
            str2 = str;
            s2 = 65535;
            file = file;
            i3 = 0;
        }
    }

    public ZipEntryRO[] getAllEntries() {
        Collection<ZipEntryRO> collectionValues = this.mHashMap.values();
        return (ZipEntryRO[]) collectionValues.toArray(new ZipEntryRO[collectionValues.size()]);
    }

    public AssetFileDescriptor getAssetFileDescriptor(String str) {
        ZipEntryRO zipEntryRO = this.mHashMap.get(str);
        if (zipEntryRO != null) {
            return zipEntryRO.getAssetFileDescriptor();
        }
        return null;
    }

    ZipEntryRO[] getEntriesAt(String str) {
        Vector vector = new Vector();
        Collection<ZipEntryRO> collectionValues = this.mHashMap.values();
        if (str == null) {
            str = "";
        }
        int length = str.length();
        for (ZipEntryRO zipEntryRO : collectionValues) {
            if (zipEntryRO.mFileName.startsWith(str) && -1 == zipEntryRO.mFileName.indexOf(47, length)) {
                vector.add(zipEntryRO);
            }
        }
        return (ZipEntryRO[]) vector.toArray(new ZipEntryRO[vector.size()]);
    }

    public InputStream getInputStream(String str) {
        ZipEntryRO zipEntryRO = this.mHashMap.get(str);
        if (zipEntryRO == null) {
            return null;
        }
        if (zipEntryRO.isUncompressed()) {
            return zipEntryRO.getAssetFileDescriptor().createInputStream();
        }
        ZipFile zipFile = this.mZipFiles.get(zipEntryRO.getZipFile());
        if (zipFile == null) {
            zipFile = new ZipFile(zipEntryRO.getZipFile(), 1);
            this.mZipFiles.put(zipEntryRO.getZipFile(), zipFile);
        }
        ZipEntry entry = zipFile.getEntry(str);
        if (entry != null) {
            return zipFile.getInputStream(entry);
        }
        return null;
    }

    private static int swapEndian(short s) {
        return ((s & 65280) >>> 8) | ((s & 255) << 8);
    }
}
