package net.fdgames.GameWorld;

import m0.e;
import net.fdgames.TiledMap.Objects.NPCSerializedSpawnData;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class Follower {
    public int dismissTime;
    public int lastLevel;
    public int missingHP;
    private e spawnData;
    public String spawn_id;
    private boolean updated;
    private NPCSerializedSpawnData updatedData;

    public Follower(String str, e eVar) {
        String str2 = eVar.f2445a;
        int i2 = eVar.f2446b;
        String str3 = eVar.f2447c;
        int i3 = eVar.f2448d;
        String str4 = eVar.f2449e;
        String str5 = eVar.f2450f;
        NPCSerializedSpawnData nPCSerializedSpawnData = new NPCSerializedSpawnData();
        nPCSerializedSpawnData.conversation_id = str2;
        nPCSerializedSpawnData.wander_range = i2;
        nPCSerializedSpawnData.name = str3;
        nPCSerializedSpawnData.name_ES = str3;
        nPCSerializedSpawnData.portrait = i3;
        nPCSerializedSpawnData.conditions = str4;
        nPCSerializedSpawnData.tag = str5;
        this.updatedData = nPCSerializedSpawnData;
        this.updated = true;
        this.spawn_id = str;
    }

    public final NPCSerializedSpawnData a() {
        if (!this.updated) {
            NPCSerializedSpawnData nPCSerializedSpawnData = new NPCSerializedSpawnData();
            this.updatedData = nPCSerializedSpawnData;
            e eVar = this.spawnData;
            nPCSerializedSpawnData.conditions = eVar.f2449e;
            nPCSerializedSpawnData.conversation_id = eVar.f2445a;
            nPCSerializedSpawnData.name = eVar.f2447c;
            nPCSerializedSpawnData.name_ES = "";
            nPCSerializedSpawnData.portrait = eVar.f2448d;
            nPCSerializedSpawnData.tag = eVar.f2450f;
            nPCSerializedSpawnData.wander_range = eVar.f2446b;
            this.updated = true;
        }
        return this.updatedData;
    }

    public Follower() {
    }
}
