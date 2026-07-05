package net.fdgames.assets;

import com.badlogic.gdx.Gdx;
import net.fdgames.ek.Settings;
import r0.a;
import t.c;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GameMusic {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c f3361a;

    public static void a(String str) {
        c cVar = f3361a;
        if (cVar != null) {
            cVar.stop();
            f3361a = null;
        }
        try {
            try {
                if (Gdx.files.internal("data/music/" + str + ".mp3").exists()) {
                    f3361a = Gdx.audio.newMusic(Gdx.files.internal("data/music/" + str + ".mp3"));
                }
            } catch (Exception unused) {
                f3361a = Gdx.audio.newMusic(Gdx.files.internal("data/music/" + str + ".mp3"));
            }
        } catch (Exception unused2) {
            a.a("ERROR 3.3 ;" + str);
        }
        c cVar2 = f3361a;
        if (cVar2 != null) {
            cVar2.setVolume(Settings.i());
            f3361a.setLooping(true);
            f3361a.play();
        }
    }

    public static void b(float f2) {
        c cVar = f3361a;
        if (cVar != null) {
            cVar.setVolume(f2);
        }
    }
}
