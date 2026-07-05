package net.fdgames.ek.android;

import a.a;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.system.licensing.support;
import android.text.TextUtils;
import android.util.Log;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.Calendar;
import l0.b;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.Serializer;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.IPlatformResolver;
import q0.a;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class MainActivity extends AndroidApplication implements IPlatformResolver {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f3422b = 0;
    private int GPGSerror = 0;
    private AchievementsClient mAchievementsClient;
    private GoogleSignInClient mGoogleSignInClient;
    private LeaderboardsClient mLeaderboardsClient;
    private SnapshotsClient mSnapshotsClient;

    static void c(MainActivity mainActivity, GoogleSignInAccount googleSignInAccount) {
        mainActivity.getClass();
        Log.d("GPGS_debug", "onConnected(): connected to Google APIs");
        mainActivity.mAchievementsClient = Games.getAchievementsClient((Activity) mainActivity, googleSignInAccount);
        mainActivity.mLeaderboardsClient = Games.getLeaderboardsClient((Activity) mainActivity, googleSignInAccount);
        mainActivity.mSnapshotsClient = Games.getSnapshotsClient((Activity) mainActivity, googleSignInAccount);
    }

    static void d(MainActivity mainActivity) {
        mainActivity.getClass();
        Log.d("GPGS_debug", "onDisconnected()");
        mainActivity.mAchievementsClient = null;
        mainActivity.mLeaderboardsClient = null;
        mainActivity.mSnapshotsClient = null;
    }

    private static String g(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String string = "";
        boolean z2 = true;
        for (char c2 : str.toCharArray()) {
            if (z2 && Character.isLetter(c2)) {
                StringBuilder sbS = a.s(string);
                sbS.append(Character.toUpperCase(c2));
                string = sbS.toString();
                z2 = false;
            } else {
                if (Character.isWhitespace(c2)) {
                    z2 = true;
                }
                string = string + c2;
            }
        }
        return string;
    }

    public final void a(final q0.a aVar) {
        this.GPGSerror = 0;
        boolean zL = l();
        a.k kVar = a.k.f3814d;
        if (!zL) {
            this.GPGSerror = 9;
            aVar.g(kVar, 9);
        } else if (m()) {
            this.mSnapshotsClient.open("EK_GAME_SAVE", true, 3).addOnFailureListener(new OnFailureListener() { // from class: net.fdgames.ek.android.MainActivity.9
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    int i2 = MainActivity.f3422b;
                    Log.e("GPGS_debug", "Error while opening Snapshot.", exc);
                    MainActivity.this.GPGSerror = 12;
                    aVar.g(a.k.f3814d, 12);
                }
            }).continueWith(new Continuation<SnapshotsClient.DataOrConflict<Snapshot>, byte[]>() { // from class: net.fdgames.ek.android.MainActivity.8
                @Override // com.google.android.gms.tasks.Continuation
                public final byte[] then(Task<SnapshotsClient.DataOrConflict<Snapshot>> task) {
                    Snapshot data = task.getResult().getData();
                    try {
                        aVar.h(a.k.f3812b, 0, data.getSnapshotContents().readFully(), data.getMetadata().getDescription());
                    } catch (IOException e2) {
                        int i2 = MainActivity.f3422b;
                        Log.e("GPGS_debug", "Error while opening Snapshot.", e2);
                        MainActivity.this.GPGSerror = 16;
                        aVar.h(a.k.f3814d, 16, null, e2.getLocalizedMessage());
                    }
                    return null;
                }
            }).addOnCompleteListener(new OnCompleteListener<byte[]>() { // from class: net.fdgames.ek.android.MainActivity.7
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task<byte[]> task) {
                }
            });
        } else {
            this.GPGSerror = 3;
            aVar.g(kVar, 3);
        }
    }

    public final void b(final q0.a aVar, final byte[] bArr) {
        this.GPGSerror = 0;
        boolean zL = l();
        a.k kVar = a.k.f3813c;
        if (!zL) {
            this.GPGSerror = 9;
            aVar.g(kVar, 9);
        } else if (!m()) {
            this.GPGSerror = 3;
            aVar.g(a.k.f3814d, 3);
        } else {
            try {
                this.mSnapshotsClient.open("EK_GAME_SAVE", true, 3).continueWith(new Continuation<SnapshotsClient.DataOrConflict<Snapshot>, byte[]>() { // from class: net.fdgames.ek.android.MainActivity.10
                    @Override // com.google.android.gms.tasks.Continuation
                    public final byte[] then(Task<SnapshotsClient.DataOrConflict<Snapshot>> task) {
                        Snapshot data = task.getResult().getData();
                        data.getSnapshotContents().writeBytes(bArr);
                        MainActivity.this.mSnapshotsClient.commitAndClose(data, new SnapshotMetadataChange.Builder().setDescription(MainActivity.this.j() + " " + Calendar.getInstance().getTime()).build());
                        aVar.f(a.k.f3811a, 0);
                        return null;
                    }
                });
            } catch (NullPointerException unused) {
                this.GPGSerror = 5;
                aVar.g(kVar, 5);
            }
        }
    }

    public final void h() {
        this.mGoogleSignInClient = GoogleSignIn.getClient((Activity) this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestScopes(Drive.SCOPE_APPFOLDER, new Scope[0]).build());
        n();
    }

    public final void i() {
        AchievementsClient achievementsClient;
        if (!ExiledKingdoms.f3377g || (achievementsClient = this.mAchievementsClient) == null) {
            return;
        }
        achievementsClient.getAchievementsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() { // from class: net.fdgames.ek.android.MainActivity.6
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Intent intent) {
                MainActivity.this.startActivityForResult(intent, 5001);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: net.fdgames.ek.android.MainActivity.5
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                System.out.println("Achievements error: " + exc.getLocalizedMessage());
            }
        });
    }

    public final String j() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return g(str2);
        }
        return g(str) + " " + str2;
    }

    public final void k(boolean z2) {
        LeaderboardsClient leaderboardsClient;
        if (!ExiledKingdoms.f3377g || (leaderboardsClient = this.mLeaderboardsClient) == null) {
            return;
        }
        leaderboardsClient.getLeaderboardIntent(z2 ? "CgkIg8PLzvILEAIQUQ" : "CgkIg8PLzvILEAIQUA").addOnSuccessListener(new OnSuccessListener<Intent>() { // from class: net.fdgames.ek.android.MainActivity.4
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Intent intent) {
                MainActivity.this.startActivityForResult(intent, 5001);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: net.fdgames.ek.android.MainActivity.3
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                System.out.println("Leaderboards error:" + exc.getLocalizedMessage());
            }
        });
    }

    public final boolean l() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }

    public final boolean m() {
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        return lastSignedInAccount != null && lastSignedInAccount.getGrantedScopes().contains(Drive.SCOPE_APPFOLDER);
    }

    public final void n() {
        if (ExiledKingdoms.f3377g) {
            startActivityForResult(this.mGoogleSignInClient.getSignInIntent(), GamesStatusCodes.STATUS_VIDEO_UNSUPPORTED);
        }
    }

    public final void o() {
        if (ExiledKingdoms.f3377g) {
            Log.d("GPGS_debug", "signOut()");
            if (l()) {
                this.mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() { // from class: net.fdgames.ek.android.MainActivity.2
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task<Void> task) {
                        boolean zIsSuccessful = task.isSuccessful();
                        int i2 = MainActivity.f3422b;
                        Log.d("GPGS_debug", "signOut(): ".concat(zIsSuccessful ? "success" : "failed"));
                        MainActivity.d(MainActivity.this);
                    }
                });
            } else {
                Log.w("GPGS_debug", "signOut() called, but was not signed in!");
            }
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplication, android.app.Activity
    protected final void onActivityResult(int i2, int i3, Intent intent) {
        if (ExiledKingdoms.f3377g) {
            super.onActivityResult(i2, i3, intent);
            if (i2 == 9001) {
                try {
                    GoogleSignInAccount result = GoogleSignIn.getSignedInAccountFromIntent(intent).getResult(ApiException.class);
                    Log.d("GPGS_debug", "onConnected(): connected to Google APIs");
                    this.mAchievementsClient = Games.getAchievementsClient((Activity) this, result);
                    this.mLeaderboardsClient = Games.getLeaderboardsClient((Activity) this, result);
                    this.mSnapshotsClient = Games.getSnapshotsClient((Activity) this, result);
                } catch (ApiException e2) {
                    String message = e2.getMessage();
                    if (message == null || message.isEmpty()) {
                        message = "Undetermined sign-in error";
                    }
                    Log.d("GPGS_debug", "onDisconnected()");
                    this.mAchievementsClient = null;
                    this.mLeaderboardsClient = null;
                    this.mSnapshotsClient = null;
                    new AlertDialog.Builder(this).setMessage(message).setNeutralButton(android.R.string.ok, (DialogInterface.OnClickListener) null).show();
                }
            }
        }
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        support.supportsystem(this);
        super.onCreate(bundle);
        this.mGoogleSignInClient = GoogleSignIn.getClient((Activity) this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestScopes(Drive.SCOPE_APPFOLDER, new Scope[0]).build());
        AndroidApplicationConfiguration androidApplicationConfiguration = new AndroidApplicationConfiguration();
        androidApplicationConfiguration.hideStatusBar = true;
        androidApplicationConfiguration.useImmersiveMode = true;
        ExiledKingdoms exiledKingdoms = new ExiledKingdoms(this);
        initialize(exiledKingdoms, androidApplicationConfiguration);
        Log.d("EK", "onCreate");
        getWindow().getDecorView().setSystemUiVisibility(5894);
        ExiledKingdoms.f3386p = new s0.a(exiledKingdoms, this);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplication, android.app.Activity
    public final void onPause() {
        b.f2310k = true;
        if (GameData.v().slot != -1) {
            GameData.v().getClass();
            if (GameData.I() && !b.f2309j) {
                Serializer.z(GameData.v().slot, 0);
            }
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (iArr[0] == 0) {
            Log.v("EK", "Permission: " + strArr[0] + "was " + iArr[0]);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplication, android.app.Activity
    public final void onResume() {
        b.f2310k = false;
        super.onResume();
        boolean z2 = ExiledKingdoms.f3377g;
        if (z2 && z2) {
            Log.d("GPGS_debug", "signInSilently()");
            this.mGoogleSignInClient.silentSignIn().addOnCompleteListener(this, new OnCompleteListener<GoogleSignInAccount>() { // from class: net.fdgames.ek.android.MainActivity.1
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task<GoogleSignInAccount> task) {
                    if (task.isSuccessful()) {
                        int i2 = MainActivity.f3422b;
                        Log.d("GPGS_debug", "signInSilently(): success");
                        MainActivity.c(MainActivity.this, task.getResult());
                    } else {
                        int i3 = MainActivity.f3422b;
                        Log.d("GPGS_debug", "signInSilently(): failure", task.getException());
                        MainActivity.d(MainActivity.this);
                    }
                }
            });
        }
    }

    @Override // android.app.Activity
    public final void onStart() {
        try {
            super.onStart();
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Activity
    public final void onStop() {
        try {
            super.onStop();
        } catch (Exception unused) {
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplication, android.app.Activity, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (z2) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    public final void p(String str) {
        AchievementsClient achievementsClient;
        if (!ExiledKingdoms.f3377g || (achievementsClient = this.mAchievementsClient) == null) {
            return;
        }
        achievementsClient.revealImmediate(str);
    }

    public final void q(int i2, String str) {
        AchievementsClient achievementsClient;
        if (!ExiledKingdoms.f3377g || (achievementsClient = this.mAchievementsClient) == null || i2 <= 0) {
            return;
        }
        achievementsClient.setStepsImmediate(str, i2);
    }

    public final void r(int i2) {
        LeaderboardsClient leaderboardsClient;
        if (!ExiledKingdoms.f3377g || (leaderboardsClient = this.mLeaderboardsClient) == null) {
            return;
        }
        leaderboardsClient.submitScoreImmediate("CgkIg8PLzvILEAIQUQ", i2);
    }

    public final void s(int i2) {
        LeaderboardsClient leaderboardsClient;
        if (!ExiledKingdoms.f3377g || (leaderboardsClient = this.mLeaderboardsClient) == null) {
            return;
        }
        leaderboardsClient.submitScoreImmediate("CgkIg8PLzvILEAIQUA", i2);
    }

    public final void t(String str) {
        AchievementsClient achievementsClient;
        if (!ExiledKingdoms.f3377g || (achievementsClient = this.mAchievementsClient) == null) {
            return;
        }
        achievementsClient.unlockImmediate(str);
    }
}
