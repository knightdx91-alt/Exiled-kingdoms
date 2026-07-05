package net.fdgames.ek.android.lan;

import android.widget.ScrollView;
import android.widget.TextView;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.a;
import com.google.android.gms.common.api.Api;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterInventory;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterSkill;
import net.fdgames.GameEntities.CharacterSheet.CharacterStats;
import net.fdgames.GameEntities.Final.MapEffectEntity;
import net.fdgames.GameEntities.Final.MonsterSpawn;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.GameObject;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.Helpers.DamageEffect;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.Follower;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameVariables;
import net.fdgames.GameWorld.MessageRouter;
import net.fdgames.GameWorld.Party;
import net.fdgames.GameWorld.WorldFactions;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.Spawn;
import net.fdgames.Rules.WeaponStats;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.NPCSerializedSpawnData;
import net.fdgames.assets.AnimationLoader;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.IPlatformResolver;
import net.fdgames.ek.android.MainActivity;
import net.fdgames.ek.android.lan.LanSessionManager;
import y0.b;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes2.dex */
public final class LanGameBridge {
    private static final long LAN_PUBLISH_INTERVAL_MS = 50;
    private static String lastAppliedWorldNpcLevelId;
    private static String lastCapturedSpellId;
    private static int lastCapturedSpellSeq;
    private static long lastNpcBroadcastAt;
    private static long lastTickAt;
    private static int localActionSeq;
    private static int localCombatSeq;
    private static int localGoldBackup;
    private static String localLastActionStateName;
    private static int localLastActionStateTimeMs;
    private static LinkedHashMap localFollowerSnapshots = new LinkedHashMap();
    private static LinkedHashMap peerActors = new LinkedHashMap();
    private static LinkedHashMap peerActorOwners = new LinkedHashMap();
    private static LinkedHashMap peerActionSeqs = new LinkedHashMap();
    private static LinkedHashMap peerCombatSeqs = new LinkedHashMap();
    private static LinkedHashMap peerCompanionActors = new LinkedHashMap();
    private static LinkedHashMap peerFollowerActors = new LinkedHashMap();
    private static LinkedHashMap peerMotionStates = new LinkedHashMap();
    private static LinkedHashMap peerAppliedAnimationKeys = new LinkedHashMap();
    private static LinkedHashMap peerAttackStateLocks = new LinkedHashMap();
    private static LinkedHashMap peerAttackStateNames = new LinkedHashMap();
    private static LinkedHashMap peerAttackFacingNames = new LinkedHashMap();
    private static LinkedHashMap peerAppliedSampleTimes = new LinkedHashMap();
    private static LinkedHashMap peerAppliedVisualKeys = new LinkedHashMap();
    private static LinkedHashMap peerSummonActors = new LinkedHashMap();
    private static LinkedHashMap peerSummonOwners = new LinkedHashMap();
    private static LinkedHashMap peerVisualSignatures = new LinkedHashMap();
    private static LinkedHashMap pendingPeerDamageProcs = new LinkedHashMap();
    private static LinkedHashMap worldNpcActors = new LinkedHashMap();
    private static HashSet peerDeadNames = new HashSet();
    private static String localLastActionSignature = "";
    private static String lastSyncedLevelId = "";
    private static LanSessionManager.FollowerState localCompanionSnapshot = null;

    private LanGameBridge() {
    }

    private static void appendSnapshotField(StringBuilder sb, Object obj, String str) {
        if (sb == null || obj == null || str == null) {
            return;
        }
        Object fieldValue = getFieldValue(obj, str);
        if (sb.length() > 0) {
            sb.append(";");
        }
        sb.append(str).append("=").append(asString(fieldValue));
    }

