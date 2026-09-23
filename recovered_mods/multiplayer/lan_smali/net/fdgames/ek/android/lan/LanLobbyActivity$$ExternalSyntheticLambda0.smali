.class public final synthetic Lnet/fdgames/ek/android/lan/LanLobbyActivity$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "D8$$SyntheticClass"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# instance fields
.field public final synthetic f$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

.field public final synthetic f$1:Landroid/widget/EditText;


# direct methods
.method public synthetic constructor <init>(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Landroid/widget/EditText;)V
    .registers 3

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$$ExternalSyntheticLambda0;->f$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$$ExternalSyntheticLambda0;->f$1:Landroid/widget/EditText;

    return-void
.end method


# virtual methods
.method public final onClick(Landroid/content/DialogInterface;I)V
    .registers 5

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$$ExternalSyntheticLambda0;->f$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$$ExternalSyntheticLambda0;->f$1:Landroid/widget/EditText;

    invoke-virtual {v0, v1, p1, p2}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->lambda$promptJoinByIp$0$net-fdgames-ek-android-lan-LanLobbyActivity(Landroid/widget/EditText;Landroid/content/DialogInterface;I)V

    return-void
.end method
