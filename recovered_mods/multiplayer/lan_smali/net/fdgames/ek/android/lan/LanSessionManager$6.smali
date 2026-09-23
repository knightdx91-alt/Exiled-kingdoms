.class Lnet/fdgames/ek/android/lan/LanSessionManager$6;
.super Ljava/lang/Object;
.source "LanSessionManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lnet/fdgames/ek/android/lan/LanSessionManager;->toast(Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

.field final synthetic val$listener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

.field final synthetic val$message:Ljava/lang/String;


# direct methods
.method constructor <init>(Lnet/fdgames/ek/android/lan/LanSessionManager;Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;Ljava/lang/String;)V
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    iput-object p1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$6;->this$0:Lnet/fdgames/ek/android/lan/LanSessionManager;

    iput-object p2, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$6;->val$listener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    iput-object p3, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$6;->val$message:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    iget-object v0, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$6;->val$listener:Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;

    iget-object v1, p0, Lnet/fdgames/ek/android/lan/LanSessionManager$6;->val$message:Ljava/lang/String;

    invoke-interface {v0, v1}, Lnet/fdgames/ek/android/lan/LanSessionManager$UiListener;->onToast(Ljava/lang/String;)V

    return-void
.end method
