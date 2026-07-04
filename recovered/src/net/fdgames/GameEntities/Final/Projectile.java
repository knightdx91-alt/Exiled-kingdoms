package net.fdgames.GameEntities.Final;

import a0.p;
import a0.q;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.a;
import com.google.android.gms.games.quest.Quests;
import java.util.ArrayList;
import java.util.Iterator;
import m0.b;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.Helpers.Factions;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Rules.AreaEffects;
import net.fdgames.Rules.WeaponStats;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Projectile extends MapSprite {
    public ArrayList<Integer> AlreadyHit;
    private boolean addedlight;
    private float angle;
    private float creationTime;
    private DamageData damageData;
    private String explosionID;
    private boolean mustaddlight;
    int owner_id;
    public float speedX;
    public float speedY;
    private String sprite;
    public ProjectileType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class ProjectileType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ProjectileType f3030a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final ProjectileType f3031b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final ProjectileType f3032c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final /* synthetic */ ProjectileType[] f3033d;

        static {
            ProjectileType projectileType = new ProjectileType("ARROW", 0);
            f3030a = projectileType;
            ProjectileType projectileType2 = new ProjectileType("BOLT", 1);
            f3031b = projectileType2;
            ProjectileType projectileType3 = new ProjectileType("GRENADE", 2);
            f3032c = projectileType3;
            f3033d = new ProjectileType[]{projectileType, projectileType2, projectileType3};
        }

        private ProjectileType() {
            throw null;
        }

        public static ProjectileType valueOf(String str) {
            return (ProjectileType) Enum.valueOf(ProjectileType.class, str);
        }

        public static ProjectileType[] values() {
            return (ProjectileType[]) f3033d.clone();
        }
    }

    public Projectile() {
        this.addedlight = false;
        this.mustaddlight = false;
        this.AlreadyHit = new ArrayList<>();
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return null;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void F(SpriteBatch spriteBatch) {
        GameAssets.f3315a.clear();
        GameAssets.f3315a.a(Assets.o(this.sprite));
        a.b<TextureRegion> it = GameAssets.f3315a.iterator();
        while (it.hasNext()) {
            spriteBatch.draw(it.next(), H(), I(), r3.getRegionWidth() / 2, r3.getRegionWidth() / 2, r3.getRegionWidth(), r3.getRegionHeight(), 1.0f, 1.0f, this.angle);
        }
    }

    @Override // net.fdgames.GameEntities.MapSprite
    protected final int G() {
        return 64;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
        if (!this.addedlight && this.mustaddlight) {
            k0.a.l().e(1.0f, q(), 60, "flash_red");
            this.addedlight = true;
        }
        if (GameData.v().u() > this.creationTime + 1.0f) {
            this.destroy = true;
        }
        int i2 = this.owner_id;
        ProjectileType projectileType = ProjectileType.f3032c;
        if (i2 == 1 && GameData.v().u() > this.creationTime + 0.45f) {
            this.destroy = true;
            if (this.type == projectileType) {
                AreaEffects.d(this.f3092x, this.f3093y, this.explosionID, 1);
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
        if (this.destroy) {
            return;
        }
        b bVarP = b.P();
        int[] iArrR = r();
        p pVar = new p(this.f3092x - 12, this.f3093y - 12, 24.0f, 24.0f);
        bVarP.getClass();
        ArrayList<Integer> arrayListL = b.l(iArrR, pVar);
        if (arrayListL.size() <= 0) {
            if (this.owner_id != 1 || this.creationTime + 0.15f <= GameData.v().u()) {
                return;
            }
            b bVarP2 = b.P();
            int i3 = this.f3092x;
            int i4 = this.f3093y;
            int i5 = i3 + 1;
            int i6 = i4 + 1;
            int i7 = (i3 - 1) / 32;
            int i8 = (i4 - 1) / 32;
            if (!bVarP2.C(i7, i8)) {
                int i9 = i6 / 32;
                if (!bVarP2.C(i7, i9)) {
                    int i10 = i5 / 32;
                    if (!bVarP2.C(i10, i9) && !bVarP2.C(i10, i8)) {
                        return;
                    }
                }
            }
            this.destroy = true;
            System.out.println("destroying projectile");
            if (this.type == projectileType) {
                AreaEffects.d(this.f3092x, this.f3093y, this.explosionID, 1);
                return;
            }
            return;
        }
        MapSprite mapSpriteI = GameLevel.i(this.owner_id);
        if (mapSpriteI == null) {
            System.out.println("WARNING: null projectile owner, no damage done");
            return;
        }
        if (this.type == ProjectileType.f3030a) {
            mapSpriteI.n(((Integer) arrayListL.get(0)).intValue(), this.damageData);
            this.destroy = true;
        }
        if (this.type == projectileType) {
            AreaEffects.d(this.f3092x, this.f3093y, this.explosionID, mapSpriteI.q());
            this.destroy = true;
        }
        if (this.type == ProjectileType.f3031b) {
            for (Integer num : arrayListL) {
                int iIntValue = num.intValue();
                ArrayList<Integer> arrayList = this.AlreadyHit;
                if (arrayList != null) {
                    Iterator<Integer> it = arrayList.iterator();
                    while (it.hasNext()) {
                        if (iIntValue == it.next().intValue()) {
                            break;
                        }
                    }
                } else {
                    this.AlreadyHit = new ArrayList<>();
                }
                mapSpriteI.n(num.intValue(), this.damageData);
                if (this.AlreadyHit == null) {
                    this.AlreadyHit = new ArrayList<>();
                }
                this.AlreadyHit.add(num);
            }
        }
    }

    public final void N(String str) {
        this.explosionID = str;
    }

    public final void O() {
        int[] iArr = this.worldfactions;
        if (iArr == null || iArr[0] == 0) {
            if (this.faction.equals(Factions.Faction.f3060a)) {
                x(Quests.SELECT_COMPLETED_UNCLAIMED);
            }
            if (this.faction.equals(Factions.Faction.f3062c)) {
                x(100);
            }
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        return "projectile " + q();
    }

    @Override // net.fdgames.GameEntities.MapObject, net.fdgames.GameEntities.GameObject
    public final void v(String str, int i2, String str2, DamageData damageData) {
        v(str, i2, str2, null);
    }

    public Projectile(int i2, int i3, int i4, int[] iArr, WeaponStats weaponStats, DamageData damageData, ProjectileType projectileType) {
        this.addedlight = false;
        this.mustaddlight = false;
        this.AlreadyHit = new ArrayList<>();
        this.creationTime = GameData.v().u();
        this.type = projectileType;
        y(iArr);
        this.damageData = damageData;
        this.owner_id = i2;
        this.f3092x = GameLevel.i(i2).f3092x;
        this.f3093y = GameLevel.i(this.owner_id).f3093y;
        this.sprite = weaponStats.sprite;
        float f2 = i3;
        float f3 = i4;
        q qVar = new q();
        qVar.f91a = f2;
        qVar.f92b = f3;
        qVar.c(new Coords(this.f3092x, this.f3093y).a());
        qVar.a();
        float f4 = qVar.f91a;
        float f5 = weaponStats.ranged_speed;
        this.speedX = f4 * f5;
        float f6 = qVar.f92b;
        this.speedY = f5 * f6;
        float fAtan2 = ((float) Math.atan2(f6, f4)) * 57.295776f;
        this.angle = fAtan2 < 0.0f ? fAtan2 + 360.0f : fAtan2;
        if (weaponStats.id.equals("firedancer_shot") || weaponStats.id.contains("fire_shot") || weaponStats.id.contains("lightning") || this.sprite.equals("ion") || weaponStats.id.contains("wand") || weaponStats.id.contains("staff")) {
            this.mustaddlight = true;
        }
        if (this.sprite.equals("ion")) {
            GameAssets.o("ion");
        }
    }
}
