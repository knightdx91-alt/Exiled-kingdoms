package net.fdgames.GameEntities.Helpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import m0.b;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterInventory;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.MapEffectData;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class MapEffect {
    private int animationIndex;
    private Rectangle collisionRectangle;
    private int damage;
    private Damage.DamageType damageType;
    private boolean drawn;
    public float duration;
    private String effect;
    public boolean fullyActive;
    public String name;
    private int owner_id;
    public int size;
    private float stateRelativeTime;
    public String tag_id;
    private ArrayList<Integer> targets;
    private float timeSinceLastDamage;
    private boolean weak;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f3068x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f3069y;

    public MapEffect() {
    }

    public final int a() {
        return (this.f3068x + this.f3069y) - 35;
    }

    public final int b() {
        return (this.f3069y - this.f3068x) / 2;
    }

    public final TextureRegion c() {
        this.drawn = true;
        return (TextureRegion) GameAssets.s[this.animationIndex].animation.getKeyFrame(this.stateRelativeTime);
    }

    public final void d() {
        int i2 = this.f3068x;
        if (i2 / 32 <= 0 || this.f3069y / 32 <= 0 || i2 / 32 >= b.P().f2435u.length || this.f3069y / 32 >= b.P().f2435u[0].length) {
            return;
        }
        b.P().f2435u[this.f3068x / 32][this.f3069y / 32] = 1;
    }

    public final void e(int i2, int i3) {
        this.f3068x = i2;
        this.f3069y = i3;
        Rectangle pVar = this.collisionRectangle;
        int i4 = this.size;
        pVar.set((i2 - (i4 / 2)) - 28, (i3 - (i4 / 2)) + 28, i4, i4);
    }

    public final void f() {
        int i2 = this.f3068x;
        if (i2 / 32 <= 0 || this.f3069y / 32 <= 0 || i2 / 32 >= b.P().f2435u.length || this.f3069y / 32 >= b.P().f2435u[0].length) {
            return;
        }
        b.P().f2435u[this.f3068x / 32][this.f3069y / 32] = 0;
    }

    public final void g(float f2, float f3) {
        boolean z2;
        MapSprite mapSpriteI;
        int i2;
        if (this.drawn) {
            this.drawn = false;
            this.stateRelativeTime += f2;
            if (!this.fullyActive || ((double) this.timeSinceLastDamage) + 0.5d >= f3 || this.damage <= 0) {
                return;
            }
            b bVarP = b.P();
            Rectangle pVar = this.collisionRectangle;
            bVarP.getClass();
            ArrayList<Integer> arrayListJ = b.j(pVar);
            this.targets = arrayListJ;
            Iterator<Integer> it = arrayListJ.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                Character characterF = GameLevel.f(iIntValue);
                if (characterF != null) {
                    boolean zK0 = characterF.k0();
                    if (this.effect.contains("heal")) {
                        if (zK0) {
                            if (this.effect.equals("healparty1")) {
                                characterF.V0(6);
                            }
                            if (this.effect.equals("healparty2")) {
                                characterF.V0(8);
                            }
                            if (this.effect.equals("healparty3")) {
                                characterF.V0(10);
                            }
                        } else if (characterF.q() == 1) {
                        }
                    }
                    int i3 = -80;
                    int i4 = -50;
                    int i5 = -30;
                    if (this.effect.contains("deathcloud")) {
                        if (characterF.s()) {
                            String str = this.effect;
                            characterF.s0(12.0f);
                            boolean zEquals = str.equals("deathcloud1");
                            CharacterResistances.ResistanceType resistanceType = CharacterResistances.ResistanceType.Death;
                            if (zEquals) {
                                characterF.q1(resistanceType, -30, 12.0f);
                            }
                            if (str.equals("deathcloud2")) {
                                characterF.q1(resistanceType, -50, 12.0f);
                            }
                            if (str.equals("deathcloud3")) {
                                characterF.q1(resistanceType, -80, 12.0f);
                            }
                        }
                    }
                    if (this.effect.contains("combustion")) {
                        if (characterF.s()) {
                            String str2 = this.effect;
                            if (str2.equals("combustion1")) {
                                i2 = -4;
                            } else {
                                i5 = 0;
                                i2 = 0;
                            }
                            if (str2.equals("combustion2")) {
                                i2 = -8;
                            } else {
                                i4 = i5;
                            }
                            if (str2.equals("combustion3")) {
                                i2 = -12;
                            } else {
                                i3 = i4;
                            }
                            characterF.q1(CharacterResistances.ResistanceType.Fire, i3, 15.0f);
                            characterF.q1(CharacterResistances.ResistanceType.Cold, i3, 15.0f);
                            characterF.q1(CharacterResistances.ResistanceType.Shock, i3, 15.0f);
                            characterF.t1(i2, 15.0f);
                        }
                    }
                    String str3 = this.tag_id;
                    if (str3 != null) {
                        Locale locale = Locale.ENGLISH;
                        if (!str3.toLowerCase(locale).contains("storm") || (!characterF.sheet.X() && !zK0)) {
                            if (!this.tag_id.toLowerCase(locale).contains("glowing") || characterF.sheet.X() || zK0) {
                                if (!this.tag_id.toLowerCase(locale).contains("beam") || characterF.sheet.X() || zK0) {
                                    if (!this.tag_id.toLowerCase(locale).contains("deathcloud") || (!characterF.sheet.X() && !zK0)) {
                                        if (!this.tag_id.toLowerCase(locale).contains("combustion") || (!characterF.sheet.X() && !zK0)) {
                                            if (this.tag_id.toLowerCase(locale).contains("glowing")) {
                                                CharacterInventory characterInventory = characterF.sheet.inventory;
                                                if (characterInventory == null ? true : characterInventory.b()) {
                                                    z2 = true;
                                                }
                                                if (characterF.X0() && (this.owner_id != 1 || !zK0)) {
                                                    if (this.weak) {
                                                        if (characterF.lastWeakEffect + 1.0f <= GameData.v().u()) {
                                                            characterF.lastWeakEffect = GameData.v().u();
                                                        }
                                                    }
                                                    if (!this.effect.equals("") && this.effect.toLowerCase(Locale.ENGLISH).equals("slow")) {
                                                        characterF.s0(5.0f);
                                                    }
                                                    Damage.DamageType damageType = this.damageType;
                                                    int i6 = this.damage;
                                                    DamageData damageData = new DamageData(damageType, FDUtils.b(i6 / 2, i6), false);
                                                    if (GameData.v().E()) {
                                                        damageData.damages.get(0).hp = (int) (damageData.damages.get(0).hp * 1.2f);
                                                    }
                                                    if (GameData.v().F()) {
                                                        damageData.damages.get(0).hp = (int) (damageData.damages.get(0).hp * 0.6f);
                                                    }
                                                    if (z2) {
                                                        damageData.damages.get(0).hp /= 4;
                                                    }
                                                    int i7 = this.owner_id;
                                                    if (i7 <= 0 || (mapSpriteI = GameLevel.i(i7)) == null) {
                                                        characterF.R0(damageData.damages.get(0), 0, false, 0);
                                                    } else {
                                                        mapSpriteI.n(iIntValue, damageData);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    z2 = false;
                    if (characterF.X0()) {
                    }
                }
            }
            if (this.targets.size() > 0) {
                this.timeSinceLastDamage = f3;
            }
        }
    }

    public MapEffect(String str, int i2, int i3, int i4, boolean z2) {
        this.f3068x = i2;
        this.f3069y = i3;
        this.weak = false;
        if (str.contains("weak")) {
            this.weak = true;
        }
        this.fullyActive = false;
        if (z2) {
            this.f3068x += 10;
            this.f3069y += 24;
            this.fullyActive = true;
        }
        MapEffectData.MapEffectDataLine mapEffectDataLineA = Rules.f3257k.a(str);
        this.damageType = mapEffectDataLineA.dmgType;
        this.damage = mapEffectDataLineA.damage;
        String strO = FDUtils.o(mapEffectDataLineA.sprite);
        int i5 = 0;
        while (true) {
            if (i5 >= GameAssets.f3349r) {
                i5 = 0;
                break;
            } else if (GameAssets.s[i5].name.equals(strO)) {
                break;
            } else {
                i5++;
            }
        }
        this.animationIndex = i5;
        this.size = mapEffectDataLineA.range;
        int i6 = this.f3068x;
        int i7 = this.size;
        int i8 = i7 / 2;
        float f2 = i7;
        this.collisionRectangle = new Rectangle (i6 - i8, this.f3069y - i8, f2, f2);
        this.duration = mapEffectDataLineA.duration;
        this.effect = mapEffectDataLineA.effect;
        if (Settings.h() == 2) {
            this.name = mapEffectDataLineA.name_ES;
        } else {
            this.name = mapEffectDataLineA.name;
        }
        this.stateRelativeTime = (float) FDUtils.m();
        d();
        this.drawn = false;
        this.owner_id = i4;
    }
}
