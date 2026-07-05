package net.fdgames.GameWorld;

import net.fdgames.TiledMap.Objects.NPCSerializedSpawnData;
import y0.e;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Follower {
    public int dismissTime;
    public int lastLevel;
    public int missingHP;
    private e spawnData;
    public String spawn_id;
    private boolean updated;
    private NPCSerializedSpawnData updatedData;

    public Follower() {
    }

    public Follower(String str, e eVar) {
        String str2 = eVar.f4039a;
        int i2 = eVar.f4040b;
        String str3 = eVar.f4041c;
        int i3 = eVar.f4042d;
        String str4 = eVar.f4043e;
        String str5 = eVar.f4044f;
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
            nPCSerializedSpawnData.conditions = eVar.f4043e;
            nPCSerializedSpawnData.conversation_id = eVar.f4039a;
            nPCSerializedSpawnData.name = eVar.f4041c;
            nPCSerializedSpawnData.name_ES = "";
            nPCSerializedSpawnData.portrait = eVar.f4042d;
            nPCSerializedSpawnData.tag = eVar.f4044f;
            nPCSerializedSpawnData.wander_range = eVar.f4040b;
            this.updated = true;
        }
        return this.updatedData;
    }
}
