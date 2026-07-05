package net.fdgames.GameEntities.Final;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import m0.b;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import u.h;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MapItem extends MapSprite {
    private String activeEffect;
    private String activeSprite;
    private int animationIndex;
    public boolean barrier;
    public boolean blocksview;
    public boolean bridge;
    private String conditionsActivate;
    private String conditionsDeactivate;
    private boolean effect;
    private String inactiveSprite;
    public String mapitem_tag;
    private MapItemState state;
    private float stateRelativeTime;
    private String toggleItems;
    private boolean walkable;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class MapItemAction {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final MapItemAction f3006a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final MapItemAction f3007b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final MapItemAction f3008c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final MapItemAction f3009d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final MapItemAction f3010e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final MapItemAction f3011f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final MapItemAction f3012g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final MapItemAction f3013h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private static final /* synthetic */ MapItemAction[] f3014i;

        static {
            MapItemAction mapItemAction = new MapItemAction("INVISIBLE", 0);
            f3006a = mapItemAction;
            MapItemAction mapItemAction2 = new MapItemAction("ACTIVE", 1);
            f3007b = mapItemAction2;
            MapItemAction mapItemAction3 = new MapItemAction("INACTIVE", 2);
            f3008c = mapItemAction3;
            MapItemAction mapItemAction4 = new MapItemAction("WALKABLE", 3);
            f3009d = mapItemAction4;
            MapItemAction mapItemAction5 = new MapItemAction("NONWALKABLE", 4);
            f3010e = mapItemAction5;
            MapItemAction mapItemAction6 = new MapItemAction("BLOCKSVIEW", 5);
            f3011f = mapItemAction6;
            MapItemAction mapItemAction7 = new MapItemAction("UNBLOCKSVIEW", 6);
            f3012g = mapItemAction7;
            MapItemAction mapItemAction8 = new MapItemAction("VISIBLE", 7);
            f3013h = mapItemAction8;
            f3014i = new MapItemAction[]{mapItemAction, mapItemAction2, mapItemAction3, mapItemAction4, mapItemAction5, mapItemAction6, mapItemAction7, mapItemAction8};
        }

        private MapItemAction() {
            throw null;
        }

        public static MapItemAction valueOf(String str) {
            return (MapItemAction) Enum.valueOf(MapItemAction.class, str);
        }

        public static MapItemAction[] values() {
            return (MapItemAction[]) f3014i.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class MapItemState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final MapItemState f3015a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final MapItemState f3016b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final MapItemState f3017c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final /* synthetic */ MapItemState[] f3018d;

        static {
            MapItemState mapItemState = new MapItemState("INVISIBLE", 0);
            f3015a = mapItemState;
            MapItemState mapItemState2 = new MapItemState("ACTIVE", 1);
            f3016b = mapItemState2;
            MapItemState mapItemState3 = new MapItemState("INACTIVE", 2);
            f3017c = mapItemState3;
            f3018d = new MapItemState[]{mapItemState, mapItemState2, mapItemState3};
        }

        private MapItemState() {
            throw null;
        }

        public static MapItemState valueOf(String str) {
            return (MapItemState) Enum.valueOf(MapItemState.class, str);
        }

        public static MapItemState[] values() {
            return (MapItemState[]) f3018d.clone();
        }
    }

    public MapItem() {
    }

    private void S() {
        if (this.toggleItems == null) {
            this.toggleItems = "";
        }
        for (String str : this.toggleItems.split(";", -1)) {
            if (!str.equals(this.tag)) {
                for (int i2 = 0; i2 < GameLevelData.r().size(); i2++) {
                    MapItem mapItem = GameLevelData.r().get(i2);
                    boolean zEquals = mapItem.conditionsActivate.equals("");
                    MapItemState mapItemState = MapItemState.f3017c;
                    MapItemState mapItemState2 = MapItemState.f3016b;
                    if (!zEquals) {
                        if (new ConditionsSet(mapItem.conditionsActivate).a().booleanValue()) {
                            mapItem.state = mapItemState2;
                            if (mapItem.bridge) {
                                mapItem.walkable = true;
                            }
                            if (mapItem.barrier) {
                                mapItem.walkable = false;
                            }
                        } else {
                            mapItem.state = mapItemState;
                            if (mapItem.bridge) {
                                mapItem.walkable = false;
                            }
                            if (mapItem.barrier) {
                                mapItem.walkable = true;
                            }
                        }
                        mapItem.T();
                    } else if (!mapItem.conditionsDeactivate.equals("")) {
                        if (new ConditionsSet(mapItem.conditionsDeactivate).a().booleanValue()) {
                            mapItem.state = mapItemState;
                        } else {
                            mapItem.state = mapItemState2;
                        }
                        mapItem.T();
                    }
                    if (GameLevelData.r().get(i2).tag != null && GameLevelData.r().get(i2).tag.equals(str)) {
                        GameLevelData.r().get(i2).R();
                    }
                }
            }
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        int iOrdinal = this.state.ordinal();
        if (iOrdinal == 1) {
            return Assets.h(this.activeSprite);
        }
        if (iOrdinal != 2) {
            return null;
        }
        return Assets.h(this.inactiveSprite) != null ? Assets.h(this.inactiveSprite) : Assets.h(this.activeSprite);
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void F(SpriteBatch spriteBatch) {
        int iOrdinal = this.state.ordinal();
        if (iOrdinal != 1) {
            if (iOrdinal == 2 && Assets.h(this.inactiveSprite) != null) {
                spriteBatch.draw(Assets.h(this.inactiveSprite), H(), I() - 8);
                return;
            }
            return;
        }
        if (!this.effect) {
            spriteBatch.draw(Assets.h(this.activeSprite), H(), I() - 8);
        } else {
            spriteBatch.draw((TextureRegion) GameAssets.s[this.animationIndex].animation.getKeyFrame(this.stateRelativeTime), H() - 42, I() - 8);
        }
    }

    @Override // net.fdgames.GameEntities.MapSprite
    protected final int G() {
        return 64;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
        if (this.effect) {
            this.stateRelativeTime += f2;
        }
    }

    public final void N(MapItemAction mapItemAction) {
        int iOrdinal = mapItemAction.ordinal();
        MapItemState mapItemState = MapItemState.f3016b;
        switch (iOrdinal) {
            case 0:
                this.state = MapItemState.f3015a;
                T();
                break;
            case 1:
                GameAssets.o("trap");
                this.state = mapItemState;
                T();
                if (Q()) {
                    S();
                }
                break;
            case 2:
                GameAssets.o("trap");
                this.state = MapItemState.f3017c;
                T();
                if (Q()) {
                    S();
                }
                break;
            case 3:
                this.walkable = true;
                T();
                break;
            case 4:
                this.walkable = false;
                T();
                break;
            case 5:
                this.blocksview = true;
                T();
                break;
            case 6:
                this.blocksview = false;
                T();
                break;
            case 7:
                this.state = mapItemState;
                T();
                break;
        }
    }

    public final Boolean O() {
        return Boolean.valueOf(this.state == MapItemState.f3016b);
    }

    public final Boolean P() {
        return Boolean.valueOf(this.state == MapItemState.f3015a);
    }

    public final boolean Q() {
        if (this.toggleItems == null) {
            this.toggleItems = "";
        }
        return !this.toggleItems.equals("");
    }

    public final void R() {
        MapItemState mapItemState = this.state;
        MapItemState mapItemState2 = MapItemState.f3016b;
        MapItemState mapItemState3 = MapItemState.f3017c;
        if (mapItemState == mapItemState2) {
            GameAssets.o("trap");
            this.state = mapItemState3;
            T();
            S();
            return;
        }
        if (mapItemState == mapItemState3) {
            GameAssets.o("trap");
            this.state = mapItemState2;
            T();
            S();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void T() {
        MapItemState mapItemState;
        boolean z2;
        boolean z3 = this.barrier;
        if (!z3 || this.state != MapItemState.f3016b) {
            MapItemState mapItemState2 = MapItemState.f3017c;
            z2 = (z3 && this.state == mapItemState2) ? true : (this.bridge && ((mapItemState = this.state) == mapItemState2 || mapItemState == MapItemState.f3015a)) ? false : this.walkable;
        }
        if (z2) {
            b.P().f2429n[this.f3092x / 32][this.f3093y / 32] = 0;
        } else {
            b.P().f2429n[this.f3092x / 32][this.f3093y / 32] = 1;
        }
        if (this.blocksview) {
            b.P().f2431p[this.f3092x / 32][this.f3093y / 32] = 1;
        } else {
            b.P().f2431p[this.f3092x / 32][this.f3093y / 32] = 0;
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        return "";
    }

    @Override // net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        throw null;
    }

    @Override // net.fdgames.GameEntities.MapObject, net.fdgames.GameEntities.GameObject
    public final void v(String str, int i2, String str2, DamageData damageData) {
    }

    public MapItem(h hVar) {
        this.f3092x = ((int) Float.parseFloat(hVar.b("x").toString())) - 24;
        this.f3093y = ((int) Float.parseFloat(hVar.b("y").toString())) + 24;
        if (hVar.a("tag")) {
            this.tag = hVar.b("tag").toString();
        }
        this.walkable = hVar.a("walkable");
        this.blocksview = hVar.a("blocksview");
        T();
        MapItemState mapItemState = MapItemState.f3016b;
        this.state = mapItemState;
        boolean zA = hVar.a("inactive");
        MapItemState mapItemState2 = MapItemState.f3017c;
        if (zA) {
            if (new ConditionsSet(hVar.b("inactive").toString()).a().booleanValue()) {
                this.state = mapItemState2;
            } else {
                this.state = mapItemState;
            }
        }
        if (hVar.a("active")) {
            if (new ConditionsSet(hVar.b("active").toString()).a().booleanValue()) {
                this.state = mapItemState;
            } else {
                this.state = mapItemState2;
            }
        }
        boolean zA2 = hVar.a("invisible");
        MapItemState mapItemState3 = MapItemState.f3015a;
        if (zA2) {
            this.state = mapItemState3;
        }
        if (hVar.a("active_sprite")) {
            this.activeSprite = hVar.b("active_sprite").toString();
        }
        if (hVar.a("inactive_sprite")) {
            this.inactiveSprite = hVar.b("inactive_sprite").toString();
        }
        this.effect = false;
        if (hVar.a("active_effect")) {
            String string = hVar.b("active_effect").toString();
            this.activeEffect = string;
            int i2 = 0;
            while (true) {
                if (i2 >= GameAssets.f3349r) {
                    i2 = 0;
                    break;
                } else if (GameAssets.s[i2].name.equals(string)) {
                    break;
                } else {
                    i2++;
                }
            }
            this.animationIndex = i2;
            this.effect = true;
        }
        this.toggleItems = "";
        if (hVar.a("toggle_items")) {
            this.toggleItems = hVar.b("toggle_items").toString().trim();
        }
        this.conditionsActivate = "";
        if (hVar.a("conditions_activate")) {
            this.conditionsActivate = hVar.b("conditions_activate").toString().trim();
        }
        this.conditionsDeactivate = "";
        if (hVar.a("conditions_deactivate")) {
            this.conditionsDeactivate = hVar.b("conditions_deactivate").toString().trim();
        }
        this.barrier = false;
        if (hVar.a("barrier")) {
            this.barrier = true;
            this.walkable = true;
            if (this.state == mapItemState) {
                this.walkable = false;
            }
        }
        this.bridge = false;
        if (hVar.a("bridge")) {
            this.bridge = true;
            this.walkable = true;
            MapItemState mapItemState4 = this.state;
            if (mapItemState4 == mapItemState2 || mapItemState4 == mapItemState3) {
                this.walkable = false;
            }
        }
        this.stateRelativeTime = (float) FDUtils.m();
        if (this.tag == null) {
            this.tag = "";
        }
    }
}
