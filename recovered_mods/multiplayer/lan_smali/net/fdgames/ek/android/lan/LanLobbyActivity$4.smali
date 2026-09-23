.class Lnet/fdgames/ek/android/lan/LanLobbyActivity$4;
.super Ljava/lang/Object;
.source "LanLobbyActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanLobbyActivity;->buildContentView()Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V
    .registers 2

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$4;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 2

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$4;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$200(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object p1

    invoke-virtual {p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->stopAll()V

    return-void
.end method
