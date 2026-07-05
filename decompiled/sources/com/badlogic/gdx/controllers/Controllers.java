package com.badlogic.gdx.controllers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.f;
import com.badlogic.gdx.k;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.y;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Controllers {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final y<Application, ControllerManager> f1613a = new y<>();

    public static a<Controller> a() {
        String str;
        y<Application, ControllerManager> yVar = f1613a;
        if (!yVar.a(Gdx.app)) {
            Application.a type = Gdx.app.getType();
            ControllerManager controllerManagerStub = null;
            if (type == Application.a.f1563a) {
                str = "com.badlogic.gdx.controllers.android.AndroidControllers";
            } else if (type == Application.a.f1564b) {
                str = Gdx.graphics.getType() == f.c.f1659b ? "com.badlogic.gdx.controllers.lwjgl3.Lwjgl3ControllerManager" : "com.badlogic.gdx.controllers.desktop.DesktopControllerManager";
            } else if (type == Application.a.f1566d) {
                str = "com.badlogic.gdx.controllers.gwt.GwtControllers";
            } else {
                Gdx.app.log("Controllers", "No controller manager is available for: " + Gdx.app.getType());
                controllerManagerStub = new ControllerManagerStub();
                str = null;
            }
            if (controllerManagerStub == null) {
                try {
                    controllerManagerStub = (ControllerManager) h0.a.f(h0.a.a(str));
                } catch (Throwable th) {
                    throw new m(a.a.A("Error creating controller manager: ", str), th);
                }
            }
            yVar.j(Gdx.app, controllerManagerStub);
            final Application application = Gdx.app;
            application.addLifecycleListener(new k() { // from class: com.badlogic.gdx.controllers.Controllers.1
                @Override // com.badlogic.gdx.k
                public final void dispose() {
                    y<Application, ControllerManager> yVar2 = Controllers.f1613a;
                    yVar2.k(application);
                    Gdx.app.log("Controllers", "removed manager for application, " + yVar2.f2043a + " managers active");
                }

                @Override // com.badlogic.gdx.k
                public final void pause() {
                }

                @Override // com.badlogic.gdx.k
                public final void resume() {
                }
            });
            Gdx.app.log("Controllers", "added manager for application, " + yVar.f2043a + " managers active");
        }
        return yVar.d(Gdx.app).a();
    }
}
