package com.badlogic.gdx.backends.android;

import android.util.Log;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidApplicationLogger implements com.badlogic.gdx.b {
    @Override // com.badlogic.gdx.b
    public void debug(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // com.badlogic.gdx.b
    public void error(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.badlogic.gdx.b
    public void log(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.badlogic.gdx.b
    public void debug(String str, String str2, Throwable th) {
        Log.d(str, str2, th);
    }

    @Override // com.badlogic.gdx.b
    public void error(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // com.badlogic.gdx.b
    public void log(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }
}
