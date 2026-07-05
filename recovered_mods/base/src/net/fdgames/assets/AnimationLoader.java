package net.fdgames.assets;

import com.badlogic.gdx.utils.a;
import java.util.Arrays;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class AnimationLoader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a<AnimationSet> f3300a;

    public static void a(String str) {
        for (String str2 : Arrays.asList(Rules.i(str).spriteName.split(";"))) {
            if (f3300a == null) {
                f3300a = new a<>();
            }
            a.b<AnimationSet> it = f3300a.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().name.equals(str2)) {
                        break;
                    }
                } else {
                    f3300a.a(new AnimationSet(str2));
                    break;
                }
            }
        }
    }
}
