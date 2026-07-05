package com.badlogic.gdx.backends.android;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.d;
import com.badlogic.gdx.utils.m;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidFiles implements d {
    protected final AssetManager assets;
    private ZipResourceFile expansionFile = null;
    protected final String externalFilesPath;
    protected final String localpath;

    public AndroidFiles(AssetManager assetManager, ContextWrapper contextWrapper) {
        this.assets = assetManager;
        String absolutePath = contextWrapper.getFilesDir().getAbsolutePath();
        this.localpath = absolutePath.endsWith("/") ? absolutePath : absolutePath.concat("/");
        File externalFilesDir = contextWrapper.getExternalFilesDir(null);
        if (externalFilesDir == null) {
            this.externalFilesPath = null;
        } else {
            String absolutePath2 = externalFilesDir.getAbsolutePath();
            this.externalFilesPath = absolutePath2.endsWith("/") ? absolutePath2 : absolutePath2.concat("/");
        }
    }

    private com.badlogic.gdx.files.a getZipFileHandleIfExists(com.badlogic.gdx.files.a aVar, String str) {
        try {
            this.assets.open(str).close();
            return aVar;
        } catch (Exception unused) {
            AndroidZipFileHandle androidZipFileHandle = new AndroidZipFileHandle(str);
            return (androidZipFileHandle.isDirectory() && !androidZipFileHandle.exists()) ? aVar : androidZipFileHandle;
        }
    }

    public com.badlogic.gdx.files.a absolute(String str) {
        return new AndroidFileHandle((AssetManager) null, str, d.a.f1647d);
    }

    @Override // com.badlogic.gdx.d
    public com.badlogic.gdx.files.a classpath(String str) {
        return new AndroidFileHandle((AssetManager) null, str, d.a.f1644a);
    }

    @Override // com.badlogic.gdx.d
    public com.badlogic.gdx.files.a external(String str) {
        return new AndroidFileHandle((AssetManager) null, str, d.a.f1646c);
    }

    public ZipResourceFile getExpansionFile() {
        return this.expansionFile;
    }

    @Override // com.badlogic.gdx.d
    public String getExternalStoragePath() {
        return this.externalFilesPath;
    }

    @Override // com.badlogic.gdx.d
    public com.badlogic.gdx.files.a getFileHandle(String str, d.a aVar) {
        d.a aVar2 = d.a.f1645b;
        AndroidFileHandle androidFileHandle = new AndroidFileHandle(aVar == aVar2 ? this.assets : null, str, aVar);
        return (this.expansionFile == null || aVar != aVar2) ? androidFileHandle : getZipFileHandleIfExists(androidFileHandle, str);
    }

    @Override // com.badlogic.gdx.d
    public String getLocalStoragePath() {
        return this.localpath;
    }

    @Override // com.badlogic.gdx.d
    public com.badlogic.gdx.files.a internal(String str) {
        AndroidFileHandle androidFileHandle = new AndroidFileHandle(this.assets, str, d.a.f1645b);
        return this.expansionFile != null ? getZipFileHandleIfExists(androidFileHandle, str) : androidFileHandle;
    }

    public boolean isExternalStorageAvailable() {
        return this.externalFilesPath != null;
    }

    public boolean isLocalStorageAvailable() {
        return true;
    }

    @Override // com.badlogic.gdx.d
    public com.badlogic.gdx.files.a local(String str) {
        return new AndroidFileHandle((AssetManager) null, str, d.a.f1648e);
    }

    public boolean setAPKExpansion(int i2, int i3) {
        Context baseContext;
        try {
            Object obj = Gdx.app;
            if (obj instanceof Activity) {
                baseContext = ((Activity) obj).getBaseContext();
            } else {
                if (!(obj instanceof Fragment)) {
                    throw new m("APK expansion not supported for application type");
                }
                baseContext = ((Fragment) obj).getActivity().getBaseContext();
            }
            ZipResourceFile aPKExpansionZipFile = APKExpansionSupport.getAPKExpansionZipFile(baseContext, i2, i3);
            this.expansionFile = aPKExpansionZipFile;
            return aPKExpansionZipFile != null;
        } catch (IOException unused) {
            throw new m("APK expansion main version " + i2 + " or patch version " + i3 + " couldn't be opened!");
        }
    }
}
