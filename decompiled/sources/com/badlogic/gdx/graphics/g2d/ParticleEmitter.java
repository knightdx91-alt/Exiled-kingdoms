package com.badlogic.gdx.graphics.g2d;

import a0.j;
import a0.p;
import b0.a;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleEmitter {
    private static final int UPDATE_ANGLE = 2;
    private static final int UPDATE_GRAVITY = 32;
    private static final int UPDATE_ROTATION = 4;
    private static final int UPDATE_SCALE = 1;
    private static final int UPDATE_SPRITE = 128;
    private static final int UPDATE_TINT = 64;
    private static final int UPDATE_VELOCITY = 8;
    private static final int UPDATE_WIND = 16;
    private float accumulator;
    private boolean[] active;
    private int activeCount;
    private boolean additive;
    private boolean aligned;
    private boolean allowCompletion;
    private ScaledNumericValue angleValue;
    private boolean attached;
    private boolean behind;
    private a bounds;
    boolean cleansUpBlendFunction;
    private boolean continuous;
    private float delay;
    private float delayTimer;
    private RangedNumericValue delayValue;
    public float duration;
    public float durationTimer;
    private RangedNumericValue durationValue;
    private int emission;
    private int emissionDelta;
    private int emissionDiff;
    private ScaledNumericValue emissionValue;
    private boolean firstUpdate;
    private boolean flipX;
    private boolean flipY;
    private ScaledNumericValue gravityValue;
    private com.badlogic.gdx.utils.a<String> imagePaths;
    private int life;
    private int lifeDiff;
    private int lifeOffset;
    private int lifeOffsetDiff;
    private IndependentScaledNumericValue lifeOffsetValue;
    private IndependentScaledNumericValue lifeValue;
    private int maxParticleCount;
    private int minParticleCount;
    private RangedNumericValue[] motionValues;
    private String name;
    private Particle[] particles;
    private boolean premultipliedAlpha;
    private ScaledNumericValue rotationValue;
    private float spawnHeight;
    private float spawnHeightDiff;
    private ScaledNumericValue spawnHeightValue;
    private SpawnShapeValue spawnShapeValue;
    private float spawnWidth;
    private float spawnWidthDiff;
    private ScaledNumericValue spawnWidthValue;
    private SpriteMode spriteMode;
    private com.badlogic.gdx.utils.a<Sprite> sprites;
    private GradientColorValue tintValue;
    private ScaledNumericValue transparencyValue;
    private int updateFlags;
    private ScaledNumericValue velocityValue;
    private ScaledNumericValue windValue;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f1687x;
    private RangedNumericValue xOffsetValue;
    private ScaledNumericValue xScaleValue;
    private RangedNumericValue[] xSizeValues;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private float f1688y;
    private RangedNumericValue yOffsetValue;
    private ScaledNumericValue yScaleValue;
    private RangedNumericValue[] ySizeValues;

    /* JADX INFO: renamed from: com.badlogic.gdx.graphics.g2d.ParticleEmitter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpawnEllipseSide;
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpawnShape;
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpriteMode;

        static {
            int[] iArr = new int[SpawnShape.values().length];
            $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpawnShape = iArr;
            try {
                iArr[SpawnShape.square.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpawnShape[SpawnShape.ellipse.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpawnShape[SpawnShape.line.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SpawnEllipseSide.values().length];
            $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpawnEllipseSide = iArr2;
            try {
                iArr2[SpawnEllipseSide.top.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpawnEllipseSide[SpawnEllipseSide.bottom.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[SpriteMode.values().length];
            $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpriteMode = iArr3;
            try {
                iArr3[SpriteMode.single.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpriteMode[SpriteMode.animated.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpriteMode[SpriteMode.random.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static class Particle extends Sprite {
        protected float angle;
        protected float angleCos;
        protected float angleDiff;
        protected float angleSin;
        protected int currentLife;
        protected int frame;
        protected float gravity;
        protected float gravityDiff;
        protected int life;
        protected float rotation;
        protected float rotationDiff;
        protected float[] tint;
        protected float transparency;
        protected float transparencyDiff;
        protected float velocity;
        protected float velocityDiff;
        protected float wind;
        protected float windDiff;
        protected float xScale;
        protected float xScaleDiff;
        protected float yScale;
        protected float yScaleDiff;

        public Particle(Sprite sprite) {
            super(sprite);
        }
    }

    public enum SpawnEllipseSide {
        both,
        top,
        bottom
    }

    public enum SpawnShape {
        point,
        line,
        square,
        ellipse
    }

    public enum SpriteMode {
        single,
        random,
        animated
    }

    public ParticleEmitter() {
        this.delayValue = new RangedNumericValue();
        this.lifeOffsetValue = new IndependentScaledNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeValue = new IndependentScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.xScaleValue = new ScaledNumericValue();
        this.yScaleValue = new ScaledNumericValue();
        this.rotationValue = new ScaledNumericValue();
        this.velocityValue = new ScaledNumericValue();
        this.angleValue = new ScaledNumericValue();
        this.windValue = new ScaledNumericValue();
        this.gravityValue = new ScaledNumericValue();
        this.transparencyValue = new ScaledNumericValue();
        this.tintValue = new GradientColorValue();
        this.xOffsetValue = new ScaledNumericValue();
        this.yOffsetValue = new ScaledNumericValue();
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnShapeValue = new SpawnShapeValue();
        this.spriteMode = SpriteMode.single;
        this.maxParticleCount = 4;
        this.duration = 1.0f;
        this.additive = true;
        this.premultipliedAlpha = false;
        this.cleansUpBlendFunction = true;
        initialize();
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void activateParticle(int i2) {
        float scale;
        float fG;
        int scale2;
        float fG2;
        float fG3;
        float f2;
        int i3 = AnonymousClass1.$SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpriteMode[this.spriteMode.ordinal()];
        Sprite spriteG = (i3 == 1 || i3 == 2) ? this.sprites.g() : i3 != 3 ? null : this.sprites.m();
        Particle[] particleArr = this.particles;
        Particle particleNewParticle = particleArr[i2];
        if (particleNewParticle == null) {
            particleNewParticle = newParticle(spriteG);
            particleArr[i2] = particleNewParticle;
            particleNewParticle.flip(this.flipX, this.flipY);
        } else {
            particleNewParticle.set(spriteG);
        }
        float f3 = this.durationTimer / this.duration;
        int i4 = this.updateFlags;
        if (this.lifeValue.independent) {
            generateLifeValues();
        }
        if (this.lifeOffsetValue.independent) {
            generateLifeOffsetValues();
        }
        int scale3 = this.life + ((int) (this.lifeValue.getScale(f3) * this.lifeDiff));
        particleNewParticle.life = scale3;
        particleNewParticle.currentLife = scale3;
        ScaledNumericValue scaledNumericValue = this.velocityValue;
        if (scaledNumericValue.active) {
            particleNewParticle.velocity = scaledNumericValue.newLowValue();
            particleNewParticle.velocityDiff = this.velocityValue.newHighValue();
            if (!this.velocityValue.isRelative()) {
                particleNewParticle.velocityDiff -= particleNewParticle.velocity;
            }
        }
        particleNewParticle.angle = this.angleValue.newLowValue();
        particleNewParticle.angleDiff = this.angleValue.newHighValue();
        if (!this.angleValue.isRelative()) {
            particleNewParticle.angleDiff -= particleNewParticle.angle;
        }
        int i5 = i4 & 2;
        if (i5 == 0) {
            scale = (this.angleValue.getScale(0.0f) * particleNewParticle.angleDiff) + particleNewParticle.angle;
            particleNewParticle.angle = scale;
            particleNewParticle.angleCos = j.c(scale);
            particleNewParticle.angleSin = j.k(scale);
        } else {
            scale = 0.0f;
        }
        float width = spriteG.getWidth();
        float height = spriteG.getHeight();
        particleNewParticle.xScale = this.xScaleValue.newLowValue() / width;
        particleNewParticle.xScaleDiff = this.xScaleValue.newHighValue() / width;
        if (!this.xScaleValue.isRelative()) {
            particleNewParticle.xScaleDiff -= particleNewParticle.xScale;
        }
        ScaledNumericValue scaledNumericValue2 = this.yScaleValue;
        if (scaledNumericValue2.active) {
            particleNewParticle.yScale = scaledNumericValue2.newLowValue() / height;
            particleNewParticle.yScaleDiff = this.yScaleValue.newHighValue() / height;
            if (!this.yScaleValue.isRelative()) {
                particleNewParticle.yScaleDiff -= particleNewParticle.yScale;
            }
            particleNewParticle.setScale((this.xScaleValue.getScale(0.0f) * particleNewParticle.xScaleDiff) + particleNewParticle.xScale, (this.yScaleValue.getScale(0.0f) * particleNewParticle.yScaleDiff) + particleNewParticle.yScale);
        } else {
            particleNewParticle.setScale((this.xScaleValue.getScale(0.0f) * particleNewParticle.xScaleDiff) + particleNewParticle.xScale);
        }
        ScaledNumericValue scaledNumericValue3 = this.rotationValue;
        if (scaledNumericValue3.active) {
            particleNewParticle.rotation = scaledNumericValue3.newLowValue();
            particleNewParticle.rotationDiff = this.rotationValue.newHighValue();
            if (!this.rotationValue.isRelative()) {
                particleNewParticle.rotationDiff -= particleNewParticle.rotation;
            }
            float scale4 = (this.rotationValue.getScale(0.0f) * particleNewParticle.rotationDiff) + particleNewParticle.rotation;
            if (this.aligned) {
                scale4 += scale;
            }
            particleNewParticle.setRotation(scale4);
        }
        ScaledNumericValue scaledNumericValue4 = this.windValue;
        if (scaledNumericValue4.active) {
            particleNewParticle.wind = scaledNumericValue4.newLowValue();
            particleNewParticle.windDiff = this.windValue.newHighValue();
            if (!this.windValue.isRelative()) {
                particleNewParticle.windDiff -= particleNewParticle.wind;
            }
        }
        ScaledNumericValue scaledNumericValue5 = this.gravityValue;
        if (scaledNumericValue5.active) {
            particleNewParticle.gravity = scaledNumericValue5.newLowValue();
            particleNewParticle.gravityDiff = this.gravityValue.newHighValue();
            if (!this.gravityValue.isRelative()) {
                particleNewParticle.gravityDiff -= particleNewParticle.gravity;
            }
        }
        float[] fArr = particleNewParticle.tint;
        if (fArr == null) {
            fArr = new float[3];
            particleNewParticle.tint = fArr;
        }
        float[] color = this.tintValue.getColor(0.0f);
        fArr[0] = color[0];
        fArr[1] = color[1];
        fArr[2] = color[2];
        particleNewParticle.transparency = this.transparencyValue.newLowValue();
        particleNewParticle.transparencyDiff = this.transparencyValue.newHighValue() - particleNewParticle.transparency;
        float fG4 = this.f1687x;
        RangedNumericValue rangedNumericValue = this.xOffsetValue;
        if (rangedNumericValue.active) {
            fG4 += rangedNumericValue.newLowValue();
        }
        float fNewLowValue = this.f1688y;
        RangedNumericValue rangedNumericValue2 = this.yOffsetValue;
        if (rangedNumericValue2.active) {
            fNewLowValue += rangedNumericValue2.newLowValue();
        }
        int i6 = AnonymousClass1.$SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpawnShape[this.spawnShapeValue.shape.ordinal()];
        if (i6 != 1) {
            if (i6 == 2) {
                float scale5 = (this.spawnWidthValue.getScale(f3) * this.spawnWidthDiff) + this.spawnWidth;
                float f4 = scale5 / 2.0f;
                float scale6 = ((this.spawnHeightValue.getScale(f3) * this.spawnHeightDiff) + this.spawnHeight) / 2.0f;
                if (f4 != 0.0f && scale6 != 0.0f) {
                    float f5 = f4 / scale6;
                    SpawnShapeValue spawnShapeValue = this.spawnShapeValue;
                    if (spawnShapeValue.edges) {
                        int i7 = AnonymousClass1.$SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpawnEllipseSide[spawnShapeValue.side.ordinal()];
                        float fG5 = i7 != 1 ? i7 != 2 ? j.g(360.0f) : j.g(179.0f) : -j.g(179.0f);
                        float fC = j.c(fG5);
                        float fK = j.k(fG5);
                        fG4 += fC * f4;
                        fNewLowValue += (f4 * fK) / f5;
                        if (i5 == 0) {
                            particleNewParticle.angle = fG5;
                            particleNewParticle.angleCos = fC;
                            particleNewParticle.angleSin = fK;
                        }
                    } else {
                        float f6 = f4 * f4;
                        do {
                            fG2 = j.g(scale5) - f4;
                            fG3 = j.g(scale5) - f4;
                        } while ((fG3 * fG3) + (fG2 * fG2) > f6);
                        fG4 += fG2;
                        f2 = fG3 / f5;
                        fNewLowValue += f2;
                    }
                }
            } else if (i6 == 3) {
                float scale7 = (this.spawnWidthValue.getScale(f3) * this.spawnWidthDiff) + this.spawnWidth;
                float scale8 = (this.spawnHeightValue.getScale(f3) * this.spawnHeightDiff) + this.spawnHeight;
                if (scale7 != 0.0f) {
                    float fNextFloat = j.f69a.nextFloat() * scale7;
                    fG4 += fNextFloat;
                    f2 = (scale8 / scale7) * fNextFloat;
                    fNewLowValue += f2;
                } else {
                    fG = j.f69a.nextFloat() * scale8;
                }
            }
            particleNewParticle.setBounds(fG4 - (width / 2.0f), fNewLowValue - (height / 2.0f), width, height);
            scale2 = (int) ((this.lifeOffsetValue.getScale(f3) * this.lifeOffsetDiff) + this.lifeOffset);
            if (scale2 <= 0) {
                int i8 = particleNewParticle.currentLife;
                if (scale2 >= i8) {
                    scale2 = i8 - 1;
                }
                updateParticle(particleNewParticle, scale2 / 1000.0f, scale2);
                return;
            }
            return;
        }
        float scale9 = (this.spawnWidthValue.getScale(f3) * this.spawnWidthDiff) + this.spawnWidth;
        float scale10 = (this.spawnHeightValue.getScale(f3) * this.spawnHeightDiff) + this.spawnHeight;
        fG4 += j.g(scale9) - (scale9 / 2.0f);
        fG = j.g(scale10) - (scale10 / 2.0f);
        fNewLowValue += fG;
        particleNewParticle.setBounds(fG4 - (width / 2.0f), fNewLowValue - (height / 2.0f), width, height);
        scale2 = (int) ((this.lifeOffsetValue.getScale(f3) * this.lifeOffsetDiff) + this.lifeOffset);
        if (scale2 <= 0) {
        }
    }

    private void generateLifeOffsetValues() {
        IndependentScaledNumericValue independentScaledNumericValue = this.lifeOffsetValue;
        this.lifeOffset = independentScaledNumericValue.active ? (int) independentScaledNumericValue.newLowValue() : 0;
        this.lifeOffsetDiff = (int) this.lifeOffsetValue.newHighValue();
        if (this.lifeOffsetValue.isRelative()) {
            return;
        }
        this.lifeOffsetDiff -= this.lifeOffset;
    }

    private void generateLifeValues() {
        this.life = (int) this.lifeValue.newLowValue();
        this.lifeDiff = (int) this.lifeValue.newHighValue();
        if (this.lifeValue.isRelative()) {
            return;
        }
        this.lifeDiff -= this.life;
    }

    private void initialize() {
        this.sprites = new com.badlogic.gdx.utils.a<>();
        this.imagePaths = new com.badlogic.gdx.utils.a<>();
        this.durationValue.setAlwaysActive(true);
        this.emissionValue.setAlwaysActive(true);
        this.lifeValue.setAlwaysActive(true);
        this.xScaleValue.setAlwaysActive(true);
        this.transparencyValue.setAlwaysActive(true);
        this.spawnShapeValue.setAlwaysActive(true);
        this.spawnWidthValue.setAlwaysActive(true);
        this.spawnHeightValue.setAlwaysActive(true);
    }

    static boolean readBoolean(String str) {
        return Boolean.parseBoolean(readString(str));
    }

    static float readFloat(BufferedReader bufferedReader, String str) {
        return Float.parseFloat(readString(bufferedReader, str));
    }

    static int readInt(BufferedReader bufferedReader, String str) {
        return Integer.parseInt(readString(bufferedReader, str));
    }

    static String readString(String str) {
        return str.substring(str.indexOf(":") + 1).trim();
    }

    private void restart() {
        RangedNumericValue rangedNumericValue = this.delayValue;
        this.delay = rangedNumericValue.active ? rangedNumericValue.newLowValue() : 0.0f;
        this.delayTimer = 0.0f;
        this.durationTimer -= this.duration;
        this.duration = this.durationValue.newLowValue();
        this.emission = (int) this.emissionValue.newLowValue();
        this.emissionDiff = (int) this.emissionValue.newHighValue();
        if (!this.emissionValue.isRelative()) {
            this.emissionDiff -= this.emission;
        }
        if (!this.lifeValue.independent) {
            generateLifeValues();
        }
        if (!this.lifeOffsetValue.independent) {
            generateLifeOffsetValues();
        }
        this.spawnWidth = this.spawnWidthValue.newLowValue();
        this.spawnWidthDiff = this.spawnWidthValue.newHighValue();
        if (!this.spawnWidthValue.isRelative()) {
            this.spawnWidthDiff -= this.spawnWidth;
        }
        this.spawnHeight = this.spawnHeightValue.newLowValue();
        this.spawnHeightDiff = this.spawnHeightValue.newHighValue();
        if (!this.spawnHeightValue.isRelative()) {
            this.spawnHeightDiff -= this.spawnHeight;
        }
        this.updateFlags = 0;
        ScaledNumericValue scaledNumericValue = this.angleValue;
        if (scaledNumericValue.active && scaledNumericValue.timeline.length > 1) {
            this.updateFlags = 2;
        }
        if (this.velocityValue.active) {
            this.updateFlags |= 8;
        }
        if (this.xScaleValue.timeline.length > 1) {
            this.updateFlags |= 1;
        }
        ScaledNumericValue scaledNumericValue2 = this.yScaleValue;
        if (scaledNumericValue2.active && scaledNumericValue2.timeline.length > 1) {
            this.updateFlags |= 1;
        }
        ScaledNumericValue scaledNumericValue3 = this.rotationValue;
        if (scaledNumericValue3.active && scaledNumericValue3.timeline.length > 1) {
            this.updateFlags |= 4;
        }
        if (this.windValue.active) {
            this.updateFlags |= 16;
        }
        if (this.gravityValue.active) {
            this.updateFlags |= 32;
        }
        if (this.tintValue.timeline.length > 1) {
            this.updateFlags |= 64;
        }
        if (this.spriteMode == SpriteMode.animated) {
            this.updateFlags |= 128;
        }
    }

    private boolean updateParticle(Particle particle, float f2, int i2) {
        float scale;
        float scale2;
        int i3 = particle.currentLife - i2;
        if (i3 <= 0) {
            return false;
        }
        particle.currentLife = i3;
        float f3 = 1.0f - (i3 / particle.life);
        int i4 = this.updateFlags;
        if ((i4 & 1) != 0) {
            if (this.yScaleValue.active) {
                particle.setScale((this.xScaleValue.getScale(f3) * particle.xScaleDiff) + particle.xScale, (this.yScaleValue.getScale(f3) * particle.yScaleDiff) + particle.yScale);
            } else {
                particle.setScale((this.xScaleValue.getScale(f3) * particle.xScaleDiff) + particle.xScale);
            }
        }
        if ((i4 & 8) != 0) {
            float scale3 = ((this.velocityValue.getScale(f3) * particle.velocityDiff) + particle.velocity) * f2;
            if ((i4 & 2) != 0) {
                float scale4 = (this.angleValue.getScale(f3) * particle.angleDiff) + particle.angle;
                scale = j.c(scale4) * scale3;
                scale2 = j.k(scale4) * scale3;
                if ((i4 & 4) != 0) {
                    float scale5 = (this.rotationValue.getScale(f3) * particle.rotationDiff) + particle.rotation;
                    if (this.aligned) {
                        scale5 += scale4;
                    }
                    particle.setRotation(scale5);
                }
            } else {
                scale = particle.angleCos * scale3;
                scale2 = particle.angleSin * scale3;
                if (this.aligned || (i4 & 4) != 0) {
                    float scale6 = (this.rotationValue.getScale(f3) * particle.rotationDiff) + particle.rotation;
                    if (this.aligned) {
                        scale6 += particle.angle;
                    }
                    particle.setRotation(scale6);
                }
            }
            if ((i4 & 16) != 0) {
                scale += ((this.windValue.getScale(f3) * particle.windDiff) + particle.wind) * f2;
            }
            if ((i4 & 32) != 0) {
                scale2 += ((this.gravityValue.getScale(f3) * particle.gravityDiff) + particle.gravity) * f2;
            }
            particle.translate(scale, scale2);
        } else if ((i4 & 4) != 0) {
            particle.setRotation((this.rotationValue.getScale(f3) * particle.rotationDiff) + particle.rotation);
        }
        float[] color = (i4 & 64) != 0 ? this.tintValue.getColor(f3) : particle.tint;
        if (this.premultipliedAlpha) {
            float f4 = this.additive ? 0.0f : 1.0f;
            float scale7 = (this.transparencyValue.getScale(f3) * particle.transparencyDiff) + particle.transparency;
            particle.setColor(color[0] * scale7, color[1] * scale7, color[2] * scale7, scale7 * f4);
        } else {
            particle.setColor(color[0], color[1], color[2], (this.transparencyValue.getScale(f3) * particle.transparencyDiff) + particle.transparency);
        }
        if ((i4 & 128) != 0) {
            int i5 = this.sprites.f1750b;
            int iMin = Math.min((int) (f3 * i5), i5 - 1);
            if (particle.frame != iMin) {
                Sprite sprite = this.sprites.get(iMin);
                float width = particle.getWidth();
                float height = particle.getHeight();
                particle.setRegion(sprite);
                particle.setSize(sprite.getWidth(), sprite.getHeight());
                particle.setOrigin(sprite.getOriginX(), sprite.getOriginY());
                particle.translate((width - sprite.getWidth()) / 2.0f, (height - sprite.getHeight()) / 2.0f);
                particle.frame = iMin;
            }
        }
        return true;
    }

    public void addParticle() {
        int i2 = this.activeCount;
        if (i2 == this.maxParticleCount) {
            return;
        }
        boolean[] zArr = this.active;
        int length = zArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (!zArr[i3]) {
                activateParticle(i3);
                zArr[i3] = true;
                this.activeCount = i2 + 1;
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
    
        activateParticle(r3);
        r0[r3] = true;
        r2 = r2 + 1;
        r3 = r3 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addParticles(int i2) {
        int iMin = Math.min(i2, this.maxParticleCount - this.activeCount);
        if (iMin == 0) {
            return;
        }
        boolean[] zArr = this.active;
        int length = zArr.length;
        int i3 = 0;
        int i4 = 0;
        loop0: while (i3 < iMin) {
            while (i4 < length) {
                if (!zArr[i4]) {
                    break;
                } else {
                    i4++;
                }
            }
            break loop0;
        }
        this.activeCount += iMin;
    }

    public void allowCompletion() {
        this.allowCompletion = true;
        this.durationTimer = this.duration;
    }

    public boolean cleansUpBlendFunction() {
        return this.cleansUpBlendFunction;
    }

    public void draw(Batch batch) {
        if (this.premultipliedAlpha) {
            batch.setBlendFunction(1, GL20.GL_ONE_MINUS_SRC_ALPHA);
        } else if (this.additive) {
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, 1);
        } else {
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        }
        Particle[] particleArr = this.particles;
        boolean[] zArr = this.active;
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (zArr[i2]) {
                particleArr[i2].draw(batch);
            }
        }
        if (this.cleansUpBlendFunction) {
            if (this.additive || this.premultipliedAlpha) {
                batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            }
        }
    }

    public void flipY() {
        ScaledNumericValue scaledNumericValue = this.angleValue;
        scaledNumericValue.setHigh(-scaledNumericValue.getHighMin(), -this.angleValue.getHighMax());
        ScaledNumericValue scaledNumericValue2 = this.angleValue;
        scaledNumericValue2.setLow(-scaledNumericValue2.getLowMin(), -this.angleValue.getLowMax());
        ScaledNumericValue scaledNumericValue3 = this.gravityValue;
        scaledNumericValue3.setHigh(-scaledNumericValue3.getHighMin(), -this.gravityValue.getHighMax());
        ScaledNumericValue scaledNumericValue4 = this.gravityValue;
        scaledNumericValue4.setLow(-scaledNumericValue4.getLowMin(), -this.gravityValue.getLowMax());
        ScaledNumericValue scaledNumericValue5 = this.windValue;
        scaledNumericValue5.setHigh(-scaledNumericValue5.getHighMin(), -this.windValue.getHighMax());
        ScaledNumericValue scaledNumericValue6 = this.windValue;
        scaledNumericValue6.setLow(-scaledNumericValue6.getLowMin(), -this.windValue.getLowMax());
        ScaledNumericValue scaledNumericValue7 = this.rotationValue;
        scaledNumericValue7.setHigh(-scaledNumericValue7.getHighMin(), -this.rotationValue.getHighMax());
        ScaledNumericValue scaledNumericValue8 = this.rotationValue;
        scaledNumericValue8.setLow(-scaledNumericValue8.getLowMin(), -this.rotationValue.getLowMax());
        RangedNumericValue rangedNumericValue = this.yOffsetValue;
        rangedNumericValue.setLow(-rangedNumericValue.getLowMin(), -this.yOffsetValue.getLowMax());
    }

    public int getActiveCount() {
        return this.activeCount;
    }

    public ScaledNumericValue getAngle() {
        return this.angleValue;
    }

    public a getBoundingBox() {
        if (this.bounds == null) {
            this.bounds = new a();
        }
        Particle[] particleArr = this.particles;
        boolean[] zArr = this.active;
        a aVar = this.bounds;
        aVar.f();
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (zArr[i2]) {
                p boundingRectangle = particleArr[i2].getBoundingRectangle();
                aVar.a(boundingRectangle.f89x, boundingRectangle.f90y, 0.0f);
                aVar.a(boundingRectangle.f89x + boundingRectangle.width, boundingRectangle.f90y + boundingRectangle.height, 0.0f);
            }
        }
        return aVar;
    }

    public RangedNumericValue getDelay() {
        return this.delayValue;
    }

    public RangedNumericValue getDuration() {
        return this.durationValue;
    }

    public ScaledNumericValue getEmission() {
        return this.emissionValue;
    }

    public ScaledNumericValue getGravity() {
        return this.gravityValue;
    }

    public com.badlogic.gdx.utils.a<String> getImagePaths() {
        return this.imagePaths;
    }

    public ScaledNumericValue getLife() {
        return this.lifeValue;
    }

    public ScaledNumericValue getLifeOffset() {
        return this.lifeOffsetValue;
    }

    public int getMaxParticleCount() {
        return this.maxParticleCount;
    }

    public int getMinParticleCount() {
        return this.minParticleCount;
    }

    protected RangedNumericValue[] getMotionValues() {
        if (this.motionValues == null) {
            this.motionValues = new RangedNumericValue[]{this.velocityValue, this.windValue, this.gravityValue};
        }
        return this.motionValues;
    }

    public String getName() {
        return this.name;
    }

    protected Particle[] getParticles() {
        return this.particles;
    }

    public float getPercentComplete() {
        if (this.delayTimer < this.delay) {
            return 0.0f;
        }
        return Math.min(1.0f, this.durationTimer / this.duration);
    }

    public ScaledNumericValue getRotation() {
        return this.rotationValue;
    }

    public ScaledNumericValue getSpawnHeight() {
        return this.spawnHeightValue;
    }

    public SpawnShapeValue getSpawnShape() {
        return this.spawnShapeValue;
    }

    public ScaledNumericValue getSpawnWidth() {
        return this.spawnWidthValue;
    }

    public SpriteMode getSpriteMode() {
        return this.spriteMode;
    }

    public com.badlogic.gdx.utils.a<Sprite> getSprites() {
        return this.sprites;
    }

    public GradientColorValue getTint() {
        return this.tintValue;
    }

    public ScaledNumericValue getTransparency() {
        return this.transparencyValue;
    }

    public ScaledNumericValue getVelocity() {
        return this.velocityValue;
    }

    public ScaledNumericValue getWind() {
        return this.windValue;
    }

    public float getX() {
        return this.f1687x;
    }

    public RangedNumericValue getXOffsetValue() {
        return this.xOffsetValue;
    }

    public ScaledNumericValue getXScale() {
        return this.xScaleValue;
    }

    protected RangedNumericValue[] getXSizeValues() {
        if (this.xSizeValues == null) {
            this.xSizeValues = new RangedNumericValue[]{this.xScaleValue, this.spawnWidthValue, this.xOffsetValue};
        }
        return this.xSizeValues;
    }

    public float getY() {
        return this.f1688y;
    }

    public RangedNumericValue getYOffsetValue() {
        return this.yOffsetValue;
    }

    public ScaledNumericValue getYScale() {
        return this.yScaleValue;
    }

    protected RangedNumericValue[] getYSizeValues() {
        if (this.ySizeValues == null) {
            this.ySizeValues = new RangedNumericValue[]{this.yScaleValue, this.spawnHeightValue, this.yOffsetValue};
        }
        return this.ySizeValues;
    }

    public boolean isAdditive() {
        return this.additive;
    }

    public boolean isAligned() {
        return this.aligned;
    }

    public boolean isAttached() {
        return this.attached;
    }

    public boolean isBehind() {
        return this.behind;
    }

    public boolean isComplete() {
        return (!this.continuous || this.allowCompletion) && this.delayTimer >= this.delay && this.durationTimer >= this.duration && this.activeCount == 0;
    }

    public boolean isContinuous() {
        return this.continuous;
    }

    public boolean isPremultipliedAlpha() {
        return this.premultipliedAlpha;
    }

    public void load(BufferedReader bufferedReader) throws IOException {
        try {
            this.name = readString(bufferedReader, "name");
            bufferedReader.readLine();
            this.delayValue.load(bufferedReader);
            bufferedReader.readLine();
            this.durationValue.load(bufferedReader);
            bufferedReader.readLine();
            setMinParticleCount(readInt(bufferedReader, "minParticleCount"));
            setMaxParticleCount(readInt(bufferedReader, "maxParticleCount"));
            bufferedReader.readLine();
            this.emissionValue.load(bufferedReader);
            bufferedReader.readLine();
            this.lifeValue.load(bufferedReader);
            bufferedReader.readLine();
            this.lifeOffsetValue.load(bufferedReader);
            bufferedReader.readLine();
            this.xOffsetValue.load(bufferedReader);
            bufferedReader.readLine();
            this.yOffsetValue.load(bufferedReader);
            bufferedReader.readLine();
            this.spawnShapeValue.load(bufferedReader);
            bufferedReader.readLine();
            this.spawnWidthValue.load(bufferedReader);
            bufferedReader.readLine();
            this.spawnHeightValue.load(bufferedReader);
            if (bufferedReader.readLine().trim().equals("- Scale -")) {
                this.xScaleValue.load(bufferedReader);
                this.yScaleValue.setActive(false);
            } else {
                this.xScaleValue.load(bufferedReader);
                bufferedReader.readLine();
                this.yScaleValue.load(bufferedReader);
            }
            bufferedReader.readLine();
            this.velocityValue.load(bufferedReader);
            bufferedReader.readLine();
            this.angleValue.load(bufferedReader);
            bufferedReader.readLine();
            this.rotationValue.load(bufferedReader);
            bufferedReader.readLine();
            this.windValue.load(bufferedReader);
            bufferedReader.readLine();
            this.gravityValue.load(bufferedReader);
            bufferedReader.readLine();
            this.tintValue.load(bufferedReader);
            bufferedReader.readLine();
            this.transparencyValue.load(bufferedReader);
            bufferedReader.readLine();
            this.attached = readBoolean(bufferedReader, "attached");
            this.continuous = readBoolean(bufferedReader, "continuous");
            this.aligned = readBoolean(bufferedReader, "aligned");
            this.additive = readBoolean(bufferedReader, "additive");
            this.behind = readBoolean(bufferedReader, "behind");
            String line = bufferedReader.readLine();
            if (line.startsWith("premultipliedAlpha")) {
                this.premultipliedAlpha = readBoolean(line);
                line = bufferedReader.readLine();
            }
            if (line.startsWith("spriteMode")) {
                this.spriteMode = SpriteMode.valueOf(readString(line));
                bufferedReader.readLine();
            }
            com.badlogic.gdx.utils.a<String> aVar = new com.badlogic.gdx.utils.a<>();
            while (true) {
                String line2 = bufferedReader.readLine();
                if (line2 == null || line2.isEmpty()) {
                    break;
                } else {
                    aVar.a(line2);
                }
            }
            setImagePaths(aVar);
        } catch (RuntimeException e2) {
            if (this.name == null) {
                throw e2;
            }
            throw new RuntimeException("Error parsing emitter: " + this.name, e2);
        }
    }

    public void matchMotion(ParticleEmitter particleEmitter) {
        RangedNumericValue[] motionValues = getMotionValues();
        RangedNumericValue[] motionValues2 = particleEmitter.getMotionValues();
        for (int i2 = 0; i2 < motionValues.length; i2++) {
            motionValues[i2].set(motionValues2[i2]);
        }
    }

    public void matchSize(ParticleEmitter particleEmitter) {
        matchXSize(particleEmitter);
        matchYSize(particleEmitter);
    }

    public void matchXSize(ParticleEmitter particleEmitter) {
        RangedNumericValue[] xSizeValues = getXSizeValues();
        RangedNumericValue[] xSizeValues2 = particleEmitter.getXSizeValues();
        for (int i2 = 0; i2 < xSizeValues.length; i2++) {
            xSizeValues[i2].set(xSizeValues2[i2]);
        }
    }

    public void matchYSize(ParticleEmitter particleEmitter) {
        RangedNumericValue[] ySizeValues = getYSizeValues();
        RangedNumericValue[] ySizeValues2 = particleEmitter.getYSizeValues();
        for (int i2 = 0; i2 < ySizeValues.length; i2++) {
            ySizeValues[i2].set(ySizeValues2[i2]);
        }
    }

    protected Particle newParticle(Sprite sprite) {
        return new Particle(sprite);
    }

    public void preAllocateParticles() {
        if (this.sprites.f1750b == 0) {
            throw new IllegalStateException("ParticleEmitter.setSprites() must have been called before preAllocateParticles()");
        }
        int i2 = 0;
        while (true) {
            Particle[] particleArr = this.particles;
            if (i2 >= particleArr.length) {
                return;
            }
            if (particleArr[i2] == null) {
                Particle particleNewParticle = newParticle(this.sprites.g());
                particleArr[i2] = particleNewParticle;
                particleNewParticle.flip(this.flipX, this.flipY);
            }
            i2++;
        }
    }

    public void reset() {
        this.emissionDelta = 0;
        this.durationTimer = this.duration;
        boolean[] zArr = this.active;
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            zArr[i2] = false;
        }
        this.activeCount = 0;
        start();
    }

    public void save(Writer writer) throws IOException {
        writer.write(this.name + "\n");
        writer.write("- Delay -\n");
        this.delayValue.save(writer);
        writer.write("- Duration - \n");
        this.durationValue.save(writer);
        writer.write("- Count - \n");
        writer.write("min: " + this.minParticleCount + "\n");
        writer.write("max: " + this.maxParticleCount + "\n");
        writer.write("- Emission - \n");
        this.emissionValue.save(writer);
        writer.write("- Life - \n");
        this.lifeValue.save(writer);
        writer.write("- Life Offset - \n");
        this.lifeOffsetValue.save(writer);
        writer.write("- X Offset - \n");
        this.xOffsetValue.save(writer);
        writer.write("- Y Offset - \n");
        this.yOffsetValue.save(writer);
        writer.write("- Spawn Shape - \n");
        this.spawnShapeValue.save(writer);
        writer.write("- Spawn Width - \n");
        this.spawnWidthValue.save(writer);
        writer.write("- Spawn Height - \n");
        this.spawnHeightValue.save(writer);
        writer.write("- X Scale - \n");
        this.xScaleValue.save(writer);
        writer.write("- Y Scale - \n");
        this.yScaleValue.save(writer);
        writer.write("- Velocity - \n");
        this.velocityValue.save(writer);
        writer.write("- Angle - \n");
        this.angleValue.save(writer);
        writer.write("- Rotation - \n");
        this.rotationValue.save(writer);
        writer.write("- Wind - \n");
        this.windValue.save(writer);
        writer.write("- Gravity - \n");
        this.gravityValue.save(writer);
        writer.write("- Tint - \n");
        this.tintValue.save(writer);
        writer.write("- Transparency - \n");
        this.transparencyValue.save(writer);
        writer.write("- Options - \n");
        writer.write("attached: " + this.attached + "\n");
        writer.write("continuous: " + this.continuous + "\n");
        writer.write("aligned: " + this.aligned + "\n");
        writer.write("additive: " + this.additive + "\n");
        writer.write("behind: " + this.behind + "\n");
        writer.write("premultipliedAlpha: " + this.premultipliedAlpha + "\n");
        writer.write("spriteMode: " + this.spriteMode.toString() + "\n");
        writer.write("- Image Paths -\n");
        a.b<String> it = this.imagePaths.iterator();
        while (it.hasNext()) {
            writer.write(it.next() + "\n");
        }
        writer.write("\n");
    }

    public void scaleMotion(float f2) {
        if (f2 == 1.0f) {
            return;
        }
        for (RangedNumericValue rangedNumericValue : getMotionValues()) {
            rangedNumericValue.scale(f2);
        }
    }

    public void scaleSize(float f2) {
        if (f2 == 1.0f) {
            return;
        }
        scaleSize(f2, f2);
    }

    public void setAdditive(boolean z2) {
        this.additive = z2;
    }

    public void setAligned(boolean z2) {
        this.aligned = z2;
    }

    public void setAttached(boolean z2) {
        this.attached = z2;
    }

    public void setBehind(boolean z2) {
        this.behind = z2;
    }

    public void setCleansUpBlendFunction(boolean z2) {
        this.cleansUpBlendFunction = z2;
    }

    public void setContinuous(boolean z2) {
        this.continuous = z2;
    }

    public void setFlip(boolean z2, boolean z3) {
        this.flipX = z2;
        this.flipY = z3;
        Particle[] particleArr = this.particles;
        if (particleArr == null) {
            return;
        }
        int length = particleArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Particle particle = this.particles[i2];
            if (particle != null) {
                particle.flip(z2, z3);
            }
        }
    }

    public void setImagePaths(com.badlogic.gdx.utils.a<String> aVar) {
        this.imagePaths = aVar;
    }

    public void setMaxParticleCount(int i2) {
        this.maxParticleCount = i2;
        this.active = new boolean[i2];
        this.activeCount = 0;
        this.particles = new Particle[i2];
    }

    public void setMinParticleCount(int i2) {
        this.minParticleCount = i2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPosition(float f2, float f3) {
        if (this.attached) {
            float f4 = f2 - this.f1687x;
            float f5 = f3 - this.f1688y;
            boolean[] zArr = this.active;
            int length = zArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i2]) {
                    this.particles[i2].translate(f4, f5);
                }
            }
        }
        this.f1687x = f2;
        this.f1688y = f3;
    }

    public void setPremultipliedAlpha(boolean z2) {
        this.premultipliedAlpha = z2;
    }

    public void setSpriteMode(SpriteMode spriteMode) {
        this.spriteMode = spriteMode;
    }

    public void setSprites(com.badlogic.gdx.utils.a<Sprite> aVar) {
        Sprite spriteG;
        this.sprites = aVar;
        if (aVar.f1750b == 0) {
            return;
        }
        int length = this.particles.length;
        for (int i2 = 0; i2 < length; i2++) {
            Particle particle = this.particles[i2];
            if (particle == null) {
                return;
            }
            int i3 = AnonymousClass1.$SwitchMap$com$badlogic$gdx$graphics$g2d$ParticleEmitter$SpriteMode[this.spriteMode.ordinal()];
            if (i3 == 1) {
                spriteG = aVar.g();
            } else if (i3 != 2) {
                spriteG = i3 != 3 ? null : aVar.m();
            } else {
                int i4 = aVar.f1750b;
                int iMin = Math.min((int) ((1.0f - (particle.currentLife / particle.life)) * i4), i4 - 1);
                particle.frame = iMin;
                spriteG = aVar.get(iMin);
            }
            particle.setRegion(spriteG);
            particle.setOrigin(spriteG.getOriginX(), spriteG.getOriginY());
        }
    }

    public void start() {
        this.firstUpdate = true;
        this.allowCompletion = false;
        restart();
    }

    public void update(float f2) {
        float f3 = (f2 * 1000.0f) + this.accumulator;
        this.accumulator = f3;
        if (f3 < 1.0f) {
            return;
        }
        int i2 = (int) f3;
        float f4 = i2;
        this.accumulator = f3 - f4;
        float f5 = this.delayTimer;
        if (f5 < this.delay) {
            this.delayTimer = f5 + f4;
        } else {
            if (this.firstUpdate) {
                this.firstUpdate = false;
                addParticle();
            }
            float f6 = this.durationTimer;
            if (f6 < this.duration) {
                this.durationTimer = f6 + f4;
            } else if (this.continuous && !this.allowCompletion) {
                restart();
            }
            this.emissionDelta += i2;
            float scale = (this.emissionValue.getScale(this.durationTimer / this.duration) * this.emissionDiff) + this.emission;
            if (scale > 0.0f) {
                float f7 = 1000.0f / scale;
                int i3 = this.emissionDelta;
                if (i3 >= f7) {
                    int iMin = Math.min((int) (i3 / f7), this.maxParticleCount - this.activeCount);
                    this.emissionDelta = (int) (((int) (this.emissionDelta - (iMin * f7))) % f7);
                    addParticles(iMin);
                }
            }
            int i4 = this.activeCount;
            int i5 = this.minParticleCount;
            if (i4 < i5) {
                addParticles(i5 - i4);
            }
        }
        boolean[] zArr = this.active;
        int i6 = this.activeCount;
        Particle[] particleArr = this.particles;
        int length = zArr.length;
        for (int i7 = 0; i7 < length; i7++) {
            if (zArr[i7] && !updateParticle(particleArr[i7], f2, i2)) {
                zArr[i7] = false;
                i6--;
            }
        }
        this.activeCount = i6;
    }

    public static class RangedNumericValue extends ParticleValue {
        private float lowMax;
        private float lowMin;

        public float getLowMax() {
            return this.lowMax;
        }

        public float getLowMin() {
            return this.lowMin;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (this.active) {
                this.lowMin = ParticleEmitter.readFloat(bufferedReader, "lowMin");
                this.lowMax = ParticleEmitter.readFloat(bufferedReader, "lowMax");
            }
        }

        public float newLowValue() {
            float f2 = this.lowMin;
            return (j.f69a.nextFloat() * (this.lowMax - f2)) + f2;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) throws IOException {
            super.save(writer);
            if (this.active) {
                writer.write("lowMin: " + this.lowMin + "\n");
                writer.write("lowMax: " + this.lowMax + "\n");
            }
        }

        public void scale(float f2) {
            this.lowMin *= f2;
            this.lowMax *= f2;
        }

        public void set(RangedNumericValue rangedNumericValue) {
            this.lowMin = rangedNumericValue.lowMin;
            this.lowMax = rangedNumericValue.lowMax;
        }

        public void setLow(float f2) {
            this.lowMin = f2;
            this.lowMax = f2;
        }

        public void setLowMax(float f2) {
            this.lowMax = f2;
        }

        public void setLowMin(float f2) {
            this.lowMin = f2;
        }

        public void setLow(float f2, float f3) {
            this.lowMin = f2;
            this.lowMax = f3;
        }

        public void load(RangedNumericValue rangedNumericValue) {
            super.load((ParticleValue) rangedNumericValue);
            this.lowMax = rangedNumericValue.lowMax;
            this.lowMin = rangedNumericValue.lowMin;
        }
    }

    public static class ScaledNumericValue extends RangedNumericValue {
        private float highMax;
        private float highMin;
        private boolean relative;
        private float[] scaling = {1.0f};
        float[] timeline = {0.0f};

        public float getHighMax() {
            return this.highMax;
        }

        public float getHighMin() {
            return this.highMin;
        }

        public float getScale(float f2) {
            float[] fArr = this.timeline;
            int length = fArr.length;
            int i2 = 1;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                }
                if (fArr[i2] > f2) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return this.scaling[length - 1];
            }
            float[] fArr2 = this.scaling;
            int i3 = i2 - 1;
            float f3 = fArr2[i3];
            float f4 = fArr[i3];
            return (((f2 - f4) / (fArr[i2] - f4)) * (fArr2[i2] - f3)) + f3;
        }

        public float[] getScaling() {
            return this.scaling;
        }

        public float[] getTimeline() {
            return this.timeline;
        }

        public boolean isRelative() {
            return this.relative;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (!this.active) {
                return;
            }
            this.highMin = ParticleEmitter.readFloat(bufferedReader, "highMin");
            this.highMax = ParticleEmitter.readFloat(bufferedReader, "highMax");
            this.relative = ParticleEmitter.readBoolean(bufferedReader, "relative");
            this.scaling = new float[ParticleEmitter.readInt(bufferedReader, "scalingCount")];
            int i2 = 0;
            int i3 = 0;
            while (true) {
                float[] fArr = this.scaling;
                if (i3 >= fArr.length) {
                    break;
                }
                fArr[i3] = ParticleEmitter.readFloat(bufferedReader, a.a.h(i3, "scaling"));
                i3++;
            }
            this.timeline = new float[ParticleEmitter.readInt(bufferedReader, "timelineCount")];
            while (true) {
                float[] fArr2 = this.timeline;
                if (i2 >= fArr2.length) {
                    return;
                }
                fArr2[i2] = ParticleEmitter.readFloat(bufferedReader, a.a.h(i2, "timeline"));
                i2++;
            }
        }

        public float newHighValue() {
            float f2 = this.highMin;
            return (j.f69a.nextFloat() * (this.highMax - f2)) + f2;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) throws IOException {
            super.save(writer);
            if (this.active) {
                writer.write("highMin: " + this.highMin + "\n");
                writer.write("highMax: " + this.highMax + "\n");
                writer.write("relative: " + this.relative + "\n");
                writer.write("scalingCount: " + this.scaling.length + "\n");
                for (int i2 = 0; i2 < this.scaling.length; i2++) {
                    StringBuilder sbR = a.a.r(i2, "scaling", ": ");
                    sbR.append(this.scaling[i2]);
                    sbR.append("\n");
                    writer.write(sbR.toString());
                }
                writer.write("timelineCount: " + this.timeline.length + "\n");
                for (int i3 = 0; i3 < this.timeline.length; i3++) {
                    StringBuilder sbR2 = a.a.r(i3, "timeline", ": ");
                    sbR2.append(this.timeline[i3]);
                    sbR2.append("\n");
                    writer.write(sbR2.toString());
                }
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue
        public void scale(float f2) {
            super.scale(f2);
            this.highMin *= f2;
            this.highMax *= f2;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue
        public void set(RangedNumericValue rangedNumericValue) {
            if (rangedNumericValue instanceof ScaledNumericValue) {
                set((ScaledNumericValue) rangedNumericValue);
            } else {
                super.set(rangedNumericValue);
            }
        }

        public void setHigh(float f2) {
            this.highMin = f2;
            this.highMax = f2;
        }

        public void setHighMax(float f2) {
            this.highMax = f2;
        }

        public void setHighMin(float f2) {
            this.highMin = f2;
        }

        public void setRelative(boolean z2) {
            this.relative = z2;
        }

        public void setScaling(float[] fArr) {
            this.scaling = fArr;
        }

        public void setTimeline(float[] fArr) {
            this.timeline = fArr;
        }

        public void setHigh(float f2, float f3) {
            this.highMin = f2;
            this.highMax = f3;
        }

        public void set(ScaledNumericValue scaledNumericValue) {
            super.set((RangedNumericValue) scaledNumericValue);
            this.highMin = scaledNumericValue.highMin;
            this.highMax = scaledNumericValue.highMax;
            float[] fArr = this.scaling;
            int length = fArr.length;
            float[] fArr2 = scaledNumericValue.scaling;
            if (length != fArr2.length) {
                this.scaling = Arrays.copyOf(fArr2, fArr2.length);
            } else {
                System.arraycopy(fArr2, 0, fArr, 0, fArr.length);
            }
            float[] fArr3 = this.timeline;
            int length2 = fArr3.length;
            float[] fArr4 = scaledNumericValue.timeline;
            if (length2 != fArr4.length) {
                this.timeline = Arrays.copyOf(fArr4, fArr4.length);
            } else {
                System.arraycopy(fArr4, 0, fArr3, 0, fArr3.length);
            }
            this.relative = scaledNumericValue.relative;
        }

        public void load(ScaledNumericValue scaledNumericValue) {
            super.load((RangedNumericValue) scaledNumericValue);
            this.highMax = scaledNumericValue.highMax;
            this.highMin = scaledNumericValue.highMin;
            float[] fArr = new float[scaledNumericValue.scaling.length];
            this.scaling = fArr;
            System.arraycopy(scaledNumericValue.scaling, 0, fArr, 0, fArr.length);
            float[] fArr2 = new float[scaledNumericValue.timeline.length];
            this.timeline = fArr2;
            System.arraycopy(scaledNumericValue.timeline, 0, fArr2, 0, fArr2.length);
            this.relative = scaledNumericValue.relative;
        }
    }

    static boolean readBoolean(BufferedReader bufferedReader, String str) {
        return Boolean.parseBoolean(readString(bufferedReader, str));
    }

    static String readString(BufferedReader bufferedReader, String str) throws IOException {
        String line = bufferedReader.readLine();
        if (line != null) {
            return readString(line);
        }
        throw new IOException(a.a.A("Missing value: ", str));
    }

    public void scaleSize(float f2, float f3) {
        if (f2 == 1.0f && f3 == 1.0f) {
            return;
        }
        for (RangedNumericValue rangedNumericValue : getXSizeValues()) {
            rangedNumericValue.scale(f2);
        }
        for (RangedNumericValue rangedNumericValue2 : getYSizeValues()) {
            rangedNumericValue2.scale(f3);
        }
    }

    public static class IndependentScaledNumericValue extends ScaledNumericValue {
        boolean independent;

        public boolean isIndependent() {
            return this.independent;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) throws IOException {
            super.load(bufferedReader);
            if (bufferedReader.markSupported()) {
                bufferedReader.mark(100);
            }
            String line = bufferedReader.readLine();
            if (line == null) {
                throw new IOException("Missing value: independent");
            }
            if (line.contains("independent")) {
                this.independent = Boolean.parseBoolean(ParticleEmitter.readString(line));
            } else if (bufferedReader.markSupported()) {
                bufferedReader.reset();
            } else {
                Gdx.app.error("ParticleEmitter", "The loaded particle effect descriptor file uses an old invalid format. Please download the latest version of the Particle Editor tool and recreate the file by loading and saving it again.");
                throw new IOException("The loaded particle effect descriptor file uses an old invalid format. Please download the latest version of the Particle Editor tool and recreate the file by loading and saving it again.");
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) throws IOException {
            super.save(writer);
            writer.write("independent: " + this.independent + "\n");
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue
        public void set(RangedNumericValue rangedNumericValue) {
            if (rangedNumericValue instanceof IndependentScaledNumericValue) {
                set((IndependentScaledNumericValue) rangedNumericValue);
            } else {
                super.set(rangedNumericValue);
            }
        }

        public void setIndependent(boolean z2) {
            this.independent = z2;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue
        public void set(ScaledNumericValue scaledNumericValue) {
            if (scaledNumericValue instanceof IndependentScaledNumericValue) {
                set((IndependentScaledNumericValue) scaledNumericValue);
            } else {
                super.set(scaledNumericValue);
            }
        }

        public void set(IndependentScaledNumericValue independentScaledNumericValue) {
            super.set((ScaledNumericValue) independentScaledNumericValue);
            this.independent = independentScaledNumericValue.independent;
        }

        public void load(IndependentScaledNumericValue independentScaledNumericValue) {
            super.load((ScaledNumericValue) independentScaledNumericValue);
            this.independent = independentScaledNumericValue.independent;
        }
    }

    public static class NumericValue extends ParticleValue {
        private float value;

        public float getValue() {
            return this.value;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (this.active) {
                this.value = ParticleEmitter.readFloat(bufferedReader, "value");
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) throws IOException {
            super.save(writer);
            if (this.active) {
                writer.write("value: " + this.value + "\n");
            }
        }

        public void setValue(float f2) {
            this.value = f2;
        }

        public void load(NumericValue numericValue) {
            super.load((ParticleValue) numericValue);
            this.value = numericValue.value;
        }
    }

    public static class ParticleValue {
        boolean active;
        boolean alwaysActive;

        public boolean isActive() {
            return this.alwaysActive || this.active;
        }

        public boolean isAlwaysActive() {
            return this.alwaysActive;
        }

        public void load(BufferedReader bufferedReader) {
            if (this.alwaysActive) {
                this.active = true;
            } else {
                this.active = ParticleEmitter.readBoolean(bufferedReader, "active");
            }
        }

        public void save(Writer writer) throws IOException {
            if (this.alwaysActive) {
                this.active = true;
                return;
            }
            writer.write("active: " + this.active + "\n");
        }

        public void setActive(boolean z2) {
            this.active = z2;
        }

        public void setAlwaysActive(boolean z2) {
            this.alwaysActive = z2;
        }

        public void load(ParticleValue particleValue) {
            this.active = particleValue.active;
            this.alwaysActive = particleValue.alwaysActive;
        }
    }

    public static class SpawnShapeValue extends ParticleValue {
        boolean edges;
        SpawnShape shape = SpawnShape.point;
        SpawnEllipseSide side = SpawnEllipseSide.both;

        public SpawnShape getShape() {
            return this.shape;
        }

        public SpawnEllipseSide getSide() {
            return this.side;
        }

        public boolean isEdges() {
            return this.edges;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (this.active) {
                SpawnShape spawnShapeValueOf = SpawnShape.valueOf(ParticleEmitter.readString(bufferedReader, "shape"));
                this.shape = spawnShapeValueOf;
                if (spawnShapeValueOf == SpawnShape.ellipse) {
                    this.edges = ParticleEmitter.readBoolean(bufferedReader, "edges");
                    this.side = SpawnEllipseSide.valueOf(ParticleEmitter.readString(bufferedReader, "side"));
                }
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) throws IOException {
            super.save(writer);
            if (this.active) {
                writer.write("shape: " + this.shape + "\n");
                if (this.shape == SpawnShape.ellipse) {
                    writer.write("edges: " + this.edges + "\n");
                    writer.write("side: " + this.side + "\n");
                }
            }
        }

        public void setEdges(boolean z2) {
            this.edges = z2;
        }

        public void setShape(SpawnShape spawnShape) {
            this.shape = spawnShape;
        }

        public void setSide(SpawnEllipseSide spawnEllipseSide) {
            this.side = spawnEllipseSide;
        }

        public void load(SpawnShapeValue spawnShapeValue) {
            super.load((ParticleValue) spawnShapeValue);
            this.shape = spawnShapeValue.shape;
            this.edges = spawnShapeValue.edges;
            this.side = spawnShapeValue.side;
        }
    }

    public void draw(Batch batch, float f2) {
        float f3 = (f2 * 1000.0f) + this.accumulator;
        this.accumulator = f3;
        if (f3 < 1.0f) {
            draw(batch);
            return;
        }
        int i2 = (int) f3;
        float f4 = i2;
        this.accumulator = f3 - f4;
        if (this.premultipliedAlpha) {
            batch.setBlendFunction(1, GL20.GL_ONE_MINUS_SRC_ALPHA);
        } else if (this.additive) {
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, 1);
        } else {
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        }
        Particle[] particleArr = this.particles;
        boolean[] zArr = this.active;
        int i3 = this.activeCount;
        int length = zArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            if (zArr[i4]) {
                Particle particle = particleArr[i4];
                if (updateParticle(particle, f2, i2)) {
                    particle.draw(batch);
                } else {
                    zArr[i4] = false;
                    i3--;
                }
            }
        }
        this.activeCount = i3;
        if (this.cleansUpBlendFunction && (this.additive || this.premultipliedAlpha)) {
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        }
        float f5 = this.delayTimer;
        if (f5 < this.delay) {
            this.delayTimer = f5 + f4;
            return;
        }
        if (this.firstUpdate) {
            this.firstUpdate = false;
            addParticle();
        }
        float f6 = this.durationTimer;
        if (f6 < this.duration) {
            this.durationTimer = f6 + f4;
        } else if (!this.continuous || this.allowCompletion) {
            return;
        } else {
            restart();
        }
        this.emissionDelta += i2;
        float scale = (this.emissionValue.getScale(this.durationTimer / this.duration) * this.emissionDiff) + this.emission;
        if (scale > 0.0f) {
            float f7 = 1000.0f / scale;
            int i5 = this.emissionDelta;
            if (i5 >= f7) {
                int iMin = Math.min((int) (i5 / f7), this.maxParticleCount - i3);
                this.emissionDelta = (int) (((int) (this.emissionDelta - (iMin * f7))) % f7);
                addParticles(iMin);
            }
        }
        int i6 = this.minParticleCount;
        if (i3 < i6) {
            addParticles(i6 - i3);
        }
    }

    public static class GradientColorValue extends ParticleValue {
        private static float[] temp = new float[4];
        private float[] colors = {1.0f, 1.0f, 1.0f};
        float[] timeline = {0.0f};

        public GradientColorValue() {
            this.alwaysActive = true;
        }

        public float[] getColor(float f2) {
            float[] fArr = this.timeline;
            int length = fArr.length;
            int i2 = 0;
            int i3 = 1;
            while (true) {
                if (i3 >= length) {
                    i3 = -1;
                    break;
                }
                if (fArr[i3] > f2) {
                    break;
                }
                i2 = i3;
                i3++;
            }
            float f3 = fArr[i2];
            int i4 = i2 * 3;
            float[] fArr2 = this.colors;
            float f4 = fArr2[i4];
            float f5 = fArr2[i4 + 1];
            float f6 = fArr2[i4 + 2];
            if (i3 == -1) {
                float[] fArr3 = temp;
                fArr3[0] = f4;
                fArr3[1] = f5;
                fArr3[2] = f6;
                return fArr3;
            }
            float f7 = (f2 - f3) / (fArr[i3] - f3);
            int i5 = i3 * 3;
            float[] fArr4 = temp;
            fArr4[0] = a.a.C(fArr2[i5], f4, f7, f4);
            fArr4[1] = a.a.C(fArr2[i5 + 1], f5, f7, f5);
            fArr4[2] = a.a.C(fArr2[i5 + 2], f6, f7, f6);
            return fArr4;
        }

        public float[] getColors() {
            return this.colors;
        }

        public float[] getTimeline() {
            return this.timeline;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (!this.active) {
                return;
            }
            this.colors = new float[ParticleEmitter.readInt(bufferedReader, "colorsCount")];
            int i2 = 0;
            int i3 = 0;
            while (true) {
                float[] fArr = this.colors;
                if (i3 >= fArr.length) {
                    break;
                }
                fArr[i3] = ParticleEmitter.readFloat(bufferedReader, a.a.h(i3, "colors"));
                i3++;
            }
            this.timeline = new float[ParticleEmitter.readInt(bufferedReader, "timelineCount")];
            while (true) {
                float[] fArr2 = this.timeline;
                if (i2 >= fArr2.length) {
                    return;
                }
                fArr2[i2] = ParticleEmitter.readFloat(bufferedReader, a.a.h(i2, "timeline"));
                i2++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) throws IOException {
            super.save(writer);
            if (this.active) {
                writer.write("colorsCount: " + this.colors.length + "\n");
                for (int i2 = 0; i2 < this.colors.length; i2++) {
                    StringBuilder sbR = a.a.r(i2, "colors", ": ");
                    sbR.append(this.colors[i2]);
                    sbR.append("\n");
                    writer.write(sbR.toString());
                }
                writer.write("timelineCount: " + this.timeline.length + "\n");
                for (int i3 = 0; i3 < this.timeline.length; i3++) {
                    StringBuilder sbR2 = a.a.r(i3, "timeline", ": ");
                    sbR2.append(this.timeline[i3]);
                    sbR2.append("\n");
                    writer.write(sbR2.toString());
                }
            }
        }

        public void setColors(float[] fArr) {
            this.colors = fArr;
        }

        public void setTimeline(float[] fArr) {
            this.timeline = fArr;
        }

        public void load(GradientColorValue gradientColorValue) {
            super.load((ParticleValue) gradientColorValue);
            float[] fArr = new float[gradientColorValue.colors.length];
            this.colors = fArr;
            System.arraycopy(gradientColorValue.colors, 0, fArr, 0, fArr.length);
            float[] fArr2 = new float[gradientColorValue.timeline.length];
            this.timeline = fArr2;
            System.arraycopy(gradientColorValue.timeline, 0, fArr2, 0, fArr2.length);
        }
    }

    public ParticleEmitter(BufferedReader bufferedReader) throws IOException {
        this.delayValue = new RangedNumericValue();
        this.lifeOffsetValue = new IndependentScaledNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeValue = new IndependentScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.xScaleValue = new ScaledNumericValue();
        this.yScaleValue = new ScaledNumericValue();
        this.rotationValue = new ScaledNumericValue();
        this.velocityValue = new ScaledNumericValue();
        this.angleValue = new ScaledNumericValue();
        this.windValue = new ScaledNumericValue();
        this.gravityValue = new ScaledNumericValue();
        this.transparencyValue = new ScaledNumericValue();
        this.tintValue = new GradientColorValue();
        this.xOffsetValue = new ScaledNumericValue();
        this.yOffsetValue = new ScaledNumericValue();
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnShapeValue = new SpawnShapeValue();
        this.spriteMode = SpriteMode.single;
        this.maxParticleCount = 4;
        this.duration = 1.0f;
        this.additive = true;
        this.premultipliedAlpha = false;
        this.cleansUpBlendFunction = true;
        initialize();
        load(bufferedReader);
    }

    public ParticleEmitter(ParticleEmitter particleEmitter) {
        this.delayValue = new RangedNumericValue();
        this.lifeOffsetValue = new IndependentScaledNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeValue = new IndependentScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.xScaleValue = new ScaledNumericValue();
        this.yScaleValue = new ScaledNumericValue();
        this.rotationValue = new ScaledNumericValue();
        this.velocityValue = new ScaledNumericValue();
        this.angleValue = new ScaledNumericValue();
        this.windValue = new ScaledNumericValue();
        this.gravityValue = new ScaledNumericValue();
        this.transparencyValue = new ScaledNumericValue();
        this.tintValue = new GradientColorValue();
        this.xOffsetValue = new ScaledNumericValue();
        this.yOffsetValue = new ScaledNumericValue();
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnShapeValue = new SpawnShapeValue();
        this.spriteMode = SpriteMode.single;
        this.maxParticleCount = 4;
        this.duration = 1.0f;
        this.additive = true;
        this.premultipliedAlpha = false;
        this.cleansUpBlendFunction = true;
        this.sprites = new com.badlogic.gdx.utils.a<>(particleEmitter.sprites);
        this.name = particleEmitter.name;
        this.imagePaths = new com.badlogic.gdx.utils.a<>(particleEmitter.imagePaths);
        setMaxParticleCount(particleEmitter.maxParticleCount);
        this.minParticleCount = particleEmitter.minParticleCount;
        this.delayValue.load(particleEmitter.delayValue);
        this.durationValue.load(particleEmitter.durationValue);
        this.emissionValue.load(particleEmitter.emissionValue);
        this.lifeValue.load(particleEmitter.lifeValue);
        this.lifeOffsetValue.load(particleEmitter.lifeOffsetValue);
        this.xScaleValue.load(particleEmitter.xScaleValue);
        this.yScaleValue.load(particleEmitter.yScaleValue);
        this.rotationValue.load(particleEmitter.rotationValue);
        this.velocityValue.load(particleEmitter.velocityValue);
        this.angleValue.load(particleEmitter.angleValue);
        this.windValue.load(particleEmitter.windValue);
        this.gravityValue.load(particleEmitter.gravityValue);
        this.transparencyValue.load(particleEmitter.transparencyValue);
        this.tintValue.load(particleEmitter.tintValue);
        this.xOffsetValue.load(particleEmitter.xOffsetValue);
        this.yOffsetValue.load(particleEmitter.yOffsetValue);
        this.spawnWidthValue.load(particleEmitter.spawnWidthValue);
        this.spawnHeightValue.load(particleEmitter.spawnHeightValue);
        this.spawnShapeValue.load(particleEmitter.spawnShapeValue);
        this.attached = particleEmitter.attached;
        this.continuous = particleEmitter.continuous;
        this.aligned = particleEmitter.aligned;
        this.behind = particleEmitter.behind;
        this.additive = particleEmitter.additive;
        this.premultipliedAlpha = particleEmitter.premultipliedAlpha;
        this.cleansUpBlendFunction = particleEmitter.cleansUpBlendFunction;
        this.spriteMode = particleEmitter.spriteMode;
        setPosition(particleEmitter.getX(), particleEmitter.getY());
    }
}
