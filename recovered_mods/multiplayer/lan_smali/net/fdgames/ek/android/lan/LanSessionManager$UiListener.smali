.class public interface abstract Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;
.super Ljava/lang/Object;
.source "LanSessionManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "UiListener"
.end annotation


# virtual methods
.method public abstract onChatUpdated(Ljava/util/List;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation
.end method

.method public abstract onDiscoveryUpdated(Ljava/util/List;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;",
            ">;)V"
        }
    .end annotation
.end method

.method public abstract onPlayersChanged(Ljava/util/List;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation
.end method

.method public abstract onStateChanged(Ljava/lang/String;)V
.end method

.method public abstract onToast(Ljava/lang/String;)V
.end method
