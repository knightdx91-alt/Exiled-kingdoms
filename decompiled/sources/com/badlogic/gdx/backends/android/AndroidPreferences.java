package com.badlogic.gdx.backends.android;

import android.content.SharedPreferences;
import com.badlogic.gdx.m;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidPreferences implements m {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPrefs;

    public AndroidPreferences(SharedPreferences sharedPreferences) {
        this.sharedPrefs = sharedPreferences;
    }

    private void edit() {
        if (this.editor == null) {
            this.editor = this.sharedPrefs.edit();
        }
    }

    public void clear() {
        edit();
        this.editor.clear();
    }

    public boolean contains(String str) {
        return this.sharedPrefs.contains(str);
    }

    public void flush() {
        SharedPreferences.Editor editor = this.editor;
        if (editor != null) {
            editor.apply();
            this.editor = null;
        }
    }

    public Map<String, ?> get() {
        return this.sharedPrefs.getAll();
    }

    public boolean getBoolean(String str) {
        return this.sharedPrefs.getBoolean(str, false);
    }

    public float getFloat(String str) {
        return this.sharedPrefs.getFloat(str, 0.0f);
    }

    public int getInteger(String str) {
        return this.sharedPrefs.getInt(str, 0);
    }

    public long getLong(String str) {
        return this.sharedPrefs.getLong(str, 0L);
    }

    public String getString(String str) {
        return this.sharedPrefs.getString(str, "");
    }

    public m put(Map<String, ?> map) {
        edit();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Boolean) {
                putBoolean(entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
            }
            if (entry.getValue() instanceof Integer) {
                putInteger(entry.getKey(), ((Integer) entry.getValue()).intValue());
            }
            if (entry.getValue() instanceof Long) {
                putLong(entry.getKey(), ((Long) entry.getValue()).longValue());
            }
            if (entry.getValue() instanceof String) {
                putString(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Float) {
                putFloat(entry.getKey(), ((Float) entry.getValue()).floatValue());
            }
        }
        return this;
    }

    public m putBoolean(String str, boolean z2) {
        edit();
        this.editor.putBoolean(str, z2);
        return this;
    }

    public m putFloat(String str, float f2) {
        edit();
        this.editor.putFloat(str, f2);
        return this;
    }

    public m putInteger(String str, int i2) {
        edit();
        this.editor.putInt(str, i2);
        return this;
    }

    public m putLong(String str, long j2) {
        edit();
        this.editor.putLong(str, j2);
        return this;
    }

    public m putString(String str, String str2) {
        edit();
        this.editor.putString(str, str2);
        return this;
    }

    public void remove(String str) {
        edit();
        this.editor.remove(str);
    }

    public boolean getBoolean(String str, boolean z2) {
        return this.sharedPrefs.getBoolean(str, z2);
    }

    public float getFloat(String str, float f2) {
        return this.sharedPrefs.getFloat(str, f2);
    }

    public int getInteger(String str, int i2) {
        return this.sharedPrefs.getInt(str, i2);
    }

    public long getLong(String str, long j2) {
        return this.sharedPrefs.getLong(str, j2);
    }

    public String getString(String str, String str2) {
        return this.sharedPrefs.getString(str, str2);
    }
}
