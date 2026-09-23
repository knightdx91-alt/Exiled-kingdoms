.class Lnet/fdgames/ek/android/lan/LanSessionManager$4;
.super Ljava/lang/Object;
.source "LanSessionManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;->startClientReader(Ljava/net/Socket;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

.field final synthetic val$socket:Ljava/net/Socket;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->val$socket:Ljava/net/Socket;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 8

    nop

    const/4 v0, 0x0

    const/4 v1, 0x0

    :try_start_3
    new-instance v2, Ljava/io/BufferedReader;

    new-instance v3, Ljava/io/InputStreamReader;

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->val$socket:Ljava/net/Socket;

    invoke-virtual {v4}, Ljava/net/Socket;->getInputStream()Ljava/io/InputStream;

    move-result-object v4

    const-string v5, "UTF-8"

    invoke-direct {v3, v4, v5}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/lang/String;)V

    invoke-direct {v2, v3}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V
    :try_end_15
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_15} :catch_7d
    .catchall {:try_start_3 .. :try_end_15} :catchall_79

    :goto_15
    :try_start_15
    invoke-virtual {v2}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_21

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->handleServerMessage(Ljava/lang/String;)V
    invoke-static {v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1900(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    goto :goto_15

    :cond_21
    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z
    invoke-static {v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$200(Lnet/fdgames/ek/android/lan/LanSessionManager;)Z

    move-result v4

    if-eqz v4, :cond_2e

    const-string v4, "Conexao com o host encerrada."

    goto :goto_30

    :cond_2e
    const-string v4, "Connection to host closed."

    :goto_30
    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V
    invoke-static {v3, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$400(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    :try_end_33
    .catch Ljava/io/IOException; {:try_start_15 .. :try_end_33} :catch_77
    .catchall {:try_start_15 .. :try_end_33} :catchall_f9

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/BufferedReader;)V
    invoke-static {v3, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2100(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/BufferedReader;)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->val$socket:Ljava/net/Socket;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V
    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2200(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;
    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$600(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Object;

    move-result-object v3

    monitor-enter v3

    :try_start_46
    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->clientSocket:Ljava/net/Socket;
    invoke-static {v2, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2302(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)Ljava/net/Socket;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;
    invoke-static {v2, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2402(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/PrintWriter;)Ljava/io/PrintWriter;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z
    invoke-static {v2, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2502(Lnet/fdgames/ek/android/lan/LanSessionManager;Z)Z

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z
    invoke-static {v2, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2602(Lnet/fdgames/ek/android/lan/LanSessionManager;Z)Z

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1500(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2700(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/LinkedHashMap;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;
    invoke-static {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2802(Lnet/fdgames/ek/android/lan/LanSessionManager;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    monitor-exit v3

    goto/16 :goto_ef

    :catchall_74
    move-exception v0

    monitor-exit v3
    :try_end_76
    .catchall {:try_start_46 .. :try_end_76} :catchall_74

    throw v0

    :catch_77
    move-exception v3

    goto :goto_7f

    :catchall_79
    move-exception v3

    move-object v2, v1

    goto/16 :goto_fa

    :catch_7d
    move-exception v3

    move-object v2, v1

    :goto_7f
    :try_start_7f
    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->isSocketClosedException(Ljava/io/IOException;)Z
    invoke-static {v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2000(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/IOException;)Z

    move-result v4

    if-nez v4, :cond_b0

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v6, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z
    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$200(Lnet/fdgames/ek/android/lan/LanSessionManager;)Z

    move-result v6

    if-eqz v6, :cond_99

    const-string v6, "Conexao com o host perdida: "

    goto :goto_9b

    :cond_99
    const-string v6, "Connection to host lost: "

    :goto_9b
    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    iget-object v6, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;
    invoke-static {v6, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$300(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V
    invoke-static {v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$400(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    :try_end_b0
    .catchall {:try_start_7f .. :try_end_b0} :catchall_f9

    :cond_b0
    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/BufferedReader;)V
    invoke-static {v3, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2100(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/BufferedReader;)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->val$socket:Ljava/net/Socket;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V
    invoke-static {v2, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2200(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;
    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$600(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Object;

    move-result-object v3

    monitor-enter v3

    :try_start_c3
    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->clientSocket:Ljava/net/Socket;
    invoke-static {v2, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2302(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)Ljava/net/Socket;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;
    invoke-static {v2, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2402(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/PrintWriter;)Ljava/io/PrintWriter;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z
    invoke-static {v2, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2502(Lnet/fdgames/ek/android/lan/LanSessionManager;Z)Z

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z
    invoke-static {v2, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2602(Lnet/fdgames/ek/android/lan/LanSessionManager;Z)Z

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1500(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2700(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/LinkedHashMap;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;
    invoke-static {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2802(Lnet/fdgames/ek/android/lan/LanSessionManager;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    monitor-exit v3
    :try_end_ef
    .catchall {:try_start_c3 .. :try_end_ef} :catchall_f6

    :goto_ef
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$800(Lnet/fdgames/ek/android/lan/LanSessionManager;)V

    nop

    return-void

    :catchall_f6
    move-exception v0

    :try_start_f7
    monitor-exit v3
    :try_end_f8
    .catchall {:try_start_f7 .. :try_end_f8} :catchall_f6

    throw v0

    :catchall_f9
    move-exception v3

    :goto_fa
    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/BufferedReader;)V
    invoke-static {v4, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2100(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/BufferedReader;)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->val$socket:Ljava/net/Socket;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V
    invoke-static {v2, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2200(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;
    invoke-static {v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$600(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Object;

    move-result-object v2

    monitor-enter v2

    :try_start_10d
    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->clientSocket:Ljava/net/Socket;
    invoke-static {v4, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2302(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)Ljava/net/Socket;

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;
    invoke-static {v4, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2402(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/PrintWriter;)Ljava/io/PrintWriter;

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z
    invoke-static {v4, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2502(Lnet/fdgames/ek/android/lan/LanSessionManager;Z)Z

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z
    invoke-static {v4, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2602(Lnet/fdgames/ek/android/lan/LanSessionManager;Z)Z

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$1500(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2700(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/LinkedHashMap;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # setter for: Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;
    invoke-static {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$2802(Lnet/fdgames/ek/android/lan/LanSessionManager;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    monitor-exit v2
    :try_end_139
    .catchall {:try_start_10d .. :try_end_139} :catchall_13f

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$4;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->access$800(Lnet/fdgames/ek/android/lan/LanSessionManager;)V

    throw v3

    :catchall_13f
    move-exception v0

    :try_start_140
    monitor-exit v2
    :try_end_141
    .catchall {:try_start_140 .. :try_end_141} :catchall_13f

    goto :goto_143

    :goto_142
    throw v0

    :goto_143
    goto :goto_142
.end method
