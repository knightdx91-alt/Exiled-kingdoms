package net.fdgames.assets;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.c0;
import net.fdgames.GameEntities.MapObject;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class MapParticleEffectPool extends c0<MapPooledEffect> {
    private final ParticleEffect effect;
    int tmpY;
    int tmpx;

    public class MapPooledEffect extends ParticleEffect {
        public boolean diurne;
        public int isoX;
        public int isoY;
        public boolean nocturne;
        public MapObject owner;

        public MapPooledEffect(ParticleEffect particleEffect) {
            super(particleEffect);
        }

        public final void a(int i2, int i3) {
            MapParticleEffectPool mapParticleEffectPool = MapParticleEffectPool.this;
            int i4 = (i2 + i3) - 64;
            mapParticleEffectPool.tmpx = i4;
            int i5 = (i3 - i2) / 2;
            mapParticleEffectPool.tmpY = i5;
            super.setPosition(i4, i5);
        }

        public final void free() {
            MapParticleEffectPool.this.free(this);
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEffect
        public final void setPosition(float f2, float f3) {
            super.setPosition(f2, f3);
            int i2 = ((int) ((f2 / 2.0f) - f3)) + 32;
            this.isoX = i2;
            this.isoY = ((int) (f3 + r2)) - 64;
            this.isoX = Math.max(0, i2);
            this.isoY = Math.max(0, this.isoY);
        }
    }

    public MapParticleEffectPool(ParticleEffect particleEffect, int i2, int i3) {
        super(i2, i3);
        this.effect = particleEffect;
    }

    @Override // com.badlogic.gdx.utils.c0
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final MapPooledEffect obtain() {
        MapPooledEffect mapPooledEffect = (MapPooledEffect) super.obtain();
        mapPooledEffect.reset();
        return mapPooledEffect;
    }

    @Override // com.badlogic.gdx.utils.c0
    protected final MapPooledEffect newObject() {
        return new MapPooledEffect(this.effect);
    }
}
