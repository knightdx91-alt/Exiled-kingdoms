.class Lnet/fdgames/ek/android/lan/LanSessionManager$3;
.super Ljava/lang/Object;
.source "LanSessionManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;->startDiscoveryResponder()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

.field final synthetic val$socket:Ljava/net/DatagramSocket;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/DatagramSocket;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->val$socket:Ljava/net/DatagramSocket;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 9

    const/16 v0, 0x200

    new-array v1, v0, [B

    :goto_4
    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->val$socket:Ljava/net/DatagramSocket;

    invoke-virtual {v2}, Ljava/net/DatagramSocket;->isClosed()Z

    move-result v2

    if-nez v2, :cond_dc

    new-instance v2, Ljava/net/DatagramPacket;

    invoke-direct {v2, v1, v0}, Ljava/net/DatagramPacket;-><init>([BI)V

    :try_start_11
    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->val$socket:Ljava/net/DatagramSocket;

    invoke-virtual {v3, v2}, Ljava/net/DatagramSocket;->receive(Ljava/net/DatagramPacket;)V

    new-instance v3, Ljava/lang/String;

    invoke-virtual {v2}, Ljava/net/DatagramPacket;->getData()[B

    move-result-object v4

    invoke-virtual {v2}, Ljava/net/DatagramPacket;->getLength()I

    move-result v5

    const-string v6, "UTF-8"

    const/4 v7, 0x0

    invoke-direct {v3, v4, v7, v5, v6}, Ljava/lang/String;-><init>([BIILjava/lang/String;)V

    const-string v4, "EK_DISCOVER"

    invoke-virtual {v3, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_a6

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$600(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Object;

    move-result-object v3

    monitor-enter v3
    :try_end_35
    .catch Ljava/io/IOException; {:try_start_11 .. :try_end_35} :catch_a9

    :try_start_35
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "EK_HOST\t"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iget-object v6, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;
    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1200(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/String;

    move-result-object v6

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;
    invoke-static {v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1300(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "\t"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I
    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1400(Lnet/fdgames/ek/android/lan/LanSessionManager;)I

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "\t"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;
    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1500(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/ArrayList;

    move-result-object v5

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "\t"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->maxPlayers:I
    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1600(Lnet/fdgames/ek/android/lan/LanSessionManager;)I

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    monitor-exit v3
    :try_end_89
    .catchall {:try_start_35 .. :try_end_89} :catchall_a3

    :try_start_89
    const-string v3, "UTF-8"

    invoke-virtual {v4, v3}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v3

    new-instance v4, Ljava/net/DatagramPacket;

    array-length v5, v3

    invoke-virtual {v2}, Ljava/net/DatagramPacket;->getAddress()Ljava/net/InetAddress;

    move-result-object v6

    invoke-virtual {v2}, Ljava/net/DatagramPacket;->getPort()I

    move-result v2

    invoke-direct {v4, v3, v5, v6, v2}, Ljava/net/DatagramPacket;-><init>([BILjava/net/InetAddress;I)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->val$socket:Ljava/net/DatagramSocket;

    invoke-virtual {v2, v4}, Ljava/net/DatagramSocket;->send(Ljava/net/DatagramPacket;)V
    :try_end_a2
    .catch Ljava/io/IOException; {:try_start_89 .. :try_end_a2} :catch_a9

    goto :goto_a6

    :catchall_a3
    move-exception v0

    :try_start_a4
    monitor-exit v3
    :try_end_a5
    .catchall {:try_start_a4 .. :try_end_a5} :catchall_a3

    :try_start_a5
    throw v0
    :try_end_a6
    .catch Ljava/io/IOException; {:try_start_a5 .. :try_end_a6} :catch_a9

    :cond_a6
    :goto_a6
    nop

    goto/16 :goto_4

    :catch_a9
    move-exception v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->val$socket:Ljava/net/DatagramSocket;

    invoke-virtual {v1}, Ljava/net/DatagramSocket;->isClosed()Z

    move-result v1

    if-nez v1, :cond_db

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$200(Lnet/fdgames/ek/android/lan/LanSessionManager;)Z

    move-result v3

    if-eqz v3, :cond_c4

    const-string v3, "Falha na descoberta LAN: "

    goto :goto_c6

    :cond_c4
    const-string v3, "LAN discovery error: "

    :goto_c6
    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$3;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;
    invoke-static {v3, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$300(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V
    invoke-static {v1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$400(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    :cond_db
    nop

    :cond_dc
    return-void
.end method
