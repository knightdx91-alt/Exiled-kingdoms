.class final Lnet/fdgames/ek/android/lan/LanSessionManagerChatSendRunnable;
.super Ljava/lang/Object;
.source "LanSessionManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final message:Ljava/lang/String;

.field private final sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Ljava/lang/String;)V
    .registers 3

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManagerChatSendRunnable;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManagerChatSendRunnable;->message:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManagerChatSendRunnable;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    if-eqz v0, :cond_9

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManagerChatSendRunnable;->message:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendChat(Ljava/lang/String;)V

    :cond_9
    return-void
.end method
