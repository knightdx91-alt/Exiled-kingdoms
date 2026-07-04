package r0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: Analytics.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a {

    /* JADX INFO: renamed from: r0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Analytics.java */
    final class C0059a implements l.c {
        @Override // com.badlogic.gdx.Net.c
        public final void cancelled() {
        }

        @Override // com.badlogic.gdx.Net.c
        public final void failed(Throwable th) {
        }

        @Override // com.badlogic.gdx.Net.c
        public final void handleHttpResponse(l.b bVar) {
        }
    }

    public static void a(String str) {
        System.out.println(str);
        HashMap map = new HashMap();
        map.put("key", "12345");
        map.put("license", ExiledKingdoms.f3371a ? Settings.f().equals("99999") ? "C" : "R" : "U");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append(simpleDateFormat.format(date));
        sb.append(";1.3.1217 ");
        sb.append(ExiledKingdoms.f3371a ? Settings.f().equals("99999") ? "C" : "R" : "U");
        sb.append(";");
        sb.append(Settings.f());
        sb.append(";");
        sb.append(Settings.a());
        sb.append(";");
        sb.append(ExiledKingdoms.f3372b);
        sb.append(" ");
        sb.append(ExiledKingdoms.f3373c);
        sb.append(";");
        sb.append(Settings.j());
        sb.append(";");
        sb.append(str);
        map.put("data", sb.toString());
        l.a aVar = new l.a("POST");
        aVar.f("http://www.exiledkingdoms.com/analytics/logerror2.php");
        Set<String> setKeySet = map.keySet();
        StringBuilder sb2 = new StringBuilder();
        for (String str2 : setKeySet) {
            try {
                sb2.append(URLEncoder.encode(str2, "UTF-8"));
                sb2.append("=");
                try {
                    sb2.append(URLEncoder.encode((String) map.get(str2), "UTF-8"));
                    sb2.append("&");
                } catch (UnsupportedEncodingException e2) {
                    throw new IllegalArgumentException(e2);
                }
            } catch (UnsupportedEncodingException e3) {
                throw new IllegalArgumentException(e3);
            }
        }
        if (sb2.length() > 0) {
            sb2.deleteCharAt(sb2.length() - 1);
        }
        aVar.e(sb2.toString());
        Gdx.f1569net.sendHttpRequest(aVar, new C0059a());
    }
}
