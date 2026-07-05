package android.support.v4.app;

import android.os.Bundle;

/* JADX INFO: compiled from: LoaderManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class y {

    /* JADX INFO: compiled from: LoaderManager.java */
    public interface a<D> {
        android.support.v4.content.b<D> onCreateLoader(int i2, Bundle bundle);

        void onLoadFinished(android.support.v4.content.b<D> bVar, D d2);

        void onLoaderReset(android.support.v4.content.b<D> bVar);
    }

    public abstract android.support.v4.content.b a(a aVar);
}
