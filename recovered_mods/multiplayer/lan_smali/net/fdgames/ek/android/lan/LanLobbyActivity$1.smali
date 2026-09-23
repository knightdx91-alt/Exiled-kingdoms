.class Lnet/fdgames/ek/android/lan/LanLobbyActivity$1;
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

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$1;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 4

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$1;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # invokes: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->savePlayerName()V
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$000(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$1;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$100(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x6

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$1;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # invokes: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->startHostingAsync(Ljava/lang/String;I)V
    invoke-static {p1, v0, v1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$700(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Ljava/lang/String;I)V

    return-void
.end method
