package com.badlogic.gdx.graphics.g3d.particles;

import a0.k;
import a0.n;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.t;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class ParticleControllerComponent implements i, Json.b, ResourceData.Configurable {
    protected ParticleController controller;
    protected static final a TMP_V1 = new a();
    protected static final a TMP_V2 = new a();
    protected static final a TMP_V3 = new a();
    protected static final a TMP_V4 = new a();
    protected static final a TMP_V5 = new a();
    protected static final a TMP_V6 = new a();
    protected static final n TMP_Q = new n();
    protected static final n TMP_Q2 = new n();
    protected static final k TMP_M3 = new k();
    protected static final Matrix4 TMP_M4 = new Matrix4();

    public void activateParticles(int i2, int i3) {
    }

    public void allocateChannels() {
    }

    public abstract ParticleControllerComponent copy();

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
    }

    public void end() {
    }

    public void init() {
    }

    public void killParticles(int i2, int i3) {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
    }

    @Override // com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
    }

    public void set(ParticleController particleController) {
        this.controller = particleController;
    }

    public void start() {
    }

    public void update() {
    }

    @Override // com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
    }
}
