package com.badlogic.gdx.backends.android;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.ZipResourceFile;
import com.badlogic.gdx.d;
import com.badlogic.gdx.utils.m;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidZipFileHandle extends AndroidFileHandle {
    private ZipResourceFile expansionFile;
    private long fdLength;
    private boolean hasAssetFd;
    private String path;

    public AndroidZipFileHandle(String str) {
        super((AssetManager) null, str, d.a.f1645b);
        initialize();
    }

    private String getPath() {
        return this.path;
    }

    private void initialize() {
        this.path = this.file.getPath().replace('\\', '/');
        ZipResourceFile expansionFile = ((AndroidFiles) Gdx.files).getExpansionFile();
        this.expansionFile = expansionFile;
        AssetFileDescriptor assetFileDescriptor = expansionFile.getAssetFileDescriptor(getPath());
        if (assetFileDescriptor != null) {
            this.hasAssetFd = true;
            this.fdLength = assetFileDescriptor.getLength();
            try {
                assetFileDescriptor.close();
            } catch (IOException unused) {
            }
        } else {
            this.hasAssetFd = false;
        }
        if (isDirectory()) {
            this.path = a.a.m(this.path, "/", new StringBuilder());
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a child(String str) {
        return this.file.getPath().length() == 0 ? new AndroidZipFileHandle(new File(str), this.type) : new AndroidZipFileHandle(new File(this.file, str), this.type);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public boolean exists() {
        return this.hasAssetFd || this.expansionFile.getEntriesAt(getPath()).length != 0;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle
    public AssetFileDescriptor getAssetFileDescriptor() {
        return this.expansionFile.getAssetFileDescriptor(getPath());
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public boolean isDirectory() {
        return !this.hasAssetFd;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public long length() {
        if (this.hasAssetFd) {
            return this.fdLength;
        }
        return 0L;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a[] list() {
        ZipResourceFile.ZipEntryRO[] entriesAt = this.expansionFile.getEntriesAt(getPath());
        com.badlogic.gdx.files.a[] aVarArr = new com.badlogic.gdx.files.a[entriesAt.length - 1];
        int length = entriesAt.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (entriesAt[i3].mFileName.length() != getPath().length()) {
                aVarArr[i2] = new AndroidZipFileHandle(entriesAt[i3].mFileName);
                i2++;
            }
        }
        return aVarArr;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a parent() {
        File parentFile = this.file.getParentFile();
        if (parentFile == null) {
            parentFile = new File("");
        }
        return new AndroidZipFileHandle(parentFile.getPath());
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public InputStream read() {
        try {
            return this.expansionFile.getInputStream(getPath());
        } catch (IOException e2) {
            throw new m("Error reading file: " + this.file + " (ZipResourceFile)", (Throwable) e2);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a sibling(String str) {
        if (this.file.getPath().length() != 0) {
            return Gdx.files.getFileHandle(new File(this.file.getParent(), str).getPath(), this.type);
        }
        throw new m("Cannot get the sibling of the root.");
    }

    public AndroidZipFileHandle(File file, d.a aVar) {
        super((AssetManager) null, file, aVar);
        initialize();
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a[] list(FileFilter fileFilter) {
        ZipResourceFile.ZipEntryRO[] entriesAt = this.expansionFile.getEntriesAt(getPath());
        int length = entriesAt.length - 1;
        com.badlogic.gdx.files.a[] aVarArr = new com.badlogic.gdx.files.a[length];
        int length2 = entriesAt.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            if (entriesAt[i3].mFileName.length() != getPath().length()) {
                AndroidZipFileHandle androidZipFileHandle = new AndroidZipFileHandle(entriesAt[i3].mFileName);
                if (fileFilter.accept(androidZipFileHandle.file())) {
                    aVarArr[i2] = androidZipFileHandle;
                    i2++;
                }
            }
        }
        if (i2 >= length) {
            return aVarArr;
        }
        com.badlogic.gdx.files.a[] aVarArr2 = new com.badlogic.gdx.files.a[i2];
        System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
        return aVarArr2;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a[] list(FilenameFilter filenameFilter) {
        ZipResourceFile.ZipEntryRO[] entriesAt = this.expansionFile.getEntriesAt(getPath());
        int length = entriesAt.length - 1;
        com.badlogic.gdx.files.a[] aVarArr = new com.badlogic.gdx.files.a[length];
        int length2 = entriesAt.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            if (entriesAt[i3].mFileName.length() != getPath().length()) {
                String str = entriesAt[i3].mFileName;
                if (filenameFilter.accept(this.file, str)) {
                    aVarArr[i2] = new AndroidZipFileHandle(str);
                    i2++;
                }
            }
        }
        if (i2 >= length) {
            return aVarArr;
        }
        com.badlogic.gdx.files.a[] aVarArr2 = new com.badlogic.gdx.files.a[i2];
        System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
        return aVarArr2;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidFileHandle, com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a[] list(String str) {
        ZipResourceFile.ZipEntryRO[] entriesAt = this.expansionFile.getEntriesAt(getPath());
        int length = entriesAt.length - 1;
        com.badlogic.gdx.files.a[] aVarArr = new com.badlogic.gdx.files.a[length];
        int length2 = entriesAt.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            if (entriesAt[i3].mFileName.length() != getPath().length()) {
                String str2 = entriesAt[i3].mFileName;
                if (str2.endsWith(str)) {
                    aVarArr[i2] = new AndroidZipFileHandle(str2);
                    i2++;
                }
            }
        }
        if (i2 >= length) {
            return aVarArr;
        }
        com.badlogic.gdx.files.a[] aVarArr2 = new com.badlogic.gdx.files.a[i2];
        System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
        return aVarArr2;
    }
}
