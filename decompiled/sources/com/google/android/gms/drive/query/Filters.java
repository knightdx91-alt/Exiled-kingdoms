package com.google.android.gms.drive.query;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.query.internal.zzn;
import com.google.android.gms.drive.query.internal.zzp;
import com.google.android.gms.drive.query.internal.zzr;
import com.google.android.gms.drive.query.internal.zzv;
import com.google.android.gms.drive.query.internal.zzx;
import com.google.android.gms.drive.query.internal.zzz;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Filters {
    public static Filter and(Filter filter, Filter... filterArr) {
        Preconditions.checkNotNull(filter, "Filter may not be null.");
        Preconditions.checkNotNull(filterArr, "Additional filters may not be null.");
        return new zzr(zzx.zzmf, filter, filterArr);
    }

    public static Filter contains(SearchableMetadataField<String> searchableMetadataField, String str) {
        Preconditions.checkNotNull(searchableMetadataField, "Field may not be null.");
        Preconditions.checkNotNull(str, "Value may not be null.");
        return new com.google.android.gms.drive.query.internal.zzb(zzx.zzmi, searchableMetadataField, str);
    }

    public static Filter eq(CustomPropertyKey customPropertyKey, String str) {
        Preconditions.checkNotNull(customPropertyKey, "Custom property key may not be null.");
        Preconditions.checkNotNull(str, "Custom property value may not be null.");
        return new zzn(SearchableField.zzlf, new AppVisibleCustomProperties.zza().zza(customPropertyKey, str).zzat());
    }

    public static <T extends Comparable<T>> Filter greaterThan(SearchableOrderedMetadataField<T> searchableOrderedMetadataField, T t2) {
        Preconditions.checkNotNull(searchableOrderedMetadataField, "Field may not be null.");
        Preconditions.checkNotNull(t2, "Value may not be null.");
        return new com.google.android.gms.drive.query.internal.zzb(zzx.zzmd, searchableOrderedMetadataField, t2);
    }

    public static <T extends Comparable<T>> Filter greaterThanEquals(SearchableOrderedMetadataField<T> searchableOrderedMetadataField, T t2) {
        Preconditions.checkNotNull(searchableOrderedMetadataField, "Field may not be null.");
        Preconditions.checkNotNull(t2, "Value may not be null.");
        return new com.google.android.gms.drive.query.internal.zzb(zzx.zzme, searchableOrderedMetadataField, t2);
    }

    public static <T> Filter in(SearchableCollectionMetadataField<T> searchableCollectionMetadataField, T t2) {
        Preconditions.checkNotNull(searchableCollectionMetadataField, "Field may not be null.");
        Preconditions.checkNotNull(t2, "Value may not be null.");
        return new zzp(searchableCollectionMetadataField, t2);
    }

    public static <T extends Comparable<T>> Filter lessThan(SearchableOrderedMetadataField<T> searchableOrderedMetadataField, T t2) {
        Preconditions.checkNotNull(searchableOrderedMetadataField, "Field may not be null.");
        Preconditions.checkNotNull(t2, "Value may not be null.");
        return new com.google.android.gms.drive.query.internal.zzb(zzx.zzmb, searchableOrderedMetadataField, t2);
    }

    public static <T extends Comparable<T>> Filter lessThanEquals(SearchableOrderedMetadataField<T> searchableOrderedMetadataField, T t2) {
        Preconditions.checkNotNull(searchableOrderedMetadataField, "Field may not be null.");
        Preconditions.checkNotNull(t2, "Value may not be null.");
        return new com.google.android.gms.drive.query.internal.zzb(zzx.zzmc, searchableOrderedMetadataField, t2);
    }

    public static Filter not(Filter filter) {
        Preconditions.checkNotNull(filter, "Filter may not be null");
        return new zzv(filter);
    }

    public static Filter openedByMe() {
        return new com.google.android.gms.drive.query.internal.zzd(SearchableField.LAST_VIEWED_BY_ME);
    }

    public static Filter or(Filter filter, Filter... filterArr) {
        Preconditions.checkNotNull(filter, "Filter may not be null.");
        Preconditions.checkNotNull(filterArr, "Additional filters may not be null.");
        return new zzr(zzx.zzmg, filter, filterArr);
    }

    public static Filter ownedByMe() {
        return new zzz();
    }

    public static Filter sharedWithMe() {
        return new com.google.android.gms.drive.query.internal.zzd(SearchableField.zzle);
    }

    public static Filter and(Iterable<Filter> iterable) {
        Preconditions.checkNotNull(iterable, "Filters may not be null");
        return new zzr(zzx.zzmf, iterable);
    }

    public static <T> Filter eq(SearchableMetadataField<T> searchableMetadataField, T t2) {
        Preconditions.checkNotNull(searchableMetadataField, "Field may not be null.");
        Preconditions.checkNotNull(t2, "Value may not be null.");
        return new com.google.android.gms.drive.query.internal.zzb(zzx.zzma, searchableMetadataField, t2);
    }

    public static Filter or(Iterable<Filter> iterable) {
        Preconditions.checkNotNull(iterable, "Filters may not be null");
        return new zzr(zzx.zzmg, iterable);
    }
}
