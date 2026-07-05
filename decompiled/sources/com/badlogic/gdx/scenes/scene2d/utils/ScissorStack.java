package com.badlogic.gdx.scenes.scene2d.utils;

import a0.p;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ScissorStack {
    private static a<p> scissors = new a<>();
    static com.badlogic.gdx.math.a tmp = new com.badlogic.gdx.math.a();
    static final p viewport = new p();

    public static void calculateScissors(Camera camera, Matrix4 matrix4, p pVar, p pVar2) {
        calculateScissors(camera, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), matrix4, pVar, pVar2);
    }

    private static void fix(p pVar) {
        pVar.f89x = Math.round(pVar.f89x);
        pVar.f90y = Math.round(pVar.f90y);
        pVar.width = Math.round(pVar.width);
        float fRound = Math.round(pVar.height);
        pVar.height = fRound;
        float f2 = pVar.width;
        if (f2 < 0.0f) {
            float f3 = -f2;
            pVar.width = f3;
            pVar.f89x -= f3;
        }
        if (fRound < 0.0f) {
            float f4 = -fRound;
            pVar.height = f4;
            pVar.f90y -= f4;
        }
    }

    public static p getViewport() {
        a<p> aVar = scissors;
        if (aVar.f1750b == 0) {
            p pVar = viewport;
            pVar.set(0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            return pVar;
        }
        p pVarK = aVar.k();
        p pVar2 = viewport;
        pVar2.set(pVarK);
        return pVar2;
    }

    public static p peekScissors() {
        a<p> aVar = scissors;
        if (aVar.f1750b == 0) {
            return null;
        }
        return aVar.k();
    }

    public static p popScissors() {
        p pVarL = scissors.l();
        a<p> aVar = scissors;
        if (aVar.f1750b == 0) {
            Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
        } else {
            p pVarK = aVar.k();
            HdpiUtils.glScissor((int) pVarK.f89x, (int) pVarK.f90y, (int) pVarK.width, (int) pVarK.height);
        }
        return pVarL;
    }

    public static boolean pushScissors(p pVar) {
        fix(pVar);
        a<p> aVar = scissors;
        int i2 = aVar.f1750b;
        if (i2 != 0) {
            p pVar2 = aVar.get(i2 - 1);
            float fMax = Math.max(pVar2.f89x, pVar.f89x);
            float fMin = Math.min(pVar2.f89x + pVar2.width, pVar.f89x + pVar.width) - fMax;
            if (fMin < 1.0f) {
                return false;
            }
            float fMax2 = Math.max(pVar2.f90y, pVar.f90y);
            float fMin2 = Math.min(pVar2.f90y + pVar2.height, pVar.f90y + pVar.height) - fMax2;
            if (fMin2 < 1.0f) {
                return false;
            }
            pVar.f89x = fMax;
            pVar.f90y = fMax2;
            pVar.width = fMin;
            pVar.height = Math.max(1.0f, fMin2);
        } else {
            if (pVar.width < 1.0f || pVar.height < 1.0f) {
                return false;
            }
            Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        }
        scissors.a(pVar);
        HdpiUtils.glScissor((int) pVar.f89x, (int) pVar.f90y, (int) pVar.width, (int) pVar.height);
        return true;
    }

    public static void calculateScissors(Camera camera, float f2, float f3, float f4, float f5, Matrix4 matrix4, p pVar, p pVar2) {
        tmp.t(pVar.f89x, pVar.f90y, 0.0f);
        tmp.m(matrix4);
        camera.project(tmp, f2, f3, f4, f5);
        com.badlogic.gdx.math.a aVar = tmp;
        pVar2.f89x = aVar.f1729a;
        pVar2.f90y = aVar.f1730b;
        aVar.t(pVar.f89x + pVar.width, pVar.f90y + pVar.height, 0.0f);
        tmp.m(matrix4);
        camera.project(tmp, f2, f3, f4, f5);
        com.badlogic.gdx.math.a aVar2 = tmp;
        pVar2.width = aVar2.f1729a - pVar2.f89x;
        pVar2.height = aVar2.f1730b - pVar2.f90y;
    }
}
