package net.fdgames.GameEntities.CharacterSheet;

import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.GameEntities.CharacterSheet.SheetEffect;
import net.fdgames.GameWorld.GameData;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SheetEffects {
    private ArrayList<SheetEffect> effects;

    public final void a(SheetEffect.SheetEffectType sheetEffectType, float f2, int i2) {
        if (this.effects == null) {
            this.effects = new ArrayList<>();
        }
        Iterator<SheetEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            if (sheetEffectType.equals(it.next().type)) {
                it.remove();
            }
        }
        ArrayList<SheetEffect> arrayList = this.effects;
        SheetEffect sheetEffect = new SheetEffect();
        sheetEffect.expiration = f2;
        sheetEffect.type = sheetEffectType;
        sheetEffect.level = i2;
        arrayList.add(sheetEffect);
    }

    public final SheetEffect b(SheetEffect.SheetEffectType sheetEffectType) {
        ArrayList<SheetEffect> arrayList = this.effects;
        if (arrayList == null) {
            this.effects = new ArrayList<>();
            return null;
        }
        Iterator<SheetEffect> it = arrayList.iterator();
        while (it.hasNext()) {
            if (GameData.v().u() > it.next().expiration) {
                it.remove();
            }
        }
        for (SheetEffect sheetEffect : this.effects) {
            if (sheetEffect.type.equals(sheetEffectType)) {
                return sheetEffect;
            }
        }
        return null;
    }
}
