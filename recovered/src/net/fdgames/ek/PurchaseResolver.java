package net.fdgames.ek;

import d0.g;
import e0.f;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class PurchaseResolver {
    protected g mgr;

    public final g a() {
        return this.mgr;
    }

    public final void b(String str) {
        g gVar = this.mgr;
        if (gVar == null) {
            System.out.println("ek gdx-pay: requestPurchase(): purchaseManager == null");
        } else if (((f) gVar).t()) {
            ((f) this.mgr).v(str);
        } else {
            System.out.println("ek gdx-pay: requestPurchase(): purchaseManager NOT installed");
        }
    }

    public final void c() {
        g gVar = this.mgr;
        if (gVar == null) {
            System.out.println("ek gdx-pay: requestPurchaseRestore(): purchaseManager == null");
        } else if (!((f) gVar).t()) {
            System.out.println("ek gdx-pay: requestPurchaseRestore(): purchaseManager NOT installed");
        } else {
            System.out.println("ek gdx-pay: attempting to restore purchase ****************************************************************************************************************************");
            ((f) this.mgr).w();
        }
    }
}
