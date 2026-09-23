.class Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;
.super Ljava/lang/Object;
.source "LanLobbyActivity.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanLobbyActivity;->joinHostAsync(Ljava/lang/String;Ljava/lang/String;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

.field final synthetic val$address:Ljava/lang/String;

.field final synthetic val$playerName:Ljava/lang/String;

.field final synthetic val$port:I


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Ljava/lang/String;Ljava/lang/String;I)V
    .registers 5

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;->val$playerName:Ljava/lang/String;

    iput-object p3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;->val$address:Ljava/lang/String;

    iput p4, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;->val$port:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 5

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$200(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;->val$playerName:Ljava/lang/String;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;->val$address:Ljava/lang/String;

    iget v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;->val$port:I

    invoke-virtual {v0, v1, v2, v3}, Lnet/fdgames/ek/android/lan/LanSessionManager;->joinHost(Ljava/lang/String;Ljava/lang/String;I)V

    return-void
.end method
