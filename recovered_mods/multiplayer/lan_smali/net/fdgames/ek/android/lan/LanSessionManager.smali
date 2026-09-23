.class public final Lnet/fdgames/ek/android/lan/LanSessionManager;
.super Ljava/lang/Object;
.source "LanSessionManager.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;,
        Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;,
        Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;,
        Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;,
        Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;
    }
.end annotation


# static fields
.field public static final CHAT_PORT:I = 0x7d7c

.field private static final DISCOVERY_MAGIC:Ljava/lang/String; = "EK_DISCOVER"

.field public static final DISCOVERY_PORT:I = 0x7d7b

.field private static final HOST_MAGIC:Ljava/lang/String; = "EK_HOST"

.field private static final MAX_CHAT_HISTORY:I = 0x78

.field private static final MAX_DISCOVERY_RESULTS:I = 0x14

.field private static final MAX_PLAYERS:I = 0x6

.field private static instance:Lnet/fdgames/ek/android/lan/LanSessionManager;


# instance fields
.field private acceptThread:Ljava/lang/Thread;

.field private final appContext:Landroid/content/Context;

.field private final chatHistory:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private clientReadThread:Ljava/lang/Thread;

.field private clientSocket:Ljava/net/Socket;

.field private clientWriter:Ljava/io/PrintWriter;

.field private connected:Z

.field private final discoveryByKey:Ljava/util/LinkedHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/LinkedHashMap<",
            "Ljava/lang/String;",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;",
            ">;"
        }
    .end annotation
.end field

.field private final discoveryResults:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;",
            ">;"
        }
    .end annotation
.end field

.field private hostAddress:Ljava/lang/String;

.field private hostDiscoverySocket:Ljava/net/DatagramSocket;

.field private hostDiscoveryThread:Ljava/lang/Thread;

.field private final hostPeers:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;",
            ">;"
        }
    .end annotation
.end field

.field private hostPort:I

.field private hostServerSocket:Ljava/net/ServerSocket;

.field private hosting:Z

.field private lastAppliedPlayerDamageLevelId:Ljava/lang/String;

.field private lastAppliedPlayerDamageSeq:I

.field private lastNpcStateData:Ljava/lang/String;

.field private localPlayerName:Ljava/lang/String;

.field private localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

.field private final lock:Ljava/lang/Object;

.field private final mainHandler:Landroid/os/Handler;

.field private maxPlayers:I

.field private nextPlayerDamageSeq:I

.field private final peerStates:Ljava/util/LinkedHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/LinkedHashMap<",
            "Ljava/lang/String;",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;",
            ">;"
        }
    .end annotation
.end field

.field private final pendingCombatPackets:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private final pendingPlayerDamagePackets:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private final players:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private scanThread:Ljava/lang/Thread;

.field private sessionName:Ljava/lang/String;

.field private statusDiag:Ljava/lang/String;

.field private stopRequested:Z

.field private uiListener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

.field private unreadChatCount:I


