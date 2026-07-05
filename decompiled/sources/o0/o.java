package o0;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Skill;

/* JADX INFO: compiled from: SkillDetailTable.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class o extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Skill f3621b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ n0.t f3622c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ p f3623d;

    /* JADX INFO: compiled from: SkillDetailTable.java */
    final class a extends h {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            Integer num = (Integer) obj;
            if (num.intValue() > -2) {
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                Player player = GameData.v().player;
                o oVar = o.this;
                player.X1(num.intValue(), oVar.f3620a);
                int iM1 = GameData.v().player.M1(oVar.f3621b.id);
                String strB = GameString.b("SLOT_NONE", false);
                if (iM1 >= 0) {
                    strB = "" + (iM1 + 1);
                }
                n0.t tVar = oVar.f3622c;
                tVar.setText("Slot: " + strB);
                tVar.setVisible(true);
                n0.z.v().Y();
            }
        }
    }

    o(p pVar, String str, Skill skill, n0.t tVar) {
        this.f3623d = pVar;
        this.f3620a = str;
        this.f3621b = skill;
        this.f3622c = tVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        a aVar = new a();
        p pVar = this.f3623d;
        pVar.f3629c = aVar;
        pVar.f3629c.show(n0.z.v().a());
        return true;
    }
}
