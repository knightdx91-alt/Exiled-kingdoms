.class final Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;
.super Ljava/lang/Object;
.source "LanSessionManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1a
    name = "ClientPeer"
.end annotation


# instance fields
.field private final playerName:Ljava/lang/String;

.field private final socket:Ljava/net/Socket;

.field private final writer:Ljava/io/PrintWriter;


# direct methods
.method private constructor <init>(Ljava/net/Socket;Ljava/io/PrintWriter;Ljava/lang/String;)V
    .registers 4

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->socket:Ljava/net/Socket;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->writer:Ljava/io/PrintWriter;

    iput-object p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;

    return-void
.end method

.method synthetic constructor <init>(Ljava/net/Socket;Ljava/io/PrintWriter;Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$1;)V
    .registers 5

    invoke-direct {p0, p1, p2, p3}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;-><init>(Ljava/net/Socket;Ljava/io/PrintWriter;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$1000(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)V
    .registers 1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->close()V

    return-void
.end method

.method static synthetic access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$900(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;Ljava/lang/String;)V
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->send(Ljava/lang/String;)V

    return-void
.end method

.method private close()V
    .registers 2

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->writer:Ljava/io/PrintWriter;

    if-eqz v0, :cond_9

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->writer:Ljava/io/PrintWriter;

    invoke-virtual {v0}, Ljava/io/PrintWriter;->close()V

    :cond_9
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->socket:Ljava/net/Socket;

    if-eqz v0, :cond_14

    :try_start_d
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->socket:Ljava/net/Socket;

    invoke-virtual {v0}, Ljava/net/Socket;->close()V
    :try_end_12
    .catch Ljava/io/IOException; {:try_start_d .. :try_end_12} :catch_13

    goto :goto_14

    :catch_13
    move-exception v0

    :cond_14
    :goto_14
    return-void
.end method

.method private send(Ljava/lang/String;)V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->writer:Ljava/io/PrintWriter;

    if-eqz v0, :cond_15

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->writer:Ljava/io/PrintWriter;

    invoke-virtual {v0, p1}, Ljava/io/PrintWriter;->print(Ljava/lang/String;)V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->writer:Ljava/io/PrintWriter;

    const/16 v0, 0xa

    invoke-virtual {p1, v0}, Ljava/io/PrintWriter;->print(C)V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->writer:Ljava/io/PrintWriter;

    invoke-virtual {p1}, Ljava/io/PrintWriter;->flush()V

    :cond_15
    return-void
.end method
