package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "GoogleSignInOptionsCreator")
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR;
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    private static Comparator<Scope> zaaf;

    @VisibleForTesting
    public static final Scope zar = new Scope(Scopes.PROFILE);

    @VisibleForTesting
    public static final Scope zas = new Scope(Scopes.EMAIL);

    @VisibleForTesting
    public static final Scope zat = new Scope(Scopes.OPEN_ID);

    @VisibleForTesting
    public static final Scope zau;

    @VisibleForTesting
    public static final Scope zav;

    @SafeParcelable.VersionField(id = 1)
    private final int versionCode;

    @SafeParcelable.Field(getter = "isForceCodeForRefreshToken", id = 6)
    private final boolean zaaa;

    @SafeParcelable.Field(getter = "getServerClientId", id = 7)
    private String zaab;

    @SafeParcelable.Field(getter = "getHostedDomain", id = 8)
    private String zaac;

    @SafeParcelable.Field(getter = "getExtensions", id = 9)
    private ArrayList<GoogleSignInOptionsExtensionParcelable> zaad;
    private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaae;

    @SafeParcelable.Field(getter = "getScopes", id = 2)
    private final ArrayList<Scope> zaw;

    @SafeParcelable.Field(getter = "getAccount", id = 3)
    private Account zax;

    @SafeParcelable.Field(getter = "isIdTokenRequested", id = 4)
    private boolean zay;

    @SafeParcelable.Field(getter = "isServerAuthCodeRequested", id = 5)
    private final boolean zaz;

    public static final class Builder {
        private Set<Scope> mScopes;
        private boolean zaaa;
        private String zaab;
        private String zaac;
        private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaag;
        private Account zax;
        private boolean zay;
        private boolean zaz;

        public Builder() {
            this.mScopes = new HashSet();
            this.zaag = new HashMap();
        }

        private final String zac(String str) {
            Preconditions.checkNotEmpty(str);
            String str2 = this.zaab;
            Preconditions.checkArgument(str2 == null || str2.equals(str), "two different server client ids provided");
            return str;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (this.zaag.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                throw new IllegalStateException("Only one extension per type may be added");
            }
            if (googleSignInOptionsExtension.getImpliedScopes() != null) {
                this.mScopes.addAll(googleSignInOptionsExtension.getImpliedScopes());
            }
            this.zaag.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension));
            return this;
        }

        public final GoogleSignInOptions build() {
            if (this.mScopes.contains(GoogleSignInOptions.zav)) {
                Set<Scope> set = this.mScopes;
                Scope scope = GoogleSignInOptions.zau;
                if (set.contains(scope)) {
                    this.mScopes.remove(scope);
                }
            }
            if (this.zay && (this.zax == null || !this.mScopes.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.mScopes), this.zax, this.zay, this.zaz, this.zaaa, this.zaab, this.zaac, this.zaag, null);
        }

        public final Builder requestEmail() {
            this.mScopes.add(GoogleSignInOptions.zas);
            return this;
        }

        public final Builder requestId() {
            this.mScopes.add(GoogleSignInOptions.zat);
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zay = true;
            this.zaab = zac(str);
            return this;
        }

        public final Builder requestProfile() {
            this.mScopes.add(GoogleSignInOptions.zar);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.mScopes.add(scope);
            this.mScopes.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder setAccountName(String str) {
            this.zax = new Account(Preconditions.checkNotEmpty(str), "com.google");
            return this;
        }

        public final Builder setHostedDomain(String str) {
            this.zaac = Preconditions.checkNotEmpty(str);
            return this;
        }

        public final Builder requestServerAuthCode(String str, boolean z2) {
            this.zaz = true;
            this.zaab = zac(str);
            this.zaaa = z2;
            return this;
        }

        public Builder(GoogleSignInOptions googleSignInOptions) {
            this.mScopes = new HashSet();
            this.zaag = new HashMap();
            Preconditions.checkNotNull(googleSignInOptions);
            this.mScopes = new HashSet(googleSignInOptions.zaw);
            this.zaz = googleSignInOptions.zaz;
            this.zaaa = googleSignInOptions.zaaa;
            this.zay = googleSignInOptions.zay;
            this.zaab = googleSignInOptions.zaab;
            this.zax = googleSignInOptions.zax;
            this.zaac = googleSignInOptions.zaac;
            this.zaag = GoogleSignInOptions.zaa(googleSignInOptions.zaad);
        }
    }

    static {
        Scope scope = new Scope(Scopes.GAMES_LITE);
        zau = scope;
        zav = new Scope(Scopes.GAMES);
        DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
        DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(scope, new Scope[0]).build();
        CREATOR = new zad();
        zaaf = new zac();
    }

    @SafeParcelable.Constructor
    GoogleSignInOptions(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) ArrayList<Scope> arrayList, @SafeParcelable.Param(id = 3) Account account, @SafeParcelable.Param(id = 4) boolean z2, @SafeParcelable.Param(id = 5) boolean z3, @SafeParcelable.Param(id = 6) boolean z4, @SafeParcelable.Param(id = 7) String str, @SafeParcelable.Param(id = 8) String str2, @SafeParcelable.Param(id = 9) ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2) {
        this(i2, arrayList, account, z2, z3, z4, str, str2, zaa(arrayList2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<Integer, GoogleSignInOptionsExtensionParcelable> zaa(List<GoogleSignInOptionsExtensionParcelable> list) {
        HashMap map = new HashMap();
        if (list == null) {
            return map;
        }
        for (GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable : list) {
            map.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.getType()), googleSignInOptionsExtensionParcelable);
        }
        return map;
    }

    public static GoogleSignInOptions zab(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            hashSet.add(new Scope(jSONArray.getString(i2)));
        }
        String strOptString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(3, (ArrayList<Scope>) new ArrayList(hashSet), !TextUtils.isEmpty(strOptString) ? new Account(strOptString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null), new HashMap());
    }

    private final JSONObject zad() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zaw, zaaf);
            ArrayList<Scope> arrayList = this.zaw;
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Scope scope = arrayList.get(i2);
                i2++;
                jSONArray.put(scope.getScopeUri());
            }
            jSONObject.put("scopes", jSONArray);
            Account account = this.zax;
            if (account != null) {
                jSONObject.put("accountName", account.name);
            }
            jSONObject.put("idTokenRequested", this.zay);
            jSONObject.put("forceCodeForRefreshToken", this.zaaa);
            jSONObject.put("serverAuthRequested", this.zaz);
            if (!TextUtils.isEmpty(this.zaab)) {
                jSONObject.put("serverClientId", this.zaab);
            }
            if (!TextUtils.isEmpty(this.zaac)) {
                jSONObject.put("hostedDomain", this.zaac);
            }
            return jSONObject;
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0051 A[Catch: ClassCastException -> 0x0082, TryCatch #0 {ClassCastException -> 0x0082, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0017, B:12:0x0027, B:15:0x0034, B:17:0x0038, B:22:0x0049, B:24:0x0051, B:29:0x0068, B:31:0x0070, B:33:0x0078, B:27:0x005c, B:20:0x003f), top: B:39:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005c A[Catch: ClassCastException -> 0x0082, TryCatch #0 {ClassCastException -> 0x0082, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0017, B:12:0x0027, B:15:0x0034, B:17:0x0038, B:22:0x0049, B:24:0x0051, B:29:0x0068, B:31:0x0070, B:33:0x0078, B:27:0x005c, B:20:0x003f), top: B:39:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0080 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zaad.size() <= 0 && googleSignInOptions.zaad.size() <= 0 && this.zaw.size() == googleSignInOptions.getScopes().size() && this.zaw.containsAll(googleSignInOptions.getScopes())) {
                Account account = this.zax;
                if (account == null) {
                    if (googleSignInOptions.getAccount() == null) {
                        if (TextUtils.isEmpty(this.zaab)) {
                            if (this.zaab.equals(googleSignInOptions.getServerClientId())) {
                                if (this.zaaa == googleSignInOptions.isForceCodeForRefreshToken()) {
                                    if (this.zaz != googleSignInOptions.isServerAuthCodeRequested()) {
                                    }
                                }
                            }
                        } else if (TextUtils.isEmpty(googleSignInOptions.getServerClientId())) {
                            if (this.zaaa == googleSignInOptions.isForceCodeForRefreshToken() && this.zay == googleSignInOptions.isIdTokenRequested()) {
                                if (this.zaz != googleSignInOptions.isServerAuthCodeRequested()) {
                                    return true;
                                }
                            }
                        }
                    }
                } else if (account.equals(googleSignInOptions.getAccount())) {
                    if (TextUtils.isEmpty(this.zaab)) {
                    }
                }
            }
        } catch (ClassCastException unused) {
        }
        return false;
    }

    @KeepForSdk
    public Account getAccount() {
        return this.zax;
    }

    @KeepForSdk
    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zaad;
    }

    public Scope[] getScopeArray() {
        ArrayList<Scope> arrayList = this.zaw;
        return (Scope[]) arrayList.toArray(new Scope[arrayList.size()]);
    }

    @KeepForSdk
    public ArrayList<Scope> getScopes() {
        return new ArrayList<>(this.zaw);
    }

    @KeepForSdk
    public String getServerClientId() {
        return this.zaab;
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList<Scope> arrayList2 = this.zaw;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            Scope scope = arrayList2.get(i2);
            i2++;
            arrayList.add(scope.getScopeUri());
        }
        Collections.sort(arrayList);
        return new HashAccumulator().addObject(arrayList).addObject(this.zax).addObject(this.zaab).zaa(this.zaaa).zaa(this.zay).zaa(this.zaz).hash();
    }

    @KeepForSdk
    public boolean isForceCodeForRefreshToken() {
        return this.zaaa;
    }

    @KeepForSdk
    public boolean isIdTokenRequested() {
        return this.zay;
    }

    @KeepForSdk
    public boolean isServerAuthCodeRequested() {
        return this.zaz;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeTypedList(parcel, 2, getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getAccount(), i2, false);
        SafeParcelWriter.writeBoolean(parcel, 4, isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel, 5, isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel, 6, isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel, 7, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zaac, false);
        SafeParcelWriter.writeTypedList(parcel, 9, getExtensions(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final String zae() {
        return zad().toString();
    }

    private GoogleSignInOptions(int i2, ArrayList<Scope> arrayList, Account account, boolean z2, boolean z3, boolean z4, String str, String str2, Map<Integer, GoogleSignInOptionsExtensionParcelable> map) {
        this.versionCode = i2;
        this.zaw = arrayList;
        this.zax = account;
        this.zay = z2;
        this.zaz = z3;
        this.zaaa = z4;
        this.zaab = str;
        this.zaac = str2;
        this.zaad = new ArrayList<>(map.values());
        this.zaae = map;
    }

    /* synthetic */ GoogleSignInOptions(int i2, ArrayList arrayList, Account account, boolean z2, boolean z3, boolean z4, String str, String str2, Map map, zac zacVar) {
        this(3, (ArrayList<Scope>) arrayList, account, z2, z3, z4, str, str2, (Map<Integer, GoogleSignInOptionsExtensionParcelable>) map);
    }
}
