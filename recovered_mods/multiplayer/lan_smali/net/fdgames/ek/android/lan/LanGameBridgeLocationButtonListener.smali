.class final Lnet/fdgames/ek/android/lan/LanGameBridgeLocationButtonListener;
.super Ljava/lang/Object;
.source "LanGameBridge.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field private final chatView:Landroid/widget/TextView;

.field private final sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Landroid/widget/TextView;)V
    .registers 3

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeLocationButtonListener;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeLocationButtonListener;->chatView:Landroid/widget/TextView;

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 8

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeLocationButtonListener;->sessionManager:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanGameBridgeLocationButtonListener;->chatView:Landroid/widget/TextView;

    if-eqz v0, :cond_d1

    if-eqz v1, :cond_d1

    const-string v4, "\n[Localizacao]\n"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    invoke-virtual {v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->getPeerStatesSnapshot()Ljava/util/List;

    move-result-object v2

    if-eqz v2, :cond_cc

    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_cc

    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_1d
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_d1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;

    const-string v4, "[Loc] "

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    iget-object v4, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->playerName:Ljava/lang/String;

    if-nez v4, :cond_34

    const-string v4, "Jogador"

    :cond_34
    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    iget-object v4, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->characterName:Ljava/lang/String;

    if-eqz v4, :cond_64

    const-string v5, " ("

    invoke-virtual {v1, v5}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    iget-object v4, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->className:Ljava/lang/String;

    if-eqz v4, :cond_4f

    const-string v5, " - "

    invoke-virtual {v1, v5}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    :cond_4f
    iget v5, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->level:I

    if-eqz v5, :cond_5f

    const-string v4, " Nv."

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    invoke-static {v5}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    :cond_5f
    const-string v4, ")"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    :cond_64
    const-string v4, "\n"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    iget-object v4, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->currentMapName:Ljava/lang/String;

    if-eqz v4, :cond_7a

    const-string v5, "  Mapa: "

    invoke-virtual {v1, v5}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    const-string v4, "\n"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    :cond_7a
    iget-object v4, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->actorStateName:Ljava/lang/String;

    if-eqz v4, :cond_ad

    const-string v5, "  Estado: "

    invoke-virtual {v1, v5}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    const-string v5, "COMBAT"

    invoke-virtual {v4, v5}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_8e

    const-string v4, "Em Combate"

    goto :goto_90

    :cond_8e
    const-string v4, "Livre"

    :goto_90
    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    iget v5, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->missingHp:I

    if-lez v5, :cond_a8

    const-string v4, " | -"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    invoke-static {v5}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    const-string v4, " HP"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    :cond_a8
    const-string v4, "\n"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    :cond_ad
    iget-object v4, v3, Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;->companionName:Ljava/lang/String;

    if-eqz v4, :cond_c4

    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_c4

    const-string v5, "  Companheiro: "

    invoke-virtual {v1, v5}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    const-string v4, "\n"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    :cond_c4
    const-string v4, "---\n"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    const/4 v5, 0x0

    goto/16 :goto_1d

    :cond_cc
    const-string v4, "Nenhum jogador conectado.\n"

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->append(Ljava/lang/CharSequence;)V

    :cond_d1
    return-void
.end method
