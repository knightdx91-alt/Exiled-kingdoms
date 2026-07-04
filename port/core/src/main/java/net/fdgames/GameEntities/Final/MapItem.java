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
import com.badlogic.gdx.maps.MapProperties;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class MapItem extends MapSprite {
    public String activeEffect;
    public String activeSprite;
    public int animationIndex;
    public boolean barrier;
    public boolean blocksview;
    public boolean bridge;
    public String conditionsActivate;
    public String conditionsDeactivate;
    public boolean effect;
    public String inactiveSprite;
    public String mapitem_tag;
    public MapItemState state;
    public float stateRelativeTime;
    public String toggleItems;
    public boolean walkable;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum MapItemAction {
        INVISIBLE, ACTIVE, INACTIVE, WALKABLE, NONWALKABLE, BLOCKSVIEW, UNBLOCKSVIEW, VISIBLE;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum MapItemState {
        INVISIBLE, ACTIVE, INACTIVE;
    }

    public MapItem() {
    }

    public void S() {
        if (this.toggleItems == null) {
            this.toggleItems = "";
        }
        for (String str : this.toggleItems.split(";", -1)) {
            if (!str.equals(this.tag)) {
                for (int i2 = 0; i2 < GameLevelData.r().size(); i2++) {
                    MapItem mapItem = GameLevelData.r().get(i2);
                    boolean zEquals = mapItem.conditionsActivate.equals("");
                    MapItemState mapItemState = MapItemState.INACTIVE;
                    MapItemState mapItemState2 = MapItemState.ACTIVE;
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
        MapItemState mapItemState = MapItemState.ACTIVE;
        switch (iOrdinal) {
            case 0:
                this.state = MapItemState.INVISIBLE;
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
                this.state = MapItemState.INACTIVE;
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
        return Boolean.valueOf(this.state == MapItemState.ACTIVE);
    }

    public final Boolean P() {
        return Boolean.valueOf(this.state == MapItemState.INVISIBLE);
    }

    public final boolean Q() {
        if (this.toggleItems == null) {
            this.toggleItems = "";
        }
        return !this.toggleItems.equals("");
    }

    public final void R() {
        MapItemState mapItemState = this.state;
        MapItemState mapItemState2 = MapItemState.ACTIVE;
        MapItemState mapItemState3 = MapItemState.INACTIVE;
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
        if (!z3 || this.state != MapItemState.ACTIVE) {
            MapItemState mapItemState2 = MapItemState.INACTIVE;
            z2 = (z3 && this.state == mapItemState2) ? true : (this.bridge && ((mapItemState = this.state) == mapItemState2 || mapItemState == MapItemState.INVISIBLE)) ? false : this.walkable;
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

    public MapItem(MapProperties hVar) {
        this.f3092x = ((int) Float.parseFloat(hVar.get("x").toString())) - 24;
        this.f3093y = ((int) Float.parseFloat(hVar.get("y").toString())) + 24;
        if (hVar.containsKey("tag")) {
            this.tag = hVar.get("tag").toString();
        }
        this.walkable = hVar.containsKey("walkable");
        this.blocksview = hVar.containsKey("blocksview");
        T();
        MapItemState mapItemState = MapItemState.ACTIVE;
        this.state = mapItemState;
        boolean zA = hVar.containsKey("inactive");
        MapItemState mapItemState2 = MapItemState.INACTIVE;
        if (zA) {
            if (new ConditionsSet(hVar.get("inactive").toString()).a().booleanValue()) {
                this.state = mapItemState2;
            } else {
                this.state = mapItemState;
            }
        }
        if (hVar.containsKey("active")) {
            if (new ConditionsSet(hVar.get("active").toString()).a().booleanValue()) {
                this.state = mapItemState;
            } else {
                this.state = mapItemState2;
            }
        }
        boolean zA2 = hVar.containsKey("invisible");
        MapItemState mapItemState3 = MapItemState.INVISIBLE;
        if (zA2) {
            this.state = mapItemState3;
        }
        if (hVar.containsKey("active_sprite")) {
            this.activeSprite = hVar.get("active_sprite").toString();
        }
        if (hVar.containsKey("inactive_sprite")) {
            this.inactiveSprite = hVar.get("inactive_sprite").toString();
        }
        this.effect = false;
        if (hVar.containsKey("active_effect")) {
            String string = hVar.get("active_effect").toString();
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
        if (hVar.containsKey("toggle_items")) {
            this.toggleItems = hVar.get("toggle_items").toString().trim();
        }
        this.conditionsActivate = "";
        if (hVar.containsKey("conditions_activate")) {
            this.conditionsActivate = hVar.get("conditions_activate").toString().trim();
        }
        this.conditionsDeactivate = "";
        if (hVar.containsKey("conditions_deactivate")) {
            this.conditionsDeactivate = hVar.get("conditions_deactivate").toString().trim();
        }
        this.barrier = false;
        if (hVar.containsKey("barrier")) {
            this.barrier = true;
            this.walkable = true;
            if (this.state == mapItemState) {
                this.walkable = false;
            }
        }
        this.bridge = false;
        if (hVar.containsKey("bridge")) {
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