# direct methods
.method private constructor <init>(Landroid/content/Context;)V
    .registers 4

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->mainHandler:Landroid/os/Handler;

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->chatHistory:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryResults:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPeers:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingCombatPackets:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingPlayerDamagePackets:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryByKey:Ljava/util/LinkedHashMap;

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    const-string v0, "lan_default_player_name"

    const-string v1, "Player"

    invoke-direct {p0, v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    const-string v0, ""

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->statusDiag:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    const/16 v1, 0x7d7c

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    const/4 v1, 0x6

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->maxPlayers:I

    const/4 v1, 0x1

    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->nextPlayerDamageSeq:I

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->appContext:Landroid/content/Context;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastAppliedPlayerDamageLevelId:Ljava/lang/String;

    return-void
.end method

.method static synthetic access$000(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/LinkedHashSet;
    .registers 1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->collectBroadcastAddresses()Ljava/util/LinkedHashSet;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$100(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/InetAddress;Ljava/lang/String;)V
    .registers 3

    invoke-direct {p0, p1, p2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->handleDiscoveryReply(Ljava/net/InetAddress;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$1100(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)V
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->handleIncomingClient(Ljava/net/Socket;)V

    return-void
.end method

.method static synthetic access$1200(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/String;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$1300(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)Ljava/lang/String;
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$1400(Lnet/fdgames/ek/android/lan/LanSessionManager;)I
    .registers 1

    iget p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    return p0
.end method

.method static synthetic access$1500(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/ArrayList;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    return-object p0
.end method

.method static synthetic access$1600(Lnet/fdgames/ek/android/lan/LanSessionManager;)I
    .registers 1

    iget p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->maxPlayers:I

    return p0
.end method

.method static synthetic access$1900(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->handleServerMessage(Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$200(Lnet/fdgames/ek/android/lan/LanSessionManager;)Z
    .registers 1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p0

    return p0
.end method

.method static synthetic access$2000(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/IOException;)Z
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSocketClosedException(Ljava/io/IOException;)Z

    move-result p0

    return p0
.end method

.method static synthetic access$2100(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/BufferedReader;)V
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/BufferedReader;)V

    return-void
.end method

.method static synthetic access$2200(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)V
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V

    return-void
.end method

.method static synthetic access$2302(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)Ljava/net/Socket;
    .registers 2

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientSocket:Ljava/net/Socket;

    return-object p1
.end method

.method static synthetic access$2402(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/io/PrintWriter;)Ljava/io/PrintWriter;
    .registers 2

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    return-object p1
.end method

.method static synthetic access$2502(Lnet/fdgames/ek/android/lan/LanSessionManager;Z)Z
    .registers 2

    iput-boolean p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    return p1
.end method

.method static synthetic access$2602(Lnet/fdgames/ek/android/lan/LanSessionManager;Z)Z
    .registers 2

    iput-boolean p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    return p1
.end method

.method static synthetic access$2700(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/util/LinkedHashMap;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    return-object p0
.end method

.method static synthetic access$2802(Lnet/fdgames/ek/android/lan/LanSessionManager;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;
    .registers 2

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    return-object p1
.end method

.method static synthetic access$2900(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$300(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/Throwable;)Ljava/lang/String;
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$400(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$500(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/DatagramSocket;)V
    .registers 2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/DatagramSocket;)V

    return-void
.end method

.method static synthetic access$600(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Object;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    return-object p0
.end method

.method static synthetic access$700(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/lang/Thread;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;

    return-object p0
.end method

.method static synthetic access$702(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/Thread;)Ljava/lang/Thread;
    .registers 2

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;

    return-object p1
.end method

.method static synthetic access$800(Lnet/fdgames/ek/android/lan/LanSessionManager;)V
    .registers 1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void
.end method

.method static synthetic access$900(Lnet/fdgames/ek/android/lan/LanSessionManager;)Ljava/net/InetAddress;
    .registers 1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->resolvePreferredIpv4Address()Ljava/net/InetAddress;

    move-result-object p0

    return-object p0
.end method

.method private addChatLineLocked(Ljava/lang/String;Ljava/lang/String;)V
    .registers 4

    if-eqz p2, :cond_28

    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_d

    goto :goto_28

    :cond_d
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v0, ": "

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->appendChatLocked(Ljava/lang/String;)V

    return-void

    :cond_28
    :goto_28
    return-void
.end method

.method private addHeuristicBroadcasts(Ljava/util/LinkedHashSet;Ljava/lang/String;)V
    .registers 11

    if-eqz p1, :cond_7e

    if-eqz p2, :cond_7e

    :try_start_4
    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_7e

    invoke-static {p2}, Ljava/net/InetAddress;->getByName(Ljava/lang/String;)Ljava/net/InetAddress;

    move-result-object v0

    instance-of v1, v0, Ljava/net/Inet4Address;

    if-eqz v1, :cond_7e

    invoke-virtual {v0}, Ljava/net/InetAddress;->getAddress()[B

    move-result-object v0

    if-eqz v0, :cond_7e

    array-length v1, v0

    const/4 v2, 0x4

    if-eq v1, v2, :cond_21

    goto :goto_7e

    :cond_21
    invoke-virtual {v0}, [B->clone()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [B

    const/4 v2, 0x3

    const/4 v3, -0x1

    aput-byte v3, v1, v2

    invoke-static {v1}, Ljava/net/InetAddress;->getByAddress([B)Ljava/net/InetAddress;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/util/LinkedHashSet;->add(Ljava/lang/Object;)Z

    const/4 v1, 0x0

    aget-byte v2, v0, v1

    and-int/lit16 v2, v2, 0xff

    const/4 v4, 0x1

    aget-byte v5, v0, v4

    and-int/lit16 v5, v5, 0xff

    const/16 v6, 0xa

    if-eq v2, v6, :cond_48

    const/16 v6, 0xc0

    if-ne v2, v6, :cond_5b

    const/16 v6, 0xa8

    if-ne v5, v6, :cond_5b

    :cond_48
    invoke-virtual {v0}, [B->clone()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, [B

    const/4 v7, 0x2

    aput-byte v3, v6, v7

    const/4 v7, 0x3

    aput-byte v3, v6, v7

    invoke-static {v6}, Ljava/net/InetAddress;->getByAddress([B)Ljava/net/InetAddress;

    move-result-object v6

    invoke-virtual {p1, v6}, Ljava/util/LinkedHashSet;->add(Ljava/lang/Object;)Z

    :cond_5b
    const/16 v6, 0xac

    if-ne v2, v6, :cond_7e

    const/16 v6, 0x10

    if-lt v5, v6, :cond_7e

    const/16 v6, 0x1f

    if-le v5, v6, :cond_68

    goto :goto_7e

    :cond_68
    invoke-virtual {v0}, [B->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [B

    const/4 v1, 0x2

    aput-byte v3, v0, v1

    const/4 v1, 0x3

    aput-byte v3, v0, v1

    invoke-static {v0}, Ljava/net/InetAddress;->getByAddress([B)Ljava/net/InetAddress;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/LinkedHashSet;->add(Ljava/lang/Object;)Z
    :try_end_7b
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_7b} :catch_7c

    goto :goto_7e

    :catch_7c
    move-exception v0

    nop

    :cond_7e
    :goto_7e
    return-void
.end method

.method private addSystemLineLocked(Ljava/lang/String;)V
    .registers 4

    if-eqz p1, :cond_39

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_d

    goto :goto_39

    :cond_d
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "["

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v1

    if-eqz v1, :cond_21

    const-string v1, "Sistema"

    goto :goto_23

    :cond_21
    const-string v1, "System"

    :goto_23
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "] "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->appendChatLocked(Ljava/lang/String;)V

    return-void

    :cond_39
    :goto_39
    return-void
.end method

.method private appendChatLocked(Ljava/lang/String;)V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->chatHistory:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_5
    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->chatHistory:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    const/16 v0, 0x78

    if-le p1, v0, :cond_16

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->chatHistory:Ljava/util/ArrayList;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    goto :goto_5

    :cond_16
    return-void
.end method

.method private appendDamagesFromCsv(Lnet/fdgames/GameEntities/Helpers/DamageData;Ljava/lang/String;)V
    .registers 13

    if-eqz p1, :cond_4b

    if-nez p2, :cond_5

    return-void

    :cond_5
    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v9

    if-eqz v9, :cond_10

    return-void

    :cond_10
    const-string v8, ";"

    invoke-virtual {p2, v8}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    :goto_18
    if-ge v2, v1, :cond_4b

    aget-object v3, v0, v2

    if-eqz v3, :cond_48

    const-string v8, ":"

    const/4 v9, -0x1

    invoke-virtual {v3, v8, v9}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v4

    array-length v9, v4

    const/4 v6, 0x3

    if-lt v9, v6, :cond_48

    const/4 v8, 0x0

    aget-object v8, v4, v8

    invoke-static {v8}, Lnet/fdgames/GameEntities/Helpers/Damage;->b(Ljava/lang/String;)Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;

    move-result-object v5

    const/4 v8, 0x1

    aget-object v8, v4, v8

    const/4 v6, 0x0

    invoke-direct {p0, v8, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v6

    const/4 v8, 0x2

    aget-object v8, v4, v8

    const/4 v7, 0x0

    invoke-direct {p0, v8, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v7

    if-eqz v7, :cond_44

    const/4 v7, 0x1

    goto :goto_45

    :cond_44
    const/4 v7, 0x0

    :goto_45
    invoke-virtual {p1, v5, v6, v7}, Lnet/fdgames/GameEntities/Helpers/DamageData;->a(Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;IZ)V

    :cond_48
    add-int/lit8 v2, v2, 0x1

    goto :goto_18

    :cond_4b
    return-void
.end method

.method private appendProcsFromCsv(Lnet/fdgames/GameEntities/Helpers/DamageData;Ljava/lang/String;)V
    .registers 14

    if-eqz p1, :cond_47

    if-nez p2, :cond_5

    return-void

    :cond_5
    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v9

    if-eqz v9, :cond_10

    return-void

    :cond_10
    const-string v8, ";"

    invoke-virtual {p2, v8}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    :goto_18
    if-ge v2, v1, :cond_47

    aget-object v3, v0, v2

    if-eqz v3, :cond_44

    const-string v8, ":"

    const/4 v9, -0x1

    invoke-virtual {v3, v8, v9}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v4

    array-length v9, v4

    const/4 v6, 0x2

    if-lt v9, v6, :cond_44

    const/4 v8, 0x0

    aget-object v8, v4, v8

    invoke-static {v8}, Lnet/fdgames/GameEntities/Helpers/DamageEffect$EffectType;->valueOf(Ljava/lang/String;)Lnet/fdgames/GameEntities/Helpers/DamageEffect$EffectType;

    move-result-object v5

    const/4 v8, 0x1

    aget-object v8, v4, v8

    const/4 v6, 0x0

    invoke-direct {p0, v8, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v6

    new-instance v7, Lnet/fdgames/GameEntities/Helpers/DamageEffect;

    const/16 v9, 0x64

    invoke-direct {v7, v5, v6, v9}, Lnet/fdgames/GameEntities/Helpers/DamageEffect;-><init>(Lnet/fdgames/GameEntities/Helpers/DamageEffect$EffectType;II)V

    iget-object v10, p1, Lnet/fdgames/GameEntities/Helpers/DamageData;->procs:Ljava/util/ArrayList;

    invoke-virtual {v10, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_44
    add-int/lit8 v2, v2, 0x1

    goto :goto_18

    :cond_47
    return-void
.end method

.method private appendSerializedFollowerState(Ljava/lang/StringBuilder;Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)V
    .registers 5

    if-eqz p1, :cond_4

    if-nez p3, :cond_5

    :cond_4
    return-void

    :cond_5
    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v0, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v0, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v0, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v0, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v0, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v0, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v0, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v0, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v0, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget p2, p3, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    return-void
.end method

.method private applyAuthoritativeVitals(Lnet/fdgames/GameEntities/Character;II)V
    .registers 6

    if-eqz p1, :cond_16

    iget-object v0, p1, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;

    if-eqz v0, :cond_16

    iget-object v0, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->stats:Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;

    if-eqz v0, :cond_16

    move v1, p2

    if-gez v1, :cond_e

    const/4 v1, 0x0

    :cond_e
    iput v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingHP:I

    move v1, p3

    if-gez v1, :cond_14

    const/4 v1, 0x0

    :cond_14
    iput v1, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingMana:I

    :cond_16
    return-void
.end method

.method private applyCombatPacket(Ljava/lang/String;)V
    .registers 15

    if-eqz p1, :cond_87

    const-string v0, "PACT\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_b

    goto :goto_87

    :cond_b
    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const/4 v0, 0x2

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    const/4 v2, 0x0

    invoke-direct {p0, v0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v2

    const/4 v0, 0x3

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    const/4 v0, 0x4

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    const/4 v4, 0x0

    invoke-direct {p0, v0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v4

    const/4 v0, 0x5

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    const/4 v0, 0x6

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    const/4 v6, -0x1

    invoke-direct {p0, v0, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v6

    const/4 v0, 0x7

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    const/4 v7, -0x1

    invoke-direct {p0, v0, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v7

    const/16 v0, 0x8

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    const/16 v0, 0x9

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    const/16 v0, 0xa

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    const/4 v10, 0x0

    invoke-direct {p0, v0, v10}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v10

    const/16 v0, 0xb

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    const/4 v11, 0x0

    invoke-direct {p0, v0, v11}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v11

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    if-eqz p1, :cond_83

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v12

    if-eqz v12, :cond_83

    return-void

    :cond_83
    invoke-static/range {v1 .. v11}, Lnet/fdgames/ek/android/lan/LanGameBridge;->receiveRemoteCombat(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;IILjava/lang/String;Ljava/lang/String;II)V

    return-void

    :cond_87
    :goto_87
    return-void
.end method

.method private applyPlayerDamagePacket(Ljava/lang/String;)V
    .registers 14

    if-eqz p1, :cond_d8

    const-string v0, "PDMG2\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_d8

    const-string v0, "\t"

    const/4 v1, -0x1

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v0

    array-length v1, v0

    const/16 v2, 0xd

    if-lt v1, v2, :cond_d8

    const/4 v1, 0x1

    aget-object v1, v0, v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x2

    aget-object v2, v0, v2

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x3

    aget-object v3, v0, v3

    const/4 v4, 0x0

    invoke-direct {p0, v3, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    const/4 v4, 0x4

    aget-object v4, v0, v4

    const/4 v5, 0x0

    invoke-direct {p0, v4, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v4

    const/16 v5, 0xb

    aget-object v5, v0, v5

    const/4 v6, 0x0

    invoke-direct {p0, v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v5

    const/16 v6, 0xc

    aget-object v6, v0, v6

    const/4 v7, 0x0

    invoke-direct {p0, v6, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v6

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v7

    if-eqz v7, :cond_d8

    iget-object v8, v7, Lnet/fdgames/GameWorld/GameData;->player:Lnet/fdgames/GameEntities/Final/Player;

    if-eqz v8, :cond_d8

    iget-object v9, v7, Lnet/fdgames/GameWorld/GameData;->CurrentLevel:Ljava/lang/String;

    if-eqz v9, :cond_5e

    invoke-virtual {v9}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v7

    if-eqz v7, :cond_66

    :cond_5e
    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v7

    if-eqz v7, :cond_d8

    iget-object v9, v7, Lnet/fdgames/GameWorld/GameData;->currentMapName:Ljava/lang/String;

    :cond_66
    iget-object v10, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v10

    :try_start_69
    iget-object v11, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    if-eqz v11, :cond_77

    invoke-virtual {v11}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v11}, Ljava/lang/String;->isEmpty()Z

    move-result v7

    if-eqz v7, :cond_7b

    :cond_77
    invoke-virtual {v8}, Lnet/fdgames/GameEntities/Character;->getName()Ljava/lang/String;

    move-result-object v11

    :cond_7b
    if-eqz v11, :cond_d9

    invoke-virtual {v1, v11}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_d9

    if-eqz v9, :cond_d9

    invoke-virtual {v9}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v7

    if-nez v7, :cond_d9

    invoke-virtual {v9, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_d9

    iget-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastAppliedPlayerDamageLevelId:Ljava/lang/String;

    if-eqz v7, :cond_9f

    invoke-virtual {v7, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v11

    if-nez v11, :cond_a4

    :cond_9f
    iput-object v9, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastAppliedPlayerDamageLevelId:Ljava/lang/String;

    const/4 v11, 0x0

    iput v11, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastAppliedPlayerDamageSeq:I

    :cond_a4
    iget v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastAppliedPlayerDamageSeq:I

    if-le v3, v7, :cond_d9

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastAppliedPlayerDamageLevelId:Ljava/lang/String;

    iput v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastAppliedPlayerDamageSeq:I

    monitor-exit v10
    :try_end_ad
    .catchall {:try_start_69 .. :try_end_ad} :catchall_db

    const/4 v7, 0x6

    aget-object v7, v0, v7

    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    if-eqz v7, :cond_c1

    invoke-virtual {v7}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v9

    if-nez v9, :cond_c1

    goto :goto_c3

    :cond_c1
    const-string v7, "HIT"

    :goto_c3
    const/4 v9, 0x5

    aget-object v9, v0, v9

    invoke-direct {p0, v9}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    if-nez v9, :cond_ce

    const-string v9, ""

    :cond_ce
    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->buildDamageDataFromParts([Ljava/lang/String;)Lnet/fdgames/GameEntities/Helpers/DamageData;

    move-result-object v10

    invoke-virtual {v8, v7, v4, v9, v10}, Lnet/fdgames/GameEntities/Character;->v(Ljava/lang/String;ILjava/lang/String;Lnet/fdgames/GameEntities/Helpers/DamageData;)V

    invoke-direct {p0, v8, v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->applyAuthoritativeVitals(Lnet/fdgames/GameEntities/Character;II)V

    :cond_d8
    return-void

    :cond_d9
    :try_start_d9
    monitor-exit v10
    :try_end_da
    .catchall {:try_start_d9 .. :try_end_da} :catchall_db

    return-void

    :catchall_db
    move-exception v0

    monitor-exit v10

    throw v0
.end method

.method private broadcastToClients(Ljava/lang/String;)V
    .registers 5

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    new-instance v1, Ljava/util/ArrayList;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPeers:Ljava/util/ArrayList;

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    monitor-exit v0
    :try_end_b
    .catchall {:try_start_3 .. :try_end_b} :catchall_20

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_f
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1f

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->send(Ljava/lang/String;)V
    invoke-static {v1, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$900(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;Ljava/lang/String;)V

    goto :goto_f

    :cond_1f
    return-void

    :catchall_20
    move-exception p1

    :try_start_21
    monitor-exit v0
    :try_end_22
    .catchall {:try_start_21 .. :try_end_22} :catchall_20

    goto :goto_24

    :goto_23
    throw p1

    :goto_24
    goto :goto_23
.end method

.method private broadcastToClientsExcept(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)V
    .registers 6

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    new-instance v1, Ljava/util/ArrayList;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPeers:Ljava/util/ArrayList;

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    monitor-exit v0
    :try_end_b
    .catchall {:try_start_3 .. :try_end_b} :catchall_22

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_f
    :goto_f
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_21

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;

    if-eq v1, p2, :cond_f

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->send(Ljava/lang/String;)V
    invoke-static {v1, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$900(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;Ljava/lang/String;)V

    goto :goto_f

    :cond_21
    return-void

    :catchall_22
    move-exception p1

    :try_start_23
    monitor-exit v0
    :try_end_24
    .catchall {:try_start_23 .. :try_end_24} :catchall_22

    goto :goto_26

    :goto_25
    throw p1

    :goto_26
    goto :goto_25
.end method

.method private buildDamageDataFromParts([Ljava/lang/String;)Lnet/fdgames/GameEntities/Helpers/DamageData;
    .registers 6

    new-instance v0, Lnet/fdgames/GameEntities/Helpers/DamageData;

    invoke-direct {v0}, Lnet/fdgames/GameEntities/Helpers/DamageData;-><init>()V

    if-eqz p1, :cond_35

    const/4 v1, 0x7

    aget-object v1, p1, v1

    const/4 v2, 0x0

    invoke-direct {p0, v1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v1

    if-eqz v1, :cond_14

    const/4 v1, 0x1

    iput-boolean v1, v0, Lnet/fdgames/GameEntities/Helpers/DamageData;->critical:Z

    :cond_14
    const/16 v1, 0x8

    aget-object v1, p1, v1

    const/4 v2, -0x1

    invoke-direct {p0, v1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v1

    iput v1, v0, Lnet/fdgames/GameEntities/Helpers/DamageData;->weapon_item_id:I

    const/16 v1, 0x9

    aget-object v1, p1, v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->appendDamagesFromCsv(Lnet/fdgames/GameEntities/Helpers/DamageData;Ljava/lang/String;)V

    const/16 v1, 0xa

    aget-object v1, p1, v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->appendProcsFromCsv(Lnet/fdgames/GameEntities/Helpers/DamageData;Ljava/lang/String;)V

    :cond_35
    return-object v0
.end method

.method private buildStateTextLocked()Ljava/lang/String;
    .registers 11

    iget-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    const-string v1, ")"

    const-string v2, "/"

    const-string v3, " ("

    const-string v4, ":"

    const-string v5, " @ "

    const-string v6, " "

    if-eqz v0, :cond_6b

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1b

    const-string v0, "0.0.0.0"

    goto :goto_1d

    :cond_1b
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    :goto_1d
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "lan_state_hosting"

    const-string v9, "Hosting"

    invoke-direct {p0, v8, v9}, Lnet/fdgames/ek/android/lan/LanSessionManager;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    iget-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->maxPlayers:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_6b
    iget-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    if-eqz v0, :cond_ca

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_7a

    const-string v0, "?"

    goto :goto_7c

    :cond_7a
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    :goto_7c
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "lan_state_connected"

    const-string v9, "Connected to"

    invoke-direct {p0, v8, v9}, Lnet/fdgames/ek/android/lan/LanSessionManager;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    iget-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->maxPlayers:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_ca
    const-string v0, "lan_status_offline"

    const-string v1, "LAN offline. Host a room or join by IP."

    invoke-direct {p0, v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private clearDiscoveryLocked()V
    .registers 2

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryByKey:Ljava/util/LinkedHashMap;

    invoke-virtual {v0}, Ljava/util/LinkedHashMap;->clear()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryResults:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    return-void
.end method

.method private closeQuietly(Ljava/io/BufferedReader;)V
    .registers 2

    if-eqz p1, :cond_7

    :try_start_2
    invoke-virtual {p1}, Ljava/io/BufferedReader;->close()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_5} :catch_6

    goto :goto_7

    :catch_6
    move-exception p1

    :cond_7
    :goto_7
    return-void
.end method

.method private closeQuietly(Ljava/io/PrintWriter;)V
    .registers 2

    if-eqz p1, :cond_5

    invoke-virtual {p1}, Ljava/io/PrintWriter;->close()V

    :cond_5
    return-void
.end method

.method private closeQuietly(Ljava/net/DatagramSocket;)V
    .registers 2

    if-eqz p1, :cond_5

    invoke-virtual {p1}, Ljava/net/DatagramSocket;->close()V

    :cond_5
    return-void
.end method

.method private closeQuietly(Ljava/net/ServerSocket;)V
    .registers 2

    if-eqz p1, :cond_7

    :try_start_2
    invoke-virtual {p1}, Ljava/net/ServerSocket;->close()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_5} :catch_6

    goto :goto_7

    :catch_6
    move-exception p1

    :cond_7
    :goto_7
    return-void
.end method

.method private closeQuietly(Ljava/net/Socket;)V
    .registers 2

    if-eqz p1, :cond_7

    :try_start_2
    invoke-virtual {p1}, Ljava/net/Socket;->close()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_5} :catch_6

    goto :goto_7

    :catch_6
    move-exception p1

    :cond_7
    :goto_7
    return-void
.end method

.method private collectBroadcastAddresses()Ljava/util/LinkedHashSet;
    .registers 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/LinkedHashSet<",
            "Ljava/net/InetAddress;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/LinkedHashSet;

    invoke-direct {v0}, Ljava/util/LinkedHashSet;-><init>()V

    :try_start_5
    invoke-static {}, Ljava/net/NetworkInterface;->getNetworkInterfaces()Ljava/util/Enumeration;

    move-result-object v1

    if-nez v1, :cond_c

    return-object v0

    :cond_c
    :goto_c
    invoke-interface {v1}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v2

    if-eqz v2, :cond_4f

    invoke-interface {v1}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/net/NetworkInterface;

    if-eqz v2, :cond_c

    invoke-virtual {v2}, Ljava/net/NetworkInterface;->isUp()Z

    move-result v3

    if-eqz v3, :cond_c

    invoke-virtual {v2}, Ljava/net/NetworkInterface;->isLoopback()Z

    move-result v3

    if-eqz v3, :cond_27

    goto :goto_c

    :cond_27
    invoke-virtual {v2}, Ljava/net/NetworkInterface;->getInterfaceAddresses()Ljava/util/List;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_2f
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_4e

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/net/InterfaceAddress;

    if-eqz v3, :cond_4d

    invoke-virtual {v3}, Ljava/net/InterfaceAddress;->getBroadcast()Ljava/net/InetAddress;

    move-result-object v4

    if-nez v4, :cond_48

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->computeDirectedBroadcast(Ljava/net/InterfaceAddress;)Ljava/net/InetAddress;

    move-result-object v3

    move-object v4, v3

    :cond_48
    if-eqz v4, :cond_4d

    invoke-virtual {v0, v4}, Ljava/util/LinkedHashSet;->add(Ljava/lang/Object;)Z
    :try_end_4d
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_4d} :catch_50

    :cond_4d
    goto :goto_2f

    :cond_4e
    goto :goto_c

    :cond_4f
    goto :goto_51

    :catch_50
    move-exception v1

    :goto_51
    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->findLocalIpv4()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addHeuristicBroadcasts(Ljava/util/LinkedHashSet;Ljava/lang/String;)V

    return-object v0
.end method

.method private computeDirectedBroadcast(Ljava/net/InterfaceAddress;)Ljava/net/InetAddress;
    .registers 13

    const/4 v0, 0x0

    if-nez p1, :cond_4

    return-object v0

    :cond_4
    :try_start_4
    invoke-virtual {p1}, Ljava/net/InterfaceAddress;->getAddress()Ljava/net/InetAddress;

    move-result-object v1

    instance-of v2, v1, Ljava/net/Inet4Address;

    if-eqz v2, :cond_63

    invoke-virtual {p1}, Ljava/net/InterfaceAddress;->getNetworkPrefixLength()S

    move-result v2

    if-ltz v2, :cond_63

    const/16 v3, 0x20

    if-le v2, v3, :cond_17

    goto :goto_63

    :cond_17
    invoke-virtual {v1}, Ljava/net/InetAddress;->getAddress()[B

    move-result-object v1

    if-eqz v1, :cond_63

    array-length v3, v1

    const/4 v4, 0x4

    if-ne v3, v4, :cond_63

    const/4 v3, 0x0

    aget-byte v4, v1, v3

    and-int/lit16 v4, v4, 0xff

    shl-int/lit8 v4, v4, 0x18

    const/4 v5, 0x1

    aget-byte v6, v1, v5

    and-int/lit16 v6, v6, 0xff

    shl-int/lit8 v6, v6, 0x10

    or-int/2addr v4, v6

    const/4 v6, 0x2

    aget-byte v7, v1, v6

    and-int/lit16 v7, v7, 0xff

    shl-int/lit8 v7, v7, 0x8

    or-int/2addr v4, v7

    const/4 v7, 0x3

    aget-byte v8, v1, v7

    and-int/lit16 v8, v8, 0xff

    or-int/2addr v4, v8

    if-nez v2, :cond_42

    const/4 v8, 0x0

    goto :goto_47

    :cond_42
    rsub-int/lit8 v8, v2, 0x20

    const/4 v9, -0x1

    shl-int v8, v9, v8

    :goto_47
    not-int v9, v8

    or-int/2addr v4, v9

    const/4 v8, 0x4

    new-array v8, v8, [B

    shr-int/lit8 v9, v4, 0x18

    int-to-byte v9, v9

    aput-byte v9, v8, v3

    shr-int/lit8 v9, v4, 0x10

    int-to-byte v9, v9

    aput-byte v9, v8, v5

    shr-int/lit8 v9, v4, 0x8

    int-to-byte v9, v9

    aput-byte v9, v8, v6

    int-to-byte v4, v4

    aput-byte v4, v8, v7

    invoke-static {v8}, Ljava/net/InetAddress;->getByAddress([B)Ljava/net/InetAddress;

    move-result-object v0
    :try_end_62
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_62} :catch_64

    return-object v0

    :cond_63
    :goto_63
    return-object v0

    :catch_64
    move-exception v1

    return-object v0
.end method

.method private copyFollowerState(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;
    .registers 4

    const/4 v0, 0x0

    if-nez p1, :cond_4

    return-object v0

    :cond_4
    new-instance v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    invoke-direct {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;-><init>()V

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    iget p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    iput p1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    return-object v0
.end method

.method private copyState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;
    .registers 6

    new-instance v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    invoke-direct {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;-><init>()V

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slot:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slot:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->level:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->level:I

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->classEnumName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->classEnumName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->raceName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->raceName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->portraitIndex:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->portraitIndex:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotBodyItemId:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotBodyItemId:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotFeetItemId:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotFeetItemId:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHandsItemId:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHandsItemId:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHeadItemId:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHeadItemId:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotLegsItemId:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotLegsItemId:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotMainhandItemId:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotMainhandItemId:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotOffhandItemId:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotOffhandItemId:I

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteIndexCsv:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteIndexCsv:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->facingName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->facingName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stateTimeMs:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stateTimeMs:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedX:F

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedX:F

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedY:F

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedY:F

    iget-wide v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    iput-wide v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionStateTimeMs:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionStateTimeMs:I

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpawnId:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpawnId:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonTag:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonTag:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonName:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonX:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonX:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonY:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonY:I

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteIndexCsv:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteIndexCsv:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonFacingName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonFacingName:Ljava/lang/String;

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonActorStateName:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonActorStateName:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonStateTimeMs:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonStateTimeMs:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionSeq:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionSeq:I

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellId:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellId:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellTarget:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellTarget:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->visualFxMask:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->visualFxMask:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stealthSkillLevel:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stealthSkillLevel:I

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->skillSnapshot:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->skillSnapshot:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingHp:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingHp:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingMana:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingMana:I

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->combatEffectsSnapshot:Ljava/lang/String;

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->combatEffectsSnapshot:Ljava/lang/String;

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->gold:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->gold:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repVarsilia:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repVarsilia:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repMercia:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repMercia:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repIlmara:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repIlmara:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repWizards:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repWizards:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repThree:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repThree:I

    iget v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->difficulty:I

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->difficulty:I

    iget-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    if-eqz v1, :cond_115

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    goto :goto_11c

    :cond_115
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    :goto_11c
    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    if-eqz v1, :cond_13c

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_124
    :goto_124
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_13c

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->copyFollowerState(Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    move-result-object v2

    if-eqz v2, :cond_124

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    invoke-virtual {v3, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_124

    :cond_13c
    return-object v0
.end method

.method private decode(Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    if-nez p1, :cond_5

    const-string p1, ""

    return-object p1

    :cond_5
    const-string v0, "%0D"

    const-string v1, "\r"

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "%0A"

    const-string v1, "\n"

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "%09"

    const-string v1, "\t"

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "%25"

    const-string v1, "%"

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private describeDatagramSocketState(Ljava/net/DatagramSocket;)Ljava/lang/String;
    .registers 5

    if-nez p1, :cond_5

    const-string p1, "socket=null"

    return-object p1

    :cond_5
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "local="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p1}, Ljava/net/DatagramSocket;->getLocalSocketAddress()Ljava/net/SocketAddress;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " bound="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p1}, Ljava/net/DatagramSocket;->isBound()Z

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " closed="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p1}, Ljava/net/DatagramSocket;->isClosed()Z

    move-result p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private describeLocalIpv4s()Ljava/lang/String;
    .registers 8

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    :try_start_5
    invoke-static {}, Ljava/net/NetworkInterface;->getNetworkInterfaces()Ljava/util/Enumeration;

    move-result-object v1

    if-eqz v1, :cond_76

    :cond_b
    invoke-interface {v1}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v2

    if-eqz v2, :cond_76

    invoke-interface {v1}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/net/NetworkInterface;

    if-eqz v2, :cond_b

    invoke-virtual {v2}, Ljava/net/NetworkInterface;->isUp()Z

    move-result v3

    if-eqz v3, :cond_b

    invoke-virtual {v2}, Ljava/net/NetworkInterface;->isLoopback()Z

    move-result v3

    if-nez v3, :cond_b

    invoke-virtual {v2}, Ljava/net/NetworkInterface;->getInetAddresses()Ljava/util/Enumeration;

    move-result-object v3

    :cond_29
    :goto_29
    invoke-interface {v3}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v4

    if-eqz v4, :cond_b

    invoke-interface {v3}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/net/InetAddress;

    instance-of v5, v4, Ljava/net/Inet4Address;

    if-eqz v5, :cond_29

    invoke-virtual {v4}, Ljava/net/InetAddress;->isLoopbackAddress()Z

    move-result v5

    if-nez v5, :cond_29

    invoke-virtual {v4}, Ljava/net/InetAddress;->isLinkLocalAddress()Z

    move-result v5

    if-nez v5, :cond_29

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->length()I

    move-result v5

    if-eqz v5, :cond_50

    const-string v5, " | "

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_50
    invoke-virtual {v2}, Ljava/net/NetworkInterface;->getName()Ljava/lang/String;

    move-result-object v5

    if-nez v5, :cond_58

    const-string v5, "iface"

    :cond_58
    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, "="

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v4}, Ljava/net/InetAddress;->getHostAddress()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    :try_end_69
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_69} :catch_6a

    goto :goto_29

    :catch_6a
    move-exception v1

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->length()I

    move-result v1

    if-nez v1, :cond_76

    const-string v1, "unavailable"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_76
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_82

    const-string v0, "none"

    :cond_82
    return-object v0
.end method

.method private describeSocketState(Ljava/net/Socket;)Ljava/lang/String;
    .registers 5

    if-nez p1, :cond_5

    const-string p1, "socket=null"

    return-object p1

    :cond_5
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "local="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p1}, Ljava/net/Socket;->getLocalSocketAddress()Ljava/net/SocketAddress;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " remote="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p1}, Ljava/net/Socket;->getRemoteSocketAddress()Ljava/net/SocketAddress;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " bound="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p1}, Ljava/net/Socket;->isBound()Z

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " connected="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p1}, Ljava/net/Socket;->isConnected()Z

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " closed="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p1}, Ljava/net/Socket;->isClosed()Z

    move-result p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private dispatchUi()V
    .registers 10

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v1

    :try_start_3
    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->uiListener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->buildStateTextLocked()Ljava/lang/String;

    move-result-object v5

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->statusDiag:Ljava/lang/String;

    if-eqz v0, :cond_2a

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_2a

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const/16 v3, 0xa

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    :cond_2a
    new-instance v6, Ljava/util/ArrayList;

    invoke-direct {v6}, Ljava/util/ArrayList;-><init>()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_35
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_5c

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_4c

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    goto :goto_54

    :cond_4c
    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    invoke-virtual {v3, v2}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    :goto_54
    invoke-direct {p0, v2, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->formatPlayerLineLocked(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v6, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_35

    :cond_5c
    new-instance v7, Ljava/util/ArrayList;

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->chatHistory:Ljava/util/ArrayList;

    invoke-direct {v7, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    new-instance v8, Ljava/util/ArrayList;

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryResults:Ljava/util/ArrayList;

    invoke-direct {v8, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    monitor-exit v1
    :try_end_6b
    .catchall {:try_start_3 .. :try_end_6b} :catchall_7a

    if-nez v4, :cond_6e

    return-void

    :cond_6e
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->mainHandler:Landroid/os/Handler;

    new-instance v2, Lnet/fdgames/ek/android/lan/LanSessionManager$5;

    move-object v3, p0

    invoke-direct/range {v2 .. v8}, Lnet/fdgames/ek/android/lan/LanSessionManager$5;-><init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    invoke-virtual {v0, v2}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void

    :catchall_7a
    move-exception v0

    :try_start_7b
    monitor-exit v1
    :try_end_7c
    .catchall {:try_start_7b .. :try_end_7c} :catchall_7a

    goto :goto_7e

    :goto_7d
    throw v0

    :goto_7e
    goto :goto_7d
.end method

.method private encode(Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    if-nez p1, :cond_5

    const-string p1, ""

    return-object p1

    :cond_5
    const-string v0, "%"

    const-string v1, "%25"

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "\t"

    const-string v1, "%09"

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "\n"

    const-string v1, "%0A"

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "\r"

    const-string v1, "%0D"

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private findLocalIpv4()Ljava/lang/String;
    .registers 9

    const-string v0, "0.0.0.0"

    const/4 v1, 0x0

    const/4 v2, 0x0

    :try_start_4
    invoke-static {}, Ljava/net/NetworkInterface;->getNetworkInterfaces()Ljava/util/Enumeration;

    move-result-object v3

    if-nez v3, :cond_b

    return-object v0

    :cond_b
    :goto_b
    invoke-interface {v3}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v4

    if-eqz v4, :cond_5d

    invoke-interface {v3}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/net/NetworkInterface;

    if-eqz v4, :cond_b

    invoke-virtual {v4}, Ljava/net/NetworkInterface;->isUp()Z

    move-result v5

    if-eqz v5, :cond_b

    invoke-virtual {v4}, Ljava/net/NetworkInterface;->isLoopback()Z

    move-result v5

    if-eqz v5, :cond_26

    goto :goto_b

    :cond_26
    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isLikelyVpnInterface(Ljava/net/NetworkInterface;)Z

    move-result v5

    invoke-virtual {v4}, Ljava/net/NetworkInterface;->getInetAddresses()Ljava/util/Enumeration;

    move-result-object v4

    :goto_2e
    invoke-interface {v4}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v6

    if-eqz v6, :cond_5c

    invoke-interface {v4}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/net/InetAddress;

    instance-of v7, v6, Ljava/net/Inet4Address;

    if-eqz v7, :cond_4d

    invoke-virtual {v6}, Ljava/net/InetAddress;->isLoopbackAddress()Z

    move-result v7

    if-nez v7, :cond_4d

    invoke-virtual {v6}, Ljava/net/InetAddress;->getHostAddress()Ljava/lang/String;

    move-result-object v7

    if-eqz v7, :cond_4d

    if-eqz v5, :cond_4e

    return-object v7

    :cond_4d
    goto :goto_2e

    :cond_4e
    invoke-virtual {v6}, Ljava/net/InetAddress;->isSiteLocalAddress()Z

    move-result v6

    if-eqz v6, :cond_58

    if-nez v1, :cond_4d

    move-object v1, v7

    goto :goto_2e

    :cond_58
    if-nez v2, :cond_4d

    move-object v2, v7

    goto :goto_2e

    :cond_5c
    goto :goto_b

    :cond_5d
    if-eqz v1, :cond_60

    return-object v1

    :cond_60
    if-eqz v2, :cond_63

    move-object v0, v2
    :try_end_63
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_63} :catch_64

    :cond_63
    return-object v0

    :catch_64
    move-exception v1

    return-object v0
.end method

.method private firstStackFrame(Ljava/lang/Throwable;)Ljava/lang/String;
    .registers 4

    if-eqz p1, :cond_13

    invoke-virtual {p1}, Ljava/lang/Throwable;->getStackTrace()[Ljava/lang/StackTraceElement;

    move-result-object p1

    if-eqz p1, :cond_13

    array-length v0, p1

    if-lez v0, :cond_13

    const/4 v0, 0x0

    aget-object p1, p1, v0

    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_13
    const-string p1, "none"

    return-object p1
.end method

.method private formatPlayerLineLocked(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;
    .registers 6

    if-nez p2, :cond_3

    return-object p1

    :cond_3
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, p1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget p1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->level:I

    if-lez p1, :cond_20

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p1

    if-eqz p1, :cond_15

    const-string p1, " - Nv "

    goto :goto_17

    :cond_15
    const-string p1, " - Lv "

    :goto_17
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->level:I

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    :cond_20
    iget-object p1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    const-string v1, " - "

    if-eqz p1, :cond_37

    iget-object p1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_37

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v2, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_37
    iget-object p1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz p1, :cond_4c

    iget-object p1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_4c

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v1, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_4c
    const-string p1, " - G "

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget p2, p2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->gold:I

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method public static declared-synchronized get(Landroid/content/Context;)Lnet/fdgames/ek/android/lan/LanSessionManager;
    .registers 3

    const-class v0, Lnet/fdgames/ek/android/lan/LanSessionManager;

    monitor-enter v0

    :try_start_3
    sget-object v1, Lnet/fdgames/ek/android/lan/LanSessionManager;->instance:Lnet/fdgames/ek/android/lan/LanSessionManager;

    if-nez v1, :cond_12

    new-instance v1, Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    invoke-direct {v1, p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;-><init>(Landroid/content/Context;)V

    sput-object v1, Lnet/fdgames/ek/android/lan/LanSessionManager;->instance:Lnet/fdgames/ek/android/lan/LanSessionManager;

    :cond_12
    sget-object p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->instance:Lnet/fdgames/ek/android/lan/LanSessionManager;
    :try_end_14
    .catchall {:try_start_3 .. :try_end_14} :catchall_16

    monitor-exit v0

    return-object p0

    :catchall_16
    move-exception p0

    :try_start_17
    monitor-exit v0
    :try_end_18
    .catchall {:try_start_17 .. :try_end_18} :catchall_16

    throw p0
.end method

.method public static declared-synchronized getInstanceIfReady()Lnet/fdgames/ek/android/lan/LanSessionManager;
    .registers 2

    const-class v0, Lnet/fdgames/ek/android/lan/LanSessionManager;

    monitor-enter v0

    :try_start_3
    sget-object v1, Lnet/fdgames/ek/android/lan/LanSessionManager;->instance:Lnet/fdgames/ek/android/lan/LanSessionManager;
    :try_end_5
    .catchall {:try_start_3 .. :try_end_5} :catchall_7

    monitor-exit v0

    return-object v1

    :catchall_7
    move-exception v1

    :try_start_8
    monitor-exit v0
    :try_end_9
    .catchall {:try_start_8 .. :try_end_9} :catchall_7

    throw v1
.end method

.method private handleDiscoveryReply(Ljava/net/InetAddress;Ljava/lang/String;)V
    .registers 11

    if-eqz p1, :cond_ae

    if-eqz p2, :cond_ae

    const-string v0, "EK_HOST\t"

    invoke-virtual {p2, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_e

    goto/16 :goto_ae

    :cond_e
    const-string v0, "\t"

    const/4 v1, -0x1

    invoke-virtual {p2, v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object p2

    array-length v0, p2

    const/4 v1, 0x5

    if-ge v0, v1, :cond_1a

    return-void

    :cond_1a
    const/4 v0, 0x1

    aget-object v1, p2, v0

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    const/4 v1, 0x2

    aget-object v1, p2, v1

    const/16 v2, 0x7d7c

    invoke-direct {p0, v1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v5

    const/4 v1, 0x3

    aget-object v1, p2, v1

    invoke-direct {p0, v1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v6

    const/4 v0, 0x4

    aget-object p2, p2, v0

    const/4 v0, 0x6

    invoke-direct {p0, p2, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v7

    invoke-virtual {p1}, Ljava/net/InetAddress;->getHostAddress()Ljava/lang/String;

    move-result-object v4

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string p2, ":"

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    move-object v4, p1

    iget-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter p2

    :try_start_58
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryByKey:Ljava/util/LinkedHashMap;

    new-instance v2, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;

    invoke-direct/range {v2 .. v7}, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;-><init>(Ljava/lang/String;Ljava/lang/String;III)V

    invoke-virtual {v0, p1, v2}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :goto_62
    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryByKey:Ljava/util/LinkedHashMap;

    invoke-virtual {p1}, Ljava/util/LinkedHashMap;->size()I

    move-result p1

    const/16 v0, 0x14

    if-le p1, v0, :cond_82

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryByKey:Ljava/util/LinkedHashMap;

    invoke-virtual {p1}, Ljava/util/LinkedHashMap;->keySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryByKey:Ljava/util/LinkedHashMap;

    invoke-virtual {v0, p1}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_62

    :cond_82
    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->rebuildDiscoveryListLocked()V

    monitor-exit p2
    :try_end_86
    .catchall {:try_start_58 .. :try_end_86} :catchall_aa

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "scan reply="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v0, " name="

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :catchall_aa
    move-exception v0

    move-object p1, v0

    :try_start_ac
    monitor-exit p2
    :try_end_ad
    .catchall {:try_start_ac .. :try_end_ad} :catchall_aa

    throw p1

    :cond_ae
    :goto_ae
    return-void
.end method

.method private handleIncomingClient(Ljava/net/Socket;)V
    .registers 10

    nop

    nop

    nop

    const/4 v0, 0x1

    const/4 v1, 0x0

    :try_start_5
    new-instance v2, Ljava/io/BufferedReader;

    new-instance v3, Ljava/io/InputStreamReader;

    invoke-virtual {p1}, Ljava/net/Socket;->getInputStream()Ljava/io/InputStream;

    move-result-object v4

    const-string v5, "UTF-8"

    invoke-direct {v3, v4, v5}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/lang/String;)V

    invoke-direct {v2, v3}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V
    :try_end_15
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_15} :catch_26d
    .catchall {:try_start_5 .. :try_end_15} :catchall_267

    :try_start_15
    new-instance v3, Ljava/io/PrintWriter;

    new-instance v4, Ljava/io/BufferedWriter;

    new-instance v5, Ljava/io/OutputStreamWriter;

    invoke-virtual {p1}, Ljava/net/Socket;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v6

    const-string v7, "UTF-8"

    invoke-direct {v5, v6, v7}, Ljava/io/OutputStreamWriter;-><init>(Ljava/io/OutputStream;Ljava/lang/String;)V

    invoke-direct {v4, v5}, Ljava/io/BufferedWriter;-><init>(Ljava/io/Writer;)V

    invoke-direct {v3, v4, v0}, Ljava/io/PrintWriter;-><init>(Ljava/io/Writer;Z)V
    :try_end_2a
    .catch Ljava/io/IOException; {:try_start_15 .. :try_end_2a} :catch_262
    .catchall {:try_start_15 .. :try_end_2a} :catchall_25d

    :try_start_2a
    invoke-virtual {p1, v0}, Ljava/net/Socket;->setTcpNoDelay(Z)V

    const/high16 v4, 0x20000

    invoke-virtual {p1, v4}, Ljava/net/Socket;->setReceiveBufferSize(I)V

    invoke-virtual {p1, v4}, Ljava/net/Socket;->setSendBufferSize(I)V

    const/16 v4, 0x10

    invoke-virtual {p1, v4}, Ljava/net/Socket;->setTrafficClass(I)V

    invoke-virtual {v2}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v4

    if-eqz v4, :cond_224

    const-string v5, "JOIN\t"

    invoke-virtual {v4, v5}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-nez v5, :cond_4a

    goto/16 :goto_224

    :cond_4a
    invoke-direct {p0, v4, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v5
    :try_end_55
    .catch Ljava/io/IOException; {:try_start_2a .. :try_end_55} :catch_259
    .catchall {:try_start_2a .. :try_end_55} :catchall_254

    :try_start_55
    iget-object v6, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    move-result v6

    iget v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->maxPlayers:I

    if-lt v6, v7, :cond_90

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "CLOSE\t"

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v6

    if-eqz v6, :cond_73

    const-string v6, "Sala cheia."

    goto :goto_75

    :cond_73
    const-string v6, "Room is full."

    :goto_75
    invoke-direct {p0, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v3, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V

    monitor-exit v5
    :try_end_85
    .catchall {:try_start_55 .. :try_end_85} :catchall_221

    nop

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/PrintWriter;)V

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/BufferedReader;)V

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V

    return-void

    :cond_90
    :try_start_90
    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sanitizePlayerName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->makeUniquePlayerNameLocked(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    new-instance v6, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;

    invoke-direct {v6, p1, v3, v4, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;-><init>(Ljava/net/Socket;Ljava/io/PrintWriter;Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$1;)V
    :try_end_9d
    .catchall {:try_start_90 .. :try_end_9d} :catchall_221

    :try_start_9d
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPeers:Ljava/util/ArrayList;

    invoke-virtual {v1, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v1

    if-eqz v1, :cond_bd

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v7, " entrou na sala."

    invoke-virtual {v1, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    goto :goto_cc

    :cond_bd
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v7, " joined the room."

    invoke-virtual {v1, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    :goto_cc
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    monitor-exit v5
    :try_end_d4
    .catchall {:try_start_9d .. :try_end_d4} :catchall_21e

    :try_start_d4
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "[CYAN]LAN[] "

    invoke-virtual {v1, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v4

    if-eqz v4, :cond_ec

    const-string v4, " entrou na sessao."

    goto :goto_ee

    :cond_ec
    const-string v4, " joined the LAN session."

    :goto_ee
    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "WELCOME\t"

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v4, "\t"

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;
    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v4, "\t2"

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->send(Ljava/lang/String;)V
    invoke-static {v6, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$900(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;Ljava/lang/String;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pushPlayersToClients()V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pushStateToClients()V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pushStateSnapshotToClients()V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    :goto_138
    invoke-virtual {v2}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_219

    const-string v4, "CHAT\t"

    invoke-virtual {v1, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_1c2

    invoke-direct {p0, v1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sanitizeChat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_1c0

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v4
    :try_end_15b
    .catch Ljava/io/IOException; {:try_start_d4 .. :try_end_15b} :catch_21b
    .catchall {:try_start_d4 .. :try_end_15b} :catchall_2cd

    :try_start_15b
    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;
    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;

    move-result-object v5

    invoke-direct {p0, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->resolveChatDisplayNameLocked(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-direct {p0, v5, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addChatLineLocked(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->incrementUnreadChatLocked()V

    monitor-exit v4
    :try_end_16a
    .catchall {:try_start_15b .. :try_end_16a} :catchall_1bd

    :try_start_16a
    invoke-direct {p0, v5, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->postChatAlert(Ljava/lang/String;Ljava/lang/String;)V

    const-string v5, "eliminated"

    invoke-virtual {v1, v5}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_18d

    const-string v5, "[PVP]"

    invoke-virtual {v1, v5}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_18d

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v5

    if-eqz v5, :cond_18d

    iget-object v5, v5, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    if-eqz v5, :cond_18d

    const/4 v7, 0x1

    const-string v4, "pvp_arena_won"

    invoke-virtual {v5, v7, v4}, Lnet/fdgames/GameWorld/GameVariables;->e(ILjava/lang/String;)V

    :cond_18d
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "CHAT\t"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;
    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;

    move-result-object v5

    invoke-direct {p0, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "\t"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V
    :try_end_1bc
    .catch Ljava/io/IOException; {:try_start_16a .. :try_end_1bc} :catch_21b
    .catchall {:try_start_16a .. :try_end_1bc} :catchall_2cd

    goto :goto_1c0

    :catchall_1bd
    move-exception v1

    :try_start_1be
    monitor-exit v4
    :try_end_1bf
    .catchall {:try_start_1be .. :try_end_1bf} :catchall_1bd

    :try_start_1bf
    throw v1

    :cond_1c0
    :goto_1c0
    goto/16 :goto_138

    :cond_1c2
    const-string v4, "PSTATE\t"

    invoke-virtual {v1, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_1ed

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseState(Ljava/lang/String;)Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    move-result-object v1

    if-eqz v1, :cond_217

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v4
    :try_end_1d3
    .catch Ljava/io/IOException; {:try_start_1bf .. :try_end_1d3} :catch_21b
    .catchall {:try_start_1bf .. :try_end_1d3} :catchall_2cd

    :try_start_1d3
    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;
    invoke-static {v6}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;

    move-result-object v7

    iput-object v7, v1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    invoke-virtual {v5, v7, v1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    monitor-exit v4
    :try_end_1df
    .catchall {:try_start_1d3 .. :try_end_1df} :catchall_1ea

    :try_start_1df
    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->serializeState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClientsExcept(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V
    :try_end_1e9
    .catch Ljava/io/IOException; {:try_start_1df .. :try_end_1e9} :catch_21b
    .catchall {:try_start_1df .. :try_end_1e9} :catchall_2cd

    goto :goto_217

    :catchall_1ea
    move-exception v1

    :try_start_1eb
    monitor-exit v4
    :try_end_1ec
    .catchall {:try_start_1eb .. :try_end_1ec} :catchall_1ea

    :try_start_1ec
    throw v1

    :cond_1ed
    const-string v4, "PACT\t"

    invoke-virtual {v1, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_1fc

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->receiveCombatPacket(Ljava/lang/String;)V

    invoke-direct {p0, v1, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClientsExcept(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)V

    goto :goto_217

    :cond_1fc
    const-string v4, "NPCSTATE2\t"

    invoke-virtual {v1, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_20e

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v4

    iput-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastNpcStateData:Ljava/lang/String;

    monitor-exit v4

    invoke-direct {p0, v1, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClientsExcept(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)V

    goto :goto_217

    :cond_20e
    const-string v4, "LEAVE"

    invoke-virtual {v4, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1
    :try_end_214
    .catch Ljava/io/IOException; {:try_start_1ec .. :try_end_214} :catch_21b
    .catchall {:try_start_1ec .. :try_end_214} :catchall_2cd

    if-eqz v1, :cond_217

    goto :goto_219

    :cond_217
    :goto_217
    goto/16 :goto_138

    :cond_219
    :goto_219
    goto/16 :goto_2be

    :catch_21b
    move-exception v1

    goto/16 :goto_272

    :catchall_21e
    move-exception v4

    move-object v1, v6

    goto :goto_222

    :catchall_221
    move-exception v4

    :goto_222
    :try_start_222
    monitor-exit v5
    :try_end_223
    .catchall {:try_start_222 .. :try_end_223} :catchall_221

    :try_start_223
    throw v4

    :cond_224
    :goto_224
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "CLOSE\t"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v5

    if-eqz v5, :cond_238

    const-string v5, "Entrada LAN invalida."

    goto :goto_23a

    :cond_238
    const-string v5, "Invalid LAN join."

    :goto_23a
    invoke-direct {p0, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v3, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V
    :try_end_249
    .catch Ljava/io/IOException; {:try_start_223 .. :try_end_249} :catch_259
    .catchall {:try_start_223 .. :try_end_249} :catchall_254

    nop

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/PrintWriter;)V

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/BufferedReader;)V

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V

    return-void

    :catchall_254
    move-exception v4

    move-object v6, v1

    move-object v1, v4

    goto/16 :goto_2ce

    :catch_259
    move-exception v4

    move-object v6, v1

    move-object v1, v4

    goto :goto_272

    :catchall_25d
    move-exception v3

    move-object v6, v1

    move-object v1, v3

    move-object v3, v6

    goto :goto_2ce

    :catch_262
    move-exception v3

    move-object v6, v1

    move-object v1, v3

    move-object v3, v6

    goto :goto_272

    :catchall_267
    move-exception v2

    move-object v3, v1

    move-object v6, v3

    move-object v1, v2

    move-object v2, v6

    goto :goto_2ce

    :catch_26d
    move-exception v2

    move-object v3, v1

    move-object v6, v3

    move-object v1, v2

    move-object v2, v6

    :goto_272
    :try_start_272
    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSocketClosedException(Ljava/io/IOException;)Z

    move-result v4

    if-nez v4, :cond_2bc

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v5

    if-eqz v5, :cond_286

    const-string v5, "Conexao LAN perdida: "

    goto :goto_288

    :cond_286
    const-string v5, "LAN connection lost: "

    :goto_288
    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "[CYAN]LAN[] HOST "

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v4

    if-eqz v4, :cond_2af

    const-string v4, "saiu da sessao."

    goto :goto_2b1

    :cond_2af
    const-string v4, "left the LAN session."

    :goto_2b1
    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V
    :try_end_2bc
    .catchall {:try_start_272 .. :try_end_2bc} :catchall_2cd

    :cond_2bc
    if-eqz v6, :cond_2c2

    :goto_2be
    invoke-direct {p0, v6, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->removeHostPeer(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;Z)V

    goto :goto_2cc

    :cond_2c2
    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/PrintWriter;)V

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/BufferedReader;)V

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V

    nop

    :goto_2cc
    return-void

    :catchall_2cd
    move-exception v1

    :goto_2ce
    if-eqz v6, :cond_2d4

    invoke-direct {p0, v6, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->removeHostPeer(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;Z)V

    goto :goto_2dd

    :cond_2d4
    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/PrintWriter;)V

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/io/BufferedReader;)V

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V

    :goto_2dd
    goto :goto_2df

    :goto_2de
    throw v1

    :goto_2df
    goto :goto_2de
.end method

.method private handleServerMessage(Ljava/lang/String;)V
    .registers 7

    const-string v0, "WELCOME\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_34

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_c
    move-object v2, p1

    invoke-direct {p0, p1, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    const/4 v3, 0x2

    invoke-direct {p0, v2, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_2c

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    :cond_2c
    monitor-exit v0
    :try_end_2d
    .catchall {:try_start_c .. :try_end_2d} :catchall_31

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :catchall_31
    move-exception p1

    :try_start_32
    monitor-exit v0
    :try_end_33
    .catchall {:try_start_32 .. :try_end_33} :catchall_31

    throw p1

    :cond_34
    const-string v0, "STATE\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    const/4 v2, 0x2

    if-eqz v0, :cond_4c

    invoke-direct {p0, p1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x6

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result p1

    iput p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->maxPlayers:I

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :cond_4c
    const-string v0, "PLAYERS\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_94

    const-string v0, "\t"

    const/4 v2, -0x1

    invoke-virtual {p1, v0, v2}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_5e
    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->clear()V

    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    nop

    :goto_69
    array-length v3, p1

    if-ge v1, v3, :cond_83

    aget-object v3, p1, v1

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_80

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_80
    add-int/lit8 v1, v1, 0x1

    goto :goto_69

    :cond_83
    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    invoke-virtual {p1}, Ljava/util/LinkedHashMap;->keySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1, v2}, Ljava/util/Set;->retainAll(Ljava/util/Collection;)Z

    monitor-exit v0
    :try_end_8d
    .catchall {:try_start_5e .. :try_end_8d} :catchall_91

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :catchall_91
    move-exception p1

    :try_start_92
    monitor-exit v0
    :try_end_93
    .catchall {:try_start_92 .. :try_end_93} :catchall_91

    throw p1

    :cond_94
    const-string v0, "CHAT\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_f1

    invoke-direct {p0, p1, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, p1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v2

    :try_start_af
    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    const/4 v4, 0x0

    if-eqz v3, :cond_b8

    invoke-virtual {v3, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    :cond_b8
    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->resolveChatDisplayNameLocked(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addChatLineLocked(Ljava/lang/String;Ljava/lang/String;)V

    if-nez v4, :cond_c4

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->incrementUnreadChatLocked()V

    :cond_c4
    monitor-exit v2
    :try_end_c5
    .catchall {:try_start_af .. :try_end_c5} :catchall_ee

    if-nez v4, :cond_ea

    invoke-direct {p0, v0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->postChatAlert(Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "eliminated"

    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_ea

    const-string v0, "[PVP]"

    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_ea

    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v0

    if-eqz v0, :cond_ea

    iget-object v0, v0, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    if-eqz v0, :cond_ea

    const/4 v3, 0x1

    const-string v4, "pvp_arena_won"

    invoke-virtual {v0, v3, v4}, Lnet/fdgames/GameWorld/GameVariables;->e(ILjava/lang/String;)V

    :cond_ea
    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :catchall_ee
    move-exception p1

    :try_start_ef
    monitor-exit v2
    :try_end_f0
    .catchall {:try_start_ef .. :try_end_f0} :catchall_ee

    throw p1

    :cond_f1
    const-string v0, "PSTATE\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_11c

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseState(Ljava/lang/String;)Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    move-result-object p1

    if-eqz p1, :cond_11b

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_11b

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_10c
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    invoke-virtual {v1, v2, p1}, Ljava/util/LinkedHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    monitor-exit v0
    :try_end_114
    .catchall {:try_start_10c .. :try_end_114} :catchall_118

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    goto :goto_11b

    :catchall_118
    move-exception p1

    :try_start_119
    monitor-exit v0
    :try_end_11a
    .catchall {:try_start_119 .. :try_end_11a} :catchall_118

    throw p1

    :cond_11b
    :goto_11b
    return-void

    :cond_11c
    const-string v0, "PACT\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_128

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->receiveCombatPacket(Ljava/lang/String;)V

    return-void

    :cond_128
    const-string v0, "PDMG2\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_134

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->receivePlayerDamagePacket(Ljava/lang/String;)V

    return-void

    :cond_134
    const-string v0, "SYSTEM\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_152

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_13f
    invoke-direct {p0, p1, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    monitor-exit v0
    :try_end_14b
    .catchall {:try_start_13f .. :try_end_14b} :catchall_14f

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :catchall_14f
    move-exception p1

    :try_start_150
    monitor-exit v0
    :try_end_151
    .catchall {:try_start_150 .. :try_end_151} :catchall_14f

    throw p1

    :cond_152
    const-string v0, "CLOSE\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_177

    invoke-direct {p0, p1, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_165
    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    monitor-exit v0
    :try_end_169
    .catchall {:try_start_165 .. :try_end_169} :catchall_174

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    const/4 p1, 0x0

    invoke-direct {p0, p1, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopAllInternal(ZZ)V

    return-void

    :catchall_174
    move-exception p1

    :try_start_175
    monitor-exit v0
    :try_end_176
    .catchall {:try_start_175 .. :try_end_176} :catchall_174

    throw p1

    :cond_177
    const-string v0, "ERROR\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_18a

    invoke-direct {p0, p1, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->tokenAt(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    :cond_18a
    const-string v0, "NPCSTATE2\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_19a

    const-string v0, "NPCSTATE\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_19c

    :cond_19a
    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastNpcStateData:Ljava/lang/String;

    :cond_19c
    return-void
.end method

.method private incrementUnreadChatLocked()V
    .registers 2

    iget v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->unreadChatCount:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->unreadChatCount:I

    return-void
.end method

.method private isLikelyVpnInterface(Ljava/net/NetworkInterface;)Z
    .registers 4

    const/4 v0, 0x0

    if-nez p1, :cond_4

    return v0

    :cond_4
    :try_start_4
    invoke-virtual {p1}, Ljava/net/NetworkInterface;->isVirtual()Z

    move-result v1

    if-nez v1, :cond_1e

    invoke-virtual {p1}, Ljava/net/NetworkInterface;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isLikelyVpnLabel(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_1e

    invoke-virtual {p1}, Ljava/net/NetworkInterface;->getDisplayName()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isLikelyVpnLabel(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_1f

    :cond_1e
    const/4 v0, 0x1
    :try_end_1f
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_1f} :catch_20

    :cond_1f
    return v0

    :catch_20
    move-exception p1

    return v0
.end method

.method private isLikelyVpnLabel(Ljava/lang/String;)Z
    .registers 4

    const/4 v0, 0x0

    if-nez p1, :cond_4

    return v0

    :cond_4
    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p1

    const-string v1, "zerotier"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_58

    const-string v1, "wireguard"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_58

    const-string v1, "tailscale"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_58

    const-string v1, "utun"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_58

    const-string v1, "tun"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_58

    const-string v1, "tap"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_58

    const-string v1, "vpn"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_58

    const-string v1, "ppp"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_58

    const-string v1, "wg"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_58

    const-string v1, "zt"

    invoke-virtual {p1, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_59

    :cond_58
    const/4 v0, 0x1

    :cond_59
    return v0
.end method

.method private isLocalPlayerName(Ljava/lang/String;)Z
    .registers 4

    const/4 v0, 0x0

    if-nez p1, :cond_4

    return v0

    :cond_4
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v1

    :try_start_7
    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    if-eqz p0, :cond_f

    invoke-virtual {p0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    :cond_f
    monitor-exit v1

    return v0

    :catchall_11
    move-exception p0

    monitor-exit v1
    :try_end_13
    .catchall {:try_start_7 .. :try_end_13} :catchall_11

    throw p0
.end method

.method private isSocketClosedException(Ljava/io/IOException;)Z
    .registers 3

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object p1

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {p1, v0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "closed"

    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1d

    const-string v0, "socket"

    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_1b

    goto :goto_1d

    :cond_1b
    const/4 p1, 0x0

    goto :goto_1e

    :cond_1d
    :goto_1d
    const/4 p1, 0x1

    :goto_1e
    return p1
.end method

.method private lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 7

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->appContext:Landroid/content/Context;

    if-eqz v0, :cond_1b

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    const-string v3, "string"

    invoke-virtual {v1, p1, v3, v2}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v1

    if-eqz v1, :cond_1b

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_1b

    return-object v0

    :cond_1b
    return-object p2
.end method

.method private logLanDebug(Ljava/lang/String;)V
    .registers 3

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V

    const-string v0, "EK-LAN"

    invoke-static {v0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method

.method private logLanError(Ljava/lang/String;Ljava/lang/Throwable;)V
    .registers 4

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V

    const-string v0, "EK-LAN"

    invoke-static {v0, p1, p2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    return-void
.end method

.method private makeUniquePlayerNameLocked(Ljava/lang/String;)Ljava/lang/String;
    .registers 5

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sanitizePlayerName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_d

    return-object p1

    :cond_d
    const/4 v0, 0x2

    :goto_e
    const/16 v1, 0x63

    const-string v2, " "

    if-gt v0, v1, :cond_35

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_32

    return-object v1

    :cond_32
    add-int/lit8 v0, v0, 0x1

    goto :goto_e

    :cond_35
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-virtual {p1, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private parseFloat(Ljava/lang/String;F)F
    .registers 3

    :try_start_0
    invoke-static {p1}, Ljava/lang/Float;->parseFloat(Ljava/lang/String;)F

    move-result p1
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_4} :catch_5

    return p1

    :catch_5
    move-exception p1

    return p2
.end method

.method private parseFollowerState([Ljava/lang/String;I)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;
    .registers 8

    array-length v0, p1

    add-int/lit8 v1, p2, 0xa

    if-lt v0, v1, :cond_6f

    new-instance v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    invoke-direct {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;-><init>()V

    aget-object v1, p1, p2

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spawnId:Ljava/lang/String;

    add-int/lit8 v1, p2, 0x1

    aget-object v1, p1, v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->tag:Ljava/lang/String;

    add-int/lit8 v1, p2, 0x2

    aget-object v1, p1, v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->name:Ljava/lang/String;

    add-int/lit8 v1, p2, 0x3

    aget-object v1, p1, v1

    const/4 v2, -0x1

    invoke-direct {p0, v1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v1

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->x:I

    add-int/lit8 v1, p2, 0x4

    aget-object v1, p1, v1

    invoke-direct {p0, v1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v1

    iput v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->y:I

    add-int/lit8 v1, p2, 0x5

    aget-object v1, p1, v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteIndexCsv:Ljava/lang/String;

    add-int/lit8 v1, p2, 0x6

    aget-object v1, p1, v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->spriteName:Ljava/lang/String;

    add-int/lit8 v1, p2, 0x7

    aget-object v1, p1, v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->facingName:Ljava/lang/String;

    add-int/lit8 v1, p2, 0x8

    aget-object v1, p1, v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->actorStateName:Ljava/lang/String;

    add-int/lit8 p2, p2, 0x9

    aget-object p1, p1, p2

    const/4 p2, 0x0

    invoke-direct {p0, p1, p2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result p1

    iput p1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;->stateTimeMs:I

    return-object v0

    :cond_6f
    const/4 p1, 0x0

    return-object p1
.end method

.method private parseInt(Ljava/lang/String;I)I
    .registers 3

    :try_start_0
    invoke-static {p1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p1
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_4} :catch_5

    return p1

    :catch_5
    move-exception p1

    return p2
.end method

.method private parseLong(Ljava/lang/String;J)J
    .registers 4

    :try_start_0
    invoke-static {p1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide p1
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_4} :catch_5

    return-wide p1

    :catch_5
    move-exception p1

    return-wide p2
.end method

.method private parseState(Ljava/lang/String;)Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;
    .registers 9

    const-string v0, "\t"

    const/4 v1, -0x1

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v0

    array-length p1, v0

    const/16 v1, 0x1d

    const/4 v2, 0x0

    if-ge p1, v1, :cond_e

    return-object v2

    :cond_e
    new-instance p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    invoke-direct {p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;-><init>()V

    const/4 v1, 0x0

    const/4 v3, 0x1

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    iput-object v4, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    const/4 v3, 0x2

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    const/4 v3, 0x3

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    const/4 v3, 0x4

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slot:I

    const/4 v3, 0x5

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    const/4 v3, 0x6

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    const/4 v3, 0x7

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->level:I

    const/16 v3, 0x8

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    const/16 v3, 0x9

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->gold:I

    const/16 v3, 0xa

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repVarsilia:I

    const/16 v3, 0xb

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repMercia:I

    const/16 v3, 0xc

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repIlmara:I

    const/16 v3, 0xd

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repWizards:I

    const/16 v3, 0xe

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repThree:I

    const/16 v3, 0xf

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->classEnumName:Ljava/lang/String;

    const/16 v3, 0x10

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->raceName:Ljava/lang/String;

    const/16 v3, 0x11

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    const/16 v3, 0x12

    aget-object v3, v0, v3

    invoke-direct {p0, v3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->portraitIndex:I

    const/16 v3, 0x13

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteIndexCsv:Ljava/lang/String;

    const/16 v3, 0x14

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteName:Ljava/lang/String;

    const/16 v3, 0x15

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->facingName:Ljava/lang/String;

    const/16 v3, 0x16

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    const/16 v3, 0x17

    aget-object v4, v0, v3

    invoke-direct {p0, v4, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stateTimeMs:I

    const/16 v3, 0x18

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    const/16 v3, 0x19

    aget-object v4, v0, v3

    invoke-direct {p0, v4, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionSeq:I

    const/16 v3, 0x1a

    aget-object v3, v0, v3

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellId:Ljava/lang/String;

    const/16 v3, 0x1b

    aget-object v4, v0, v3

    invoke-direct {p0, v4, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    const/16 v3, 0x1c

    aget-object v4, v0, v3

    invoke-direct {p0, v4, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    const/16 v3, 0x1d

    array-length v4, v0

    if-le v4, v3, :cond_133

    aget-object v4, v0, v3

    const/4 v3, 0x0

    invoke-direct {p0, v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseFloat(Ljava/lang/String;F)F

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedX:F

    :cond_133
    const/16 v3, 0x1e

    array-length v4, v0

    if-le v4, v3, :cond_141

    aget-object v4, v0, v3

    const/4 v3, 0x0

    invoke-direct {p0, v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseFloat(Ljava/lang/String;F)F

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedY:F

    :cond_141
    const/16 v3, 0x1f

    array-length v4, v0

    if-le v4, v3, :cond_150

    aget-object v4, v0, v3

    const-wide/16 v5, 0x0

    invoke-direct {p0, v4, v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseLong(Ljava/lang/String;J)J

    move-result-wide v5

    iput-wide v5, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    :cond_150
    const/16 v3, 0x20

    array-length v4, v0

    if-le v4, v3, :cond_15d

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpawnId:Ljava/lang/String;

    :cond_15d
    const/16 v3, 0x21

    array-length v4, v0

    if-le v4, v3, :cond_16a

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonTag:Ljava/lang/String;

    :cond_16a
    const/16 v3, 0x22

    array-length v4, v0

    if-le v4, v3, :cond_177

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonName:Ljava/lang/String;

    :cond_177
    const/16 v3, 0x23

    array-length v4, v0

    if-le v4, v3, :cond_185

    aget-object v4, v0, v3

    const/4 v3, -0x1

    invoke-direct {p0, v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonX:I

    :cond_185
    const/16 v3, 0x24

    array-length v4, v0

    if-le v4, v3, :cond_193

    aget-object v4, v0, v3

    const/4 v3, -0x1

    invoke-direct {p0, v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonY:I

    :cond_193
    const/16 v3, 0x25

    array-length v4, v0

    if-le v4, v3, :cond_1a0

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteIndexCsv:Ljava/lang/String;

    :cond_1a0
    const/16 v3, 0x26

    array-length v4, v0

    if-le v4, v3, :cond_1ad

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    :cond_1ad
    const/16 v3, 0x27

    array-length v4, v0

    if-le v4, v3, :cond_1ba

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonFacingName:Ljava/lang/String;

    :cond_1ba
    const/16 v3, 0x28

    array-length v4, v0

    if-le v4, v3, :cond_1c7

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonActorStateName:Ljava/lang/String;

    :cond_1c7
    const/16 v3, 0x29

    array-length v4, v0

    if-le v4, v3, :cond_1d5

    aget-object v4, v0, v3

    const/4 v3, 0x0

    invoke-direct {p0, v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonStateTimeMs:I

    :cond_1d5
    const/16 v3, 0x2a

    array-length v4, v0

    if-le v4, v3, :cond_1e2

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    :cond_1e2
    const/16 v3, 0x2b

    array-length v4, v0

    if-le v4, v3, :cond_1ef

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    :cond_1ef
    const/16 v3, 0x2c

    array-length v4, v0

    if-le v4, v3, :cond_1fc

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    :cond_1fc
    const/16 v3, 0x2d

    array-length v4, v0

    if-le v4, v3, :cond_20a

    aget-object v4, v0, v3

    const/4 v3, -0x1

    invoke-direct {p0, v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    :cond_20a
    const/16 v3, 0x2e

    array-length v4, v0

    if-le v4, v3, :cond_218

    aget-object v4, v0, v3

    const/4 v3, -0x1

    invoke-direct {p0, v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    :cond_218
    const/16 v3, 0x2f

    array-length v4, v0

    if-le v4, v3, :cond_225

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    :cond_225
    const/16 v3, 0x30

    array-length v4, v0

    if-le v4, v3, :cond_232

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    :cond_232
    const/16 v3, 0x31

    array-length v4, v0

    if-le v4, v3, :cond_23f

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    :cond_23f
    const/16 v3, 0x32

    array-length v4, v0

    if-le v4, v3, :cond_24c

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    :cond_24c
    const/16 v3, 0x33

    array-length v4, v0

    if-le v4, v3, :cond_25a

    aget-object v4, v0, v3

    const/4 v3, 0x0

    invoke-direct {p0, v4, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v3

    iput v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionStateTimeMs:I

    :cond_25a
    const/16 v3, 0x34

    array-length v4, v0

    if-le v4, v3, :cond_27c

    aget-object v4, v0, v3

    const/4 v5, 0x0

    invoke-direct {p0, v4, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v4

    add-int/lit8 v3, v3, 0x1

    if-lez v4, :cond_27c

    :goto_26a
    if-lez v4, :cond_27c

    invoke-direct {p0, v0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseFollowerState([Ljava/lang/String;I)Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    move-result-object v5

    if-eqz v5, :cond_27c

    iget-object v6, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 v3, v3, 0xa

    add-int/lit8 v4, v4, -0x1

    goto :goto_26a

    :cond_27c
    array-length v4, v0

    if-le v4, v3, :cond_308

    aget-object v4, v0, v3

    const/4 v5, 0x0

    invoke-direct {p0, v4, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v4

    if-lez v4, :cond_308

    add-int/lit8 v3, v3, 0x1

    const/4 v5, 0x1

    if-ge v4, v5, :cond_28e

    goto :goto_308

    :cond_28e
    array-length v5, v0

    if-le v5, v3, :cond_308

    aget-object v5, v0, v3

    const/4 v6, -0x1

    invoke-direct {p0, v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v5

    iput v5, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotBodyItemId:I

    add-int/lit8 v3, v3, 0x1

    const/4 v5, 0x2

    if-ge v4, v5, :cond_2a0

    goto :goto_308

    :cond_2a0
    array-length v5, v0

    if-le v5, v3, :cond_308

    aget-object v5, v0, v3

    const/4 v6, -0x1

    invoke-direct {p0, v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v5

    iput v5, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotFeetItemId:I

    add-int/lit8 v3, v3, 0x1

    const/4 v5, 0x3

    if-ge v4, v5, :cond_2b2

    goto :goto_308

    :cond_2b2
    array-length v5, v0

    if-le v5, v3, :cond_308

    aget-object v5, v0, v3

    const/4 v6, -0x1

    invoke-direct {p0, v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v5

    iput v5, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHandsItemId:I

    add-int/lit8 v3, v3, 0x1

    const/4 v5, 0x4

    if-ge v4, v5, :cond_2c4

    goto :goto_308

    :cond_2c4
    array-length v5, v0

    if-le v5, v3, :cond_308

    aget-object v5, v0, v3

    const/4 v6, -0x1

    invoke-direct {p0, v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v5

    iput v5, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHeadItemId:I

    add-int/lit8 v3, v3, 0x1

    const/4 v5, 0x5

    if-ge v4, v5, :cond_2d6

    goto :goto_308

    :cond_2d6
    array-length v5, v0

    if-le v5, v3, :cond_308

    aget-object v5, v0, v3

    const/4 v6, -0x1

    invoke-direct {p0, v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v5

    iput v5, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotLegsItemId:I

    add-int/lit8 v3, v3, 0x1

    const/4 v5, 0x6

    if-ge v4, v5, :cond_2e8

    goto :goto_308

    :cond_2e8
    array-length v5, v0

    if-le v5, v3, :cond_308

    aget-object v5, v0, v3

    const/4 v6, -0x1

    invoke-direct {p0, v5, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v5

    iput v5, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotMainhandItemId:I

    add-int/lit8 v3, v3, 0x1

    const/4 v5, 0x7

    if-ge v4, v5, :cond_2fa

    goto :goto_308

    :cond_2fa
    array-length v4, v0

    if-le v4, v3, :cond_308

    aget-object v4, v0, v3

    const/4 v5, -0x1

    invoke-direct {p0, v4, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v4

    iput v4, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotOffhandItemId:I

    add-int/lit8 v3, v3, 0x1

    :cond_308
    :goto_308
    array-length v4, v0

    if-le v4, v3, :cond_314

    aget-object v4, v0, v3

    const/4 v5, 0x0

    invoke-direct {p0, v4, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v4

    iput v4, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->visualFxMask:I

    :cond_314
    add-int/lit8 v3, v3, 0x1

    array-length v4, v0

    if-le v4, v3, :cond_322

    aget-object v4, v0, v3

    const/4 v5, 0x0

    invoke-direct {p0, v4, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v4

    iput v4, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stealthSkillLevel:I

    :cond_322
    add-int/lit8 v3, v3, 0x1

    array-length v4, v0

    if-le v4, v3, :cond_32f

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    iput-object v4, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->skillSnapshot:Ljava/lang/String;

    :cond_32f
    add-int/lit8 v3, v3, 0x1

    array-length v4, v0

    if-le v4, v3, :cond_33d

    aget-object v4, v0, v3

    const/4 v5, 0x0

    invoke-direct {p0, v4, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v4

    iput v4, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingHp:I

    :cond_33d
    add-int/lit8 v3, v3, 0x1

    array-length v4, v0

    if-le v4, v3, :cond_34b

    aget-object v4, v0, v3

    const/4 v5, 0x0

    invoke-direct {p0, v4, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v4

    iput v4, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingMana:I

    :cond_34b
    add-int/lit8 v3, v3, 0x1

    array-length v4, v0

    if-le v4, v3, :cond_358

    aget-object v4, v0, v3

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    iput-object v4, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->combatEffectsSnapshot:Ljava/lang/String;

    :cond_358
    iget-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    if-eqz v3, :cond_366

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_36a

    :cond_366
    iget-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    :cond_36a
    iget-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    if-eqz v3, :cond_378

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_37c

    :cond_378
    iget-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    :cond_37c
    iget-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    if-eqz v3, :cond_38b

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_38b

    goto :goto_38f

    :cond_38b
    iget-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    iput-object v3, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    :goto_38f
    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v0, :cond_39f

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_39e

    goto :goto_39f

    :cond_39e
    move-object v2, p1

    :cond_39f
    :goto_39f
    return-object v2
.end method

.method private postChatAlert(Ljava/lang/String;Ljava/lang/String;)V
    .registers 4

    if-eqz p2, :cond_2d

    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_d

    goto :goto_2d

    :cond_d
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string p0, "[CYAN]LAN[] "

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string p1, ": "

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V

    :cond_2d
    :goto_2d
    return-void
.end method

.method private pt()Z
    .registers 3

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/Locale;->getLanguage()Ljava/lang/String;

    move-result-object v0

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {v0, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "pt"

    invoke-virtual {v0, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method private pushPlayersToClients()V
    .registers 5

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    new-instance v1, Ljava/util/ArrayList;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    monitor-exit v0
    :try_end_b
    .catchall {:try_start_3 .. :try_end_b} :catchall_38

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v2, "PLAYERS"

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_16
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_30

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    const/16 v3, 0x9

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_16

    :cond_30
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    return-void

    :catchall_38
    move-exception v1

    :try_start_39
    monitor-exit v0
    :try_end_3a
    .catchall {:try_start_39 .. :try_end_3a} :catchall_38

    goto :goto_3c

    :goto_3b
    throw v1

    :goto_3c
    goto :goto_3b
.end method

.method private pushStateSnapshotToClients()V
    .registers 7

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    if-eqz v2, :cond_13

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->serializeState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_13
    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    invoke-virtual {v2}, Ljava/util/LinkedHashMap;->values()Ljava/util/Collection;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_1d
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_31

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->serializeState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1d

    :cond_31
    monitor-exit v0
    :try_end_32
    .catchall {:try_start_3 .. :try_end_32} :catchall_47

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_36
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_46

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    goto :goto_36

    :cond_46
    return-void

    :catchall_47
    move-exception v1

    :try_start_48
    monitor-exit v0
    :try_end_49
    .catchall {:try_start_48 .. :try_end_49} :catchall_47

    throw v1
.end method

.method private pushStateToClients()V
    .registers 5

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    iget v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->maxPlayers:I

    monitor-exit v0
    :try_end_c
    .catchall {:try_start_3 .. :try_end_c} :catchall_2d

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "STATE\t"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "\t"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    return-void

    :catchall_2d
    move-exception v1

    :try_start_2e
    monitor-exit v0
    :try_end_2f
    .catchall {:try_start_2e .. :try_end_2f} :catchall_2d

    throw v1
.end method

.method private queueCombatPacket(Ljava/lang/String;)V
    .registers 4

    if-nez p1, :cond_3

    return-void

    :cond_3
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_6
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingCombatPackets:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    monitor-exit v0
    :try_end_c
    .catchall {:try_start_6 .. :try_end_c} :catchall_d

    return-void

    :catchall_d
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method private queuePlayerDamagePacket(Ljava/lang/String;)V
    .registers 4

    if-nez p1, :cond_3

    return-void

    :cond_3
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_6
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingPlayerDamagePackets:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    monitor-exit v0
    :try_end_c
    .catchall {:try_start_6 .. :try_end_c} :catchall_d

    return-void

    :catchall_d
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method private rebuildDiscoveryListLocked()V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryResults:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryResults:Ljava/util/ArrayList;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryByKey:Ljava/util/LinkedHashMap;

    invoke-virtual {v1}, Ljava/util/LinkedHashMap;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    return-void
.end method

.method private receiveCombatPacket(Ljava/lang/String;)V
    .registers 3

    if-eqz p1, :cond_d

    const-string v0, "PACT\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_d

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->queueCombatPacket(Ljava/lang/String;)V

    :cond_d
    return-void
.end method

.method private receivePlayerDamagePacket(Ljava/lang/String;)V
    .registers 3

    if-eqz p1, :cond_10

    const-string v0, "PDMG2\t"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_10

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->queuePlayerDamagePacket(Ljava/lang/String;)V

    :cond_10
    return-void
.end method

.method private removeHostPeer(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;Z)V
    .registers 6

    nop

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_4
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPeers:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_57

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    nop

    if-eqz p2, :cond_55

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p2

    if-eqz p2, :cond_3b

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p2

    const-string v1, " saiu da sala."

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p2

    goto :goto_4e

    :cond_3b
    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p2

    const-string v1, " left the room."

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p2

    :goto_4e
    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-direct {p0, p2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    :cond_55
    const/4 p2, 0x1

    goto :goto_58

    :cond_57
    const/4 p2, 0x0

    :goto_58
    monitor-exit v0
    :try_end_59
    .catchall {:try_start_4 .. :try_end_59} :catchall_94

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->close()V
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1000(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)V

    if-eqz p2, :cond_93

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "[CYAN]LAN[] "

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p2

    # getter for: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->playerName:Ljava/lang/String;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1800(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p2

    if-eqz p2, :cond_7a

    const-string p2, " saiu da sessao."

    goto :goto_7c

    :cond_7a
    const-string p2, " left the LAN session."

    :goto_7c
    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pushPlayersToClients()V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pushStateToClients()V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pushStateSnapshotToClients()V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    :cond_93
    return-void

    :catchall_94
    move-exception p1

    :try_start_95
    monitor-exit v0
    :try_end_96
    .catchall {:try_start_95 .. :try_end_96} :catchall_94

    throw p1
.end method

.method private resolveChatDisplayNameLocked(Ljava/lang/String;)Ljava/lang/String;
    .registers 5

    if-nez p1, :cond_5

    const-string p1, ""

    return-object p1

    :cond_5
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_10

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    goto :goto_18

    :cond_10
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    invoke-virtual {v0, p1}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    :goto_18
    if-eqz v0, :cond_39

    iget-object v1, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    if-eqz v1, :cond_29

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_29

    return-object v1

    :cond_29
    iget-object v0, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v0, :cond_39

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_38

    return-object v0

    :cond_38
    return-object p1

    :cond_39
    return-object p1
.end method

.method private resolvePreferredIpv4Address()Ljava/net/InetAddress;
    .registers 4

    const/4 v0, 0x0

    :try_start_1
    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->findLocalIpv4()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_25

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_25

    const-string v2, "0.0.0.0"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_16

    goto :goto_25

    :cond_16
    invoke-static {v1}, Ljava/net/InetAddress;->getByName(Ljava/lang/String;)Ljava/net/InetAddress;

    move-result-object v1

    instance-of v2, v1, Ljava/net/Inet4Address;

    if-eqz v2, :cond_25

    invoke-virtual {v1}, Ljava/net/InetAddress;->isAnyLocalAddress()Z

    move-result v2

    if-nez v2, :cond_25

    return-object v1
    :try_end_25
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_25} :catch_26

    :cond_25
    :goto_25
    return-object v0

    :catch_26
    move-exception v1

    return-object v0
.end method

.method private safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;
    .registers 4

    if-nez p1, :cond_5

    const-string p1, ""

    return-object p1

    :cond_5
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_1d

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_1e

    :cond_1d
    return-object v0

    :cond_1e
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v0, ": "

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private sanitizeChat(Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    if-nez p1, :cond_5

    const-string p1, ""

    goto :goto_9

    :cond_5
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    :goto_9
    const/16 v0, 0xa

    const/16 v1, 0x20

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object p1

    const/16 v0, 0xd

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object p1

    const/16 v0, 0x9

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v0

    const/16 v1, 0xb4

    if-le v0, v1, :cond_2e

    const/4 v0, 0x0

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    :cond_2e
    return-object p1
.end method

.method private sanitizePlayerName(Ljava/lang/String;)Ljava/lang/String;
    .registers 6

    if-nez p1, :cond_5

    const-string p1, ""

    goto :goto_9

    :cond_5
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    :goto_9
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    const-string v1, "Jogador"

    const-string v2, "Player"

    if-eqz v0, :cond_1c

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p1

    if-eqz p1, :cond_1b

    move-object p1, v1

    goto :goto_1c

    :cond_1b
    move-object p1, v2

    :cond_1c
    :goto_1c
    const/16 v0, 0xa

    const/16 v3, 0x20

    invoke-virtual {p1, v0, v3}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object p1

    const/16 v0, 0xd

    invoke-virtual {p1, v0, v3}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object p1

    const/16 v0, 0x9

    invoke-virtual {p1, v0, v3}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v0

    const/16 v3, 0x14

    if-le v0, v3, :cond_41

    const/4 v0, 0x0

    invoke-virtual {p1, v0, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    :cond_41
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_50

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p1

    if-eqz p1, :cond_4e

    goto :goto_51

    :cond_4e
    move-object v1, v2

    goto :goto_51

    :cond_50
    move-object v1, p1

    :goto_51
    return-object v1
.end method

.method private sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V
    .registers 3

    if-eqz p1, :cond_11

    if-nez p2, :cond_5

    goto :goto_11

    :cond_5
    invoke-virtual {p1, p2}, Ljava/io/PrintWriter;->print(Ljava/lang/String;)V

    const/16 p2, 0xa

    invoke-virtual {p1, p2}, Ljava/io/PrintWriter;->print(C)V

    invoke-virtual {p1}, Ljava/io/PrintWriter;->flush()V

    return-void

    :cond_11
    :goto_11
    return-void
.end method

.method private serializeState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;
    .registers 6

    if-nez p1, :cond_5

    const-string p1, "PSTATE"

    return-object p1

    :cond_5
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "PSTATE\t"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "\t"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentLevelId:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slot:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->x:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->y:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->level:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->gold:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repVarsilia:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repMercia:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repIlmara:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repWizards:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->repThree:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->classEnumName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->raceName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->genderName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->portraitIndex:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteIndexCsv:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spriteName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->facingName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v2, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stateTimeMs:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    move-object v2, p1

    iget-object p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionSeq:I

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->spellId:Ljava/lang/String;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginX:I

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actionOriginY:I

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedX:F

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v3, v2, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->speedY:F

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    move-object v0, v2

    iget-wide v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->sampleTimeMs:J

    invoke-virtual {p1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpawnId:Ljava/lang/String;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonTag:Ljava/lang/String;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonName:Ljava/lang/String;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonX:I

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonY:I

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteIndexCsv:Ljava/lang/String;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonSpriteName:Ljava/lang/String;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonFacingName:Ljava/lang/String;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonActorStateName:Ljava/lang/String;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->summonStateTimeMs:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpawnId:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionTag:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionX:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionY:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteIndexCsv:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionSpriteName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionFacingName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionActorStateName:Ljava/lang/String;

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionStateTimeMs:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    move-object v3, v0

    iget-object v2, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    if-eqz v2, :cond_284

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    goto :goto_285

    :cond_284
    const/4 v2, 0x0

    :goto_285
    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    if-lez v2, :cond_2a5

    iget-object v0, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->followers:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_295
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2a5

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;

    invoke-direct {p0, p1, v1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->appendSerializedFollowerState(Ljava/lang/StringBuilder;Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$FollowerState;)V

    goto :goto_295

    :cond_2a5
    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const/4 v2, 0x7

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotBodyItemId:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotFeetItemId:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHandsItemId:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotHeadItemId:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotLegsItemId:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v2, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotMainhandItemId:I

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v0, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->slotOffhandItemId:I

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v0, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->visualFxMask:I

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v0, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->stealthSkillLevel:I

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v0, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->skillSnapshot:Ljava/lang/String;

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v0, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingHp:I

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget v0, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingMana:I

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    iget-object v0, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->combatEffectsSnapshot:Ljava/lang/String;

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private setStatusDiag(Ljava/lang/String;)V
    .registers 5

    if-nez p1, :cond_5

    const-string p1, ""

    goto :goto_35

    :cond_5
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_35

    const-string v0, "LAN DIAG "

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_28

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    :cond_28
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v0

    const/16 v1, 0x1c2

    if-le v0, v1, :cond_35

    const/4 v0, 0x0

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    :cond_35
    :goto_35
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_38
    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->statusDiag:Ljava/lang/String;

    monitor-exit v0
    :try_end_3b
    .catchall {:try_start_38 .. :try_end_3b} :catchall_3f

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :catchall_3f
    move-exception p1

    :try_start_40
    monitor-exit v0
    :try_end_41
    .catchall {:try_start_40 .. :try_end_41} :catchall_3f

    throw p1
.end method

.method private showLanDiag(Ljava/lang/String;)V
    .registers 9

    if-nez p1, :cond_3

    return-void

    :cond_3
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_e

    return-void

    :cond_e
    const-string v0, "LAN DIAG "

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_27

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    :cond_27
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v0

    const/16 v1, 0x1c2

    if-le v0, v1, :cond_34

    const/4 v0, 0x0

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    :cond_34
    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->logLanDebug(Ljava/lang/String;)V

    move-object v3, p1

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3b
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->statusDiag:Ljava/lang/String;

    if-eqz v1, :cond_65

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_65

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_4d

    move-object p1, v1

    goto :goto_66

    :cond_4d
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    goto :goto_66

    :cond_65
    move-object p1, v3

    :goto_66
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v2, 0x2d0

    if-le v1, v2, :cond_73

    sub-int/2addr v1, v2

    invoke-virtual {p1, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p1

    :cond_73
    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->statusDiag:Ljava/lang/String;

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    monitor-exit v0
    :try_end_79
    .catchall {:try_start_3b .. :try_end_79} :catchall_7d

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :catchall_7d
    move-exception p1

    :try_start_7e
    monitor-exit v0
    :try_end_7f
    .catchall {:try_start_7e .. :try_end_7f} :catchall_7d

    throw p1
.end method

.method private startAcceptThread(Ljava/net/ServerSocket;)V
    .registers 4

    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lnet/fdgames/ek/android/lan/LanSessionManager$2;

    invoke-direct {v1, p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$2;-><init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/ServerSocket;)V

    const-string p1, "ek-lan-accept"

    invoke-direct {v0, v1, p1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    const/4 p1, 0x1

    invoke-virtual {v0, p1}, Ljava/lang/Thread;->setDaemon(Z)V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter p1

    :try_start_13
    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->acceptThread:Ljava/lang/Thread;

    monitor-exit p1
    :try_end_16
    .catchall {:try_start_13 .. :try_end_16} :catchall_1a

    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    return-void

    :catchall_1a
    move-exception v0

    :try_start_1b
    monitor-exit p1
    :try_end_1c
    .catchall {:try_start_1b .. :try_end_1c} :catchall_1a

    throw v0
.end method

.method private startClientReader(Ljava/net/Socket;)V
    .registers 4

    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lnet/fdgames/ek/android/lan/LanSessionManager$4;

    invoke-direct {v1, p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$4;-><init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/Socket;)V

    const-string p1, "ek-lan-client-reader"

    invoke-direct {v0, v1, p1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    const/4 p1, 0x1

    invoke-virtual {v0, p1}, Ljava/lang/Thread;->setDaemon(Z)V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter p1

    :try_start_13
    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientReadThread:Ljava/lang/Thread;

    monitor-exit p1
    :try_end_16
    .catchall {:try_start_13 .. :try_end_16} :catchall_1a

    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    return-void

    :catchall_1a
    move-exception v0

    :try_start_1b
    monitor-exit p1
    :try_end_1c
    .catchall {:try_start_1b .. :try_end_1c} :catchall_1a

    throw v0
.end method

.method private startDiscoveryResponder()V
    .registers 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/net/SocketException;
        }
    .end annotation

    :try_start_0
    const/4 v1, 0x1

    new-instance v0, Ljava/net/DatagramSocket;

    const/4 v2, 0x0

    check-cast v2, Ljava/net/SocketAddress;

    invoke-direct {v0, v2}, Ljava/net/DatagramSocket;-><init>(Ljava/net/SocketAddress;)V

    invoke-virtual {v0, v1}, Ljava/net/DatagramSocket;->setReuseAddress(Z)V

    const-string v2, "0.0.0.0"

    invoke-static {v2}, Ljava/net/InetAddress;->getByName(Ljava/lang/String;)Ljava/net/InetAddress;

    move-result-object v2

    new-instance v3, Ljava/net/InetSocketAddress;

    const/16 v4, 0x7d7b

    invoke-direct {v3, v2, v4}, Ljava/net/InetSocketAddress;-><init>(Ljava/net/InetAddress;I)V

    invoke-virtual {v0, v3}, Ljava/net/DatagramSocket;->bind(Ljava/net/SocketAddress;)V

    invoke-virtual {v0, v1}, Ljava/net/DatagramSocket;->setBroadcast(Z)V

    invoke-virtual {v0, v1}, Ljava/net/DatagramSocket;->setReuseAddress(Z)V
    :try_end_22
    .catch Ljava/net/SocketException; {:try_start_0 .. :try_end_22} :catch_71

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v2

    :try_start_25
    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostDiscoverySocket:Ljava/net/DatagramSocket;

    monitor-exit v2
    :try_end_28
    .catchall {:try_start_25 .. :try_end_28} :catchall_6e

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "host udp="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Ljava/net/DatagramSocket;->getLocalSocketAddress()Ljava/net/SocketAddress;

    move-result-object v3

    invoke-static {v3}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, " preferred="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V

    new-instance v2, Ljava/lang/Thread;

    new-instance v3, Lnet/fdgames/ek/android/lan/LanSessionManager$3;

    invoke-direct {v3, p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager$3;-><init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/net/DatagramSocket;)V

    const-string v0, "ek-lan-discovery-host"

    invoke-direct {v2, v3, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/Thread;->setDaemon(Z)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_64
    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostDiscoveryThread:Ljava/lang/Thread;

    monitor-exit v0
    :try_end_67
    .catchall {:try_start_64 .. :try_end_67} :catchall_6b

    invoke-virtual {v2}, Ljava/lang/Thread;->start()V

    return-void

    :catchall_6b
    move-exception v1

    :try_start_6c
    monitor-exit v0
    :try_end_6d
    .catchall {:try_start_6c .. :try_end_6d} :catchall_6b

    throw v1

    :catchall_6e
    move-exception v0

    :try_start_6f
    monitor-exit v2
    :try_end_70
    .catchall {:try_start_6f .. :try_end_70} :catchall_6e

    throw v0

    :catch_71
    move-exception v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "DISCOVERY responder unavailable localIps="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeLocalIpv4s()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " reason="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->logLanError(Ljava/lang/String;)V

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V

    return-void
.end method

.method private stopAllInternal(ZZ)V
    .registers 11

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    const/4 v1, 0x1

    :try_start_4
    iput-boolean v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopRequested:Z

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostServerSocket:Ljava/net/ServerSocket;

    const/4 v2, 0x0

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostServerSocket:Ljava/net/ServerSocket;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostDiscoverySocket:Ljava/net/DatagramSocket;

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostDiscoverySocket:Ljava/net/DatagramSocket;

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientSocket:Ljava/net/Socket;

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientSocket:Ljava/net/Socket;

    iget-object v5, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    new-instance v6, Ljava/util/ArrayList;

    iget-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPeers:Ljava/util/ArrayList;

    invoke-direct {v6, v7}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    iget-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPeers:Ljava/util/ArrayList;

    invoke-virtual {v7}, Ljava/util/ArrayList;->clear()V

    const/4 v7, 0x0

    iput-boolean v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    iput-boolean v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    if-eqz p2, :cond_38

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v7

    if-eqz v7, :cond_33

    const-string v7, "Sessao LAN encerrada."

    goto :goto_35

    :cond_33
    const-string v7, "LAN session closed."

    :goto_35
    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    :cond_38
    iget-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v7}, Ljava/util/ArrayList;->clear()V

    iget-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    invoke-virtual {v7}, Ljava/util/LinkedHashMap;->clear()V

    iget-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingCombatPackets:Ljava/util/ArrayList;

    invoke-virtual {v7}, Ljava/util/ArrayList;->clear()V

    iget-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingPlayerDamagePackets:Ljava/util/ArrayList;

    invoke-virtual {v7}, Ljava/util/ArrayList;->clear()V

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastNpcStateData:Ljava/lang/String;

    const/4 v7, 0x0

    iput v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastAppliedPlayerDamageSeq:I

    const/4 v7, 0x1

    iput v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->nextPlayerDamageSeq:I

    const-string v7, ""

    iput-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastAppliedPlayerDamageLevelId:Ljava/lang/String;

    if-eqz p1, :cond_5f

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->clearDiscoveryLocked()V

    :cond_5f
    const-string p1, ""

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    const-string p1, ""

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    const/16 p1, 0x7d7c

    iput p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    monitor-exit v0
    :try_end_6c
    .catchall {:try_start_4 .. :try_end_6c} :catchall_e0

    if-eqz v5, :cond_75

    :try_start_6e
    const-string p1, "LEAVE"

    invoke-direct {p0, v5, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V
    :try_end_73
    .catch Ljava/lang/Exception; {:try_start_6e .. :try_end_73} :catch_74

    goto :goto_75

    :catch_74
    move-exception p1

    :cond_75
    :goto_75
    invoke-virtual {v6}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_79
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_b0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;

    :try_start_85
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "CLOSE\t"

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v5

    if-eqz v5, :cond_99

    const-string v5, "Host encerrou a sala."

    goto :goto_9b

    :cond_99
    const-string v5, "Host closed the room."

    :goto_9b
    invoke-direct {p0, v5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->send(Ljava/lang/String;)V
    invoke-static {v0, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$900(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;Ljava/lang/String;)V
    :try_end_aa
    .catch Ljava/lang/Exception; {:try_start_85 .. :try_end_aa} :catch_ab

    goto :goto_ac

    :catch_ab
    move-exception v2

    :goto_ac
    # invokes: Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->close()V
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;->access$1000(Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;)V

    goto :goto_79

    :cond_b0
    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/ServerSocket;)V

    invoke-direct {p0, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/DatagramSocket;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    if-eqz p2, :cond_df

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string p2, "[CYAN]LAN[] "

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p2

    if-eqz p2, :cond_d2

    const-string p2, "Sessao LAN encerrada."

    goto :goto_d4

    :cond_d2
    const-string p2, "LAN session closed."

    :goto_d4
    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V

    :cond_df
    return-void

    :catchall_e0
    move-exception p1

    :try_start_e1
    monitor-exit v0
    :try_end_e2
    .catchall {:try_start_e1 .. :try_end_e2} :catchall_e0

    goto :goto_e4

    :goto_e3
    throw p1

    :goto_e4
    goto :goto_e3
.end method

.method private toast(Ljava/lang/String;)V
    .registers 5

    if-eqz p1, :cond_24

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_d

    goto :goto_24

    :cond_d
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_10
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->uiListener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    monitor-exit v0
    :try_end_13
    .catchall {:try_start_10 .. :try_end_13} :catchall_21

    if-nez v1, :cond_16

    return-void

    :cond_16
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->mainHandler:Landroid/os/Handler;

    new-instance v2, Lnet/fdgames/ek/android/lan/LanSessionManager$6;

    invoke-direct {v2, p0, v1, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager$6;-><init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;Ljava/lang/String;)V

    invoke-virtual {v0, v2}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void

    :catchall_21
    move-exception p1

    :try_start_22
    monitor-exit v0
    :try_end_23
    .catchall {:try_start_22 .. :try_end_23} :catchall_21

    throw p1

    :cond_24
    :goto_24
    return-void
.end method

.method private tokenAt(Ljava/lang/String;I)Ljava/lang/String;
    .registers 5

    const-string v0, "\t"

    const/4 v1, -0x1

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object p1

    array-length v0, p1

    if-le v0, p2, :cond_d

    aget-object p1, p1, p2

    goto :goto_f

    :cond_d
    const-string p1, ""

    :goto_f
    return-object p1
.end method


# virtual methods
.method public broadcastNpcState(Ljava/lang/String;)V
    .registers 4

    if-eqz p1, :cond_1e

    iget-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    if-eqz v0, :cond_1b

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "NPCSTATE2\t"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    goto :goto_1e

    :cond_1b
    invoke-virtual {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendNpcStateToHost(Ljava/lang/String;)V

    :cond_1e
    :goto_1e
    return-void
.end method

.method public flushPendingCombatPackets()V
    .registers 6

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingCombatPackets:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_d

    monitor-exit v0

    return-void

    :cond_d
    new-instance v2, Ljava/util/ArrayList;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingCombatPackets:Ljava/util/ArrayList;

    invoke-direct {v2, v3}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    monitor-exit v0
    :try_end_18
    .catchall {:try_start_3 .. :try_end_18} :catchall_2d

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_1c
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2c

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->applyCombatPacket(Ljava/lang/String;)V

    goto :goto_1c

    :cond_2c
    return-void

    :catchall_2d
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method public flushPendingPlayerDamagePackets()V
    .registers 6

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingPlayerDamagePackets:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_d

    monitor-exit v0

    return-void

    :cond_d
    new-instance v2, Ljava/util/ArrayList;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->pendingPlayerDamagePackets:Ljava/util/ArrayList;

    invoke-direct {v2, v3}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    monitor-exit v0
    :try_end_18
    .catchall {:try_start_3 .. :try_end_18} :catchall_2d

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_1c
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2c

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->applyPlayerDamagePacket(Ljava/lang/String;)V

    goto :goto_1c

    :cond_2c
    return-void

    :catchall_2d
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method public getChatSnapshot()Ljava/util/List;
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    new-instance v1, Ljava/util/ArrayList;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->chatHistory:Ljava/util/ArrayList;

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    monitor-exit v0

    return-object v1

    :catchall_c
    move-exception v1

    monitor-exit v0
    :try_end_e
    .catchall {:try_start_3 .. :try_end_e} :catchall_c

    throw v1
.end method

.method public getDiscoverySnapshot()Ljava/util/List;
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    new-instance v1, Ljava/util/ArrayList;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->discoveryResults:Ljava/util/ArrayList;

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    monitor-exit v0

    return-object v1

    :catchall_c
    move-exception v1

    monitor-exit v0
    :try_end_e
    .catchall {:try_start_3 .. :try_end_e} :catchall_c

    throw v1
.end method

.method public getLastNpcStateData()Ljava/lang/String;
    .registers 2

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lastNpcStateData:Ljava/lang/String;

    return-object v0
.end method

.method public getLocalPlayerName()Ljava/lang/String;
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    monitor-exit v0

    return-object v1

    :catchall_7
    move-exception v1

    monitor-exit v0
    :try_end_9
    .catchall {:try_start_3 .. :try_end_9} :catchall_7

    throw v1
.end method

.method public getPeerStatesSnapshot()Ljava/util/List;
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    new-instance v1, Ljava/util/ArrayList;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    invoke-virtual {v2}, Ljava/util/LinkedHashMap;->values()Ljava/util/Collection;

    move-result-object v2

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    monitor-exit v0

    return-object v1

    :catchall_10
    move-exception v1

    monitor-exit v0
    :try_end_12
    .catchall {:try_start_3 .. :try_end_12} :catchall_10

    throw v1
.end method

.method public getPlayerCount()I
    .registers 2

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    return v0
.end method

.method public getPlayersSnapshot()Ljava/util/List;
    .registers 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_e
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_35

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_25

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    goto :goto_2d

    :cond_25
    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    invoke-virtual {v4, v3}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    :goto_2d
    invoke-direct {p0, v3, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->formatPlayerLineLocked(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_e

    :cond_35
    monitor-exit v0

    return-object v1

    :catchall_37
    move-exception v1

    monitor-exit v0
    :try_end_39
    .catchall {:try_start_3 .. :try_end_39} :catchall_37

    goto :goto_3b

    :goto_3a
    throw v1

    :goto_3b
    goto :goto_3a
.end method

.method public getStateText()Ljava/lang/String;
    .registers 5

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->buildStateTextLocked()Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->statusDiag:Ljava/lang/String;

    if-eqz v2, :cond_28

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_28

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v3, 0xa

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    :cond_28
    monitor-exit v0

    return-object v1

    :catchall_2a
    move-exception v1

    monitor-exit v0
    :try_end_2c
    .catchall {:try_start_3 .. :try_end_2c} :catchall_2a

    throw v1
.end method

.method public getUnreadChatCount()I
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->unreadChatCount:I

    monitor-exit v0

    return v1

    :catchall_7
    move-exception v1

    monitor-exit v0
    :try_end_9
    .catchall {:try_start_3 .. :try_end_9} :catchall_7

    throw v1
.end method

.method public isHosting()Z
    .registers 2

    iget-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    return v0
.end method

.method public isInSession()Z
    .registers 3

    iget-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    if-nez v0, :cond_a

    iget-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    if-nez v0, :cond_a

    const/4 v1, 0x0

    return v1

    :cond_a
    const/4 v1, 0x1

    return v1
.end method

.method public isSessionRunning()Z
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    iget-boolean v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    if-nez v1, :cond_e

    iget-boolean v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    if-eqz v1, :cond_c

    goto :goto_e

    :cond_c
    const/4 v1, 0x0

    goto :goto_f

    :cond_e
    :goto_e
    const/4 v1, 0x1

    :goto_f
    monitor-exit v0

    return v1

    :catchall_11
    move-exception v1

    monitor-exit v0
    :try_end_13
    .catchall {:try_start_3 .. :try_end_13} :catchall_11

    throw v1
.end method

.method public joinHost(Ljava/lang/String;Ljava/lang/String;I)V
    .registers 12

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sanitizePlayerName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-nez p2, :cond_9

    const-string p2, ""

    goto :goto_d

    :cond_9
    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p2

    :goto_d
    invoke-virtual {p2}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_22

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p1

    if-eqz p1, :cond_1c

    const-string p1, "Informe um IP valido."

    goto :goto_1e

    :cond_1c
    const-string p1, "Enter a valid IP."

    :goto_1e
    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    return-void

    :cond_22
    const/16 v0, 0x3a

    invoke-virtual {p2, v0}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v0

    if-lez v0, :cond_6e

    const/16 v1, 0x3a

    invoke-virtual {p2, v1}, Ljava/lang/String;->indexOf(I)I

    move-result v1

    if-ne v0, v1, :cond_6e

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    if-lt v0, v1, :cond_3b

    goto :goto_6e

    :cond_3b
    add-int/lit8 v1, v0, 0x1

    invoke-virtual {p2, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v1

    const/16 v2, 0x7d7c

    invoke-direct {p0, v1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->parseInt(Ljava/lang/String;I)I

    move-result v1

    if-lez v1, :cond_6e

    const v2, 0xffff

    if-le v1, v2, :cond_4f

    goto :goto_6e

    :cond_4f
    move p3, v1

    const/4 v1, 0x0

    invoke-virtual {p2, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_6e

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p1

    if-eqz p1, :cond_68

    const-string p1, "Informe um IP valido."

    goto :goto_6a

    :cond_68
    const-string p1, "Enter a valid IP."

    :goto_6a
    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    return-void

    :cond_6e
    :goto_6e
    const/4 v0, 0x0

    invoke-direct {p0, v0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopAllInternal(ZZ)V

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v1

    :try_start_75
    iput-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopRequested:Z

    iput-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    const-string v2, ""

    iput-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->statusDiag:Ljava/lang/String;

    const/4 v2, 0x1

    iput-boolean v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    if-lez p3, :cond_87

    goto :goto_89

    :cond_87
    const/16 p3, 0x7d7c

    :goto_89
    iput p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    iget-object p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {p3}, Ljava/util/ArrayList;->clear()V

    iget-object p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-virtual {p3, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->chatHistory:Ljava/util/ArrayList;

    invoke-virtual {p3}, Ljava/util/ArrayList;->clear()V

    const/4 p3, 0x0

    iput p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->unreadChatCount:I

    iget-object p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    invoke-virtual {p3}, Ljava/util/LinkedHashMap;->clear()V

    const/4 p3, 0x0

    iput-object p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v3

    if-eqz v3, :cond_b7

    const-string v3, "Conectando em "

    goto :goto_b9

    :cond_b7
    const-string v3, "Connecting to "

    :goto_b9
    invoke-virtual {p3, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p3

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p3

    const-string v3, ":"

    invoke-virtual {p3, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p3

    iget v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {p3, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p3

    const-string v3, "..."

    invoke-virtual {p3, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p3

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    invoke-direct {p0, p3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    monitor-exit v1
    :try_end_db
    .catchall {:try_start_75 .. :try_end_db} :catchall_2c0

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "JOIN attempt target="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, ":"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, " timeoutMs=10000 localIps="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeLocalIpv4s()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->logLanDebug(Ljava/lang/String;)V

    new-instance v7, Ljava/net/Socket;

    invoke-direct {v7}, Ljava/net/Socket;-><init>()V

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "tcp pre-connect target="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, ":"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, " socket="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeSocketState(Ljava/net/Socket;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V

    :try_start_143
    new-instance v1, Ljava/net/InetSocketAddress;

    iget v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-direct {v1, p2, v3}, Ljava/net/InetSocketAddress;-><init>(Ljava/lang/String;I)V

    const/16 p2, 0x2710

    invoke-virtual {v7, v1, p2}, Ljava/net/Socket;->connect(Ljava/net/SocketAddress;I)V

    invoke-virtual {v7, v2}, Ljava/net/Socket;->setTcpNoDelay(Z)V

    const v3, 0x10000

    invoke-virtual {v7, v3}, Ljava/net/Socket;->setReceiveBufferSize(I)V

    invoke-virtual {v7, v3}, Ljava/net/Socket;->setSendBufferSize(I)V

    const/16 v3, 0x10

    invoke-virtual {v7, v3}, Ljava/net/Socket;->setTrafficClass(I)V

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "tcp connected target="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, ":"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, " socket="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeSocketState(Ljava/net/Socket;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V

    new-instance p2, Ljava/io/PrintWriter;

    new-instance v1, Ljava/io/BufferedWriter;

    new-instance v3, Ljava/io/OutputStreamWriter;

    invoke-virtual {v7}, Ljava/net/Socket;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v4

    const-string v5, "UTF-8"

    invoke-direct {v3, v4, v5}, Ljava/io/OutputStreamWriter;-><init>(Ljava/io/OutputStream;Ljava/lang/String;)V

    invoke-direct {v1, v3}, Ljava/io/BufferedWriter;-><init>(Ljava/io/Writer;)V

    invoke-direct {p2, v1, v2}, Ljava/io/PrintWriter;-><init>(Ljava/io/Writer;Z)V

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v1
    :try_end_1aa
    .catch Ljava/io/IOException; {:try_start_143 .. :try_end_1aa} :catch_1fa

    :try_start_1aa
    iput-object v7, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientSocket:Ljava/net/Socket;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    monitor-exit v1
    :try_end_1af
    .catchall {:try_start_1aa .. :try_end_1af} :catchall_1f7

    :try_start_1af
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "JOIN\t"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p2, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V

    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->startClientReader(Ljava/net/Socket;)V

    const-string p1, "lan_toast_connected_room"

    const-string p2, "Connected to LAN room."

    invoke-direct {p0, p1, p2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string p2, "[CYAN]LAN[] "

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string p2, "lan_log_connected_session"

    const-string p3, "Connected to a LAN session."

    invoke-direct {p0, p2, p3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V
    :try_end_1f5
    .catch Ljava/io/IOException; {:try_start_1af .. :try_end_1f5} :catch_1fa

    goto/16 :goto_2bc

    :catchall_1f7
    move-exception p1

    :try_start_1f8
    monitor-exit v1
    :try_end_1f9
    .catchall {:try_start_1f8 .. :try_end_1f9} :catchall_1f7

    :try_start_1f9
    throw p1
    :try_end_1fa
    .catch Ljava/io/IOException; {:try_start_1f9 .. :try_end_1fa} :catch_1fa

    :catch_1fa
    move-exception p1

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "tcp connect failed target="

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v1, ":"

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v1, " socket="

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeSocketState(Ljava/net/Socket;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v1, " error="

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v1, " first="

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->firstStackFrame(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {p0, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V

    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanSessionManager;->closeQuietly(Ljava/net/Socket;)V

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "JOIN fail target="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ":"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " localIps="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeLocalIpv4s()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " reason="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->logLanError(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-direct {p0, v6}, Lnet/fdgames/ek/android/lan/LanSessionManager;->setStatusDiag(Ljava/lang/String;)V

    iget-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter p2

    :try_start_292
    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    monitor-exit p2
    :try_end_296
    .catchall {:try_start_292 .. :try_end_296} :catchall_2bd

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string p3, "lan_error_join_prefix"

    const-string v1, "Unable to join room: "

    invoke-direct {p0, p3, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    invoke-direct {p0, v0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopAllInternal(ZZ)V

    :goto_2bc
    return-void

    :catchall_2bd
    move-exception p1

    :try_start_2be
    monitor-exit p2
    :try_end_2bf
    .catchall {:try_start_2be .. :try_end_2bf} :catchall_2bd

    throw p1

    :catchall_2c0
    move-exception p1

    :try_start_2c1
    monitor-exit v1
    :try_end_2c2
    .catchall {:try_start_2c1 .. :try_end_2c2} :catchall_2c0

    throw p1
.end method

.method public markChatRead()V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    const/4 v1, 0x0

    :try_start_4
    iput v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->unreadChatCount:I

    monitor-exit v0

    return-void

    :catchall_8
    move-exception p0

    monitor-exit v0
    :try_end_a
    .catchall {:try_start_4 .. :try_end_a} :catchall_8

    throw p0
.end method

.method public publishCombatAction(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;IILjava/lang/String;Ljava/lang/String;II)V
    .registers 18

    if-eqz p1, :cond_b0

    if-nez p3, :cond_6

    goto/16 :goto_b0

    :cond_6
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "PACT\t"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v0, "\t"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0, p3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0, p5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0, p8}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0, p9}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string p2, "\t"

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    move/from16 p2, p10

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string p2, "\t"

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    move/from16 p2, p11

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v2

    :try_start_8a
    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    iget-boolean p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    if-eqz p2, :cond_93

    const/4 v3, 0x1

    goto :goto_9d

    :cond_93
    iget-boolean p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    if-eqz p2, :cond_9c

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    if-eqz v4, :cond_9c

    goto :goto_9d

    :cond_9c
    const/4 v5, 0x1

    :goto_9d
    monitor-exit v2
    :try_end_9e
    .catchall {:try_start_8a .. :try_end_9e} :catchall_ad

    if-eqz v5, :cond_a1

    goto :goto_b0

    :cond_a1
    if-eqz v3, :cond_a7

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    goto :goto_b0

    :cond_a7
    if-eqz v4, :cond_b0

    invoke-direct {p0, v4, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V

    goto :goto_b0

    :catchall_ad
    move-exception p1

    :try_start_ae
    monitor-exit v2
    :try_end_af
    .catchall {:try_start_ae .. :try_end_af} :catchall_ad

    throw p1

    :cond_b0
    :goto_b0
    return-void
.end method

.method public publishLiveState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)V
    .registers 6

    if-eqz p1, :cond_6c

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-eqz v0, :cond_12

    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_33

    :cond_12
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    if-eqz v0, :cond_23

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_23

    iput-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    goto :goto_33

    :cond_23
    iget-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    if-eqz v0, :cond_6c

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_6c

    iput-object v0, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    :cond_33
    :goto_33
    nop

    nop

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_38
    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->copyState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    move-result-object p1

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->serializeState(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Ljava/lang/String;

    move-result-object p1

    iget-boolean v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    const/4 v2, 0x0

    if-eqz v1, :cond_4b

    const/4 v1, 0x1

    goto :goto_59

    :cond_4b
    iget-boolean v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    const/4 v3, 0x0

    if-eqz v1, :cond_58

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    if-eqz v1, :cond_58

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    const/4 v1, 0x0

    goto :goto_59

    :cond_58
    const/4 v1, 0x0

    :goto_59
    monitor-exit v0
    :try_end_5a
    .catchall {:try_start_38 .. :try_end_5a} :catchall_69

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    if-eqz v1, :cond_63

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    goto :goto_68

    :cond_63
    if-eqz v2, :cond_68

    invoke-direct {p0, v2, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V

    :cond_68
    :goto_68
    return-void

    :catchall_69
    move-exception p1

    :try_start_6a
    monitor-exit v0
    :try_end_6b
    .catchall {:try_start_6a .. :try_end_6b} :catchall_69

    throw p1

    :cond_6c
    return-void
.end method

.method public publishPlayerDamage(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;II)V
    .registers 18

    if-eqz p1, :cond_b7

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_b7

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_f
    iget-boolean v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    if-nez v1, :cond_15

    monitor-exit v0

    return-void

    :cond_15
    iget v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->nextPlayerDamageSeq:I

    add-int/lit8 v2, v1, 0x1

    iput v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->nextPlayerDamageSeq:I

    monitor-exit v0
    :try_end_1c
    .catchall {:try_start_f .. :try_end_1c} :catchall_b4

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "PDMG2\t"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v0, "\t"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0, p2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0, p4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0, p5}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0, p8}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0, p9}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    move/from16 v2, p10

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    move/from16 v3, p11

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iget-boolean v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    if-eqz v2, :cond_ac

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    goto :goto_b3

    :cond_ac
    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    if-eqz v2, :cond_b3

    invoke-direct {p0, v2, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V

    :cond_b3
    :goto_b3
    return-void

    :catchall_b4
    move-exception p1

    monitor-exit v0

    throw p1

    :cond_b7
    return-void
.end method

.method public scanLan(Ljava/lang/String;)V
    .registers 4

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sanitizePlayerName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->clearDiscoveryLocked()V

    monitor-exit v0
    :try_end_d
    .catchall {:try_start_3 .. :try_end_d} :catchall_4d

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter p1

    :try_start_13
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;

    monitor-exit p1
    :try_end_16
    .catchall {:try_start_13 .. :try_end_16} :catchall_4a

    if-eqz v0, :cond_2d

    invoke-virtual {v0}, Ljava/lang/Thread;->isAlive()Z

    move-result p1

    if-eqz p1, :cond_2d

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p1

    if-eqz p1, :cond_27

    const-string p1, "A busca LAN ja esta em andamento."

    goto :goto_29

    :cond_27
    const-string p1, "LAN scan is already running."

    :goto_29
    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    return-void

    :cond_2d
    new-instance p1, Ljava/lang/Thread;

    new-instance v0, Lnet/fdgames/ek/android/lan/LanSessionManager$1;

    invoke-direct {v0, p0}, Lnet/fdgames/ek/android/lan/LanSessionManager$1;-><init>(Lnet/fdgames/ek/android/lan/LanSessionManager;)V

    const-string v1, "ek-lan-scan"

    invoke-direct {p1, v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Ljava/lang/Thread;->setDaemon(Z)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_40
    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->scanThread:Ljava/lang/Thread;

    monitor-exit v0
    :try_end_43
    .catchall {:try_start_40 .. :try_end_43} :catchall_47

    invoke-virtual {p1}, Ljava/lang/Thread;->start()V

    return-void

    :catchall_47
    move-exception p1

    :try_start_48
    monitor-exit v0
    :try_end_49
    .catchall {:try_start_48 .. :try_end_49} :catchall_47

    throw p1

    :catchall_4a
    move-exception v0

    :try_start_4b
    monitor-exit p1
    :try_end_4c
    .catchall {:try_start_4b .. :try_end_4c} :catchall_4a

    throw v0

    :catchall_4d
    move-exception p1

    :try_start_4e
    monitor-exit v0
    :try_end_4f
    .catchall {:try_start_4e .. :try_end_4f} :catchall_4d

    throw p1
.end method

.method public sendChat(Ljava/lang/String;)V
    .registers 6

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sanitizeChat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_b

    return-void

    :cond_b
    nop

    nop

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_10
    iget-boolean v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    const/4 v2, 0x0

    if-eqz v1, :cond_23

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->resolveChatDisplayNameLocked(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addChatLineLocked(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {p0, v1, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->postChatAlert(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v1, 0x1

    goto :goto_31

    :cond_23
    iget-boolean v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    const/4 v3, 0x0

    if-eqz v1, :cond_30

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    if-eqz v1, :cond_30

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    const/4 v1, 0x0

    goto :goto_31

    :cond_30
    const/4 v1, 0x0

    :goto_31
    monitor-exit v0
    :try_end_32
    .catchall {:try_start_10 .. :try_end_32} :catchall_8b

    if-eqz v1, :cond_62

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "CHAT\t"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "\t"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->broadcastToClients(Ljava/lang/String;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :cond_62
    if-eqz v2, :cond_7f

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "CHAT\t"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, v2, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V

    goto :goto_8a

    :cond_7f
    const-string p1, "lan_error_send_join_first"

    const-string v0, "Join a LAN room before sending messages."

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    :goto_8a
    return-void

    :catchall_8b
    move-exception p1

    :try_start_8c
    monitor-exit v0
    :try_end_8d
    .catchall {:try_start_8c .. :try_end_8d} :catchall_8b

    throw p1
.end method

.method public sendChatAsync(Ljava/lang/String;)V
    .registers 5

    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lnet/fdgames/ek/android/lan/LanSessionManagerChatSendRunnable;

    invoke-direct {v1, p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManagerChatSendRunnable;-><init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V

    const-string v2, "ek-lan-chat-send"

    invoke-direct {v0, v1, v2}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/lang/Thread;->setDaemon(Z)V

    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    return-void
.end method

.method public sendNpcStateToHost(Ljava/lang/String;)V
    .registers 5

    if-eqz p1, :cond_22

    iget-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    if-nez v0, :cond_22

    iget-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    if-eqz v0, :cond_22

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->clientWriter:Ljava/io/PrintWriter;

    if-eqz v0, :cond_22

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "NPCSTATE2\t"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V

    :cond_22
    return-void
.end method

.method public setUiListener(Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;)V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_3
    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->uiListener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    monitor-exit v0
    :try_end_6
    .catchall {:try_start_3 .. :try_end_6} :catchall_a

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    return-void

    :catchall_a
    move-exception p1

    :try_start_b
    monitor-exit v0
    :try_end_c
    .catchall {:try_start_b .. :try_end_c} :catchall_a

    throw p1
.end method

.method public startHosting(Ljava/lang/String;I)V
    .registers 8

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sanitizePlayerName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x0

    invoke-direct {p0, v0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopAllInternal(ZZ)V

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter v1

    :try_start_b
    iput-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopRequested:Z

    const/4 v2, 0x1

    iput-boolean v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hosting:Z

    iput-boolean v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->connected:Z

    const-string v3, ""

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->statusDiag:Ljava/lang/String;

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result v3

    if-eqz v3, :cond_2e

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Sala de "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    goto :goto_3d

    :cond_2e
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v3, "\'s Room"

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    :goto_3d
    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->sessionName:Ljava/lang/String;

    const/16 p1, 0x7d7c

    iput p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    const/4 v3, 0x6

    invoke-static {v3, p2}, Ljava/lang/Math;->min(II)I

    move-result p2

    const/4 v3, 0x2

    invoke-static {v3, p2}, Ljava/lang/Math;->max(II)I

    move-result p2

    iput p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->maxPlayers:I

    iget-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    invoke-virtual {p2}, Ljava/util/ArrayList;->clear()V

    iget-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->players:Ljava/util/ArrayList;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localPlayerName:Ljava/lang/String;

    invoke-virtual {p2, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->chatHistory:Ljava/util/ArrayList;

    invoke-virtual {p2}, Ljava/util/ArrayList;->clear()V

    const/4 p2, 0x0

    iput p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->unreadChatCount:I

    iget-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->peerStates:Ljava/util/LinkedHashMap;

    invoke-virtual {p2}, Ljava/util/LinkedHashMap;->clear()V

    const/4 p2, 0x0

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->localState:Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p2

    if-eqz p2, :cond_78

    const-string p2, "Sala LAN criada. Ate 6 jogadores podem entrar."

    goto :goto_7a

    :cond_78
    const-string p2, "LAN room created. Up to 6 players can join."

    :goto_7a
    invoke-direct {p0, p2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->clearDiscoveryLocked()V

    monitor-exit v1
    :try_end_81
    .catchall {:try_start_b .. :try_end_81} :catchall_217

    :try_start_81
    new-instance p2, Ljava/net/ServerSocket;

    invoke-direct {p2}, Ljava/net/ServerSocket;-><init>()V

    invoke-virtual {p2, v2}, Ljava/net/ServerSocket;->setReuseAddress(Z)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->resolvePreferredIpv4Address()Ljava/net/InetAddress;

    move-result-object v3

    const-string v1, "0.0.0.0"

    invoke-static {v1}, Ljava/net/InetAddress;->getByName(Ljava/lang/String;)Ljava/net/InetAddress;

    move-result-object v1

    new-instance v4, Ljava/net/InetSocketAddress;

    invoke-direct {v4, v1, p1}, Ljava/net/InetSocketAddress;-><init>(Ljava/net/InetAddress;I)V

    invoke-virtual {p2, v4}, Ljava/net/ServerSocket;->bind(Ljava/net/SocketAddress;)V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter p1
    :try_end_9e
    .catch Ljava/io/IOException; {:try_start_81 .. :try_end_9e} :catch_1a2

    :try_start_9e
    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostServerSocket:Ljava/net/ServerSocket;

    if-eqz v3, :cond_a7

    invoke-virtual {v3}, Ljava/net/InetAddress;->getHostAddress()Ljava/lang/String;

    move-result-object v1

    goto :goto_ab

    :cond_a7
    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->findLocalIpv4()Ljava/lang/String;

    move-result-object v1

    :goto_ab
    iput-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    monitor-exit p1
    :try_end_ae
    .catchall {:try_start_9e .. :try_end_ae} :catchall_19f

    :try_start_ae
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "host tcp="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p2}, Ljava/net/ServerSocket;->getLocalSocketAddress()Ljava/net/SocketAddress;

    move-result-object v4

    invoke-static {v4}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v4, " preferred="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v4, " localIps="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeLocalIpv4s()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V

    invoke-direct {p0, p2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->startAcceptThread(Ljava/net/ServerSocket;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->startDiscoveryResponder()V

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "host tcp="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p2}, Ljava/net/ServerSocket;->getLocalSocketAddress()Ljava/net/SocketAddress;

    move-result-object v4

    invoke-static {v4}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v4, " udp="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostDiscoverySocket:Ljava/net/DatagramSocket;

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeDatagramSocketState(Ljava/net/DatagramSocket;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v4, " preferred="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v4, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v4, " localIps="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeLocalIpv4s()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->showLanDiag(Ljava/lang/String;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "HOST ready port="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, " bind=0.0.0.0"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, " preferred="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, " localIps="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeLocalIpv4s()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->logLanDebug(Ljava/lang/String;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p1

    if-eqz p1, :cond_178

    const-string p1, "Sala LAN hospedada com sucesso."

    goto :goto_17a

    :cond_178
    const-string p1, "LAN room hosted successfully."

    :goto_17a
    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string p2, "[CYAN]LAN[] "

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->pt()Z

    move-result p2

    if-eqz p2, :cond_191

    const-string p2, "Sessao LAN hospedada."

    goto :goto_193

    :cond_191
    const-string p2, "LAN session hosted."

    :goto_193
    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanGameBridge;->postGameLog(Ljava/lang/String;)V
    :try_end_19e
    .catch Ljava/io/IOException; {:try_start_ae .. :try_end_19e} :catch_1a2

    goto :goto_213

    :catchall_19f
    move-exception p2

    :try_start_1a0
    monitor-exit p1
    :try_end_1a1
    .catchall {:try_start_1a0 .. :try_end_1a1} :catchall_19f

    :try_start_1a1
    throw p2
    :try_end_1a2
    .catch Ljava/io/IOException; {:try_start_1a1 .. :try_end_1a2} :catch_1a2

    :catch_1a2
    move-exception p1

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "HOST fail port="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostPort:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " preferred="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->hostAddress:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " localIps="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->describeLocalIpv4s()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " reason="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->logLanError(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->setStatusDiag(Ljava/lang/String;)V

    iget-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager;->lock:Ljava/lang/Object;

    monitor-enter p2

    :try_start_1e9
    invoke-direct {p0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->addSystemLineLocked(Ljava/lang/String;)V

    monitor-exit p2
    :try_end_1ed
    .catchall {:try_start_1e9 .. :try_end_1ed} :catchall_214

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->dispatchUi()V

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "lan_error_host_prefix"

    const-string v2, "Unable to host room: "

    invoke-direct {p0, v1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p2

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->safeMessage(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V

    invoke-direct {p0, v0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopAllInternal(ZZ)V

    :goto_213
    return-void

    :catchall_214
    move-exception p1

    :try_start_215
    monitor-exit p2
    :try_end_216
    .catchall {:try_start_215 .. :try_end_216} :catchall_214

    throw p1

    :catchall_217
    move-exception p1

    :try_start_218
    monitor-exit v1
    :try_end_219
    .catchall {:try_start_218 .. :try_end_219} :catchall_217

    throw p1
.end method

.method public stopAll()V
    .registers 2

    const/4 v0, 0x1

    invoke-direct {p0, v0, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopAllInternal(ZZ)V

    return-void
.end method
