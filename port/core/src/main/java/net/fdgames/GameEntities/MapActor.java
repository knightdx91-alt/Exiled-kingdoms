package net.fdgames.GameEntities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import m0.b;
import net.fdgames.GameWorld.GameData;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.AnimationLoader;
import net.fdgames.assets.AnimationSet;
import net.fdgames.assets.GameAssets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class MapActor extends MapSprite {
    protected float actionDuration;
    protected float actionStartTime;
    public ArrayList<String> animationSetName;
    public float speedX;
    public float speedY;
    public boolean stuck;
    private ActorState state = ActorState.IDLE;
    private boolean movementBlocked = false;
    public float pushmaxtime = 0.0f;
    public Facing facing = Facing.LD;
    public float stateRelativeTime = 0.0f;
    protected int timesStuck = 0;
    protected Coords destination = new Coords(-1, -1);
    private Rectangle projectCollisionRectangle = new Rectangle ();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum ActorState {
        IDLE, MOVING, ATTACKING, DEAD, ACTING, PUSHED, SKILL_WHIRLWIND, SKILL_CHARGE, DISABLED, PARALIZED, FIRING;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum Facing {
        U, RU, R, RD, D, LD, L, LU;
    }

    private boolean P(int i2, int i3) {
        b bVarP = b.P();
        int i4 = this.uniqueID;
        ActorState actorState = this.state;
        return bVarP.f(i2, i3, i4, (actorState == ActorState.PUSHED || actorState == ActorState.SKILL_CHARGE) ? false : true);
    }

    private void Y() {
        for (int i2 = 1; P(this.f3092x, this.f3093y) && i2 < 72; i2++) {
            if (!P(this.f3092x, this.f3093y - i2)) {
                this.f3093y -= i2;
                return;
            }
            if (!P(this.f3092x - i2, this.f3093y)) {
                this.f3092x -= i2;
                return;
            }
            if (!P(this.f3092x + i2, this.f3093y)) {
                this.f3092x += i2;
                return;
            }
            if (!P(this.f3092x, this.f3093y + i2)) {
                this.f3093y += i2;
                return;
            }
            if (!P(this.f3092x - i2, this.f3093y - i2)) {
                this.f3093y -= i2;
                this.f3092x -= i2;
                return;
            }
            if (!P(this.f3092x + i2, this.f3093y + i2)) {
                this.f3093y += i2;
                this.f3092x += i2;
                return;
            } else if (!P(this.f3092x + i2, this.f3093y - i2)) {
                this.f3093y -= i2;
                this.f3092x += i2;
                return;
            } else {
                if (!P(this.f3092x - i2, this.f3093y + i2)) {
                    this.f3093y += i2;
                    this.f3092x -= i2;
                    return;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x014c  */
    @Override // net.fdgames.GameEntities.MapSprite
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void M(float f2) {
        boolean z2;
        ActorState actorState;
        ActorState actorState2;
        w0(f2);
        ActorState actorState3 = this.state;
        ActorState actorState4 = ActorState.DEAD;
        ActorState actorState5 = ActorState.IDLE;
        ActorState actorState6 = ActorState.MOVING;
        if (actorState3 != actorState4 && actorState3 != ActorState.PARALIZED) {
            boolean z3 = this.movementBlocked;
            ActorState actorState7 = ActorState.SKILL_CHARGE;
            if (!z3 || actorState3 == actorState7) {
                u0(this.speedX, this.speedY);
                float f3 = this.speedX;
                ActorState actorState8 = ActorState.PUSHED;
                if (f3 != 0.0f) {
                    float f4 = f3 * f2;
                    float f5 = this.f3092x + f4;
                    int i2 = this.destination.f3287x;
                    if (i2 != -1 && Math.abs(f5 - i2) < Math.abs(f4)) {
                        f5 = this.destination.f3287x;
                    }
                    z2 = f5 != ((float) this.f3092x);
                    b bVarP = b.P();
                    int i3 = (int) f5;
                    int i4 = this.f3093y;
                    int i5 = this.uniqueID;
                    ActorState actorState9 = this.state;
                    if (bVarP.f(i3, i4, i5, (actorState9 == actorState8 || actorState9 == actorState7) ? false : true)) {
                        this.speedX = 0.0f;
                        if (this.state == actorState8) {
                            this.destination.f3287x = this.f3092x;
                        }
                    } else {
                        this.f3092x = Math.round(f5);
                    }
                } else {
                    z2 = false;
                }
                float f6 = this.speedY;
                if (f6 != 0.0f) {
                    float f7 = f6 * f2;
                    float f8 = this.f3093y + f7;
                    int i6 = this.destination.f3288y;
                    if (i6 != -1 && Math.abs(f8 - i6) < Math.abs(f7)) {
                        f8 = this.destination.f3288y;
                    }
                    z2 = z2 || f8 != ((float) this.f3093y);
                    b bVarP2 = b.P();
                    int i7 = this.f3092x;
                    int i8 = (int) f8;
                    int i9 = this.uniqueID;
                    ActorState actorState10 = this.state;
                    if (bVarP2.f(i7, i8, i9, (actorState10 == actorState8 || actorState10 == actorState7) ? false : true)) {
                        this.speedY = 0.0f;
                        if (this.state == actorState8) {
                            this.destination.f3288y = this.f3093y;
                        }
                    } else {
                        this.f3093y = Math.round(f8);
                    }
                }
                Y();
                if (!g0() && !i0() && (actorState2 = this.state) != actorState8 && actorState2 != actorState7) {
                    if (z2 || !(this.destination.f3287x == -1 || actorState2 == actorState6)) {
                        q0(actorState6);
                    } else if (actorState2 != actorState5) {
                        q0(actorState5);
                    }
                }
                if (z2 && this.state == ActorState.DISABLED) {
                    q0(actorState5);
                }
                Y();
                if (this.speedY == 0.0f && this.speedX == 0.0f) {
                    Coords coords = this.destination;
                    if ((coords.f3287x != -1 || coords.f3288y != -1) && this.state == actorState6) {
                        this.stuck = true;
                    }
                    actorState = this.state;
                    if (actorState != actorState8) {
                        if (actorState == actorState7) {
                        }
                        this.state = actorState5;
                        this.speedX = 0.0f;
                        this.speedY = 0.0f;
                        Coords coords2 = this.destination;
                        coords2.f3287x = -1;
                        coords2.f3288y = -1;
                    } else {
                        if (actorState == actorState7) {
                        }
                        this.state = actorState5;
                        this.speedX = 0.0f;
                        this.speedY = 0.0f;
                        Coords coords22 = this.destination;
                        coords22.f3287x = -1;
                        coords22.f3288y = -1;
                    }
                } else {
                    this.stuck = false;
                    this.timesStuck = 0;
                    actorState = this.state;
                    if ((actorState != actorState8 || actorState == actorState7) && (!z2 || this.stuck)) {
                        if (actorState == actorState7) {
                            this.movementBlocked = false;
                        }
                        this.state = actorState5;
                        this.speedX = 0.0f;
                        this.speedY = 0.0f;
                        Coords coords222 = this.destination;
                        coords222.f3287x = -1;
                        coords222.f3288y = -1;
                    }
                }
            }
        }
        if (this.uniqueID == 1 && this.speedX == 0.0f && this.speedY == 0.0f && this.state == actorState6) {
            this.state = actorState5;
            Coords coords3 = this.destination;
            coords3.f3287x = -1;
            coords3.f3288y = -1;
        }
    }

    public final void N(float f2) {
        if (this.movementBlocked && this.state != ActorState.SKILL_CHARGE) {
            this.speedX = 0.0f;
        } else {
            this.speedX = c0() * f2 * 125.0f;
        }
    }

    public final void O(float f2) {
        if (this.movementBlocked && this.state != ActorState.SKILL_CHARGE) {
            this.speedY = 0.0f;
        } else {
            this.speedY = c0() * f2 * 125.0f;
        }
    }

    public a<TextureRegion> Q() {
        AnimationSet animationSet;
        GameAssets.f3315a.clear();
        for (String str : this.animationSetName) {
            Array<TextureRegion> aVar = GameAssets.f3315a;
            int i2 = 0;
            while (true) {
                Array<AnimationSet> aVar2 = AnimationLoader.f3300a;
                if (i2 >= aVar2.f1750b) {
                    if (Gdx.files.internal("data/sprites/" + str + ".png").exists()) {
                        AnimationSet animationSet2 = new AnimationSet(str);
                        AnimationLoader.f3300a.a(animationSet2);
                        animationSet = animationSet2;
                    } else {
                        Array<AnimationSet> aVar3 = AnimationLoader.f3300a;
                        if (aVar3.f1750b > 0) {
                            animationSet = aVar3.get(0);
                        } else {
                            animationSet = new AnimationSet("composite/male_clothes");
                            AnimationLoader.f3300a.a(animationSet);
                        }
                    }
                } else {
                    if (aVar2.get(i2).name.equals(str)) {
                        animationSet = AnimationLoader.f3300a.get(i2);
                        break;
                    }
                    i2++;
                }
            }
            aVar.a((TextureRegion) animationSet.a(this.state, this.facing).getKeyFrame(V()));
        }
        return GameAssets.f3315a;
    }

    public final void R(Coords coords) {
        int i2 = coords.f3287x - this.f3092x;
        int i3 = coords.f3288y - this.f3093y;
        float fAbs = Math.abs(i3) + Math.abs(i2);
        u0((i2 / fAbs) * 125.0f, (i3 / fAbs) * 125.0f);
    }

    public Rectangle S(boolean z2) {
        if (this.state == ActorState.DEAD) {
            return null;
        }
        return T(this.f3092x, this.f3093y, z2);
    }

    public final Rectangle T(int i2, int i3, boolean z2) {
        if (z2) {
            this.projectCollisionRectangle.setPosition(i2 - (b0() / 2), i3 - (b0() / 2));
            this.projectCollisionRectangle.setSize(b0(), b0());
        } else {
            this.projectCollisionRectangle.setPosition(i2 - (Z() / 2), i3 - (Z() / 2));
            this.projectCollisionRectangle.setSize(Z(), Z());
        }
        return this.projectCollisionRectangle;
    }

    public void U(int i2) {
    }

    protected final float V() {
        return this.state == ActorState.SKILL_CHARGE ? this.stateRelativeTime * 3.0f : this.stateRelativeTime;
    }

    public final void W() {
        this.movementBlocked = true;
    }

    protected void X() {
        if (i0()) {
            return;
        }
        q0(ActorState.DEAD);
        this.movementBlocked = true;
    }

    public int Z() {
        return 12;
    }

    public final Coords a0() {
        return this.destination;
    }

    public int b0() {
        return 9;
    }

    public abstract float c0();

    public final ActorState d0() {
        return this.state;
    }

    public boolean e0() {
        return false;
    }

    protected boolean f0() {
        return false;
    }

    public final boolean g0() {
        ActorState actorState = this.state;
        return actorState == ActorState.ACTING || actorState == ActorState.ATTACKING || actorState == ActorState.FIRING || actorState == ActorState.SKILL_WHIRLWIND || actorState == ActorState.SKILL_CHARGE;
    }

    public boolean h0() {
        return false;
    }

    public final boolean i0() {
        ActorState actorState = this.state;
        return actorState == ActorState.DEAD || actorState == ActorState.DISABLED;
    }

    public final boolean j0() {
        ActorState actorState = this.state;
        return actorState == ActorState.PUSHED || actorState == ActorState.PARALIZED;
    }

    public boolean k0() {
        return false;
    }

    public final boolean l0() {
        return (g0() || i0() || j0()) ? false : true;
    }

    public boolean m0() {
        return !i0();
    }

    public final void n0(double d2, Coords coords) {
        if (!f0() && b.s(B(), coords) <= 192) {
            if (b.s(B(), coords) > 160) {
                d2 = Math.min(32.0d, d2);
            }
            float f2 = this.f3092x;
            float f3 = f2 - coords.f3287x;
            float f4 = this.f3093y - coords.f3288y;
            float fSqrt = (float) Math.sqrt((f4 * f4) + (f3 * f3));
            if (fSqrt != 0.0f) {
                f3 /= fSqrt;
                f4 /= fSqrt;
            }
            float f5 = (float) d2;
            float f6 = f4 * f5;
            int iO = (int) (this.f3092x + (f3 * f5));
            int iN = (int) (this.f3093y + f6);
            if (iO < 0) {
                iO = 0;
            }
            if (iN < 0) {
                iN = 0;
            }
            if (iO >= b.O()) {
                iO = b.O() - 1;
            }
            if (iN >= b.N()) {
                iN = b.N() - 1;
            }
            Coords coords2 = this.destination;
            coords2.f3287x = iO;
            coords2.f3288y = iN;
            q0(ActorState.PUSHED);
            this.pushmaxtime = GameData.v().u() + 0.35f;
            this.movementBlocked = false;
        }
    }

    public void o0(Coords coords) {
    }

    public final void p0(Coords coords) {
        if (!j0() || coords.f3287x == -1) {
            Coords coords2 = this.destination;
            coords2.f3287x = coords.f3287x;
            coords2.f3288y = coords.f3288y;
        }
    }

    public final void q0(ActorState actorState) {
        this.state = actorState;
        v0();
    }

    public void r0(Coords coords) {
    }

    public void s0(float f2) {
    }

    public final void t0() {
        this.movementBlocked = false;
    }

    public void u0(float f2, float f3) {
        if ((f2 == 0.0f && f3 == 0.0f) || j0() || i0() || this.state == ActorState.SKILL_CHARGE) {
            return;
        }
        if (f2 == 0.0f && f3 == 0.0f) {
            return;
        }
        Facing facing = this.facing;
        float f4 = f2 + f3;
        float f5 = f3 - f2;
        if (Math.abs(f4) <= 55.0f) {
            if (f5 < 0.0f) {
                facing = Facing.D;
            } else if (f5 > 0.0f) {
                facing = Facing.U;
            }
        } else if (f4 > 0.0f) {
            if (Math.abs(f5) <= 55.0f) {
                facing = Facing.R;
            } else if (f5 < 0.0f) {
                facing = Facing.RD;
            } else if (f5 > 0.0f) {
                facing = Facing.RU;
            }
        } else if (f4 < 0.0f) {
            if (Math.abs(f5) <= 55.0f) {
                facing = Facing.L;
            } else if (f5 < 0.0f) {
                facing = Facing.LD;
            } else if (f5 > 0.0f) {
                facing = Facing.LU;
            }
        }
        this.facing = facing;
    }

    public abstract void v0();

    protected void w0(float f2) {
        ActorState actorState = this.state;
        if (actorState == ActorState.IDLE || actorState == ActorState.PUSHED) {
            this.stateRelativeTime = 0.0f;
        } else {
            this.stateRelativeTime += f2;
        }
    }
}
