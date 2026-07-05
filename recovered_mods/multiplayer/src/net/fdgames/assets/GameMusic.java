package net.fdgames.assets;

import com.badlogic.gdx.Gdx;
import d1.a;
import f0.c;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class GameMusic {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c f3589a;

    public static void a(String str) {
        c cVar = f3589a;
        if (cVar != null) {
            cVar.stop();
            f3589a = null;
        }
        try {
            try {
                if (Gdx.files.internal("data/music/" + str + ".mp3").exists()) {
                    f3589a = Gdx.audio.newMusic(Gdx.files.internal("data/music/" + str + ".mp3"));
                }
            } catch (Exception unused) {
                f3589a = Gdx.audio.newMusic(Gdx.files.internal("data/music/" + str + ".mp3"));
            }
        } catch (Exception unused2) {
            a.a("ERROR 3.3 ;" + str);
        }
        c cVar2 = f3589a;
        if (cVar2 != null) {
            cVar2.setVolume(Settings.i());
            f3589a.setLooping(true);
            f3589a.play();
        }
    }

    public static void b(float f2) {
        c cVar = f3589a;
        if (cVar != null) {
            cVar.setVolume(f2);
        }
    }
}
