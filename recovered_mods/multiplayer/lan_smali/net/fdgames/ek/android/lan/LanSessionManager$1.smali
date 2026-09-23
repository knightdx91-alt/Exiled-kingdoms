.class Lnet/fdgames/ek/android/lan/LanSessionManager$1;
.super Ljava/lang/Object;
.source "LanSessionManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;->scanLan(Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanSessionManager;)V
    .registers 2

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 11

    nop

    const/4 v0, 0x0

    :try_start_2
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->resolvePreferredIpv4Address()Ljava/net/InetAddress;
    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$900(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/net/InetAddress;

    move-result-object v1

    if-eqz v1, :cond_21

    new-instance v2, Ljava/net/DatagramSocket;

    const/4 v3, 0x0

    check-cast v3, Ljava/net/SocketAddress;

    invoke-direct {v2, v3}, Ljava/net/DatagramSocket;-><init>(Ljava/net/SocketAddress;)V

    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Ljava/net/DatagramSocket;->setReuseAddress(Z)V

    new-instance v4, Ljava/net/InetSocketAddress;

    const/4 v5, 0x0

    invoke-direct {v4, v1, v5}, Ljava/net/InetSocketAddress;-><init>(Ljava/net/InetAddress;I)V

    invoke-virtual {v2, v4}, Ljava/net/DatagramSocket;->bind(Ljava/net/SocketAddress;)V

    move-object v1, v2

    goto :goto_26

    :cond_21
    new-instance v1, Ljava/net/DatagramSocket;

    invoke-direct {v1}, Ljava/net/DatagramSocket;-><init>()V
    :try_end_26
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_26} :catch_114
    .catchall {:try_start_2 .. :try_end_26} :catchall_111

    :goto_26
    const/4 v2, 0x1

    :try_start_27
    invoke-virtual {v1, v2}, Ljava/net/DatagramSocket;->setBroadcast(Z)V

    invoke-virtual {v1, v2}, Ljava/net/DatagramSocket;->setReuseAddress(Z)V

    const/16 v2, 0x5dc

    invoke-virtual {v1, v2}, Ljava/net/DatagramSocket;->setSoTimeout(I)V

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "scan udp="

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v1}, Ljava/net/DatagramSocket;->getLocalSocketAddress()Ljava/net/SocketAddress;

    move-result-object v4

    invoke-static {v4}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V
    invoke-static {v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2900(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    const-string v2, "EK_DISCOVER\t1"

    const-string v3, "UTF-8"

    invoke-virtual {v2, v3}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v2

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->collectBroadcastAddresses()Ljava/util/LinkedHashSet;
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$000(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/LinkedHashSet;

    move-result-object v3

    const-string v4, "255.255.255.255"

    invoke-static {v4}, Ljava/net/InetAddress;->getByName(Ljava/lang/String;)Ljava/net/InetAddress;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/util/LinkedHashSet;->add(Ljava/lang/Object;)Z

    invoke-virtual {v3}, Ljava/util/LinkedHashSet;->iterator()Ljava/util/Iterator;

    move-result-object v3

    const/4 v4, 0x0

    :goto_6e
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_8a

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/net/InetAddress;
    :try_end_7a
    .catch Ljava/lang/Exception; {:try_start_27 .. :try_end_7a} :catch_10f
    .catchall {:try_start_27 .. :try_end_7a} :catchall_167

    :try_start_7a
    new-instance v6, Ljava/net/DatagramPacket;

    array-length v7, v2

    const/16 v8, 0x7d7b

    invoke-direct {v6, v2, v7, v5, v8}, Ljava/net/DatagramPacket;-><init>([BILjava/net/InetAddress;I)V

    invoke-virtual {v1, v6}, Ljava/net/DatagramSocket;->send(Ljava/net/DatagramPacket;)V
    :try_end_85
    .catch Ljava/lang/Exception; {:try_start_7a .. :try_end_85} :catch_88
    .catchall {:try_start_7a .. :try_end_85} :catchall_167

    :try_start_85
    add-int/lit8 v4, v4, 0x1

    goto :goto_6e

    :catch_88
    move-exception v5

    goto :goto_6e

    :cond_8a
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "scan probes="

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v5, " udp="

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v1}, Ljava/net/DatagramSocket;->getLocalSocketAddress()Ljava/net/SocketAddress;

    move-result-object v5

    invoke-static {v5}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V
    invoke-static {v5, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2900(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    const-wide/16 v4, 0x1770

    add-long/2addr v2, v4

    :goto_bb
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    cmp-long v6, v4, v2

    if-gez v6, :cond_ed

    const/16 v4, 0x200

    new-array v5, v4, [B

    new-instance v6, Ljava/net/DatagramPacket;

    invoke-direct {v6, v5, v4}, Ljava/net/DatagramPacket;-><init>([BI)V
    :try_end_cc
    .catch Ljava/lang/Exception; {:try_start_85 .. :try_end_cc} :catch_10f
    .catchall {:try_start_85 .. :try_end_cc} :catchall_167

    :try_start_cc
    invoke-virtual {v1, v6}, Ljava/net/DatagramSocket;->receive(Ljava/net/DatagramPacket;)V

    new-instance v4, Ljava/lang/String;

    invoke-virtual {v6}, Ljava/net/DatagramPacket;->getData()[B

    move-result-object v5

    invoke-virtual {v6}, Ljava/net/DatagramPacket;->getLength()I

    move-result v7

    const-string v8, "UTF-8"

    const/4 v9, 0x0

    invoke-direct {v4, v5, v9, v7, v8}, Ljava/lang/String;-><init>([BIILjava/lang/String;)V

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {v6}, Ljava/net/DatagramPacket;->getAddress()Ljava/net/InetAddress;

    move-result-object v6

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->handleDiscoveryReply(Ljava/net/InetAddress;Ljava/lang/String;)V
    invoke-static {v5, v6, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$100(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/InetAddress;Ljava/lang/String;)V
    :try_end_e8
    .catch Ljava/net/SocketException; {:try_start_cc .. :try_end_e8} :catch_eb
    .catch Ljava/io/IOException; {:try_start_cc .. :try_end_e8} :catch_e9
    .catch Ljava/lang/Exception; {:try_start_cc .. :try_end_e8} :catch_10f
    .catchall {:try_start_cc .. :try_end_e8} :catchall_167

    goto :goto_ea

    :catch_e9
    move-exception v4

    :goto_ea
    goto :goto_bb

    :catch_eb
    move-exception v2

    nop

    :cond_ed
    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/DatagramSocket;)V
    invoke-static {v2, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$500(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/DatagramSocket;)V

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;
    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$600(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Object;

    move-result-object v2

    monitor-enter v2

    :try_start_f9
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v1

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$700(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Thread;

    move-result-object v3

    if-ne v1, v3, :cond_10a

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;
    invoke-static {v1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$702(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/Thread;)Ljava/lang/Thread;

    :cond_10a
    monitor-exit v2

    goto :goto_15d

    :catchall_10c
    move-exception v0

    monitor-exit v2
    :try_end_10e
    .catchall {:try_start_f9 .. :try_end_10e} :catchall_10c

    throw v0

    :catch_10f
    move-exception v2

    goto :goto_116

    :catchall_111
    move-exception v2

    move-object v1, v0

    goto :goto_168

    :catch_114
    move-exception v2

    move-object v1, v0

    :goto_116
    :try_start_116
    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z
    invoke-static {v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$200(Lnet/fdgames/ek/android/lan/LanSessionManager;)Z

    move-result v5

    if-eqz v5, :cond_128

    const-string v5, "Falha ao buscar salas LAN: "

    goto :goto_12a

    :cond_128
    const-string v5, "Failed to scan LAN rooms: "

    :goto_12a
    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;
    invoke-static {v5, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$300(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V
    invoke-static {v3, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$400(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    :try_end_13f
    .catchall {:try_start_116 .. :try_end_13f} :catchall_167

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/DatagramSocket;)V
    invoke-static {v2, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$500(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/DatagramSocket;)V

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;
    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$600(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Object;

    move-result-object v2

    monitor-enter v2

    :try_start_14b
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v1

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;
    invoke-static {v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$700(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Thread;

    move-result-object v3

    if-ne v1, v3, :cond_15c

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;
    invoke-static {v1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$702(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/Thread;)Ljava/lang/Thread;

    :cond_15c
    monitor-exit v2
    :try_end_15d
    .catchall {:try_start_14b .. :try_end_15d} :catchall_164

    :goto_15d
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$800(Lnet/fdgames/ek/android/lan/LanSessionManager;)V

    nop

    return-void

    :catchall_164
    move-exception v0

    :try_start_165
    monitor-exit v2
    :try_end_166
    .catchall {:try_start_165 .. :try_end_166} :catchall_164

    throw v0

    :catchall_167
    move-exception v2

    :goto_168
    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/DatagramSocket;)V
    invoke-static {v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$500(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/DatagramSocket;)V

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;
    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$600(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Object;

    move-result-object v1

    monitor-enter v1

    :try_start_174
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v3

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;
    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$700(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Thread;

    move-result-object v4

    if-ne v3, v4, :cond_185

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;
    invoke-static {v3, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$702(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/Thread;)Ljava/lang/Thread;

    :cond_185
    monitor-exit v1
    :try_end_186
    .catchall {:try_start_174 .. :try_end_186} :catchall_18c

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$800(Lnet/fdgames/ek/android/lan/LanSessionManager;)V

    throw v2

    :catchall_18c
    move-exception v0

    :try_start_18d
    monitor-exit v1
    :try_end_18e
    .catchall {:try_start_18d .. :try_end_18e} :catchall_18c

    goto :goto_190

    :goto_18f
    throw v0

    :goto_190
    goto :goto_18f
.end method
