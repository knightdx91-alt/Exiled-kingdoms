.class public final Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;
.super Ljava/lang/Object;
.source "LanSessionManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "DiscoveryResult"
.end annotation


# instance fields
.field public final address:Ljava/lang/String;

.field public final maxPlayers:I

.field public final playerCount:I

.field public final port:I

.field public final sessionName:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;III)V
    .registers 6

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->sessionName:Ljava/lang/String;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->address:Ljava/lang/String;

    iput p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->port:I

    iput p4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->playerCount:I

    iput p5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->maxPlayers:I

    return-void
.end method
