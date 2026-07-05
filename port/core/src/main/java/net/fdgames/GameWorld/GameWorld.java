package net.fdgames.GameWorld;

import com.badlogic.gdx.utils.l;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class GameWorld {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Quests f3187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Castles f3188b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static WorldFactions f3189c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Rumors f3190d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static SpawnTables f3191e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Areas f3192f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static WorldEvents f3193g;

    public static void a() {
        l.d("ExiledKingdoms.GameWorld.load() -        >Subtask 1/8: loading quests....");
        f3187a = new Quests();
        l.d("ExiledKingdoms.GameWorld.load() -        >Subtask 2/8: loading THs....");
        f3188b = new Castles();
        l.d("ExiledKingdoms.GameWorld.load() -        >Subtask 3/8: loading factions....");
        f3189c = new WorldFactions();
        l.d("ExiledKingdoms.GameWorld.load() -        >Subtask 4/8: loading rumors....");
        f3190d = new Rumors();
        l.d("ExiledKingdoms.GameWorld.load() -        >Subtask 5/8: loading spawntables....");
        f3191e = new SpawnTables();
        l.d("ExiledKingdoms.GameWorld.load() -        >Subtask 6/8: loading areas....");
        f3192f = new Areas();
        l.d("ExiledKingdoms.GameWorld.load() -        >Subtask 7/8: loading events....");
        f3193g = new WorldEvents();
        l.d("ExiledKingdoms.GameWorld.load() -        >Subtask 8/8: loading random names....");
        new WorldRandomNames();
    }
}
