.class final Lnet/fdgames/ek/android/lan/LanGameBridgeChatRunnable;
.super Ljava/lang/Object;
.source "LanGameBridge.java"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final activity:Lnet/fdgames/ek/android/MainActivity;

.field private final sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/MainActivity;Lnet/fdgames/ek/android/lan/LanSessionManager;)V
    .registers 3

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatRunnable;->activity:Lnet/fdgames/ek/android/MainActivity;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatRunnable;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    return-void
.end method


# virtual methods
.method public run()V
    .registers 16

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatRunnable;->activity:Lnet/fdgames/ek/android/MainActivity;

    if-eqz v0, :cond_19e

    invoke-virtual {v0}, Landroid/app/Activity;->isFinishing()Z

    move-result v1

    if-nez v1, :cond_19e

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeChatRunnable;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    if-eqz v1, :cond_19e

    invoke-virtual {v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v2

    if-eqz v2, :cond_19e

    new-instance v2, Landroid/widget/LinearLayout;

    invoke-direct {v2, v0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->setOrientation(I)V

    const/16 v4, 0x18

    invoke-virtual {v2, v4, v4, v4, v4}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    const v4, -0xe5edf8

    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->setBackgroundColor(I)V

    const/16 v4, 0x18

    new-instance v5, Landroid/widget/ScrollView;

    invoke-direct {v5, v0}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    const v4, -0x33e5f0f9    # -4.0385564E7f

    invoke-virtual {v5, v4}, Landroid/widget/ScrollView;->setBackgroundColor(I)V

    new-instance v6, Landroid/widget/TextView;

    invoke-direct {v6, v0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    const/high16 v7, 0x41800000    # 16.0f

    invoke-virtual {v6, v7}, Landroid/widget/TextView;->setTextSize(F)V

    const v4, -0xb132e

    invoke-virtual {v6, v4}, Landroid/widget/TextView;->setTextColor(I)V

    const/16 v4, 0x8

    invoke-virtual {v6, v4, v4, v4, v4}, Landroid/widget/TextView;->setPadding(IIII)V

    invoke-virtual {v5, v6}, Landroid/widget/ScrollView;->addView(Landroid/view/View;)V

    new-instance v7, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v8, -0x1

    const/4 v9, 0x0

    const/high16 v10, 0x3f800000    # 1.0f

    invoke-direct {v7, v8, v9, v10}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    invoke-virtual {v2, v5, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v7, Landroid/widget/EditText;

    invoke-direct {v7, v0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    const-string v9, "LAN_CHAT_HINT"

    const/4 v11, 0x0

    invoke-static {v9, v11}, Lnet/fdgames/Helpers/GameString;->b(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v7, v9}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    const/4 v9, 0x0

    invoke-virtual {v7, v9}, Landroid/widget/EditText;->setSingleLine(Z)V

    new-instance v9, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v10, -0x2

    invoke-direct {v9, v8, v10}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v2, v7, v9}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v8, Landroid/widget/Button;

    invoke-direct {v8, v0}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    const-string v9, "LAN_CHAT_LOCATION"

    const/4 v11, 0x0

    invoke-static {v9, v11}, Lnet/fdgames/Helpers/GameString;->b(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    new-instance v9, Lnet/fdgames/ek/android/lan/LanGameBridgeLocationButtonListener;

    invoke-direct {v9, v1, v6}, Lnet/fdgames/ek/android/lan/LanGameBridgeLocationButtonListener;-><init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Landroid/widget/TextView;)V

    invoke-virtual {v8, v9}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    invoke-virtual {v2, v8}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    new-instance v8, Landroid/app/AlertDialog$Builder;

    invoke-direct {v8, v0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const-string v9, "LAN_CHAT_TITLE"

    const/4 v11, 0x0

    invoke-static {v9, v11}, Lnet/fdgames/Helpers/GameString;->b(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v8

    invoke-virtual {v8, v2}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    move-result-object v8

    const-string v2, "LAN_CHAT_SEND"

    const/4 v11, 0x0

    invoke-static {v2, v11}, Lnet/fdgames/Helpers/GameString;->b(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v2

    const/4 v9, 0x0

    invoke-virtual {v8, v2, v9}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v2

    const-string v8, "LAN_CHAT_REFRESH"

    const/4 v11, 0x0

    invoke-static {v8, v11}, Lnet/fdgames/Helpers/GameString;->b(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v2, v8, v9}, Landroid/app/AlertDialog$Builder;->setNeutralButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v2

    const-string v8, "LAN_CHAT_CLOSE"

    const/4 v11, 0x0

    invoke-static {v8, v11}, Lnet/fdgames/Helpers/GameString;->b(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v2, v8, v9}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;

    move-result-object v2

    new-instance v13, Landroid/graphics/drawable/GradientDrawable;

    invoke-direct {v13}, Landroid/graphics/drawable/GradientDrawable;-><init>()V

    const/4 v14, 0x0

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setShape(I)V

    const v14, -0xe5edf8

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setColor(I)V

    const/high16 v12, 0x41000000    # 8.0f

    invoke-virtual {v13, v12}, Landroid/graphics/drawable/GradientDrawable;->setCornerRadius(F)V

    const/4 v14, 0x2

    const v12, -0x375fd0

    invoke-virtual {v13, v14, v12}, Landroid/graphics/drawable/GradientDrawable;->setStroke(II)V

    invoke-virtual {v2}, Landroid/app/Dialog;->getWindow()Landroid/view/Window;

    move-result-object v14

    invoke-virtual {v14, v13}, Landroid/view/Window;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    invoke-virtual {v14}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v13

    const v12, 0x1020016

    invoke-virtual {v13, v12}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v13

    if-eqz v13, :cond_105

    check-cast v13, Landroid/widget/TextView;

    const v12, -0x375fd0

    invoke-virtual {v13, v12}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v12, 0x41900000    # 18.0f

    invoke-virtual {v13, v12}, Landroid/widget/TextView;->setTextSize(F)V

    :cond_105
    new-instance v13, Landroid/graphics/drawable/GradientDrawable;

    invoke-direct {v13}, Landroid/graphics/drawable/GradientDrawable;-><init>()V

    const/4 v14, 0x0

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setShape(I)V

    const v14, -0x91b4e2

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setColor(I)V

    const/high16 v14, 0x40c00000    # 6.0f

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setCornerRadius(F)V

    const/4 v14, 0x2

    const v12, -0x375fd0

    invoke-virtual {v13, v14, v12}, Landroid/graphics/drawable/GradientDrawable;->setStroke(II)V

    const/4 v14, -0x1

    invoke-virtual {v2, v14}, Landroid/app/AlertDialog;->getButton(I)Landroid/widget/Button;

    move-result-object v14

    if-eqz v14, :cond_12e

    invoke-virtual {v14, v13}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/4 v12, -0x1

    invoke-virtual {v14, v12}, Landroid/widget/Button;->setTextColor(I)V

    :cond_12e
    new-instance v13, Landroid/graphics/drawable/GradientDrawable;

    invoke-direct {v13}, Landroid/graphics/drawable/GradientDrawable;-><init>()V

    const/4 v14, 0x0

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setShape(I)V

    const v14, -0x91b4e2

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setColor(I)V

    const/high16 v14, 0x40c00000    # 6.0f

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setCornerRadius(F)V

    const/4 v14, 0x2

    const v12, -0x375fd0

    invoke-virtual {v13, v14, v12}, Landroid/graphics/drawable/GradientDrawable;->setStroke(II)V

    const/4 v14, -0x3

    invoke-virtual {v2, v14}, Landroid/app/AlertDialog;->getButton(I)Landroid/widget/Button;

    move-result-object v14

    if-eqz v14, :cond_157

    invoke-virtual {v14, v13}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/4 v12, -0x1

    invoke-virtual {v14, v12}, Landroid/widget/Button;->setTextColor(I)V

    :cond_157
    new-instance v13, Landroid/graphics/drawable/GradientDrawable;

    invoke-direct {v13}, Landroid/graphics/drawable/GradientDrawable;-><init>()V

    const/4 v14, 0x0

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setShape(I)V

    const v14, -0x91b4e2

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setColor(I)V

    const/high16 v14, 0x40c00000    # 6.0f

    invoke-virtual {v13, v14}, Landroid/graphics/drawable/GradientDrawable;->setCornerRadius(F)V

    const/4 v14, 0x2

    const v12, -0x375fd0

    invoke-virtual {v13, v14, v12}, Landroid/graphics/drawable/GradientDrawable;->setStroke(II)V

    const/4 v14, -0x2

    invoke-virtual {v2, v14}, Landroid/app/AlertDialog;->getButton(I)Landroid/widget/Button;

    move-result-object v14

    if-eqz v14, :cond_180

    invoke-virtual {v14, v13}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/4 v12, -0x1

    invoke-virtual {v14, v12}, Landroid/widget/Button;->setTextColor(I)V

    :cond_180
    invoke-static {v1, v6, v5}, Lnet/fdgames/ek/android/lan/LanGameBridge;->refreshChatDialogView(Lnet/fdgames/ek/android/lan/LanSessionManager;Landroid/widget/TextView;Landroid/widget/ScrollView;)V

    const/4 v8, -0x1

    invoke-virtual {v2, v8}, Landroid/app/AlertDialog;->getButton(I)Landroid/widget/Button;

    move-result-object v8

    new-instance v9, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;

    const/4 v10, 0x0

    invoke-direct {v9, v10, v1, v7, v6}, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;-><init>(ILnet/fdgames/ek/android/lan/LanSessionManager;Landroid/widget/EditText;Landroid/widget/TextView;)V

    invoke-virtual {v8, v9}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const/4 v8, -0x3

    invoke-virtual {v2, v8}, Landroid/app/AlertDialog;->getButton(I)Landroid/widget/Button;

    move-result-object v2

    new-instance v8, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;

    invoke-direct {v8, v3, v1, v7, v6}, Lnet/fdgames/ek/android/lan/LanGameBridgeChatButtonListener;-><init>(ILnet/fdgames/ek/android/lan/LanSessionManager;Landroid/widget/EditText;Landroid/widget/TextView;)V

    invoke-virtual {v2, v8}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    :cond_19e
    return-void
.end method
