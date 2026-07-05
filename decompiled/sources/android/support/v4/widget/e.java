package android.support.v4.widget;

import android.widget.ListView;

/* JADX INFO: compiled from: ListViewAutoScrollHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e extends a {
    private final ListView s;

    public e(ListView listView) {
        super(listView);
        this.s = listView;
    }

    @Override // android.support.v4.widget.a
    public final boolean a(int i2) {
        ListView listView = this.s;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i3 = firstVisiblePosition + childCount;
        if (i2 > 0) {
            if (i3 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i2 >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // android.support.v4.widget.a
    public final void e(int i2) {
        this.s.scrollListBy(i2);
    }
}
