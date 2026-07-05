package com.badlogic.gdx.backends.android;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.d;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.n0;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidFileHandle extends com.badlogic.gdx.files.a {
    private final AssetManager assets;

    AndroidFileHandle(AssetManager assetManager, String str, d.a aVar) {
        super(str.replace('\\', '/'), aVar);
        this.assets = assetManager;
    }

    @Override // com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a child(String str) {
        String strReplace = str.replace('\\', '/');
        return this.file.getPath().length() == 0 ? new AndroidFileHandle(this.assets, new File(strReplace), this.type) : new AndroidFileHandle(this.assets, new File(this.file, strReplace), this.type);
    }

    @Override // com.badlogic.gdx.files.a
    public boolean exists() {
        if (this.type != d.a.f1645b) {
            return super.exists();
        }
        String path = this.file.getPath();
        try {
            this.assets.open(path).close();
            return true;
        } catch (Exception unused) {
            try {
                return this.assets.list(path).length > 0;
            } catch (Exception unused2) {
                return false;
            }
        }
    }

    @Override // com.badlogic.gdx.files.a
    public File file() {
        return this.type == d.a.f1648e ? new File(Gdx.files.getLocalStoragePath(), this.file.getPath()) : super.file();
    }

    public AssetFileDescriptor getAssetFileDescriptor() {
        AssetManager assetManager = this.assets;
        if (assetManager != null) {
            return assetManager.openFd(path());
        }
        return null;
    }

    @Override // com.badlogic.gdx.files.a
    public boolean isDirectory() {
        if (this.type != d.a.f1645b) {
            return super.isDirectory();
        }
        try {
            return this.assets.list(this.file.getPath()).length > 0;
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // com.badlogic.gdx.files.a
    public long lastModified() {
        return super.lastModified();
    }

    @Override // com.badlogic.gdx.files.a
    public long length() {
        if (this.type == d.a.f1645b) {
            AssetFileDescriptor assetFileDescriptorOpenFd = null;
            try {
                assetFileDescriptorOpenFd = this.assets.openFd(this.file.getPath());
                long length = assetFileDescriptorOpenFd.getLength();
                try {
                    assetFileDescriptorOpenFd.close();
                } catch (IOException unused) {
                }
                return length;
            } catch (IOException unused2) {
                if (assetFileDescriptorOpenFd != null) {
                    try {
                        assetFileDescriptorOpenFd.close();
                    } catch (IOException unused3) {
                    }
                }
            } catch (Throwable th) {
                if (assetFileDescriptorOpenFd != null) {
                    try {
                        assetFileDescriptorOpenFd.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        }
        return super.length();
    }

    @Override // com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a[] list() {
        if (this.type != d.a.f1645b) {
            return super.list();
        }
        try {
            String[] list = this.assets.list(this.file.getPath());
            int length = list.length;
            com.badlogic.gdx.files.a[] aVarArr = new com.badlogic.gdx.files.a[length];
            for (int i2 = 0; i2 < length; i2++) {
                aVarArr[i2] = new AndroidFileHandle(this.assets, new File(this.file, list[i2]), this.type);
            }
            return aVarArr;
        } catch (Exception e2) {
            throw new m("Error listing children: " + this.file + " (" + this.type + ")", (Throwable) e2);
        }
    }

    @Override // com.badlogic.gdx.files.a
    public ByteBuffer map(FileChannel.MapMode mapMode) throws Throwable {
        long startOffset;
        long declaredLength;
        FileInputStream fileInputStream;
        if (this.type != d.a.f1645b) {
            return super.map(mapMode);
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                AssetFileDescriptor assetFileDescriptor = getAssetFileDescriptor();
                startOffset = assetFileDescriptor.getStartOffset();
                declaredLength = assetFileDescriptor.getDeclaredLength();
                fileInputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            MappedByteBuffer map = fileInputStream.getChannel().map(mapMode, startOffset, declaredLength);
            map.order(ByteOrder.nativeOrder());
            n0.a(fileInputStream);
            return map;
        } catch (Exception e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            throw new m("Error memory mapping file: " + this + " (" + this.type + ")", (Throwable) e);
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            n0.a(fileInputStream2);
            throw th;
        }
    }

    @Override // com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a parent() {
        File parentFile = this.file.getParentFile();
        if (parentFile == null) {
            parentFile = this.type == d.a.f1647d ? new File("/") : new File("");
        }
        return new AndroidFileHandle(this.assets, parentFile, this.type);
    }

    @Override // com.badlogic.gdx.files.a
    public InputStream read() {
        if (this.type != d.a.f1645b) {
            return super.read();
        }
        try {
            return this.assets.open(this.file.getPath());
        } catch (IOException e2) {
            throw new m("Error reading file: " + this.file + " (" + this.type + ")", (Throwable) e2);
        }
    }

    @Override // com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a sibling(String str) {
        String strReplace = str.replace('\\', '/');
        if (this.file.getPath().length() != 0) {
            return Gdx.files.getFileHandle(new File(this.file.getParent(), strReplace).getPath(), this.type);
        }
        throw new m("Cannot get the sibling of the root.");
    }

    AndroidFileHandle(AssetManager assetManager, File file, d.a aVar) {
        super(file, aVar);
        this.assets = assetManager;
    }

    @Override // com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a[] list(FileFilter fileFilter) {
        if (this.type == d.a.f1645b) {
            try {
                String[] list = this.assets.list(this.file.getPath());
                com.badlogic.gdx.files.a[] aVarArr = new com.badlogic.gdx.files.a[list.length];
                int i2 = 0;
                for (String str : list) {
                    AndroidFileHandle androidFileHandle = new AndroidFileHandle(this.assets, new File(this.file, str), this.type);
                    if (fileFilter.accept(androidFileHandle.file())) {
                        aVarArr[i2] = androidFileHandle;
                        i2++;
                    }
                }
                if (i2 >= list.length) {
                    return aVarArr;
                }
                com.badlogic.gdx.files.a[] aVarArr2 = new com.badlogic.gdx.files.a[i2];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
                return aVarArr2;
            } catch (Exception e2) {
                throw new m("Error listing children: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
        }
        return super.list(fileFilter);
    }

    @Override // com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a[] list(FilenameFilter filenameFilter) {
        if (this.type == d.a.f1645b) {
            try {
                String[] list = this.assets.list(this.file.getPath());
                com.badlogic.gdx.files.a[] aVarArr = new com.badlogic.gdx.files.a[list.length];
                int i2 = 0;
                for (String str : list) {
                    if (filenameFilter.accept(this.file, str)) {
                        aVarArr[i2] = new AndroidFileHandle(this.assets, new File(this.file, str), this.type);
                        i2++;
                    }
                }
                if (i2 >= list.length) {
                    return aVarArr;
                }
                com.badlogic.gdx.files.a[] aVarArr2 = new com.badlogic.gdx.files.a[i2];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
                return aVarArr2;
            } catch (Exception e2) {
                throw new m("Error listing children: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
        }
        return super.list(filenameFilter);
    }

    @Override // com.badlogic.gdx.files.a
    public com.badlogic.gdx.files.a[] list(String str) {
        if (this.type == d.a.f1645b) {
            try {
                String[] list = this.assets.list(this.file.getPath());
                com.badlogic.gdx.files.a[] aVarArr = new com.badlogic.gdx.files.a[list.length];
                int i2 = 0;
                for (String str2 : list) {
                    if (str2.endsWith(str)) {
                        aVarArr[i2] = new AndroidFileHandle(this.assets, new File(this.file, str2), this.type);
                        i2++;
                    }
                }
                if (i2 >= list.length) {
                    return aVarArr;
                }
                com.badlogic.gdx.files.a[] aVarArr2 = new com.badlogic.gdx.files.a[i2];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
                return aVarArr2;
            } catch (Exception e2) {
                throw new m("Error listing children: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
        }
        return super.list(str);
    }
}
