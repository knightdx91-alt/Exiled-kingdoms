.class Lnet/fdgames/ek/android/lan/LanLobbyActivity$7;
.super Ljava/lang/Object;
.source "LanLobbyActivity.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanLobbyActivity;->onChatUpdated(Ljava/util/List;)V
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

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$7;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$7;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatScroll:Landroid/widget/ScrollView;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$600(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Landroid/widget/ScrollView;

    move-result-object v0

    const/16 v1, 0x82

    invoke-virtual {v0, v1}, Landroid/widget/ScrollView;->fullScroll(I)Z

    return-void
.end method
