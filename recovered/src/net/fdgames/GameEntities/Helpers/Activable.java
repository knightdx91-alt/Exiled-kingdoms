package net.fdgames.GameEntities.Helpers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.fdgames.GameEntities.Final.MapItem;
import net.fdgames.GameEntities.Final.PlantSpawn;
import net.fdgames.GameEntities.Final.SecretDoor;
import net.fdgames.GameEntities.Final.Trap;
import net.fdgames.TiledMap.Objects.MapCastle;
import net.fdgames.TiledMap.Objects.MapConversation;
import net.fdgames.TiledMap.Objects.RestPoint;
import net.fdgames.assets.Assets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Activable {
    private String IconName;
    private String castleID;
    private MapConversation conversation;
    private MapItem item;
    private PlantSpawn plant;
    private RestPoint rest;
    private SecretDoor sd;
    private Shop shop;
    private Trap trap;
    private int type;
    private int uniqueID;

    public Activable() {
    }

    public final TextureRegion a() {
        int i2 = this.type;
        return i2 == 1 ? Assets.a("loot") : i2 == 3 ? this.conversation.c() : i2 == 4 ? this.shop.n() : i2 == 5 ? Assets.a("campfire") : i2 == 6 ? this.plant.E() : i2 == 7 ? Assets.a("tower") : i2 == 8 ? Assets.a("door") : i2 == 10 ? Assets.a("bed") : i2 == 9 ? Assets.a("trap_master") : i2 == 11 ? this.item.E() : Assets.a(this.IconName);
    }

    public final MapConversation b() {
        if (this.type == 3) {
            return this.conversation;
        }
        return null;
    }

    public final PlantSpawn c() {
        return this.plant;
    }

    public final RestPoint d() {
        return this.rest;
    }

    public final SecretDoor e() {
        return this.sd;
    }

    public final Shop f() {
        return this.shop;
    }

    public final MapItem g() {
        return this.item;
    }

    public final Trap h() {
        return this.trap;
    }

    public final int i() {
        return this.type;
    }

    public final int j() {
        return this.uniqueID;
    }

    public final String k() {
        return this.castleID;
    }

    public Activable(int i2) {
        this.type = 1;
    }

    public Activable(String str, int i2) {
        this.type = 2;
        this.IconName = str;
        this.uniqueID = i2;
    }

    public Activable(MapConversation mapConversation) {
        this.type = 3;
        this.conversation = mapConversation;
    }

    public Activable(Shop shop) {
        this.type = 4;
        this.shop = shop;
    }

    public Activable(MapCastle mapCastle) {
        this.type = 7;
        this.castleID = mapCastle.id;
    }

    public Activable(PlantSpawn plantSpawn) {
        this.type = 6;
        this.plant = plantSpawn;
    }

    public Activable(RestPoint restPoint) {
        this.type = 5;
        this.rest = restPoint;
    }

    public Activable(SecretDoor secretDoor) {
        this.type = 8;
        this.sd = secretDoor;
    }

    public Activable(Trap trap) {
        this.type = 9;
        this.trap = trap;
    }

    public Activable(Object obj) {
        this.type = 10;
    }

    public Activable(MapItem mapItem) {
        this.type = 11;
        this.item = mapItem;
    }
}
