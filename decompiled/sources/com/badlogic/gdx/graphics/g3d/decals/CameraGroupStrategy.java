package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.y;
import java.util.Comparator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CameraGroupStrategy implements GroupStrategy, i {
    private static final int GROUP_BLEND = 1;
    private static final int GROUP_OPAQUE = 0;
    c0<a<Decal>> arrayPool;
    Camera camera;
    private final Comparator<Decal> cameraSorter;
    y<DecalMaterial, a<Decal>> materialGroups;
    ShaderProgram shader;
    a<a<Decal>> usedArrays;

    public CameraGroupStrategy(final Camera camera) {
        this(camera, new Comparator<Decal>() { // from class: com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy.2
            @Override // java.util.Comparator
            public int compare(Decal decal, Decal decal2) {
                return (int) Math.signum(camera.position.e(decal2.position) - camera.position.e(decal.position));
            }
        });
    }

    private void createDefaultShader() {
        ShaderProgram shaderProgram = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projectionViewMatrix;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projectionViewMatrix * a_position;\n}\n", "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}");
        this.shader = shaderProgram;
        if (shaderProgram.isCompiled()) {
            return;
        }
        throw new IllegalArgumentException("couldn't compile shader: " + this.shader.getLog());
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroup(int i2) {
        if (i2 == 1) {
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroups() {
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroup(int i2, a<Decal> aVar) {
        if (i2 == 1) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            aVar.sort(this.cameraSorter);
            return;
        }
        int i3 = aVar.f1750b;
        for (int i4 = 0; i4 < i3; i4++) {
            Decal decal = aVar.get(i4);
            a<Decal> aVarD = this.materialGroups.d(decal.material);
            if (aVarD == null) {
                aVarD = this.arrayPool.obtain();
                aVarD.clear();
                this.usedArrays.a(aVarD);
                this.materialGroups.j(decal.material, aVarD);
            }
            aVarD.a(decal);
        }
        aVar.clear();
        y.e<a<Decal>> eVarN = this.materialGroups.n();
        eVarN.getClass();
        while (eVarN.hasNext()) {
            aVar.b(eVarN.next());
        }
        this.materialGroups.clear();
        this.arrayPool.freeAll(this.usedArrays);
        this.usedArrays.clear();
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroups() {
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        this.shader.bind();
        this.shader.setUniformMatrix("u_projectionViewMatrix", this.camera.combined);
        this.shader.setUniformi("u_texture", 0);
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public int decideGroup(Decal decal) {
        return !decal.getMaterial().isOpaque() ? 1 : 0;
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        ShaderProgram shaderProgram = this.shader;
        if (shaderProgram != null) {
            shaderProgram.dispose();
        }
    }

    public Camera getCamera() {
        return this.camera;
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public ShaderProgram getGroupShader(int i2) {
        return this.shader;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public CameraGroupStrategy(Camera camera, Comparator<Decal> comparator) {
        this.arrayPool = new c0<a<Decal>>(16) { // from class: com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.c0
            public a<Decal> newObject() {
                return new a<>();
            }
        };
        this.usedArrays = new a<>();
        this.materialGroups = new y<>();
        this.camera = camera;
        this.cameraSorter = comparator;
        createDefaultShader();
    }
}
