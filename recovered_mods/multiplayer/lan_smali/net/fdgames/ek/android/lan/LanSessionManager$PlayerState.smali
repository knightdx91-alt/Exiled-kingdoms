.class public final Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;
.super Ljava/lang/Object;
.source "LanSessionManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "PlayerState"
.end annotation


# instance fields
.field public actionOriginX:I

.field public actionOriginY:I

.field public actionSeq:I

.field public actorStateName:Ljava/lang/String;

.field public characterName:Ljava/lang/String;

.field public classEnumName:Ljava/lang/String;

.field public className:Ljava/lang/String;

.field public combatEffectsSnapshot:Ljava/lang/String;

.field public companionActorStateName:Ljava/lang/String;

.field public companionFacingName:Ljava/lang/String;

.field public companionName:Ljava/lang/String;

.field public companionSpawnId:Ljava/lang/String;

.field public companionSpriteIndexCsv:Ljava/lang/String;

.field public companionSpriteName:Ljava/lang/String;

.field public companionStateTimeMs:I

.field public companionTag:Ljava/lang/String;

.field public companionX:I

.field public companionY:I

.field public currentLevelId:Ljava/lang/String;

.field public currentMapName:Ljava/lang/String;

.field public difficulty:I

.field public facingName:Ljava/lang/String;

.field public followers:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;",
            ">;"
        }
    .end annotation
.end field

.field public genderName:Ljava/lang/String;

.field public gold:I

.field public level:I

.field public missingHp:I

.field public missingMana:I

.field public playerName:Ljava/lang/String;

.field public portraitIndex:I

.field public raceName:Ljava/lang/String;

.field public repIlmara:I

.field public repMercia:I

.field public repThree:I

.field public repVarsilia:I

.field public repWizards:I

.field public sampleTimeMs:J

.field public skillSnapshot:Ljava/lang/String;

.field public slot:I

.field public slotBodyItemId:I

.field public slotFeetItemId:I

.field public slotHandsItemId:I

.field public slotHeadItemId:I

.field public slotLegsItemId:I

.field public slotMainhandItemId:I

.field public slotOffhandItemId:I

.field public speedX:F

.field public speedY:F

.field public spellId:Ljava/lang/String;

.field public spellTarget:I

.field public spriteIndexCsv:Ljava/lang/String;

.field public spriteName:Ljava/lang/String;

.field public stateTimeMs:I

.field public stealthSkillLevel:I

.field public summonActorStateName:Ljava/lang/String;

.field public summonFacingName:Ljava/lang/String;

.field public summonName:Ljava/lang/String;

.field public summonSpawnId:Ljava/lang/String;

.field public summonSpriteIndexCsv:Ljava/lang/String;

.field public summonSpriteName:Ljava/lang/String;

.field public summonStateTimeMs:I

.field public summonTag:Ljava/lang/String;

.field public summonX:I

.field public summonY:I

.field public visualFxMask:I

.field public x:I

.field public y:I


# direct methods
.method public constructor <init>()V
    .registers 3

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-string v0, ""

    const/4 v1, -0x1

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->raceName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->combatEffectsSnapshot:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->classEnumName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellId:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->skillSnapshot:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteIndexCsv:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->facingName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpawnId:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonTag:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteIndexCsv:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonFacingName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonActorStateName:Ljava/lang/String;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotBodyItemId:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotFeetItemId:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHandsItemId:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHeadItemId:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotLegsItemId:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotMainhandItemId:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotOffhandItemId:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonX:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonY:I

    return-void
.end method
