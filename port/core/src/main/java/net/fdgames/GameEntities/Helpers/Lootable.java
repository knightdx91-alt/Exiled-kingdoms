package net.fdgames.GameEntities.Helpers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public interface Lootable {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum LootableType {
        MAPCONTAINER, LOOT, SHOP;
    }

    float a();

    void b();

    void c(int i2);

    void d();

    void e();

    boolean f();

    boolean g();

    int getItem(int i2);

    String getName();

    LootableType getType();

    int h();

    boolean i(int i2);

    Boolean isEmpty();

    Items j();

    boolean k(int i2);

    TextureRegion l();

    void removeItem(int i2);
}
