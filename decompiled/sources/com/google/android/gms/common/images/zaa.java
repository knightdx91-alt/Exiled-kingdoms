package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zak;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zaa {
    final zab zamu;
    protected int zamw;
    private int zamv = 0;
    private boolean zamx = false;
    private boolean zamy = true;
    private boolean zamz = false;
    private boolean zana = true;

    public zaa(Uri uri, int i2) {
        this.zamw = 0;
        this.zamu = new zab(uri);
        this.zamw = i2;
    }

    final void zaa(Context context, Bitmap bitmap, boolean z2) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), z2, false, true);
    }

    protected abstract void zaa(Drawable drawable, boolean z2, boolean z3, boolean z4);

    final void zaa(Context context, zak zakVar) {
        if (this.zana) {
            zaa(null, false, true, false);
        }
    }

    final void zaa(Context context, zak zakVar, boolean z2) {
        int i2 = this.zamw;
        zaa(i2 != 0 ? context.getResources().getDrawable(i2) : null, z2, false, false);
    }

    protected final boolean zaa(boolean z2, boolean z3) {
        return (!this.zamy || z3 || z2) ? false : true;
    }
}
