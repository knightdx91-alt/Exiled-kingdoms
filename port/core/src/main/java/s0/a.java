package s0;

import e0.f;
import java.io.PrintStream;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.PurchaseResolver;
import net.fdgames.ek.android.MainActivity;

/* JADX INFO: compiled from: AndroidResolver.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a extends PurchaseResolver {
    public a(ExiledKingdoms exiledKingdoms, MainActivity mainActivity) {
        exiledKingdoms.purchaseManagerConfig.b();
        PrintStream printStream = System.out;
        printStream.println("ek  --  initializing android purchase resolver    ***************************************************************************************************************************");
        this.mgr = new f(mainActivity);
        printStream.println("ek gdx-pay: assigned manager: " + this.mgr.toString());
    }
}
