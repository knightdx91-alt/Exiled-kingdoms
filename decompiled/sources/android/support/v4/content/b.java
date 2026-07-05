package android.support.v4.content;

import android.content.Context;
import android.support.v4.util.k;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: Loader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class b<D> {
    Context mContext;
    int mId;
    InterfaceC0004b<D> mListener;
    a<D> mOnLoadCanceledListener;
    boolean mStarted = false;
    boolean mAbandoned = false;
    boolean mReset = true;
    boolean mContentChanged = false;
    boolean mProcessingChange = false;

    /* JADX INFO: compiled from: Loader.java */
    public interface a<D> {
        void b();
    }

    /* JADX INFO: renamed from: android.support.v4.content.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Loader.java */
    public interface InterfaceC0004b<D> {
        void a(b<D> bVar, D d2);
    }

    public b(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void abandon() {
        this.mAbandoned = true;
        onAbandon();
    }

    public boolean cancelLoad() {
        return onCancelLoad();
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    public String dataToString(D d2) {
        StringBuilder sb = new StringBuilder(64);
        k.c(d2, sb);
        sb.append("}");
        return sb.toString();
    }

    public void deliverCancellation() {
        a<D> aVar = this.mOnLoadCanceledListener;
        if (aVar != null) {
            aVar.b();
        }
    }

    public void deliverResult(D d2) {
        InterfaceC0004b<D> interfaceC0004b = this.mListener;
        if (interfaceC0004b != null) {
            interfaceC0004b.a(this, d2);
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mListener=");
        printWriter.println(this.mListener);
        if (this.mStarted || this.mContentChanged || this.mProcessingChange) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.mStarted);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.mContentChanged);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.mProcessingChange);
        }
        if (this.mAbandoned || this.mReset) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.mAbandoned);
            printWriter.print(" mReset=");
            printWriter.println(this.mReset);
        }
    }

    public void forceLoad() {
        onForceLoad();
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getId() {
        return this.mId;
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isReset() {
        return this.mReset;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    protected void onAbandon() {
    }

    protected boolean onCancelLoad() {
        return false;
    }

    public void onContentChanged() {
        if (this.mStarted) {
            forceLoad();
        } else {
            this.mContentChanged = true;
        }
    }

    protected void onForceLoad() {
    }

    protected void onReset() {
    }

    protected void onStartLoading() {
    }

    protected void onStopLoading() {
    }

    public void registerListener(int i2, InterfaceC0004b<D> interfaceC0004b) {
        if (this.mListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mListener = interfaceC0004b;
        this.mId = i2;
    }

    public void registerOnLoadCanceledListener(a<D> aVar) {
        if (this.mOnLoadCanceledListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mOnLoadCanceledListener = aVar;
    }

    public void reset() {
        onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if (this.mProcessingChange) {
            onContentChanged();
        }
    }

    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        onStartLoading();
    }

    public void stopLoading() {
        this.mStarted = false;
        onStopLoading();
    }

    public boolean takeContentChanged() {
        boolean z2 = this.mContentChanged;
        this.mContentChanged = false;
        this.mProcessingChange |= z2;
        return z2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        k.c(this, sb);
        sb.append(" id=");
        return a.a.p(sb, this.mId, "}");
    }

    public void unregisterListener(InterfaceC0004b<D> interfaceC0004b) {
        InterfaceC0004b<D> interfaceC0004b2 = this.mListener;
        if (interfaceC0004b2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (interfaceC0004b2 != interfaceC0004b) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mListener = null;
    }

    public void unregisterOnLoadCanceledListener(a<D> aVar) {
        a<D> aVar2 = this.mOnLoadCanceledListener;
        if (aVar2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (aVar2 != aVar) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mOnLoadCanceledListener = null;
    }
}
