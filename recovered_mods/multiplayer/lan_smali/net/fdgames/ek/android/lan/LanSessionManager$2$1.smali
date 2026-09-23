.class Lnet/fdgames/ek/android/lan/LanSessionManager$2$1;
.super Ljava/lang/Object;
.source "LanSessionManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager$2;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lnet/fdgames/ek/android/lan/LanSessionManager$2;

.field final synthetic val$socket:Ljava/net/Socket;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanSessionManager$2;Ljava/net/Socket;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2$1;->this$1:Lnet/fdgames/ek/android/lan/LanSessionManager$2;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2$1;->val$socket:Ljava/net/Socket;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2$1;->this$1:Lnet/fdgames/ek/android/lan/LanSessionManager$2;

    iget-object v0, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2$1;->val$socket:Ljava/net/Socket;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->handleIncomingClient(Ljava/net/Socket;)V
    invoke-static {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1100(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)V

    return-void
.end method
