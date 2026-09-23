.class Lnet/fdgames/ek/android/lan/LanLobbyActivity$6;
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

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$6;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 3

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$6;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->messageInput:Landroid/widget/EditText;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$500(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Landroid/widget/EditText;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_31

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$6;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # invokes: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->savePlayerName()V
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$000(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$6;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;
    invoke-static {v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$200(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object v0

    invoke-virtual {v0, p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendChatAsync(Ljava/lang/String;)V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$6;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->messageInput:Landroid/widget/EditText;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$500(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Landroid/widget/EditText;

    move-result-object p1

    const-string v0, ""

    invoke-virtual {p1, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    :cond_31
    return-void
.end method
