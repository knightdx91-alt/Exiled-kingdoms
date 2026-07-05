package net.fdgames.GameWorld;

import android.app.cYK.BQmoQ;
import com.badlogic.gdx.utils.l;
import com.google.android.gms.tasks.bnw.jUhYGTigo;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class GameWorld {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Quests f3408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Castles f3409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static WorldFactions f3410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Rumors f3411d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static SpawnTables f3412e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Areas f3413f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static WorldEvents f3414g;

    public static void a() {
        l.e("ExiledKingdoms.GameWorld.load() -        >Subtask 1/8: loading quests....");
        f3408a = new Quests();
        l.e("ExiledKingdoms.GameWorld.load() -        >Subtask 2/8: loading THs....");
        f3409b = new Castles();
        l.e(jUhYGTigo.QMpkmwQRlBYSj);
        f3410c = new WorldFactions();
        l.e("ExiledKingdoms.GameWorld.load() -        >Subtask 4/8: loading rumors....");
        f3411d = new Rumors();
        l.e("ExiledKingdoms.GameWorld.load() -        >Subtask 5/8: loading spawntables....");
        f3412e = new SpawnTables();
        l.e(BQmoQ.OSoMXPMl);
        f3413f = new Areas();
        l.e("ExiledKingdoms.GameWorld.load() -        >Subtask 7/8: loading events....");
        f3414g = new WorldEvents();
        l.e("ExiledKingdoms.GameWorld.load() -        >Subtask 8/8: loading random names....");
        new WorldRandomNames();
    }

    public static void b() {
        if (f3414g == null || f3413f == null) {
            l.e("ExiledKingdoms.GameWorld.ensureLoaded() - reloading missing gameworld data....");
            a();
        }
    }
}
