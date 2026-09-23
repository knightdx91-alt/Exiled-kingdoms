.class Lnet/fdgames/ek/android/lan/LanLobbyActivity$2;
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

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$2;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 2

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$2;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # invokes: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->promptJoinByIp()V
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$300(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    return-void
.end method
