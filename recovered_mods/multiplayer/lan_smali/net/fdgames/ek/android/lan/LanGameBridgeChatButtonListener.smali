.class final Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;
.super Ljava/lang/Object;
.source "LanGameBridge.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field private final chatView:Landroid/widget/TextView;

.field private final input:Landroid/widget/EditText;

.field private final mode:I

.field private final sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;


# direct methods
.method constructor <init>(ILnet/fdgames/ek/android/lan/LanSessionManager;Landroid/widget/EditText;Landroid/widget/TextView;)V
    .registers 5

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->mode:I

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iput-object p3, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->input:Landroid/widget/EditText;

    iput-object p4, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->chatView:Landroid/widget/TextView;

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 5

    iget v0, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->mode:I

    if-nez v0, :cond_25

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->input:Landroid/widget/EditText;

    if-eqz v0, :cond_1c

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {v1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->sendChatAsync(Ljava/lang/String;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->input:Landroid/widget/EditText;

    const-string v1, ""

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    :cond_1c
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->chatView:Landroid/widget/TextView;

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->refreshChatDialogView(Lnet/fdgames/ek/android/lan/LanSessionManager;Landroid/widget/TextView;Landroid/widget/ScrollView;)V

    return-void

    :cond_25
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;->chatView:Landroid/widget/TextView;

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Lnet/fdgames/ek/android/lan/LanGameBridge;->refreshChatDialogView(Lnet/fdgames/ek/android/lan/LanSessionManager;Landroid/widget/TextView;Landroid/widget/ScrollView;)V

    return-void
.end method
