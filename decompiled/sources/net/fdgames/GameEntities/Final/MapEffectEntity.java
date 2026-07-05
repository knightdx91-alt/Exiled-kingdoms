package net.fdgames.GameEntities.Final;

import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import m0.b;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.Helpers.MapEffect;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.MapEffectData;
import net.fdgames.Rules.Rules;
import net.fdgames.TiledMap.Objects.Coords;
import u.h;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MapEffectEntity extends MapSprite {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Color f3005a = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    float activationTime;
    public boolean active;
    private boolean addedlight;
    float alpha;
    float baseDelay;
    private int caster_id;
    float deactivationTime;
    private boolean debug;
    private float destructionTime;
    float fadeInStartTime;
    float fadeOutEndTime;
    float fadein_duration;
    float fadeout_duration;
    public boolean fullyActive;
    String id;
    float lastbounce;
    private MapEffect mapeffect;
    private boolean mustaddlight;
    float nextDirectionChange;
    int originX;
    int originY;
    float speedX;
    float speedY;

    public MapEffectEntity() {
        this.addedlight = false;
        this.mustaddlight = false;
        this.debug = false;
    }

    private void P() {
        int iB;
        int iB2;
        if (this.debug) {
            System.out.println("mapEffectEntity newTarget");
        }
        int i2 = 0;
        while (true) {
            iB = FDUtils.b(-120, 120) + this.originX;
            iB2 = FDUtils.b(-120, 120) + this.originY;
            i2++;
            if (i2 >= 20 || (iB >= 0 && iB <= b.O() && iB2 >= 0 && iB2 <= b.N() && !b.P().i(iB, iB2, this.mapeffect.size) && !b.P().g(iB, iB2, this.mapeffect.size))) {
                break;
            }
        }
        float f2 = iB2;
        q qVar = new q();
        qVar.f91a = iB;
        qVar.f92b = f2;
        qVar.c(new Coords(this.f3092x, this.f3093y).a());
        qVar.a();
        this.speedX = qVar.f91a * 145.0f;
        this.speedY = qVar.f92b * 145.0f;
        this.nextDirectionChange = ((float) ((Math.random() * ((double) 0.6f)) + ((double) 0.0f))) + GameData.v().u() + 5.0f;
    }

    private void T() {
        MapEffectData.MapEffectDataLine mapEffectDataLineA = Rules.f3257k.a(this.id);
        if (mapEffectDataLineA.duration > 0.0f) {
            float fU = GameData.v().u() + this.baseDelay;
            this.fadeInStartTime = fU;
            float f2 = mapEffectDataLineA.fadeInDuration;
            float f3 = fU + f2;
            this.activationTime = f3;
            float f4 = f3 + mapEffectDataLineA.duration;
            this.deactivationTime = f4;
            float f5 = mapEffectDataLineA.fadeOutDuration;
            this.fadeOutEndTime = f4 + f5;
            this.fadein_duration = f2;
            this.fadeout_duration = f5;
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return null;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void F(SpriteBatch spriteBatch) {
        MapEffect mapEffect;
        if (!this.active || (mapEffect = this.mapeffect) == null) {
            return;
        }
        if (this.alpha >= 1.0f) {
            spriteBatch.draw(mapEffect.c(), mapEffect.a() - 38, mapEffect.b() - 8);
            return;
        }
        Color color = f3005a;
        color.set(spriteBatch.getColor());
        spriteBatch.setColor(1.0f, 1.0f, 1.0f, this.alpha);
        MapEffect mapEffect2 = this.mapeffect;
        float fMax = Math.max(this.alpha, 0.5f);
        float f2 = fMax * 140.0f;
        spriteBatch.draw(mapEffect2.c(), mapEffect2.a() - (38.0f * fMax), mapEffect2.b() - (8.0f * fMax), f2, f2);
        spriteBatch.setColor(color);
    }

    @Override // net.fdgames.GameEntities.MapSprite
    protected final int G() {
        return 70;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
        if (!this.active || this.mapeffect == null) {
            return;
        }
        if (this.destructionTime > 0.0f && GameData.v().u() > this.destructionTime) {
            this.destroy = true;
            this.active = false;
        }
        if (this.nextDirectionChange > 0.0f) {
            Coords coordsB = B();
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (b.s(coordsB, GameData.v().player.B()) <= 500) {
                if (GameData.v().u() > this.nextDirectionChange) {
                    P();
                }
                if (this.lastbounce + 0.5f < GameData.v().u()) {
                    if (b.P().i((int) ((this.speedX * f2) + this.f3092x), this.f3093y, 5)) {
                        this.speedX = -this.speedX;
                        this.lastbounce = GameData.v().u();
                        if (this.debug) {
                            System.out.println("mapEffectEntity bounced on X axis ");
                        }
                    }
                    if (b.P().i(this.f3092x, (int) ((this.speedY * f2) + this.f3093y), 5)) {
                        this.speedY = -this.speedY;
                        this.lastbounce = GameData.v().u();
                        if (this.debug) {
                            System.out.println("mapEffectEntity bounced on Y axis ");
                        }
                    }
                }
                float f3 = this.speedX;
                if (f3 != 0.0f) {
                    this.f3092x = Math.round(this.f3092x + (f3 * f2));
                }
                float f4 = this.speedY;
                if (f4 != 0.0f) {
                    this.f3093y = Math.round(this.f3093y + (f4 * f2));
                }
                this.mapeffect.e(this.f3092x, this.f3093y);
            }
        }
        if (!this.addedlight && this.mustaddlight) {
            k0.a.l().e(0.0f, q(), 90, "torch");
            this.addedlight = true;
        }
        if (this.fadein_duration == 0.0f || this.fadeout_duration == 0.0f) {
            this.mapeffect.fullyActive = true;
        } else {
            float fU = GameData.v().u();
            float f5 = this.fadeInStartTime;
            if (fU < f5) {
                this.alpha = 0.0f;
                this.mapeffect.fullyActive = false;
            } else if (fU > f5 && fU < this.activationTime) {
                this.alpha = (fU - f5) / this.fadein_duration;
                this.mapeffect.fullyActive = false;
            } else if (fU < this.activationTime || fU >= this.deactivationTime) {
                float f6 = this.deactivationTime;
                if (fU > f6 && fU < this.fadeOutEndTime) {
                    this.alpha = 1.0f - ((fU - f6) / this.fadeout_duration);
                    this.mapeffect.fullyActive = false;
                } else if (fU >= this.fadeOutEndTime) {
                    this.alpha = 0.0f;
                    this.active = false;
                    this.mapeffect.fullyActive = false;
                    if (this.tag.equals("")) {
                        this.destroy = true;
                    }
                }
            } else {
                this.alpha = 1.0f;
                this.mapeffect.fullyActive = true;
            }
        }
        this.mapeffect.g(f2, GameData.v().u());
        MapEffect mapEffect = this.mapeffect;
        if (mapEffect.tag_id == null) {
            mapEffect.tag_id = this.id;
        }
    }

    public final void N() {
        this.active = true;
        if (this.mapeffect.duration > 0.0f) {
            T();
        }
        V();
    }

    public final void O() {
        this.active = false;
        V();
    }

    public final void Q() {
        this.addedlight = false;
    }

    public final void R() {
        if (this.id.contains("glowing")) {
            this.f3092x = this.originX;
            this.f3093y = this.originY;
            P();
        }
    }

    public final void S(float f2) {
        this.destructionTime = GameData.v().u() + f2;
    }

    public final void U(int i2, int i3) {
        if (this.debug) {
            System.out.println("mapEffectEntity setTarget: " + i2 + "," + i3);
        }
        q qVar = new q();
        qVar.f91a = i2;
        qVar.f92b = i3;
        qVar.c(new Coords(this.f3092x, this.f3093y).a());
        qVar.a();
        this.speedX = qVar.f91a * 145.0f;
        this.speedY = qVar.f92b * 145.0f;
        this.nextDirectionChange = GameData.v().u() + 1.3f;
    }

    public final void V() {
        if (this.active) {
            this.mapeffect.d();
        } else {
            this.mapeffect.f();
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        MapEffect mapEffect = this.mapeffect;
        return mapEffect == null ? "" : mapEffect.name;
    }

    @Override // net.fdgames.GameEntities.MapObject, net.fdgames.GameEntities.GameObject
    public final void v(String str, int i2, String str2, DamageData damageData) {
    }

    public MapEffectEntity(float f2, int i2, int i3, int i4, String str) {
        this.addedlight = false;
        this.mustaddlight = false;
        this.debug = false;
        this.id = str;
        this.caster_id = i4;
        this.f3092x = i2;
        this.f3093y = i3;
        this.tag = "";
        this.active = true;
        this.alpha = 1.0f;
        this.baseDelay = f2;
        MapEffect mapEffect = new MapEffect(str, i2, i3, i4, false);
        this.mapeffect = mapEffect;
        boolean z2 = this.active;
        mapEffect.fullyActive = z2;
        mapEffect.tag_id = this.id;
        if (z2) {
            T();
        }
        V();
    }

    public MapEffectEntity(h hVar) {
        this.addedlight = false;
        this.mustaddlight = false;
        this.debug = false;
        this.caster_id = 0;
        int i2 = (int) Float.parseFloat(hVar.b("x").toString());
        int i3 = (int) Float.parseFloat(hVar.b("y").toString());
        this.f3092x = i2 - 24;
        this.f3093y = i3 + 24;
        if (hVar.a("tag")) {
            this.tag = hVar.b("tag").toString();
        }
        this.active = true;
        if (hVar.a("inactive")) {
            this.active = false;
        }
        this.alpha = 1.0f;
        String string = hVar.b("id").toString();
        this.id = string;
        MapEffect mapEffect = new MapEffect(string, this.f3092x, this.f3093y, q(), false);
        this.mapeffect = mapEffect;
        boolean z2 = this.active;
        mapEffect.fullyActive = z2;
        mapEffect.tag_id = this.id;
        if (z2 && mapEffect.duration > 0.0f) {
            T();
        }
        if (this.id.contains("glowing")) {
            this.mustaddlight = true;
            this.originX = this.f3092x;
            this.originY = this.f3093y;
            P();
        }
        if (this.id.contains("blizzard")) {
            this.originX = this.f3092x;
            this.originY = this.f3093y;
            P();
        }
        V();
    }
}
