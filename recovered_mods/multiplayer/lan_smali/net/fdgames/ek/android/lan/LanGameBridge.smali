.class public final Lnet/fdgames/ek/android/lan/LanGameBridge;
.super Ljava/lang/Object;
.source "LanGameBridge.java"


# static fields
.field private static final LAN_PUBLISH_INTERVAL_MS:J = 0x32L

.field private static lastAppliedWorldNpcLevelId:Ljava/lang/String;

.field private static lastCapturedSpellId:Ljava/lang/String;

.field private static lastCapturedSpellSeq:I

.field private static lastNpcBroadcastAt:J

.field private static lastSyncedLevelId:Ljava/lang/String;

.field private static lastTickAt:J

.field private static localActionSeq:I

.field private static localCombatSeq:I

.field private static localCompanionSnapshot:Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

.field private static localFollowerSnapshots:Ljava/util/LinkedHashMap;

.field private static localGoldBackup:I

.field private static localLastActionSignature:Ljava/lang/String;

.field private static localLastActionStateName:Ljava/lang/String;

.field private static localLastActionStateTimeMs:I

.field private static peerActionSeqs:Ljava/util/LinkedHashMap;

.field private static peerActorOwners:Ljava/util/LinkedHashMap;

.field private static peerActors:Ljava/util/LinkedHashMap;

.field private static peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

.field private static peerAppliedSampleTimes:Ljava/util/LinkedHashMap;

.field private static peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

.field private static peerAttackFacingNames:Ljava/util/LinkedHashMap;

.field private static peerAttackStateLocks:Ljava/util/LinkedHashMap;

.field private static peerAttackStateNames:Ljava/util/LinkedHashMap;

.field private static peerCombatSeqs:Ljava/util/LinkedHashMap;

.field private static peerCompanionActors:Ljava/util/LinkedHashMap;

.field private static peerDeadNames:Ljava/util/HashSet;

.field private static peerFollowerActors:Ljava/util/LinkedHashMap;

.field private static peerMotionStates:Ljava/util/LinkedHashMap;

.field private static peerSummonActors:Ljava/util/LinkedHashMap;

.field private static peerSummonOwners:Ljava/util/LinkedHashMap;

.field private static peerVisualSignatures:Ljava/util/LinkedHashMap;

.field private static pendingPeerDamageProcs:Ljava/util/LinkedHashMap;

.field private static worldNpcActors:Ljava/util/LinkedHashMap;


# direct methods
.method static constructor <clinit>()V
    .registers 1

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localFollowerSnapshots:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActorOwners:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActionSeqs:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCombatSeqs:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCompanionActors:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerFollowerActors:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerMotionStates:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateLocks:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateNames:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackFacingNames:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedSampleTimes:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonActors:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonOwners:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerVisualSignatures:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->pendingPeerDamageProcs:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->worldNpcActors:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerDeadNames:Ljava/util/HashSet;

    const-string v0, ""

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localLastActionSignature:Ljava/lang/String;

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastSyncedLevelId:Ljava/lang/String;

    const/4 v0, 0x0

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCompanionSnapshot:Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    return-void
.end method

