package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Collections;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzhp {
    public static final MetadataField<DriveId> zziv = zzij.zzkt;
    public static final MetadataField<String> zziw = new com.google.android.gms.drive.metadata.internal.zzt("alternateLink", GmsVersion.VERSION_JARLSBERG);
    public static final zzhs zzix = new zzhs(GmsVersion.VERSION_LONGHORN);
    public static final MetadataField<String> zziy = new com.google.android.gms.drive.metadata.internal.zzt("description", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<String> zziz = new com.google.android.gms.drive.metadata.internal.zzt("embedLink", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<String> zzja = new com.google.android.gms.drive.metadata.internal.zzt("fileExtension", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<Long> zzjb = new com.google.android.gms.drive.metadata.internal.zzi("fileSize", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<String> zzjc = new com.google.android.gms.drive.metadata.internal.zzt("folderColorRgb", GmsVersion.VERSION_QUESO);
    public static final MetadataField<Boolean> zzjd = new com.google.android.gms.drive.metadata.internal.zzb("hasThumbnail", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<String> zzje = new com.google.android.gms.drive.metadata.internal.zzt("indexableText", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<Boolean> zzjf = new com.google.android.gms.drive.metadata.internal.zzb("isAppData", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<Boolean> zzjg = new com.google.android.gms.drive.metadata.internal.zzb("isCopyable", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<Boolean> zzjh = new com.google.android.gms.drive.metadata.internal.zzb("isEditable", GmsVersion.VERSION_HALLOUMI);
    public static final MetadataField<Boolean> zzji = new zzhq("isExplicitlyTrashed", Collections.singleton("trashed"), Collections.emptySet(), GmsVersion.VERSION_ORLA);
    public static final MetadataField<Boolean> zzjj = new com.google.android.gms.drive.metadata.internal.zzb("isLocalContentUpToDate", GmsVersion.VERSION_REBLOCHON);
    public static final zzht zzjk = new zzht("isPinned", GmsVersion.VERSION_HALLOUMI);
    public static final MetadataField<Boolean> zzjl = new com.google.android.gms.drive.metadata.internal.zzb("isOpenable", GmsVersion.VERSION_PARMESAN);
    public static final MetadataField<Boolean> zzjm = new com.google.android.gms.drive.metadata.internal.zzb("isRestricted", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<Boolean> zzjn = new com.google.android.gms.drive.metadata.internal.zzb("isShared", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<Boolean> zzjo = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosFolder", GmsVersion.VERSION_ORLA);
    public static final MetadataField<Boolean> zzjp = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosRootFolder", GmsVersion.VERSION_ORLA);
    public static final MetadataField<Boolean> zzjq = new com.google.android.gms.drive.metadata.internal.zzb("isTrashable", GmsVersion.VERSION_KENAFA);
    public static final MetadataField<Boolean> zzjr = new com.google.android.gms.drive.metadata.internal.zzb("isViewed", GmsVersion.VERSION_JARLSBERG);
    public static final zzhu zzjs = new zzhu(GmsVersion.VERSION_HALLOUMI);
    public static final MetadataField<String> zzjt = new com.google.android.gms.drive.metadata.internal.zzt("originalFilename", GmsVersion.VERSION_JARLSBERG);
    public static final com.google.android.gms.drive.metadata.zzb<String> zzju = new com.google.android.gms.drive.metadata.internal.zzs("ownerNames", GmsVersion.VERSION_JARLSBERG);
    public static final com.google.android.gms.drive.metadata.internal.zzu zzjv = new com.google.android.gms.drive.metadata.internal.zzu("lastModifyingUser", GmsVersion.VERSION_MANCHEGO);
    public static final com.google.android.gms.drive.metadata.internal.zzu zzjw = new com.google.android.gms.drive.metadata.internal.zzu("sharingUser", GmsVersion.VERSION_MANCHEGO);
    public static final com.google.android.gms.drive.metadata.internal.zzo zzjx = new com.google.android.gms.drive.metadata.internal.zzo(GmsVersion.VERSION_HALLOUMI);
    public static final zzhv zzjy = new zzhv("quotaBytesUsed", GmsVersion.VERSION_JARLSBERG);
    public static final zzhx zzjz = new zzhx("starred", GmsVersion.VERSION_HALLOUMI);
    public static final MetadataField<BitmapTeleporter> zzka = new zzhr("thumbnail", Collections.emptySet(), Collections.emptySet(), GmsVersion.VERSION_KENAFA);
    public static final zzhy zzkb = new zzhy("title", GmsVersion.VERSION_HALLOUMI);
    public static final zzhz zzkc = new zzhz("trashed", GmsVersion.VERSION_HALLOUMI);
    public static final MetadataField<String> zzkd = new com.google.android.gms.drive.metadata.internal.zzt("webContentLink", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<String> zzke = new com.google.android.gms.drive.metadata.internal.zzt("webViewLink", GmsVersion.VERSION_JARLSBERG);
    public static final MetadataField<String> zzkf = new com.google.android.gms.drive.metadata.internal.zzt("uniqueIdentifier", GmsVersion.VERSION_LONGHORN);
    public static final com.google.android.gms.drive.metadata.internal.zzb zzkg = new com.google.android.gms.drive.metadata.internal.zzb("writersCanShare", GmsVersion.VERSION_MANCHEGO);
    public static final MetadataField<String> zzkh = new com.google.android.gms.drive.metadata.internal.zzt("role", GmsVersion.VERSION_MANCHEGO);
    public static final MetadataField<String> zzki = new com.google.android.gms.drive.metadata.internal.zzt("md5Checksum", GmsVersion.VERSION_ORLA);
    public static final zzhw zzkj = new zzhw(GmsVersion.VERSION_ORLA);
    public static final MetadataField<String> zzkk = new com.google.android.gms.drive.metadata.internal.zzt("recencyReason", GmsVersion.VERSION_SAGA);
    public static final MetadataField<Boolean> zzkl = new com.google.android.gms.drive.metadata.internal.zzb("subscribed", GmsVersion.VERSION_SAGA);
}
