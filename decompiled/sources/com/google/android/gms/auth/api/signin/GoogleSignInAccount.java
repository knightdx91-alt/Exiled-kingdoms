package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "GoogleSignInAccountCreator")
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zab();

    @VisibleForTesting
    private static Clock zae = DefaultClock.getInstance();

    @SafeParcelable.Field(getter = "getId", id = 2)
    private String mId;

    @SafeParcelable.VersionField(id = 1)
    private final int versionCode;

    @SafeParcelable.Field(getter = "getIdToken", id = 3)
    private String zaf;

    @SafeParcelable.Field(getter = "getEmail", id = 4)
    private String zag;

    @SafeParcelable.Field(getter = "getDisplayName", id = 5)
    private String zah;

    @SafeParcelable.Field(getter = "getPhotoUrl", id = 6)
    private Uri zai;

    @SafeParcelable.Field(getter = "getServerAuthCode", id = 7)
    private String zaj;

    @SafeParcelable.Field(getter = "getExpirationTimeSecs", id = 8)
    private long zak;

    @SafeParcelable.Field(getter = "getObfuscatedIdentifier", id = 9)
    private String zal;

    @SafeParcelable.Field(id = 10)
    private List<Scope> zam;

    @SafeParcelable.Field(getter = "getGivenName", id = 11)
    private String zan;

    @SafeParcelable.Field(getter = "getFamilyName", id = 12)
    private String zao;
    private Set<Scope> zap = new HashSet();

    @SafeParcelable.Constructor
    GoogleSignInAccount(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) Uri uri, @SafeParcelable.Param(id = 7) String str5, @SafeParcelable.Param(id = 8) long j2, @SafeParcelable.Param(id = 9) String str6, @SafeParcelable.Param(id = 10) List<Scope> list, @SafeParcelable.Param(id = 11) String str7, @SafeParcelable.Param(id = 12) String str8) {
        this.versionCode = i2;
        this.mId = str;
        this.zaf = str2;
        this.zag = str3;
        this.zah = str4;
        this.zai = uri;
        this.zaj = str5;
        this.zak = j2;
        this.zal = str6;
        this.zam = list;
        this.zan = str7;
        this.zao = str8;
    }

    @KeepForSdk
    public static GoogleSignInAccount createDefault() {
        Account account = new Account("<<default account>>", "com.google");
        return zaa(null, null, account.name, null, null, null, null, 0L, account.name, new HashSet());
    }

    public static GoogleSignInAccount zaa(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString("photoUrl", null);
        Uri uri = !TextUtils.isEmpty(strOptString) ? Uri.parse(strOptString) : null;
        long j2 = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            hashSet.add(new Scope(jSONArray.getString(i2)));
        }
        GoogleSignInAccount googleSignInAccountZaa = zaa(jSONObject.optString("id"), jSONObject.optString("tokenId", null), jSONObject.optString(Scopes.EMAIL, null), jSONObject.optString("displayName", null), jSONObject.optString("givenName", null), jSONObject.optString("familyName", null), uri, Long.valueOf(j2), jSONObject.getString("obfuscatedIdentifier"), hashSet);
        googleSignInAccountZaa.zaj = jSONObject.optString("serverAuthCode", null);
        return googleSignInAccountZaa;
    }

    private final JSONObject zad() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put("id", getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put(Scopes.EMAIL, getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            if (getPhotoUrl() != null) {
                jSONObject.put("photoUrl", getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.zak);
            jSONObject.put("obfuscatedIdentifier", this.zal);
            JSONArray jSONArray = new JSONArray();
            List<Scope> list = this.zam;
            Scope[] scopeArr = (Scope[]) list.toArray(new Scope[list.size()]);
            Arrays.sort(scopeArr, zaa.zaq);
            for (Scope scope : scopeArr) {
                jSONArray.put(scope.getScopeUri());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.zal.equals(this.zal) && googleSignInAccount.getRequestedScopes().equals(getRequestedScopes());
    }

    public Account getAccount() {
        if (this.zag == null) {
            return null;
        }
        return new Account(this.zag, "com.google");
    }

    public String getDisplayName() {
        return this.zah;
    }

    public String getEmail() {
        return this.zag;
    }

    public String getFamilyName() {
        return this.zao;
    }

    public String getGivenName() {
        return this.zan;
    }

    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.zam);
    }

    public String getId() {
        return this.mId;
    }

    public String getIdToken() {
        return this.zaf;
    }

    public Uri getPhotoUrl() {
        return this.zai;
    }

    @KeepForSdk
    public Set<Scope> getRequestedScopes() {
        HashSet hashSet = new HashSet(this.zam);
        hashSet.addAll(this.zap);
        return hashSet;
    }

    public String getServerAuthCode() {
        return this.zaj;
    }

    public int hashCode() {
        return getRequestedScopes().hashCode() + ((this.zal.hashCode() + 527) * 31);
    }

    @KeepForSdk
    public boolean isExpired() {
        return zae.currentTimeMillis() / 1000 >= this.zak - 300;
    }

    @KeepForSdk
    public GoogleSignInAccount requestExtraScopes(Scope... scopeArr) {
        if (scopeArr != null) {
            Collections.addAll(this.zap, scopeArr);
        }
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 4, getEmail(), false);
        SafeParcelWriter.writeString(parcel, 5, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, getPhotoUrl(), i2, false);
        SafeParcelWriter.writeString(parcel, 7, getServerAuthCode(), false);
        SafeParcelWriter.writeLong(parcel, 8, this.zak);
        SafeParcelWriter.writeString(parcel, 9, this.zal, false);
        SafeParcelWriter.writeTypedList(parcel, 10, this.zam, false);
        SafeParcelWriter.writeString(parcel, 11, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 12, getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final String zab() {
        return this.zal;
    }

    public final String zac() {
        JSONObject jSONObjectZad = zad();
        jSONObjectZad.remove("serverAuthCode");
        return jSONObjectZad.toString();
    }

    private static GoogleSignInAccount zaa(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Long l2, String str7, Set<Scope> set) {
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, (l2 == null ? Long.valueOf(zae.currentTimeMillis() / 1000) : l2).longValue(), Preconditions.checkNotEmpty(str7), new ArrayList((Collection) Preconditions.checkNotNull(set)), str5, str6);
    }
}
