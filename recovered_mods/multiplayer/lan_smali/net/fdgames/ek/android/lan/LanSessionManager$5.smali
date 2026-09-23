.class Lnet/fdgames/ek/android/lan/LanSessionManager$5;
.super Ljava/lang/Object;
.source "LanSessionManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

.field final synthetic val$chatCopy:Ljava/util/ArrayList;

.field final synthetic val$discoveryCopy:Ljava/util/ArrayList;

.field final synthetic val$listener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

.field final synthetic val$playersCopy:Ljava/util/ArrayList;

.field final synthetic val$state:Ljava/lang/String;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    .registers 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$listener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    iput-object p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$state:Ljava/lang/String;

    iput-object p4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$playersCopy:Ljava/util/ArrayList;

    iput-object p5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$chatCopy:Ljava/util/ArrayList;

    iput-object p6, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$discoveryCopy:Ljava/util/ArrayList;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$listener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$state:Ljava/lang/String;

    invoke-interface {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;->onStateChanged(Ljava/lang/String;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$listener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$playersCopy:Ljava/util/ArrayList;

    invoke-interface {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;->onPlayersChanged(Ljava/util/List;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$listener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$chatCopy:Ljava/util/ArrayList;

    invoke-interface {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;->onChatUpdated(Ljava/util/List;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$listener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$5;->val$discoveryCopy:Ljava/util/ArrayList;

    invoke-interface {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;->onDiscoveryUpdated(Ljava/util/List;)V

    return-void
.end method
