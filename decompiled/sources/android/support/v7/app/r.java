package android.support.v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: compiled from: TwilightManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static r f799d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f800a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final LocationManager f801b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f802c = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f803a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f804b;
    }

    r(Context context, LocationManager locationManager) {
        this.f800a = context;
        this.f801b = locationManager;
    }

    static r a(Context context) {
        if (f799d == null) {
            Context applicationContext = context.getApplicationContext();
            f799d = new r(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f799d;
    }

    final boolean b() {
        Location location;
        long j2;
        a aVar = this.f802c;
        if (aVar.f804b > System.currentTimeMillis()) {
            return aVar.f803a;
        }
        Context context = this.f800a;
        int iF = com.badlogic.gdx.utils.l.f(context, "android.permission.ACCESS_COARSE_LOCATION");
        LocationManager locationManager = this.f801b;
        Location lastKnownLocation = null;
        if (iF == 0) {
            try {
            } catch (Exception e2) {
                Log.d("TwilightManager", "Failed to get last known location", e2);
            }
            Location lastKnownLocation2 = locationManager.isProviderEnabled("network") ? locationManager.getLastKnownLocation("network") : null;
            location = lastKnownLocation2;
        } else {
            location = null;
        }
        if (com.badlogic.gdx.utils.l.f(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            try {
                if (locationManager.isProviderEnabled("gps")) {
                    lastKnownLocation = locationManager.getLastKnownLocation("gps");
                }
            } catch (Exception e3) {
                Log.d("TwilightManager", "Failed to get last known location", e3);
            }
        }
        if (lastKnownLocation == null || location == null ? lastKnownLocation != null : lastKnownLocation.getTime() > location.getTime()) {
            location = lastKnownLocation;
        }
        if (location == null) {
            Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
            int i2 = Calendar.getInstance().get(11);
            return i2 < 6 || i2 >= 22;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        q qVarB = q.b();
        qVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        qVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z2 = qVarB.f798c == 1;
        long j3 = qVarB.f797b;
        long j4 = qVarB.f796a;
        qVarB.a(jCurrentTimeMillis + 86400000, location.getLatitude(), location.getLongitude());
        long j5 = qVarB.f797b;
        if (j3 == -1 || j4 == -1) {
            j2 = jCurrentTimeMillis + 43200000;
        } else {
            if (jCurrentTimeMillis <= j4) {
                j5 = jCurrentTimeMillis > j3 ? j4 : j3;
            }
            j2 = j5 + 60000;
        }
        aVar.f803a = z2;
        aVar.f804b = j2;
        return z2;
    }
}
