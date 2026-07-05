package net.fdgames.assets;

import com.badlogic.gdx.utils.a;
import java.util.Arrays;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.Spawn;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class AnimationLoader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a<AnimationSet> f3521a;

    public static void a(String str) {
        Spawn spawnI = Rules.i(str);
        if (spawnI == null) {
            return;
        }
        for (String str2 : Arrays.asList(spawnI.spriteName.split(";"))) {
            if (f3521a == null) {
                f3521a = new a<>();
            }
            a.b<AnimationSet> it = f3521a.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().name.equals(str2)) {
                        break;
                    }
                } else {
                    f3521a.a(new AnimationSet(str2));
                    break;
                }
            }
        }
    }
}
