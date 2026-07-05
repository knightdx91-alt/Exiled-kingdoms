package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: FragmentTransitionImpl.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class w implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f334b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ ArrayList f335c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ android.support.v4.util.b f336d;

    public /* synthetic */ w(ArrayList arrayList, android.support.v4.util.b bVar, int i2) {
        this.f334b = i2;
        this.f335c = arrayList;
        this.f336d = bVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        String str;
        switch (this.f334b) {
            case 0:
                ArrayList arrayList = this.f335c;
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    View view = (View) arrayList.get(i2);
                    String transitionName = view.getTransitionName();
                    if (transitionName != null) {
                        Iterator it = this.f336d.entrySet().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                Map.Entry entry = (Map.Entry) it.next();
                                if (transitionName.equals(entry.getValue())) {
                                    str = (String) entry.getKey();
                                }
                            } else {
                                str = null;
                            }
                        }
                        view.setTransitionName(str);
                    }
                }
                break;
            default:
                ArrayList arrayList2 = this.f335c;
                int size2 = arrayList2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    View view2 = (View) arrayList2.get(i3);
                    view2.setTransitionName((String) this.f336d.get(view2.getTransitionName()));
                }
                break;
        }
    }
}
