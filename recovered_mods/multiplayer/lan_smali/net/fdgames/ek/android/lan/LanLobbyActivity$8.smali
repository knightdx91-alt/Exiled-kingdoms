.class Lnet/fdgames/ek/android/lan/LanLobbyActivity$8;
.super Ljava/lang/Object;
.source "LanLobbyActivity.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanLobbyActivity;->startHostingAsync(Ljava/lang/String;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

.field final synthetic val$maxPlayers:I

.field final synthetic val$playerName:Ljava/lang/String;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Ljava/lang/String;I)V
    .registers 4

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$8;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$8;->val$playerName:Ljava/lang/String;

    iput p3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$8;->val$maxPlayers:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 4

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$8;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$200(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$8;->val$playerName:Ljava/lang/String;

    iget v2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$8;->val$maxPlayers:I

    invoke-virtual {v0, v1, v2}, Lnet/fdgames/ek/android/lan/LanSessionManager;->startHosting(Ljava/lang/String;I)V

    return-void
.end method
