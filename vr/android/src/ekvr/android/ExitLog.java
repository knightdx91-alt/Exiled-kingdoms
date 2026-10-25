package ekvr.android;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.ContentValues;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Why did the game close last time? (deobf/QUEST_VR_SPEC.md §4.1) On the headset there is no
 * PC log to read, so: a Java crash's stack trace is written to the app's files when it happens,
 * and on the next start Android's own record of the last exit (ApplicationExitInfo: crash,
 * native crash, out of memory, "not responding", the game closing itself ...) is collected with
 * it. The report is shown in VR and saved as Download/EK_VR_last_exit.txt.
 */
final class ExitLog {
    static final String TAG = "ekvr";

    /** Record any uncaught Java exception (then let Android handle it as usual). */
    static void install(final Activity a) {
        final Thread.UncaughtExceptionHandler prev = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            try {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                try (FileOutputStream o = new FileOutputStream(new File(a.getFilesDir(), "ek_crash.txt"))) {
                    o.write(("thread " + t.getName() + " at " + new java.util.Date() + "\n" + sw).getBytes("UTF-8"));
                }
            } catch (Throwable ignored) {}
            if (prev != null) prev.uncaughtException(t, e);
        });
    }

    static String reason(int r) {
        switch (r) {
            case 1: return "THE GAME CLOSED ITSELF";
            case 2: return "KILLED BY A SIGNAL";
            case 3: return "OUT OF MEMORY (LOW MEMORY KILL)";
            case 4: return "JAVA CRASH";
            case 5: return "NATIVE CRASH";
            case 6: return "NOT RESPONDING (ANR)";
            case 7: return "STARTUP FAILURE";
            case 9: return "TOO MUCH RESOURCE USE";
            case 10: return "CLOSED BY THE USER";
            case 11: return "STOPPED BY THE USER";
            case 13: return "OTHER";
            case 14: return "FROZEN";
            default: return "REASON " + r;
        }
    }

    /** The report for the last exit, if it was not a normal one and we haven't shown it yet; else null. */
    static String collect(Activity a) {
        StringBuilder sb = new StringBuilder();
        File crash = new File(a.getFilesDir(), "ek_crash.txt");
        File seen = new File(a.getFilesDir(), "ek_exit_seen.txt");
        long seenTs = 0;
        try { seenTs = Long.parseLong(read(new java.io.FileInputStream(seen), 1).trim()); } catch (Throwable ignored) {}
        long newest = seenTs;
        boolean report = false;
        try {
            if (Build.VERSION.SDK_INT >= 30) {
                ActivityManager am = (ActivityManager) a.getSystemService(Activity.ACTIVITY_SERVICE);
                List<ApplicationExitInfo> l = am.getHistoricalProcessExitReasons(a.getPackageName(), 0, 1);
                if (!l.isEmpty()) {
                    ApplicationExitInfo e = l.get(0);
                    int r = e.getReason();
                    if (e.getTimestamp() > seenTs && r != 10 && r != 11 && r != 15 && r != 16) {
                        report = true;
                        newest = e.getTimestamp();
                        sb.append("LAST EXIT: ").append(reason(r)).append('\n');
                        sb.append("WHEN: ").append(new java.util.Date(e.getTimestamp())).append('\n');
                        if (e.getDescription() != null) sb.append("DETAIL: ").append(e.getDescription()).append('\n');
                        sb.append("MEMORY: pss ").append(e.getPss() / 1024).append(" MB, rss ").append(e.getRss() / 1024).append(" MB\n");
                        try (InputStream in = e.getTraceInputStream()) {
                            if (in != null) sb.append("TRACE:\n").append(read(in, 60));
                        } catch (Throwable ignored) {}
                    }
                }
            }
            if (crash.exists() && crash.lastModified() > seenTs) {
                report = true;
                newest = Math.max(newest, crash.lastModified());
                sb.append("JAVA EXCEPTION:\n").append(read(new java.io.FileInputStream(crash), 40));
            }
        } catch (Throwable t) {
            Log.w(TAG, "exit report: " + t);
        }
        if (!report) return null;
        try (FileOutputStream o = new FileOutputStream(seen)) { o.write(Long.toString(newest).getBytes()); } catch (Throwable ignored) {}
        String s = sb.toString();
        Log.e(TAG, "last exit:\n" + s);
        save(a, s);
        return s;
    }

    static String read(InputStream in, int maxLines) throws java.io.IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
            String line;
            for (int n = 0; n < maxLines && (line = r.readLine()) != null; n++) sb.append(line).append('\n');
        }
        return sb.toString();
    }

    /** Download/EK_VR_last_exit.txt (MediaStore: no permission needed), so it can be opened or sent. */
    static void save(Activity a, String s) {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                ContentValues v = new ContentValues();
                v.put(MediaStore.MediaColumns.DISPLAY_NAME, "EK_VR_last_exit.txt");
                v.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");
                v.put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/");
                android.net.Uri u = a.getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, v);
                if (u != null) try (OutputStream o = a.getContentResolver().openOutputStream(u)) { o.write(s.getBytes("UTF-8")); }
            }
        } catch (Throwable t) {
            Log.w(TAG, "can't save exit report: " + t);
        }
    }
}
