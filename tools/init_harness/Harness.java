import java.io.File;
import com.badlogic.gdx.Gdx;

public class Harness {
    static File root;
    public static class F implements com.badlogic.gdx.d {
        public com.badlogic.gdx.q.a classpath(String p) { return new com.badlogic.gdx.q.a(new File(root, p)); }
        public com.badlogic.gdx.q.a external(String p) { return new com.badlogic.gdx.q.a(new File("/tmp/h31/ext", p)); }
        public String getExternalStoragePath() { return "/tmp/h31/ext/"; }
        public com.badlogic.gdx.q.a getFileHandle(String p, com.badlogic.gdx.d.a t) { return new com.badlogic.gdx.q.a(new File(root, p)); }
        public String getLocalStoragePath() { return "/tmp/h31/local/"; }
        public com.badlogic.gdx.q.a internal(String p) { return new com.badlogic.gdx.q.a(new File(root, p)); }
        public com.badlogic.gdx.q.a local(String p) { return new com.badlogic.gdx.q.a(new File("/tmp/h31/local", p)); }
    }
    public static void main(String[] args) throws Exception {
        root = new File(args[0]);
        Gdx.files = new F();
        Gdx.graphics = (com.badlogic.gdx.f) java.lang.reflect.Proxy.newProxyInstance(Harness.class.getClassLoader(),
                new Class[] {com.badlogic.gdx.f.class}, new java.lang.reflect.InvocationHandler() {
                    public Object invoke(Object o, java.lang.reflect.Method m, Object[] a) {
                        Class<?> r = m.getReturnType();
                        if (r == int.class) return (m.getName().contains("Width") ? 1920 : 1080);
                        if (r == float.class) return 1f;
                        if (r == long.class) return 0L;
                        if (r == double.class) return 0d;
                        if (r == boolean.class) return false;
                        return null;
                    }
                });
        Object pr = java.lang.reflect.Proxy.newProxyInstance(Harness.class.getClassLoader(),
                new Class[] {net.fdgames.ek.IPlatformResolver.class}, new java.lang.reflect.InvocationHandler() {
                    public Object invoke(Object o, java.lang.reflect.Method m, Object[] a) {
                        Class<?> r = m.getReturnType();
                        if (r == int.class) return 0;
                        if (r == float.class) return 0f;
                        if (r == long.class) return 0L;
                        if (r == double.class) return 0d;
                        if (r == boolean.class) return false;
                        if (r == String.class) return "";
                        return null;
                    }
                });
        Gdx.net = (com.badlogic.gdx.l) java.lang.reflect.Proxy.newProxyInstance(Harness.class.getClassLoader(),
                new Class[] {com.badlogic.gdx.l.class}, new java.lang.reflect.InvocationHandler() {
                    public Object invoke(Object o, java.lang.reflect.Method m, Object[] a) {
                        Class<?> r = m.getReturnType();
                        if (r == boolean.class) return false;
                        return null;
                    }
                });
        java.lang.reflect.Field pf = net.fdgames.ek.ExiledKingdoms.class.getDeclaredField("o");
        pf.setAccessible(true);
        pf.set(null, pr);
        String[] steps = {"strings", "rules", "gameworld"};
        try {
            System.out.println("== strings"); net.fdgames.Helpers.GameString.a();
            System.out.println("== rules"); net.fdgames.Rules.Rules.a();
            System.out.println("== gameworld"); net.fdgames.GameWorld.GameWorld.a();
            System.out.println("INIT OK, quests=" + (net.fdgames.GameWorld.GameWorld.a != null));
            File[] cs = new File(root, "data/conversations").listFiles();
            int ok = 0, bad = 0;
            if (cs != null) {
                java.util.Arrays.sort(cs);
                for (File c : cs) {
                    String n = c.getName();
                    if (!n.endsWith(".txt")) continue;
                    try {
                        new net.fdgames.GameLogic.Conversation(n.substring(0, n.length() - 4));
                        ok++;
                    } catch (Throwable t) {
                        bad++;
                        System.out.println("CONV FAIL " + n + ": " + t); if (System.getenv("TRACE") != null) t.printStackTrace(System.out);
                    }
                }
            }
            System.out.println("conversations ok=" + ok + " failed=" + bad);
        } catch (Throwable t) {
            System.out.println("INIT FAILED: " + t);
            t.printStackTrace(System.out);
        }
    }
}