.method private constructor <init>()V
    .registers 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V
    .registers 5

    if-eqz p0, :cond_26

    if-eqz p1, :cond_26

    if-eqz p2, :cond_26

    invoke-static {p1, p2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->length()I

    move-result v1

    if-lez v1, :cond_15

    const-string v1, ";"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_15
    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string p1, "="

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_26
    return-void
.end method

.method private static applyAnimationSet(Lnet/fdgames/GameEntities/MapActor;Ljava/lang/String;)V
    .registers 7

    if-eqz p0, :cond_3f

    if-eqz p1, :cond_3f

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_f

    goto :goto_3f

    :cond_f
    iget-object v0, p0, Lnet/fdgames/GameEntities/MapActor;->animationSetName:Ljava/util/ArrayList;

    if-nez v0, :cond_1b

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/GameEntities/MapActor;->animationSetName:Ljava/util/ArrayList;

    goto :goto_1e

    :cond_1b
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    :goto_1e
    const-string v1, ";"

    invoke-virtual {p1, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    array-length v1, p1

    const/4 v2, 0x0

    :goto_26
    if-ge v2, v1, :cond_3c

    aget-object v3, p1, v2

    if-eqz v3, :cond_39

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_39

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_39
    add-int/lit8 v2, v2, 0x1

    goto :goto_26

    :cond_3c
    invoke-virtual {p0}, Lnet/fdgames/GameEntities/MapActor;->v0()V

    :cond_3f
    :goto_3f
    return-void
.end method

.method private static applyFieldFromString(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    .registers 9

    if-eqz p0, :cond_6c

    if-eqz p1, :cond_6c

    :try_start_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-static {v0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->findField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    if-nez v0, :cond_f

    return-void

    :cond_f
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    invoke-virtual {v0}, Ljava/lang/reflect/Field;->getType()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    const-string v2, "boolean"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_2b

    const-string v2, "java.lang.Boolean"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_34

    :cond_2b
    invoke-static {p2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->parseBooleanString(Ljava/lang/String;)Z

    move-result p1

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    goto :goto_67

    :cond_34
    const-string v2, "int"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_44

    const-string v2, "java.lang.Integer"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_4d

    :cond_44
    invoke-static {p2}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    goto :goto_67

    :cond_4d
    const-string v2, "float"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_5d

    const-string v2, "java.lang.Float"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_66

    :cond_5d
    invoke-static {p2}, Ljava/lang/Float;->parseFloat(Ljava/lang/String;)F

    move-result p1

    invoke-static {p1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p1

    goto :goto_67

    :cond_66
    move-object p1, p2

    :goto_67
    invoke-virtual {v0, p0, p1}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_6a
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_6a} :catch_6b

    goto :goto_6c

    :catch_6b
    move-exception p0

    :cond_6c
    :goto_6c
    return-void
.end method

.method private static applyPeerAttackVisualLock(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;F)V
    .registers 7

    if-eqz p1, :cond_53

    const/4 v0, 0x0

    cmpg-float v0, p2, v0

    if-ltz v0, :cond_53

    const-string v3, "ATTACKING"

    if-eqz p0, :cond_3e

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackFacingNames:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_27

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    if-eqz v0, :cond_27

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_27

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v1

    iput-object v1, p1, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    :cond_27
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateNames:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_3e

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    if-eqz v0, :cond_3e

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_3e

    move-object v3, v0

    :cond_3e
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v0

    sget-object v1, Lnet/fdgames/GameEntities/MapActor$ActorState;->b:Lnet/fdgames/GameEntities/MapActor$ActorState;

    if-ne v0, v1, :cond_48

    sget-object v0, Lnet/fdgames/GameEntities/MapActor$ActorState;->d:Lnet/fdgames/GameEntities/MapActor$ActorState;

    :cond_48
    invoke-virtual {p1, v0}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->b()F

    move-result v0

    iput v0, p1, Lnet/fdgames/GameEntities/MapActor;->actionStartTime:F

    iput p2, p1, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    :cond_53
    return-void
.end method

.method private static applyPeerCombatEffectsSnapshot(Lnet/fdgames/GameEntities/Character;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 10

    if-eqz p0, :cond_5d

    if-nez p1, :cond_5

    goto :goto_5d

    :cond_5
    iget-object p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->combatEffectsSnapshot:Ljava/lang/String;

    if-eqz p1, :cond_5d

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_14

    goto :goto_5d

    :cond_14
    const-string v0, "sheet"

    invoke-static {p0, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_5d

    const-string v0, "effects"

    invoke-static {p0, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_5d

    const-string v0, ";"

    invoke-virtual {p1, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    array-length v0, p1

    const/4 v1, 0x0

    :goto_2c
    if-ge v1, v0, :cond_5d

    aget-object v2, p1, v1

    if-eqz v2, :cond_5a

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_5a

    const/16 v3, 0x3d

    invoke-virtual {v2, v3}, Ljava/lang/String;->indexOf(I)I

    move-result v3

    if-lez v3, :cond_5a

    const/4 v4, 0x0

    invoke-virtual {v2, v4, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v5

    add-int/lit8 v3, v3, 0x1

    invoke-virtual {v2, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-static {p0, v3, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyFieldFromString(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V

    :cond_5a
    add-int/lit8 v1, v1, 0x1

    goto :goto_2c

    :cond_5d
    :goto_5d
    return-void
.end method

.method private static applyPeerCombatSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 4

    if-eqz p0, :cond_23

    if-nez p1, :cond_5

    goto :goto_23

    :cond_5
    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerSkillSnapshot(Lnet/fdgames/GameEntities/Character;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerCombatEffectsSnapshot(Lnet/fdgames/GameEntities/Character;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    iget-object p0, p0, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;

    if-eqz p0, :cond_23

    iget-object p0, p0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->stats:Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;

    if-eqz p0, :cond_23

    iget v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingHp:I

    if-ltz v0, :cond_18

    goto :goto_19

    :cond_18
    const/4 v0, 0x0

    :goto_19
    iput v0, p0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingHP:I

    iget p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingMana:I

    if-ltz p1, :cond_20

    goto :goto_21

    :cond_20
    const/4 p1, 0x0

    :goto_21
    iput p1, p0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingMana:I

    :cond_23
    :goto_23
    return-void
.end method

.method private static applyPeerEquipmentSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 7

    if-eqz p0, :cond_73

    if-nez p1, :cond_6

    goto/16 :goto_73

    :cond_6
    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveGender(Ljava/lang/String;)Lnet/fdgames/GameEntities/Character$Gender;

    move-result-object v2

    iput-object v2, p0, Lnet/fdgames/GameEntities/Character;->gender:Lnet/fdgames/GameEntities/Character$Gender;

    iget-object v0, p0, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;

    if-eqz v0, :cond_73

    iget-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->inventory:Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;

    if-nez v1, :cond_17

    goto :goto_73

    :cond_17
    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->portraitIndex:I

    if-ltz v2, :cond_1d

    iput v2, p0, Lnet/fdgames/GameEntities/Character;->portraitIndex:I

    :cond_1d
    const/4 v2, 0x0

    iget v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotBodyItemId:I

    if-ltz v3, :cond_29

    iget v4, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_body:I

    if-eq v4, v3, :cond_29

    iput v3, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_body:I

    const/4 v2, 0x1

    :cond_29
    iget v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotFeetItemId:I

    if-ltz v3, :cond_34

    iget v4, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_feet:I

    if-eq v4, v3, :cond_34

    iput v3, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_feet:I

    const/4 v2, 0x1

    :cond_34
    iget v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHandsItemId:I

    if-ltz v3, :cond_3f

    iget v4, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_hands:I

    if-eq v4, v3, :cond_3f

    iput v3, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_hands:I

    const/4 v2, 0x1

    :cond_3f
    iget v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHeadItemId:I

    if-ltz v3, :cond_4a

    iget v4, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_head:I

    if-eq v4, v3, :cond_4a

    iput v3, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_head:I

    const/4 v2, 0x1

    :cond_4a
    iget v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotLegsItemId:I

    if-ltz v3, :cond_55

    iget v4, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_legs:I

    if-eq v4, v3, :cond_55

    iput v3, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_legs:I

    const/4 v2, 0x1

    :cond_55
    iget v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotMainhandItemId:I

    if-ltz v3, :cond_60

    iget v4, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_mainhand:I

    if-eq v4, v3, :cond_60

    iput v3, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_mainhand:I

    const/4 v2, 0x1

    :cond_60
    iget v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotOffhandItemId:I

    if-ltz v3, :cond_6b

    iget v4, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_offhand:I

    if-eq v4, v3, :cond_6b

    iput v3, v1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->slot_offhand:I

    const/4 v2, 0x1

    :cond_6b
    if-eqz v2, :cond_73

    invoke-virtual {v1}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->s()V

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/Final/NPC;->v0()V

    :cond_73
    :goto_73
    return-void
.end method

.method private static applyPeerMotion(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 17

    move-object/from16 v11, p0

    move-object/from16 v12, p1

    move-object/from16 v13, p2

    if-eqz v12, :cond_102

    if-nez v13, :cond_c

    goto/16 :goto_102

    :cond_c
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerMotionStates:Ljava/util/LinkedHashMap;

    const/4 v1, 0x0

    if-eqz v0, :cond_18

    invoke-virtual {v0, v11}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    goto :goto_19

    :cond_18
    move-object v0, v1

    :goto_19
    const/4 v2, 0x0

    if-eqz v0, :cond_44

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    iget-object v4, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    if-eqz v3, :cond_2b

    if-eqz v4, :cond_44

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_2b

    goto :goto_44

    :cond_2b
    iget v3, v12, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v4, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    sub-int/2addr v3, v4

    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v3

    const/16 v4, 0x60

    if-gt v3, v4, :cond_44

    iget v3, v12, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget v5, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    sub-int/2addr v3, v5

    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v3

    if-gt v3, v4, :cond_44

    goto :goto_45

    :cond_44
    :goto_44
    const/4 v2, 0x1

    :goto_45
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iget-wide v5, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    const-wide/16 v7, 0x0

    cmp-long v0, v5, v7

    if-gtz v0, :cond_52

    move-wide v5, v3

    :cond_52
    sub-long/2addr v3, v5

    cmp-long v0, v3, v7

    if-ltz v0, :cond_58

    goto :goto_59

    :cond_58
    move-wide v3, v7

    :goto_59
    const-wide/16 v5, 0x190

    cmp-long v0, v3, v5

    if-lez v0, :cond_60

    move-wide v3, v5

    :cond_60
    long-to-float v0, v3

    const/high16 v3, 0x447a0000    # 1000.0f

    div-float/2addr v0, v3

    iget v3, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    int-to-float v3, v3

    iget v4, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedX:F

    mul-float/2addr v4, v0

    add-float/2addr v3, v4

    invoke-static {v3}, Ljava/lang/Math;->round(F)I

    move-result v3

    iget v4, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    int-to-float v4, v4

    iget v5, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedY:F

    mul-float/2addr v5, v0

    add-float/2addr v4, v5

    invoke-static {v4}, Ljava/lang/Math;->round(F)I

    move-result v4

    if-eqz v2, :cond_8a

    iput v3, v12, Lnet/fdgames/GameEntities/MapObject;->x:I

    iput v4, v12, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget-object v0, v12, Lnet/fdgames/GameEntities/MapActor;->destination:Lnet/fdgames/TiledMap/Objects/Coords;

    if-eqz v0, :cond_f0

    const/4 v1, -0x1

    iput v1, v0, Lnet/fdgames/TiledMap/Objects/Coords;->x:I

    iput v1, v0, Lnet/fdgames/TiledMap/Objects/Coords;->y:I

    goto :goto_f0

    :cond_8a
    iget v0, v12, Lnet/fdgames/GameEntities/MapObject;->x:I

    sub-int v0, v3, v0

    iget v5, v12, Lnet/fdgames/GameEntities/MapObject;->y:I

    sub-int v5, v4, v5

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v6

    const/4 v7, 0x1

    if-gt v6, v7, :cond_bf

    invoke-static {v5}, Ljava/lang/Math;->abs(I)I

    move-result v6

    if-gt v6, v7, :cond_bf

    iget v6, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedX:F

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v6

    const v8, 0x3d4ccccd    # 0.05f

    cmpl-float v6, v6, v8

    if-gtz v6, :cond_f0

    iget v6, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedY:F

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v6

    cmpl-float v6, v6, v8

    if-gtz v6, :cond_f0

    iget v0, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    iput v0, v12, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v0, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    iput v0, v12, Lnet/fdgames/GameEntities/MapObject;->y:I

    goto :goto_f0

    :cond_bf
    int-to-float v6, v0

    const v8, 0x3f000000    # 0.5f

    mul-float/2addr v6, v8

    invoke-static {v6}, Ljava/lang/Math;->round(F)I

    move-result v6

    if-nez v6, :cond_d1

    if-lez v0, :cond_ce

    const/4 v6, 0x1

    goto :goto_d1

    :cond_ce
    if-gez v0, :cond_d1

    const/4 v6, -0x1

    :cond_d1
    :goto_d1
    iget v0, v12, Lnet/fdgames/GameEntities/MapObject;->x:I

    add-int/2addr v0, v6

    iput v0, v12, Lnet/fdgames/GameEntities/MapObject;->x:I

    int-to-float v0, v5

    mul-float/2addr v0, v8

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    if-nez v0, :cond_e2

    if-lez v5, :cond_e8

    const/4 v0, 0x1

    goto :goto_eb

    :cond_e2
    iget v1, v12, Lnet/fdgames/GameEntities/MapObject;->y:I

    add-int/2addr v1, v0

    iput v1, v12, Lnet/fdgames/GameEntities/MapObject;->y:I

    goto :goto_f0

    :cond_e8
    if-gez v5, :cond_e2

    const/4 v0, -0x1

    :goto_eb
    iget v1, v12, Lnet/fdgames/GameEntities/MapObject;->y:I

    add-int/2addr v1, v0

    iput v1, v12, Lnet/fdgames/GameEntities/MapObject;->y:I

    :cond_f0
    :goto_f0
    iget v0, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedX:F

    iput v0, v12, Lnet/fdgames/GameEntities/MapActor;->speedX:F

    iget v0, v13, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedY:F

    iput v0, v12, Lnet/fdgames/GameEntities/MapActor;->speedY:F

    const/4 v0, 0x0

    iput-boolean v0, v12, Lnet/fdgames/GameEntities/MapActor;->stuck:Z

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerMotionStates:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_102

    invoke-virtual {v0, v11, v13}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_102
    :goto_102
    return-void
.end method

.method private static applyPeerSkillSnapshot(Lnet/fdgames/GameEntities/Character;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 13

    if-eqz p0, :cond_8f

    if-nez p1, :cond_6

    goto/16 :goto_8f

    :cond_6
    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->skillSnapshot:Ljava/lang/String;

    if-eqz v0, :cond_88

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_15

    goto :goto_88

    :cond_15
    const-string v1, "sheet"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_8f

    const-string v2, "skillSet"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_8f

    instance-of v2, v1, Lnet/fdgames/GameEntities/Helpers/SkillSet;

    if-eqz v2, :cond_8f

    check-cast v1, Lnet/fdgames/GameEntities/Helpers/SkillSet;

    const-string v2, "characterSkills"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    instance-of v3, v2, Ljava/util/ArrayList;

    if-eqz v3, :cond_8f

    check-cast v2, Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->clear()V

    const-string v3, ","

    invoke-virtual {v0, v3}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    array-length v3, v0

    const/16 v9, 0x7d0

    if-le v3, v9, :cond_46

    move v3, v9

    :cond_46
    const/4 v4, 0x0

    :goto_47
    if-ge v4, v3, :cond_84

    aget-object v5, v0, v4

    if-eqz v5, :cond_81

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v6

    if-nez v6, :cond_81

    const/16 v6, 0x3d

    invoke-virtual {v5, v6}, Ljava/lang/String;->indexOf(I)I

    move-result v6

    if-lez v6, :cond_81

    const/4 v7, 0x0

    invoke-virtual {v5, v7, v6}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v8

    add-int/lit8 v6, v6, 0x1

    invoke-virtual {v5, v6}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v8}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v5

    if-lez v5, :cond_81

    new-instance v8, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSkill;

    invoke-direct {v8, v6, v5}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSkill;-><init>(Ljava/lang/String;I)V

    invoke-virtual {v2, v8}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_81

    :cond_81
    :goto_81
    add-int/lit8 v4, v4, 0x1

    goto :goto_47

    :cond_84
    invoke-virtual {v1}, Lnet/fdgames/GameEntities/Helpers/SkillSet;->n()V

    goto :goto_8f

    :cond_88
    :goto_88
    const-string v0, "stealth"

    iget p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stealthSkillLevel:I

    invoke-static {p0, v0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->ensureActorSkillLevel(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;I)V

    :cond_8f
    :goto_8f
    return-void
.end method

.method private static applyPeerVisualFxMask(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 7

    if-eqz p0, :cond_7b

    if-nez p1, :cond_6

    goto/16 :goto_7b

    :cond_6
    iget-object v0, p0, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;

    if-eqz v0, :cond_7b

    iget-object v0, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->effects:Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;

    if-nez v0, :cond_10

    goto/16 :goto_7b

    :cond_10
    sget-object v1, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    sget-object v2, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    const/4 v3, 0x0

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->holy_shielded:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->shielded:Ljava/lang/Boolean;

    iput v3, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->shieldBonus:I

    iput v3, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->mageArmor_Charges:I

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->might:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->might_arbenos:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->might_prayer:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->stab:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->fury:Ljava/lang/Boolean;

    iput-boolean v3, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->rapid_fire:Z

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->disintegrate:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->duel:Ljava/lang/Boolean;

    iput-boolean v3, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->flurry:Z

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->rage:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->bloodlust:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->evasion:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->stealth:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->slowed:Ljava/lang/Boolean;

    iput-object v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->stunned:Ljava/lang/Boolean;

    iget-object v4, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->resistances:Lnet/fdgames/GameEntities/CharacterSheet/CharacterResistances;

    if-eqz v4, :cond_42

    invoke-virtual {v4}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterResistances;->e()V

    :cond_42
    iget p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->visualFxMask:I

    and-int/lit8 v4, p1, 0x1

    if-eqz v4, :cond_4a

    iput-object v2, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->holy_shielded:Ljava/lang/Boolean;

    :cond_4a
    and-int/lit8 v4, p1, 0x2

    if-eqz v4, :cond_50

    iput-object v2, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->might:Ljava/lang/Boolean;

    :cond_50
    and-int/lit8 v4, p1, 0x4

    if-eqz v4, :cond_56

    iput-object v2, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->bloodlust:Ljava/lang/Boolean;

    :cond_56
    and-int/lit8 v4, p1, 0x8

    if-eqz v4, :cond_5c

    iput-object v2, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->evasion:Ljava/lang/Boolean;

    :cond_5c
    and-int/lit8 v4, p1, 0x10

    if-eqz v4, :cond_69

    iget-object v4, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->resistances:Lnet/fdgames/GameEntities/CharacterSheet/CharacterResistances;

    if-eqz v4, :cond_69

    const/4 v3, 0x1

    invoke-virtual {v4, v3}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterResistances;->f(I)V

    const/4 v3, 0x0

    :cond_69
    and-int/lit8 v4, p1, 0x20

    if-eqz v4, :cond_6f

    iput-object v2, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->stealth:Ljava/lang/Boolean;

    :cond_6f
    and-int/lit8 v4, p1, 0x40

    if-eqz v4, :cond_75

    iput-object v2, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->slowed:Ljava/lang/Boolean;

    :cond_75
    and-int/lit16 p1, p1, 0x80

    if-eqz p1, :cond_7b

    iput-object v2, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterEffects;->stunned:Ljava/lang/Boolean;

    :cond_7b
    :goto_7b
    return-void
.end method

.method private static applyReceivedWorldNpcStates(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    .registers 16

    :try_start_0
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->prepareWorldNpcLevel(Ljava/lang/String;)V

    invoke-virtual {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getLastNpcStateData()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_d7

    const-string v1, "NPCSTATE2\t"

    invoke-virtual {v0, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_15

    invoke-static {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyReceivedWorldNpcStatesV2(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_15
    const-string v1, "\t"

    invoke-virtual {v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    array-length v2, v1

    const/4 v3, 0x3

    if-lt v2, v3, :cond_d7

    const/4 v2, 0x0

    aget-object v2, v1, v2

    const-string v3, "NPCSTATE"

    invoke-virtual {v3, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_d7

    const/4 v2, 0x2

    aget-object v2, v1, v2

    invoke-static {v2}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v2

    if-lez v2, :cond_d7

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v3

    if-eqz v3, :cond_d7

    const/4 v4, 0x0

    const/4 v5, 0x3

    :cond_3b
    :goto_3b
    if-ge v4, v2, :cond_d7

    aget-object v6, v1, v5

    add-int/lit8 v7, v5, 0x1

    aget-object v7, v1, v7

    invoke-static {v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v7

    add-int/lit8 v8, v5, 0x2

    aget-object v8, v1, v8

    invoke-static {v8}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v8

    add-int/lit8 v9, v5, 0x3

    aget-object v9, v1, v9

    invoke-static {v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v9

    add-int/lit8 v10, v5, 0x4

    aget-object v10, v1, v10

    invoke-static {v10}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v10

    add-int/lit8 v11, v5, 0x5

    aget-object v11, v1, v11

    invoke-static {v11}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v11

    add-int/lit8 v4, v4, 0x1

    add-int/lit8 v5, v5, 0x6

    invoke-virtual {v3}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v12

    :cond_6f
    invoke-interface {v12}, Ljava/util/Iterator;->hasNext()Z

    move-result v13

    if-eqz v13, :cond_8b

    invoke-interface {v12}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v13

    instance-of v0, v13, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v0, :cond_6f

    check-cast v13, Lnet/fdgames/GameEntities/Final/NPC;

    iget-object v0, v13, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    if-eqz v0, :cond_6f

    invoke-virtual {v6, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6f

    move-object v13, v13

    goto :goto_aa

    :cond_8b
    invoke-virtual {v3}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v12

    :cond_8f
    invoke-interface {v12}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3b

    invoke-interface {v12}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    instance-of v13, v0, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v13, :cond_8f

    check-cast v0, Lnet/fdgames/GameEntities/Final/NPC;

    iget-object v13, v0, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    if-eqz v13, :cond_8f

    invoke-virtual {v6, v13}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v13

    if-eqz v13, :cond_8f

    move-object v13, v0

    :goto_aa
    iput v7, v13, Lnet/fdgames/GameEntities/MapObject;->x:I

    iput v8, v13, Lnet/fdgames/GameEntities/MapObject;->y:I

    if-eqz v9, :cond_b3

    invoke-virtual {v13, v9}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    :cond_b3
    if-eqz v10, :cond_b7

    iput-object v10, v13, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    :cond_b7
    const/4 v0, 0x0

    iput-boolean v0, v13, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    iget-object v0, v13, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;

    if-eqz v0, :cond_3b

    invoke-virtual {v0}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->z()I

    move-result v7

    if-lez v7, :cond_3b

    iget-object v8, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->stats:Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;

    if-eqz v8, :cond_3b

    const/16 v0, 0x64

    sub-int v0, v0, v11

    mul-int/2addr v7, v0

    const/16 v0, 0x64

    div-int/2addr v7, v0

    if-gez v7, :cond_d3

    const/4 v7, 0x0

    :cond_d3
    iput v7, v8, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingHP:I

    goto/16 :goto_3b
    :try_end_d7
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_d7} :catch_d8

    :cond_d7
    return-void

    :catch_d8
    move-exception v0

    return-void
.end method

.method private static applyReceivedWorldNpcStatesV2(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;Ljava/lang/String;)V
    .registers 16

    :try_start_0
    if-eqz p1, :cond_1ab

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_e

    goto/16 :goto_1ab

    :cond_e
    if-eqz p2, :cond_1ae

    const-string v0, "\t"

    const/4 v1, -0x1

    invoke-virtual {p2, v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x3

    if-lt v1, v2, :cond_1ae

    const/4 v1, 0x0

    aget-object v1, v0, v1

    const-string v2, "NPCSTATE2"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1ae

    const/4 v1, 0x1

    aget-object v2, v0, v1

    invoke-virtual {p1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_34

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearWorldNpcActors()V

    goto/16 :goto_1ae

    :cond_34
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastAppliedWorldNpcLevelId:Ljava/lang/String;

    if-eqz v1, :cond_41

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_41

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearWorldNpcActors()V

    :cond_41
    sput-object v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastAppliedWorldNpcLevelId:Ljava/lang/String;

    const/4 v1, 0x2

    aget-object v1, v0, v1

    invoke-static {v1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v1

    if-gez v1, :cond_4d

    const/4 v1, 0x0

    :cond_4d
    new-instance v3, Ljava/util/HashSet;

    invoke-direct {v3}, Ljava/util/HashSet;-><init>()V

    const/4 v4, 0x0

    const/4 v5, 0x3

    :goto_54
    if-ge v4, v1, :cond_1a7

    add-int/lit8 v2, v5, 0xa

    array-length v6, v0

    if-gt v6, v2, :cond_5d

    goto/16 :goto_1a7

    :cond_5d
    aget-object v2, v0, v5

    add-int/lit8 v6, v5, 0x1

    aget-object v6, v0, v6

    add-int/lit8 v7, v5, 0x2

    aget-object v7, v0, v7

    invoke-static {v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v7

    add-int/lit8 v8, v5, 0x4

    aget-object v8, v0, v8

    add-int/lit8 v9, v5, 0x5

    aget-object v9, v0, v9

    add-int/lit8 v10, v5, 0x6

    aget-object v10, v0, v10

    invoke-static {v10}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v10

    add-int/lit8 v11, v5, 0x7

    aget-object v11, v0, v11

    invoke-static {v11}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v11

    if-eqz v2, :cond_8a

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    goto :goto_8c

    :cond_8a
    const-string v2, ""

    :goto_8c
    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v12

    if-eqz v12, :cond_a3

    if-eqz v8, :cond_9f

    invoke-virtual {v8}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v12

    if-nez v12, :cond_9f

    goto :goto_a3

    :cond_9f
    invoke-static {v6, v7}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildWorldNpcFallbackAuthId(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v2

    :cond_a3
    :goto_a3
    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v12

    if-nez v12, :cond_1a1

    invoke-virtual {v3, v2}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->findAuthoritativeWorldNpcByAuthId(Ljava/lang/String;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object p0

    if-nez p0, :cond_b4

    goto/16 :goto_1a1

    :cond_b4
    if-eqz p0, :cond_1a1

    sget-object v12, Lnet/fdgames/ek/android/lan/LanGameBridge;->worldNpcActors:Ljava/util/LinkedHashMap;

    if-eqz v12, :cond_bd

    invoke-virtual {v12, v2, p0}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_bd
    if-eqz v8, :cond_cb

    invoke-virtual {v8}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v12

    if-nez v12, :cond_cb

    iput-object v2, p0, Lnet/fdgames/GameEntities/Final/NPC;->unique_tag:Ljava/lang/String;

    :cond_cb
    if-eqz v9, :cond_d9

    invoke-virtual {v9}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v12

    if-nez v12, :cond_d9

    iput-object v2, p0, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    :cond_d9
    if-lez v7, :cond_ea

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/Final/NPC;->L1()I

    move-result v12

    if-eq v12, v7, :cond_ea

    invoke-static {v7}, Lnet/fdgames/GameLevel/GameLevel;->h(I)Lnet/fdgames/GameEntities/MapObject;

    move-result-object v12

    if-eqz v12, :cond_ea

    invoke-virtual {p0, v7}, Lnet/fdgames/GameEntities/Final/NPC;->U1(I)V

    :cond_ea
    iget v2, p0, Lnet/fdgames/GameEntities/MapObject;->x:I

    sub-int v2, v10, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v12

    const/16 v7, 0x60

    if-gt v12, v7, :cond_10d

    const/4 v7, 0x2

    if-le v12, v7, :cond_10a

    int-to-float v2, v2

    const v12, 0x3ecccccd    # 0.4f

    mul-float/2addr v2, v12

    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v2

    if-eqz v2, :cond_10a

    iget v12, p0, Lnet/fdgames/GameEntities/MapObject;->x:I

    add-int/2addr v12, v2

    iput v12, p0, Lnet/fdgames/GameEntities/MapObject;->x:I

    goto :goto_10f

    :cond_10a
    iput v10, p0, Lnet/fdgames/GameEntities/MapObject;->x:I

    goto :goto_10f

    :cond_10d
    iput v10, p0, Lnet/fdgames/GameEntities/MapObject;->x:I

    :goto_10f
    iget v2, p0, Lnet/fdgames/GameEntities/MapObject;->y:I

    sub-int v2, v11, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v12

    const/16 v7, 0x60

    if-gt v12, v7, :cond_132

    const/4 v7, 0x2

    if-le v12, v7, :cond_12f

    int-to-float v2, v2

    const v12, 0x3ecccccd    # 0.4f

    mul-float/2addr v2, v12

    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v2

    if-eqz v2, :cond_12f

    iget v12, p0, Lnet/fdgames/GameEntities/MapObject;->y:I

    add-int/2addr v12, v2

    iput v12, p0, Lnet/fdgames/GameEntities/MapObject;->y:I

    goto :goto_134

    :cond_12f
    iput v11, p0, Lnet/fdgames/GameEntities/MapObject;->y:I

    goto :goto_134

    :cond_132
    iput v11, p0, Lnet/fdgames/GameEntities/MapObject;->y:I

    :goto_134
    add-int/lit8 v12, v5, 0x8

    aget-object v12, v0, v12

    invoke-static {v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object p1

    add-int/lit8 v12, v5, 0x9

    aget-object v12, v0, v12

    invoke-static {v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object p2

    add-int/lit8 v12, v5, 0xa

    aget-object v12, v0, v12

    invoke-static {v12}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v12

    sget-object v2, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v2, p0, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    if-eqz p1, :cond_175

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/MapActor;->d0()Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v2

    if-eq v2, p1, :cond_175

    invoke-virtual {p0, p1}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    sget-object v2, Lnet/fdgames/GameEntities/MapActor$ActorState;->e:Lnet/fdgames/GameEntities/MapActor$ActorState;

    if-ne p1, v2, :cond_175

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removeNpcFromLevel(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveWorldNpcAuthId(Lnet/fdgames/GameEntities/Final/NPC;)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_16f

    sget-object p1, Lnet/fdgames/ek/android/lan/LanGameBridge;->worldNpcActors:Ljava/util/LinkedHashMap;

    if-eqz p1, :cond_16f

    invoke-virtual {p1, v2}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_16f
    add-int/lit8 v4, v4, 0x1

    add-int/lit8 v5, v5, 0xb

    goto/16 :goto_54

    :cond_175
    if-eqz p2, :cond_17d

    iget-object v2, p0, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    if-eq v2, p2, :cond_17d

    iput-object p2, p0, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    :cond_17d
    invoke-virtual {p0}, Lnet/fdgames/GameEntities/Final/NPC;->M1()Z

    move-result v2

    if-nez v2, :cond_1a1

    const/4 v2, 0x1

    iput-boolean v2, p0, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    iget-object v2, p0, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;

    if-eqz v2, :cond_1a1

    invoke-virtual {v2}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->z()I

    move-result v7

    if-lez v7, :cond_1a1

    iget-object v8, v2, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->stats:Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;

    if-eqz v8, :cond_1a1

    const/16 v2, 0x64

    sub-int v2, v2, v12

    mul-int/2addr v7, v2

    const/16 v2, 0x64

    div-int/2addr v7, v2

    if-gez v7, :cond_19f

    const/4 v7, 0x0

    :cond_19f
    iput v7, v8, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingHP:I

    :cond_1a1
    :goto_1a1
    add-int/lit8 v4, v4, 0x1

    add-int/lit8 v5, v5, 0xb

    goto/16 :goto_54

    :cond_1a7
    :goto_1a7
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removeAbsentAuthoritativeWorldNpcs(Ljava/util/HashSet;)V

    goto :goto_1ae

    :cond_1ab
    :goto_1ab
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearWorldNpcActors()V
    :try_end_1ae
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_1ae} :catch_1af

    :cond_1ae
    :goto_1ae
    return-void

    :catch_1af
    move-exception v0

    return-void
.end method

.method private static applySpriteIndexCsv(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;)V
    .registers 9

    if-eqz p0, :cond_49

    if-eqz p1, :cond_49

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_49

    iget-object v0, p0, Lnet/fdgames/GameEntities/Character;->spriteIndex:Lcom/badlogic/gdx/utils/a;

    if-nez v0, :cond_1a

    new-instance v0, Lcom/badlogic/gdx/utils/a;

    invoke-direct {v0}, Lcom/badlogic/gdx/utils/a;-><init>()V

    iput-object v0, p0, Lnet/fdgames/GameEntities/Character;->spriteIndex:Lcom/badlogic/gdx/utils/a;

    goto :goto_1d

    :cond_1a
    invoke-virtual {v0}, Lcom/badlogic/gdx/utils/a;->clear()V

    :goto_1d
    const-string v0, ","

    invoke-virtual {p1, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    array-length v0, p1

    const/4 v1, 0x0

    :goto_25
    if-ge v1, v0, :cond_49

    aget-object v2, p1, v1

    if-eqz v2, :cond_46

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_46

    :try_start_35
    invoke-static {v2}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v3

    if-ltz v3, :cond_46

    iget-object v4, p0, Lnet/fdgames/GameEntities/Character;->spriteIndex:Lcom/badlogic/gdx/utils/a;

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v4, v3}, Lcom/badlogic/gdx/utils/a;->a(Ljava/lang/Object;)V
    :try_end_44
    .catch Ljava/lang/Exception; {:try_start_35 .. :try_end_44} :catch_45

    goto :goto_46

    :catch_45
    move-exception v2

    :cond_46
    :goto_46
    add-int/lit8 v1, v1, 0x1

    goto :goto_25

    :cond_49
    return-void
.end method

.method private static asBoolean(Ljava/lang/Object;)Z
    .registers 2

    instance-of v0, p0, Ljava/lang/Boolean;

    if-eqz v0, :cond_b

    check-cast p0, Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0

    :cond_b
    instance-of v0, p0, Ljava/lang/Number;

    if-eqz v0, :cond_19

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->intValue()I

    move-result p0

    if-eqz p0, :cond_19

    const/4 p0, 0x1

    return p0

    :cond_19
    const/4 p0, 0x0

    return p0
.end method

.method public static asString(Ljava/lang/Object;)Ljava/lang/String;
    .registers 1

    if-nez p0, :cond_5

    const-string p0, ""

    goto :goto_9

    :cond_5
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    :goto_9
    return-object p0
.end method

.method private static bindNewPeerSummons(Ljava/util/HashSet;Lnet/fdgames/GameEntities/Final/NPC;)V
    .registers 11

    if-eqz p1, :cond_71

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v0

    if-eqz v0, :cond_71

    invoke-virtual {p1}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v6

    const/4 v7, 0x0

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_11
    :goto_11
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_71

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    instance-of v2, v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v2, :cond_11

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-virtual {v1}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    if-eqz p0, :cond_31

    invoke-virtual {p0, v3}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_11

    :cond_31
    iget-boolean v4, v1, Lnet/fdgames/GameEntities/Final/NPC;->summoned:Z

    if-nez v4, :cond_41

    iget-object v4, v1, Lnet/fdgames/GameEntities/Final/NPC;->ai_type:Ljava/lang/String;

    if-eqz v4, :cond_11

    const-string v5, "companion"

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_11

    :cond_41
    iget v4, v1, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v5, p1, Lnet/fdgames/GameEntities/MapObject;->x:I

    sub-int/2addr v4, v5

    invoke-static {v4}, Ljava/lang/Math;->abs(I)I

    move-result v4

    const/16 v5, 0x100

    if-le v4, v5, :cond_4f

    goto :goto_11

    :cond_4f
    iget v4, v1, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget v8, p1, Lnet/fdgames/GameEntities/MapObject;->y:I

    sub-int/2addr v4, v8

    invoke-static {v4}, Ljava/lang/Math;->abs(I)I

    move-result v4

    if-le v4, v5, :cond_5b

    goto :goto_11

    :cond_5b
    if-nez v7, :cond_61

    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removePeerSummonsForOwner(I)V

    const/4 v7, 0x1

    :cond_61
    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonOwners:Ljava/util/LinkedHashMap;

    if-eqz v4, :cond_11

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v4, v3, v5}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v4, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v4, v1, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    goto :goto_11

    :cond_71
    return-void
.end method

.method private static buildAnimationKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, "|"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static buildAnimationKey(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    .registers 5

    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildAnimationKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v0, "|"

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static buildCombatEffectsSnapshot(Ljava/lang/Object;)Ljava/lang/String;
    .registers 3

    const-string v0, ""

    if-nez p0, :cond_5

    return-object v0

    :cond_5
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "shielded"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "holy_shielded"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "shieldBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "holy_shieldBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "mageArmor_Charges"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "mageArmorBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "might"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "might_arbenos"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "might_prayer"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "mightBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "mightBonus_arbenos"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "mightBonus_prayer"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "bloodlust"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "bloodlustBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "bloodlustArmorBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "fury"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "furyMultiplier"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "rage"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "rageStrBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "rageArmorBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "stab"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "stabBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "disintegrate"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "disintegrateBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "duel"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "duelbonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "poison"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "poisonBonus"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "rapid_fire"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "flurry"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "flameAura"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "evasion"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "stealth"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "slowed"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "stunned"

    invoke-static {v0, p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->appendSnapshotField(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, "\t"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static buildFollowerIdentityKey(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 5

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, "\t"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string p1, "\t"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-static {p2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static buildPeerFollowerKey(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Ljava/lang/String;
    .registers 4

    const-string v0, ""

    if-nez p1, :cond_5

    return-object v0

    :cond_5
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, "\t"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, "\t"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, "\t"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    iget-object p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static buildPeerVisualKey(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;
    .registers 2

    if-nez p0, :cond_5

    const-string p0, ""

    return-object p0

    :cond_5
    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildPeerVisualSignature(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v0

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteName:Ljava/lang/String;

    invoke-static {v0, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildVisualKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static buildPeerVisualSignature(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;
    .registers 3

    if-nez p0, :cond_5

    const-string p0, ""

    return-object p0

    :cond_5
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->normalizeGenderName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->portraitIndex:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->classEnumName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->raceName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotBodyItemId:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotFeetItemId:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHandsItemId:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHeadItemId:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotLegsItemId:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotMainhandItemId:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotOffhandItemId:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteIndexCsv:Ljava/lang/String;

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static buildVisualKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, "|"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static buildWorldNpcFallbackAuthId(Ljava/lang/String;I)Ljava/lang/String;
    .registers 4

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, "|"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static captureAndPublishWorldNpcs(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    .registers 14

    if-eqz p1, :cond_131

    :try_start_2
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v1

    if-eqz v1, :cond_35

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_11
    :goto_11
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_35

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    instance-of v3, v2, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v3, :cond_11

    check-cast v2, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->isAuthoritativeWorldNpc(Lnet/fdgames/GameEntities/Final/NPC;)Z

    move-result v3

    if-eqz v3, :cond_11

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveWorldNpcAuthId(Lnet/fdgames/GameEntities/Final/NPC;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_11

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_11

    :cond_35
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "\t"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_50
    :goto_50
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_12a

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveWorldNpcAuthId(Lnet/fdgames/GameEntities/Final/NPC;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_50

    invoke-virtual {v2}, Lnet/fdgames/GameEntities/Final/NPC;->L1()I

    move-result v4

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getWorldNpcSpawnKey(I)Ljava/lang/String;

    move-result-object v5

    const-string v6, "\t"

    invoke-virtual {v1, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v6, "\t"

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v6, v2, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v6, "\t"

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v6, "\t"

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v5, "\t"

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v5, v2, Lnet/fdgames/GameEntities/Final/NPC;->unique_tag:Ljava/lang/String;

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v5, "\t"

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v5, v2, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v5, "\t"

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget v5, v2, Lnet/fdgames/GameEntities/MapObject;->x:I

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v5, "\t"

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget v5, v2, Lnet/fdgames/GameEntities/MapObject;->y:I

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v5, "\t"

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v2}, Lnet/fdgames/GameEntities/MapActor;->d0()Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/Enum;->name()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v5, "\t"

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v5, v2, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    if-nez v5, :cond_f7

    const-string v5, "SOUTH"

    goto :goto_fb

    :cond_f7
    invoke-virtual {v5}, Ljava/lang/Enum;->name()Ljava/lang/String;

    move-result-object v5

    :goto_fb
    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v5, "\t"

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v5, v2, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;

    const/16 v6, 0x64

    if-eqz v5, :cond_125

    invoke-virtual {v5}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->z()I

    move-result v7

    if-lez v7, :cond_125

    iget-object v8, v5, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->stats:Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;

    if-eqz v8, :cond_125

    iget v8, v8, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingHP:I

    sub-int v6, v7, v8

    const/16 v8, 0x64

    mul-int/2addr v6, v8

    div-int/2addr v6, v7

    if-gez v6, :cond_120

    const/4 v6, 0x0

    :cond_120
    const/16 v7, 0x64

    if-le v6, v7, :cond_125

    move v6, v7

    :cond_125
    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    goto/16 :goto_50

    :cond_12a
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastNpcState(Ljava/lang/String;)V
    :try_end_131
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_131} :catch_132

    :cond_131
    return-void

    :catch_132
    move-exception v0

    return-void
.end method

.method private static captureAndPublishWorldNpcsToHost(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    .registers 2

    if-eqz p0, :cond_7

    if-eqz p1, :cond_7

    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureAndPublishWorldNpcs(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    :cond_7
    return-void
.end method

.method private static captureGenderName(Ljava/lang/Object;)Ljava/lang/String;
    .registers 1

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->normalizeGenderName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static captureLocalCompanionState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 8

    if-eqz p0, :cond_c4

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v0

    if-eqz v0, :cond_c4

    iget-object v0, v0, Lnet/fdgames/GameWorld/GameData;->party:Lnet/fdgames/GameWorld/Party;

    if-eqz v0, :cond_c4

    invoke-virtual {v0}, Lnet/fdgames/GameWorld/Party;->g()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_c1

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_1e

    goto/16 :goto_c1

    :cond_1e
    invoke-virtual {v0}, Lnet/fdgames/GameWorld/Party;->f()Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v0

    if-eqz v0, :cond_a7

    iget-object v2, v0, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    if-eqz v2, :cond_a7

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_a7

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    iget-object v3, v0, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    if-eqz v3, :cond_3a

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    :cond_3a
    invoke-virtual {v0}, Lnet/fdgames/GameEntities/Character;->getName()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_42

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    :cond_42
    iget v3, v0, Lnet/fdgames/GameEntities/MapObject;->x:I

    iput v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    iget v3, v0, Lnet/fdgames/GameEntities/MapObject;->y:I

    iput v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->ensureVisualSnapshot(Ljava/lang/Object;)V

    iget-object v3, v0, Lnet/fdgames/GameEntities/MapActor;->animationSetName:Ljava/util/ArrayList;

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->joinStrings(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureSpriteIndexCsv(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    iget-object v3, v0, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    invoke-virtual {v0}, Lnet/fdgames/GameEntities/MapActor;->d0()Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v3

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    iget v0, v0, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    const/high16 v3, 0x447a0000    # 1000.0f

    mul-float/2addr v0, v3

    float-to-int v0, v0

    iput v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionStateTimeMs:I

    new-instance v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    invoke-direct {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;-><init>()V

    iput-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    iput-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    iput-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    iget v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    iput v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iget v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    iput v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    iput-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    iput-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    iput-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    iput-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    iget v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionStateTimeMs:I

    iput v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->copyFollowerState(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    move-result-object v0

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCompanionSnapshot:Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    return-void

    :cond_a7
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCompanionSnapshot:Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    if-eqz v0, :cond_bd

    iget-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    if-eqz v2, :cond_bd

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_bd

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->copyFollowerState(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    move-result-object v0

    invoke-static {p0, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->fillCompanionStateFromSnapshot(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)V

    return-void

    :cond_bd
    const/4 v0, 0x0

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCompanionSnapshot:Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    goto :goto_c4

    :cond_c1
    :goto_c1
    const/4 v0, 0x0

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCompanionSnapshot:Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    :cond_c4
    :goto_c4
    return-void
.end method

.method private static captureLocalFollowerStates(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 14

    if-eqz p0, :cond_132

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v0

    if-eqz v0, :cond_132

    iget-object v0, v0, Lnet/fdgames/GameWorld/GameData;->party:Lnet/fdgames/GameWorld/Party;

    if-eqz v0, :cond_132

    iget-object v1, v0, Lnet/fdgames/GameWorld/Party;->followers:Ljava/util/ArrayList;

    if-eqz v1, :cond_132

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    if-eqz v0, :cond_18

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    goto :goto_1f

    :cond_18
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    :goto_1f
    new-instance v2, Ljava/util/HashSet;

    invoke-direct {v2}, Ljava/util/HashSet;-><init>()V

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_28
    :goto_28
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_10e

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lnet/fdgames/GameWorld/Follower;

    if-eqz v3, :cond_28

    iget-object v4, v3, Lnet/fdgames/GameWorld/Follower;->spawn_id:Ljava/lang/String;

    if-eqz v4, :cond_28

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_45

    goto :goto_28

    :cond_45
    invoke-virtual {v3}, Lnet/fdgames/GameWorld/Follower;->a()Lnet/fdgames/TiledMap/Objects/NPCSerializedSpawnData;

    move-result-object v5

    if-eqz v5, :cond_28

    iget-object v6, v5, Lnet/fdgames/TiledMap/Objects/NPCSerializedSpawnData;->tag:Ljava/lang/String;

    if-eqz v6, :cond_28

    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_5a

    goto :goto_28

    :cond_5a
    const-string v0, "summon"

    invoke-virtual {v6, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_63

    goto :goto_28

    :cond_63
    iget-object v7, v5, Lnet/fdgames/TiledMap/Objects/NPCSerializedSpawnData;->name:Ljava/lang/String;

    if-nez v7, :cond_69

    const-string v7, ""

    :cond_69
    invoke-static {v4, v6, v7}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildFollowerIdentityKey(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v2, v8}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    invoke-static {v6}, Lnet/fdgames/GameLevel/GameLevel;->j(Ljava/lang/String;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v9

    if-eqz v9, :cond_ee

    new-instance v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    invoke-direct {v10}, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;-><init>()V

    iput-object v4, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    iput-object v6, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    invoke-virtual {v9}, Lnet/fdgames/GameEntities/Character;->getName()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_92

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_92

    iput-object v0, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    goto :goto_94

    :cond_92
    iput-object v7, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    :goto_94
    iget v0, v9, Lnet/fdgames/GameEntities/MapObject;->x:I

    iput v0, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iget v0, v9, Lnet/fdgames/GameEntities/MapObject;->y:I

    iput v0, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    invoke-static {v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->ensureVisualSnapshot(Ljava/lang/Object;)V

    iget-object v0, v9, Lnet/fdgames/GameEntities/MapActor;->animationSetName:Ljava/util/ArrayList;

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->joinStrings(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    invoke-static {v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureSpriteIndexCsv(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    iget-object v0, v9, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    invoke-virtual {v9}, Lnet/fdgames/GameEntities/MapActor;->d0()Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v0

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    iget v0, v9, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    const/high16 v3, 0x447a0000    # 1000.0f

    mul-float/2addr v0, v3

    float-to-int v0, v0

    iput v0, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    invoke-static {v4, v6, v7}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildFollowerIdentityKey(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v2, v9}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    iget-object v0, v10, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    invoke-static {v4, v6, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildFollowerIdentityKey(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v2, v11}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localFollowerSnapshots:Ljava/util/LinkedHashMap;

    invoke-static {v10}, Lnet/fdgames/ek/android/lan/LanGameBridge;->copyFollowerState(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    move-result-object v12

    invoke-virtual {v0, v9, v12}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v10}, Lnet/fdgames/ek/android/lan/LanGameBridge;->copyFollowerState(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    move-result-object v12

    invoke-virtual {v0, v11, v12}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    invoke-virtual {v0, v10}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_28

    :cond_ee
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localFollowerSnapshots:Ljava/util/LinkedHashMap;

    invoke-virtual {v0, v8}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    if-eqz v0, :cond_28

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->copyFollowerState(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    move-result-object v0

    if-eqz v0, :cond_28

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    invoke-static {v4, v6, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildFollowerIdentityKey(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    invoke-virtual {v3, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_28

    :cond_10e
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localFollowerSnapshots:Ljava/util/LinkedHashMap;

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_118
    :goto_118
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_132

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v2, v1}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_118

    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    goto :goto_118

    :cond_132
    return-void
.end method

.method private static captureLocalState()Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;
    .registers 15

    const-string v0, "b"

    const/4 v1, 0x0

    :try_start_3
    const-string v2, "net.fdgames.GameWorld.GameData"

    const-string v3, "v"

    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    if-nez v2, :cond_e

    return-object v1

    :cond_e
    const-string v3, "player"

    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    if-nez v3, :cond_17

    return-object v1

    :cond_17
    move-object v12, v3

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->ensureVisualSnapshot(Ljava/lang/Object;)V

    new-instance v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    invoke-direct {v4}, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;-><init>()V

    const-string v5, "getName"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeString(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v6

    if-eqz v6, :cond_41

    invoke-virtual {v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getLocalPlayerName()Ljava/lang/String;

    move-result-object v6

    if-eqz v6, :cond_41

    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v8

    if-nez v8, :cond_41

    iput-object v7, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    goto :goto_43

    :cond_41
    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    :goto_43
    const-string v5, "CurrentLevel"

    invoke-static {v2, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    const-string v5, "currentMapName"

    invoke-static {v2, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz v5, :cond_69

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v6

    if-eqz v6, :cond_6d

    :cond_69
    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    :cond_6d
    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    if-eqz v5, :cond_7b

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v6

    if-eqz v6, :cond_7f

    :cond_7b
    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    :cond_7f
    const-string v5, "slot"

    invoke-static {v2, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slot:I

    const-string v5, "x"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    const-string v5, "y"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    const-string v5, "speedX"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFloatField(Ljava/lang/Object;Ljava/lang/String;)F

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedX:F

    const-string v5, "speedY"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFloatField(Ljava/lang/Object;Ljava/lang/String;)F

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedY:F

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v5

    iput-wide v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    const-string v5, "gold"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->gold:I

    if-lez v5, :cond_b9

    sput v5, Lnet/fdgames/ek/android/lan/LanGameBridge;->localGoldBackup:I

    :cond_b9
    const-string v5, "portraitIndex"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->portraitIndex:I

    const-string v5, "gender"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureGenderName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    const-string v5, "facing"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->facingName:Ljava/lang/String;

    const-string v5, "d0"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeObject(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    const-string v5, "stateRelativeTime"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFloatField(Ljava/lang/Object;Ljava/lang/String;)F

    move-result v5

    const/high16 v6, 0x447a0000    # 1000.0f

    mul-float/2addr v5, v6

    float-to-int v5, v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stateTimeMs:I

    const-string v5, "animationSetName"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->joinStrings(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteName:Ljava/lang/String;

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->ensureVisualSnapshot(Ljava/lang/Object;)V

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureSpriteIndexCsv(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteIndexCsv:Ljava/lang/String;

    const-string v5, "sheet"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    const-string v5, "inventory"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    if-eqz v5, :cond_14c

    const-string v6, "slot_body"

    invoke-static {v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v6

    iput v6, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotBodyItemId:I

    const-string v6, "slot_feet"

    invoke-static {v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v6

    iput v6, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotFeetItemId:I

    const-string v6, "slot_hands"

    invoke-static {v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v6

    iput v6, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHandsItemId:I

    const-string v6, "slot_head"

    invoke-static {v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v6

    iput v6, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHeadItemId:I

    const-string v6, "slot_legs"

    invoke-static {v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v6

    iput v6, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotLegsItemId:I

    const-string v6, "slot_mainhand"

    invoke-static {v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v6

    iput v6, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotMainhandItemId:I

    const-string v6, "slot_offhand"

    invoke-static {v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotOffhandItemId:I

    :cond_14c
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->computeVisualFxMask(Ljava/lang/Object;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->visualFxMask:I

    const-string v5, "skillSet"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    if-eqz v5, :cond_16c

    const-string v6, "toString"

    invoke-static {v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeString(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    iput-object v6, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->skillSnapshot:Ljava/lang/String;

    const-string v6, "g"

    const-string v7, "stealth"

    invoke-static {v5, v6, v7}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stealthSkillLevel:I

    :cond_16c
    const-string v5, "effects"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildCombatEffectsSnapshot(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->combatEffectsSnapshot:Ljava/lang/String;

    const-string v5, "stats"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    const-string v5, "missingHP"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingHp:I

    const-string v5, "missingMana"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingMana:I

    const-string v5, "f"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v5

    iput v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->level:I

    const-string v5, "characterClass"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    iput-object v6, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->classEnumName:Ljava/lang/String;

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    const-string v5, "characterRace"

    invoke-static {v3, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->raceName:Ljava/lang/String;

    const-string v3, "gameVariables"

    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    const-string v3, "REP_varsilia"

    invoke-static {v2, v0, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I

    move-result v3

    iput v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repVarsilia:I

    const-string v3, "REP_mercia"

    invoke-static {v2, v0, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I

    move-result v3

    iput v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repMercia:I

    const-string v3, "REP_ilmara"

    invoke-static {v2, v0, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I

    move-result v3

    iput v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repIlmara:I

    const-string v3, "REP_wizardsguild"

    invoke-static {v2, v0, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I

    move-result v3

    iput v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repWizards:I

    const-string v3, "REP_the_three"

    invoke-static {v2, v0, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    iput v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repThree:I

    const-string v0, "spell_id"

    invoke-static {v12, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    iget v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionSeq:I

    sget v6, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastCapturedSpellSeq:I

    if-eq v3, v6, :cond_204

    sput v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastCapturedSpellSeq:I

    if-eqz v0, :cond_1ff

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_1ff

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastCapturedSpellId:Ljava/lang/String;

    goto :goto_206

    :cond_1ff
    const-string v0, ""

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastCapturedSpellId:Ljava/lang/String;

    goto :goto_206

    :cond_204
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastCapturedSpellId:Ljava/lang/String;

    :goto_206
    iput-object v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellId:Ljava/lang/String;

    const-string v0, "spellTarget"

    invoke-static {v12, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v0

    iput v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellTarget:I

    const-string v0, "skillOrigin"

    invoke-static {v12, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_229

    const-string v3, "x"

    invoke-static {v0, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v3

    iput v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    const-string v3, "y"

    invoke-static {v0, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v0

    iput v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    goto :goto_22e

    :cond_229
    const/4 v0, -0x1

    iput v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    iput v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    :goto_22e
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v3, "|"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellId:Ljava/lang/String;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v3, "|"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v3, "|"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v13

    iget v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    if-ltz v0, :cond_267

    const/4 v0, 0x1

    goto :goto_278

    :cond_267
    iget-object v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellId:Ljava/lang/String;

    if-eqz v0, :cond_277

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_277

    const/4 v0, 0x1

    goto :goto_278

    :cond_277
    const/4 v0, 0x0

    :goto_278
    if-eqz v0, :cond_297

    iget v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stateTimeMs:I

    const/16 v5, 0x4b0

    if-gt v3, v5, :cond_297

    sget-object v5, Lnet/fdgames/ek/android/lan/LanGameBridge;->localLastActionSignature:Ljava/lang/String;

    invoke-virtual {v13, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_28d

    sget v5, Lnet/fdgames/ek/android/lan/LanGameBridge;->localLastActionStateTimeMs:I

    if-lt v3, v5, :cond_28d

    goto :goto_297

    :cond_28d
    sget v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->localActionSeq:I

    add-int/lit8 v3, v3, 0x1

    sput v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->localActionSeq:I

    iget-object v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    sput-object v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->localLastActionStateName:Ljava/lang/String;

    :cond_297
    :goto_297
    sget v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->localActionSeq:I

    iput v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionSeq:I

    iget v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    if-ltz v3, :cond_2a5

    sget-object v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->localLastActionStateName:Ljava/lang/String;

    if-eqz v3, :cond_2a5

    iput-object v3, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    :cond_2a5
    if-eqz v0, :cond_2aa

    sput-object v13, Lnet/fdgames/ek/android/lan/LanGameBridge;->localLastActionSignature:Ljava/lang/String;

    goto :goto_2ae

    :cond_2aa
    const-string v0, ""

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localLastActionSignature:Ljava/lang/String;

    :goto_2ae
    iget v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stateTimeMs:I

    sput v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localLastActionStateTimeMs:I

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureLocalSummonState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureLocalCompanionState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureLocalFollowerStates(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    iget-object v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v0, :cond_2cd

    iget-object v0, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_2c9
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_2c9} :catch_2ce

    if-eqz v0, :cond_2cc

    goto :goto_2cd

    :cond_2cc
    return-object v4

    :cond_2cd
    :goto_2cd
    return-object v1

    :catch_2ce
    move-exception v0

    return-object v1
.end method

.method private static captureLocalSummonState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 5

    if-eqz p0, :cond_59

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v0

    if-eqz v0, :cond_59

    iget-object v0, v0, Lnet/fdgames/GameWorld/GameData;->party:Lnet/fdgames/GameWorld/Party;

    if-eqz v0, :cond_59

    invoke-virtual {v0}, Lnet/fdgames/GameWorld/Party;->i()Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v0

    if-eqz v0, :cond_59

    iget-object v1, v0, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    if-eqz v1, :cond_59

    iput-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpawnId:Ljava/lang/String;

    iget-object v1, v0, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    if-eqz v1, :cond_1e

    iput-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonTag:Ljava/lang/String;

    :cond_1e
    invoke-virtual {v0}, Lnet/fdgames/GameEntities/Character;->getName()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_26

    iput-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonName:Ljava/lang/String;

    :cond_26
    iget v1, v0, Lnet/fdgames/GameEntities/MapObject;->x:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonX:I

    iget v1, v0, Lnet/fdgames/GameEntities/MapObject;->y:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonY:I

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->ensureVisualSnapshot(Ljava/lang/Object;)V

    iget-object v1, v0, Lnet/fdgames/GameEntities/MapActor;->animationSetName:Ljava/util/ArrayList;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->joinStrings(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureSpriteIndexCsv(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteIndexCsv:Ljava/lang/String;

    iget-object v1, v0, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonFacingName:Ljava/lang/String;

    invoke-virtual {v0}, Lnet/fdgames/GameEntities/MapActor;->d0()Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v1

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonActorStateName:Ljava/lang/String;

    iget v0, v0, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    const/high16 v1, 0x447a0000    # 1000.0f

    mul-float/2addr v0, v1

    float-to-int v0, v0

    iput v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonStateTimeMs:I

    :cond_59
    return-void
.end method

.method private static captureSpriteIndexCsv(Ljava/lang/Object;)Ljava/lang/String;
    .registers 9

    const-string v0, ""

    if-nez p0, :cond_5

    return-object v0

    :cond_5
    :try_start_5
    const-string v1, "spriteIndex"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    if-nez p0, :cond_e

    return-object v0

    :cond_e
    const-string v1, "c"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v1

    if-gtz v1, :cond_17

    return-object v0

    :cond_17
    const-string v2, "b"

    invoke-static {p0, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    instance-of v2, p0, [Ljava/lang/Object;

    if-nez v2, :cond_22

    return-object v0

    :cond_22
    check-cast p0, [Ljava/lang/Object;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    array-length v3, p0

    const/4 v4, 0x0

    :goto_2b
    if-ge v4, v1, :cond_4c

    if-ge v4, v3, :cond_4c

    aget-object v5, p0, v4

    instance-of v6, v5, Ljava/lang/Number;

    if-eqz v6, :cond_49

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->length()I

    move-result v6

    if-lez v6, :cond_40

    const/16 v6, 0x2c

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    :cond_40
    check-cast v5, Ljava/lang/Number;

    invoke-virtual {v5}, Ljava/lang/Number;->intValue()I

    move-result v5

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    :cond_49
    add-int/lit8 v4, v4, 0x1

    goto :goto_2b

    :cond_4c
    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0
    :try_end_50
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_50} :catch_51

    return-object v0

    :catch_51
    move-exception p0

    return-object v0
.end method

.method private static cityGroup(Ljava/lang/String;)Ljava/lang/String;
    .registers 5

    const-string v0, ""

    if-nez p0, :cond_5

    return-object v0

    :cond_5
    nop

    const-string v1, "i.LXkY.rFUF"

    const-string v2, "ZTiRQlaiRSeCND"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getStaticFieldValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_15

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    goto :goto_16

    :cond_15
    move-object v1, v0

    :goto_16
    const-string v2, "NG"

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_1f

    return-object v2

    :cond_1f
    const-string v2, "FT"

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_28

    return-object v2

    :cond_28
    const-string v2, "NI"

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_31

    return-object v2

    :cond_31
    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_3e

    invoke-virtual {p0, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_3e

    return-object v1

    :cond_3e
    return-object v0
.end method

.method private static clearAppliedState(Ljava/lang/String;)V
    .registers 2

    if-eqz p0, :cond_2c

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_9

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_9
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedSampleTimes:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_10

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_10
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_17

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_17
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateLocks:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_1e

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1e
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateNames:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_25

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_25
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackFacingNames:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_2c

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_2c
    return-void
.end method

.method private static clearPeerActors()V
    .registers 4

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearWorldNpcActors()V

    const-string v0, ""

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastSyncedLevelId:Ljava/lang/String;

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    if-nez v0, :cond_c

    return-void

    :cond_c
    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_14
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3c

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v1, :cond_38

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v2

    iget-object v2, v2, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_38
    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    goto :goto_14

    :cond_3c
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActionSeqs:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_43

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_43
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCombatSeqs:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_4a

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_4a
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerMotionStates:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_51

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_51
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_58

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_58
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedSampleTimes:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_5f

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_5f
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_66

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_66
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateLocks:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_6d

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_6d
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateNames:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_74

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_74
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackFacingNames:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_7b

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_7b
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerVisualSignatures:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_82

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_82
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActorOwners:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_89

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_89
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->pendingPeerDamageProcs:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_90

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_90
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localFollowerSnapshots:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_97

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_97
    const/4 v0, 0x0

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCompanionSnapshot:Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonActors:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_ce

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_a6
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_ce

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v1, :cond_ca

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v2

    iget-object v2, v2, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_ca
    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    goto :goto_a6

    :cond_ce
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCompanionActors:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_102

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_da
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_102

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v1, :cond_fe

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v2

    iget-object v2, v2, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_fe
    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    goto :goto_da

    :cond_102
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerFollowerActors:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_136

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_10e
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_136

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v1, :cond_132

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v2

    iget-object v2, v2, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_132
    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    goto :goto_10e

    :cond_136
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonOwners:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_17a

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_142
    :goto_142
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_175

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Integer;

    if-eqz v1, :cond_142

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-static {v1}, Lnet/fdgames/GameLevel/GameLevel;->g(I)Lnet/fdgames/GameEntities/MapActor;

    move-result-object v1

    instance-of v2, v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v2, :cond_142

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v2

    iget-object v2, v2, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    goto :goto_142

    :cond_175
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonOwners:Ljava/util/LinkedHashMap;

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_17a
    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v0

    if-eqz v0, :cond_1a3

    iget-object v0, v0, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    if-eqz v0, :cond_1a3

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_188
    :goto_188
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1a3

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    iget-boolean v2, v1, Lnet/fdgames/GameEntities/Final/NPC;->lanPeerVisual:Z

    if-eqz v2, :cond_188

    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    goto :goto_188

    :cond_1a3
    sget v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->localGoldBackup:I

    if-lez v1, :cond_1c1

    const-string v2, "net.fdgames.GameWorld.GameData"

    const-string v3, "v"

    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    if-eqz v2, :cond_1c1

    const-string v3, "player"

    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    if-eqz v2, :cond_1c1

    const-string v3, "gold"

    invoke-static {v2, v3, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setIntField(Ljava/lang/Object;Ljava/lang/String;I)V

    const/4 v1, 0x0

    sput v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->localGoldBackup:I

    :cond_1c1
    return-void
.end method

.method private static clearWorldNpcActors()V
    .registers 4

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->worldNpcActors:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_21

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_c
    :goto_c
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1e

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v2, :cond_c

    const/4 v3, 0x0

    iput-boolean v3, v2, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    goto :goto_c

    :cond_1e
    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    :cond_21
    const/4 v0, 0x0

    sput-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastAppliedWorldNpcLevelId:Ljava/lang/String;

    return-void
.end method

.method private static collectActorIds()Ljava/util/HashSet;
    .registers 4

    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v1

    if-eqz v1, :cond_30

    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_14
    :goto_14
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_30

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    instance-of v3, v2, Lnet/fdgames/GameEntities/GameObject;

    if-eqz v3, :cond_14

    check-cast v2, Lnet/fdgames/GameEntities/GameObject;

    invoke-virtual {v2}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    goto :goto_14

    :cond_30
    return-object v0
.end method

.method private static computeStateRelativeTime(IJ)F
    .registers 8

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long v4, p1, v2

    if-gtz v4, :cond_b

    move-wide p1, v0

    :cond_b
    sub-long/2addr v0, p1

    cmp-long p1, v0, v2

    if-ltz p1, :cond_11

    goto :goto_12

    :cond_11
    move-wide v0, v2

    :goto_12
    const-wide/16 v2, 0x190

    cmp-long p1, v0, v2

    if-lez p1, :cond_19

    move-wide v0, v2

    :cond_19
    long-to-int p1, v0

    add-int/2addr p0, p1

    int-to-float p0, p0

    const/high16 p1, 0x447a0000    # 1000.0f

    div-float/2addr p0, p1

    return p0
.end method

.method private static computeVisualFxMask(Ljava/lang/Object;)I
    .registers 5

    const/4 v0, 0x0

    if-nez p0, :cond_4

    return v0

    :cond_4
    const-string v1, "effects"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    if-nez p0, :cond_d

    return v0

    :cond_d
    const-string v1, "holy_shielded"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_30

    const-string v1, "shielded"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_26

    const-string v1, "shieldBonus"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v1

    if-lez v1, :cond_26

    goto :goto_30

    :cond_26
    const-string v1, "mageArmor_Charges"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v1

    if-lez v1, :cond_2f

    goto :goto_30

    :cond_2f
    goto :goto_32

    :cond_30
    :goto_30
    or-int/lit8 v0, v0, 0x1

    :goto_32
    const-string v1, "stab"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_84

    const-string v1, "fury"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_84

    const-string v1, "might"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_84

    const-string v1, "rapid_fire"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_84

    const-string v1, "disintegrate"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_84

    const-string v1, "duel"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_84

    const-string v1, "flurry"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_84

    const-string v1, "rage"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_84

    const-string v1, "might_arbenos"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_84

    const-string v1, "might_prayer"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_83

    goto :goto_84

    :cond_83
    goto :goto_86

    :cond_84
    :goto_84
    or-int/lit8 v0, v0, 0x2

    :goto_86
    const-string v1, "bloodlust"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_90

    or-int/lit8 v0, v0, 0x4

    :cond_90
    const-string v1, "evasion"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_9a

    or-int/lit8 v0, v0, 0x8

    :cond_9a
    const-string v1, "resistances"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_be

    const-string v2, "d"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeObject(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asBoolean(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_b0

    or-int/lit8 v0, v0, 0x10

    :cond_b0
    const-string v2, "c"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeObject(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asBoolean(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_be

    or-int/lit8 v0, v0, 0x40

    :cond_be
    const-string v1, "stealth"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_c8

    or-int/lit8 v0, v0, 0x20

    :cond_c8
    const-string v1, "slowed"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_d2

    or-int/lit8 v0, v0, 0x40

    :cond_d2
    const-string v1, "stunned"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_dc

    or-int/lit16 v0, v0, 0x80

    :cond_dc
    return v0
.end method

.method private static consumeAppliedPeerProcCsv(Lnet/fdgames/GameEntities/Character;)Ljava/lang/String;
    .registers 4

    const-string v0, ""

    if-eqz p0, :cond_19

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->pendingPeerDamageProcs:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_19

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    if-eqz p0, :cond_19

    return-object p0

    :cond_19
    return-object v0
.end method

.method private static copyFollowerState(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;
    .registers 3

    const/4 v0, 0x0

    if-nez p0, :cond_4

    return-object v0

    :cond_4
    new-instance v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    invoke-direct {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;-><init>()V

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    iget p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    iput p0, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    return-object v0
.end method

.method private static createPeerActor(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;
    .registers 10

    const/4 v0, 0x0

    if-eqz p0, :cond_11d

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-nez v1, :cond_9

    goto/16 :goto_11d

    :cond_9
    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_15

    goto/16 :goto_11d

    :cond_15
    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getDisplayName(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_26

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_26

    move-object v1, v2

    :cond_26
    const-string v2, "varannari_warrior"

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->classEnumName:Ljava/lang/String;

    const-string v4, "ROGUE"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_35

    const-string v2, "human_archer"

    goto :goto_4a

    :cond_35
    const-string v4, "CLERIC"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_40

    const-string v2, "varannari_druid"

    goto :goto_4a

    :cond_40
    const-string v4, "WIZARD"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_4a

    const-string v2, "janod"

    :cond_4a
    :goto_4a
    invoke-static {v2}, Lnet/fdgames/Rules/Rules;->i(Ljava/lang/String;)Lnet/fdgames/Rules/Spawn;

    move-result-object v3

    if-nez v3, :cond_59

    const-string v2, "varannari_warrior"

    invoke-static {v2}, Lnet/fdgames/Rules/Rules;->i(Ljava/lang/String;)Lnet/fdgames/Rules/Spawn;

    move-result-object v3

    if-nez v3, :cond_59

    return-object v0

    :cond_59
    new-instance v4, Lnet/fdgames/Rules/Spawn;

    invoke-direct {v4, v3}, Lnet/fdgames/Rules/Spawn;-><init>(Lnet/fdgames/Rules/Spawn;)V

    invoke-virtual {v4, v1}, Lnet/fdgames/Rules/Spawn;->b(Ljava/lang/String;)V

    const-string v3, ""

    iput-object v3, v4, Lnet/fdgames/Rules/Spawn;->conversation_ID:Ljava/lang/String;

    iput-object v3, v4, Lnet/fdgames/Rules/Spawn;->lootTable:Ljava/lang/String;

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v5

    if-eqz v5, :cond_95

    iget-object v5, v5, Lnet/fdgames/GameWorld/GameData;->CurrentLevel:Ljava/lang/String;

    if-eqz v5, :cond_95

    const-string v6, "H10_pvp_arena"

    invoke-virtual {v5, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_95

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v5

    if-eqz v5, :cond_8c

    iget-object v6, v5, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    if-eqz v6, :cond_8c

    const-string v5, "pvp_arena_won"

    invoke-virtual {v6, v5}, Lnet/fdgames/GameWorld/GameVariables;->b(Ljava/lang/String;)I

    move-result v5

    const/4 v6, 0x1

    if-lt v5, v6, :cond_95

    :cond_8c
    const-string v3, "enemy"

    iput-object v3, v4, Lnet/fdgames/Rules/Spawn;->faction:Ljava/lang/String;

    const-string v3, "idle"

    iput-object v3, v4, Lnet/fdgames/Rules/Spawn;->AI_type:Ljava/lang/String;

    goto :goto_9d

    :cond_95
    const-string v3, "player"

    iput-object v3, v4, Lnet/fdgames/Rules/Spawn;->faction:Ljava/lang/String;

    const-string v3, "idle"

    iput-object v3, v4, Lnet/fdgames/Rules/Spawn;->AI_type:Ljava/lang/String;

    :goto_9d
    const/4 v3, 0x0

    iput v3, v4, Lnet/fdgames/Rules/Spawn;->wander:I

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->classEnumName:Ljava/lang/String;

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveCharacterClass(Ljava/lang/String;)Lnet/fdgames/Rules/Rules$CharacterClass;

    move-result-object v3

    iput-object v3, v4, Lnet/fdgames/Rules/Spawn;->characterclass:Lnet/fdgames/Rules/Rules$CharacterClass;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->raceName:Ljava/lang/String;

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveCharacterRace(Ljava/lang/String;)Lnet/fdgames/Rules/Rules$CharacterRace;

    move-result-object v3

    iput-object v3, v4, Lnet/fdgames/Rules/Spawn;->race:Lnet/fdgames/Rules/Rules$CharacterRace;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveGender(Ljava/lang/String;)Lnet/fdgames/GameEntities/Character$Gender;

    move-result-object v3

    iput-object v3, v4, Lnet/fdgames/Rules/Spawn;->gender:Lnet/fdgames/GameEntities/Character$Gender;

    iget v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->portraitIndex:I

    if-ltz v3, :cond_be

    iput v3, v4, Lnet/fdgames/Rules/Spawn;->portrait:I

    :cond_be
    iget v6, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotMainhandItemId:I

    if-ltz v6, :cond_c8

    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveWeaponStatsFromItemId(I)Lnet/fdgames/Rules/WeaponStats;

    move-result-object v6

    iput-object v6, v4, Lnet/fdgames/Rules/Spawn;->weaponStats:Lnet/fdgames/Rules/WeaponStats;

    :cond_c8
    iget v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->level:I

    const/4 v5, 0x1

    if-ge v3, v5, :cond_ce

    move v3, v5

    :cond_ce
    iput v3, v4, Lnet/fdgames/Rules/Spawn;->minlevel:I

    iput v3, v4, Lnet/fdgames/Rules/Spawn;->maxlevel:I

    new-instance v3, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-direct {v3, v4}, Lnet/fdgames/GameEntities/Final/NPC;-><init>(Lnet/fdgames/Rules/Spawn;)V

    const/4 v4, 0x1

    iput-boolean v4, v3, Lnet/fdgames/GameEntities/Final/NPC;->lanPeerVisual:Z

    invoke-static {v3, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->refreshPeerVisualFromSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-static {v3, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerVisualFxMask(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-static {v3, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerCombatSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-virtual {v3, v1}, Lnet/fdgames/GameEntities/Character;->r1(Ljava/lang/String;)V

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    iput v1, v3, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    iput v1, v3, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->facingName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v1

    iput-object v1, v3, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v1

    invoke-virtual {v3, v1}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    invoke-static {v3, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->refreshPeerVisualFromSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stateTimeMs:I

    int-to-float v1, v1

    const/high16 v2, 0x447a0000    # 1000.0f

    div-float/2addr v1, v2

    iput v1, v3, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    invoke-static {v3}, Lnet/fdgames/GameLevel/GameLevel;->a(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-virtual {v3}, Lnet/fdgames/GameEntities/Final/NPC;->B1()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v4

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    invoke-static {v4, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->trackPeerActorOwner(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;)V

    return-object v3

    :cond_11d
    :goto_11d
    return-object v0
.end method

.method private static createPeerCompanion(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;
    .registers 9

    const/4 v0, 0x0

    if-eqz p0, :cond_ba

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    if-nez v1, :cond_8

    return-object v0

    :cond_8
    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_13

    return-object v0

    :cond_13
    invoke-static {v1}, Lnet/fdgames/Rules/Rules;->i(Ljava/lang/String;)Lnet/fdgames/Rules/Spawn;

    move-result-object v2

    if-nez v2, :cond_1a

    return-object v0

    :cond_1a
    iget v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    if-nez v4, :cond_23

    iget v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    if-nez v4, :cond_23

    return-object v0

    :cond_23
    new-instance v3, Lnet/fdgames/Rules/Spawn;

    invoke-direct {v3, v2}, Lnet/fdgames/Rules/Spawn;-><init>(Lnet/fdgames/Rules/Spawn;)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    if-eqz v2, :cond_39

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_39

    invoke-virtual {v3, v2}, Lnet/fdgames/Rules/Spawn;->b(Ljava/lang/String;)V

    :cond_39
    const-string v2, ""

    iput-object v2, v3, Lnet/fdgames/Rules/Spawn;->conversation_ID:Ljava/lang/String;

    iput-object v2, v3, Lnet/fdgames/Rules/Spawn;->lootTable:Ljava/lang/String;

    const-string v4, "player"

    iput-object v4, v3, Lnet/fdgames/Rules/Spawn;->faction:Ljava/lang/String;

    const-string v4, "idle"

    iput-object v4, v3, Lnet/fdgames/Rules/Spawn;->AI_type:Ljava/lang/String;

    const/4 v4, 0x0

    iput v4, v3, Lnet/fdgames/Rules/Spawn;->wander:I

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    if-eqz v4, :cond_5a

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_5a

    iput-object v4, v3, Lnet/fdgames/Rules/Spawn;->spriteName:Ljava/lang/String;

    :cond_5a
    new-instance v4, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-direct {v4, v3}, Lnet/fdgames/GameEntities/Final/NPC;-><init>(Lnet/fdgames/Rules/Spawn;)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    if-eqz v3, :cond_70

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_70

    invoke-virtual {v4, v3}, Lnet/fdgames/GameEntities/Character;->r1(Ljava/lang/String;)V

    :cond_70
    iput-object v1, v4, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    iput v1, v4, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    iput v1, v4, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    if-eqz v1, :cond_80

    iput-object v1, v4, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    :cond_80
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v1

    iput-object v1, v4, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v1

    invoke-virtual {v4, v1}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionStateTimeMs:I

    int-to-float v1, v1

    const/high16 v3, 0x447a0000    # 1000.0f

    div-float/2addr v1, v3

    iput v1, v4, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    invoke-static {v4}, Lnet/fdgames/GameLevel/GameLevel;->a(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-virtual {v4}, Lnet/fdgames/GameEntities/Final/NPC;->B1()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    invoke-static {v4, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyAnimationSet(Lnet/fdgames/GameEntities/MapActor;Ljava/lang/String;)V

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    invoke-static {v4, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applySpriteIndexCsv(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;)V

    const/4 v1, 0x1

    iput-boolean v1, v4, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    iput-boolean v1, v4, Lnet/fdgames/GameEntities/Final/NPC;->summoned:Z

    sget-object v1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v1, v4, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    return-object v4

    :cond_ba
    return-object v0
.end method

.method private static createPeerFollower(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/GameEntities/Final/NPC;
    .registers 9

    const/4 v0, 0x0

    if-eqz p0, :cond_b1

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    if-nez v1, :cond_8

    return-object v0

    :cond_8
    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_13

    return-object v0

    :cond_13
    invoke-static {v1}, Lnet/fdgames/Rules/Rules;->i(Ljava/lang/String;)Lnet/fdgames/Rules/Spawn;

    move-result-object v2

    if-nez v2, :cond_1a

    return-object v0

    :cond_1a
    new-instance v3, Lnet/fdgames/Rules/Spawn;

    invoke-direct {v3, v2}, Lnet/fdgames/Rules/Spawn;-><init>(Lnet/fdgames/Rules/Spawn;)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    if-eqz v2, :cond_30

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_30

    invoke-virtual {v3, v2}, Lnet/fdgames/Rules/Spawn;->b(Ljava/lang/String;)V

    :cond_30
    const-string v2, ""

    iput-object v2, v3, Lnet/fdgames/Rules/Spawn;->conversation_ID:Ljava/lang/String;

    iput-object v2, v3, Lnet/fdgames/Rules/Spawn;->lootTable:Ljava/lang/String;

    const-string v4, "player"

    iput-object v4, v3, Lnet/fdgames/Rules/Spawn;->faction:Ljava/lang/String;

    const-string v4, "idle"

    iput-object v4, v3, Lnet/fdgames/Rules/Spawn;->AI_type:Ljava/lang/String;

    const/4 v4, 0x0

    iput v4, v3, Lnet/fdgames/Rules/Spawn;->wander:I

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    if-eqz v4, :cond_51

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_51

    iput-object v4, v3, Lnet/fdgames/Rules/Spawn;->spriteName:Ljava/lang/String;

    :cond_51
    new-instance v4, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-direct {v4, v3}, Lnet/fdgames/GameEntities/Final/NPC;-><init>(Lnet/fdgames/Rules/Spawn;)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    if-eqz v3, :cond_67

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_67

    invoke-virtual {v4, v3}, Lnet/fdgames/GameEntities/Character;->r1(Ljava/lang/String;)V

    :cond_67
    iput-object v1, v4, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iput v1, v4, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    iput v1, v4, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    if-eqz v1, :cond_77

    iput-object v1, v4, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    :cond_77
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v1

    iput-object v1, v4, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v1

    invoke-virtual {v4, v1}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    int-to-float v1, v1

    const/high16 v3, 0x447a0000    # 1000.0f

    div-float/2addr v1, v3

    iput v1, v4, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    invoke-static {v4}, Lnet/fdgames/GameLevel/GameLevel;->a(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-virtual {v4}, Lnet/fdgames/GameEntities/Final/NPC;->B1()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    invoke-static {v4, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyAnimationSet(Lnet/fdgames/GameEntities/MapActor;Ljava/lang/String;)V

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    invoke-static {v4, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applySpriteIndexCsv(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;)V

    const/4 v1, 0x1

    iput-boolean v1, v4, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    iput-boolean v1, v4, Lnet/fdgames/GameEntities/Final/NPC;->summoned:Z

    sget-object v1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v1, v4, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    return-object v4

    :cond_b1
    return-object v0
.end method

.method private static createPeerSummon(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;
    .registers 9

    const/4 v0, 0x0

    if-eqz p0, :cond_b1

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpawnId:Ljava/lang/String;

    if-nez v1, :cond_8

    return-object v0

    :cond_8
    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_13

    return-object v0

    :cond_13
    invoke-static {v1}, Lnet/fdgames/Rules/Rules;->i(Ljava/lang/String;)Lnet/fdgames/Rules/Spawn;

    move-result-object v2

    if-nez v2, :cond_1a

    return-object v0

    :cond_1a
    new-instance v3, Lnet/fdgames/Rules/Spawn;

    invoke-direct {v3, v2}, Lnet/fdgames/Rules/Spawn;-><init>(Lnet/fdgames/Rules/Spawn;)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonName:Ljava/lang/String;

    if-eqz v2, :cond_30

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_30

    invoke-virtual {v3, v2}, Lnet/fdgames/Rules/Spawn;->b(Ljava/lang/String;)V

    :cond_30
    const-string v2, ""

    iput-object v2, v3, Lnet/fdgames/Rules/Spawn;->conversation_ID:Ljava/lang/String;

    iput-object v2, v3, Lnet/fdgames/Rules/Spawn;->lootTable:Ljava/lang/String;

    const-string v4, "player"

    iput-object v4, v3, Lnet/fdgames/Rules/Spawn;->faction:Ljava/lang/String;

    const-string v4, "idle"

    iput-object v4, v3, Lnet/fdgames/Rules/Spawn;->AI_type:Ljava/lang/String;

    const/4 v4, 0x0

    iput v4, v3, Lnet/fdgames/Rules/Spawn;->wander:I

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    if-eqz v4, :cond_51

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_51

    iput-object v4, v3, Lnet/fdgames/Rules/Spawn;->spriteName:Ljava/lang/String;

    :cond_51
    new-instance v4, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-direct {v4, v3}, Lnet/fdgames/GameEntities/Final/NPC;-><init>(Lnet/fdgames/Rules/Spawn;)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonName:Ljava/lang/String;

    if-eqz v3, :cond_67

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_67

    invoke-virtual {v4, v3}, Lnet/fdgames/GameEntities/Character;->r1(Ljava/lang/String;)V

    :cond_67
    iput-object v1, v4, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonX:I

    iput v1, v4, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonY:I

    iput v1, v4, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonTag:Ljava/lang/String;

    if-eqz v1, :cond_77

    iput-object v1, v4, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    :cond_77
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonFacingName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v1

    iput-object v1, v4, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonActorStateName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v1

    invoke-virtual {v4, v1}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonStateTimeMs:I

    int-to-float v1, v1

    const/high16 v3, 0x447a0000    # 1000.0f

    div-float/2addr v1, v3

    iput v1, v4, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    invoke-static {v4}, Lnet/fdgames/GameLevel/GameLevel;->a(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-virtual {v4}, Lnet/fdgames/GameEntities/Final/NPC;->B1()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    invoke-static {v4, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyAnimationSet(Lnet/fdgames/GameEntities/MapActor;Ljava/lang/String;)V

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteIndexCsv:Ljava/lang/String;

    invoke-static {v4, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applySpriteIndexCsv(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;)V

    const/4 v1, 0x1

    iput-boolean v1, v4, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    iput-boolean v1, v4, Lnet/fdgames/GameEntities/Final/NPC;->summoned:Z

    sget-object v1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v1, v4, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    return-object v4

    :cond_b1
    return-object v0
.end method

.method private static createWorldNpcClone(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;II)Lnet/fdgames/GameEntities/Final/NPC;
    .registers 14

    const/4 v0, 0x0

    if-eqz p0, :cond_6f

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_e

    return-object v0

    :cond_e
    invoke-static {p0}, Lnet/fdgames/Rules/Rules;->i(Ljava/lang/String;)Lnet/fdgames/Rules/Spawn;

    move-result-object v1

    if-nez v1, :cond_15

    return-object v0

    :cond_15
    invoke-static {p0}, Lnet/fdgames/assets/AnimationLoader;->a(Ljava/lang/String;)V

    new-instance v2, Lnet/fdgames/Rules/Spawn;

    invoke-direct {v2, v1}, Lnet/fdgames/Rules/Spawn;-><init>(Lnet/fdgames/Rules/Spawn;)V

    new-instance v1, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-direct {v1, v2}, Lnet/fdgames/GameEntities/Final/NPC;-><init>(Lnet/fdgames/Rules/Spawn;)V

    if-eqz p3, :cond_30

    invoke-virtual {p3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p3}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_30

    iput-object p3, v1, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    :cond_30
    if-eqz p2, :cond_3e

    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_3e

    iput-object p2, v1, Lnet/fdgames/GameEntities/Final/NPC;->unique_tag:Ljava/lang/String;

    :cond_3e
    const/4 v2, 0x0

    if-lez p1, :cond_4b

    invoke-static {p1}, Lnet/fdgames/GameLevel/GameLevel;->h(I)Lnet/fdgames/GameEntities/MapObject;

    move-result-object v3

    if-eqz v3, :cond_4b

    invoke-virtual {v1, p1}, Lnet/fdgames/GameEntities/Final/NPC;->U1(I)V

    goto :goto_4e

    :cond_4b
    invoke-virtual {v1, v2}, Lnet/fdgames/GameEntities/Final/NPC;->U1(I)V

    :goto_4e
    iput p4, v1, Lnet/fdgames/GameEntities/MapObject;->x:I

    iput p5, v1, Lnet/fdgames/GameEntities/MapObject;->y:I

    sget-object v3, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v3, v1, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    const/4 v3, 0x1

    iput-boolean v3, v1, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    invoke-static {v1}, Lnet/fdgames/GameLevel/GameLevel;->a(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-virtual {v1}, Lnet/fdgames/GameEntities/Final/NPC;->B1()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v3

    if-eqz v3, :cond_6e

    invoke-virtual {v3, v1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_6e

    invoke-virtual {v3, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_6e
    return-object v1

    :cond_6f
    return-object v0
.end method

.method private static damageTypeCode(Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;)Ljava/lang/String;
    .registers 3

    const-string v0, "n"

    if-eqz p0, :cond_2d

    sget-object v1, Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;->c:Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;

    if-ne p0, v1, :cond_b

    const-string v0, "f"

    return-object v0

    :cond_b
    sget-object v1, Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;->d:Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;

    if-ne p0, v1, :cond_12

    const-string v0, "c"

    return-object v0

    :cond_12
    sget-object v1, Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;->e:Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;

    if-ne p0, v1, :cond_19

    const-string v0, "s"

    return-object v0

    :cond_19
    sget-object v1, Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;->f:Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;

    if-ne p0, v1, :cond_20

    const-string v0, "d"

    return-object v0

    :cond_20
    sget-object v1, Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;->g:Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;

    if-ne p0, v1, :cond_27

    const-string v0, "t"

    return-object v0

    :cond_27
    sget-object v1, Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;->h:Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;

    if-ne p0, v1, :cond_2d

    const-string v0, "sp"

    :cond_2d
    return-object v0
.end method

.method public static drawPeerMapPins(Lcom/badlogic/gdx/graphics/g2d/Batch;Lcom/badlogic/gdx/graphics/Texture;)V
    .registers 16

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_132

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v1

    if-eqz v1, :cond_132

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v1

    if-eqz v1, :cond_132

    iget-object v2, v1, Lnet/fdgames/GameWorld/GameData;->CurrentLevel:Ljava/lang/String;

    if-eqz v2, :cond_132

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v3

    if-eqz v3, :cond_132

    sget-object v4, Lnet/fdgames/assets/GameAssets;->f0:Lcom/badlogic/gdx/graphics/g2d/BitmapFont;

    if-eqz v4, :cond_132

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    const/4 v5, 0x0

    :goto_25
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_da

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    iget-object v7, v6, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz v7, :cond_d1

    invoke-virtual {v7, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_d1

    const/4 v7, 0x6

    rem-int v8, v5, v7

    packed-switch v8, :pswitch_data_138

    :pswitch_41
    const/4 v9, 0x0

    const v10, 0x3f666666    # 0.9f

    const/high16 v11, 0x3f800000    # 1.0f

    goto :goto_6f

    :pswitch_48
    const v9, 0x3e4ccccd    # 0.2f

    const/high16 v10, 0x3f800000    # 1.0f

    const v11, 0x3e4ccccd    # 0.2f

    goto :goto_6f

    :pswitch_51
    const/high16 v9, 0x3f800000    # 1.0f

    const/high16 v10, 0x3f000000    # 0.5f

    const/4 v11, 0x0

    goto :goto_6f

    :pswitch_57
    const/high16 v9, 0x3f800000    # 1.0f

    const/high16 v10, 0x3f800000    # 1.0f

    const/4 v11, 0x0

    goto :goto_6f

    :pswitch_5d
    const v9, 0x3f333333    # 0.7f

    const v10, 0x3e4ccccd    # 0.2f

    const/high16 v11, 0x3f800000    # 1.0f

    goto :goto_6f

    :pswitch_66
    const/high16 v9, 0x3f800000    # 1.0f

    const v10, 0x3ecccccd    # 0.4f

    const v11, 0x3f333333    # 0.7f

    goto :goto_6f

    :goto_6f
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v7, v6, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    sub-long v0, v0, v7

    const-wide/16 v7, 0xc8

    cmp-long v12, v0, v7

    if-lez v12, :cond_7e

    move-wide v0, v7

    :cond_7e
    long-to-float v0, v0

    const/high16 v1, 0x447a0000    # 1000.0f

    div-float/2addr v0, v1

    iget v12, v6, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    int-to-float v12, v12

    iget v1, v6, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedX:F

    mul-float v1, v1, v0

    add-float/2addr v12, v1

    invoke-static {v12}, Ljava/lang/Math;->round(F)I

    move-result v12

    iget v13, v6, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    int-to-float v13, v13

    iget v1, v6, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedY:F

    mul-float v1, v1, v0

    add-float/2addr v13, v1

    invoke-static {v13}, Ljava/lang/Math;->round(F)I

    move-result v13

    new-instance v7, Lnet/fdgames/TiledMap/Objects/Coords;

    invoke-direct {v7, v12, v13}, Lnet/fdgames/TiledMap/Objects/Coords;-><init>(II)V

    const/16 v8, 0x8c

    invoke-static {v8, v7}, Ly0/b;->A(ILnet/fdgames/TiledMap/Objects/Coords;)Lnet/fdgames/TiledMap/Objects/Coords;

    move-result-object v7

    iget v12, v7, Lnet/fdgames/TiledMap/Objects/Coords;->x:I

    add-int/lit8 v12, v12, 0x20

    int-to-float v12, v12

    iget v13, v7, Lnet/fdgames/TiledMap/Objects/Coords;->y:I

    int-to-float v13, v13

    const/high16 v7, 0x3f800000    # 1.0f

    invoke-interface {p0, v9, v10, v11, v7}, Lcom/badlogic/gdx/graphics/g2d/Batch;->setColor(FFFF)V

    invoke-interface {p0, p1, v12, v13}, Lcom/badlogic/gdx/graphics/g2d/Batch;->draw(Lcom/badlogic/gdx/graphics/Texture;FF)V

    const/high16 v7, 0x3f800000    # 1.0f

    invoke-interface {p0, v7, v7, v7, v7}, Lcom/badlogic/gdx/graphics/g2d/Batch;->setColor(FFFF)V

    iget-object v7, v6, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v7, :cond_d1

    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v8

    if-nez v8, :cond_d1

    const v8, 0x41200000    # 10.0f

    sub-float v12, v12, v8

    const v8, 0x41000000    # 8.0f

    add-float v13, v13, v8

    invoke-virtual {v4, p0, v7, v12, v13}, Lcom/badlogic/gdx/graphics/g2d/BitmapFont;->draw(Lcom/badlogic/gdx/graphics/g2d/Batch;Ljava/lang/CharSequence;FF)Lcom/badlogic/gdx/graphics/g2d/GlyphLayout;

    :cond_d1
    const/high16 v7, 0x3f800000    # 1.0f

    invoke-interface {p0, v7, v7, v7, v7}, Lcom/badlogic/gdx/graphics/g2d/Batch;->setColor(FFFF)V

    add-int/lit8 v5, v5, 0x1

    goto/16 :goto_25

    :cond_da
    const/high16 v7, 0x3f800000    # 1.0f

    invoke-interface {p0, v7, v7, v7, v7}, Lcom/badlogic/gdx/graphics/g2d/Batch;->setColor(FFFF)V

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_132

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v3

    if-eqz v3, :cond_132

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    const v5, 0x43200000    # 160.0f

    const v6, 0x41200000    # 10.0f

    :goto_f5
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_132

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    iget-object v8, v7, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz v8, :cond_131

    invoke-virtual {v8, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_131

    iget-object v8, v7, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    iget-object v9, v7, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    if-eqz v8, :cond_11a

    invoke-virtual {v10, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    :cond_11a
    const-string v11, ": "

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    if-eqz v9, :cond_126

    invoke-virtual {v10, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    :cond_126
    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v4, p0, v8, v6, v5}, Lcom/badlogic/gdx/graphics/g2d/BitmapFont;->draw(Lcom/badlogic/gdx/graphics/g2d/Batch;Ljava/lang/CharSequence;FF)Lcom/badlogic/gdx/graphics/g2d/GlyphLayout;

    const v11, 0x41800000    # 16.0f

    sub-float/2addr v5, v11

    :cond_131
    goto :goto_f5

    :cond_132
    const/high16 v0, 0x3f800000    # 1.0f

    invoke-interface {p0, v0, v0, v0, v0}, Lcom/badlogic/gdx/graphics/g2d/Batch;->setColor(FFFF)V

    return-void

    :pswitch_data_138
    .packed-switch 0x0
        :pswitch_41
        :pswitch_48
        :pswitch_51
        :pswitch_57
        :pswitch_5d
        :pswitch_66
    .end packed-switch
.end method

.method private static ensureActorSkillLevel(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;I)V
    .registers 7

    if-eqz p0, :cond_2e

    if-eqz p1, :cond_2e

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_2e

    const-string v0, "sheet"

    invoke-static {p0, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_2e

    const-string v1, "skillSet"

    invoke-static {v0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_2e

    const-string v1, "q"

    invoke-static {v0, v1, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I

    const/4 v1, 0x0

    :goto_24
    if-ge v1, p2, :cond_2e

    const-string v2, "j"

    invoke-static {v0, v2, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I

    add-int/lit8 v1, v1, 0x1

    goto :goto_24

    :cond_2e
    return-void
.end method

.method private static ensureVisualSnapshot(Ljava/lang/Object;)V
    .registers 2

    if-eqz p0, :cond_9

    :try_start_2
    const-string v0, "v0"

    invoke-static {p0, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeObject(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_7} :catch_8

    goto :goto_9

    :catch_8
    move-exception p0

    :cond_9
    :goto_9
    return-void
.end method

.method private static enumName(Ljava/lang/Object;)Ljava/lang/String;
    .registers 5

    const-string v0, ""

    if-nez p0, :cond_5

    return-object v0

    :cond_5
    :try_start_5
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const-string v2, "name"

    const/4 v3, 0x0

    new-array v3, v3, [Ljava/lang/Class;

    invoke-virtual {v1, v2, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    invoke-virtual {v1, p0, v3}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0
    :try_end_1d
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_1d} :catch_1e

    return-object v0

    :catch_1e
    move-exception v0

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static fillCompanionStateFromSnapshot(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)V
    .registers 3

    if-eqz p0, :cond_2c

    if-eqz p1, :cond_2c

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    iget v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iput v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    iget v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    iput v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    iget p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    iput p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionStateTimeMs:I

    :cond_2c
    return-void
.end method

.method private static findAuthoritativeWorldNpcByAuthId(Ljava/lang/String;)Lnet/fdgames/GameEntities/Final/NPC;
    .registers 7

    const/4 v0, 0x0

    if-eqz p0, :cond_55

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_e

    return-object v0

    :cond_e
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->worldNpcActors:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_2a

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v2, :cond_2a

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v3

    if-eqz v3, :cond_27

    invoke-virtual {v3, v2}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_27

    return-object v2

    :cond_27
    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_2a
    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v1

    if-eqz v1, :cond_55

    iget-object v1, v1, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    if-eqz v1, :cond_55

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_38
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_55

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->isAuthoritativeWorldNpc(Lnet/fdgames/GameEntities/Final/NPC;)Z

    move-result v3

    if-eqz v3, :cond_38

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveWorldNpcAuthId(Lnet/fdgames/GameEntities/Final/NPC;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p0, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_38

    return-object v2

    :cond_55
    return-object v0
.end method

.method private static findField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/lang/String;",
            ")",
            "Ljava/lang/reflect/Field;"
        }
    .end annotation

    nop

    :goto_1
    if-eqz p0, :cond_e

    :try_start_3
    invoke-virtual {p0, p1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object p0
    :try_end_7
    .catch Ljava/lang/NoSuchFieldException; {:try_start_3 .. :try_end_7} :catch_8

    return-object p0

    :catch_8
    move-exception v0

    invoke-virtual {p0}, Ljava/lang/Class;->getSuperclass()Ljava/lang/Class;

    move-result-object p0

    goto :goto_1

    :cond_e
    const/4 p0, 0x0

    return-object p0
.end method

.method private static findPeerCompanionCandidate(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;
    .registers 11

    const/4 v0, 0x0

    if-eqz p0, :cond_70

    if-eqz p1, :cond_70

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    if-eqz v1, :cond_70

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    if-eqz v2, :cond_70

    iget-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_15
    :goto_15
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_70

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    instance-of v5, v4, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v5, :cond_15

    check-cast v4, Lnet/fdgames/GameEntities/Final/NPC;

    if-ne v4, p0, :cond_28

    goto :goto_15

    :cond_28
    invoke-virtual {v4}, Lnet/fdgames/GameEntities/Final/NPC;->k0()Z

    move-result v5

    if-nez v5, :cond_34

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->isTrackedPeerExtraActor(Lnet/fdgames/GameEntities/Final/NPC;)Z

    move-result v5

    if-eqz v5, :cond_35

    :cond_34
    goto :goto_15

    :cond_35
    iget-object v5, v4, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    if-eqz v5, :cond_15

    invoke-virtual {v1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_15

    if-eqz v3, :cond_55

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_55

    iget-object v5, v4, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    if-eqz v5, :cond_15

    invoke-virtual {v3, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_15

    :cond_55
    iget v5, v4, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v6, p0, Lnet/fdgames/GameEntities/MapObject;->x:I

    sub-int/2addr v5, v6

    invoke-static {v5}, Ljava/lang/Math;->abs(I)I

    move-result v5

    const/16 v6, 0x180

    if-le v5, v6, :cond_63

    goto :goto_15

    :cond_63
    iget v5, v4, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget v7, p0, Lnet/fdgames/GameEntities/MapObject;->y:I

    sub-int/2addr v5, v7

    invoke-static {v5}, Ljava/lang/Math;->abs(I)I

    move-result v5

    if-le v5, v6, :cond_6f

    goto :goto_15

    :cond_6f
    return-object v4

    :cond_70
    return-object v0
.end method

.method private static findPeerSummonCandidate(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;
    .registers 11

    const/4 v0, 0x0

    if-eqz p0, :cond_70

    if-eqz p1, :cond_70

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpawnId:Ljava/lang/String;

    if-eqz v1, :cond_70

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    if-eqz v2, :cond_70

    iget-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonTag:Ljava/lang/String;

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_15
    :goto_15
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_70

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    instance-of v5, v4, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v5, :cond_15

    check-cast v4, Lnet/fdgames/GameEntities/Final/NPC;

    if-ne v4, p0, :cond_28

    goto :goto_15

    :cond_28
    invoke-virtual {v4}, Lnet/fdgames/GameEntities/Final/NPC;->k0()Z

    move-result v5

    if-nez v5, :cond_34

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->isTrackedPeerExtraActor(Lnet/fdgames/GameEntities/Final/NPC;)Z

    move-result v5

    if-eqz v5, :cond_35

    :cond_34
    goto :goto_15

    :cond_35
    iget-object v5, v4, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    if-eqz v5, :cond_15

    invoke-virtual {v1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_15

    if-eqz v3, :cond_55

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_55

    iget-object v5, v4, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    if-eqz v5, :cond_15

    invoke-virtual {v3, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_15

    :cond_55
    iget v5, v4, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v6, p0, Lnet/fdgames/GameEntities/MapObject;->x:I

    sub-int/2addr v5, v6

    invoke-static {v5}, Ljava/lang/Math;->abs(I)I

    move-result v5

    const/16 v6, 0x180

    if-le v5, v6, :cond_63

    goto :goto_15

    :cond_63
    iget v5, v4, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget v7, p0, Lnet/fdgames/GameEntities/MapObject;->y:I

    sub-int/2addr v5, v7

    invoke-static {v5}, Ljava/lang/Math;->abs(I)I

    move-result v5

    if-le v5, v6, :cond_6f

    goto :goto_15

    :cond_6f
    return-object v4

    :cond_70
    return-object v0
.end method

.method public static forcePublishLocalState()V
    .registers 2

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_15

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v1

    if-eqz v1, :cond_15

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureLocalState()Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    move-result-object v1

    if-eqz v1, :cond_15

    invoke-virtual {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->publishLiveState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    :cond_15
    return-void
.end method

.method private static getAppliedSampleTime(Ljava/lang/String;)J
    .registers 4

    const-wide/16 v0, -0x1

    if-eqz p0, :cond_14

    sget-object v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedSampleTimes:Ljava/util/LinkedHashMap;

    if-eqz v2, :cond_14

    invoke-virtual {v2, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Long;

    if-eqz p0, :cond_14

    invoke-virtual {p0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    :cond_14
    return-wide v0
.end method

.method private static getBooleanField(Ljava/lang/Object;Ljava/lang/String;)Z
    .registers 2

    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asBoolean(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method private static getCurrentPlayerName()Ljava/lang/String;
    .registers 4

    const-string v0, ""

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v1

    if-eqz v1, :cond_19

    invoke-virtual {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getLocalPlayerName()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_19

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_19

    return-object v2

    :cond_19
    :try_start_19
    const-string v1, "net.fdgames.GameWorld.GameData"

    const-string v2, "v"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-nez v1, :cond_24

    return-object v0

    :cond_24
    const-string v2, "player"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-nez v1, :cond_2d

    return-object v0

    :cond_2d
    const-string v2, "getName"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeString(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_40

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_40

    return-object v2
    :try_end_40
    .catch Ljava/lang/Exception; {:try_start_19 .. :try_end_40} :catch_41

    :cond_40
    return-object v0

    :catch_41
    move-exception v1

    return-object v0
.end method

.method private static getDisplayName(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;
    .registers 4

    const-string v0, ""

    if-nez p0, :cond_5

    return-object v0

    :cond_5
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    if-eqz v1, :cond_14

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_14

    return-object v1

    :cond_14
    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz p0, :cond_23

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_23

    return-object v1

    :cond_23
    return-object v0
.end method

.method public static getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    .registers 4

    const/4 v0, 0x0

    if-nez p0, :cond_4

    return-object v0

    :cond_4
    :try_start_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-static {v1, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->findField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object p1

    if-nez p1, :cond_f

    return-object v0

    :cond_f
    const/4 v1, 0x1

    invoke-virtual {p1, v1}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    invoke-virtual {p1, p0}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_17
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_17} :catch_18

    return-object p0

    :catch_18
    move-exception p0

    return-object v0
.end method

.method private static getFloatField(Ljava/lang/Object;Ljava/lang/String;)F
    .registers 3

    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    instance-of p1, p0, Ljava/lang/Number;

    if-eqz p1, :cond_f

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->floatValue()F

    move-result p0

    goto :goto_10

    :cond_f
    const/4 p0, 0x0

    :goto_10
    return p0
.end method

.method private static getIntField(Ljava/lang/Object;Ljava/lang/String;)I
    .registers 2

    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    instance-of p1, p0, Ljava/lang/Number;

    if-eqz p1, :cond_f

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->intValue()I

    move-result p0

    goto :goto_10

    :cond_f
    const/4 p0, 0x0

    :goto_10
    return p0
.end method

.method private static getOrCreatePeerActor(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;
    .registers 9

    const/4 v0, 0x0

    if-eqz p0, :cond_c7

    if-nez p1, :cond_7

    goto/16 :goto_c7

    :cond_7
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerDeadNames:Ljava/util/HashSet;

    if-eqz v0, :cond_e

    invoke-virtual {v0, p0}, Ljava/util/HashSet;->remove(Ljava/lang/Object;)Z

    :cond_e
    const/4 v0, 0x0

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildPeerVisualSignature(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v1

    sget-object v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    invoke-virtual {v2, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/GameEntities/Final/NPC;

    sget-object v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerVisualSignatures:Ljava/util/LinkedHashMap;

    invoke-virtual {v3, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    if-eqz v2, :cond_7b

    if-eqz v3, :cond_2d

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_5c

    :cond_2d
    invoke-virtual {v2}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v4

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removePeerSummonsForOwner(I)V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v4

    iget-object v4, v4, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v4, v2}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerVisualSignatures:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    const-string v4, "player"

    invoke-static {v4, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearAppliedState(Ljava/lang/String;)V

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->untrackPeerActorOwner(Lnet/fdgames/GameEntities/Final/NPC;)V

    const/4 v2, 0x0

    goto :goto_7b

    :cond_5c
    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerVisualSignatures:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, p0, v1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v4

    iget-object v4, v4, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v4, v2}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_8b

    invoke-static {v2}, Lnet/fdgames/GameLevel/GameLevel;->a(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-virtual {v2}, Lnet/fdgames/GameEntities/Final/NPC;->B1()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_8b

    :cond_7b
    :goto_7b
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->createPeerActor(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v2

    if-eqz v2, :cond_c7

    sget-object p1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    invoke-virtual {p1, p0, v2}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object p1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerVisualSignatures:Ljava/util/LinkedHashMap;

    invoke-virtual {p1, p0, v1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_8b
    :goto_8b
    if-eqz v2, :cond_c6

    invoke-static {p0, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->trackPeerActorOwner(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v3

    if-eqz v3, :cond_c6

    iget-object v3, v3, Lnet/fdgames/GameWorld/GameData;->CurrentLevel:Ljava/lang/String;

    if-eqz v3, :cond_c6

    const-string v4, "H10_pvp_arena"

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_c6

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v3

    if-eqz v3, :cond_b5

    iget-object v4, v3, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    if-eqz v4, :cond_b5

    const-string v3, "pvp_arena_won"

    invoke-virtual {v4, v3}, Lnet/fdgames/GameWorld/GameVariables;->b(Ljava/lang/String;)I

    move-result v3

    const/4 v4, 0x1

    if-lt v3, v4, :cond_be

    :cond_b5
    const-string v3, "enemy"

    invoke-static {v3}, Lnet/fdgames/GameWorld/WorldFactions;->i(Ljava/lang/String;)[I

    move-result-object v3

    iput-object v3, v2, Lnet/fdgames/GameEntities/GameObject;->worldfactions:[I

    goto :goto_c6

    :cond_be
    const-string v3, "player"

    invoke-static {v3}, Lnet/fdgames/GameWorld/WorldFactions;->i(Ljava/lang/String;)[I

    move-result-object v3

    iput-object v3, v2, Lnet/fdgames/GameEntities/GameObject;->worldfactions:[I

    :cond_c6
    :goto_c6
    return-object v2

    :cond_c7
    :goto_c7
    return-object v0
.end method

.method private static getPeerAttackStateTime(Ljava/lang/String;J)F
    .registers 10

    const/high16 v0, -0x40800000    # -1.0f

    if-eqz p0, :cond_37

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateLocks:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_37

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Long;

    if-eqz v2, :cond_37

    invoke-virtual {v2}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p1

    cmp-long v6, p1, v2

    if-gtz v6, :cond_26

    sub-long/2addr p1, v2

    const-wide/16 v2, 0x1f4

    add-long/2addr p1, v2

    long-to-float p0, p1

    const p1, 0x447a0000    # 1000.0f

    div-float/2addr p0, p1

    return p0

    :cond_26
    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateNames:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_30

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_30
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackFacingNames:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_37

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_37
    return v0
.end method

.method public static getPeerMarkerNames(Ljava/lang/String;)[Ljava/lang/String;
    .registers 7

    const/4 v0, 0x0

    if-eqz p0, :cond_79

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_79

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v1

    if-nez v1, :cond_16

    new-array p0, v0, [Ljava/lang/String;

    return-object p0

    :cond_16
    invoke-virtual {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v1

    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeAreasIsCity(Ljava/lang/String;)Z

    move-result v3

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_27
    :goto_27
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_6c

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    if-eqz v4, :cond_27

    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz v5, :cond_27

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_27

    if-eqz v3, :cond_4e

    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    invoke-static {p0, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->sameCityGroup(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_27

    goto :goto_56

    :cond_4e
    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    invoke-virtual {p0, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_27

    :goto_56
    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    if-eqz v5, :cond_64

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_64

    invoke-virtual {v2, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_27

    :cond_64
    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v5, :cond_27

    invoke-virtual {v2, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_27

    :cond_6c
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result p0

    new-array p0, p0, [Ljava/lang/String;

    invoke-virtual {v2, p0}, Ljava/util/ArrayList;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object p0

    check-cast p0, [Ljava/lang/String;

    return-object p0

    :cond_79
    new-array p0, v0, [Ljava/lang/String;

    return-object p0
.end method

.method public static getPeerMarkerPairs(Ljava/lang/String;F)[F
    .registers 8

    const/4 v0, 0x0

    if-eqz p0, :cond_95

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_f

    goto/16 :goto_95

    :cond_f
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v1

    if-nez v1, :cond_18

    new-array p0, v0, [F

    return-object p0

    :cond_18
    invoke-virtual {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/List;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_25

    new-array p0, v0, [F

    return-object p0

    :cond_25
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeAreasIsCity(Ljava/lang/String;)Z

    move-result v3

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_32
    :goto_32
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_78

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    if-eqz v4, :cond_32

    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz v5, :cond_32

    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_51

    goto :goto_32

    :cond_51
    if-eqz v3, :cond_5c

    iget-object v5, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    invoke-static {p0, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->sameCityGroup(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v5

    if-nez v5, :cond_5c

    goto :goto_32

    :cond_5c
    iget-object v4, v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    invoke-static {v4, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveMarker(Ljava/lang/String;F)[F

    move-result-object v4

    if-eqz v4, :cond_77

    aget v5, v4, v0

    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v5

    invoke-virtual {v2, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    const/4 v5, 0x1

    aget v4, v4, v5

    invoke-static {v4}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v4

    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_77
    goto :goto_32

    :cond_78
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result p0

    new-array p0, p0, [F

    nop

    :goto_7f
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result p1

    if-ge v0, p1, :cond_94

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    aput p1, p0, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_7f

    :cond_94
    return-object p0

    :cond_95
    :goto_95
    new-array p0, v0, [F

    return-object p0
.end method

.method public static getPeersForLevel(Ljava/lang/String;)Ljava/util/List;
    .registers 8

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    if-eqz p0, :cond_6a

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_12

    goto :goto_6a

    :cond_12
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v1

    if-eqz v1, :cond_6a

    invoke-virtual {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v2

    if-nez v2, :cond_1f

    goto :goto_6a

    :cond_1f
    invoke-virtual {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v1

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getCurrentPlayerName()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_2b
    :goto_2b
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_6a

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    if-eqz v3, :cond_2b

    iget-object v5, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v5, :cond_50

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    if-eqz v6, :cond_50

    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_50

    invoke-virtual {v6, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_50

    goto :goto_2b

    :cond_50
    iget-object v4, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz v4, :cond_2b

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_5f

    goto :goto_2b

    :cond_5f
    invoke-virtual {p0, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_66

    goto :goto_2b

    :cond_66
    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_2b

    :cond_6a
    :goto_6a
    return-object v0
.end method

.method private static getStaticFieldValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    .registers 3

    const/4 v0, 0x0

    :try_start_1
    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object p0

    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->findField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object p0

    if-nez p0, :cond_c

    return-object v0

    :cond_c
    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    invoke-virtual {p0, v0}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_14
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_14} :catch_15

    return-object p0

    :catch_15
    move-exception p0

    return-object v0
.end method

.method private static getWorldNpcSpawnKey(I)Ljava/lang/String;
    .registers 3

    const-string v0, ""

    if-lez p0, :cond_15

    invoke-static {p0}, Lnet/fdgames/GameLevel/GameLevel;->h(I)Lnet/fdgames/GameEntities/MapObject;

    move-result-object p0

    instance-of v1, p0, Lnet/fdgames/GameEntities/Final/MonsterSpawn;

    if-eqz v1, :cond_15

    check-cast p0, Lnet/fdgames/GameEntities/Final/MonsterSpawn;

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/Final/MonsterSpawn;->getName()Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_15

    return-object p0

    :cond_15
    return-object v0
.end method

.method private static hasPeerAttackStateLock(Ljava/lang/String;)Z
    .registers 7

    const/4 v0, 0x0

    if-eqz p0, :cond_32

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateLocks:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_32

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Long;

    if-eqz v2, :cond_32

    invoke-virtual {v2}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    cmp-long v1, v4, v2

    if-lez v1, :cond_31

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateLocks:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_22

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_22
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateNames:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_29

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_29
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackFacingNames:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_32

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    return v0

    :cond_31
    const/4 v0, 0x1

    :cond_32
    return v0
.end method

.method private static invokeAreasIsCity(Ljava/lang/String;)Z
    .registers 5

    const/4 v0, 0x0

    :try_start_1
    const-string v1, "net.fdgames.GameWorld.Areas"

    const-string v2, "j"

    const-class v3, Ljava/lang/String;

    invoke-static {v1, v2, v3, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    instance-of v1, p0, Ljava/lang/Boolean;

    if-eqz v1, :cond_18

    check-cast p0, Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0
    :try_end_15
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_15} :catch_19

    if-eqz p0, :cond_18

    const/4 v0, 0x1

    :cond_18
    return v0

    :catch_19
    move-exception p0

    return v0
.end method

.method private static invokeInt(Ljava/lang/Object;Ljava/lang/String;)I
    .registers 5

    const/4 v0, 0x0

    if-nez p0, :cond_4

    return v0

    :cond_4
    :try_start_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    new-array v2, v0, [Ljava/lang/Class;

    invoke-virtual {v1, p1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p1

    const/4 v1, 0x1

    invoke-virtual {p1, v1}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    new-array v1, v0, [Ljava/lang/Object;

    invoke-virtual {p1, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    instance-of p1, p0, Ljava/lang/Number;

    if-eqz p1, :cond_22

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->intValue()I

    move-result v0
    :try_end_22
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_22} :catch_23

    :cond_22
    return v0

    :catch_23
    move-exception p0

    return v0
.end method

.method private static invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I
    .registers 8

    const/4 v0, 0x0

    if-nez p0, :cond_4

    return v0

    :cond_4
    :try_start_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const/4 v2, 0x1

    new-array v3, v2, [Ljava/lang/Class;

    const-class v4, Ljava/lang/String;

    aput-object v4, v3, v0

    invoke-virtual {v1, p1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p1

    invoke-virtual {p1, v2}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    new-array v1, v2, [Ljava/lang/Object;

    aput-object p2, v1, v0

    invoke-virtual {p1, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    instance-of p1, p0, Ljava/lang/Number;

    if-eqz p1, :cond_28

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->intValue()I

    move-result v0
    :try_end_28
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_28} :catch_29

    :cond_28
    return v0

    :catch_29
    move-exception p0

    return v0
.end method

.method private static invokeObject(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    .registers 6

    const/4 v0, 0x0

    if-nez p0, :cond_4

    return-object v0

    :cond_4
    :try_start_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v1, p1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p1

    const/4 v1, 0x1

    invoke-virtual {p1, v1}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    new-array v1, v2, [Ljava/lang/Object;

    invoke-virtual {p1, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_19
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_19} :catch_1a

    return-object p0

    :catch_1a
    move-exception p0

    return-object v0
.end method

.method private static invokePrivate(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    .registers 5

    :try_start_0
    invoke-static {p1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Class;

    invoke-virtual {v0, p2, v1}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_15
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_15} :catch_15

    :catch_15
    return-void
.end method

.method private static invokeStatic(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    .registers 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object p0

    const/4 v0, 0x0

    new-array v1, v0, [Ljava/lang/Class;

    invoke-virtual {p0, p1, v1}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    const/4 p1, 0x0

    new-array v0, v0, [Ljava/lang/Object;

    invoke-virtual {p0, p1, v0}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method private static invokeStatic(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/lang/Object;",
            ")",
            "Ljava/lang/Object;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object p0

    const/4 v0, 0x1

    new-array v1, v0, [Ljava/lang/Class;

    const/4 v2, 0x0

    aput-object p2, v1, v2

    invoke-virtual {p0, p1, v1}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p0

    invoke-virtual {p0, v0}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    new-array p1, v0, [Ljava/lang/Object;

    aput-object p3, p1, v2

    const/4 p2, 0x0

    invoke-virtual {p0, p2, p1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method private static invokeString(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;
    .registers 6

    const-string v0, ""

    if-nez p0, :cond_5

    return-object v0

    :cond_5
    :try_start_5
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v1, p1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p1

    const/4 v1, 0x1

    invoke-virtual {p1, v1}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    new-array v1, v2, [Ljava/lang/Object;

    invoke-virtual {p1, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    if-nez p0, :cond_1d

    goto :goto_21

    :cond_1d
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0
    :try_end_21
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_21} :catch_22

    :goto_21
    return-object v0

    :catch_22
    move-exception p0

    return-object v0
.end method

.method private static isAuthoritativeWorldNpc(Lnet/fdgames/GameEntities/Final/NPC;)Z
    .registers 5

    const/4 v0, 0x0

    if-eqz p0, :cond_34

    iget-boolean v1, p0, Lnet/fdgames/GameEntities/Final/NPC;->lanPeerVisual:Z

    if-nez v1, :cond_34

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->isTrackedPeerExtraActor(Lnet/fdgames/GameEntities/Final/NPC;)Z

    move-result v1

    if-nez v1, :cond_34

    iget-boolean v1, p0, Lnet/fdgames/GameEntities/Final/NPC;->summoned:Z

    if-nez v1, :cond_34

    iget-object v1, p0, Lnet/fdgames/GameEntities/Final/NPC;->ai_type:Ljava/lang/String;

    if-eqz v1, :cond_1d

    const-string v2, "companion"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_34

    :cond_1d
    invoke-virtual {p0}, Lnet/fdgames/GameEntities/Final/NPC;->L1()I

    move-result v1

    if-lez v1, :cond_2d

    invoke-static {v1}, Lnet/fdgames/GameLevel/GameLevel;->h(I)Lnet/fdgames/GameEntities/MapObject;

    move-result-object v1

    instance-of v1, v1, Lnet/fdgames/GameEntities/Final/MonsterSpawn;

    if-eqz v1, :cond_2d

    const/4 v0, 0x1

    return v0

    :cond_2d
    invoke-virtual {p0}, Lnet/fdgames/GameEntities/GameObject;->s()Z

    move-result v1

    if-eqz v1, :cond_34

    const/4 v0, 0x1

    :cond_34
    return v0
.end method

.method public static isSessionRunning()Z
    .registers 2

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_b

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v1

    return v1

    :cond_b
    const/4 v1, 0x0

    return v1
.end method

.method private static isTrackedPeerExtraActor(Lnet/fdgames/GameEntities/Final/NPC;)Z
    .registers 3

    const/4 v0, 0x0

    if-eqz p0, :cond_22

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonActors:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_d

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->containsValue(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_21

    :cond_d
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCompanionActors:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_17

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->containsValue(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_21

    :cond_17
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerFollowerActors:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_22

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->containsValue(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_22

    :cond_21
    const/4 v0, 0x1

    :cond_22
    return v0
.end method

.method private static joinStrings(Ljava/lang/Object;)Ljava/lang/String;
    .registers 6

    const-string v0, ""

    if-nez p0, :cond_5

    return-object v0

    :cond_5
    instance-of v1, p0, Ljava/util/List;

    if-nez v1, :cond_e

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_e
    check-cast p0, Ljava/util/List;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_19
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_41

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_32

    goto :goto_19

    :cond_32
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->length()I

    move-result v3

    if-lez v3, :cond_3d

    const-string v3, ";"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3d
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_19

    :cond_41
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static lockPeerAttackState(Ljava/lang/String;)V
    .registers 6

    if-eqz p0, :cond_14

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateLocks:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_14

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    const-wide/16 v3, 0x1f4

    add-long/2addr v1, v3

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v0, p0, v1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_14
    return-void
.end method

.method public static mirrorCombatAction(Ljava/lang/String;IILjava/lang/String;F)V
    .registers 20

    move/from16 v6, p1

    move/from16 v7, p2

    if-eqz p0, :cond_e9

    const-string v13, "ATTACK"

    invoke-virtual {v13, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v14

    if-nez v14, :cond_18

    const-string v13, "CAST"

    invoke-virtual {v13, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v14

    if-nez v14, :cond_18

    goto/16 :goto_e9

    :cond_18
    if-ne v6, v7, :cond_e9

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_e9

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v14

    if-nez v14, :cond_28

    goto/16 :goto_e9

    :cond_28
    const-string v13, "net.fdgames.GameWorld.GameData"

    const-string v14, "v"

    invoke-static {v13, v14}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_e9

    const-string v13, "player"

    invoke-static {v1, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v12

    if-eqz v12, :cond_e9

    const-string v13, "uniqueID"

    invoke-static {v12, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v14

    if-ne v6, v14, :cond_e9

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getLocalPlayerName()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_53

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v14

    if-nez v14, :cond_53

    goto :goto_59

    :cond_53
    const-string v13, "getName"

    invoke-static {v12, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeString(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    :goto_59
    if-eqz v1, :cond_e9

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v14

    if-eqz v14, :cond_67

    goto/16 :goto_e9

    :cond_67
    sget v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCombatSeq:I

    add-int/lit8 v2, v2, 0x1

    sput v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCombatSeq:I

    move-object v3, p0

    const/high16 v13, 0x447a0000    # 1000.0f

    move/from16 v14, p4

    mul-float/2addr v13, v14

    invoke-static {v13}, Ljava/lang/Math;->round(F)I

    move-result v4

    const/4 v6, -0x1

    const/4 v7, -0x1

    const/4 v10, 0x0

    const/4 v11, 0x0

    const-string v13, "ATTACK"

    invoke-virtual {v13, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v14

    if-eqz v14, :cond_8c

    const-string v13, "lastTargetHit_id"

    invoke-static {v12, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v11

    const-string v5, ""

    goto :goto_be

    :cond_8c
    const-string v13, "spellTarget"

    invoke-static {v12, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v11

    const-string v13, "spell_id"

    invoke-static {v12, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v13

    invoke-static {v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    if-eqz v5, :cond_be

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v14

    if-nez v14, :cond_be

    const-string v13, "sheet"

    invoke-static {v12, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v13

    if-eqz v13, :cond_be

    const-string v14, "skillSet"

    invoke-static {v13, v14}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v13

    if-eqz v13, :cond_be

    const-string v14, "g"

    invoke-static {v13, v14, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I

    move-result v10

    :cond_be
    :goto_be
    const-string v13, "skillOrigin"

    invoke-static {v12, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v13

    if-eqz v13, :cond_d2

    const-string v14, "x"

    invoke-static {v13, v14}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v6

    const-string v14, "y"

    invoke-static {v13, v14}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v7

    :cond_d2
    const-string v13, "d0"

    invoke-static {v12, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeObject(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v13

    invoke-static {v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v8

    const-string v13, "facing"

    invoke-static {v12, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v13

    invoke-static {v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v9

    invoke-virtual/range {v0 .. v11}, Lnet/fdgames/ek/android/lan/LanSessionManager;->publishCombatAction(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;IILjava/lang/String;Ljava/lang/String;II)V

    :cond_e9
    :goto_e9
    return-void
.end method

.method private static normalizeGenderName(Ljava/lang/String;)Ljava/lang/String;
    .registers 3

    const-string v0, "male"

    if-eqz p0, :cond_29

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_f

    goto :goto_29

    :cond_f
    sget-object v1, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p0, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    const-string v1, "female"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_20

    const-string p0, "female"

    return-object p0

    :cond_20
    const-string v1, "male"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_29

    return-object v0

    :cond_29
    :goto_29
    return-object v0
.end method

.method private static normalizePeerAttackStateName(Ljava/lang/String;)Ljava/lang/String;
    .registers 3

    const-string v0, "ATTACKING"

    if-eqz p0, :cond_17

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_17

    const-string v1, "IDLE"

    invoke-virtual {v1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_17

    return-object p0

    :cond_17
    return-object v0
.end method

.method public static openChat()V
    .registers 3

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_c

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v1

    if-nez v1, :cond_d

    :cond_c
    return-void

    :cond_d
    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->markChatRead()V

    invoke-static {}, Lnet/fdgames/ek/ExiledKingdoms;->f()Lnet/fdgames/ek/IPlatformResolver;

    move-result-object v1

    instance-of v2, v1, Lnet/fdgames/ek/android/MainActivity;

    if-eqz v2, :cond_c

    check-cast v1, Lnet/fdgames/ek/android/MainActivity;

    new-instance v2, Lnet/fdgames/ek/android/lan/LanGameBridgeChatRunnable;

    invoke-direct {v2, v1, v0}, Lnet/fdgames/ek/android/lan/LanGameBridgeChatRunnable;-><init>(Lnet/fdgames/ek/android/MainActivity;Lnet/fdgames/ek/android/lan/LanSessionManager;)V

    invoke-virtual {v1, v2}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static openLobby()V
    .registers 2

    invoke-static {}, Lnet/fdgames/ek/ExiledKingdoms;->f()Lnet/fdgames/ek/IPlatformResolver;

    move-result-object v0

    instance-of v1, v0, Lnet/fdgames/ek/android/MainActivity;

    if-eqz v1, :cond_d

    check-cast v0, Lnet/fdgames/ek/android/MainActivity;

    invoke-virtual {v0}, Lnet/fdgames/ek/android/MainActivity;->u()V

    :cond_d
    return-void
.end method

.method private static parseBooleanString(Ljava/lang/String;)Z
    .registers 3

    const/4 v0, 0x0

    if-nez p0, :cond_4

    return v0

    :cond_4
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    const-string v1, "1"

    invoke-virtual {v1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_18

    const-string v1, "true"

    invoke-virtual {v1, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_19

    :cond_18
    const/4 v0, 0x1

    :cond_19
    return v0
.end method

.method public static postGameLog(Ljava/lang/String;)V
    .registers 8

    if-eqz p0, :cond_55

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_d

    goto :goto_55

    :cond_d
    const/4 v0, 0x0

    const/4 v1, 0x1

    :try_start_f
    const-string v2, "net.fdgames.GameWorld.GameVariables"

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    const-string v3, "g"

    new-array v4, v1, [Ljava/lang/Class;

    const-class v5, Ljava/lang/String;

    aput-object v5, v4, v0

    invoke-virtual {v2, v3, v4}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    new-array v3, v1, [Ljava/lang/Object;

    aput-object p0, v3, v0

    const/4 v4, 0x0

    invoke-virtual {v2, v4, v3}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_29
    .catch Ljava/lang/Exception; {:try_start_f .. :try_end_29} :catch_2a

    goto :goto_54

    :catch_2a
    move-exception v2

    :try_start_2b
    const-string v2, "net.fdgames.GameWorld.GameData"

    const-string v3, "v"

    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    const-string v3, "log"

    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    if-eqz v2, :cond_52

    invoke-virtual {v2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v3

    const-string v4, "a"

    new-array v5, v1, [Ljava/lang/Class;

    const-class v6, Ljava/lang/String;

    aput-object v6, v5, v0

    invoke-virtual {v3, v4, v5}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v3

    new-array v1, v1, [Ljava/lang/Object;

    aput-object p0, v1, v0

    invoke-virtual {v3, v2, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_52
    .catch Ljava/lang/Exception; {:try_start_2b .. :try_end_52} :catch_53

    :cond_52
    goto :goto_54

    :catch_53
    move-exception p0

    :goto_54
    return-void

    :cond_55
    :goto_55
    return-void
.end method

.method private static prepareWorldNpcLevel(Ljava/lang/String;)V
    .registers 3

    if-eqz p0, :cond_1a

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1a

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastAppliedWorldNpcLevelId:Ljava/lang/String;

    if-eqz v0, :cond_19

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_19

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearWorldNpcActors()V

    :cond_19
    return-void

    :cond_1a
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearWorldNpcActors()V

    return-void
.end method

.method public static publishLocalAttackStart(Lnet/fdgames/GameEntities/Character;I)V
    .registers 14

    if-eqz p0, :cond_77

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_77

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v1

    if-nez v1, :cond_f

    goto :goto_77

    :cond_f
    const-string v1, "net.fdgames.GameWorld.GameData"

    const-string v2, "v"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_77

    const-string v2, "player"

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_77

    const-string v2, "uniqueID"

    invoke-static {p0, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v3

    invoke-static {v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v1

    if-ne v3, v1, :cond_77

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getLocalPlayerName()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_3e

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_3e

    goto :goto_44

    :cond_3e
    const-string v2, "getName"

    invoke-static {p0, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeString(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    :goto_44
    if-eqz v1, :cond_77

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_51

    goto :goto_77

    :cond_51
    sget v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCombatSeq:I

    add-int/lit8 v2, v2, 0x1

    sput v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->localCombatSeq:I

    const-string v3, "ATTACK_START"

    const/4 v4, 0x0

    const-string v5, ""

    const/4 v6, -0x1

    const/4 v7, -0x1

    const-string v8, "d0"

    invoke-static {p0, v8}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeObject(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v8

    invoke-static {v8}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v8

    const-string v9, "facing"

    invoke-static {p0, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    invoke-static {v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->enumName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v9

    const/4 v10, 0x0

    move v11, p1

    invoke-virtual/range {v0 .. v11}, Lnet/fdgames/ek/android/lan/LanSessionManager;->publishCombatAction(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;IILjava/lang/String;Ljava/lang/String;II)V

    :cond_77
    :goto_77
    return-void
.end method

.method public static publishPeerDamageIfNeeded(Lnet/fdgames/GameEntities/Character;ILjava/lang/String;Ljava/lang/String;Lnet/fdgames/GameEntities/Helpers/DamageData;)V
    .registers 16

    move-object v2, p0

    move v3, p1

    move-object p1, p2

    move-object p2, p3

    move-object p3, p4

    if-eqz v2, :cond_91

    if-eqz p3, :cond_91

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_91

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v1

    if-eqz v1, :cond_21

    iget-object v1, v1, Lnet/fdgames/GameWorld/GameData;->CurrentLevel:Ljava/lang/String;

    if-eqz v1, :cond_21

    const-string p0, "H10_pvp_arena"

    invoke-virtual {v1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_21

    :cond_21
    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolvePeerOwnerName(Lnet/fdgames/GameEntities/Character;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_30

    goto :goto_91

    :cond_30
    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object p0

    if-eqz p0, :cond_91

    iget-object v4, p0, Lnet/fdgames/GameWorld/GameData;->CurrentLevel:Ljava/lang/String;

    if-eqz v4, :cond_44

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_4c

    :cond_44
    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object p0

    if-eqz p0, :cond_91

    iget-object v4, p0, Lnet/fdgames/GameWorld/GameData;->currentMapName:Ljava/lang/String;

    :cond_4c
    if-eqz v4, :cond_91

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_59

    goto :goto_91

    :cond_59
    if-eqz p1, :cond_67

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_67

    move-object v5, p1

    goto :goto_69

    :cond_67
    const-string v5, "HIT"

    :goto_69
    if-nez p2, :cond_6d

    const-string p2, ""

    :cond_6d
    invoke-static {p3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->serializeDamageCsv(Lnet/fdgames/GameEntities/Helpers/DamageData;)Ljava/lang/String;

    move-result-object v8

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->consumeAppliedPeerProcCsv(Lnet/fdgames/GameEntities/Character;)Ljava/lang/String;

    move-result-object v9

    const/4 v10, 0x0

    const/4 p0, 0x0

    iget-object p1, v2, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;

    if-eqz p1, :cond_83

    iget-object p1, p1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->stats:Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;

    if-eqz p1, :cond_83

    iget v10, p1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingHP:I

    iget p0, p1, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingMana:I

    :cond_83
    iget-boolean v6, p3, Lnet/fdgames/GameEntities/Helpers/DamageData;->critical:Z

    if-eqz v6, :cond_89

    const/4 v6, 0x1

    goto :goto_8a

    :cond_89
    const/4 v6, 0x0

    :goto_8a
    iget v7, p3, Lnet/fdgames/GameEntities/Helpers/DamageData;->weapon_item_id:I

    move-object v2, v4

    move-object v4, p2

    invoke-virtual/range {v0 .. v11}, Lnet/fdgames/ek/android/lan/LanSessionManager;->publishPlayerDamage(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;II)V

    :cond_91
    :goto_91
    return-void
.end method

.method public static receiveRemoteCombat(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;IILjava/lang/String;Ljava/lang/String;II)V
    .registers 27

    move-object/from16 v7, p0

    move/from16 v8, p1

    move-object/from16 v9, p2

    move/from16 v10, p3

    move/from16 v11, p9

    move/from16 v12, p10

    if-eqz v7, :cond_245

    if-nez v9, :cond_12

    goto/16 :goto_245

    :cond_12
    invoke-virtual {v7}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1e

    goto/16 :goto_245

    :cond_1e
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getCurrentPlayerName()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_9b

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_9b

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v0

    if-eqz v0, :cond_9a

    iget-object v1, v0, Lnet/fdgames/GameWorld/GameData;->CurrentLevel:Ljava/lang/String;

    if-eqz v1, :cond_9a

    const-string v2, "H10_pvp_arena"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_9a

    iget-object v1, v0, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    if-eqz v1, :cond_49

    const-string v2, "pvp_arena_won"

    invoke-virtual {v1, v2}, Lnet/fdgames/GameWorld/GameVariables;->b(Ljava/lang/String;)I

    move-result v2

    const/4 v3, 0x1

    if-lt v2, v3, :cond_9a

    :cond_49
    iget-object v1, v0, Lnet/fdgames/GameWorld/GameData;->player:Lnet/fdgames/GameEntities/Final/Player;

    if-eqz v1, :cond_9a

    iget-object v2, v1, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;

    if-eqz v2, :cond_9a

    iget-object v3, v2, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->stats:Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;

    if-eqz v3, :cond_9a

    move v4, v11

    invoke-virtual {v3}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->g()I

    move-result v5

    if-le v4, v5, :cond_5d

    move v4, v5

    :cond_5d
    iput v4, v3, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingHP:I

    if-lt v4, v5, :cond_96

    const/4 v4, 0x0

    iput v4, v3, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingHP:I

    iget-object v2, v0, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    if-eqz v2, :cond_6e

    const-string v3, "pvp_arena_won"

    const/4 v4, 0x0

    invoke-virtual {v2, v4, v3}, Lnet/fdgames/GameWorld/GameVariables;->e(ILjava/lang/String;)V

    :cond_6e
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v2

    if-eqz v2, :cond_96

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getCurrentPlayerName()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_96

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "[PVP] "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, " has been eliminated!"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendChat(Ljava/lang/String;)V

    :cond_96
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->forcePublishLocalState()V

    return-void

    :cond_9a
    return-void

    :cond_9b
    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    const/4 v6, 0x0

    if-eqz v0, :cond_a6

    invoke-virtual {v0, v7}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lnet/fdgames/GameEntities/Final/NPC;

    :cond_a6
    if-nez v6, :cond_c8

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_c8

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v1

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureLocalState()Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    move-result-object v2

    const/4 v3, 0x0

    if-eqz v2, :cond_bb

    iget-object v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    :cond_bb
    invoke-static {v1, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->syncPeerActors(Ljava/util/List;Ljava/lang/String;)V

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_c8

    invoke-virtual {v1, v7}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lnet/fdgames/GameEntities/Final/NPC;

    :cond_c8
    if-eqz v6, :cond_245

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCombatSeqs:Ljava/util/LinkedHashMap;

    const/4 v1, 0x0

    if-eqz v0, :cond_db

    invoke-virtual {v0, v7}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    if-eqz v2, :cond_db

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v1

    :cond_db
    if-lt v1, v8, :cond_de

    return-void

    :cond_de
    if-eqz v0, :cond_e7

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v7, v1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_e7
    const-string v0, "ATTACK_START"

    invoke-virtual {v0, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v13

    const-string v0, "ATTACK"

    invoke-virtual {v0, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    move v15, v1

    if-eqz v13, :cond_123

    invoke-static {v7, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->refreshPeerEquipmentFromLatestSnapshot(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;)V

    if-eqz v6, :cond_122

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v2

    if-eqz v2, :cond_122

    iget-object v2, v2, Lnet/fdgames/GameWorld/GameData;->player:Lnet/fdgames/GameEntities/Final/Player;

    if-eqz v2, :cond_122

    iget v3, v2, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v2, v2, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget v0, v6, Lnet/fdgames/GameEntities/MapObject;->x:I

    sub-int v0, v0, v3

    if-gez v0, :cond_110

    neg-int v0, v0

    :cond_110
    iget v3, v6, Lnet/fdgames/GameEntities/MapObject;->y:I

    sub-int v3, v3, v2

    if-gez v3, :cond_117

    neg-int v3, v3

    :cond_117
    add-int v0, v0, v3

    const/16 v3, 0x280

    if-gt v0, v3, :cond_122

    const-string v0, "swing"

    invoke-static {v0}, Lnet/fdgames/assets/GameAssets;->o(Ljava/lang/String;)V

    :cond_122
    goto :goto_151

    :cond_123
    if-eqz v1, :cond_151

    invoke-static {v7, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->refreshPeerEquipmentFromLatestSnapshot(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;)V

    if-eqz v6, :cond_151

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v2

    if-eqz v2, :cond_151

    iget-object v2, v2, Lnet/fdgames/GameWorld/GameData;->player:Lnet/fdgames/GameEntities/Final/Player;

    if-eqz v2, :cond_151

    iget v3, v2, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v2, v2, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget v0, v6, Lnet/fdgames/GameEntities/MapObject;->x:I

    sub-int v0, v0, v3

    if-gez v0, :cond_13f

    neg-int v0, v0

    :cond_13f
    iget v3, v6, Lnet/fdgames/GameEntities/MapObject;->y:I

    sub-int v3, v3, v2

    if-gez v3, :cond_146

    neg-int v3, v3

    :cond_146
    add-int v0, v0, v3

    const/16 v3, 0x280

    if-gt v0, v3, :cond_151

    const-string v0, "hit"

    invoke-static {v0}, Lnet/fdgames/assets/GameAssets;->o(Ljava/lang/String;)V

    :cond_151
    :goto_151
    if-eqz v13, :cond_15c

    iput v12, v6, Lnet/fdgames/GameEntities/Character;->lastTargetHit_id:I

    const/4 v0, 0x0

    const-string v1, "spellTarget"

    invoke-static {v6, v1, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setIntField(Ljava/lang/Object;Ljava/lang/String;I)V

    goto :goto_16c

    :cond_15c
    if-eqz v1, :cond_167

    iput v12, v6, Lnet/fdgames/GameEntities/Character;->lastTargetHit_id:I

    const/4 v0, 0x0

    const-string v1, "spellTarget"

    invoke-static {v6, v1, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setIntField(Ljava/lang/Object;Ljava/lang/String;I)V

    goto :goto_16c

    :cond_167
    const-string v0, "spellTarget"

    invoke-static {v6, v0, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setIntField(Ljava/lang/Object;Ljava/lang/String;I)V

    :goto_16c
    move-object/from16 v0, p4

    if-eqz v0, :cond_177

    iput-object v0, v6, Lnet/fdgames/GameEntities/Character;->spell_id:Ljava/lang/String;

    if-lez v11, :cond_177

    invoke-static {v6, v0, v11}, Lnet/fdgames/ek/android/lan/LanGameBridge;->ensureActorSkillLevel(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;I)V

    :cond_177
    move/from16 v0, p5

    move/from16 v1, p6

    if-ltz v0, :cond_18d

    iget-object v2, v6, Lnet/fdgames/GameEntities/Character;->skillOrigin:Lnet/fdgames/TiledMap/Objects/Coords;

    if-nez v2, :cond_189

    new-instance v2, Lnet/fdgames/TiledMap/Objects/Coords;

    invoke-direct {v2, v0, v1}, Lnet/fdgames/TiledMap/Objects/Coords;-><init>(II)V

    iput-object v2, v6, Lnet/fdgames/GameEntities/Character;->skillOrigin:Lnet/fdgames/TiledMap/Objects/Coords;

    goto :goto_18d

    :cond_189
    iput v0, v2, Lnet/fdgames/TiledMap/Objects/Coords;->x:I

    iput v1, v2, Lnet/fdgames/TiledMap/Objects/Coords;->y:I

    :cond_18d
    :goto_18d
    if-gez v0, :cond_192

    const/4 v1, 0x0

    iput-object v1, v6, Lnet/fdgames/GameEntities/Character;->skillOrigin:Lnet/fdgames/TiledMap/Objects/Coords;

    :cond_192
    move-object/from16 v2, p4

    if-eqz v2, :cond_1a8

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_1a8

    if-ltz v0, :cond_1a1

    if-ltz v1, :cond_1a1

    goto :goto_1a5

    :cond_1a1
    iget v0, v6, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v1, v6, Lnet/fdgames/GameEntities/MapObject;->y:I

    :goto_1a5
    invoke-static {v2, v0, v1, v11}, Lnet/fdgames/ek/android/lan/LanGameBridge;->spawnVisualEffect(Ljava/lang/String;III)V

    :cond_1a8
    move-object/from16 v0, p8

    if-eqz v0, :cond_1bc

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_1bc

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v1

    iput-object v1, v6, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    :cond_1bc
    move-object/from16 v0, p7

    if-nez v13, :cond_1f9

    if-nez v15, :cond_212

    if-eqz v0, :cond_212

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_212

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v2

    invoke-virtual {v6, v2}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    const/4 v2, 0x0

    iput v2, v6, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    iget-object v2, v6, Lnet/fdgames/GameEntities/MapActor;->animationSetName:Ljava/util/ArrayList;

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->joinStrings(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureSpriteIndexCsv(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v14

    if-eqz v2, :cond_1f2

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_1f2

    invoke-static {v6, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyAnimationSet(Lnet/fdgames/GameEntities/MapActor;Ljava/lang/String;)V

    goto :goto_1f5

    :cond_1f2
    invoke-virtual {v6}, Lnet/fdgames/GameEntities/MapActor;->v0()V

    :goto_1f5
    invoke-static {v6, v14}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applySpriteIndexCsv(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;)V

    goto :goto_212

    :cond_1f9
    move-object/from16 v0, p7

    move-object/from16 v1, p8

    invoke-static {v7, v0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->rememberPeerAttackStart(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "player"

    invoke-static {v0, v7}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    invoke-static {v0, v6, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerAttackVisualLock(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;F)V

    const/4 v1, 0x1

    iput-boolean v1, v6, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v0, v6, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    return-void

    :cond_212
    :goto_212
    sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v0, v6, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    if-eqz v15, :cond_232

    const-string v0, "player"

    invoke-static {v0, v7}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->hasPeerAttackStateLock(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_232

    move-object/from16 v1, p7

    move-object/from16 v2, p8

    invoke-static {v7, v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->rememberPeerAttackStart(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const/4 v1, 0x0

    invoke-static {v0, v6, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerAttackVisualLock(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;F)V

    const/4 v1, 0x1

    iput-boolean v1, v6, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    :cond_232
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->collectActorIds()Ljava/util/HashSet;

    move-result-object v13

    move-object v0, v9

    invoke-virtual {v6}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v1

    move v2, v1

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-static/range {v0 .. v5}, Lnet/fdgames/GameWorld/MessageRouter;->a(Ljava/lang/String;IILjava/lang/String;FLnet/fdgames/GameEntities/Helpers/DamageData;)V

    invoke-static {v13, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->bindNewPeerSummons(Ljava/util/HashSet;Lnet/fdgames/GameEntities/Final/NPC;)V

    :cond_245
    :goto_245
    return-void
.end method

.method public static recordAppliedPeerProc(Lnet/fdgames/GameEntities/Character;Lnet/fdgames/GameEntities/Helpers/DamageEffect;)V
    .registers 9

    if-eqz p0, :cond_65

    if-eqz p1, :cond_65

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_65

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isHosting()Z

    move-result v0

    if-eqz v0, :cond_65

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolvePeerOwnerName(Lnet/fdgames/GameEntities/Character;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1b

    goto :goto_65

    :cond_1b
    iget-object v1, p1, Lnet/fdgames/GameEntities/Helpers/DamageEffect;->type:Lnet/fdgames/GameEntities/Helpers/DamageEffect$EffectType;

    if-eqz v1, :cond_65

    sget-object v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->pendingPeerDamageProcs:Ljava/util/LinkedHashMap;

    if-eqz v2, :cond_65

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    if-nez v4, :cond_35

    const-string v4, ""

    :cond_35
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v6

    if-lez v6, :cond_4a

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v4, ";"

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    :cond_4a
    invoke-virtual {v1}, Ljava/lang/Enum;->name()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v4, ":"

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget p1, p1, Lnet/fdgames/GameEntities/Helpers/DamageEffect;->level:I

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v2, v3, p1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_65
    :goto_65
    return-void
.end method

.method public static refreshChatDialogView(Lnet/fdgames/ek/android/lan/LanSessionManager;Landroid/widget/TextView;Landroid/widget/ScrollView;)V
    .registers 8

    if-eqz p0, :cond_3a

    if-nez p1, :cond_5

    goto :goto_3a

    :cond_5
    invoke-virtual {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getChatSnapshot()Ljava/util/List;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_12
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_28

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const/16 v3, 0xa

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_12

    :cond_28
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    if-eqz p2, :cond_3a

    const/16 v0, 0x82

    invoke-virtual {p2, v0}, Landroid/widget/ScrollView;->fullScroll(I)Z

    :cond_3a
    :goto_3a
    return-void
.end method

.method private static refreshPeerEquipmentFromLatestSnapshot(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;)V
    .registers 5

    if-eqz p0, :cond_36

    if-nez p1, :cond_5

    goto :goto_36

    :cond_5
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_36

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v1

    if-nez v1, :cond_12

    goto :goto_36

    :cond_12
    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_1a
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_36

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    if-eqz v1, :cond_1a

    iget-object v2, v1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v2, :cond_1a

    invoke-virtual {v2, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1a

    invoke-static {p1, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerEquipmentSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    return-void

    :cond_36
    :goto_36
    return-void
.end method

.method private static refreshPeerVisualFromSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 5

    if-eqz p0, :cond_44

    if-nez p1, :cond_5

    goto :goto_44

    :cond_5
    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerEquipmentSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveGender(Ljava/lang/String;)Lnet/fdgames/GameEntities/Character$Gender;

    move-result-object v0

    iput-object v0, p0, Lnet/fdgames/GameEntities/Character;->gender:Lnet/fdgames/GameEntities/Character$Gender;

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/Final/NPC;->v0()V

    iget-boolean v0, p0, Lnet/fdgames/GameEntities/Final/NPC;->lanPeerVisual:Z

    if-eqz v0, :cond_20

    iget-object v0, p0, Lnet/fdgames/GameEntities/Character;->spriteIndex:Lcom/badlogic/gdx/utils/a;

    if-eqz v0, :cond_20

    iget v1, v0, Lcom/badlogic/gdx/utils/a;->c:I

    if-lez v1, :cond_20

    goto :goto_44

    :cond_20
    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteIndexCsv:Ljava/lang/String;

    if-eqz v0, :cond_32

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_32

    invoke-static {p0, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applySpriteIndexCsv(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;)V

    goto :goto_44

    :cond_32
    iget-object p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteName:Ljava/lang/String;

    if-eqz p1, :cond_44

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_44

    invoke-static {p0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyAnimationSet(Lnet/fdgames/GameEntities/MapActor;Ljava/lang/String;)V

    goto :goto_44

    :cond_44
    :goto_44
    return-void
.end method

.method private static rememberPeerAttackStart(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .registers 9

    if-eqz p0, :cond_50

    const-string v0, "player"

    invoke-static {v0, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->lockPeerAttackState(Ljava/lang/String;)V

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->normalizePeerAttackStateName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackStateNames:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_16

    invoke-virtual {v1, v0, p1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_16
    const/4 v5, 0x0

    if-eqz p2, :cond_2c

    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_2c

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackFacingNames:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_2a

    invoke-virtual {v1, v0, v5}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_2a
    move-object p2, v5

    goto :goto_34

    :cond_2c
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAttackFacingNames:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_33

    invoke-virtual {v1, v0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_33
    move-object p2, v5

    :goto_34
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActionSeqs:Ljava/util/LinkedHashMap;

    const/4 v2, 0x0

    if-eqz v1, :cond_45

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Integer;

    if-eqz v3, :cond_45

    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v2

    :cond_45
    invoke-static {p1, p2, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildAnimationKey(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v2

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_50

    invoke-virtual {v1, v0, v2}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_50
    return-void
.end method

.method private static removeAbsentAuthoritativeWorldNpcs(Ljava/util/HashSet;)V
    .registers 7

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->worldNpcActors:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_2a

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_c
    :goto_c
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2a

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    if-eqz p0, :cond_26

    invoke-virtual {p0, v2}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_c

    :cond_26
    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    goto :goto_c

    :cond_2a
    return-void

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v0

    if-eqz v0, :cond_6b

    iget-object v0, v0, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    if-eqz v0, :cond_6b

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_39
    :goto_39
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_6b

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->isAuthoritativeWorldNpc(Lnet/fdgames/GameEntities/Final/NPC;)Z

    move-result v2

    if-eqz v2, :cond_39

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveWorldNpcAuthId(Lnet/fdgames/GameEntities/Final/NPC;)Ljava/lang/String;

    move-result-object v2

    if-eqz p0, :cond_57

    invoke-virtual {p0, v2}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_39

    :cond_57
    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v3

    if-eqz v3, :cond_63

    invoke-virtual {v3, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_63
    sget-object v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->worldNpcActors:Ljava/util/LinkedHashMap;

    if-eqz v3, :cond_39

    invoke-virtual {v3, v2}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_39

    :cond_6b
    return-void
.end method

.method private static removeAllPeerFollowers(Ljava/lang/String;)V
    .registers 6

    if-eqz p0, :cond_62

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerFollowerActors:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_62

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, "\t"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_25
    :goto_25
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_62

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    move-object v3, v2

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    if-eqz v2, :cond_25

    invoke-virtual {v2, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_25

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v1, :cond_5b

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v2

    iget-object v2, v2, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_5b
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearAppliedState(Ljava/lang/String;)V

    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    goto :goto_25

    :cond_62
    return-void
.end method

.method private static removeNpcFromLevel(Lnet/fdgames/GameEntities/Final/NPC;)V
    .registers 2

    if-eqz p0, :cond_18

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v0

    if-eqz v0, :cond_f

    iget-object v0, v0, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    if-eqz v0, :cond_f

    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_f
    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v0

    if-eqz v0, :cond_18

    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_18
    return-void
.end method

.method private static removePeerCompanion(Ljava/lang/String;)V
    .registers 6

    if-eqz p0, :cond_36

    const-string v0, "companion"

    invoke-static {v0, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearAppliedState(Ljava/lang/String;)V

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCompanionActors:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_36

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v2, :cond_36

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v1

    iget-object v1, v1, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonOwners:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_36

    invoke-virtual {v2}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_36
    return-void
.end method

.method private static removePeerFollower(Ljava/lang/String;)V
    .registers 4

    if-eqz p0, :cond_21

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearAppliedState(Ljava/lang/String;)V

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerFollowerActors:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_21

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v1, :cond_21

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v0

    iget-object v0, v0, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_21
    return-void
.end method

.method private static removePeerSummon(Ljava/lang/String;)V
    .registers 6

    if-eqz p0, :cond_36

    const-string v0, "summon"

    invoke-static {v0, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearAppliedState(Ljava/lang/String;)V

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonActors:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_36

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v2, :cond_36

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v1

    iget-object v1, v1, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonOwners:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_36

    invoke-virtual {v2}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_36
    return-void
.end method

.method private static removePeerSummonsForOwner(I)V
    .registers 7

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonOwners:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_50

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_c
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_50

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    if-eqz v2, :cond_4f

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    if-ne v2, p0, :cond_4f

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    if-eqz v2, :cond_4c

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    invoke-static {v2}, Lnet/fdgames/GameLevel/GameLevel;->g(I)Lnet/fdgames/GameEntities/MapActor;

    move-result-object v3

    instance-of v4, v3, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v4, :cond_4c

    check-cast v3, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v4

    iget-object v4, v4, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v4

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_4c
    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    :cond_4f
    goto :goto_c

    :cond_50
    return-void
.end method

.method private static resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;
    .registers 2

    if-eqz p0, :cond_12

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_12

    :try_start_c
    invoke-static {p0}, Lnet/fdgames/GameEntities/MapActor$ActorState;->valueOf(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object p0
    :try_end_10
    .catch Ljava/lang/Exception; {:try_start_c .. :try_end_10} :catch_11

    return-object p0

    :catch_11
    move-exception p0

    :cond_12
    sget-object p0, Lnet/fdgames/GameEntities/MapActor$ActorState;->b:Lnet/fdgames/GameEntities/MapActor$ActorState;

    return-object p0
.end method

.method private static resolveCharacterClass(Ljava/lang/String;)Lnet/fdgames/Rules/Rules$CharacterClass;
    .registers 2

    if-eqz p0, :cond_12

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_12

    :try_start_c
    invoke-static {p0}, Lnet/fdgames/Rules/Rules$CharacterClass;->valueOf(Ljava/lang/String;)Lnet/fdgames/Rules/Rules$CharacterClass;

    move-result-object p0
    :try_end_10
    .catch Ljava/lang/Exception; {:try_start_c .. :try_end_10} :catch_11

    return-object p0

    :catch_11
    move-exception p0

    :cond_12
    sget-object p0, Lnet/fdgames/Rules/Rules$CharacterClass;->b:Lnet/fdgames/Rules/Rules$CharacterClass;

    return-object p0
.end method

.method private static resolveCharacterRace(Ljava/lang/String;)Lnet/fdgames/Rules/Rules$CharacterRace;
    .registers 2

    if-eqz p0, :cond_12

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_12

    :try_start_c
    invoke-static {p0}, Lnet/fdgames/Rules/Rules$CharacterRace;->valueOf(Ljava/lang/String;)Lnet/fdgames/Rules/Rules$CharacterRace;

    move-result-object p0
    :try_end_10
    .catch Ljava/lang/Exception; {:try_start_c .. :try_end_10} :catch_11

    return-object p0

    :catch_11
    move-exception p0

    :cond_12
    sget-object p0, Lnet/fdgames/Rules/Rules$CharacterRace;->b:Lnet/fdgames/Rules/Rules$CharacterRace;

    return-object p0
.end method

.method private static resolveClassName(Ljava/lang/Object;)Ljava/lang/String;
    .registers 5

    const-string v0, "characterClass"

    invoke-static {p0, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    if-nez p0, :cond_b

    const-string p0, ""

    return-object p0

    :cond_b
    :try_start_b
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "name"

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    new-array v1, v2, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0
    :try_end_22
    .catch Ljava/lang/Exception; {:try_start_b .. :try_end_22} :catch_23

    return-object p0

    :catch_23
    move-exception v0

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static resolveCompanionAnchor(Lnet/fdgames/GameEntities/Final/NPC;)Lnet/fdgames/GameEntities/MapActor;
    .registers 5

    const/4 v0, 0x0

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v1

    if-eqz v1, :cond_9

    iget-object v0, v1, Lnet/fdgames/GameWorld/GameData;->player:Lnet/fdgames/GameEntities/Final/Player;

    :cond_9
    if-eqz p0, :cond_35

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonOwners:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_35

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    if-eqz v2, :cond_35

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    invoke-static {v2}, Lnet/fdgames/GameLevel/GameLevel;->g(I)Lnet/fdgames/GameEntities/MapActor;

    move-result-object v3

    if-eqz v3, :cond_2a

    return-object v3

    :cond_2a
    invoke-virtual {p0}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_35
    return-object v0
.end method

.method private static resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;
    .registers 2

    if-eqz p0, :cond_12

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_12

    :try_start_c
    invoke-static {p0}, Lnet/fdgames/GameEntities/MapActor$Facing;->valueOf(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object p0
    :try_end_10
    .catch Ljava/lang/Exception; {:try_start_c .. :try_end_10} :catch_11

    return-object p0

    :catch_11
    move-exception p0

    :cond_12
    sget-object p0, Lnet/fdgames/GameEntities/MapActor$Facing;->g:Lnet/fdgames/GameEntities/MapActor$Facing;

    return-object p0
.end method

.method private static resolveGender(Ljava/lang/String;)Lnet/fdgames/GameEntities/Character$Gender;
    .registers 3

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->normalizeGenderName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "female"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_f

    sget-object p0, Lnet/fdgames/GameEntities/Character$Gender;->c:Lnet/fdgames/GameEntities/Character$Gender;

    return-object p0

    :cond_f
    sget-object p0, Lnet/fdgames/GameEntities/Character$Gender;->b:Lnet/fdgames/GameEntities/Character$Gender;

    return-object p0
.end method

.method public static resolveHostileTargetActor(Lnet/fdgames/GameEntities/MapActor;)Lnet/fdgames/GameEntities/MapActor;
    .registers 16

    const/4 v0, 0x0

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v1

    if-nez v1, :cond_8

    return-object v0

    :cond_8
    iget-object v2, v1, Lnet/fdgames/GameWorld/GameData;->player:Lnet/fdgames/GameEntities/Final/Player;

    if-eqz v2, :cond_14

    invoke-virtual {v2}, Lnet/fdgames/GameEntities/MapActor;->d0()Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v3

    sget-object v4, Lnet/fdgames/GameEntities/MapActor$ActorState;->e:Lnet/fdgames/GameEntities/MapActor$ActorState;

    if-ne v3, v4, :cond_10a

    :cond_14
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v3

    if-eqz v3, :cond_145

    invoke-virtual {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v4

    if-eqz v4, :cond_145

    invoke-virtual {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isHosting()Z

    move-result v4

    if-eqz v4, :cond_145

    const-string v4, "CurrentLevel"

    invoke-static {v1, v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    const-string v5, "currentMapName"

    invoke-static {v1, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    if-eqz v4, :cond_46

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v6

    if-eqz v6, :cond_47

    :cond_46
    move-object v4, v5

    :cond_47
    if-eqz v5, :cond_53

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v6

    if-eqz v6, :cond_54

    :cond_53
    move-object v5, v4

    :cond_54
    invoke-virtual {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v3

    if-eqz v3, :cond_145

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    const v6, 0x7fffffff

    move-object v7, v0

    :cond_62
    :goto_62
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v8

    if-eqz v8, :cond_fc

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    if-eqz v8, :cond_62

    iget-object v9, v8, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    const-string v10, "DEAD"

    invoke-virtual {v10, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_b7

    iget-object v9, v8, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v9, :cond_62

    sget-object v10, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerDeadNames:Ljava/util/HashSet;

    if-eqz v10, :cond_62

    invoke-virtual {v10, v9}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v11

    if-nez v11, :cond_62

    invoke-virtual {v10, v9}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    sget-object v10, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    if-eqz v10, :cond_9d

    invoke-virtual {v10, v9}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v11

    if-eqz v11, :cond_9d

    check-cast v11, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-static {v11}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removeNpcFromLevel(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-virtual {v10, v9}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_9d
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "\u2694 "

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v10, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v11, " foi derrotado!"

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-static {v10}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V

    goto :goto_62

    :cond_b7
    iget-object v9, v8, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v9, :cond_62

    invoke-virtual {v9}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v10

    if-nez v10, :cond_62

    invoke-static {v4, v5, v8}, Lnet/fdgames/ek/android/lan/LanGameBridge;->samePeerLocation(Ljava/lang/String;Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Z

    move-result v10

    if-nez v10, :cond_cc

    goto :goto_62

    :cond_cc
    invoke-static {v9, v8}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getOrCreatePeerActor(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v10

    if-eqz v10, :cond_62

    invoke-static {v10, v8}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerVisualFxMask(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    const-string v11, "stealth"

    iget v12, v8, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stealthSkillLevel:I

    invoke-static {v10, v11, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->ensureActorSkillLevel(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;I)V

    invoke-virtual {v10}, Lnet/fdgames/GameEntities/MapActor;->d0()Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v11

    sget-object v12, Lnet/fdgames/GameEntities/MapActor$ActorState;->e:Lnet/fdgames/GameEntities/MapActor$ActorState;

    if-ne v11, v12, :cond_e6

    goto/16 :goto_62

    :cond_e6
    if-nez p0, :cond_ea

    move-object v7, v10

    goto :goto_fc

    :cond_ea
    iget v11, p0, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v12, p0, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget v13, v10, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v14, v10, Lnet/fdgames/GameEntities/MapObject;->y:I

    invoke-static {v11, v12, v13, v14}, Ly0/b;->r(IIII)I

    move-result v11

    if-ge v11, v6, :cond_62

    move v6, v11

    move-object v7, v10

    goto/16 :goto_62

    :cond_fc
    :goto_fc
    if-nez v7, :cond_109

    if-eqz v2, :cond_109

    invoke-virtual {v2}, Lnet/fdgames/GameEntities/MapActor;->d0()Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v3

    sget-object v4, Lnet/fdgames/GameEntities/MapActor$ActorState;->e:Lnet/fdgames/GameEntities/MapActor$ActorState;

    if-eq v3, v4, :cond_109

    move-object v7, v2

    :cond_109
    return-object v7

    :cond_10a
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v3

    if-eqz v3, :cond_142

    invoke-virtual {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v4

    if-eqz v4, :cond_142

    invoke-virtual {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isHosting()Z

    move-result v4

    if-eqz v4, :cond_142

    invoke-virtual {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v3

    if-eqz v3, :cond_142

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    const v6, 0x7fffffff

    move-object v7, v0

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v1

    const-string v4, "CurrentLevel"

    invoke-static {v1, v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    const-string v5, "currentMapName"

    invoke-static {v1, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    :cond_142
    return-object v2

    goto/16 :goto_62

    :cond_145
    return-object v0
.end method

.method private static resolveMarker(Ljava/lang/String;F)[F
    .registers 10

    const/4 v0, 0x0

    :try_start_1
    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeAreasIsCity(Ljava/lang/String;)Z

    move-result v1

    const/4 v2, 0x1

    const/4 v3, 0x2

    const/4 v4, 0x0

    if-eqz v1, :cond_7d

    const-string v1, "i.LXkY.rFUF"

    const-string v5, "ZTiRQlaiRSeCND"

    invoke-static {v1, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getStaticFieldValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    if-nez v1, :cond_17

    const-string v1, ""

    goto :goto_1b

    :cond_17
    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    :goto_1b
    nop

    nop

    const-string v5, "NG"

    invoke-virtual {p0, v5}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_2b

    nop

    const/16 v5, 0x15

    const/16 v6, 0x16

    goto :goto_5b

    :cond_2b
    const-string v5, "FT"

    invoke-virtual {p0, v5}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_39

    nop

    const/16 v5, 0x9

    const/16 v6, 0x19

    goto :goto_5b

    :cond_39
    const-string v5, "NI"

    invoke-virtual {p0, v5}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_47

    nop

    const/16 v5, 0x11

    const/16 v6, 0x20

    goto :goto_5b

    :cond_47
    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_59

    invoke-virtual {p0, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_59

    nop

    const/16 v5, 0x1a

    const/16 v6, 0x25

    goto :goto_5b

    :cond_59
    const/4 v5, 0x0

    const/4 v6, 0x0

    :goto_5b
    int-to-float v5, v5

    mul-float v5, v5, p1

    int-to-float v6, v6

    mul-float v6, v6, p1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v7

    if-nez v7, :cond_76

    invoke-virtual {p0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_76

    const/high16 p0, 0x40000000    # 2.0f

    div-float p0, p1, p0

    sub-float/2addr v5, p0

    const/high16 p0, 0x40200000    # 2.5f

    div-float/2addr p1, p0

    sub-float/2addr v6, p1

    :cond_76
    new-array p0, v3, [F

    aput v5, p0, v4

    aput v6, p0, v2

    return-object p0

    :cond_7d
    const-string v1, "net.fdgames.GameWorld.Areas"

    const-string v5, "h"

    const-class v6, Ljava/lang/String;

    invoke-static {v1, v5, v6, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    if-nez p0, :cond_8a

    return-object v0

    :cond_8a
    const-string v1, "x"

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result v1

    const-string v5, "y"

    invoke-static {p0, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getIntField(Ljava/lang/Object;Ljava/lang/String;)I

    move-result p0

    int-to-float v1, v1

    mul-float v1, v1, p1

    int-to-float p0, p0

    mul-float p0, p0, p1

    new-array p1, v3, [F

    aput v1, p1, v4

    aput p0, p1, v2
    :try_end_a2
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_a2} :catch_a3

    return-object p1

    :catch_a3
    move-exception p0

    return-object v0
.end method

.method private static resolvePeerOwnerName(Lnet/fdgames/GameEntities/Character;)Ljava/lang/String;
    .registers 4

    const-string v0, ""

    if-eqz p0, :cond_23

    instance-of v1, p0, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v1, :cond_23

    check-cast p0, Lnet/fdgames/GameEntities/Final/NPC;

    iget-boolean v1, p0, Lnet/fdgames/GameEntities/Final/NPC;->lanPeerVisual:Z

    if-eqz v1, :cond_23

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActorOwners:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_23

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    if-eqz p0, :cond_23

    return-object p0

    :cond_23
    return-object v0
.end method

.method private static resolveWeaponStatsFromItemId(I)Lnet/fdgames/Rules/WeaponStats;
    .registers 3

    if-lez p0, :cond_d

    invoke-static {p0}, Lnet/fdgames/Rules/Rules;->f(I)Lnet/fdgames/Rules/Item;

    move-result-object v0

    if-eqz v0, :cond_d

    iget-object v1, v0, Lnet/fdgames/Rules/Item;->weaponStats:Lnet/fdgames/Rules/WeaponStats;

    if-eqz v1, :cond_d

    return-object v1

    :cond_d
    new-instance v0, Lnet/fdgames/Rules/WeaponStats;

    invoke-direct {v0}, Lnet/fdgames/Rules/WeaponStats;-><init>()V

    return-object v0
.end method

.method private static resolveWorldNpcAuthId(Lnet/fdgames/GameEntities/Final/NPC;)Ljava/lang/String;
    .registers 5

    const-string v0, ""

    if-eqz p0, :cond_2a

    iget-object v1, p0, Lnet/fdgames/GameEntities/Final/NPC;->unique_tag:Ljava/lang/String;

    if-eqz v1, :cond_13

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_13

    return-object v1

    :cond_13
    invoke-virtual {p0}, Lnet/fdgames/GameEntities/Final/NPC;->L1()I

    move-result v1

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getWorldNpcSpawnKey(I)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_24

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_24

    return-object v2

    :cond_24
    iget-object p0, p0, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    invoke-static {p0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildWorldNpcFallbackAuthId(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    :cond_2a
    return-object v0
.end method

.method private static sameCityGroup(Ljava/lang/String;Ljava/lang/String;)Z
    .registers 3

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->cityGroup(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->cityGroup(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_16

    invoke-virtual {p0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_16

    const/4 p0, 0x1

    goto :goto_17

    :cond_16
    const/4 p0, 0x0

    :goto_17
    return p0
.end method

.method private static samePeerLocation(Ljava/lang/String;Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Z
    .registers 6

    const/4 v0, 0x0

    if-nez p2, :cond_4

    return v0

    :cond_4
    if-eqz p0, :cond_1a

    iget-object v1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz v1, :cond_10

    invoke-virtual {p0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_31

    :cond_10
    iget-object v1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    if-eqz v1, :cond_1a

    invoke-virtual {p0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_31

    :cond_1a
    if-eqz p1, :cond_30

    iget-object p0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    if-eqz p0, :cond_26

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_31

    :cond_26
    iget-object p0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz p0, :cond_30

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_31

    :cond_30
    return v0

    :cond_31
    const/4 p0, 0x1

    return p0
.end method

.method private static serializeDamageCsv(Lnet/fdgames/GameEntities/Helpers/DamageData;)Ljava/lang/String;
    .registers 8

    const-string v0, ""

    if-eqz p0, :cond_55

    iget-object p0, p0, Lnet/fdgames/GameEntities/Helpers/DamageData;->damages:Ljava/util/ArrayList;

    if-eqz p0, :cond_55

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_11
    :goto_11
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_51

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/GameEntities/Helpers/Damage;

    if-eqz v2, :cond_11

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->length()I

    move-result v3

    if-lez v3, :cond_2a

    const-string v3, ";"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2a
    iget-object v3, v2, Lnet/fdgames/GameEntities/Helpers/Damage;->type:Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;

    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->damageTypeCode(Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ":"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget v5, v2, Lnet/fdgames/GameEntities/Helpers/Damage;->hp:I

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-boolean v2, v2, Lnet/fdgames/GameEntities/Helpers/Damage;->projectile:Z

    if-eqz v2, :cond_4b

    const-string v2, "1"

    goto :goto_4d

    :cond_4b
    const-string v2, "0"

    :goto_4d
    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_11

    :cond_51
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    :cond_55
    return-object v0
.end method

.method private static setAppliedSampleTime(Ljava/lang/String;J)V
    .registers 4

    if-eqz p0, :cond_d

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedSampleTimes:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_d

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {v0, p0, p1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_d
    return-void
.end method

.method private static setFieldValue(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
    .registers 5

    if-nez p0, :cond_3

    return-void

    :cond_3
    :try_start_3
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-static {v0, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->findField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object p1

    if-nez p1, :cond_e

    return-void

    :cond_e
    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    invoke-virtual {p1, p0, p2}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_15
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_15} :catch_16

    goto :goto_17

    :catch_16
    move-exception p0

    :goto_17
    return-void
.end method

.method private static setIntField(Ljava/lang/Object;Ljava/lang/String;I)V
    .registers 4

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-static {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setFieldValue(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V

    return-void
.end method

.method private static spawnVisualEffect(Ljava/lang/String;III)V
    .registers 11

    if-eqz p0, :cond_8

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_9

    :cond_8
    return-void

    :cond_9
    const-string v0, ""

    invoke-virtual {p0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p0

    if-lez p3, :cond_12

    goto :goto_13

    :cond_12
    const/4 p3, 0x1

    :goto_13
    const-string v1, "fireball"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_2f

    const-string v0, "fireball_weak_1"

    const/4 v1, 0x4

    if-lt p3, v1, :cond_23

    const-string v0, "fireball_weak_4"

    goto :goto_2e

    :cond_23
    const/4 v1, 0x3

    if-lt p3, v1, :cond_29

    const-string v0, "fireball_weak_3"

    goto :goto_2e

    :cond_29
    const/4 v1, 0x2

    if-lt p3, v1, :cond_2e

    const-string v0, "fireball_weak_2"

    :cond_2e
    :goto_2e
    goto :goto_97

    :cond_2f
    const-string v1, "ice_storm"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_4b

    const-string v0, "ice_storm_weak_1"

    const/4 v1, 0x4

    if-lt p3, v1, :cond_3f

    const-string v0, "ice_storm_weak_4"

    goto :goto_4a

    :cond_3f
    const/4 v1, 0x3

    if-lt p3, v1, :cond_45

    const-string v0, "ice_storm_weak_3"

    goto :goto_4a

    :cond_45
    const/4 v1, 0x2

    if-lt p3, v1, :cond_4a

    const-string v0, "ice_storm_weak_2"

    :cond_4a
    :goto_4a
    goto :goto_97

    :cond_4b
    const-string v1, "combustion"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_61

    const-string v0, "combustion_weak_1"

    const/4 v1, 0x3

    if-lt p3, v1, :cond_5b

    const-string v0, "combustion_weak_3"

    goto :goto_60

    :cond_5b
    const/4 v1, 0x2

    if-lt p3, v1, :cond_60

    const-string v0, "combustion_weak_2"

    :cond_60
    :goto_60
    goto :goto_97

    :cond_61
    const-string v1, "death_cloud"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_77

    const-string v0, "dc_weak_1"

    const/4 v1, 0x3

    if-lt p3, v1, :cond_71

    const-string v0, "dc_weak_3"

    goto :goto_76

    :cond_71
    const/4 v1, 0x2

    if-lt p3, v1, :cond_76

    const-string v0, "dc_weak_2"

    :cond_76
    :goto_76
    goto :goto_97

    :cond_77
    const-string v1, "flames_of_faith"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_8d

    const-string v0, "flames_faith_weak1"

    const/4 v1, 0x3

    if-lt p3, v1, :cond_87

    const-string v0, "flames_faith_weak3"

    goto :goto_8c

    :cond_87
    const/4 v1, 0x2

    if-lt p3, v1, :cond_8c

    const-string v0, "flames_faith_weak2"

    :cond_8c
    :goto_8c
    goto :goto_97

    :cond_8d
    const-string p3, "smoke_bomb"

    invoke-virtual {p0, p3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_97

    const-string v0, "smoke_bomb"

    :cond_97
    :goto_97
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_9e

    return-void

    :cond_9e
    move-object v6, v0

    new-instance v1, Lnet/fdgames/GameEntities/Final/MapEffectEntity;

    const/4 v2, 0x0

    move v3, p1

    move v4, p2

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v6}, Lnet/fdgames/GameEntities/Final/MapEffectEntity;-><init>(FIIILjava/lang/String;)V

    invoke-static {v1}, Lnet/fdgames/GameLevel/GameLevelData;->e(Lnet/fdgames/GameEntities/Final/MapEffectEntity;)V

    return-void
.end method

.method private static syncPeerActors(Ljava/util/List;Ljava/lang/String;)V
    .registers 16

    if-eqz p0, :cond_23a

    if-eqz p1, :cond_f

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_f

    goto :goto_10

    :cond_f
    const/4 p1, 0x0

    :goto_10
    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v0

    if-eqz v0, :cond_43

    iget-object v0, v0, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    if-eqz v0, :cond_43

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_1e
    :goto_1e
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_43

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/GameEntities/Final/NPC;

    iget-boolean v3, v2, Lnet/fdgames/GameEntities/Final/NPC;->lanPeerVisual:Z

    if-eqz v3, :cond_1e

    sget-object v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    if-eqz v3, :cond_38

    invoke-virtual {v3, v2}, Ljava/util/LinkedHashMap;->containsValue(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_1e

    :cond_38
    invoke-interface {v1}, Ljava/util/Iterator;->remove()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v3

    invoke-virtual {v3, v2}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    goto :goto_1e

    :cond_43
    const-string v11, ""

    :try_start_45
    const-string v0, "net.fdgames.GameWorld.GameData"

    const-string v1, "v"

    invoke-static {v0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_5c

    const-string v1, "currentMapName"

    invoke-static {v0, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getFieldValue(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v11
    :try_end_59
    .catch Ljava/lang/Exception; {:try_start_45 .. :try_end_59} :catch_5a

    goto :goto_5c

    :catch_5a
    const-string v11, ""

    :cond_5c
    :goto_5c
    invoke-virtual {v11}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v11

    if-nez p1, :cond_69

    invoke-virtual {v11}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_69

    return-void

    :cond_69
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getCurrentPlayerName()Ljava/lang/String;

    move-result-object v10

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_76
    :goto_76
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1c7

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    if-eqz v1, :cond_76

    iget-object v2, v1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v2, :cond_76

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_76

    if-eqz v10, :cond_a2

    invoke-virtual {v10}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_a2

    invoke-virtual {v10, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_a2

    goto/16 :goto_76

    :cond_a2
    invoke-virtual {v0, v2}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    invoke-static {p1, v11, v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->samePeerLocation(Ljava/lang/String;Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Z

    move-result v3

    if-nez v3, :cond_ac

    goto :goto_76

    :cond_ac
    :try_start_ac
    move-object v12, v1

    move-object v13, v2

    invoke-static {v13, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getOrCreatePeerActor(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v3

    if-eqz v3, :cond_76

    const-string v2, "player"

    invoke-static {v2, v13}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getAppliedSampleTime(Ljava/lang/String;)J

    move-result-wide v5

    iget-wide v7, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    cmp-long v2, v5, v7

    if-nez v2, :cond_c6

    const/4 v9, 0x0

    goto :goto_c7

    :cond_c6
    const/4 v9, 0x1

    :goto_c7
    iget-object v1, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    iget-object v2, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->facingName:Ljava/lang/String;

    iget v5, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionSeq:I

    invoke-static {v1, v2, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildAnimationKey(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v5

    sget-object v6, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v6, v4}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    if-eqz v6, :cond_e1

    invoke-virtual {v5, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e3

    :cond_e1
    const/4 v6, 0x1

    goto :goto_e4

    :cond_e3
    const/4 v6, 0x0

    :goto_e4
    invoke-static {v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildPeerVisualKey(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v1

    sget-object v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v2, v4}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    if-eqz v2, :cond_f8

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_100

    :cond_f8
    invoke-static {v3, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->refreshPeerVisualFromSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    sget-object v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v2, v4, v1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_100
    invoke-static {v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getDisplayName(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_110

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_112

    :cond_110
    move-object v2, v13

    goto :goto_113

    :cond_112
    move-object v2, v1

    :goto_113
    invoke-virtual {v3, v2}, Lnet/fdgames/GameEntities/Character;->r1(Ljava/lang/String;)V

    iget v1, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->portraitIndex:I

    if-ltz v1, :cond_11c

    iput v1, v3, Lnet/fdgames/GameEntities/Character;->portraitIndex:I

    :cond_11c
    invoke-static {v4, v7, v8}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getPeerAttackStateTime(Ljava/lang/String;J)F

    move-result v2

    invoke-static {v13, v3, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerMotion(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    const/4 v1, 0x0

    cmpg-float v1, v2, v1

    if-ltz v1, :cond_133

    invoke-static {v4, v3, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerAttackVisualLock(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;F)V

    const/4 v1, 0x1

    iput-boolean v1, v3, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    sget-object v1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v1, v3, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    goto :goto_15a

    :cond_133
    iget-object v1, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->facingName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v1

    iput-object v1, v3, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    iget-object v1, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v1

    invoke-virtual {v3, v1}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    iget v1, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stateTimeMs:I

    invoke-static {v1, v7, v8}, Lnet/fdgames/ek/android/lan/LanGameBridge;->computeStateRelativeTime(IJ)F

    move-result v1

    iput v1, v3, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    if-eqz v6, :cond_153

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v1, v4, v5}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_153
    const/4 v1, 0x1

    iput-boolean v1, v3, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    sget-object v1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v1, v3, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    :goto_15a
    invoke-static {v13, v3, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->syncPeerSummon(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-static {v13, v3, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->syncPeerCompanion(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-static {v13, v3, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->syncPeerFollowers(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    if-eqz v9, :cond_168

    invoke-static {v4, v7, v8}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setAppliedSampleTime(Ljava/lang/String;J)V

    :cond_168
    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActionSeqs:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, v13}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Integer;

    if-eqz v4, :cond_177

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    goto :goto_178

    :cond_177
    const/4 v4, 0x0

    :goto_178
    iget v5, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionSeq:I

    if-eq v4, v5, :cond_1bc

    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActionSeqs:Ljava/util/LinkedHashMap;

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-virtual {v4, v13, v6}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v4, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellId:Ljava/lang/String;

    iput-object v4, v3, Lnet/fdgames/GameEntities/Character;->spell_id:Ljava/lang/String;

    iget v4, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellTarget:I

    const-string v5, "spellTarget"

    invoke-static {v3, v5, v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setIntField(Ljava/lang/Object;Ljava/lang/String;I)V

    iget v4, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    if-ltz v4, :cond_1ad

    iget-object v5, v3, Lnet/fdgames/GameEntities/Character;->skillOrigin:Lnet/fdgames/TiledMap/Objects/Coords;

    if-nez v5, :cond_1a4

    new-instance v5, Lnet/fdgames/TiledMap/Objects/Coords;

    iget v6, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    iget v7, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    invoke-direct {v5, v6, v7}, Lnet/fdgames/TiledMap/Objects/Coords;-><init>(II)V

    iput-object v5, v3, Lnet/fdgames/GameEntities/Character;->skillOrigin:Lnet/fdgames/TiledMap/Objects/Coords;

    goto :goto_1b0

    :cond_1a4
    iget v6, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    iput v6, v5, Lnet/fdgames/TiledMap/Objects/Coords;->x:I

    iget v6, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    iput v6, v5, Lnet/fdgames/TiledMap/Objects/Coords;->y:I

    goto :goto_1b0

    :cond_1ad
    const/4 v5, 0x0

    iput-object v5, v3, Lnet/fdgames/GameEntities/Character;->skillOrigin:Lnet/fdgames/TiledMap/Objects/Coords;

    :goto_1b0
    iget-object v4, v12, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v4

    invoke-virtual {v3, v4}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    const/4 v4, 0x0

    iput v4, v3, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    :cond_1bc
    invoke-static {v3, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerVisualFxMask(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-static {v3, v12}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyPeerCombatSnapshot(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    :try_end_1c2
    .catch Ljava/lang/Exception; {:try_start_ac .. :try_end_1c2} :catch_1c4

    goto/16 :goto_76

    :catch_1c4
    move-exception v3

    goto/16 :goto_76

    :cond_1c7
    sget-object p0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    invoke-virtual {p0}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1d1
    :goto_1d1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_239

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/Map$Entry;

    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1d1

    invoke-interface {p1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    if-eqz v1, :cond_218

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->untrackPeerActorOwner(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-virtual {v1}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v2

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removePeerSummonsForOwner(I)V

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removePeerSummon(Ljava/lang/String;)V

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removePeerCompanion(Ljava/lang/String;)V

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removeAllPeerFollowers(Ljava/lang/String;)V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v2

    iget-object v2, v2, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_218
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActionSeqs:Ljava/util/LinkedHashMap;

    invoke-virtual {v1, p1}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCombatSeqs:Ljava/util/LinkedHashMap;

    invoke-virtual {v1, p1}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerMotionStates:Ljava/util/LinkedHashMap;

    invoke-virtual {v1, p1}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerVisualSignatures:Ljava/util/LinkedHashMap;

    invoke-virtual {v1, p1}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    const-string v1, "player"

    invoke-static {v1, p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearAppliedState(Ljava/lang/String;)V

    invoke-interface {p0}, Ljava/util/Iterator;->remove()V

    goto :goto_1d1

    :cond_239
    return-void

    :cond_23a
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearPeerActors()V

    return-void
.end method

.method private static syncPeerCompanion(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 13

    if-eqz p0, :cond_112

    if-eqz p1, :cond_113

    if-eqz p2, :cond_113

    iget-object v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    if-eqz v0, :cond_113

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_113

    iget v1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    if-ltz v1, :cond_113

    iget v1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    if-gez v1, :cond_1e

    goto/16 :goto_113

    :cond_1e
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCompanionActors:Ljava/util/LinkedHashMap;

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v1, :cond_34

    iget-object v2, v1, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_34

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removePeerCompanion(Ljava/lang/String;)V

    const/4 v1, 0x0

    :cond_34
    if-nez v1, :cond_42

    invoke-static {p1, p2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->findPeerCompanionCandidate(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v1

    if-nez v1, :cond_42

    invoke-static {p2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->createPeerCompanion(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v1

    if-eqz v1, :cond_112

    :cond_42
    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v2

    if-eqz v2, :cond_5f

    iget-object v2, v2, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    if-eqz v2, :cond_5f

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_5f

    invoke-static {v1}, Lnet/fdgames/GameLevel/GameLevel;->a(Lnet/fdgames/GameEntities/Final/NPC;)V

    invoke-virtual {v1}, Lnet/fdgames/GameEntities/Final/NPC;->B1()V

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_5f
    sget-object v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerCompanionActors:Ljava/util/LinkedHashMap;

    invoke-virtual {v2, p0, v1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iput-object v0, v1, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    const-string v2, "companion"

    invoke-static {v2, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getAppliedSampleTime(Ljava/lang/String;)J

    move-result-wide v3

    iget-wide v5, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    cmp-long v0, v3, v5

    if-nez v0, :cond_78

    const/4 v7, 0x0

    goto :goto_79

    :cond_78
    const/4 v7, 0x1

    :goto_79
    iget-object v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    if-eqz v0, :cond_89

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_89

    iput-object v0, v1, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    :cond_89
    iget-object v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    if-eqz v0, :cond_9a

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_9a

    invoke-virtual {v1, v0}, Lnet/fdgames/GameEntities/Character;->r1(Ljava/lang/String;)V

    :cond_9a
    iget v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    iput v0, v1, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    iput v0, v1, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget-object v3, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    iget-object v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    invoke-static {v3, v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildAnimationKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, v2}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    if-eqz v4, :cond_ba

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v8

    if-nez v8, :cond_bc

    :cond_ba
    const/4 v8, 0x1

    goto :goto_bd

    :cond_bc
    const/4 v8, 0x0

    :goto_bd
    iget-object v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    iget-object v9, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    invoke-static {v4, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildVisualKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    sget-object v9, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v9, v2}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/lang/String;

    if-eqz v9, :cond_d5

    invoke-virtual {v4, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_e4

    :cond_d5
    iget-object v9, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    invoke-static {v1, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyAnimationSet(Lnet/fdgames/GameEntities/MapActor;Ljava/lang/String;)V

    iget-object v9, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    invoke-static {v1, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applySpriteIndexCsv(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;)V

    sget-object v9, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v9, v2, v4}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_e4
    if-eqz v8, :cond_104

    iget-object v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v4

    iput-object v4, v1, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    iget-object v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v4

    invoke-virtual {v1, v4}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    iget v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionStateTimeMs:I

    invoke-static {v4, v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->computeStateRelativeTime(IJ)F

    move-result v4

    iput v4, v1, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, v2, v3}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_104
    const/4 v3, 0x1

    iput-boolean v3, v1, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    iput-boolean v3, v1, Lnet/fdgames/GameEntities/Final/NPC;->summoned:Z

    sget-object v3, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v3, v1, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    if-eqz v7, :cond_112

    invoke-static {v2, v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setAppliedSampleTime(Ljava/lang/String;J)V

    :cond_112
    :goto_112
    return-void

    :cond_113
    :goto_113
    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removePeerCompanion(Ljava/lang/String;)V

    goto :goto_112
.end method

.method private static syncPeerFollowers(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 16

    if-eqz p0, :cond_169

    if-eqz p1, :cond_166

    if-eqz p2, :cond_166

    iget-object v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    if-eqz v0, :cond_166

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-lez v1, :cond_166

    new-instance v1, Ljava/util/HashSet;

    invoke-direct {v1}, Ljava/util/HashSet;-><init>()V

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_19
    :goto_19
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_106

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    if-eqz v2, :cond_19

    iget-object v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    if-eqz v3, :cond_19

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_36

    goto :goto_19

    :cond_36
    iget-object v4, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    if-eqz v4, :cond_19

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_45

    goto :goto_19

    :cond_45
    iget v5, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    if-ltz v5, :cond_19

    iget v5, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    if-gez v5, :cond_4e

    goto :goto_19

    :cond_4e
    invoke-static {p0, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildPeerFollowerKey(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    sget-object v6, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerFollowerActors:Ljava/util/LinkedHashMap;

    invoke-virtual {v6, v5}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lnet/fdgames/GameEntities/Final/NPC;

    if-nez v6, :cond_65

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->createPeerFollower(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v6

    if-eqz v6, :cond_19

    :cond_65
    sget-object v7, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerFollowerActors:Ljava/util/LinkedHashMap;

    invoke-virtual {v7, v5, v6}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iput-object v3, v6, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    iput-object v4, v6, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    iget-object v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    if-eqz v3, :cond_7f

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_7f

    invoke-virtual {v6, v3}, Lnet/fdgames/GameEntities/Character;->r1(Ljava/lang/String;)V

    :cond_7f
    iget v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iput v3, v6, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    iput v3, v6, Lnet/fdgames/GameEntities/MapObject;->y:I

    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getAppliedSampleTime(Ljava/lang/String;)J

    move-result-wide v8

    iget-wide v10, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    cmp-long v3, v8, v10

    if-nez v3, :cond_93

    const/4 v7, 0x0

    goto :goto_94

    :cond_93
    const/4 v7, 0x1

    :goto_94
    iget-object v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    iget-object v4, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    invoke-static {v3, v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildAnimationKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    sget-object v9, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v9, v5}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/lang/String;

    if-eqz v9, :cond_ac

    invoke-virtual {v8, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_ae

    :cond_ac
    const/4 v3, 0x1

    goto :goto_af

    :cond_ae
    const/4 v3, 0x0

    :goto_af
    iget-object v4, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    iget-object v9, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    invoke-static {v4, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildVisualKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    sget-object v9, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v9, v5}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/lang/String;

    if-eqz v9, :cond_c7

    invoke-virtual {v4, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_d6

    :cond_c7
    iget-object v9, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    invoke-static {v6, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyAnimationSet(Lnet/fdgames/GameEntities/MapActor;Ljava/lang/String;)V

    iget-object v9, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    invoke-static {v6, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applySpriteIndexCsv(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;)V

    sget-object v9, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v9, v5, v4}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_d6
    if-eqz v3, :cond_f6

    iget-object v4, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v4

    iput-object v4, v6, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    iget-object v4, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v4

    invoke-virtual {v6, v4}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    iget v4, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    invoke-static {v4, v10, v11}, Lnet/fdgames/ek/android/lan/LanGameBridge;->computeStateRelativeTime(IJ)F

    move-result v4

    iput v4, v6, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, v5, v8}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_f6
    const/4 v4, 0x1

    iput-boolean v4, v6, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    iput-boolean v4, v6, Lnet/fdgames/GameEntities/Final/NPC;->summoned:Z

    sget-object v4, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v4, v6, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    if-eqz v7, :cond_19

    invoke-static {v5, v10, v11}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setAppliedSampleTime(Ljava/lang/String;J)V

    goto/16 :goto_19

    :cond_106
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->asString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v2, "\t"

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    sget-object v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerFollowerActors:Ljava/util/LinkedHashMap;

    invoke-virtual {v2}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_127
    :goto_127
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_169

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    if-eqz v4, :cond_127

    invoke-virtual {v4, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_127

    invoke-virtual {v1, v4}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_127

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v3, :cond_15f

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v5

    iget-object v5, v5, Lnet/fdgames/GameLevel/GameLevelData;->npcs:Ljava/util/ArrayList;

    invoke-virtual {v5, v3}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    invoke-static {}, Lnet/fdgames/GameLevel/GameLevel;->e()Ljava/util/ArrayList;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    :cond_15f
    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearAppliedState(Ljava/lang/String;)V

    invoke-interface {v2}, Ljava/util/Iterator;->remove()V

    goto :goto_127

    :cond_166
    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removeAllPeerFollowers(Ljava/lang/String;)V

    :cond_169
    return-void
.end method

.method private static syncPeerSummon(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 13

    if-eqz p0, :cond_f5

    if-eqz p1, :cond_f6

    if-eqz p2, :cond_f6

    iget-object v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpawnId:Ljava/lang/String;

    if-eqz v0, :cond_f6

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_f6

    iget v1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonX:I

    if-ltz v1, :cond_f6

    iget v1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonY:I

    if-gez v1, :cond_1e

    goto/16 :goto_f6

    :cond_1e
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonActors:Ljava/util/LinkedHashMap;

    invoke-virtual {v1, p0}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/GameEntities/Final/NPC;

    if-eqz v1, :cond_34

    iget-object v2, v1, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_34

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removePeerSummon(Ljava/lang/String;)V

    const/4 v1, 0x0

    :cond_34
    if-nez v1, :cond_42

    invoke-static {p1, p2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->findPeerSummonCandidate(Lnet/fdgames/GameEntities/Final/NPC;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v1

    if-nez v1, :cond_42

    invoke-static {p2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->createPeerSummon(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;

    move-result-object v1

    if-eqz v1, :cond_f5

    :cond_42
    sget-object v2, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonActors:Ljava/util/LinkedHashMap;

    invoke-virtual {v2, p0, v1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iput-object v0, v1, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;

    const-string v2, "summon"

    invoke-static {v2, p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildEntityCacheKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getAppliedSampleTime(Ljava/lang/String;)J

    move-result-wide v3

    iget-wide v5, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    cmp-long v0, v3, v5

    if-nez v0, :cond_5b

    const/4 v7, 0x0

    goto :goto_5c

    :cond_5b
    const/4 v7, 0x1

    :goto_5c
    iget-object v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonTag:Ljava/lang/String;

    if-eqz v0, :cond_6c

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_6c

    iput-object v0, v1, Lnet/fdgames/GameEntities/MapObject;->tag:Ljava/lang/String;

    :cond_6c
    iget-object v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonName:Ljava/lang/String;

    if-eqz v0, :cond_7d

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_7d

    invoke-virtual {v1, v0}, Lnet/fdgames/GameEntities/Character;->r1(Ljava/lang/String;)V

    :cond_7d
    iget v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonX:I

    iput v0, v1, Lnet/fdgames/GameEntities/MapObject;->x:I

    iget v0, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonY:I

    iput v0, v1, Lnet/fdgames/GameEntities/MapObject;->y:I

    iget-object v3, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonActorStateName:Ljava/lang/String;

    iget-object v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonFacingName:Ljava/lang/String;

    invoke-static {v3, v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildAnimationKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, v2}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    if-eqz v4, :cond_9d

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v8

    if-nez v8, :cond_9f

    :cond_9d
    const/4 v8, 0x1

    goto :goto_a0

    :cond_9f
    const/4 v8, 0x0

    :goto_a0
    iget-object v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    iget-object v9, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteIndexCsv:Ljava/lang/String;

    invoke-static {v4, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->buildVisualKey(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    sget-object v9, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v9, v2}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/lang/String;

    if-eqz v9, :cond_b8

    invoke-virtual {v4, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_c7

    :cond_b8
    iget-object v9, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    invoke-static {v1, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyAnimationSet(Lnet/fdgames/GameEntities/MapActor;Ljava/lang/String;)V

    iget-object v9, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteIndexCsv:Ljava/lang/String;

    invoke-static {v1, v9}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applySpriteIndexCsv(Lnet/fdgames/GameEntities/Character;Ljava/lang/String;)V

    sget-object v9, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedVisualKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v9, v2, v4}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_c7
    if-eqz v8, :cond_e7

    iget-object v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonFacingName:Ljava/lang/String;

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveFacing(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$Facing;

    move-result-object v4

    iput-object v4, v1, Lnet/fdgames/GameEntities/MapActor;->facing:Lnet/fdgames/GameEntities/MapActor$Facing;

    iget-object v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonActorStateName:Ljava/lang/String;

    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanGameBridge;->resolveActorState(Ljava/lang/String;)Lnet/fdgames/GameEntities/MapActor$ActorState;

    move-result-object v4

    invoke-virtual {v1, v4}, Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V

    iget v4, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonStateTimeMs:I

    invoke-static {v4, v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->computeStateRelativeTime(IJ)F

    move-result v4

    iput v4, v1, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F

    sget-object v4, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerAppliedAnimationKeys:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, v2, v3}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_e7
    const/4 v3, 0x1

    iput-boolean v3, v1, Lnet/fdgames/GameEntities/Final/NPC;->ai_disabled:Z

    iput-boolean v3, v1, Lnet/fdgames/GameEntities/Final/NPC;->summoned:Z

    sget-object v3, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v3, v1, Lnet/fdgames/GameEntities/MapSprite;->visibleToPlayer:Ljava/lang/Boolean;

    if-eqz v7, :cond_f5

    invoke-static {v2, v5, v6}, Lnet/fdgames/ek/android/lan/LanGameBridge;->setAppliedSampleTime(Ljava/lang/String;J)V

    :cond_f5
    :goto_f5
    return-void

    :cond_f6
    :goto_f6
    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->removePeerSummon(Ljava/lang/String;)V

    goto :goto_f5
.end method

.method public static tick()V
    .registers 13

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    if-eqz v0, :cond_b1

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v1

    if-nez v1, :cond_e

    goto/16 :goto_b1

    :cond_e
    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v9

    if-eqz v9, :cond_1e

    iget-object v9, v9, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    if-eqz v9, :cond_1e

    const/4 v10, 0x1

    const-string v3, "lan_pvp_active"

    invoke-virtual {v9, v10, v3}, Lnet/fdgames/GameWorld/GameVariables;->e(ILjava/lang/String;)V

    :cond_1e
    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v10

    const/4 v3, 0x0

    if-eqz v10, :cond_42

    invoke-interface {v10}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v10

    :cond_29
    invoke-interface {v10}, Ljava/util/Iterator;->hasNext()Z

    move-result v11

    if-eqz v11, :cond_42

    invoke-interface {v10}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    iget-object v11, v11, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    if-eqz v11, :cond_29

    const-string v12, "H10_pvp_arena"

    invoke-virtual {v11, v12}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v11

    if-eqz v11, :cond_29

    const/4 v3, 0x1

    :cond_42
    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v10

    if-eqz v10, :cond_51

    iget-object v10, v10, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    if-eqz v10, :cond_51

    const-string v11, "pvp_fight_active"

    invoke-virtual {v10, v3, v11}, Lnet/fdgames/GameWorld/GameVariables;->e(ILjava/lang/String;)V

    :cond_51
    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v2

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureLocalState()Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    move-result-object v1

    if-eqz v1, :cond_80

    iget-object v3, v1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz v3, :cond_74

    sget-object v9, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastSyncedLevelId:Ljava/lang/String;

    if-eqz v9, :cond_72

    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v10

    if-nez v10, :cond_72

    invoke-virtual {v9, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-nez v10, :cond_74

    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearPeerActors()V

    :cond_72
    sput-object v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastSyncedLevelId:Ljava/lang/String;

    :cond_74
    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->flushPendingCombatPackets()V

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->flushPendingPlayerDamagePackets()V

    :try_start_7a
    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->syncPeerActors(Ljava/util/List;Ljava/lang/String;)V
    :try_end_7d
    .catch Ljava/lang/Exception; {:try_start_7a .. :try_end_7d} :catch_7e

    goto :goto_8d

    :catch_7e
    move-exception v4

    goto :goto_8d

    :cond_80
    const/4 v3, 0x0

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->flushPendingCombatPackets()V

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->flushPendingPlayerDamagePackets()V

    :try_start_87
    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->syncPeerActors(Ljava/util/List;Ljava/lang/String;)V
    :try_end_8a
    .catch Ljava/lang/Exception; {:try_start_87 .. :try_end_8a} :catch_8b

    return-void

    :catch_8b
    move-exception v2

    return-void

    :goto_8d
    invoke-static {v0, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->applyReceivedWorldNpcStates(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    sget-wide v5, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastTickAt:J

    sub-long v5, v3, v5

    sget-wide v7, Lnet/fdgames/ek/android/lan/LanGameBridge;->LAN_PUBLISH_INTERVAL_MS:J

    cmp-long v2, v5, v7

    if-gez v2, :cond_9f

    return-void

    :cond_9f
    sput-wide v3, Lnet/fdgames/ek/android/lan/LanGameBridge;->lastTickAt:J

    invoke-virtual {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->publishLiveState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isHosting()Z

    move-result v2

    iget-object v3, v1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-nez v2, :cond_ad

    goto :goto_b0

    :cond_ad
    invoke-static {v0, v3}, Lnet/fdgames/ek/android/lan/LanGameBridge;->captureAndPublishWorldNpcs(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    :goto_b0
    return-void

    :cond_b1
    :goto_b1
    invoke-static {}, Lnet/fdgames/ek/android/lan/LanGameBridge;->clearPeerActors()V

    return-void
.end method

.method private static trackPeerActorOwner(Ljava/lang/String;Lnet/fdgames/GameEntities/Final/NPC;)V
    .registers 4

    if-eqz p0, :cond_1d

    if-eqz p1, :cond_1d

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1d

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActorOwners:Ljava/util/LinkedHashMap;

    if-eqz v0, :cond_1d

    invoke-virtual {p1}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1, p0}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1d
    return-void
.end method

.method private static untrackPeerActorOwner(Lnet/fdgames/GameEntities/Final/NPC;)V
    .registers 4

    if-eqz p0, :cond_18

    invoke-virtual {p0}, Lnet/fdgames/GameEntities/GameObject;->q()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActorOwners:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_11

    invoke-virtual {v1, v0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_11
    sget-object v1, Lnet/fdgames/ek/android/lan/LanGameBridge;->pendingPeerDamageProcs:Ljava/util/LinkedHashMap;

    if-eqz v1, :cond_18

    invoke-virtual {v1, v0}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_18
    return-void
.end method
