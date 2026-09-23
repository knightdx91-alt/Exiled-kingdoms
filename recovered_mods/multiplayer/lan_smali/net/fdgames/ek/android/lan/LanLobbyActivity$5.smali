.class Lnet/fdgames/ek/android/lan/LanLobbyActivity$5;
.super Ljava/lang/Object;
.source "LanLobbyActivity.java"

# interfaces
.implements Landroid/widget/AdapterView$OnItemClickListener;


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

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$5;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
    .registers 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/widget/AdapterView<",
            "*>;",
            "Landroid/view/View;",
            "IJ)V"
        }
    .end annotation

    if-ltz p3, :cond_3a

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$5;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryResults:Ljava/util/List;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$400(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Ljava/util/List;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result p1

    if-ge p3, p1, :cond_3a

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$5;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # invokes: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->savePlayerName()V
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$000(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)V

    iget-object p1, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$5;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->discoveryResults:Ljava/util/List;
    invoke-static {p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$400(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Ljava/util/List;

    move-result-object p1

    invoke-interface {p1, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;

    iget-object p2, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$5;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    iget-object p3, p0, Lnet/fdgames/ek/android/lan/LanLobbyActivity$5;->this$0:Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    # getter for: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->playerNameInput:Landroid/widget/EditText;
    invoke-static {p3}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$100(Lnet/fdgames/ek/android/lan/LanLobbyActivity;)Landroid/widget/EditText;

    move-result-object p3

    invoke-virtual {p3}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p3

    invoke-virtual {p3}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p3

    iget-object p4, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->address:Ljava/lang/String;

    iget p1, p1, Lnet/fdgames/ek/android/lan/LanSessionManager$DiscoveryResult;->port:I

    # invokes: Lnet/fdgames/ek/android/lan/LanLobbyActivity;->joinHostAsync(Ljava/lang/String;Ljava/lang/String;I)V
    invoke-static {p2, p3, p4, p1}, Lnet/fdgames/ek/android/lan/LanLobbyActivity;->access$800(Lnet/fdgames/ek/android/lan/LanLobbyActivity;Ljava/lang/String;Ljava/lang/String;I)V

    :cond_3a
    return-void
.end method
