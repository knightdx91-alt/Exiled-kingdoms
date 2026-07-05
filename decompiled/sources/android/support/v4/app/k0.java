package android.support.v4.app;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TaskStackBuilder.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class k0 implements Iterable<Intent> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<Intent> f272a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AppCompatActivity f273b;

    /* JADX INFO: compiled from: TaskStackBuilder.java */
    public interface a {
        Intent a();
    }

    private k0(AppCompatActivity appCompatActivity) {
        this.f273b = appCompatActivity;
    }

    public static k0 b(AppCompatActivity appCompatActivity) {
        return new k0(appCompatActivity);
    }

    public final void a(AppCompatActivity appCompatActivity) {
        Intent intentA = appCompatActivity.a();
        if (intentA == null) {
            intentA = a0.a(appCompatActivity);
        }
        if (intentA != null) {
            ComponentName component = intentA.getComponent();
            AppCompatActivity appCompatActivity2 = this.f273b;
            if (component == null) {
                component = intentA.resolveActivity(appCompatActivity2.getPackageManager());
            }
            ArrayList<Intent> arrayList = this.f272a;
            int size = arrayList.size();
            try {
                for (Intent intentB = a0.b(appCompatActivity2, component); intentB != null; intentB = a0.b(appCompatActivity2, intentB.getComponent())) {
                    arrayList.add(size, intentB);
                }
                arrayList.add(intentA);
            } catch (PackageManager.NameNotFoundException e2) {
                Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                throw new IllegalArgumentException(e2);
            }
        }
    }

    public final void c() {
        ArrayList<Intent> arrayList = this.f272a;
        if (arrayList.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        this.f273b.startActivities(intentArr, null);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public final Iterator<Intent> iterator() {
        return this.f272a.iterator();
    }
}
