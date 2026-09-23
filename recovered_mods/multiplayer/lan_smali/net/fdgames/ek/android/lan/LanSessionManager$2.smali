.class Lnet/fdgames/ek/android/lan/LanSessionManager$2;
.super Ljava/lang/Object;
.source "LanSessionManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;->startAcceptThread(Ljava/net/ServerSocket;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

.field final synthetic val$serverSocket:Ljava/net/ServerSocket;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/ServerSocket;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->val$serverSocket:Ljava/net/ServerSocket;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 7

    nop

    :goto_1
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->val$serverSocket:Ljava/net/ServerSocket;

    invoke-virtual {v0}, Ljava/net/ServerSocket;->isClosed()Z

    move-result v0

    if-nez v0, :cond_a4

    :try_start_9
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->val$serverSocket:Ljava/net/ServerSocket;

    invoke-virtual {v0}, Ljava/net/ServerSocket;->accept()Ljava/net/Socket;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/net/Socket;->setTcpNoDelay(Z)V

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "host accepted local="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v0}, Ljava/net/Socket;->getLocalSocketAddress()Ljava/net/SocketAddress;

    move-result-object v5

    invoke-static {v5}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, " remote="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v0}, Ljava/net/Socket;->getRemoteSocketAddress()Ljava/net/SocketAddress;

    move-result-object v5

    invoke-static {v5}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V
    invoke-static {v5, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2900(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    new-instance v2, Ljava/lang/Thread;

    new-instance v3, Lnet/fdgames/ek/android/lan/LanSessionManager$2$1;

    invoke-direct {v3, p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager$2$1;-><init>(Lnet/fdgames/ek/android/lan/LanSessionManager$2;Ljava/net/Socket;)V

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "ek-lan-client-"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v0}, Ljava/net/Socket;->getInetAddress()Ljava/net/InetAddress;

    move-result-object v0

    invoke-virtual {v0}, Ljava/net/InetAddress;->getHostAddress()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v3, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/Thread;->setDaemon(Z)V

    invoke-virtual {v2}, Ljava/lang/Thread;->start()V
    :try_end_70
    .catch Ljava/io/IOException; {:try_start_9 .. :try_end_70} :catch_71

    goto :goto_1

    :catch_71
    move-exception v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->val$serverSocket:Ljava/net/ServerSocket;

    invoke-virtual {v1}, Ljava/net/ServerSocket;->isClosed()Z

    move-result v1

    if-nez v1, :cond_a3

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$200(Lnet/fdgames/ek/android/lan/LanSessionManager;)Z

    move-result v3

    if-eqz v3, :cond_8c

    const-string v3, "Falha no servidor LAN: "

    goto :goto_8e

    :cond_8c
    const-string v3, "LAN server error: "

    :goto_8e
    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$2;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;
    invoke-static {v3, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$300(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V
    invoke-static {v1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$400(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    :cond_a3
    nop

    :cond_a4
    return-void
.end method
