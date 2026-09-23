.class public final Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;
.super Ljava/lang/Object;
.source "LanSessionManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "FollowerState"
.end annotation


# instance fields
.field public actorStateName:Ljava/lang/String;

.field public facingName:Ljava/lang/String;

.field public name:Ljava/lang/String;

.field public spawnId:Ljava/lang/String;

.field public spriteIndexCsv:Ljava/lang/String;

.field public spriteName:Ljava/lang/String;

.field public stateTimeMs:I

.field public tag:Ljava/lang/String;

.field public x:I

.field public y:I


# direct methods
.method public constructor <init>()V
    .registers 3

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-string v0, ""

    const/4 v1, -0x1

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    return-void
.end method
