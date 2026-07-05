package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class APKExpansionSupport {
    private static final String EXP_PATH = "/Android/obb/";

    static String[] getAPKExpansionFiles(Context context, int i2, int i3) {
        String packageName = context.getPackageName();
        Vector vector = new Vector();
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(Environment.getExternalStorageDirectory().toString() + EXP_PATH + packageName);
            if (file.exists()) {
                if (i2 > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(file);
                    sb.append(File.separator);
                    sb.append("main.");
                    sb.append(i2);
                    sb.append(".");
                    String strM = a.a.m(packageName, ".obb", sb);
                    if (new File(strM).isFile()) {
                        vector.add(strM);
                    }
                }
                if (i3 > 0) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(file);
                    sb2.append(File.separator);
                    sb2.append("patch.");
                    sb2.append(i3);
                    sb2.append(".");
                    String strM2 = a.a.m(packageName, ".obb", sb2);
                    if (new File(strM2).isFile()) {
                        vector.add(strM2);
                    }
                }
            }
        }
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        return strArr;
    }

    public static ZipResourceFile getAPKExpansionZipFile(Context context, int i2, int i3) {
        return getResourceZipFile(getAPKExpansionFiles(context, i2, i3));
    }

    public static ZipResourceFile getResourceZipFile(String[] strArr) throws IOException {
        ZipResourceFile zipResourceFile = null;
        for (String str : strArr) {
            if (zipResourceFile == null) {
                zipResourceFile = new ZipResourceFile(str);
            } else {
                zipResourceFile.addPatchFile(str);
            }
        }
        return zipResourceFile;
    }
}
