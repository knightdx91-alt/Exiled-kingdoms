package o0;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.fdgames.GameWorld.GameData;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: CharacterWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class e extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3468a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ f f3469b;

    public /* synthetic */ e(f fVar, int i2) {
        this.f3468a = i2;
        this.f3469b = fVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x016d  */
    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        switch (this.f3468a) {
            case 0:
                m.g().getClass();
                if (m.h()) {
                    return false;
                }
                x xVarB = x.b();
                f fVar = this.f3469b;
                xVarB.d(fVar.f3498o.sheet);
                if (ExiledKingdoms.f3378h) {
                    x.b().setX((fVar.getWidth() / 3.0f) + (f.N0 * 35.0f) + fVar.getX());
                }
                return true;
            case 1:
                f fVar2 = this.f3469b;
                if (fVar2.f3473b0.isDisabled()) {
                    return false;
                }
                m.g().getClass();
                if (m.h()) {
                    return false;
                }
                fVar2.V(0, 0);
                m.g().j(fVar2);
                if (ExiledKingdoms.f3378h) {
                    m.g().setY(fVar2.getY());
                }
                return true;
            case 2:
                this.f3469b.L();
                return true;
            case 3:
                f fVar3 = this.f3469b;
                boolean zA = f.a(fVar3);
                int i4 = 0;
                while (true) {
                    if (i4 < 20) {
                        if (inputEvent.getTarget() != fVar3.f3503q0[i4]) {
                            i4++;
                        } else if (zA) {
                            fVar3.B();
                        } else {
                            m.g().getClass();
                            if (m.h()) {
                                m.g().d(1, i4);
                            } else {
                                if (!zA && GameData.v().backpack.h(i4) == 0 && f.V0 == 1 && fVar3.R() != 0) {
                                    GameData.v().backpack.r(f.W0, i4);
                                }
                                fVar3.V(1, i4);
                                fVar3.X();
                            }
                        }
                    }
                }
                if (f.L0 == 1) {
                    int i5 = 0;
                    while (true) {
                        if (i5 < 20) {
                            if (inputEvent.getTarget() != fVar3.f3506s0[i5]) {
                                i5++;
                            } else if (zA) {
                                fVar3.B();
                            } else {
                                if (f.K0.g() && f.K0.j().h(i5) == 0 && f.V0 == 4 && fVar3.R() != 0) {
                                    f.K0.j().r(f.W0, i5);
                                }
                                fVar3.V(4, i5);
                                fVar3.X();
                            }
                        }
                    }
                    if (zA) {
                        m.g().getClass();
                        if (!m.h()) {
                            if (inputEvent.getTarget() == fVar3.f3479e0) {
                                fVar3.V(2, 0);
                            } else if (inputEvent.getTarget() == fVar3.f3481f0) {
                                fVar3.V(2, 1);
                            } else if (inputEvent.getTarget() == fVar3.f3483g0) {
                                fVar3.V(2, 2);
                            } else if (inputEvent.getTarget() == fVar3.f3485h0) {
                                fVar3.V(2, 3);
                            } else if (inputEvent.getTarget() == fVar3.f3487i0) {
                                fVar3.V(2, 4);
                            } else if (inputEvent.getTarget() == fVar3.f3489j0) {
                                fVar3.V(2, 5);
                            } else if (inputEvent.getTarget() == fVar3.f3491k0) {
                                fVar3.V(2, 10);
                            } else if (inputEvent.getTarget() == fVar3.f3493l0) {
                                fVar3.V(2, 11);
                            } else if (inputEvent.getTarget() == fVar3.f3495m0) {
                                fVar3.V(2, 6);
                            } else if (inputEvent.getTarget() == fVar3.f3501p0) {
                                fVar3.V(2, 7);
                            } else if (inputEvent.getTarget() == fVar3.f3497n0) {
                                fVar3.V(2, 8);
                            } else if (inputEvent.getTarget() == fVar3.f3499o0) {
                                fVar3.V(2, 9);
                            }
                            fVar3.X();
                        } else if (inputEvent.getTarget() == fVar3.f3479e0) {
                            m.g().d(2, 0);
                        } else if (inputEvent.getTarget() == fVar3.f3481f0) {
                            m.g().d(2, 1);
                        } else if (inputEvent.getTarget() == fVar3.f3483g0) {
                            m.g().d(2, 2);
                        } else if (inputEvent.getTarget() == fVar3.f3485h0) {
                            m.g().d(2, 3);
                        } else if (inputEvent.getTarget() == fVar3.f3487i0) {
                            m.g().d(2, 4);
                        } else if (inputEvent.getTarget() == fVar3.f3489j0) {
                            m.g().d(2, 5);
                        } else if (inputEvent.getTarget() == fVar3.f3491k0) {
                            m.g().d(2, 10);
                        } else if (inputEvent.getTarget() == fVar3.f3493l0) {
                            m.g().d(2, 11);
                        } else if (inputEvent.getTarget() == fVar3.f3495m0) {
                            m.g().d(2, 6);
                        } else if (inputEvent.getTarget() == fVar3.f3501p0) {
                            m.g().d(2, 7);
                        } else if (inputEvent.getTarget() == fVar3.f3497n0) {
                            m.g().d(2, 8);
                        } else if (inputEvent.getTarget() == fVar3.f3499o0) {
                            m.g().d(2, 9);
                        }
                    } else {
                        fVar3.B();
                    }
                } else if (zA) {
                }
                return true;
            case 4:
                m.g().getClass();
                if (m.h()) {
                    return false;
                }
                this.f3469b.E();
                return true;
            case 5:
                this.f3469b.T(1, GameData.v().z("bag_of_holding"));
                GameAssets.o("item");
                return false;
            case 6:
                m.g().getClass();
                if (!m.h()) {
                    f fVar4 = this.f3469b;
                    fVar4.X();
                    if (fVar4.f3498o.q() == 1) {
                        fVar4.U(0, null, GameData.v().party.f());
                    } else {
                        fVar4.U(0, null, GameData.v().player);
                    }
                }
                return false;
            case 7:
                this.f3469b.B();
                return true;
            default:
                m.g().getClass();
                if (m.h()) {
                    return false;
                }
                f fVar5 = this.f3469b;
                if (!fVar5.z0.isDisabled()) {
                    n0.z.v().X.p(fVar5.f3498o.sheet, n0.z.v().a());
                }
                return true;
        }
    }
}
