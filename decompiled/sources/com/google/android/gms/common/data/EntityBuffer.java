package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zamd;
    private ArrayList<Integer> zame;

    @KeepForSdk
    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zamd = false;
    }

    private final void zacb() {
        synchronized (this) {
            try {
                if (!this.zamd) {
                    int count = this.mDataHolder.getCount();
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    this.zame = arrayList;
                    if (count > 0) {
                        arrayList.add(0);
                        String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                        String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                        for (int i2 = 1; i2 < count; i2++) {
                            int windowIndex = this.mDataHolder.getWindowIndex(i2);
                            String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i2, windowIndex);
                            if (string2 == null) {
                                StringBuilder sb = new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78);
                                sb.append("Missing value for markerColumn: ");
                                sb.append(primaryDataMarkerColumn);
                                sb.append(", at row: ");
                                sb.append(i2);
                                sb.append(", for window: ");
                                sb.append(windowIndex);
                                throw new NullPointerException(sb.toString());
                            }
                            if (!string2.equals(string)) {
                                this.zame.add(Integer.valueOf(i2));
                                string = string2;
                            }
                        }
                    }
                    this.zamd = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final int zah(int i2) {
        if (i2 >= 0 && i2 < this.zame.size()) {
            return this.zame.get(i2).intValue();
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("Position ");
        sb.append(i2);
        sb.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final T get(int i2) {
        int iIntValue;
        int iIntValue2;
        zacb();
        int iZah = zah(i2);
        int i3 = 0;
        if (i2 >= 0 && i2 != this.zame.size()) {
            if (i2 == this.zame.size() - 1) {
                iIntValue = this.mDataHolder.getCount();
                iIntValue2 = this.zame.get(i2).intValue();
            } else {
                iIntValue = this.zame.get(i2 + 1).intValue();
                iIntValue2 = this.zame.get(i2).intValue();
            }
            int i4 = iIntValue - iIntValue2;
            if (i4 == 1) {
                int iZah2 = zah(i2);
                int windowIndex = this.mDataHolder.getWindowIndex(iZah2);
                String childDataMarkerColumn = getChildDataMarkerColumn();
                if (childDataMarkerColumn == null || this.mDataHolder.getString(childDataMarkerColumn, iZah2, windowIndex) != null) {
                    i3 = i4;
                }
            }
        }
        return getEntry(iZah, i3);
    }

    @KeepForSdk
    protected String getChildDataMarkerColumn() {
        return null;
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @KeepForSdk
    public int getCount() {
        zacb();
        return this.zame.size();
    }

    @KeepForSdk
    protected abstract T getEntry(int i2, int i3);

    @KeepForSdk
    protected abstract String getPrimaryDataMarkerColumn();
}
