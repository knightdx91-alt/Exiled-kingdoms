.class public Lnet/fdgames/ek/android/lan/LanLobbyActivity;
.super Landroid/app/Activity;
.source "LanLobbyActivity.java"

# interfaces
.implements Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;


# static fields
.field private static final PREFS:Ljava/lang/String; = "ek_lan_prefs"

.field private static final PREF_NAME:Ljava/lang/String; = "lan_player_name"


# instance fields
.field private chatScroll:Landroid/widget/ScrollView;

.field private chatView:Landroid/widget/TextView;

.field private diagScroll:Landroid/widget/ScrollView;

.field private diagView:Landroid/widget/TextView;

.field private discoveryAdapter:Landroid/widget/ArrayAdapter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/widget/ArrayAdapter<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private final discoveryResults:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;",
            ">;"
        }
    .end annotation
.end field

.field private messageInput:Landroid/widget/EditText;

.field private multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

.field private playerNameInput:Landroid/widget/EditText;

.field private playersView:Landroid/widget/TextView;

.field private sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

.field private statusView:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .registers 2

    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryResults:Ljava/util/List;

    return-void
.end method

.method static synthetic access$000(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V
    .registers 1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->savePlayerName()V

    return-void
.end method

.method static synthetic access$100(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Landroid/widget/EditText;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    return-object p0
.end method

.method static synthetic access$200(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Lnet/fdgames/ek/android/lan/LanSessionManager;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    return-object p0
.end method

.method static synthetic access$300(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V
    .registers 1

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->promptJoinByIp()V

    return-void
.end method

.method static synthetic access$400(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Ljava/util/List;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryResults:Ljava/util/List;

    return-object p0
.end method

.method static synthetic access$500(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Landroid/widget/EditText;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->messageInput:Landroid/widget/EditText;

    return-object p0
.end method

.method static synthetic access$600(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Landroid/widget/ScrollView;
    .registers 1

    iget-object p0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatScroll:Landroid/widget/ScrollView;

    return-object p0
.end method

.method static synthetic access$700(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Ljava/lang/String;I)V
    .registers 3

    invoke-direct {p0, p1, p2}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->startHostingAsync(Ljava/lang/String;I)V

    return-void
.end method

.method static synthetic access$800(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Ljava/lang/String;Ljava/lang/String;I)V
    .registers 4

    invoke-direct {p0, p1, p2, p3}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->joinHostAsync(Ljava/lang/String;Ljava/lang/String;I)V

    return-void
.end method

.method private acquireMulticastLock()V
    .registers 3

    :try_start_0
    invoke-virtual {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    const-string v1, "wifi"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/wifi/WifiManager;

    if-eqz v0, :cond_25

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

    if-nez v1, :cond_25

    const-string v1, "ek-lan-lock"

    invoke-virtual {v0, v1}, Landroid/net/wifi/WifiManager;->createMulticastLock(Ljava/lang/String;)Landroid/net/wifi/WifiManager$MulticastLock;

    move-result-object v0

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/net/wifi/WifiManager$MulticastLock;->setReferenceCounted(Z)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

    invoke-virtual {v0}, Landroid/net/wifi/WifiManager$MulticastLock;->acquire()V
    :try_end_25
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_25} :catch_26

    :cond_25
    goto :goto_27

    :catch_26
    move-exception v0

    :goto_27
    return-void
.end method

.method private addControlButton(Landroid/widget/LinearLayout;Ljava/lang/String;Landroid/view/View$OnClickListener;)V
    .registers 12

    new-instance v0, Landroid/widget/Button;

    invoke-direct {v0, p0}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0, p2}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    invoke-virtual {v0, p3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const v1, -0x1

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setTextColor(I)V

    const/high16 v1, 0x41800000    # 16.0f

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setTextSize(F)V

    new-instance v1, Landroid/graphics/drawable/GradientDrawable;

    invoke-direct {v1}, Landroid/graphics/drawable/GradientDrawable;-><init>()V

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Landroid/graphics/drawable/GradientDrawable;->setShape(I)V

    const v2, -0x91b4e2

    invoke-virtual {v1, v2}, Landroid/graphics/drawable/GradientDrawable;->setColor(I)V

    const/4 v2, 0x6

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v2

    int-to-float v3, v2

    invoke-virtual {v1, v3}, Landroid/graphics/drawable/GradientDrawable;->setCornerRadius(F)V

    const/4 v4, 0x2

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v4

    const v5, -0x375fd0

    invoke-virtual {v1, v4, v5}, Landroid/graphics/drawable/GradientDrawable;->setStroke(II)V

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/16 v2, 0x8

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v2

    invoke-virtual {v0, v2, v2, v2, v2}, Landroid/widget/Button;->setPadding(IIII)V

    new-instance p2, Landroid/widget/LinearLayout$LayoutParams;

    const/4 p3, -0x2

    const/high16 v6, 0x3f800000    # 1.0f

    const/4 v7, 0x0

    invoke-direct {p2, v7, p3, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    const/4 v2, 0x3

    invoke-direct {p0, v2}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v2

    iput v2, p2, Landroid/widget/LinearLayout$LayoutParams;->leftMargin:I

    iput v2, p2, Landroid/widget/LinearLayout$LayoutParams;->rightMargin:I

    invoke-virtual {p1, v0, p2}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    return-void
.end method

.method private buildContentView()Landroid/view/View;
    .registers 14

    const/16 v0, 0xc

    invoke-direct {p0, v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v0

    new-instance v1, Landroid/widget/LinearLayout;

    invoke-direct {v1, p0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/4 v2, 0x1

    invoke-virtual {v1, v2}, Landroid/widget/LinearLayout;->setOrientation(I)V

    invoke-virtual {v1, v0, v0, v0, v0}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    const v3, -0xe5edf8

    invoke-virtual {v1, v3}, Landroid/widget/LinearLayout;->setBackgroundColor(I)V

    new-instance v3, Landroid/widget/TextView;

    invoke-direct {v3, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    const-string v4, "LAN / CHAT BETA"

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const/high16 v4, 0x41b00000    # 22.0f

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setTextSize(F)V

    invoke-virtual {v3, v2}, Landroid/widget/TextView;->setGravity(I)V

    const v4, -0x375fd0

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setTextColor(I)V

    new-instance v4, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v5, -0x1

    const/4 v6, -0x2

    invoke-direct {v4, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v3, Landroid/widget/TextView;

    invoke-direct {v3, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    const-string v4, "lan_subtitle"

    const-string v7, "Host, join, discover LAN sessions and chat. Map/combat sync will arrive in phases."

    invoke-direct {p0, v4, v7}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const v4, -0x324770

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v4, 0x41600000    # 14.0f

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setTextSize(F)V

    const/4 v4, 0x6

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v7

    const/16 v8, 0xa

    invoke-direct {p0, v8}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v8

    const/4 v9, 0x0

    invoke-virtual {v3, v9, v7, v9, v8}, Landroid/widget/TextView;->setPadding(IIII)V

    new-instance v7, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v7, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v3, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v3, Landroid/widget/TextView;

    invoke-direct {v3, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->statusView:Landroid/widget/TextView;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->statusView:Landroid/widget/TextView;

    invoke-virtual {v3, v0, v0, v0, v0}, Landroid/widget/TextView;->setPadding(IIII)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->statusView:Landroid/widget/TextView;

    const/high16 v7, 0x41700000    # 15.0f

    invoke-virtual {v3, v7}, Landroid/widget/TextView;->setTextSize(F)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->statusView:Landroid/widget/TextView;

    const v7, 0x66331f0d

    invoke-virtual {v3, v7}, Landroid/widget/TextView;->setBackgroundColor(I)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->statusView:Landroid/widget/TextView;

    const v7, -0xb132e

    invoke-virtual {v3, v7}, Landroid/widget/TextView;->setTextColor(I)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->statusView:Landroid/widget/TextView;

    const/4 v7, 0x3

    invoke-virtual {v3, v7}, Landroid/widget/TextView;->setMaxLines(I)V

    new-instance v7, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v7, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v3, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v3, Landroid/widget/EditText;

    invoke-direct {v3, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    const-string v7, "lan_player_name_hint"

    const-string v8, "Player name"

    invoke-direct {p0, v7, v8}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v3, v7}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    invoke-virtual {v3, v2}, Landroid/widget/EditText;->setSingleLine(Z)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    const/16 v7, 0x2001

    invoke-virtual {v3, v7}, Landroid/widget/EditText;->setInputType(I)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    new-instance v7, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v7, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v3, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v3, Landroid/widget/LinearLayout;

    invoke-direct {v3, p0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    invoke-virtual {v3, v9}, Landroid/widget/LinearLayout;->setOrientation(I)V

    const/16 v7, 0x8

    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v8

    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v10

    invoke-virtual {v3, v9, v8, v9, v10}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    new-instance v8, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v8, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v3, v8}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    const-string v8, "lan_button_host"

    const-string v10, "Host"

    invoke-direct {p0, v8, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    new-instance v10, Lnet/fdgames/ek/android/lan/LanLobbyActivity$1;

    invoke-direct {v10, p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$1;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    invoke-direct {p0, v3, v8, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->addControlButton(Landroid/widget/LinearLayout;Ljava/lang/String;Landroid/view/View$OnClickListener;)V

    const-string v8, "lan_button_join_ip"

    const-string v10, "Join IP"

    invoke-direct {p0, v8, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    new-instance v10, Lnet/fdgames/ek/android/lan/LanLobbyActivity$2;

    invoke-direct {v10, p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$2;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    invoke-direct {p0, v3, v8, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->addControlButton(Landroid/widget/LinearLayout;Ljava/lang/String;Landroid/view/View$OnClickListener;)V

    const-string v8, "lan_button_scan_lan"

    const-string v10, "Scan LAN"

    invoke-direct {p0, v8, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    new-instance v10, Lnet/fdgames/ek/android/lan/LanLobbyActivity$3;

    invoke-direct {v10, p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$3;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    invoke-direct {p0, v3, v8, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->addControlButton(Landroid/widget/LinearLayout;Ljava/lang/String;Landroid/view/View$OnClickListener;)V

    const-string v8, "lan_button_leave"

    const-string v10, "Leave"

    invoke-direct {p0, v8, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    new-instance v10, Lnet/fdgames/ek/android/lan/LanLobbyActivity$4;

    invoke-direct {v10, p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$4;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    invoke-direct {p0, v3, v8, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->addControlButton(Landroid/widget/LinearLayout;Ljava/lang/String;Landroid/view/View$OnClickListener;)V

    new-instance v8, Landroid/widget/ScrollView;

    invoke-direct {v8, p0}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    iput-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagScroll:Landroid/widget/ScrollView;

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagScroll:Landroid/widget/ScrollView;

    const/16 v10, 0x8

    invoke-virtual {v8, v10}, Landroid/widget/ScrollView;->setVisibility(I)V

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagScroll:Landroid/widget/ScrollView;

    invoke-virtual {v8, v2}, Landroid/widget/ScrollView;->setFillViewport(Z)V

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagScroll:Landroid/widget/ScrollView;

    invoke-virtual {v8, v9}, Landroid/widget/ScrollView;->setScrollbarFadingEnabled(Z)V

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagScroll:Landroid/widget/ScrollView;

    const v10, 0x4d1a0f07    # 1.6154226E8f

    invoke-virtual {v8, v10}, Landroid/widget/ScrollView;->setBackgroundColor(I)V

    new-instance v8, Landroid/widget/TextView;

    invoke-direct {v8, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagView:Landroid/widget/TextView;

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagView:Landroid/widget/TextView;

    const/4 v10, 0x6

    invoke-direct {p0, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v10

    invoke-virtual {v8, v10, v10, v10, v10}, Landroid/widget/TextView;->setPadding(IIII)V

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagView:Landroid/widget/TextView;

    const/high16 v10, 0x41400000    # 12.0f

    invoke-virtual {v8, v10}, Landroid/widget/TextView;->setTextSize(F)V

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagView:Landroid/widget/TextView;

    const v10, -0x21347d

    invoke-virtual {v8, v10}, Landroid/widget/TextView;->setTextColor(I)V

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagScroll:Landroid/widget/ScrollView;

    iget-object v10, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagView:Landroid/widget/TextView;

    new-instance v11, Landroid/widget/FrameLayout$LayoutParams;

    invoke-direct {v11, v5, v6}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v8, v10, v11}, Landroid/widget/ScrollView;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagScroll:Landroid/widget/ScrollView;

    new-instance v10, Landroid/widget/LinearLayout$LayoutParams;

    const/16 v11, 0x2c

    invoke-direct {p0, v11}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v11

    invoke-direct {v10, v5, v11}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v8, v10}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v3, Landroid/widget/TextView;

    invoke-direct {v3, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    const-string v8, "lan_heading_sessions"

    const-string v10, "Discovered sessions"

    invoke-direct {p0, v8, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const v8, -0x21347d

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v8, 0x41800000    # 16.0f

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setTextSize(F)V

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v8

    const/4 v10, 0x4

    invoke-direct {p0, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v11

    invoke-virtual {v3, v9, v8, v9, v11}, Landroid/widget/TextView;->setPadding(IIII)V

    invoke-virtual {v1, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    new-instance v3, Landroid/widget/ListView;

    invoke-direct {v3, p0}, Landroid/widget/ListView;-><init>(Landroid/content/Context;)V

    new-instance v8, Landroid/widget/ArrayAdapter;

    new-instance v11, Ljava/util/ArrayList;

    invoke-direct {v11}, Ljava/util/ArrayList;-><init>()V

    const v12, 0x1090003

    invoke-direct {v8, p0, v12, v11}, Landroid/widget/ArrayAdapter;-><init>(Landroid/content/Context;ILjava/util/List;)V

    iput-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryAdapter:Landroid/widget/ArrayAdapter;

    iget-object v8, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryAdapter:Landroid/widget/ArrayAdapter;

    invoke-virtual {v3, v8}, Landroid/widget/ListView;->setAdapter(Landroid/widget/ListAdapter;)V

    new-instance v8, Lnet/fdgames/ek/android/lan/LanLobbyActivity$5;

    invoke-direct {v8, p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$5;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    invoke-virtual {v3, v8}, Landroid/widget/ListView;->setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V

    new-instance v8, Landroid/widget/LinearLayout$LayoutParams;

    const/16 v11, 0x8c

    invoke-direct {p0, v11}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v11

    invoke-direct {v8, v5, v11}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v3, v8}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v3, Landroid/widget/TextView;

    invoke-direct {v3, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    const-string v8, "lan_heading_players"

    const-string v11, "Players in room"

    invoke-direct {p0, v8, v11}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const v8, -0x21347d

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v8, 0x41800000    # 16.0f

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setTextSize(F)V

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v8

    invoke-direct {p0, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v11

    invoke-virtual {v3, v9, v8, v9, v11}, Landroid/widget/TextView;->setPadding(IIII)V

    invoke-virtual {v1, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    new-instance v3, Landroid/widget/TextView;

    invoke-direct {v3, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playersView:Landroid/widget/TextView;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playersView:Landroid/widget/TextView;

    invoke-virtual {v3, v0, v0, v0, v0}, Landroid/widget/TextView;->setPadding(IIII)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playersView:Landroid/widget/TextView;

    const v8, 0x221f160d

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setBackgroundColor(I)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playersView:Landroid/widget/TextView;

    const v8, -0xb132e

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setTextColor(I)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playersView:Landroid/widget/TextView;

    new-instance v8, Landroid/widget/LinearLayout$LayoutParams;

    const/16 v11, 0x5a

    invoke-direct {p0, v11}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v11

    invoke-direct {v8, v5, v11}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v3, v8}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v3, Landroid/widget/TextView;

    invoke-direct {v3, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    const-string v8, "lan_heading_chat"

    const-string v11, "Room chat"

    invoke-direct {p0, v8, v11}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const v8, -0x21347d

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v8, 0x41800000    # 16.0f

    invoke-virtual {v3, v8}, Landroid/widget/TextView;->setTextSize(F)V

    invoke-direct {p0, v4}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v4

    invoke-direct {p0, v10}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v8

    invoke-virtual {v3, v9, v4, v9, v8}, Landroid/widget/TextView;->setPadding(IIII)V

    invoke-virtual {v1, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    new-instance v3, Landroid/widget/ScrollView;

    invoke-direct {v3, p0}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatScroll:Landroid/widget/ScrollView;

    new-instance v3, Landroid/widget/TextView;

    invoke-direct {v3, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatView:Landroid/widget/TextView;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatView:Landroid/widget/TextView;

    invoke-virtual {v3, v0, v0, v0, v0}, Landroid/widget/TextView;->setPadding(IIII)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatView:Landroid/widget/TextView;

    const v4, -0xb132e

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setTextColor(I)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatScroll:Landroid/widget/ScrollView;

    const v4, 0x221f160d

    invoke-virtual {v3, v4}, Landroid/widget/ScrollView;->setBackgroundColor(I)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatScroll:Landroid/widget/ScrollView;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatView:Landroid/widget/TextView;

    new-instance v4, Landroid/widget/FrameLayout$LayoutParams;

    invoke-direct {v4, v5, v6}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v0, v3, v4}, Landroid/widget/ScrollView;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatScroll:Landroid/widget/ScrollView;

    new-instance v3, Landroid/widget/LinearLayout$LayoutParams;

    const/high16 v4, 0x3f800000    # 1.0f

    invoke-direct {v3, v5, v9, v4}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    invoke-virtual {v1, v0, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v0, Landroid/widget/LinearLayout;

    invoke-direct {v0, p0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0, v9}, Landroid/widget/LinearLayout;->setOrientation(I)V

    invoke-direct {p0, v7}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->dp(I)I

    move-result v3

    invoke-virtual {v0, v9, v3, v9, v9}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    new-instance v3, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v3, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v0, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v3, Landroid/widget/EditText;

    invoke-direct {v3, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    iput-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->messageInput:Landroid/widget/EditText;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->messageInput:Landroid/widget/EditText;

    const-string v5, "lan_message_hint"

    const-string v8, "Message"

    invoke-direct {p0, v5, v8}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->messageInput:Landroid/widget/EditText;

    invoke-virtual {v3, v2}, Landroid/widget/EditText;->setSingleLine(Z)V

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->messageInput:Landroid/widget/EditText;

    new-instance v3, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v3, v9, v6, v4}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    invoke-virtual {v0, v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    new-instance v2, Landroid/widget/Button;

    invoke-direct {v2, p0}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    const-string v3, "lan_button_send"

    const-string v4, "Send"

    invoke-direct {p0, v3, v4}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    new-instance v3, Lnet/fdgames/ek/android/lan/LanLobbyActivity$6;

    invoke-direct {v3, p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$6;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    invoke-virtual {v2, v3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    new-instance v3, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v3, v6, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v0, v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    return-object v1
.end method

.method private dp(I)I
    .registers 3

    invoke-virtual {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    int-to-float p1, p1

    mul-float v0, v0, p1

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result p1

    return p1
.end method

.method private isPortuguese()Z
    .registers 3

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/Locale;->getLanguage()Ljava/lang/String;

    move-result-object v0

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {v0, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "pt"

    invoke-virtual {v0, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method private joinHostAsync(Ljava/lang/String;Ljava/lang/String;I)V
    .registers 7

    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;

    invoke-direct {v1, p0, p1, p2, p3}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$9;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Ljava/lang/String;Ljava/lang/String;I)V

    const-string p1, "ek-lan-join-ui"

    invoke-direct {v0, v1, p1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    const/4 p1, 0x1

    invoke-virtual {v0, p1}, Ljava/lang/Thread;->setDaemon(Z)V

    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    return-void
.end method

.method private lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 7

    invoke-virtual {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->getPackageName()Ljava/lang/String;

    move-result-object v1

    const-string v2, "string"

    invoke-virtual {v0, p1, v2, v1}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v1

    if-eqz v1, :cond_17

    invoke-virtual {p0, v1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->getString(I)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_17

    return-object v2

    :cond_17
    return-object p2
.end method

.method private promptJoinByIp()V
    .registers 5

    new-instance v0, Landroid/widget/EditText;

    invoke-direct {v0, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    const-string v1, "192.168.0.15"

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setSingleLine(Z)V

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setInputType(I)V

    new-instance v1, Landroid/app/AlertDialog$Builder;

    invoke-direct {v1, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const-string v2, "lan_join_ip_title"

    const-string v3, "Join by IP"

    invoke-direct {p0, v2, v3}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    const-string v2, "lan_button_join"

    const-string v3, "Join"

    invoke-direct {p0, v2, v3}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lnet/fdgames/ek/android/lan/LanLobbyActivity$$ExternalSyntheticLambda0;

    invoke-direct {v3, p0, v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$$ExternalSyntheticLambda0;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Landroid/widget/EditText;)V

    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const-string v1, "lan_button_cancel"

    const-string v2, "Cancel"

    invoke-direct {p0, v1, v2}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;

    return-void
.end method

.method private refreshUi()V
    .registers 5

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getLocalPlayerName()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_2e

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_2e

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    invoke-virtual {v2}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_2e

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    invoke-virtual {v2, v1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->savePlayerName()V

    :cond_2e
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getStateText()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->onStateChanged(Ljava/lang/String;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPlayersSnapshot()Ljava/util/List;

    move-result-object v0

    invoke-virtual {p0, v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->onPlayersChanged(Ljava/util/List;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getChatSnapshot()Ljava/util/List;

    move-result-object v0

    invoke-virtual {p0, v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->onChatUpdated(Ljava/util/List;)V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getDiscoverySnapshot()Ljava/util/List;

    move-result-object v0

    invoke-virtual {p0, v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->onDiscoveryUpdated(Ljava/util/List;)V

    return-void
.end method

.method private releaseMulticastLock()V
    .registers 3

    const/4 v0, 0x0

    :try_start_1
    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

    if-eqz v1, :cond_18

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

    invoke-virtual {v1}, Landroid/net/wifi/WifiManager$MulticastLock;->isHeld()Z

    move-result v1

    if-eqz v1, :cond_18

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

    invoke-virtual {v1}, Landroid/net/wifi/WifiManager$MulticastLock;->release()V
    :try_end_12
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_12} :catch_17
    .catchall {:try_start_1 .. :try_end_12} :catchall_13

    goto :goto_18

    :catchall_13
    move-exception v1

    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

    throw v1

    :catch_17
    move-exception v1

    :cond_18
    :goto_18
    iput-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->multicastLock:Landroid/net/wifi/WifiManager$MulticastLock;

    nop

    return-void
.end method

.method private restorePlayerName()V
    .registers 5

    const-string v0, "ek_lan_prefs"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    const-string v2, "lan_default_player_name"

    const-string v3, "Player"

    invoke-direct {p0, v2, v3}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    const-string v3, "lan_player_name"

    invoke-interface {v0, v3, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.method private savePlayerName()V
    .registers 4

    const-string v0, "ek_lan_prefs"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    invoke-virtual {v1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    const-string v2, "lan_player_name"

    invoke-interface {v0, v2, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method private startHostingAsync(Ljava/lang/String;I)V
    .registers 6

    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lnet/fdgames/ek/android/lan/LanLobbyActivity$8;

    invoke-direct {v1, p0, p1, p2}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$8;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Ljava/lang/String;I)V

    const-string p1, "ek-lan-host-ui"

    invoke-direct {v0, v1, p1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    const/4 p1, 0x1

    invoke-virtual {v0, p1}, Ljava/lang/Thread;->setDaemon(Z)V

    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    return-void
.end method


# virtual methods
.method synthetic lambda$promptJoinByIp$0$net-fdgames-ek-android-lan-LanLobbyActivity(Landroid/widget/EditText;Landroid/content/DialogInterface;I)V
    .registers 5

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->savePlayerName()V

    iget-object p3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;

    invoke-virtual {p3}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p3

    invoke-virtual {p3}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    const/16 v0, 0x7d7c

    invoke-direct {p0, p3, p1, v0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->joinHostAsync(Ljava/lang/String;Ljava/lang/String;I)V

    return-void
.end method

.method public onChatUpdated(Ljava/util/List;)V
    .registers 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_9
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1f

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_9

    :cond_1f
    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatView:Landroid/widget/TextView;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->chatScroll:Landroid/widget/ScrollView;

    new-instance v0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$7;

    invoke-direct {v0, p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity$7;-><init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    invoke-virtual {p1, v0}, Landroid/widget/ScrollView;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .registers 2

    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->get(Landroid/content/Context;)Lnet/fdgames/ek/android/lan/LanSessionManager;

    move-result-object p1

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->buildContentView()Landroid/view/View;

    move-result-object p1

    invoke-virtual {p0, p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->setContentView(Landroid/view/View;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->restorePlayerName()V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {p1, p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->setUiListener(Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->refreshUi()V

    return-void
.end method

.method public onDiscoveryUpdated(Ljava/util/List;)V
    .registers 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;",
            ">;)V"
        }
    .end annotation

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryResults:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryResults:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryAdapter:Landroid/widget/ArrayAdapter;

    invoke-virtual {v0}, Landroid/widget/ArrayAdapter;->clear()V

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_23

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryAdapter:Landroid/widget/ArrayAdapter;

    const-string v0, "lan_no_sessions"

    const-string v1, "No LAN session found."

    invoke-direct {p0, v0, v1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lanString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/ArrayAdapter;->add(Ljava/lang/Object;)V

    goto :goto_76

    :cond_23
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_27
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_76

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryAdapter:Landroid/widget/ArrayAdapter;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->sessionName:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, " - "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v4, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->address:Ljava/lang/String;

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v4, ":"

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget v4, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->port:I

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget v3, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->playerCount:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "/"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget v0, v0, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->maxPlayers:I

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/widget/ArrayAdapter;->add(Ljava/lang/Object;)V

    goto :goto_27

    :cond_76
    :goto_76
    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryAdapter:Landroid/widget/ArrayAdapter;

    invoke-virtual {p1}, Landroid/widget/ArrayAdapter;->notifyDataSetChanged()V

    return-void
.end method

.method protected onPause()V
    .registers 3

    invoke-super {p0}, Landroid/app/Activity;->onPause()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->isSessionRunning()Z

    move-result v0

    if-eqz v0, :cond_c

    goto :goto_f

    :cond_c
    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->releaseMulticastLock()V

    :goto_f
    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager;->setUiListener(Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;)V

    return-void
.end method

.method public onPlayersChanged(Ljava/util/List;)V
    .registers 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1a

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->isPortuguese()Z

    move-result p1

    if-eqz p1, :cond_14

    const-string p1, "Nenhum jogador conectado."

    goto :goto_16

    :cond_14
    const-string p1, "No players connected."

    :goto_16
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_3a

    :cond_1a
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_1e
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3a

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    const-string v2, " - "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_1e

    :cond_3a
    :goto_3a
    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playersView:Landroid/widget/TextView;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.method protected onResume()V
    .registers 2

    invoke-super {p0}, Landroid/app/Activity;->onResume()V

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    invoke-virtual {v0, p0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->setUiListener(Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;)V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->acquireMulticastLock()V

    invoke-direct {p0}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->refreshUi()V

    return-void
.end method

.method public onStateChanged(Ljava/lang/String;)V
    .registers 7

    const-string v0, ""

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->statusView:Landroid/widget/TextView;

    iget-object v2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagView:Landroid/widget/TextView;

    iget-object v3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->diagScroll:Landroid/widget/ScrollView;

    if-nez p1, :cond_13

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    if-eqz v2, :cond_41

    invoke-virtual {v2, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_41

    :cond_13
    const-string v4, "LAN DIAG "

    invoke-virtual {p1, v4}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v4

    if-ltz v4, :cond_35

    const/4 v0, 0x0

    invoke-virtual {p1, v0, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    invoke-virtual {p1, v4}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    if-eqz v2, :cond_49

    invoke-virtual {v2, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_49

    :cond_35
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    if-eqz v2, :cond_41

    invoke-virtual {v2, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    :cond_41
    :goto_41
    if-eqz v3, :cond_54

    const/16 p1, 0x8

    invoke-virtual {v3, p1}, Landroid/widget/ScrollView;->setVisibility(I)V

    goto :goto_54

    :cond_49
    :goto_49
    if-eqz v3, :cond_54

    const/4 p1, 0x0

    invoke-virtual {v3, p1}, Landroid/widget/ScrollView;->setVisibility(I)V

    const/16 p1, 0x82

    invoke-virtual {v3, p1}, Landroid/widget/ScrollView;->fullScroll(I)Z

    :cond_54
    :goto_54
    return-void
.end method

.method public onToast(Ljava/lang/String;)V
    .registers 3

    const/4 v0, 0x0

    invoke-static {p0, p1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    return-void
.end method