    private static void applyAnimationSet(MapActor mapActor, String str) {
        if (mapActor == null || str == null) {
            return;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return;
        }
        ArrayList<String> arrayList = mapActor.animationSetName;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            mapActor.animationSetName = arrayList;
        } else {
            arrayList.clear();
        }
        for (String str2 : strTrim.split(";")) {
            if (str2 != null) {
                String strTrim2 = str2.trim();
                if (!strTrim2.isEmpty()) {
                    arrayList.add(strTrim2);
                }
            }
        }
        mapActor.v0();
    }

    private static void applyFieldFromString(Object obj, String str, String str2) {
        if (obj == null || str == null) {
            return;
        }
        try {
            Field fieldFindField = findField(obj.getClass(), str);
            if (fieldFindField == null) {
                return;
            }
            fieldFindField.setAccessible(true);
            String name = fieldFindField.getType().getName();
            fieldFindField.set(obj, ("boolean".equals(name) || "java.lang.Boolean".equals(name)) ? Boolean.valueOf(parseBooleanString(str2)) : ("int".equals(name) || "java.lang.Integer".equals(name)) ? Integer.valueOf(Integer.parseInt(str2)) : ("float".equals(name) || "java.lang.Float".equals(name)) ? Float.valueOf(Float.parseFloat(str2)) : str2);
        } catch (Exception e2) {
        }
    }

    private static void applyPeerAttackVisualLock(String str, NPC npc, float f2) {
        String str2;
        String str3;
        if (npc == null || f2 < 0.0f) {
            return;
        }
        String str4 = "ATTACKING";
        if (str != null) {
            LinkedHashMap linkedHashMap = peerAttackFacingNames;
            if (linkedHashMap != null && (str3 = (String) linkedHashMap.get(str)) != null) {
                String strTrim = str3.trim();
                if (!strTrim.isEmpty()) {
                    npc.facing = resolveFacing(strTrim);
                }
            }
            LinkedHashMap linkedHashMap2 = peerAttackStateNames;
            if (linkedHashMap2 != null && (str2 = (String) linkedHashMap2.get(str)) != null) {
                String strTrim2 = str2.trim();
                if (!strTrim2.isEmpty()) {
                    str4 = strTrim2;
                }
            }
        }
        MapActor.ActorState actorStateResolveActorState = resolveActorState(str4);
        if (actorStateResolveActorState == MapActor.ActorState.f3286b) {
            actorStateResolveActorState = MapActor.ActorState.f3288d;
        }
        npc.q0(actorStateResolveActorState);
        npc.actionStartTime = GameLevel.b();
        npc.stateRelativeTime = f2;
    }

    private static void applyPeerCombatEffectsSnapshot(Character character, LanSessionManager.PlayerState playerState) {
        String str;
        Object fieldValue;
        Object fieldValue2;
        int iIndexOf;
        if (character == null || playerState == null || (str = playerState.combatEffectsSnapshot) == null) {
            return;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty() || (fieldValue = getFieldValue(character, "sheet")) == null || (fieldValue2 = getFieldValue(fieldValue, "effects")) == null) {
            return;
        }
        for (String str2 : strTrim.split(";")) {
            if (str2 != null) {
                String strTrim2 = str2.trim();
                if (!strTrim2.isEmpty() && (iIndexOf = strTrim2.indexOf(61)) > 0) {
                    applyFieldFromString(fieldValue2, strTrim2.substring(0, iIndexOf).trim(), strTrim2.substring(iIndexOf + 1).trim());
                }
            }
        }
    }

    private static void applyPeerCombatSnapshot(NPC npc, LanSessionManager.PlayerState playerState) {
        CharacterStats characterStats;
        if (npc == null || playerState == null) {
            return;
        }
        applyPeerSkillSnapshot(npc, playerState);
        applyPeerCombatEffectsSnapshot(npc, playerState);
        CharacterSheet characterSheet = npc.sheet;
        if (characterSheet == null || (characterStats = characterSheet.stats) == null) {
            return;
        }
        int i2 = playerState.missingHp;
        if (i2 < 0) {
            i2 = 0;
        }
        characterStats.missingHP = i2;
        int i3 = playerState.missingMana;
        if (i3 < 0) {
            i3 = 0;
        }
        characterStats.missingMana = i3;
    }

    private static void applyPeerEquipmentSnapshot(NPC npc, LanSessionManager.PlayerState playerState) {
        CharacterInventory characterInventory;
        if (npc == null || playerState == null) {
            return;
        }
        npc.gender = resolveGender(playerState.genderName);
        CharacterSheet characterSheet = npc.sheet;
        if (characterSheet == null || (characterInventory = characterSheet.inventory) == null) {
            return;
        }
        int i2 = playerState.portraitIndex;
        if (i2 >= 0) {
            npc.portraitIndex = i2;
        }
        boolean z2 = false;
        int i3 = playerState.slotBodyItemId;
        if (i3 >= 0 && characterInventory.slot_body != i3) {
            characterInventory.slot_body = i3;
            z2 = true;
        }
        int i4 = playerState.slotFeetItemId;
        if (i4 >= 0 && characterInventory.slot_feet != i4) {
            characterInventory.slot_feet = i4;
            z2 = true;
        }
        int i5 = playerState.slotHandsItemId;
        if (i5 >= 0 && characterInventory.slot_hands != i5) {
            characterInventory.slot_hands = i5;
            z2 = true;
        }
        int i6 = playerState.slotHeadItemId;
        if (i6 >= 0 && characterInventory.slot_head != i6) {
            characterInventory.slot_head = i6;
            z2 = true;
        }
        int i7 = playerState.slotLegsItemId;
        if (i7 >= 0 && characterInventory.slot_legs != i7) {
            characterInventory.slot_legs = i7;
            z2 = true;
        }
        int i8 = playerState.slotMainhandItemId;
        if (i8 >= 0 && characterInventory.slot_mainhand != i8) {
            characterInventory.slot_mainhand = i8;
            z2 = true;
        }
        int i9 = playerState.slotOffhandItemId;
        if (i9 >= 0 && characterInventory.slot_offhand != i9) {
            characterInventory.slot_offhand = i9;
            z2 = true;
        }
        if (z2) {
            characterInventory.s();
            npc.v0();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void applyPeerMotion(String str, NPC npc, LanSessionManager.PlayerState playerState) {
        boolean z2;
        int i2;
        if (npc == null || playerState == null) {
            return;
        }
        LinkedHashMap linkedHashMap = peerMotionStates;
        LanSessionManager.PlayerState playerState2 = linkedHashMap != null ? (LanSessionManager.PlayerState) linkedHashMap.get(str) : null;
        if (playerState2 != null) {
            String str2 = playerState2.currentMapName;
            String str3 = playerState.currentMapName;
            z2 = (str2 != null && (str3 == null || !str2.equals(str3))) || Math.abs(npc.f3307x - playerState.f3655x) > 96 || Math.abs(npc.f3308y - playerState.f3656y) > 96;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j2 = playerState.sampleTimeMs;
        if (j2 <= 0) {
            j2 = jCurrentTimeMillis;
        }
        long j3 = jCurrentTimeMillis - j2;
        if (j3 < 0) {
            j3 = 0;
        }
        if (j3 > 400) {
            j3 = 400;
        }
        float f2 = j3 / 1000.0f;
        int iRound = Math.round(playerState.f3655x + (playerState.speedX * f2));
        int iRound2 = Math.round(playerState.f3656y + (playerState.speedY * f2));
        if (z2) {
            npc.f3307x = iRound;
            npc.f3308y = iRound2;
            Coords coords = npc.destination;
            if (coords != null) {
                coords.f3508x = -1;
                coords.f3509y = -1;
            }
        } else {
            int i3 = iRound - npc.f3307x;
            int i4 = iRound2 - npc.f3308y;
            if (Math.abs(i3) > 1 || Math.abs(i4) > 1) {
                int iRound3 = Math.round(i3 * 0.5f);
                if (iRound3 == 0) {
                    if (i3 > 0) {
                        iRound3 = 1;
                    } else if (i3 < 0) {
                        iRound3 = -1;
                    }
                }
                npc.f3307x += iRound3;
                int iRound4 = Math.round(i4 * 0.5f);
                if (iRound4 != 0) {
                    npc.f3308y += iRound4;
                } else {
                    if (i4 <= 0) {
                        i2 = i4 < 0 ? -1 : 1;
                        npc.f3308y += iRound4;
                    }
                    npc.f3308y += i2;
                }
            } else if (Math.abs(playerState.speedX) <= 0.05f && Math.abs(playerState.speedY) <= 0.05f) {
                npc.f3307x = playerState.f3655x;
                npc.f3308y = playerState.f3656y;
            }
        }
        npc.speedX = playerState.speedX;
        npc.speedY = playerState.speedY;
        npc.stuck = false;
        LinkedHashMap linkedHashMap2 = peerMotionStates;
        if (linkedHashMap2 != null) {
            linkedHashMap2.put(str, playerState);
        }
    }

    private static void applyPeerSkillSnapshot(Character character, LanSessionManager.PlayerState playerState) {
        Object fieldValue;
        int iIndexOf;
        if (character == null || playerState == null) {
            return;
        }
        String str = playerState.skillSnapshot;
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                Object fieldValue2 = getFieldValue(character, "sheet");
                if (fieldValue2 == null || (fieldValue = getFieldValue(fieldValue2, "skillSet")) == null || !(fieldValue instanceof SkillSet)) {
                    return;
                }
                SkillSet skillSet = (SkillSet) fieldValue;
                Object fieldValue3 = getFieldValue(skillSet, "characterSkills");
                if (fieldValue3 instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) fieldValue3;
                    arrayList.clear();
                    String[] strArrSplit = strTrim.split(",");
                    int length = strArrSplit.length;
                    if (length > 2000) {
                        length = 2000;
                    }
                    for (int i2 = 0; i2 < length; i2++) {
                        String str2 = strArrSplit[i2];
                        if (str2 != null) {
                            String strTrim2 = str2.trim();
                            if (!strTrim2.isEmpty() && (iIndexOf = strTrim2.indexOf(61)) > 0) {
                                String strSubstring = strTrim2.substring(0, iIndexOf);
                                String strSubstring2 = strTrim2.substring(iIndexOf + 1);
                                String strTrim3 = strSubstring.trim();
                                int i3 = Integer.parseInt(strSubstring2.trim());
                                if (i3 > 0) {
                                    arrayList.add(new CharacterSkill(strTrim3, i3));
                                }
                            }
                        }
                    }
                    skillSet.n();
                    return;
                }
                return;
            }
        }
        ensureActorSkillLevel(character, "stealth", playerState.stealthSkillLevel);
    }

    private static void applyPeerVisualFxMask(NPC npc, LanSessionManager.PlayerState playerState) {
        CharacterSheet characterSheet;
        CharacterEffects characterEffects;
        CharacterResistances characterResistances;
        if (npc == null || playerState == null || (characterSheet = npc.sheet) == null || (characterEffects = characterSheet.effects) == null) {
            return;
        }
        Boolean bool = Boolean.FALSE;
        Boolean bool2 = Boolean.TRUE;
        characterEffects.holy_shielded = bool;
        characterEffects.shielded = bool;
        characterEffects.shieldBonus = 0;
        characterEffects.mageArmor_Charges = 0;
        characterEffects.might = bool;
        characterEffects.might_arbenos = bool;
        characterEffects.might_prayer = bool;
        characterEffects.stab = bool;
        characterEffects.fury = bool;
        characterEffects.rapid_fire = false;
        characterEffects.disintegrate = bool;
        characterEffects.duel = bool;
        characterEffects.flurry = false;
        characterEffects.rage = bool;
        characterEffects.bloodlust = bool;
        characterEffects.evasion = bool;
        characterEffects.stealth = bool;
        characterEffects.slowed = bool;
        characterEffects.stunned = bool;
        CharacterResistances characterResistances2 = characterEffects.resistances;
        if (characterResistances2 != null) {
            characterResistances2.e();
        }
        int i2 = playerState.visualFxMask;
        if ((i2 & 1) != 0) {
            characterEffects.holy_shielded = bool2;
        }
        if ((i2 & 2) != 0) {
            characterEffects.might = bool2;
        }
        if ((i2 & 4) != 0) {
            characterEffects.bloodlust = bool2;
        }
        if ((i2 & 8) != 0) {
            characterEffects.evasion = bool2;
        }
        if ((i2 & 16) != 0 && (characterResistances = characterEffects.resistances) != null) {
            characterResistances.f(1);
        }
        if ((i2 & 32) != 0) {
            characterEffects.stealth = bool2;
        }
        if ((i2 & 64) != 0) {
            characterEffects.slowed = bool2;
        }
        if ((i2 & VertexAttributes.Usage.Tangent) != 0) {
            characterEffects.stunned = bool2;
        }
    }

    private static void applyReceivedWorldNpcStates(LanSessionManager lanSessionManager, String str) {
        int i2;
        ArrayList<MapActor> arrayListE;
        NPC npc;
        String str2;
        NPC npc2;
        int iZ;
        CharacterStats characterStats;
        NPC npc3;
        String str3;
        try {
            prepareWorldNpcLevel(str);
            String lastNpcStateData = lanSessionManager.getLastNpcStateData();
            if (lastNpcStateData != null) {
                if (lastNpcStateData.startsWith("NPCSTATE2\t")) {
                    applyReceivedWorldNpcStatesV2(lanSessionManager, str, lastNpcStateData);
                    return;
                }
                String[] strArrSplit = lastNpcStateData.split("\t");
                if (strArrSplit.length < 3 || !"NPCSTATE".equals(strArrSplit[0]) || (i2 = Integer.parseInt(strArrSplit[2])) <= 0 || (arrayListE = GameLevel.e()) == null) {
                    return;
                }
                int i3 = 0;
                int i4 = 3;
                while (i3 < i2) {
                    String str4 = strArrSplit[i4];
                    int i5 = Integer.parseInt(strArrSplit[i4 + 1]);
                    int i6 = Integer.parseInt(strArrSplit[i4 + 2]);
                    MapActor.ActorState actorStateResolveActorState = resolveActorState(strArrSplit[i4 + 3]);
                    MapActor.Facing facingResolveFacing = resolveFacing(strArrSplit[i4 + 4]);
                    int i7 = Integer.parseInt(strArrSplit[i4 + 5]);
                    i3++;
                    i4 += 6;
                    Iterator<MapActor> it = arrayListE.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            MapActor next = it.next();
                            if ((next instanceof NPC) && (str3 = (npc3 = (NPC) next).tag) != null && str4.equals(str3)) {
                                npc2 = npc3;
                                break;
                            }
                        } else {
                            for (MapActor mapActor : arrayListE) {
                                if ((mapActor instanceof NPC) && (str2 = (npc = (NPC) mapActor).spawn_id) != null && str4.equals(str2)) {
                                    npc2 = npc;
                                }
                            }
                        }
                    }
                    npc2.f3307x = i5;
                    npc2.f3308y = i6;
                    if (actorStateResolveActorState != null) {
                        npc2.q0(actorStateResolveActorState);
                    }
                    if (facingResolveFacing != null) {
                        npc2.facing = facingResolveFacing;
                    }
                    npc2.ai_disabled = false;
                    CharacterSheet characterSheet = npc2.sheet;
                    if (characterSheet != null && (iZ = characterSheet.z()) > 0 && (characterStats = characterSheet.stats) != null) {
                        int i8 = (iZ * (100 - i7)) / 100;
                        if (i8 < 0) {
                            i8 = 0;
                        }
                        characterStats.missingHP = i8;
                    }
                }
            }
        } catch (Exception e2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x009f A[Catch: Exception -> 0x01af, TryCatch #0 {Exception -> 0x01af, blocks: (B:3:0x0002, B:7:0x0010, B:9:0x001b, B:11:0x0026, B:13:0x002f, B:14:0x0034, B:16:0x0038, B:18:0x003e, B:19:0x0041, B:22:0x004d, B:24:0x0056, B:27:0x005d, B:29:0x0085, B:31:0x008c, B:34:0x0094, B:37:0x009f, B:38:0x00a3, B:40:0x00a9, B:44:0x00b6, B:46:0x00ba, B:48:0x00bf, B:50:0x00c9, B:52:0x00cd, B:54:0x00d7, B:56:0x00db, B:58:0x00e1, B:60:0x00e7, B:61:0x00ea, B:65:0x00f9, B:67:0x0104, B:70:0x010f, B:74:0x011e, B:76:0x0129, B:79:0x0134, B:81:0x0152, B:83:0x0158, B:85:0x015f, B:87:0x0168, B:89:0x016c, B:90:0x016f, B:92:0x0177, B:94:0x017b, B:95:0x017d, B:97:0x0183, B:99:0x018a, B:101:0x0190, B:103:0x0194, B:106:0x019f, B:77:0x012f, B:78:0x0132, B:68:0x010a, B:69:0x010d, B:107:0x01a1, B:108:0x01a7, B:109:0x01ab), top: B:113:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void applyReceivedWorldNpcStatesV2(LanSessionManager lanSessionManager, String str, String str2) {
        int iZ;
        CharacterStats characterStats;
        LinkedHashMap linkedHashMap;
        int iRound;
        int iRound2;
        if (str != null) {
            try {
                String strTrim = str.trim();
                if (!strTrim.isEmpty()) {
                    if (str2 != null) {
                        String[] strArrSplit = str2.split("\t", -1);
                        if (strArrSplit.length < 3 || !"NPCSTATE2".equals(strArrSplit[0])) {
                            return;
                        }
                        String str3 = strArrSplit[1];
                        if (!strTrim.equals(str3)) {
                            clearWorldNpcActors();
                            return;
                        }
                        String str4 = lastAppliedWorldNpcLevelId;
                        if (str4 != null && !str4.equals(str3)) {
                            clearWorldNpcActors();
                        }
                        lastAppliedWorldNpcLevelId = str3;
                        int i2 = Integer.parseInt(strArrSplit[2]);
                        if (i2 < 0) {
                            i2 = 0;
                        }
                        HashSet hashSet = new HashSet();
                        int i3 = 0;
                        int i4 = 3;
                        while (i3 < i2) {
                            if (strArrSplit.length <= i4 + 10) {
                                break;
                            }
                            String str5 = strArrSplit[i4];
                            String str6 = strArrSplit[i4 + 1];
                            int i5 = Integer.parseInt(strArrSplit[i4 + 2]);
                            String str7 = strArrSplit[i4 + 4];
                            String str8 = strArrSplit[i4 + 5];
                            int i6 = Integer.parseInt(strArrSplit[i4 + 6]);
                            int i7 = Integer.parseInt(strArrSplit[i4 + 7]);
                            String strTrim2 = str5 != null ? str5.trim() : "";
                            if (strTrim2.isEmpty()) {
                                if (str7 != null) {
                                    strTrim2 = str7.trim();
                                    if (strTrim2.isEmpty()) {
                                        strTrim2 = buildWorldNpcFallbackAuthId(str6, i5);
                                    }
                                }
                            }
                            if (!strTrim2.isEmpty()) {
                                hashSet.add(strTrim2);
                                NPC npcFindAuthoritativeWorldNpcByAuthId = findAuthoritativeWorldNpcByAuthId(strTrim2);
                                if (npcFindAuthoritativeWorldNpcByAuthId != null && npcFindAuthoritativeWorldNpcByAuthId != null) {
                                    LinkedHashMap linkedHashMap2 = worldNpcActors;
                                    if (linkedHashMap2 != null) {
                                        linkedHashMap2.put(strTrim2, npcFindAuthoritativeWorldNpcByAuthId);
                                    }
                                    if (str7 != null) {
                                        String strTrim3 = str7.trim();
                                        if (!strTrim3.isEmpty()) {
                                            npcFindAuthoritativeWorldNpcByAuthId.unique_tag = strTrim3;
                                        }
                                    }
                                    if (str8 != null) {
                                        String strTrim4 = str8.trim();
                                        if (!strTrim4.isEmpty()) {
                                            npcFindAuthoritativeWorldNpcByAuthId.tag = strTrim4;
                                        }
                                    }
                                    if (i5 > 0 && npcFindAuthoritativeWorldNpcByAuthId.L1() != i5 && GameLevel.h(i5) != null) {
                                        npcFindAuthoritativeWorldNpcByAuthId.U1(i5);
                                    }
                                    int i8 = i6 - npcFindAuthoritativeWorldNpcByAuthId.f3307x;
                                    int iAbs = Math.abs(i8);
                                    if (iAbs > 96 || iAbs <= 2 || (iRound2 = Math.round(i8 * 0.4f)) == 0) {
                                        npcFindAuthoritativeWorldNpcByAuthId.f3307x = i6;
                                    } else {
                                        npcFindAuthoritativeWorldNpcByAuthId.f3307x += iRound2;
                                    }
                                    int i9 = i7 - npcFindAuthoritativeWorldNpcByAuthId.f3308y;
                                    int iAbs2 = Math.abs(i9);
                                    if (iAbs2 > 96 || iAbs2 <= 2 || (iRound = Math.round(i9 * 0.4f)) == 0) {
                                        npcFindAuthoritativeWorldNpcByAuthId.f3308y = i7;
                                    } else {
                                        npcFindAuthoritativeWorldNpcByAuthId.f3308y += iRound;
                                    }
                                    MapActor.ActorState actorStateResolveActorState = resolveActorState(strArrSplit[i4 + 8]);
                                    MapActor.Facing facingResolveFacing = resolveFacing(strArrSplit[i4 + 9]);
                                    int i10 = Integer.parseInt(strArrSplit[i4 + 10]);
                                    npcFindAuthoritativeWorldNpcByAuthId.visibleToPlayer = Boolean.TRUE;
                                    if (actorStateResolveActorState != null && npcFindAuthoritativeWorldNpcByAuthId.d0() != actorStateResolveActorState) {
                                        npcFindAuthoritativeWorldNpcByAuthId.q0(actorStateResolveActorState);
                                        if (actorStateResolveActorState == MapActor.ActorState.f3289e) {
                                            removeNpcFromLevel(npcFindAuthoritativeWorldNpcByAuthId);
                                            String strResolveWorldNpcAuthId = resolveWorldNpcAuthId(npcFindAuthoritativeWorldNpcByAuthId);
                                            if (strResolveWorldNpcAuthId != null && (linkedHashMap = worldNpcActors) != null) {
                                                linkedHashMap.remove(strResolveWorldNpcAuthId);
                                            }
                                            i3++;
                                            i4 += 11;
                                        }
                                    }
                                    if (facingResolveFacing != null && npcFindAuthoritativeWorldNpcByAuthId.facing != facingResolveFacing) {
                                        npcFindAuthoritativeWorldNpcByAuthId.facing = facingResolveFacing;
                                    }
                                    if (!npcFindAuthoritativeWorldNpcByAuthId.M1()) {
                                        npcFindAuthoritativeWorldNpcByAuthId.ai_disabled = true;
                                        CharacterSheet characterSheet = npcFindAuthoritativeWorldNpcByAuthId.sheet;
                                        if (characterSheet != null && (iZ = characterSheet.z()) > 0 && (characterStats = characterSheet.stats) != null) {
                                            int i11 = (iZ * (100 - i10)) / 100;
                                            if (i11 < 0) {
                                                i11 = 0;
                                            }
                                            characterStats.missingHP = i11;
                                        }
                                    }
                                }
                            }
                            i3++;
                            i4 += 11;
                        }
                        removeAbsentAuthoritativeWorldNpcs(hashSet);
                        return;
                    }
                    return;
                }
            } catch (Exception e2) {
                return;
            }
        }
        clearWorldNpcActors();
    }

    private static void applySpriteIndexCsv(Character character, String str) {
        if (character == null || str == null) {
            return;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return;
        }
        a<Integer> aVar = character.spriteIndex;
        if (aVar == null) {
            character.spriteIndex = new a<>();
        } else {
            aVar.clear();
        }
        for (String str2 : strTrim.split(",")) {
            if (str2 != null) {
                String strTrim2 = str2.trim();
                if (!strTrim2.isEmpty()) {
                    try {
                        int i2 = Integer.parseInt(strTrim2);
                        if (i2 >= 0) {
                            character.spriteIndex.a(Integer.valueOf(i2));
                        }
                    } catch (Exception e2) {
                    }
                }
            }
        }
    }

    private static boolean asBoolean(Object obj) {
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : (obj instanceof Number) && ((Number) obj).intValue() != 0;
    }

    public static String asString(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    private static void bindNewPeerSummons(HashSet hashSet, NPC npc) {
        ArrayList<MapActor> arrayListE;
        String str;
        if (npc == null || (arrayListE = GameLevel.e()) == null) {
            return;
        }
        int iQ = npc.q();
        boolean z2 = false;
        for (MapActor mapActor : arrayListE) {
            if (mapActor instanceof NPC) {
                NPC npc2 = (NPC) mapActor;
                Integer numValueOf = Integer.valueOf(npc2.q());
                if (hashSet == null || !hashSet.contains(numValueOf)) {
                    if (npc2.summoned || ((str = npc2.ai_type) != null && "companion".equals(str))) {
                        if (Math.abs(npc2.f3307x - npc.f3307x) <= 256 && Math.abs(npc2.f3308y - npc.f3308y) <= 256) {
                            if (!z2) {
                                removePeerSummonsForOwner(iQ);
                                z2 = true;
                            }
                            LinkedHashMap linkedHashMap = peerSummonOwners;
                            if (linkedHashMap != null) {
                                linkedHashMap.put(numValueOf, Integer.valueOf(iQ));
                                npc2.visibleToPlayer = Boolean.TRUE;
                            }
                        }
                    }
                }
            }
        }
    }

    private static String buildAnimationKey(String str, String str2) {
        return asString(str) + "|" + asString(str2);
    }

    private static String buildAnimationKey(String str, String str2, int i2) {
        return buildAnimationKey(str, str2) + "|" + i2;
    }

    private static String buildCombatEffectsSnapshot(Object obj) {
        if (obj == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        appendSnapshotField(sb, obj, "shielded");
        appendSnapshotField(sb, obj, "holy_shielded");
        appendSnapshotField(sb, obj, "shieldBonus");
        appendSnapshotField(sb, obj, "holy_shieldBonus");
        appendSnapshotField(sb, obj, "mageArmor_Charges");
        appendSnapshotField(sb, obj, "mageArmorBonus");
        appendSnapshotField(sb, obj, "might");
        appendSnapshotField(sb, obj, "might_arbenos");
        appendSnapshotField(sb, obj, "might_prayer");
        appendSnapshotField(sb, obj, "mightBonus");
        appendSnapshotField(sb, obj, "mightBonus_arbenos");
        appendSnapshotField(sb, obj, "mightBonus_prayer");
        appendSnapshotField(sb, obj, "bloodlust");
        appendSnapshotField(sb, obj, "bloodlustBonus");
        appendSnapshotField(sb, obj, "bloodlustArmorBonus");
        appendSnapshotField(sb, obj, "fury");
        appendSnapshotField(sb, obj, "furyMultiplier");
        appendSnapshotField(sb, obj, "rage");
        appendSnapshotField(sb, obj, "rageStrBonus");
        appendSnapshotField(sb, obj, "rageArmorBonus");
        appendSnapshotField(sb, obj, "stab");
        appendSnapshotField(sb, obj, "stabBonus");
        appendSnapshotField(sb, obj, "disintegrate");
        appendSnapshotField(sb, obj, "disintegrateBonus");
        appendSnapshotField(sb, obj, "duel");
        appendSnapshotField(sb, obj, "duelbonus");
        appendSnapshotField(sb, obj, "poison");
        appendSnapshotField(sb, obj, "poisonBonus");
        appendSnapshotField(sb, obj, "rapid_fire");
        appendSnapshotField(sb, obj, "flurry");
        appendSnapshotField(sb, obj, "flameAura");
        appendSnapshotField(sb, obj, "evasion");
        appendSnapshotField(sb, obj, "stealth");
        appendSnapshotField(sb, obj, "slowed");
        appendSnapshotField(sb, obj, "stunned");
        return sb.toString();
    }

    private static String buildEntityCacheKey(String str, String str2) {
        return asString(str) + "\t" + asString(str2);
    }

    private static String buildFollowerIdentityKey(String str, String str2, String str3) {
        return asString(str) + "\t" + asString(str2) + "\t" + asString(str3);
    }

    private static String buildPeerFollowerKey(String str, LanSessionManager.FollowerState followerState) {
        return followerState == null ? "" : asString(str) + "\t" + asString(followerState.spawnId) + "\t" + asString(followerState.tag) + "\t" + asString(followerState.name);
    }

    private static String buildPeerVisualKey(LanSessionManager.PlayerState playerState) {
        return playerState == null ? "" : buildVisualKey(buildPeerVisualSignature(playerState), playerState.spriteName);
    }

    private static String buildPeerVisualSignature(LanSessionManager.PlayerState playerState) {
        return playerState == null ? "" : normalizeGenderName(playerState.genderName) + "|" + playerState.portraitIndex + "|" + asString(playerState.classEnumName) + "|" + asString(playerState.raceName) + "|" + playerState.slotBodyItemId + "|" + playerState.slotFeetItemId + "|" + playerState.slotHandsItemId + "|" + playerState.slotHeadItemId + "|" + playerState.slotLegsItemId + "|" + playerState.slotMainhandItemId + "|" + playerState.slotOffhandItemId + "|" + asString(playerState.spriteIndexCsv);
    }

    private static String buildVisualKey(String str, String str2) {
        return asString(str) + "|" + asString(str2);
    }

    private static String buildWorldNpcFallbackAuthId(String str, int i2) {
        return asString(str) + "|" + i2;
    }

    private static void captureAndPublishWorldNpcs(LanSessionManager lanSessionManager, String str) {
        int iZ;
        CharacterStats characterStats;
        if (str != null) {
            try {
                ArrayList<NPC> arrayList = new ArrayList();
                ArrayList<MapActor> arrayListE = GameLevel.e();
                if (arrayListE != null) {
                    for (MapActor mapActor : arrayListE) {
                        if (mapActor instanceof NPC) {
                            NPC npc = (NPC) mapActor;
                            if (isAuthoritativeWorldNpc(npc) && !resolveWorldNpcAuthId(npc).isEmpty()) {
                                arrayList.add(npc);
                            }
                        }
                    }
                }
                StringBuilder sbAppend = new StringBuilder().append(str).append("\t").append(arrayList.size());
                for (NPC npc2 : arrayList) {
                    String strResolveWorldNpcAuthId = resolveWorldNpcAuthId(npc2);
                    if (!strResolveWorldNpcAuthId.isEmpty()) {
                        int iL1 = npc2.L1();
                        StringBuilder sbAppend2 = sbAppend.append("\t").append(strResolveWorldNpcAuthId).append("\t").append(asString(npc2.spawn_id)).append("\t").append(iL1).append("\t").append(asString(getWorldNpcSpawnKey(iL1))).append("\t").append(asString(npc2.unique_tag)).append("\t").append(asString(npc2.tag)).append("\t").append(npc2.f3307x).append("\t").append(npc2.f3308y).append("\t").append(npc2.d0().name()).append("\t");
                        MapActor.Facing facing = npc2.facing;
                        StringBuilder sbAppend3 = sbAppend2.append(facing == null ? "SOUTH" : facing.name()).append("\t");
                        CharacterSheet characterSheet = npc2.sheet;
                        int i2 = 100;
                        if (characterSheet != null && (iZ = characterSheet.z()) > 0 && (characterStats = characterSheet.stats) != null) {
                            i2 = ((iZ - characterStats.missingHP) * 100) / iZ;
                            if (i2 < 0) {
                                i2 = 0;
                            }
                            if (i2 > 100) {
                                i2 = 100;
                            }
                        }
                        sbAppend3.append(i2);
                    }
                }
                lanSessionManager.broadcastNpcState(sbAppend.toString());
            } catch (Exception e2) {
            }
        }
    }

    private static void captureAndPublishWorldNpcsToHost(LanSessionManager lanSessionManager, String str) {
        if (lanSessionManager == null || str == null) {
            return;
        }
        captureAndPublishWorldNpcs(lanSessionManager, str);
    }

    private static String captureGenderName(Object obj) {
        return normalizeGenderName(enumName(obj));
    }

    private static void captureLocalCompanionState(LanSessionManager.PlayerState playerState) {
        GameData gameDataV;
        Party party;
        String str;
        String str2;
        if (playerState == null || (gameDataV = GameData.v()) == null || (party = gameDataV.party) == null) {
            return;
        }
        String strG = party.g();
        if (strG != null) {
            String strTrim = strG.trim();
            if (!strTrim.isEmpty()) {
                NPC npcF = party.f();
                if (npcF == null || (str2 = npcF.spawn_id) == null || str2.trim().isEmpty()) {
                    LanSessionManager.FollowerState followerState = localCompanionSnapshot;
                    if (followerState == null || (str = followerState.spawnId) == null || !strTrim.equals(str)) {
                        localCompanionSnapshot = null;
                        return;
                    } else {
                        fillCompanionStateFromSnapshot(playerState, copyFollowerState(followerState));
                        return;
                    }
                }
                playerState.companionSpawnId = str2;
                String str3 = npcF.tag;
                if (str3 != null) {
                    playerState.companionTag = str3;
                }
                String name = npcF.getName();
                if (name != null) {
                    playerState.companionName = name;
                }
                playerState.companionX = npcF.f3307x;
                playerState.companionY = npcF.f3308y;
                ensureVisualSnapshot(npcF);
                playerState.companionSpriteName = joinStrings(npcF.animationSetName);
                playerState.companionSpriteIndexCsv = captureSpriteIndexCsv(npcF);
                playerState.companionFacingName = enumName(npcF.facing);
                playerState.companionActorStateName = enumName(npcF.d0());
                playerState.companionStateTimeMs = (int) (npcF.stateRelativeTime * 1000.0f);
                LanSessionManager.FollowerState followerState2 = new LanSessionManager.FollowerState();
                followerState2.spawnId = str2;
                followerState2.tag = playerState.companionTag;
                followerState2.name = playerState.companionName;
                followerState2.f3653x = playerState.companionX;
                followerState2.f3654y = playerState.companionY;
                followerState2.spriteIndexCsv = playerState.companionSpriteIndexCsv;
                followerState2.spriteName = playerState.companionSpriteName;
                followerState2.facingName = playerState.companionFacingName;
                followerState2.actorStateName = playerState.companionActorStateName;
                followerState2.stateTimeMs = playerState.companionStateTimeMs;
                localCompanionSnapshot = copyFollowerState(followerState2);
                return;
            }
        }
        localCompanionSnapshot = null;
    }

    private static void captureLocalFollowerStates(LanSessionManager.PlayerState playerState) {
        GameData gameDataV;
        Party party;
        ArrayList<Follower> arrayList;
        String str;
        NPCSerializedSpawnData nPCSerializedSpawnDataA;
        String str2;
        LanSessionManager.FollowerState followerStateCopyFollowerState;
        if (playerState == null || (gameDataV = GameData.v()) == null || (party = gameDataV.party) == null || (arrayList = party.followers) == null) {
            return;
        }
        ArrayList<LanSessionManager.FollowerState> arrayList2 = playerState.followers;
        if (arrayList2 != null) {
            arrayList2.clear();
        } else {
            playerState.followers = new ArrayList<>();
        }
        HashSet hashSet = new HashSet();
        for (Follower follower : arrayList) {
            if (follower != null && (str = follower.spawn_id) != null && !str.trim().isEmpty() && (nPCSerializedSpawnDataA = follower.a()) != null && (str2 = nPCSerializedSpawnDataA.tag) != null && !str2.trim().isEmpty() && !str2.contains("summon")) {
                String str3 = nPCSerializedSpawnDataA.name;
                if (str3 == null) {
                    str3 = "";
                }
                String strBuildFollowerIdentityKey = buildFollowerIdentityKey(str, str2, str3);
                hashSet.add(strBuildFollowerIdentityKey);
                NPC npcJ = GameLevel.j(str2);
                if (npcJ != null) {
                    LanSessionManager.FollowerState followerState = new LanSessionManager.FollowerState();
                    followerState.spawnId = str;
                    followerState.tag = str2;
                    String name = npcJ.getName();
                    if (name == null || name.trim().isEmpty()) {
                        followerState.name = str3;
                    } else {
                        followerState.name = name;
                    }
                    followerState.f3653x = npcJ.f3307x;
                    followerState.f3654y = npcJ.f3308y;
                    ensureVisualSnapshot(npcJ);
                    followerState.spriteName = joinStrings(npcJ.animationSetName);
                    followerState.spriteIndexCsv = captureSpriteIndexCsv(npcJ);
                    followerState.facingName = enumName(npcJ.facing);
                    followerState.actorStateName = enumName(npcJ.d0());
                    followerState.stateTimeMs = (int) (npcJ.stateRelativeTime * 1000.0f);
                    String strBuildFollowerIdentityKey2 = buildFollowerIdentityKey(str, str2, str3);
                    hashSet.add(strBuildFollowerIdentityKey2);
                    String strBuildFollowerIdentityKey3 = buildFollowerIdentityKey(str, str2, followerState.name);
                    hashSet.add(strBuildFollowerIdentityKey3);
                    LinkedHashMap linkedHashMap = localFollowerSnapshots;
                    linkedHashMap.put(strBuildFollowerIdentityKey2, copyFollowerState(followerState));
                    linkedHashMap.put(strBuildFollowerIdentityKey3, copyFollowerState(followerState));
                    playerState.followers.add(followerState);
                } else {
                    LanSessionManager.FollowerState followerState2 = (LanSessionManager.FollowerState) localFollowerSnapshots.get(strBuildFollowerIdentityKey);
                    if (followerState2 != null && (followerStateCopyFollowerState = copyFollowerState(followerState2)) != null) {
                        hashSet.add(buildFollowerIdentityKey(str, str2, followerStateCopyFollowerState.name));
                        playerState.followers.add(followerStateCopyFollowerState);
                    }
                }
            }
        }
        Iterator it = localFollowerSnapshots.entrySet().iterator();
        while (it.hasNext()) {
            if (!hashSet.contains(((Map.Entry) it.next()).getKey())) {
                it.remove();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0041 A[Catch: Exception -> 0x02ce, TryCatch #0 {Exception -> 0x02ce, blocks: (B:3:0x0003, B:6:0x000e, B:9:0x0017, B:11:0x002e, B:13:0x0034, B:15:0x003e, B:17:0x0043, B:19:0x005f, B:22:0x006d, B:24:0x0071, B:27:0x007f, B:29:0x00b7, B:30:0x00b9, B:32:0x0114, B:33:0x014c, B:35:0x015a, B:36:0x016c, B:38:0x01f2, B:40:0x01f6, B:42:0x01fc, B:45:0x0206, B:47:0x0218, B:49:0x022e, B:59:0x027a, B:61:0x0280, B:63:0x0288, B:66:0x028d, B:67:0x0297, B:69:0x029f, B:71:0x02a3, B:73:0x02a7, B:75:0x02ae, B:77:0x02bf, B:74:0x02aa, B:52:0x0267, B:54:0x026b, B:48:0x0229, B:43:0x01ff, B:44:0x0204, B:26:0x007b, B:21:0x0069, B:16:0x0041), top: B:84:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static LanSessionManager.PlayerState captureLocalState() {
        Object fieldValue;
        boolean z2;
        String str;
        int i2;
        String localPlayerName;
        try {
            Object objInvokeStatic = invokeStatic("net.fdgames.GameWorld.GameData", "v");
            if (objInvokeStatic == null || (fieldValue = getFieldValue(objInvokeStatic, "player")) == null) {
                return null;
            }
            ensureVisualSnapshot(fieldValue);
            LanSessionManager.PlayerState playerState = new LanSessionManager.PlayerState();
            String strInvokeString = invokeString(fieldValue, "getName");
            playerState.characterName = strInvokeString;
            LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
            if (instanceIfReady == null || (localPlayerName = instanceIfReady.getLocalPlayerName()) == null) {
                playerState.playerName = strInvokeString;
            } else {
                String strTrim = localPlayerName.trim();
                if (!strTrim.isEmpty()) {
                    playerState.playerName = strTrim;
                }
            }
            playerState.currentLevelId = asString(getFieldValue(objInvokeStatic, "CurrentLevel"));
            playerState.currentMapName = asString(getFieldValue(objInvokeStatic, "currentMapName"));
            String str2 = playerState.currentLevelId;
            if (str2 == null || str2.trim().isEmpty()) {
                playerState.currentLevelId = playerState.currentMapName;
            }
            String str3 = playerState.currentMapName;
            if (str3 == null || str3.trim().isEmpty()) {
                playerState.currentMapName = playerState.currentLevelId;
            }
            playerState.slot = getIntField(objInvokeStatic, "slot");
            playerState.f3655x = getIntField(fieldValue, "x");
            playerState.f3656y = getIntField(fieldValue, "y");
            playerState.speedX = getFloatField(fieldValue, "speedX");
            playerState.speedY = getFloatField(fieldValue, "speedY");
            playerState.sampleTimeMs = System.currentTimeMillis();
            int intField = getIntField(fieldValue, "gold");
            playerState.gold = intField;
            if (intField > 0) {
                localGoldBackup = intField;
            }
            playerState.portraitIndex = getIntField(fieldValue, "portraitIndex");
            playerState.genderName = captureGenderName(getFieldValue(fieldValue, "gender"));
            playerState.facingName = enumName(getFieldValue(fieldValue, "facing"));
            playerState.actorStateName = enumName(invokeObject(fieldValue, "d0"));
            playerState.stateTimeMs = (int) (getFloatField(fieldValue, "stateRelativeTime") * 1000.0f);
            playerState.spriteName = joinStrings(getFieldValue(fieldValue, "animationSetName"));
            ensureVisualSnapshot(fieldValue);
            playerState.spriteIndexCsv = captureSpriteIndexCsv(fieldValue);
            Object fieldValue2 = getFieldValue(fieldValue, "sheet");
            Object fieldValue3 = getFieldValue(fieldValue2, "inventory");
            if (fieldValue3 != null) {
                playerState.slotBodyItemId = getIntField(fieldValue3, "slot_body");
                playerState.slotFeetItemId = getIntField(fieldValue3, "slot_feet");
                playerState.slotHandsItemId = getIntField(fieldValue3, "slot_hands");
                playerState.slotHeadItemId = getIntField(fieldValue3, "slot_head");
                playerState.slotLegsItemId = getIntField(fieldValue3, "slot_legs");
                playerState.slotMainhandItemId = getIntField(fieldValue3, "slot_mainhand");
                playerState.slotOffhandItemId = getIntField(fieldValue3, "slot_offhand");
            }
            playerState.visualFxMask = computeVisualFxMask(fieldValue2);
            Object fieldValue4 = getFieldValue(fieldValue2, "skillSet");
            if (fieldValue4 != null) {
                playerState.skillSnapshot = invokeString(fieldValue4, "toString");
                playerState.stealthSkillLevel = invokeInt(fieldValue4, "g", "stealth");
            }
            playerState.combatEffectsSnapshot = buildCombatEffectsSnapshot(getFieldValue(fieldValue2, "effects"));
            Object fieldValue5 = getFieldValue(fieldValue2, "stats");
            playerState.missingHp = getIntField(fieldValue5, "missingHP");
            playerState.missingMana = getIntField(fieldValue5, "missingMana");
            playerState.level = invokeInt(fieldValue5, "f");
            Object fieldValue6 = getFieldValue(fieldValue5, "characterClass");
            playerState.classEnumName = enumName(fieldValue6);
            playerState.className = asString(fieldValue6);
            playerState.raceName = enumName(getFieldValue(fieldValue5, "characterRace"));
            Object fieldValue7 = getFieldValue(objInvokeStatic, "gameVariables");
            playerState.repVarsilia = invokeInt(fieldValue7, "b", "REP_varsilia");
            playerState.repMercia = invokeInt(fieldValue7, "b", "REP_mercia");
            playerState.repIlmara = invokeInt(fieldValue7, "b", "REP_ilmara");
            playerState.repWizards = invokeInt(fieldValue7, "b", "REP_wizardsguild");
            playerState.repThree = invokeInt(fieldValue7, "b", "REP_the_three");
            String strAsString = asString(getFieldValue(fieldValue, "spell_id"));
            int i3 = playerState.actionSeq;
            if (i3 != lastCapturedSpellSeq) {
                lastCapturedSpellSeq = i3;
                if (strAsString == null || strAsString.isEmpty()) {
                    strAsString = "";
                    lastCapturedSpellId = "";
                } else {
                    lastCapturedSpellId = strAsString;
                }
            } else {
                strAsString = lastCapturedSpellId;
            }
            playerState.spellId = strAsString;
            playerState.spellTarget = getIntField(fieldValue, "spellTarget");
            Object fieldValue8 = getFieldValue(fieldValue, "skillOrigin");
            if (fieldValue8 != null) {
                playerState.actionOriginX = getIntField(fieldValue8, "x");
                playerState.actionOriginY = getIntField(fieldValue8, "y");
            } else {
                playerState.actionOriginX = -1;
                playerState.actionOriginY = -1;
            }
            String str4 = playerState.actorStateName + "|" + playerState.spellId + "|" + playerState.actionOriginX + "|" + playerState.actionOriginY;
            if (playerState.actionOriginX >= 0) {
                z2 = true;
            } else {
                String str5 = playerState.spellId;
                z2 = (str5 == null || str5.trim().isEmpty()) ? false : true;
            }
            if (z2 && (i2 = playerState.stateTimeMs) <= 1200 && (!str4.equals(localLastActionSignature) || i2 < localLastActionStateTimeMs)) {
                localActionSeq++;
                localLastActionStateName = playerState.actorStateName;
            }
            playerState.actionSeq = localActionSeq;
            if (playerState.actionOriginX >= 0 && (str = localLastActionStateName) != null) {
                playerState.actorStateName = str;
            }
            if (z2) {
                localLastActionSignature = str4;
            } else {
                localLastActionSignature = "";
            }
            localLastActionStateTimeMs = playerState.stateTimeMs;
            captureLocalSummonState(playerState);
            captureLocalCompanionState(playerState);
            captureLocalFollowerStates(playerState);
            if (playerState.playerName != null) {
                if (!playerState.playerName.trim().isEmpty()) {
                    return playerState;
                }
            }
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    private static void captureLocalSummonState(LanSessionManager.PlayerState playerState) {
        GameData gameDataV;
        Party party;
        NPC npcI;
        String str;
        if (playerState == null || (gameDataV = GameData.v()) == null || (party = gameDataV.party) == null || (npcI = party.i()) == null || (str = npcI.spawn_id) == null) {
            return;
        }
        playerState.summonSpawnId = str;
        String str2 = npcI.tag;
        if (str2 != null) {
            playerState.summonTag = str2;
        }
        String name = npcI.getName();
        if (name != null) {
            playerState.summonName = name;
        }
        playerState.summonX = npcI.f3307x;
        playerState.summonY = npcI.f3308y;
        ensureVisualSnapshot(npcI);
        playerState.summonSpriteName = joinStrings(npcI.animationSetName);
        playerState.summonSpriteIndexCsv = captureSpriteIndexCsv(npcI);
        playerState.summonFacingName = enumName(npcI.facing);
        playerState.summonActorStateName = enumName(npcI.d0());
        playerState.summonStateTimeMs = (int) (npcI.stateRelativeTime * 1000.0f);
    }

    private static String captureSpriteIndexCsv(Object obj) {
        int intField;
        if (obj == null) {
            return "";
        }
        try {
            Object fieldValue = getFieldValue(obj, "spriteIndex");
            if (fieldValue == null || (intField = getIntField(fieldValue, "c")) <= 0) {
                return "";
            }
            Object fieldValue2 = getFieldValue(fieldValue, "b");
            if (!(fieldValue2 instanceof Object[])) {
                return "";
            }
            Object[] objArr = (Object[]) fieldValue2;
            StringBuilder sb = new StringBuilder();
            int length = objArr.length;
            for (int i2 = 0; i2 < intField && i2 < length; i2++) {
                Object obj2 = objArr[i2];
                if (obj2 instanceof Number) {
                    if (sb.length() > 0) {
                        sb.append(',');
                    }
                    sb.append(((Number) obj2).intValue());
                }
            }
            return sb.toString();
        } catch (Exception e2) {
            return "";
        }
    }

    private static String cityGroup(String str) {
        if (str == null) {
            return "";
        }
        Object staticFieldValue = getStaticFieldValue("i.LXkY.rFUF", "ZTiRQlaiRSeCND");
        String strValueOf = staticFieldValue != null ? String.valueOf(staticFieldValue) : "";
        return str.startsWith("NG") ? "NG" : str.startsWith("FT") ? "FT" : str.startsWith("NI") ? "NI" : (strValueOf.isEmpty() || !str.startsWith(strValueOf)) ? "" : strValueOf;
    }

    private static void clearAppliedState(String str) {
        if (str != null) {
            LinkedHashMap linkedHashMap = peerAppliedAnimationKeys;
            if (linkedHashMap != null) {
                linkedHashMap.remove(str);
            }
            LinkedHashMap linkedHashMap2 = peerAppliedSampleTimes;
            if (linkedHashMap2 != null) {
                linkedHashMap2.remove(str);
            }
            LinkedHashMap linkedHashMap3 = peerAppliedVisualKeys;
            if (linkedHashMap3 != null) {
                linkedHashMap3.remove(str);
            }
            LinkedHashMap linkedHashMap4 = peerAttackStateLocks;
            if (linkedHashMap4 != null) {
                linkedHashMap4.remove(str);
            }
            LinkedHashMap linkedHashMap5 = peerAttackStateNames;
            if (linkedHashMap5 != null) {
                linkedHashMap5.remove(str);
            }
            LinkedHashMap linkedHashMap6 = peerAttackFacingNames;
            if (linkedHashMap6 != null) {
                linkedHashMap6.remove(str);
            }
        }
    }

    private static void clearPeerActors() {
        Object objInvokeStatic;
        Object fieldValue;
        ArrayList<NPC> arrayList;
        clearWorldNpcActors();
        lastSyncedLevelId = "";
        LinkedHashMap linkedHashMap = peerActors;
        if (linkedHashMap == null) {
            return;
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            NPC npc = (NPC) ((Map.Entry) it.next()).getValue();
            if (npc != null) {
                GameLevelData.o().npcs.remove(npc);
                GameLevel.e().remove(npc);
            }
            it.remove();
        }
        LinkedHashMap linkedHashMap2 = peerActionSeqs;
        if (linkedHashMap2 != null) {
            linkedHashMap2.clear();
        }
        LinkedHashMap linkedHashMap3 = peerCombatSeqs;
        if (linkedHashMap3 != null) {
            linkedHashMap3.clear();
        }
        LinkedHashMap linkedHashMap4 = peerMotionStates;
        if (linkedHashMap4 != null) {
            linkedHashMap4.clear();
        }
        LinkedHashMap linkedHashMap5 = peerAppliedAnimationKeys;
        if (linkedHashMap5 != null) {
            linkedHashMap5.clear();
        }
        LinkedHashMap linkedHashMap6 = peerAppliedSampleTimes;
        if (linkedHashMap6 != null) {
            linkedHashMap6.clear();
        }
        LinkedHashMap linkedHashMap7 = peerAppliedVisualKeys;
        if (linkedHashMap7 != null) {
            linkedHashMap7.clear();
        }
        LinkedHashMap linkedHashMap8 = peerAttackStateLocks;
        if (linkedHashMap8 != null) {
            linkedHashMap8.clear();
        }
        LinkedHashMap linkedHashMap9 = peerAttackStateNames;
        if (linkedHashMap9 != null) {
            linkedHashMap9.clear();
        }
        LinkedHashMap linkedHashMap10 = peerAttackFacingNames;
        if (linkedHashMap10 != null) {
            linkedHashMap10.clear();
        }
        LinkedHashMap linkedHashMap11 = peerVisualSignatures;
        if (linkedHashMap11 != null) {
            linkedHashMap11.clear();
        }
        LinkedHashMap linkedHashMap12 = peerActorOwners;
        if (linkedHashMap12 != null) {
            linkedHashMap12.clear();
        }
        LinkedHashMap linkedHashMap13 = pendingPeerDamageProcs;
        if (linkedHashMap13 != null) {
            linkedHashMap13.clear();
        }
        LinkedHashMap linkedHashMap14 = localFollowerSnapshots;
        if (linkedHashMap14 != null) {
            linkedHashMap14.clear();
        }
        localCompanionSnapshot = null;
        LinkedHashMap linkedHashMap15 = peerSummonActors;
        if (linkedHashMap15 != null) {
            Iterator it2 = linkedHashMap15.entrySet().iterator();
            while (it2.hasNext()) {
                NPC npc2 = (NPC) ((Map.Entry) it2.next()).getValue();
                if (npc2 != null) {
                    GameLevelData.o().npcs.remove(npc2);
                    GameLevel.e().remove(npc2);
                }
                it2.remove();
            }
        }
        LinkedHashMap linkedHashMap16 = peerCompanionActors;
        if (linkedHashMap16 != null) {
            Iterator it3 = linkedHashMap16.entrySet().iterator();
            while (it3.hasNext()) {
                NPC npc3 = (NPC) ((Map.Entry) it3.next()).getValue();
                if (npc3 != null) {
                    GameLevelData.o().npcs.remove(npc3);
                    GameLevel.e().remove(npc3);
                }
                it3.remove();
            }
        }
        LinkedHashMap linkedHashMap17 = peerFollowerActors;
        if (linkedHashMap17 != null) {
            Iterator it4 = linkedHashMap17.entrySet().iterator();
            while (it4.hasNext()) {
                NPC npc4 = (NPC) ((Map.Entry) it4.next()).getValue();
                if (npc4 != null) {
                    GameLevelData.o().npcs.remove(npc4);
                    GameLevel.e().remove(npc4);
                }
                it4.remove();
            }
        }
        LinkedHashMap linkedHashMap18 = peerSummonOwners;
        if (linkedHashMap18 != null) {
            Iterator it5 = linkedHashMap18.entrySet().iterator();
            while (it5.hasNext()) {
                Integer num = (Integer) ((Map.Entry) it5.next()).getKey();
                if (num != null) {
                    MapActor mapActorG = GameLevel.g(num.intValue());
                    if (mapActorG instanceof NPC) {
                        NPC npc5 = (NPC) mapActorG;
                        GameLevelData.o().npcs.remove(npc5);
                        GameLevel.e().remove(npc5);
                    }
                }
            }
            peerSummonOwners.clear();
        }
        GameLevelData gameLevelDataO = GameLevelData.o();
        if (gameLevelDataO != null && (arrayList = gameLevelDataO.npcs) != null) {
            Iterator<NPC> it6 = arrayList.iterator();
            while (it6.hasNext()) {
                NPC next = it6.next();
                if (next.lanPeerVisual) {
                    it6.remove();
                    GameLevel.e().remove(next);
                }
            }
        }
        int i2 = localGoldBackup;
        if (i2 <= 0 || (objInvokeStatic = invokeStatic("net.fdgames.GameWorld.GameData", "v")) == null || (fieldValue = getFieldValue(objInvokeStatic, "player")) == null) {
            return;
        }
        setIntField(fieldValue, "gold", i2);
        localGoldBackup = 0;
    }

    private static void clearWorldNpcActors() {
        LinkedHashMap linkedHashMap = worldNpcActors;
        if (linkedHashMap != null) {
            for (NPC npc : linkedHashMap.values()) {
                if (npc != null) {
                    npc.ai_disabled = false;
                }
            }
            linkedHashMap.clear();
        }
        lastAppliedWorldNpcLevelId = null;
    }

    private static HashSet collectActorIds() {
        HashSet hashSet = new HashSet();
        ArrayList<MapActor> arrayListE = GameLevel.e();
        if (arrayListE != null) {
            for (Object obj : new ArrayList(arrayListE)) {
                if (obj instanceof GameObject) {
                    hashSet.add(Integer.valueOf(((GameObject) obj).q()));
                }
            }
        }
        return hashSet;
    }

    private static float computeStateRelativeTime(int i2, long j2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (j2 <= 0) {
            j2 = jCurrentTimeMillis;
        }
        long j3 = jCurrentTimeMillis - j2;
        if (j3 < 0) {
            j3 = 0;
        }
        if (j3 > 400) {
            j3 = 400;
        }
        return (i2 + ((int) j3)) / 1000.0f;
    }

    private static int computeVisualFxMask(Object obj) {
        Object fieldValue;
        if (obj == null || (fieldValue = getFieldValue(obj, "effects")) == null) {
            return 0;
        }
        int i2 = (getBooleanField(fieldValue, "holy_shielded") || (getBooleanField(fieldValue, "shielded") && getIntField(fieldValue, "shieldBonus") > 0) || getIntField(fieldValue, "mageArmor_Charges") > 0) ? 0 | 1 : 0;
        if (getBooleanField(fieldValue, "stab") || getBooleanField(fieldValue, "fury") || getBooleanField(fieldValue, "might") || getBooleanField(fieldValue, "rapid_fire") || getBooleanField(fieldValue, "disintegrate") || getBooleanField(fieldValue, "duel") || getBooleanField(fieldValue, "flurry") || getBooleanField(fieldValue, "rage") || getBooleanField(fieldValue, "might_arbenos") || getBooleanField(fieldValue, "might_prayer")) {
            i2 |= 2;
        }
        if (getBooleanField(fieldValue, "bloodlust")) {
            i2 |= 4;
        }
        if (getBooleanField(fieldValue, "evasion")) {
            i2 |= 8;
        }
        Object fieldValue2 = getFieldValue(fieldValue, "resistances");
        if (fieldValue2 != null) {
            if (asBoolean(invokeObject(fieldValue2, "d"))) {
                i2 |= 16;
            }
            if (asBoolean(invokeObject(fieldValue2, "c"))) {
                i2 |= 64;
            }
        }
        if (getBooleanField(fieldValue, "stealth")) {
            i2 |= 32;
        }
        if (getBooleanField(fieldValue, "slowed")) {
            i2 |= 64;
        }
        return getBooleanField(fieldValue, "stunned") ? i2 | VertexAttributes.Usage.Tangent : i2;
    }

    private static String consumeAppliedPeerProcCsv(Character character) {
        LinkedHashMap linkedHashMap;
        String str;
        return (character == null || (linkedHashMap = pendingPeerDamageProcs) == null || (str = (String) linkedHashMap.remove(Integer.valueOf(character.q()))) == null) ? "" : str;
    }

    private static LanSessionManager.FollowerState copyFollowerState(LanSessionManager.FollowerState followerState) {
        if (followerState == null) {
            return null;
        }
        LanSessionManager.FollowerState followerState2 = new LanSessionManager.FollowerState();
        followerState2.spawnId = followerState.spawnId;
        followerState2.tag = followerState.tag;
        followerState2.name = followerState.name;
        followerState2.f3653x = followerState.f3653x;
        followerState2.f3654y = followerState.f3654y;
        followerState2.spriteIndexCsv = followerState.spriteIndexCsv;
        followerState2.spriteName = followerState.spriteName;
        followerState2.facingName = followerState.facingName;
        followerState2.actorStateName = followerState.actorStateName;
        followerState2.stateTimeMs = followerState.stateTimeMs;
        return followerState2;
    }

    private static NPC createPeerActor(LanSessionManager.PlayerState playerState) {
        String str;
        String str2;
        GameData gameDataV;
        GameVariables gameVariables;
        if (playerState != null && (str = playerState.playerName) != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                String displayName = getDisplayName(playerState);
                if (displayName != null && !displayName.trim().isEmpty()) {
                    strTrim = displayName;
                }
                String str3 = "varannari_warrior";
                String str4 = playerState.classEnumName;
                if ("ROGUE".equals(str4)) {
                    str3 = "human_archer";
                } else if ("CLERIC".equals(str4)) {
                    str3 = "varannari_druid";
                } else if ("WIZARD".equals(str4)) {
                    str3 = "janod";
                }
                Spawn spawnI = Rules.i(str3);
                if (spawnI == null && (spawnI = Rules.i("varannari_warrior")) == null) {
                    return null;
                }
                Spawn spawn = new Spawn(spawnI);
                spawn.b(strTrim);
                spawn.conversation_ID = "";
                spawn.lootTable = "";
                GameData gameDataV2 = GameData.v();
                if (gameDataV2 == null || (str2 = gameDataV2.CurrentLevel) == null || !str2.equals("H10_pvp_arena") || !((gameDataV = GameData.v()) == null || (gameVariables = gameDataV.gameVariables) == null || gameVariables.b("pvp_arena_won") >= 1)) {
                    spawn.faction = "player";
                    spawn.AI_type = "idle";
                } else {
                    spawn.faction = "enemy";
                    spawn.AI_type = "idle";
                }
                spawn.wander = 0;
                spawn.characterclass = resolveCharacterClass(playerState.classEnumName);
                spawn.race = resolveCharacterRace(playerState.raceName);
                spawn.gender = resolveGender(playerState.genderName);
                int i2 = playerState.portraitIndex;
                if (i2 >= 0) {
                    spawn.portrait = i2;
                }
                int i3 = playerState.slotMainhandItemId;
                if (i3 >= 0) {
                    spawn.weaponStats = resolveWeaponStatsFromItemId(i3);
                }
                int i4 = playerState.level;
                if (i4 < 1) {
                    i4 = 1;
                }
                spawn.minlevel = i4;
                spawn.maxlevel = i4;
                NPC npc = new NPC(spawn);
                npc.lanPeerVisual = true;
                refreshPeerVisualFromSnapshot(npc, playerState);
                applyPeerVisualFxMask(npc, playerState);
                applyPeerCombatSnapshot(npc, playerState);
                npc.r1(strTrim);
                npc.f3307x = playerState.f3655x;
                npc.f3308y = playerState.f3656y;
                npc.facing = resolveFacing(playerState.facingName);
                npc.q0(resolveActorState(playerState.actorStateName));
                refreshPeerVisualFromSnapshot(npc, playerState);
                npc.stateRelativeTime = playerState.stateTimeMs / 1000.0f;
                GameLevel.a(npc);
                npc.B1();
                GameLevel.e().add(npc);
                trackPeerActorOwner(playerState.playerName, npc);
                return npc;
            }
        }
        return null;
    }

    private static NPC createPeerCompanion(LanSessionManager.PlayerState playerState) {
        String str;
        Spawn spawnI;
        if (playerState == null || (str = playerState.companionSpawnId) == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty() || (spawnI = Rules.i(strTrim)) == null) {
            return null;
        }
        if (playerState.companionX == 0 && playerState.companionY == 0) {
            return null;
        }
        Spawn spawn = new Spawn(spawnI);
        String str2 = playerState.companionName;
        if (str2 != null) {
            String strTrim2 = str2.trim();
            if (!strTrim2.isEmpty()) {
                spawn.b(strTrim2);
            }
        }
        spawn.conversation_ID = "";
        spawn.lootTable = "";
        spawn.faction = "player";
        spawn.AI_type = "idle";
        spawn.wander = 0;
        String str3 = playerState.companionSpriteName;
        if (str3 != null) {
            String strTrim3 = str3.trim();
            if (!strTrim3.isEmpty()) {
                spawn.spriteName = strTrim3;
            }
        }
        NPC npc = new NPC(spawn);
        String str4 = playerState.companionName;
        if (str4 != null && !str4.trim().isEmpty()) {
            npc.r1(str4);
        }
        npc.spawn_id = strTrim;
        npc.f3307x = playerState.companionX;
        npc.f3308y = playerState.companionY;
        String str5 = playerState.companionTag;
        if (str5 != null) {
            npc.tag = str5;
        }
        npc.facing = resolveFacing(playerState.companionFacingName);
        npc.q0(resolveActorState(playerState.companionActorStateName));
        npc.stateRelativeTime = playerState.companionStateTimeMs / 1000.0f;
        GameLevel.a(npc);
        npc.B1();
        GameLevel.e().add(npc);
        applyAnimationSet(npc, playerState.companionSpriteName);
        applySpriteIndexCsv(npc, playerState.companionSpriteIndexCsv);
        npc.ai_disabled = true;
        npc.summoned = true;
        npc.visibleToPlayer = Boolean.TRUE;
        return npc;
    }

    private static NPC createPeerFollower(LanSessionManager.FollowerState followerState) {
        String str;
        Spawn spawnI;
        if (followerState == null || (str = followerState.spawnId) == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty() || (spawnI = Rules.i(strTrim)) == null) {
            return null;
        }
        Spawn spawn = new Spawn(spawnI);
        String str2 = followerState.name;
        if (str2 != null) {
            String strTrim2 = str2.trim();
            if (!strTrim2.isEmpty()) {
                spawn.b(strTrim2);
            }
        }
        spawn.conversation_ID = "";
        spawn.lootTable = "";
        spawn.faction = "player";
        spawn.AI_type = "idle";
        spawn.wander = 0;
        String str3 = followerState.spriteName;
        if (str3 != null) {
            String strTrim3 = str3.trim();
            if (!strTrim3.isEmpty()) {
                spawn.spriteName = strTrim3;
            }
        }
        NPC npc = new NPC(spawn);
        String str4 = followerState.name;
        if (str4 != null && !str4.trim().isEmpty()) {
            npc.r1(str4);
        }
        npc.spawn_id = strTrim;
        npc.f3307x = followerState.f3653x;
        npc.f3308y = followerState.f3654y;
        String str5 = followerState.tag;
        if (str5 != null) {
            npc.tag = str5;
        }
        npc.facing = resolveFacing(followerState.facingName);
        npc.q0(resolveActorState(followerState.actorStateName));
        npc.stateRelativeTime = followerState.stateTimeMs / 1000.0f;
        GameLevel.a(npc);
        npc.B1();
        GameLevel.e().add(npc);
        applyAnimationSet(npc, followerState.spriteName);
        applySpriteIndexCsv(npc, followerState.spriteIndexCsv);
        npc.ai_disabled = true;
        npc.summoned = true;
        npc.visibleToPlayer = Boolean.TRUE;
        return npc;
    }

    private static NPC createPeerSummon(LanSessionManager.PlayerState playerState) {
        String str;
        Spawn spawnI;
        if (playerState == null || (str = playerState.summonSpawnId) == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty() || (spawnI = Rules.i(strTrim)) == null) {
            return null;
        }
        Spawn spawn = new Spawn(spawnI);
        String str2 = playerState.summonName;
        if (str2 != null) {
            String strTrim2 = str2.trim();
            if (!strTrim2.isEmpty()) {
                spawn.b(strTrim2);
            }
        }
        spawn.conversation_ID = "";
        spawn.lootTable = "";
        spawn.faction = "player";
        spawn.AI_type = "idle";
        spawn.wander = 0;
        String str3 = playerState.summonSpriteName;
        if (str3 != null) {
            String strTrim3 = str3.trim();
            if (!strTrim3.isEmpty()) {
                spawn.spriteName = strTrim3;
            }
        }
        NPC npc = new NPC(spawn);
        String str4 = playerState.summonName;
        if (str4 != null && !str4.trim().isEmpty()) {
            npc.r1(str4);
        }
        npc.spawn_id = strTrim;
        npc.f3307x = playerState.summonX;
        npc.f3308y = playerState.summonY;
        String str5 = playerState.summonTag;
        if (str5 != null) {
            npc.tag = str5;
        }
        npc.facing = resolveFacing(playerState.summonFacingName);
        npc.q0(resolveActorState(playerState.summonActorStateName));
        npc.stateRelativeTime = playerState.summonStateTimeMs / 1000.0f;
        GameLevel.a(npc);
        npc.B1();
        GameLevel.e().add(npc);
        applyAnimationSet(npc, playerState.summonSpriteName);
        applySpriteIndexCsv(npc, playerState.summonSpriteIndexCsv);
        npc.ai_disabled = true;
        npc.summoned = true;
        npc.visibleToPlayer = Boolean.TRUE;
        return npc;
    }

    private static NPC createWorldNpcClone(String str, int i2, String str2, String str3, int i3, int i4) {
        Spawn spawnI;
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty() || (spawnI = Rules.i(strTrim)) == null) {
            return null;
        }
        AnimationLoader.a(strTrim);
        NPC npc = new NPC(new Spawn(spawnI));
        if (str3 != null) {
            String strTrim2 = str3.trim();
            if (!strTrim2.isEmpty()) {
                npc.tag = strTrim2;
            }
        }
        if (str2 != null) {
            String strTrim3 = str2.trim();
            if (!strTrim3.isEmpty()) {
                npc.unique_tag = strTrim3;
            }
        }
        if (i2 <= 0 || GameLevel.h(i2) == null) {
            npc.U1(0);
        } else {
            npc.U1(i2);
        }
        npc.f3307x = i3;
        npc.f3308y = i4;
        npc.visibleToPlayer = Boolean.TRUE;
        npc.ai_disabled = true;
        GameLevel.a(npc);
        npc.B1();
        ArrayList<MapActor> arrayListE = GameLevel.e();
        if (arrayListE != null && !arrayListE.contains(npc)) {
            arrayListE.add(npc);
        }
        return npc;
    }

    private static String damageTypeCode(Damage.DamageType damageType) {
        return damageType != null ? damageType == Damage.DamageType.f3262c ? "f" : damageType == Damage.DamageType.f3263d ? "c" : damageType == Damage.DamageType.f3264e ? "s" : damageType == Damage.DamageType.f3265f ? "d" : damageType == Damage.DamageType.f3266g ? "t" : damageType == Damage.DamageType.f3267h ? "sp" : "n" : "n";
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static void drawPeerMapPins(Batch batch, Texture texture) {
        GameData gameDataV;
        String str;
        List<LanSessionManager.PlayerState> peerStatesSnapshot;
        BitmapFont bitmapFont;
        List<LanSessionManager.PlayerState> peerStatesSnapshot2;
        float f2;
        float f3;
        float f4;
        LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
        if (instanceIfReady != null && instanceIfReady.isSessionRunning() && (gameDataV = GameData.v()) != null && (str = gameDataV.CurrentLevel) != null && (peerStatesSnapshot = instanceIfReady.getPeerStatesSnapshot()) != null && (bitmapFont = GameAssets.f3546f0) != null) {
            int i2 = 0;
            for (LanSessionManager.PlayerState playerState : peerStatesSnapshot) {
                String str2 = playerState.currentLevelId;
                if (str2 != null && str2.equals(str)) {
                    switch (i2 % 6) {
                        case 0:
                        default:
                            f2 = 0.0f;
                            f3 = 0.9f;
                            f4 = 1.0f;
                            break;
                        case 1:
                            f2 = 0.2f;
                            f3 = 1.0f;
                            f4 = 0.2f;
                            break;
                        case 2:
                            f2 = 1.0f;
                            f3 = 0.5f;
                            f4 = 0.0f;
                            break;
                        case 3:
                            f2 = 1.0f;
                            f3 = 1.0f;
                            f4 = 0.0f;
                            break;
                        case 4:
                            f2 = 0.7f;
                            f3 = 0.2f;
                            f4 = 1.0f;
                            break;
                        case 5:
                            f2 = 1.0f;
                            f3 = 0.4f;
                            f4 = 0.7f;
                            break;
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis() - playerState.sampleTimeMs;
                    if (jCurrentTimeMillis > 200) {
                        jCurrentTimeMillis = 200;
                    }
                    float f5 = jCurrentTimeMillis / 1000.0f;
                    Coords coordsA = b.A(140, new Coords(Math.round(playerState.f3655x + (playerState.speedX * f5)), Math.round(playerState.f3656y + (playerState.speedY * f5))));
                    float f6 = coordsA.f3508x + 32;
                    float f7 = coordsA.f3509y;
                    batch.setColor(f2, f3, f4, 1.0f);
                    batch.draw(texture, f6, f7);
                    batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                    String str3 = playerState.playerName;
                    if (str3 != null && !str3.isEmpty()) {
                        bitmapFont.draw(batch, str3, f6 - 10.0f, f7 + 8.0f);
                    }
                }
                batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                i2++;
            }
            batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            LanSessionManager instanceIfReady2 = LanSessionManager.getInstanceIfReady();
            if (instanceIfReady2 != null && (peerStatesSnapshot2 = instanceIfReady2.getPeerStatesSnapshot()) != null) {
                float f8 = 160.0f;
                for (LanSessionManager.PlayerState playerState2 : peerStatesSnapshot2) {
                    String str4 = playerState2.currentLevelId;
                    if (str4 != null && !str4.equals(str)) {
                        String str5 = playerState2.playerName;
                        String str6 = playerState2.currentMapName;
                        StringBuilder sb = new StringBuilder();
                        if (str5 != null) {
                            sb = sb.append(str5);
                        }
                        StringBuilder sbAppend = sb.append(": ");
                        if (str6 != null) {
                            sbAppend = sbAppend.append(str6);
                        }
                        bitmapFont.draw(batch, sbAppend.toString(), 10.0f, f8);
                        f8 -= 16.0f;
                    }
                }
            }
        }
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private static void ensureActorSkillLevel(Character character, String str, int i2) {
        Object fieldValue;
        Object fieldValue2;
        if (character == null || str == null) {
            return;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty() || (fieldValue = getFieldValue(character, "sheet")) == null || (fieldValue2 = getFieldValue(fieldValue, "skillSet")) == null) {
            return;
        }
        invokeInt(fieldValue2, "q", strTrim);
        for (int i3 = 0; i3 < i2; i3++) {
            invokeInt(fieldValue2, "j", strTrim);
        }
    }

    private static void ensureVisualSnapshot(Object obj) {
        if (obj != null) {
            try {
                invokeObject(obj, "v0");
            } catch (Exception e2) {
            }
        }
    }

    private static String enumName(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            obj = obj.getClass().getMethod("name", new Class[0]).invoke(obj, new Object[0]);
            return String.valueOf(obj);
        } catch (Exception e2) {
            return asString(obj);
        }
    }

    private static void fillCompanionStateFromSnapshot(LanSessionManager.PlayerState playerState, LanSessionManager.FollowerState followerState) {
        if (playerState == null || followerState == null) {
            return;
        }
        playerState.companionSpawnId = followerState.spawnId;
        playerState.companionTag = followerState.tag;
        playerState.companionName = followerState.name;
        playerState.companionX = followerState.f3653x;
        playerState.companionY = followerState.f3654y;
        playerState.companionSpriteIndexCsv = followerState.spriteIndexCsv;
        playerState.companionSpriteName = followerState.spriteName;
        playerState.companionFacingName = followerState.facingName;
        playerState.companionActorStateName = followerState.actorStateName;
        playerState.companionStateTimeMs = followerState.stateTimeMs;
    }

    private static NPC findAuthoritativeWorldNpcByAuthId(String str) {
        ArrayList<NPC> arrayList;
        NPC npc;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return null;
            }
            LinkedHashMap linkedHashMap = worldNpcActors;
            if (linkedHashMap != null && (npc = (NPC) linkedHashMap.get(strTrim)) != null) {
                ArrayList<MapActor> arrayListE = GameLevel.e();
                if (arrayListE != null && arrayListE.contains(npc)) {
                    return npc;
                }
                linkedHashMap.remove(strTrim);
            }
            GameLevelData gameLevelDataO = GameLevelData.o();
            if (gameLevelDataO != null && (arrayList = gameLevelDataO.npcs) != null) {
                for (NPC npc2 : arrayList) {
                    if (isAuthoritativeWorldNpc(npc2) && strTrim.equals(resolveWorldNpcAuthId(npc2))) {
                        return npc2;
                    }
                }
            }
        }
        return null;
    }

    private static Field findField(Class<?> cls, String str) {
        while (cls != null) {
            try {
                return cls.getDeclaredField(str);
            } catch (NoSuchFieldException e2) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    private static NPC findPeerCompanionCandidate(NPC npc, LanSessionManager.PlayerState playerState) {
        String str;
        ArrayList<MapActor> arrayListE;
        NPC npc2;
        String str2;
        String str3;
        if (npc != null && playerState != null && (str = playerState.companionSpawnId) != null && (arrayListE = GameLevel.e()) != null) {
            String str4 = playerState.companionTag;
            for (MapActor mapActor : arrayListE) {
                if ((mapActor instanceof NPC) && (npc2 = (NPC) mapActor) != npc && !npc2.k0() && !isTrackedPeerExtraActor(npc2) && (str2 = npc2.spawn_id) != null && str.equals(str2) && (str4 == null || str4.trim().isEmpty() || ((str3 = npc2.tag) != null && str4.equals(str3)))) {
                    if (Math.abs(npc2.f3307x - npc.f3307x) <= 384 && Math.abs(npc2.f3308y - npc.f3308y) <= 384) {
                        return npc2;
                    }
                }
            }
        }
        return null;
    }

    private static NPC findPeerSummonCandidate(NPC npc, LanSessionManager.PlayerState playerState) {
        String str;
        ArrayList<MapActor> arrayListE;
        NPC npc2;
        String str2;
        String str3;
        if (npc != null && playerState != null && (str = playerState.summonSpawnId) != null && (arrayListE = GameLevel.e()) != null) {
            String str4 = playerState.summonTag;
            for (MapActor mapActor : arrayListE) {
                if ((mapActor instanceof NPC) && (npc2 = (NPC) mapActor) != npc && !npc2.k0() && !isTrackedPeerExtraActor(npc2) && (str2 = npc2.spawn_id) != null && str.equals(str2) && (str4 == null || str4.trim().isEmpty() || ((str3 = npc2.tag) != null && str4.equals(str3)))) {
                    if (Math.abs(npc2.f3307x - npc.f3307x) <= 384 && Math.abs(npc2.f3308y - npc.f3308y) <= 384) {
                        return npc2;
                    }
                }
            }
        }
        return null;
    }

    public static void forcePublishLocalState() {
        LanSessionManager.PlayerState playerStateCaptureLocalState;
        LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
        if (instanceIfReady == null || !instanceIfReady.isSessionRunning() || (playerStateCaptureLocalState = captureLocalState()) == null) {
            return;
        }
        instanceIfReady.publishLiveState(playerStateCaptureLocalState);
    }

    private static long getAppliedSampleTime(String str) {
        LinkedHashMap linkedHashMap;
        Long l2;
        if (str == null || (linkedHashMap = peerAppliedSampleTimes) == null || (l2 = (Long) linkedHashMap.get(str)) == null) {
            return -1L;
        }
        return l2.longValue();
    }

    private static boolean getBooleanField(Object obj, String str) {
        return asBoolean(getFieldValue(obj, str));
    }

    private static String getCurrentPlayerName() {
        Object fieldValue;
        String strInvokeString;
        String localPlayerName;
        LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
        if (instanceIfReady != null && (localPlayerName = instanceIfReady.getLocalPlayerName()) != null) {
            String strTrim = localPlayerName.trim();
            if (!strTrim.isEmpty()) {
                return strTrim;
            }
        }
        try {
            Object objInvokeStatic = invokeStatic("net.fdgames.GameWorld.GameData", "v");
            if (objInvokeStatic != null && (fieldValue = getFieldValue(objInvokeStatic, "player")) != null && (strInvokeString = invokeString(fieldValue, "getName")) != null) {
                String strTrim2 = strInvokeString.trim();
                if (!strTrim2.isEmpty()) {
                    return strTrim2;
                }
            }
            return "";
        } catch (Exception e2) {
            return "";
        }
    }

    private static String getDisplayName(LanSessionManager.PlayerState playerState) {
        if (playerState == null) {
            return "";
        }
        String str = playerState.characterName;
        if (str != null && !str.trim().isEmpty()) {
            return str;
        }
        String str2 = playerState.playerName;
        if (str2 != null) {
            String strTrim = str2.trim();
            if (!strTrim.isEmpty()) {
                return strTrim;
            }
        }
        return "";
    }

    public static Object getFieldValue(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        try {
            Field fieldFindField = findField(obj.getClass(), str);
            if (fieldFindField == null) {
                return null;
            }
            fieldFindField.setAccessible(true);
            return fieldFindField.get(obj);
        } catch (Exception e2) {
            return null;
        }
    }

    private static float getFloatField(Object obj, String str) {
        Object fieldValue = getFieldValue(obj, str);
        if (fieldValue instanceof Number) {
            return ((Number) fieldValue).floatValue();
        }
        return 0.0f;
    }

    private static int getIntField(Object obj, String str) {
        Object fieldValue = getFieldValue(obj, str);
        if (fieldValue instanceof Number) {
            return ((Number) fieldValue).intValue();
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static NPC getOrCreatePeerActor(String str, LanSessionManager.PlayerState playerState) {
        String str2;
        GameVariables gameVariables;
        NPC npc = null;
        if (str != null && playerState != null) {
            HashSet hashSet = peerDeadNames;
            if (hashSet != null) {
                hashSet.remove(str);
            }
            npc = null;
            String strBuildPeerVisualSignature = buildPeerVisualSignature(playerState);
            NPC npcCreatePeerActor = (NPC) peerActors.get(str);
            String str3 = (String) peerVisualSignatures.get(str);
            if (npcCreatePeerActor != null) {
                if (str3 != null && strBuildPeerVisualSignature.equals(str3)) {
                    peerVisualSignatures.put(str, strBuildPeerVisualSignature);
                    if (!GameLevelData.o().npcs.contains(npcCreatePeerActor)) {
                        GameLevel.a(npcCreatePeerActor);
                        npcCreatePeerActor.B1();
                        GameLevel.e().add(npcCreatePeerActor);
                    }
                    if (npcCreatePeerActor != null) {
                        trackPeerActorOwner(str, npcCreatePeerActor);
                        GameData gameDataV = GameData.v();
                        if (gameDataV != null && (str2 = gameDataV.CurrentLevel) != null && str2.equals("H10_pvp_arena")) {
                            GameData gameDataV2 = GameData.v();
                            if (gameDataV2 == null || (gameVariables = gameDataV2.gameVariables) == null || gameVariables.b("pvp_arena_won") >= 1) {
                                npcCreatePeerActor.worldfactions = WorldFactions.i("enemy");
                            } else {
                                npcCreatePeerActor.worldfactions = WorldFactions.i("player");
                            }
                        }
                    }
                    return npcCreatePeerActor;
                }
                removePeerSummonsForOwner(npcCreatePeerActor.q());
                GameLevelData.o().npcs.remove(npcCreatePeerActor);
                GameLevel.e().remove(npcCreatePeerActor);
                peerActors.remove(str);
                peerVisualSignatures.remove(str);
                clearAppliedState(buildEntityCacheKey("player", str));
                untrackPeerActorOwner(npcCreatePeerActor);
            }
            npcCreatePeerActor = createPeerActor(playerState);
            if (npcCreatePeerActor != null) {
                peerActors.put(str, npcCreatePeerActor);
                peerVisualSignatures.put(str, strBuildPeerVisualSignature);
                if (npcCreatePeerActor != null) {
                }
                return npcCreatePeerActor;
            }
        }
        return npc;
    }

    private static float getPeerAttackStateTime(String str, long j2) {
        LinkedHashMap linkedHashMap;
        Long l2;
        if (str != null && (linkedHashMap = peerAttackStateLocks) != null && (l2 = (Long) linkedHashMap.get(str)) != null) {
            long jLongValue = l2.longValue();
            if (System.currentTimeMillis() <= jLongValue) {
                return ((r8 - jLongValue) + 500) / 1000.0f;
            }
            linkedHashMap.remove(str);
            LinkedHashMap linkedHashMap2 = peerAttackStateNames;
            if (linkedHashMap2 != null) {
                linkedHashMap2.remove(str);
            }
            LinkedHashMap linkedHashMap3 = peerAttackFacingNames;
            if (linkedHashMap3 != null) {
                linkedHashMap3.remove(str);
            }
        }
        return -1.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0068 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0027 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String[] getPeerMarkerNames(String str) {
        String str2;
        String str3;
        String str4;
        if (str == null || str.trim().isEmpty()) {
            return new String[0];
        }
        LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
        if (instanceIfReady == null) {
            return new String[0];
        }
        List<LanSessionManager.PlayerState> peerStatesSnapshot = instanceIfReady.getPeerStatesSnapshot();
        ArrayList arrayList = new ArrayList();
        boolean zInvokeAreasIsCity = invokeAreasIsCity(str);
        for (LanSessionManager.PlayerState playerState : peerStatesSnapshot) {
            if (playerState != null && (str2 = playerState.currentLevelId) != null && !str2.trim().isEmpty()) {
                if (zInvokeAreasIsCity) {
                    if (sameCityGroup(str, playerState.currentLevelId)) {
                        str3 = playerState.characterName;
                        if (str3 != null || (zInvokeAreasIsCity = str3.isEmpty())) {
                            str4 = playerState.playerName;
                            if (str4 == null) {
                                arrayList.add(str4);
                            }
                        } else {
                            arrayList.add(str3);
                        }
                    }
                } else if (str.equals(playerState.currentLevelId)) {
                    str3 = playerState.characterName;
                    if (str3 != null) {
                    }
                    str4 = playerState.playerName;
                    if (str4 == null) {
                    }
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static float[] getPeerMarkerPairs(String str, float f2) {
        if (str == null || str.trim().isEmpty()) {
            return new float[0];
        }
        LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
        if (instanceIfReady == null) {
            return new float[0];
        }
        List<LanSessionManager.PlayerState> peerStatesSnapshot = instanceIfReady.getPeerStatesSnapshot();
        if (peerStatesSnapshot.isEmpty()) {
            return new float[0];
        }
        ArrayList arrayList = new ArrayList();
        boolean zInvokeAreasIsCity = invokeAreasIsCity(str);
        for (LanSessionManager.PlayerState playerState : peerStatesSnapshot) {
            if (playerState != null && playerState.currentLevelId != null && !playerState.currentLevelId.trim().isEmpty() && (!zInvokeAreasIsCity || sameCityGroup(str, playerState.currentLevelId))) {
                float[] fArrResolveMarker = resolveMarker(playerState.currentLevelId, f2);
                if (fArrResolveMarker != null) {
                    arrayList.add(Float.valueOf(fArrResolveMarker[0]));
                    arrayList.add(Float.valueOf(fArrResolveMarker[1]));
                }
            }
        }
        float[] fArr = new float[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            fArr[i2] = ((Float) arrayList.get(i2)).floatValue();
        }
        return fArr;
    }

    public static List getPeersForLevel(String str) {
        LanSessionManager instanceIfReady;
        ArrayList arrayList = new ArrayList();
        if (str != null && !str.trim().isEmpty() && (instanceIfReady = LanSessionManager.getInstanceIfReady()) != null && instanceIfReady.isSessionRunning()) {
            List<LanSessionManager.PlayerState> peerStatesSnapshot = instanceIfReady.getPeerStatesSnapshot();
            String currentPlayerName = getCurrentPlayerName();
            for (LanSessionManager.PlayerState playerState : peerStatesSnapshot) {
                if (playerState != null) {
                    String str2 = playerState.playerName;
                    if (str2 != null) {
                        String strTrim = str2.trim();
                        if (currentPlayerName == null || currentPlayerName.isEmpty() || !currentPlayerName.equals(strTrim)) {
                        }
                    }
                    String str3 = playerState.currentLevelId;
                    if (str3 != null && !str3.trim().isEmpty() && str.equals(str3)) {
                        arrayList.add(playerState);
                    }
                }
            }
        }
        return arrayList;
    }

    private static Object getStaticFieldValue(String str, String str2) {
        try {
            Field fieldFindField = findField(Class.forName(str), str2);
            if (fieldFindField == null) {
                return null;
            }
            fieldFindField.setAccessible(true);
            return fieldFindField.get(null);
        } catch (Exception e2) {
            return null;
        }
    }

    private static String getWorldNpcSpawnKey(int i2) {
        String name;
        if (i2 > 0) {
            MapObject mapObjectH = GameLevel.h(i2);
            if ((mapObjectH instanceof MonsterSpawn) && (name = ((MonsterSpawn) mapObjectH).getName()) != null) {
                return name;
            }
        }
        return "";
    }

    private static boolean hasPeerAttackStateLock(String str) {
        LinkedHashMap linkedHashMap;
        Long l2;
        if (str == null || (linkedHashMap = peerAttackStateLocks) == null || (l2 = (Long) linkedHashMap.get(str)) == null) {
            return false;
        }
        if (System.currentTimeMillis() <= l2.longValue()) {
            return true;
        }
        LinkedHashMap linkedHashMap2 = peerAttackStateLocks;
        if (linkedHashMap2 != null) {
            linkedHashMap2.remove(str);
        }
        LinkedHashMap linkedHashMap3 = peerAttackStateNames;
        if (linkedHashMap3 != null) {
            linkedHashMap3.remove(str);
        }
        LinkedHashMap linkedHashMap4 = peerAttackFacingNames;
        if (linkedHashMap4 == null) {
            return false;
        }
        linkedHashMap4.remove(str);
        return false;
    }

    private static boolean invokeAreasIsCity(String str) {
        try {
            Object objInvokeStatic = invokeStatic("net.fdgames.GameWorld.Areas", "j", String.class, str);
            if (objInvokeStatic instanceof Boolean) {
                return ((Boolean) objInvokeStatic).booleanValue();
            }
            return false;
        } catch (Exception e2) {
            return false;
        }
    }

    private static int invokeInt(Object obj, String str) {
        if (obj == null) {
            return 0;
        }
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            Object objInvoke = method.invoke(obj, new Object[0]);
            if (objInvoke instanceof Number) {
                return ((Number) objInvoke).intValue();
            }
            return 0;
        } catch (Exception e2) {
            return 0;
        }
    }

    private static int invokeInt(Object obj, String str, String str2) {
        if (obj == null) {
            return 0;
        }
        try {
            Method method = obj.getClass().getMethod(str, String.class);
            method.setAccessible(true);
            Object objInvoke = method.invoke(obj, str2);
            if (objInvoke instanceof Number) {
                return ((Number) objInvoke).intValue();
            }
            return 0;
        } catch (Exception e2) {
            return 0;
        }
    }

    private static Object invokeObject(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            return method.invoke(obj, new Object[0]);
        } catch (Exception e2) {
            return null;
        }
    }

    private static void invokePrivate(Object obj, String str, String str2) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, new Object[0]);
        } catch (Exception unused) {
        }
    }

    private static Object invokeStatic(String str, String str2) throws Exception {
        Method method = Class.forName(str).getMethod(str2, new Class[0]);
        method.setAccessible(true);
        return method.invoke(null, new Object[0]);
    }

    private static Object invokeStatic(String str, String str2, Class<?> cls, Object obj) throws Exception {
        Method method = Class.forName(str).getMethod(str2, cls);
        method.setAccessible(true);
        return method.invoke(null, obj);
    }

    private static String invokeString(Object obj, String str) {
        if (obj == null) {
            return "";
        }
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            Object objInvoke = method.invoke(obj, new Object[0]);
            return objInvoke == null ? "" : String.valueOf(objInvoke);
        } catch (Exception e2) {
            return "";
        }
    }

    private static boolean isAuthoritativeWorldNpc(NPC npc) {
        if (npc == null || npc.lanPeerVisual || isTrackedPeerExtraActor(npc) || npc.summoned) {
            return false;
        }
        String str = npc.ai_type;
        if (str != null && "companion".equals(str)) {
            return false;
        }
        int iL1 = npc.L1();
        return (iL1 > 0 && (GameLevel.h(iL1) instanceof MonsterSpawn)) || npc.s();
    }

    public static boolean isSessionRunning() {
        LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
        if (instanceIfReady != null) {
            return instanceIfReady.isSessionRunning();
        }
        return false;
    }

    private static boolean isTrackedPeerExtraActor(NPC npc) {
        LinkedHashMap linkedHashMap;
        LinkedHashMap linkedHashMap2;
        if (npc == null) {
            return false;
        }
        LinkedHashMap linkedHashMap3 = peerSummonActors;
        return (linkedHashMap3 != null && linkedHashMap3.containsValue(npc)) || ((linkedHashMap = peerCompanionActors) != null && linkedHashMap.containsValue(npc)) || ((linkedHashMap2 = peerFollowerActors) != null && linkedHashMap2.containsValue(npc));
    }

    private static String joinStrings(Object obj) {
        if (obj == null) {
            return "";
        }
        if (!(obj instanceof List)) {
            return asString(obj);
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            String strTrim = asString(it.next()).trim();
            if (!strTrim.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(strTrim);
            }
        }
        return sb.toString();
    }

    private static void lockPeerAttackState(String str) {
        LinkedHashMap linkedHashMap;
        if (str == null || (linkedHashMap = peerAttackStateLocks) == null) {
            return;
        }
        linkedHashMap.put(str, Long.valueOf(System.currentTimeMillis() + 500));
    }

    public static void mirrorCombatAction(String str, int i2, int i3, String str2, float f2) {
        LanSessionManager instanceIfReady;
        Object objInvokeStatic;
        Object fieldValue;
        int intField;
        String strAsString;
        Object fieldValue2;
        Object fieldValue3;
        if (str != null) {
            if (("ATTACK".equals(str) || "CAST".equals(str)) && i2 == i3 && (instanceIfReady = LanSessionManager.getInstanceIfReady()) != null && instanceIfReady.isSessionRunning() && (objInvokeStatic = invokeStatic("net.fdgames.GameWorld.GameData", "v")) != null && (fieldValue = getFieldValue(objInvokeStatic, "player")) != null && i2 == getIntField(fieldValue, "uniqueID")) {
                String localPlayerName = instanceIfReady.getLocalPlayerName();
                if (localPlayerName == null || localPlayerName.trim().isEmpty()) {
                    localPlayerName = invokeString(fieldValue, "getName");
                }
                if (localPlayerName == null || localPlayerName.trim().isEmpty()) {
                    return;
                }
                int i4 = localCombatSeq + 1;
                localCombatSeq = i4;
                int iRound = Math.round(1000.0f * f2);
                int intField2 = -1;
                int intField3 = -1;
                int iInvokeInt = 0;
                if ("ATTACK".equals(str)) {
                    intField = getIntField(fieldValue, "lastTargetHit_id");
                    strAsString = "";
                } else {
                    intField = getIntField(fieldValue, "spellTarget");
                    strAsString = asString(getFieldValue(fieldValue, "spell_id"));
                    if (strAsString != null && !strAsString.trim().isEmpty() && (fieldValue2 = getFieldValue(fieldValue, "sheet")) != null && (fieldValue3 = getFieldValue(fieldValue2, "skillSet")) != null) {
                        iInvokeInt = invokeInt(fieldValue3, "g", strAsString);
                    }
                }
                Object fieldValue4 = getFieldValue(fieldValue, "skillOrigin");
                if (fieldValue4 != null) {
                    intField2 = getIntField(fieldValue4, "x");
                    intField3 = getIntField(fieldValue4, "y");
                }
                instanceIfReady.publishCombatAction(localPlayerName, i4, str, iRound, strAsString, intField2, intField3, enumName(invokeObject(fieldValue, "d0")), enumName(getFieldValue(fieldValue, "facing")), iInvokeInt, intField);
            }
        }
    }

    private static String normalizeGenderName(String str) {
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                String lowerCase = strTrim.toLowerCase(Locale.ROOT);
                if (lowerCase.contains("female")) {
                    return "female";
                }
                if (lowerCase.contains("male")) {
                    return "male";
                }
            }
        }
        return "male";
    }

    private static String normalizePeerAttackStateName(String str) {
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty() && !"IDLE".equals(strTrim)) {
                return strTrim;
            }
        }
        return "ATTACKING";
    }

    public static void openChat() {
        LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
        if (instanceIfReady == null || !instanceIfReady.isSessionRunning()) {
            return;
        }
        instanceIfReady.markChatRead();
        IPlatformResolver iPlatformResolverF = ExiledKingdoms.f();
        if (iPlatformResolverF instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) iPlatformResolverF;
            mainActivity.runOnUiThread(new LanGameBridgeChatRunnable(mainActivity, instanceIfReady));
        }
    }

    public static void openLobby() {
        IPlatformResolver iPlatformResolverF = ExiledKingdoms.f();
        if (iPlatformResolverF instanceof MainActivity) {
            ((MainActivity) iPlatformResolverF).u();
        }
    }

    private static boolean parseBooleanString(String str) {
        if (str == null) {
            return false;
        }
        String strTrim = str.trim();
        return "1".equals(strTrim) || "true".equalsIgnoreCase(strTrim);
    }

    public static void postGameLog(String str) {
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        try {
            Class.forName("net.fdgames.GameWorld.GameVariables").getMethod("g", String.class).invoke(null, str);
        } catch (Exception e2) {
            try {
                Object fieldValue = getFieldValue(invokeStatic("net.fdgames.GameWorld.GameData", "v"), "log");
                if (fieldValue != null) {
                    fieldValue.getClass().getMethod("a", String.class).invoke(fieldValue, str);
                }
            } catch (Exception e3) {
            }
        }
    }

    private static void prepareWorldNpcLevel(String str) {
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                String str2 = lastAppliedWorldNpcLevelId;
                if (str2 == null || str2.equals(strTrim)) {
                    return;
                }
                clearWorldNpcActors();
                return;
            }
        }
        clearWorldNpcActors();
    }

    public static void publishLocalAttackStart(Character character, int i2) {
        LanSessionManager instanceIfReady;
        Object objInvokeStatic;
        Object fieldValue;
        if (character == null || (instanceIfReady = LanSessionManager.getInstanceIfReady()) == null || !instanceIfReady.isSessionRunning() || (objInvokeStatic = invokeStatic("net.fdgames.GameWorld.GameData", "v")) == null || (fieldValue = getFieldValue(objInvokeStatic, "player")) == null || getIntField(character, "uniqueID") != getIntField(fieldValue, "uniqueID")) {
            return;
        }
        String localPlayerName = instanceIfReady.getLocalPlayerName();
        if (localPlayerName == null || localPlayerName.trim().isEmpty()) {
            localPlayerName = invokeString(character, "getName");
        }
        if (localPlayerName == null || localPlayerName.trim().isEmpty()) {
            return;
        }
        int i3 = localCombatSeq + 1;
        localCombatSeq = i3;
        instanceIfReady.publishCombatAction(localPlayerName, i3, "ATTACK_START", 0, "", -1, -1, enumName(invokeObject(character, "d0")), enumName(getFieldValue(character, "facing")), 0, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void publishPeerDamageIfNeeded(Character character, int i2, String str, String str2, DamageData damageData) {
        LanSessionManager instanceIfReady;
        GameData gameDataV;
        String strTrim;
        CharacterStats characterStats;
        String str3;
        String str4 = str2;
        if (character == null || damageData == null || (instanceIfReady = LanSessionManager.getInstanceIfReady()) == null) {
            return;
        }
        GameData gameDataV2 = GameData.v();
        if (gameDataV2 == null || (str3 = gameDataV2.CurrentLevel) == null || !str3.equals("H10_pvp_arena")) {
        }
        String strTrim2 = resolvePeerOwnerName(character).trim();
        if (strTrim2.isEmpty() || (gameDataV = GameData.v()) == null) {
            return;
        }
        String str5 = gameDataV.CurrentLevel;
        if (str5 != null) {
            strTrim = str5.trim();
            if (strTrim.isEmpty()) {
                GameData gameDataV3 = GameData.v();
                if (gameDataV3 == null) {
                    return;
                } else {
                    strTrim = gameDataV3.currentMapName;
                }
            }
        }
        if (strTrim != null) {
            String strTrim3 = strTrim.trim();
            if (strTrim3.isEmpty()) {
                return;
            }
            String str6 = (str == null || str.trim().isEmpty()) ? "HIT" : str;
            if (str4 == null) {
                str4 = "";
            }
            String strSerializeDamageCsv = serializeDamageCsv(damageData);
            String strConsumeAppliedPeerProcCsv = consumeAppliedPeerProcCsv(character);
            int i3 = 0;
            int i4 = 0;
            CharacterSheet characterSheet = character.sheet;
            if (characterSheet != null && (characterStats = characterSheet.stats) != null) {
                i3 = characterStats.missingHP;
                i4 = characterStats.missingMana;
            }
            instanceIfReady.publishPlayerDamage(strTrim2, strTrim3, i2, str4, str6, damageData.critical ? 1 : 0, damageData.weapon_item_id, strSerializeDamageCsv, strConsumeAppliedPeerProcCsv, i3, i4);
        }
    }

    public static void receiveRemoteCombat(String str, int i2, String str2, int i3, String str3, int i4, int i5, String str4, String str5, int i6, int i7) {
        GameData gameDataV;
        Player player;
        GameData gameDataV2;
        Player player2;
        Integer num;
        LanSessionManager instanceIfReady;
        String str6;
        Player player3;
        CharacterSheet characterSheet;
        CharacterStats characterStats;
        String currentPlayerName;
        if (str == null || str2 == null || str.trim().isEmpty()) {
            return;
        }
        String currentPlayerName2 = getCurrentPlayerName();
        if (currentPlayerName2 != null && currentPlayerName2.equals(str)) {
            GameData gameDataV3 = GameData.v();
            if (gameDataV3 == null || (str6 = gameDataV3.CurrentLevel) == null || !str6.equals("H10_pvp_arena")) {
                return;
            }
            GameVariables gameVariables = gameDataV3.gameVariables;
            if ((gameVariables != null && gameVariables.b("pvp_arena_won") < 1) || (player3 = gameDataV3.player) == null || (characterSheet = player3.sheet) == null || (characterStats = characterSheet.stats) == null) {
                return;
            }
            int i8 = i6;
            int iG = characterStats.g();
            if (i8 > iG) {
                i8 = iG;
            }
            characterStats.missingHP = i8;
            if (i8 >= iG) {
                characterStats.missingHP = 0;
                GameVariables gameVariables2 = gameDataV3.gameVariables;
                if (gameVariables2 != null) {
                    gameVariables2.e(0, "pvp_arena_won");
                }
                LanSessionManager instanceIfReady2 = LanSessionManager.getInstanceIfReady();
                if (instanceIfReady2 != null && (currentPlayerName = getCurrentPlayerName()) != null) {
                    instanceIfReady2.sendChat("[PVP] " + currentPlayerName + " has been eliminated!");
                }
            }
            forcePublishLocalState();
            return;
        }
        LinkedHashMap linkedHashMap = peerActors;
        NPC npc = linkedHashMap != null ? (NPC) linkedHashMap.get(str) : null;
        if (npc == null && (instanceIfReady = LanSessionManager.getInstanceIfReady()) != null) {
            List<LanSessionManager.PlayerState> peerStatesSnapshot = instanceIfReady.getPeerStatesSnapshot();
            LanSessionManager.PlayerState playerStateCaptureLocalState = captureLocalState();
            syncPeerActors(peerStatesSnapshot, playerStateCaptureLocalState != null ? playerStateCaptureLocalState.currentLevelId : null);
            LinkedHashMap linkedHashMap2 = peerActors;
            if (linkedHashMap2 != null) {
                npc = (NPC) linkedHashMap2.get(str);
            }
        }
        if (npc != null) {
            LinkedHashMap linkedHashMap3 = peerCombatSeqs;
            int iIntValue = 0;
            if (linkedHashMap3 != null && (num = (Integer) linkedHashMap3.get(str)) != null) {
                iIntValue = num.intValue();
            }
            if (iIntValue >= i2) {
                return;
            }
            if (linkedHashMap3 != null) {
                linkedHashMap3.put(str, Integer.valueOf(i2));
            }
            boolean zEquals = "ATTACK_START".equals(str2);
            boolean zEquals2 = "ATTACK".equals(str2);
            if (zEquals) {
                refreshPeerEquipmentFromLatestSnapshot(str, npc);
                if (npc != null && (gameDataV2 = GameData.v()) != null && (player2 = gameDataV2.player) != null) {
                    int i9 = player2.f3307x;
                    int i10 = player2.f3308y;
                    int i11 = npc.f3307x - i9;
                    if (i11 < 0) {
                        i11 = -i11;
                    }
                    int i12 = npc.f3308y - i10;
                    if (i12 < 0) {
                        i12 = -i12;
                    }
                    if (i11 + i12 <= 640) {
                        GameAssets.o("swing");
                    }
                }
            } else if (zEquals2) {
                refreshPeerEquipmentFromLatestSnapshot(str, npc);
                if (npc != null && (gameDataV = GameData.v()) != null && (player = gameDataV.player) != null) {
                    int i13 = player.f3307x;
                    int i14 = player.f3308y;
                    int i15 = npc.f3307x - i13;
                    if (i15 < 0) {
                        i15 = -i15;
                    }
                    int i16 = npc.f3308y - i14;
                    if (i16 < 0) {
                        i16 = -i16;
                    }
                    if (i15 + i16 <= 640) {
                        GameAssets.o("hit");
                    }
                }
            }
            if (zEquals || zEquals2) {
                npc.lastTargetHit_id = i7;
                setIntField(npc, "spellTarget", 0);
            } else {
                setIntField(npc, "spellTarget", i7);
            }
            if (str3 != null) {
                npc.spell_id = str3;
                if (i6 > 0) {
                    ensureActorSkillLevel(npc, str3, i6);
                }
            }
            int i17 = i4;
            int i18 = i5;
            if (i17 >= 0) {
                Coords coords = npc.skillOrigin;
                if (coords == null) {
                    npc.skillOrigin = new Coords(i17, i18);
                } else {
                    coords.f3508x = i17;
                    coords.f3509y = i18;
                }
            }
            if (i17 < 0) {
                i18 = 0;
                npc.skillOrigin = null;
            }
            if (str3 != null && !str3.isEmpty()) {
                if (i17 < 0 || i18 < 0) {
                    i17 = npc.f3307x;
                    i18 = npc.f3308y;
                }
                spawnVisualEffect(str3, i17, i18, i6);
            }
            if (str5 != null && !str5.trim().isEmpty()) {
                npc.facing = resolveFacing(str5);
            }
            if (zEquals) {
                rememberPeerAttackStart(str, str4, str5);
                applyPeerAttackVisualLock(buildEntityCacheKey("player", str), npc, 0.0f);
                npc.ai_disabled = true;
                npc.visibleToPlayer = Boolean.TRUE;
                return;
            }
            if (!zEquals2 && str4 != null && !str4.trim().isEmpty()) {
                npc.q0(resolveActorState(str4));
                npc.stateRelativeTime = 0.0f;
                String strJoinStrings = joinStrings(npc.animationSetName);
                String strCaptureSpriteIndexCsv = captureSpriteIndexCsv(npc);
                if (strJoinStrings == null || strJoinStrings.trim().isEmpty()) {
                    npc.v0();
                } else {
                    applyAnimationSet(npc, strJoinStrings);
                }
                applySpriteIndexCsv(npc, strCaptureSpriteIndexCsv);
            }
            npc.visibleToPlayer = Boolean.TRUE;
            if (zEquals2) {
                String strBuildEntityCacheKey = buildEntityCacheKey("player", str);
                if (!hasPeerAttackStateLock(strBuildEntityCacheKey)) {
                    rememberPeerAttackStart(str, str4, str5);
                    applyPeerAttackVisualLock(strBuildEntityCacheKey, npc, 0.0f);
                    npc.ai_disabled = true;
                }
            }
            HashSet hashSetCollectActorIds = collectActorIds();
            int iQ = npc.q();
            MessageRouter.a(str2, iQ, iQ, null, 0.0f, null);
            bindNewPeerSummons(hashSetCollectActorIds, npc);
        }
    }

    public static void recordAppliedPeerProc(Character character, DamageEffect damageEffect) {
        LanSessionManager instanceIfReady;
        DamageEffect.EffectType effectType;
        LinkedHashMap linkedHashMap;
        if (character == null || damageEffect == null || (instanceIfReady = LanSessionManager.getInstanceIfReady()) == null || !instanceIfReady.isHosting() || resolvePeerOwnerName(character).isEmpty() || (effectType = damageEffect.type) == null || (linkedHashMap = pendingPeerDamageProcs) == null) {
            return;
        }
        Integer numValueOf = Integer.valueOf(character.q());
        String str = (String) linkedHashMap.get(numValueOf);
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.length() > 0) {
            sb = sb.append(str).append(";");
        }
        linkedHashMap.put(numValueOf, sb.append(effectType.name()).append(":").append(damageEffect.level).toString());
    }

    public static void refreshChatDialogView(LanSessionManager lanSessionManager, TextView textView, ScrollView scrollView) {
        if (lanSessionManager == null || textView == null) {
            return;
        }
        List<String> chatSnapshot = lanSessionManager.getChatSnapshot();
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = chatSnapshot.iterator();
        while (it.hasNext()) {
            sb.append(it.next()).append('\n');
        }
        textView.setText(sb.toString().trim());
        if (scrollView != null) {
            scrollView.fullScroll(130);
        }
    }

    private static void refreshPeerEquipmentFromLatestSnapshot(String str, NPC npc) {
        LanSessionManager instanceIfReady;
        String str2;
        if (str == null || npc == null || (instanceIfReady = LanSessionManager.getInstanceIfReady()) == null || !instanceIfReady.isSessionRunning()) {
            return;
        }
        for (LanSessionManager.PlayerState playerState : instanceIfReady.getPeerStatesSnapshot()) {
            if (playerState != null && (str2 = playerState.playerName) != null && str2.equals(str)) {
                applyPeerEquipmentSnapshot(npc, playerState);
                return;
            }
        }
    }

    private static void refreshPeerVisualFromSnapshot(NPC npc, LanSessionManager.PlayerState playerState) {
        a<Integer> aVar;
        if (npc == null || playerState == null) {
            return;
        }
        applyPeerEquipmentSnapshot(npc, playerState);
        npc.gender = resolveGender(playerState.genderName);
        npc.v0();
        if (!npc.lanPeerVisual || (aVar = npc.spriteIndex) == null || aVar.f2517c <= 0) {
            String str = playerState.spriteIndexCsv;
            if (str != null && !str.trim().isEmpty()) {
                applySpriteIndexCsv(npc, str);
                return;
            }
            String str2 = playerState.spriteName;
            if (str2 == null || str2.trim().isEmpty()) {
                return;
            }
            applyAnimationSet(npc, str2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002c A[PHI: r5
      0x002c: PHI (r5v1 java.lang.String) = (r5v0 java.lang.String), (r5v2 java.lang.String) binds: [B:7:0x0017, B:9:0x0021] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void rememberPeerAttackStart(String str, String str2, String str3) {
        String str4;
        Integer num;
        if (str != null) {
            String strBuildEntityCacheKey = buildEntityCacheKey("player", str);
            lockPeerAttackState(strBuildEntityCacheKey);
            String strNormalizePeerAttackStateName = normalizePeerAttackStateName(str2);
            LinkedHashMap linkedHashMap = peerAttackStateNames;
            if (linkedHashMap != null) {
                linkedHashMap.put(strBuildEntityCacheKey, strNormalizePeerAttackStateName);
            }
            String strTrim = null;
            if (str3 != null) {
                strTrim = str3.trim();
                if (strTrim.isEmpty()) {
                    LinkedHashMap linkedHashMap2 = peerAttackFacingNames;
                    if (linkedHashMap2 != null) {
                        linkedHashMap2.remove(strBuildEntityCacheKey);
                    }
                    str4 = strTrim;
                } else {
                    LinkedHashMap linkedHashMap3 = peerAttackFacingNames;
                    if (linkedHashMap3 != null) {
                        linkedHashMap3.put(strBuildEntityCacheKey, strTrim);
                    }
                    str4 = strTrim;
                }
            }
            LinkedHashMap linkedHashMap4 = peerActionSeqs;
            int iIntValue = 0;
            if (linkedHashMap4 != null && (num = (Integer) linkedHashMap4.get(str)) != null) {
                iIntValue = num.intValue();
            }
            String strBuildAnimationKey = buildAnimationKey(strNormalizePeerAttackStateName, str4, iIntValue);
            LinkedHashMap linkedHashMap5 = peerAppliedAnimationKeys;
            if (linkedHashMap5 != null) {
                linkedHashMap5.put(strBuildEntityCacheKey, strBuildAnimationKey);
            }
        }
    }

    /* JADX WARN: Unreachable blocks removed: 5, instructions: 5 */
    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:34)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:24)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:253)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:56)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:50)
        */
    private static void removeAbsentAuthoritativeWorldNpcs(java.util.HashSet r6) {
        /*
            java.util.LinkedHashMap r0 = net.fdgames.ek.android.lan.LanGameBridge.worldNpcActors
            if (r0 == 0) goto L2a
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        Lc:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L2a
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            if (r6 == 0) goto L26
            boolean r3 = r6.contains(r2)
            if (r3 != 0) goto Lc
        L26:
            r0.remove()
            goto Lc
        L2a:
            return
            net.fdgames.GameLevel.GameLevelData r0 = net.fdgames.GameLevel.GameLevelData.o()
            if (r0 == 0) goto L6b
            java.util.ArrayList<net.fdgames.GameEntities.Final.NPC> r0 = r0.npcs
            if (r0 == 0) goto L6b
            java.util.Iterator r0 = r0.iterator()
        L39:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L6b
            java.lang.Object r1 = r0.next()
            net.fdgames.GameEntities.Final.NPC r1 = (net.fdgames.GameEntities.Final.NPC) r1
            boolean r2 = isAuthoritativeWorldNpc(r1)
            if (r2 == 0) goto L39
            java.lang.String r2 = resolveWorldNpcAuthId(r1)
            if (r6 == 0) goto L57
            boolean r3 = r6.contains(r2)
            if (r3 != 0) goto L39
        L57:
            r0.remove()
            java.util.ArrayList r3 = net.fdgames.GameLevel.GameLevel.e()
            if (r3 == 0) goto L63
            r3.remove(r1)
        L63:
            java.util.LinkedHashMap r3 = net.fdgames.ek.android.lan.LanGameBridge.worldNpcActors
            if (r3 == 0) goto L39
            r3.remove(r2)
            goto L39
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.fdgames.ek.android.lan.LanGameBridge.removeAbsentAuthoritativeWorldNpcs(java.util.HashSet):void");
    }

    private static void removeAllPeerFollowers(String str) {
        LinkedHashMap linkedHashMap;
        if (str == null || (linkedHashMap = peerFollowerActors) == null) {
            return;
        }
        String str2 = asString(str) + "\t";
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str3 = (String) entry.getKey();
            entry.getKey();
            if (str3 != null && str3.startsWith(str2)) {
                NPC npc = (NPC) entry.getValue();
                if (npc != null) {
                    GameLevelData.o().npcs.remove(npc);
                    GameLevel.e().remove(npc);
                }
                clearAppliedState(str3);
                it.remove();
            }
        }
    }

    private static void removeNpcFromLevel(NPC npc) {
        ArrayList<NPC> arrayList;
        if (npc != null) {
            GameLevelData gameLevelDataO = GameLevelData.o();
            if (gameLevelDataO != null && (arrayList = gameLevelDataO.npcs) != null) {
                arrayList.remove(npc);
            }
            ArrayList<MapActor> arrayListE = GameLevel.e();
            if (arrayListE != null) {
                arrayListE.remove(npc);
            }
        }
    }

    private static void removePeerCompanion(String str) {
        NPC npc;
        if (str != null) {
            clearAppliedState(buildEntityCacheKey("companion", str));
            LinkedHashMap linkedHashMap = peerCompanionActors;
            if (linkedHashMap == null || (npc = (NPC) linkedHashMap.remove(str)) == null) {
                return;
            }
            GameLevelData.o().npcs.remove(npc);
            GameLevel.e().remove(npc);
            LinkedHashMap linkedHashMap2 = peerSummonOwners;
            if (linkedHashMap2 != null) {
                linkedHashMap2.remove(Integer.valueOf(npc.q()));
            }
        }
    }

    private static void removePeerFollower(String str) {
        NPC npc;
        if (str != null) {
            clearAppliedState(str);
            LinkedHashMap linkedHashMap = peerFollowerActors;
            if (linkedHashMap == null || (npc = (NPC) linkedHashMap.remove(str)) == null) {
                return;
            }
            GameLevelData.o().npcs.remove(npc);
            GameLevel.e().remove(npc);
        }
    }

    private static void removePeerSummon(String str) {
        NPC npc;
        if (str != null) {
            clearAppliedState(buildEntityCacheKey("summon", str));
            LinkedHashMap linkedHashMap = peerSummonActors;
            if (linkedHashMap == null || (npc = (NPC) linkedHashMap.remove(str)) == null) {
                return;
            }
            GameLevelData.o().npcs.remove(npc);
            GameLevel.e().remove(npc);
            LinkedHashMap linkedHashMap2 = peerSummonOwners;
            if (linkedHashMap2 != null) {
                linkedHashMap2.remove(Integer.valueOf(npc.q()));
            }
        }
    }

    private static void removePeerSummonsForOwner(int i2) {
        LinkedHashMap linkedHashMap = peerSummonOwners;
        if (linkedHashMap != null) {
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Integer num = (Integer) entry.getValue();
                if (num != null && num.intValue() == i2) {
                    Integer num2 = (Integer) entry.getKey();
                    if (num2 != null) {
                        MapActor mapActorG = GameLevel.g(num2.intValue());
                        if (mapActorG instanceof NPC) {
                            NPC npc = (NPC) mapActorG;
                            GameLevelData.o().npcs.remove(npc);
                            GameLevel.e().remove(npc);
                        }
                    }
                    it.remove();
                }
            }
        }
    }

    private static MapActor.ActorState resolveActorState(String str) {
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                try {
                    return MapActor.ActorState.valueOf(strTrim);
                } catch (Exception e2) {
                }
            }
        }
        return MapActor.ActorState.f3286b;
    }

    private static Rules.CharacterClass resolveCharacterClass(String str) {
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                try {
                    return Rules.CharacterClass.valueOf(strTrim);
                } catch (Exception e2) {
                }
            }
        }
        return Rules.CharacterClass.f3479b;
    }

    private static Rules.CharacterRace resolveCharacterRace(String str) {
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                try {
                    return Rules.CharacterRace.valueOf(strTrim);
                } catch (Exception e2) {
                }
            }
        }
        return Rules.CharacterRace.f3487b;
    }

    private static String resolveClassName(Object obj) {
        Object fieldValue = getFieldValue(obj, "characterClass");
        if (fieldValue == null) {
            return "";
        }
        try {
            return String.valueOf(fieldValue.getClass().getMethod("name", new Class[0]).invoke(fieldValue, new Object[0]));
        } catch (Exception e2) {
            return String.valueOf(fieldValue);
        }
    }

    public static MapActor resolveCompanionAnchor(NPC npc) {
        LinkedHashMap linkedHashMap;
        Integer num;
        GameData gameDataV = GameData.v();
        Player player = gameDataV != null ? gameDataV.player : null;
        if (npc != null && (linkedHashMap = peerSummonOwners) != null && (num = (Integer) linkedHashMap.get(Integer.valueOf(npc.q()))) != null) {
            MapActor mapActorG = GameLevel.g(num.intValue());
            if (mapActorG != null) {
                return mapActorG;
            }
            linkedHashMap.remove(Integer.valueOf(npc.q()));
        }
        return player;
    }

    private static MapActor.Facing resolveFacing(String str) {
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                try {
                    return MapActor.Facing.valueOf(strTrim);
                } catch (Exception e2) {
                }
            }
        }
        return MapActor.Facing.f3303g;
    }

    private static Character.Gender resolveGender(String str) {
        return "female".equals(normalizeGenderName(str)) ? Character.Gender.f3208c : Character.Gender.f3207b;
    }

    public static MapActor resolveHostileTargetActor(MapActor mapActor) {
        HashSet hashSet;
        Object obj;
        NPC orCreatePeerActor;
        List<LanSessionManager.PlayerState> peerStatesSnapshot;
        GameData gameDataV = GameData.v();
        if (gameDataV == null) {
            return null;
        }
        Player player = gameDataV.player;
        if (player != null && player.d0() != MapActor.ActorState.f3289e) {
            LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
            if (instanceIfReady != null && instanceIfReady.isSessionRunning() && instanceIfReady.isHosting() && (peerStatesSnapshot = instanceIfReady.getPeerStatesSnapshot()) != null) {
                peerStatesSnapshot.iterator();
                GameData gameDataV2 = GameData.v();
                asString(getFieldValue(gameDataV2, "CurrentLevel"));
                asString(getFieldValue(gameDataV2, "currentMapName"));
            }
            return player;
        }
        LanSessionManager instanceIfReady2 = LanSessionManager.getInstanceIfReady();
        if (instanceIfReady2 != null && instanceIfReady2.isSessionRunning() && instanceIfReady2.isHosting()) {
            String strAsString = asString(getFieldValue(gameDataV, "CurrentLevel"));
            String strAsString2 = asString(getFieldValue(gameDataV, "currentMapName"));
            if (strAsString == null || strAsString.trim().isEmpty()) {
                strAsString = strAsString2;
            }
            if (strAsString2 == null || strAsString2.trim().isEmpty()) {
                strAsString2 = strAsString;
            }
            List<LanSessionManager.PlayerState> peerStatesSnapshot2 = instanceIfReady2.getPeerStatesSnapshot();
            if (peerStatesSnapshot2 != null) {
                Iterator<LanSessionManager.PlayerState> it = peerStatesSnapshot2.iterator();
                int i2 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
                NPC npc = null;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    LanSessionManager.PlayerState next = it.next();
                    if (next != null) {
                        if ("DEAD".equals(next.actorStateName)) {
                            String str = next.playerName;
                            if (str != null && (hashSet = peerDeadNames) != null && !hashSet.contains(str)) {
                                hashSet.add(str);
                                LinkedHashMap linkedHashMap = peerActors;
                                if (linkedHashMap != null && (obj = linkedHashMap.get(str)) != null) {
                                    removeNpcFromLevel((NPC) obj);
                                    linkedHashMap.remove(str);
                                }
                                postGameLog("⚔ " + str + " foi derrotado!");
                            }
                        } else {
                            String str2 = next.playerName;
                            if (str2 != null) {
                                String strTrim = str2.trim();
                                if (!strTrim.isEmpty() && samePeerLocation(strAsString, strAsString2, next) && (orCreatePeerActor = getOrCreatePeerActor(strTrim, next)) != null) {
                                    applyPeerVisualFxMask(orCreatePeerActor, next);
                                    ensureActorSkillLevel(orCreatePeerActor, "stealth", next.stealthSkillLevel);
                                    if (orCreatePeerActor.d0() == MapActor.ActorState.f3289e) {
                                        continue;
                                    } else {
                                        if (mapActor == null) {
                                            npc = orCreatePeerActor;
                                            break;
                                        }
                                        int iR = b.r(mapActor.f3307x, mapActor.f3308y, orCreatePeerActor.f3307x, orCreatePeerActor.f3308y);
                                        if (iR < i2) {
                                            i2 = iR;
                                            npc = orCreatePeerActor;
                                        }
                                    }
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                return (npc != null || player == null || player.d0() == MapActor.ActorState.f3289e) ? npc : player;
            }
        }
        return null;
    }

    private static float[] resolveMarker(String str, float f2) {
        int i2;
        int i3;
        try {
            if (!invokeAreasIsCity(str)) {
                if (invokeStatic("net.fdgames.GameWorld.Areas", "h", String.class, str) == null) {
                    return null;
                }
                return new float[]{getIntField(r8, "x") * f2, getIntField(r8, "y") * f2};
            }
            Object staticFieldValue = getStaticFieldValue("i.LXkY.rFUF", "ZTiRQlaiRSeCND");
            String strValueOf = staticFieldValue == null ? "" : String.valueOf(staticFieldValue);
            if (str.startsWith("NG")) {
                i2 = 21;
                i3 = 22;
            } else if (str.startsWith("FT")) {
                i2 = 9;
                i3 = 25;
            } else if (str.startsWith("NI")) {
                i2 = 17;
                i3 = 32;
            } else if (strValueOf.isEmpty() || !str.startsWith(strValueOf)) {
                i2 = 0;
                i3 = 0;
            } else {
                i2 = 26;
                i3 = 37;
            }
            float f3 = i2 * f2;
            float f4 = i3 * f2;
            if (!strValueOf.isEmpty() && str.equals(strValueOf)) {
                f3 -= f2 / 2.0f;
                f4 -= f2 / 2.5f;
            }
            return new float[]{f3, f4};
        } catch (Exception e2) {
            return null;
        }
    }

    private static String resolvePeerOwnerName(Character character) {
        LinkedHashMap linkedHashMap;
        String str;
        if (character != null && (character instanceof NPC)) {
            NPC npc = (NPC) character;
            if (npc.lanPeerVisual && (linkedHashMap = peerActorOwners) != null && (str = (String) linkedHashMap.get(Integer.valueOf(npc.q()))) != null) {
                return str;
            }
        }
        return "";
    }

    private static WeaponStats resolveWeaponStatsFromItemId(int i2) {
        Item itemF;
        WeaponStats weaponStats;
        return (i2 <= 0 || (itemF = Rules.f(i2)) == null || (weaponStats = itemF.weaponStats) == null) ? new WeaponStats() : weaponStats;
    }

    private static String resolveWorldNpcAuthId(NPC npc) {
        if (npc == null) {
            return "";
        }
        String str = npc.unique_tag;
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                return strTrim;
            }
        }
        int iL1 = npc.L1();
        String worldNpcSpawnKey = getWorldNpcSpawnKey(iL1);
        return (worldNpcSpawnKey == null || worldNpcSpawnKey.isEmpty()) ? buildWorldNpcFallbackAuthId(npc.spawn_id, iL1) : worldNpcSpawnKey;
    }

    private static boolean sameCityGroup(String str, String str2) {
        String strCityGroup = cityGroup(str);
        return !strCityGroup.isEmpty() && strCityGroup.equals(cityGroup(str2));
    }

    private static boolean samePeerLocation(String str, String str2, LanSessionManager.PlayerState playerState) {
        if (playerState == null) {
            return false;
        }
        if (str != null) {
            String str3 = playerState.currentLevelId;
            if (str3 != null && str.equals(str3)) {
                return true;
            }
            String str4 = playerState.currentMapName;
            if (str4 != null && str.equals(str4)) {
                return true;
            }
        }
        if (str2 != null) {
            String str5 = playerState.currentMapName;
            if (str5 != null && str2.equals(str5)) {
                return true;
            }
            String str6 = playerState.currentLevelId;
            if (str6 != null && str2.equals(str6)) {
                return true;
            }
        }
        return false;
    }

    private static String serializeDamageCsv(DamageData damageData) {
        ArrayList<Damage> arrayList;
        if (damageData == null || (arrayList = damageData.damages) == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Damage damage : arrayList) {
            if (damage != null) {
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(damageTypeCode(damage.type)).append(":").append(damage.hp).append(":").append(damage.projectile ? "1" : "0");
            }
        }
        return sb.toString();
    }

    private static void setAppliedSampleTime(String str, long j2) {
        LinkedHashMap linkedHashMap;
        if (str == null || (linkedHashMap = peerAppliedSampleTimes) == null) {
            return;
        }
        linkedHashMap.put(str, Long.valueOf(j2));
    }

    private static void setFieldValue(Object obj, String str, Object obj2) {
        if (obj == null) {
            return;
        }
        try {
            Field fieldFindField = findField(obj.getClass(), str);
            if (fieldFindField == null) {
                return;
            }
            fieldFindField.setAccessible(true);
            fieldFindField.set(obj, obj2);
        } catch (Exception e2) {
        }
    }

    private static void setIntField(Object obj, String str, int i2) {
        setFieldValue(obj, str, Integer.valueOf(i2));
    }

    private static void spawnVisualEffect(String str, int i2, int i3, int i4) {
        if (str == null || str.isEmpty()) {
            return;
        }
        String str2 = "";
        String lowerCase = str.toLowerCase();
        if (i4 <= 0) {
            i4 = 1;
        }
        if (lowerCase.contains("fireball")) {
            str2 = "fireball_weak_1";
            if (i4 >= 4) {
                str2 = "fireball_weak_4";
            } else if (i4 >= 3) {
                str2 = "fireball_weak_3";
            } else if (i4 >= 2) {
                str2 = "fireball_weak_2";
            }
        } else if (lowerCase.contains("ice_storm")) {
            str2 = "ice_storm_weak_1";
            if (i4 >= 4) {
                str2 = "ice_storm_weak_4";
            } else if (i4 >= 3) {
                str2 = "ice_storm_weak_3";
            } else if (i4 >= 2) {
                str2 = "ice_storm_weak_2";
            }
        } else if (lowerCase.contains("combustion")) {
            str2 = "combustion_weak_1";
            if (i4 >= 3) {
                str2 = "combustion_weak_3";
            } else if (i4 >= 2) {
                str2 = "combustion_weak_2";
            }
        } else if (lowerCase.contains("death_cloud")) {
            str2 = "dc_weak_1";
            if (i4 >= 3) {
                str2 = "dc_weak_3";
            } else if (i4 >= 2) {
                str2 = "dc_weak_2";
            }
        } else if (lowerCase.contains("flames_of_faith")) {
            str2 = "flames_faith_weak1";
            if (i4 >= 3) {
                str2 = "flames_faith_weak3";
            } else if (i4 >= 2) {
                str2 = "flames_faith_weak2";
            }
        } else if (lowerCase.contains("smoke_bomb")) {
            str2 = "smoke_bomb";
        }
        if (str2.isEmpty()) {
            return;
        }
        GameLevelData.e(new MapEffectEntity(0.0f, i2, i3, 0, str2));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void syncPeerActors(List list, String str) {
        String strTrim;
        String str2;
        ArrayList<NPC> arrayList;
        LinkedHashMap linkedHashMap;
        if (list == null) {
            clearPeerActors();
            return;
        }
        if (str != null) {
            strTrim = str.trim();
            if (strTrim.isEmpty()) {
                strTrim = null;
            }
        }
        GameLevelData gameLevelDataO = GameLevelData.o();
        if (gameLevelDataO != null && (arrayList = gameLevelDataO.npcs) != null) {
            Iterator<NPC> it = arrayList.iterator();
            while (it.hasNext()) {
                NPC next = it.next();
                if (next.lanPeerVisual && ((linkedHashMap = peerActors) == null || !linkedHashMap.containsValue(next))) {
                    it.remove();
                    GameLevel.e().remove(next);
                }
            }
        }
        String strAsString = "";
        try {
            Object objInvokeStatic = invokeStatic("net.fdgames.GameWorld.GameData", "v");
            if (objInvokeStatic != null) {
                strAsString = asString(getFieldValue(objInvokeStatic, "currentMapName"));
            }
        } catch (Exception unused) {
            strAsString = "";
        }
        String strTrim2 = strAsString.trim();
        if (strTrim == null && strTrim2.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        String currentPlayerName = getCurrentPlayerName();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            LanSessionManager.PlayerState playerState = (LanSessionManager.PlayerState) it2.next();
            if (playerState != null && (str2 = playerState.playerName) != null) {
                String strTrim3 = str2.trim();
                if (!strTrim3.isEmpty() && (currentPlayerName == null || currentPlayerName.isEmpty() || !currentPlayerName.equals(strTrim3))) {
                    hashSet.add(strTrim3);
                    if (samePeerLocation(strTrim, strTrim2, playerState)) {
                        try {
                            NPC orCreatePeerActor = getOrCreatePeerActor(strTrim3, playerState);
                            if (orCreatePeerActor != null) {
                                String strBuildEntityCacheKey = buildEntityCacheKey("player", strTrim3);
                                long appliedSampleTime = getAppliedSampleTime(strBuildEntityCacheKey);
                                long j2 = playerState.sampleTimeMs;
                                boolean z2 = appliedSampleTime != j2;
                                String strBuildAnimationKey = buildAnimationKey(playerState.actorStateName, playerState.facingName, playerState.actionSeq);
                                String str3 = (String) peerAppliedAnimationKeys.get(strBuildEntityCacheKey);
                                boolean z3 = str3 == null || !strBuildAnimationKey.equals(str3);
                                String strBuildPeerVisualKey = buildPeerVisualKey(playerState);
                                String str4 = (String) peerAppliedVisualKeys.get(strBuildEntityCacheKey);
                                if (str4 == null || !strBuildPeerVisualKey.equals(str4)) {
                                    refreshPeerVisualFromSnapshot(orCreatePeerActor, playerState);
                                    peerAppliedVisualKeys.put(strBuildEntityCacheKey, strBuildPeerVisualKey);
                                }
                                String displayName = getDisplayName(playerState);
                                orCreatePeerActor.r1((displayName == null || displayName.trim().isEmpty()) ? strTrim3 : displayName);
                                int i2 = playerState.portraitIndex;
                                if (i2 >= 0) {
                                    orCreatePeerActor.portraitIndex = i2;
                                }
                                float peerAttackStateTime = getPeerAttackStateTime(strBuildEntityCacheKey, j2);
                                applyPeerMotion(strTrim3, orCreatePeerActor, playerState);
                                if (peerAttackStateTime >= 0.0f) {
                                    applyPeerAttackVisualLock(strBuildEntityCacheKey, orCreatePeerActor, peerAttackStateTime);
                                    orCreatePeerActor.ai_disabled = true;
                                    orCreatePeerActor.visibleToPlayer = Boolean.TRUE;
                                } else {
                                    orCreatePeerActor.facing = resolveFacing(playerState.facingName);
                                    orCreatePeerActor.q0(resolveActorState(playerState.actorStateName));
                                    orCreatePeerActor.stateRelativeTime = computeStateRelativeTime(playerState.stateTimeMs, j2);
                                    if (z3) {
                                        peerAppliedAnimationKeys.put(strBuildEntityCacheKey, strBuildAnimationKey);
                                    }
                                    orCreatePeerActor.ai_disabled = true;
                                    orCreatePeerActor.visibleToPlayer = Boolean.TRUE;
                                }
                                syncPeerSummon(strTrim3, orCreatePeerActor, playerState);
                                syncPeerCompanion(strTrim3, orCreatePeerActor, playerState);
                                syncPeerFollowers(strTrim3, orCreatePeerActor, playerState);
                                if (z2) {
                                    setAppliedSampleTime(strBuildEntityCacheKey, j2);
                                }
                                Integer num = (Integer) peerActionSeqs.get(strTrim3);
                                int iIntValue = num != null ? num.intValue() : 0;
                                int i3 = playerState.actionSeq;
                                if (iIntValue != i3) {
                                    peerActionSeqs.put(strTrim3, Integer.valueOf(i3));
                                    orCreatePeerActor.spell_id = playerState.spellId;
                                    setIntField(orCreatePeerActor, "spellTarget", playerState.spellTarget);
                                    if (playerState.actionOriginX >= 0) {
                                        Coords coords = orCreatePeerActor.skillOrigin;
                                        if (coords == null) {
                                            orCreatePeerActor.skillOrigin = new Coords(playerState.actionOriginX, playerState.actionOriginY);
                                        } else {
                                            coords.f3508x = playerState.actionOriginX;
                                            coords.f3509y = playerState.actionOriginY;
                                        }
                                    } else {
                                        orCreatePeerActor.skillOrigin = null;
                                    }
                                    orCreatePeerActor.q0(resolveActorState(playerState.actorStateName));
                                    orCreatePeerActor.stateRelativeTime = 0.0f;
                                }
                                applyPeerVisualFxMask(orCreatePeerActor, playerState);
                                applyPeerCombatSnapshot(orCreatePeerActor, playerState);
                            }
                        } catch (Exception e2) {
                        }
                    }
                }
            }
        }
        Iterator it3 = peerActors.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry entry = (Map.Entry) it3.next();
            if (!hashSet.contains(entry.getKey())) {
                NPC npc = (NPC) entry.getValue();
                String str5 = (String) entry.getKey();
                if (npc != null) {
                    untrackPeerActorOwner(npc);
                    removePeerSummonsForOwner(npc.q());
                    removePeerSummon(str5);
                    removePeerCompanion(str5);
                    removeAllPeerFollowers(str5);
                    GameLevelData.o().npcs.remove(npc);
                    GameLevel.e().remove(npc);
                }
                peerActionSeqs.remove(str5);
                peerCombatSeqs.remove(str5);
                peerMotionStates.remove(str5);
                peerVisualSignatures.remove(str5);
                clearAppliedState(buildEntityCacheKey("player", str5));
                it3.remove();
            }
        }
    }

    private static void syncPeerCompanion(String str, NPC npc, LanSessionManager.PlayerState playerState) {
        String str2;
        ArrayList<NPC> arrayList;
        if (str != null) {
            if (npc != null && playerState != null && (str2 = playerState.companionSpawnId) != null) {
                String strTrim = str2.trim();
                if (!strTrim.isEmpty() && playerState.companionX >= 0 && playerState.companionY >= 0) {
                    NPC npcFindPeerCompanionCandidate = (NPC) peerCompanionActors.get(str);
                    if (npcFindPeerCompanionCandidate != null && !strTrim.equals(npcFindPeerCompanionCandidate.spawn_id)) {
                        removePeerCompanion(str);
                        npcFindPeerCompanionCandidate = null;
                    }
                    if (npcFindPeerCompanionCandidate == null && (npcFindPeerCompanionCandidate = findPeerCompanionCandidate(npc, playerState)) == null && (npcFindPeerCompanionCandidate = createPeerCompanion(playerState)) == null) {
                        return;
                    }
                    GameLevelData gameLevelDataO = GameLevelData.o();
                    if (gameLevelDataO != null && (arrayList = gameLevelDataO.npcs) != null && !arrayList.contains(npcFindPeerCompanionCandidate)) {
                        GameLevel.a(npcFindPeerCompanionCandidate);
                        npcFindPeerCompanionCandidate.B1();
                        GameLevel.e().add(npcFindPeerCompanionCandidate);
                    }
                    peerCompanionActors.put(str, npcFindPeerCompanionCandidate);
                    npcFindPeerCompanionCandidate.spawn_id = strTrim;
                    String strBuildEntityCacheKey = buildEntityCacheKey("companion", str);
                    long appliedSampleTime = getAppliedSampleTime(strBuildEntityCacheKey);
                    long j2 = playerState.sampleTimeMs;
                    boolean z2 = appliedSampleTime != j2;
                    String str3 = playerState.companionTag;
                    if (str3 != null && !str3.trim().isEmpty()) {
                        npcFindPeerCompanionCandidate.tag = str3;
                    }
                    String str4 = playerState.companionName;
                    if (str4 != null && !str4.trim().isEmpty()) {
                        npcFindPeerCompanionCandidate.r1(str4);
                    }
                    npcFindPeerCompanionCandidate.f3307x = playerState.companionX;
                    npcFindPeerCompanionCandidate.f3308y = playerState.companionY;
                    String strBuildAnimationKey = buildAnimationKey(playerState.companionActorStateName, playerState.companionFacingName);
                    String str5 = (String) peerAppliedAnimationKeys.get(strBuildEntityCacheKey);
                    boolean z3 = str5 == null || !strBuildAnimationKey.equals(str5);
                    String strBuildVisualKey = buildVisualKey(playerState.companionSpriteName, playerState.companionSpriteIndexCsv);
                    String str6 = (String) peerAppliedVisualKeys.get(strBuildEntityCacheKey);
                    if (str6 == null || !strBuildVisualKey.equals(str6)) {
                        applyAnimationSet(npcFindPeerCompanionCandidate, playerState.companionSpriteName);
                        applySpriteIndexCsv(npcFindPeerCompanionCandidate, playerState.companionSpriteIndexCsv);
                        peerAppliedVisualKeys.put(strBuildEntityCacheKey, strBuildVisualKey);
                    }
                    if (z3) {
                        npcFindPeerCompanionCandidate.facing = resolveFacing(playerState.companionFacingName);
                        npcFindPeerCompanionCandidate.q0(resolveActorState(playerState.companionActorStateName));
                        npcFindPeerCompanionCandidate.stateRelativeTime = computeStateRelativeTime(playerState.companionStateTimeMs, j2);
                        peerAppliedAnimationKeys.put(strBuildEntityCacheKey, strBuildAnimationKey);
                    }
                    npcFindPeerCompanionCandidate.ai_disabled = true;
                    npcFindPeerCompanionCandidate.summoned = true;
                    npcFindPeerCompanionCandidate.visibleToPlayer = Boolean.TRUE;
                    if (z2) {
                        setAppliedSampleTime(strBuildEntityCacheKey, j2);
                        return;
                    }
                    return;
                }
            }
            removePeerCompanion(str);
        }
    }

    private static void syncPeerFollowers(String str, NPC npc, LanSessionManager.PlayerState playerState) {
        ArrayList<LanSessionManager.FollowerState> arrayList;
        String str2;
        String str3;
        if (str != null) {
            if (npc == null || playerState == null || (arrayList = playerState.followers) == null || arrayList.size() <= 0) {
                removeAllPeerFollowers(str);
                return;
            }
            HashSet hashSet = new HashSet();
            for (LanSessionManager.FollowerState followerState : arrayList) {
                if (followerState != null && (str2 = followerState.spawnId) != null && !str2.trim().isEmpty() && (str3 = followerState.tag) != null && !str3.trim().isEmpty() && followerState.f3653x >= 0 && followerState.f3654y >= 0) {
                    String strBuildPeerFollowerKey = buildPeerFollowerKey(str, followerState);
                    hashSet.add(strBuildPeerFollowerKey);
                    NPC npcCreatePeerFollower = (NPC) peerFollowerActors.get(strBuildPeerFollowerKey);
                    if (npcCreatePeerFollower != null || (npcCreatePeerFollower = createPeerFollower(followerState)) != null) {
                        peerFollowerActors.put(strBuildPeerFollowerKey, npcCreatePeerFollower);
                        npcCreatePeerFollower.spawn_id = str2;
                        npcCreatePeerFollower.tag = str3;
                        String str4 = followerState.name;
                        if (str4 != null && !str4.trim().isEmpty()) {
                            npcCreatePeerFollower.r1(str4);
                        }
                        npcCreatePeerFollower.f3307x = followerState.f3653x;
                        npcCreatePeerFollower.f3308y = followerState.f3654y;
                        long appliedSampleTime = getAppliedSampleTime(strBuildPeerFollowerKey);
                        long j2 = playerState.sampleTimeMs;
                        boolean z2 = appliedSampleTime != j2;
                        String strBuildAnimationKey = buildAnimationKey(followerState.actorStateName, followerState.facingName);
                        String str5 = (String) peerAppliedAnimationKeys.get(strBuildPeerFollowerKey);
                        boolean z3 = str5 == null || !strBuildAnimationKey.equals(str5);
                        String strBuildVisualKey = buildVisualKey(followerState.spriteName, followerState.spriteIndexCsv);
                        String str6 = (String) peerAppliedVisualKeys.get(strBuildPeerFollowerKey);
                        if (str6 == null || !strBuildVisualKey.equals(str6)) {
                            applyAnimationSet(npcCreatePeerFollower, followerState.spriteName);
                            applySpriteIndexCsv(npcCreatePeerFollower, followerState.spriteIndexCsv);
                            peerAppliedVisualKeys.put(strBuildPeerFollowerKey, strBuildVisualKey);
                        }
                        if (z3) {
                            npcCreatePeerFollower.facing = resolveFacing(followerState.facingName);
                            npcCreatePeerFollower.q0(resolveActorState(followerState.actorStateName));
                            npcCreatePeerFollower.stateRelativeTime = computeStateRelativeTime(followerState.stateTimeMs, j2);
                            peerAppliedAnimationKeys.put(strBuildPeerFollowerKey, strBuildAnimationKey);
                        }
                        npcCreatePeerFollower.ai_disabled = true;
                        npcCreatePeerFollower.summoned = true;
                        npcCreatePeerFollower.visibleToPlayer = Boolean.TRUE;
                        if (z2) {
                            setAppliedSampleTime(strBuildPeerFollowerKey, j2);
                        }
                    }
                }
            }
            String str7 = asString(str) + "\t";
            Iterator it = peerFollowerActors.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String str8 = (String) entry.getKey();
                if (str8 != null && str8.startsWith(str7) && !hashSet.contains(str8)) {
                    NPC npc2 = (NPC) entry.getValue();
                    if (npc2 != null) {
                        GameLevelData.o().npcs.remove(npc2);
                        GameLevel.e().remove(npc2);
                    }
                    clearAppliedState(str8);
                    it.remove();
                }
            }
        }
    }

    private static void syncPeerSummon(String str, NPC npc, LanSessionManager.PlayerState playerState) {
        String str2;
        if (str != null) {
            if (npc != null && playerState != null && (str2 = playerState.summonSpawnId) != null) {
                String strTrim = str2.trim();
                if (!strTrim.isEmpty() && playerState.summonX >= 0 && playerState.summonY >= 0) {
                    NPC npcFindPeerSummonCandidate = (NPC) peerSummonActors.get(str);
                    if (npcFindPeerSummonCandidate != null && !strTrim.equals(npcFindPeerSummonCandidate.spawn_id)) {
                        removePeerSummon(str);
                        npcFindPeerSummonCandidate = null;
                    }
                    if (npcFindPeerSummonCandidate == null && (npcFindPeerSummonCandidate = findPeerSummonCandidate(npc, playerState)) == null && (npcFindPeerSummonCandidate = createPeerSummon(playerState)) == null) {
                        return;
                    }
                    peerSummonActors.put(str, npcFindPeerSummonCandidate);
                    npcFindPeerSummonCandidate.spawn_id = strTrim;
                    String strBuildEntityCacheKey = buildEntityCacheKey("summon", str);
                    long appliedSampleTime = getAppliedSampleTime(strBuildEntityCacheKey);
                    long j2 = playerState.sampleTimeMs;
                    boolean z2 = appliedSampleTime != j2;
                    String str3 = playerState.summonTag;
                    if (str3 != null && !str3.trim().isEmpty()) {
                        npcFindPeerSummonCandidate.tag = str3;
                    }
                    String str4 = playerState.summonName;
                    if (str4 != null && !str4.trim().isEmpty()) {
                        npcFindPeerSummonCandidate.r1(str4);
                    }
                    npcFindPeerSummonCandidate.f3307x = playerState.summonX;
                    npcFindPeerSummonCandidate.f3308y = playerState.summonY;
                    String strBuildAnimationKey = buildAnimationKey(playerState.summonActorStateName, playerState.summonFacingName);
                    String str5 = (String) peerAppliedAnimationKeys.get(strBuildEntityCacheKey);
                    boolean z3 = str5 == null || !strBuildAnimationKey.equals(str5);
                    String strBuildVisualKey = buildVisualKey(playerState.summonSpriteName, playerState.summonSpriteIndexCsv);
                    String str6 = (String) peerAppliedVisualKeys.get(strBuildEntityCacheKey);
                    if (str6 == null || !strBuildVisualKey.equals(str6)) {
                        applyAnimationSet(npcFindPeerSummonCandidate, playerState.summonSpriteName);
                        applySpriteIndexCsv(npcFindPeerSummonCandidate, playerState.summonSpriteIndexCsv);
                        peerAppliedVisualKeys.put(strBuildEntityCacheKey, strBuildVisualKey);
                    }
                    if (z3) {
                        npcFindPeerSummonCandidate.facing = resolveFacing(playerState.summonFacingName);
                        npcFindPeerSummonCandidate.q0(resolveActorState(playerState.summonActorStateName));
                        npcFindPeerSummonCandidate.stateRelativeTime = computeStateRelativeTime(playerState.summonStateTimeMs, j2);
                        peerAppliedAnimationKeys.put(strBuildEntityCacheKey, strBuildAnimationKey);
                    }
                    npcFindPeerSummonCandidate.ai_disabled = true;
                    npcFindPeerSummonCandidate.summoned = true;
                    npcFindPeerSummonCandidate.visibleToPlayer = Boolean.TRUE;
                    if (z2) {
                        setAppliedSampleTime(strBuildEntityCacheKey, j2);
                        return;
                    }
                    return;
                }
            }
            removePeerSummon(str);
        }
    }

    public static void tick() {
        GameVariables gameVariables;
        GameVariables gameVariables2;
        LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
        if (instanceIfReady == null || !instanceIfReady.isSessionRunning()) {
            clearPeerActors();
            return;
        }
        GameData gameDataV = GameData.v();
        if (gameDataV != null && (gameVariables2 = gameDataV.gameVariables) != null) {
            gameVariables2.e(1, "lan_pvp_active");
        }
        List<LanSessionManager.PlayerState> peerStatesSnapshot = instanceIfReady.getPeerStatesSnapshot();
        int i2 = 0;
        if (peerStatesSnapshot != null) {
            Iterator<LanSessionManager.PlayerState> it = peerStatesSnapshot.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String str = it.next().currentMapName;
                if (str != null && str.equals("H10_pvp_arena")) {
                    i2 = 1;
                    break;
                }
            }
        }
        GameData gameDataV2 = GameData.v();
        if (gameDataV2 != null && (gameVariables = gameDataV2.gameVariables) != null) {
            gameVariables.e(i2, "pvp_fight_active");
        }
        List<LanSessionManager.PlayerState> peerStatesSnapshot2 = instanceIfReady.getPeerStatesSnapshot();
        LanSessionManager.PlayerState playerStateCaptureLocalState = captureLocalState();
        if (playerStateCaptureLocalState == null) {
            instanceIfReady.flushPendingCombatPackets();
            instanceIfReady.flushPendingPlayerDamagePackets();
            try {
                syncPeerActors(peerStatesSnapshot2, null);
                return;
            } catch (Exception e2) {
                return;
            }
        }
        String str2 = playerStateCaptureLocalState.currentLevelId;
        if (str2 != null) {
            String str3 = lastSyncedLevelId;
            if (str3 == null || str3.isEmpty()) {
                lastSyncedLevelId = str2;
            } else if (!str3.equals(str2)) {
                clearPeerActors();
                lastSyncedLevelId = str2;
            }
        }
        instanceIfReady.flushPendingCombatPackets();
        instanceIfReady.flushPendingPlayerDamagePackets();
        try {
            syncPeerActors(peerStatesSnapshot2, str2);
        } catch (Exception e3) {
        }
        applyReceivedWorldNpcStates(instanceIfReady, str2);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - lastTickAt < LAN_PUBLISH_INTERVAL_MS) {
            return;
        }
        lastTickAt = jCurrentTimeMillis;
        instanceIfReady.publishLiveState(playerStateCaptureLocalState);
        boolean zIsHosting = instanceIfReady.isHosting();
        String str4 = playerStateCaptureLocalState.currentLevelId;
        if (zIsHosting) {
            captureAndPublishWorldNpcs(instanceIfReady, str4);
        }
    }

    private static void trackPeerActorOwner(String str, NPC npc) {
        LinkedHashMap linkedHashMap;
        if (str == null || npc == null) {
            return;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty() || (linkedHashMap = peerActorOwners) == null) {
            return;
        }
        linkedHashMap.put(Integer.valueOf(npc.q()), strTrim);
    }

    private static void untrackPeerActorOwner(NPC npc) {
        if (npc != null) {
            Integer numValueOf = Integer.valueOf(npc.q());
            LinkedHashMap linkedHashMap = peerActorOwners;
            if (linkedHashMap != null) {
                linkedHashMap.remove(numValueOf);
            }
            LinkedHashMap linkedHashMap2 = pendingPeerDamageProcs;
            if (linkedHashMap2 != null) {
                linkedHashMap2.remove(numValueOf);
            }
        }
    }
}
