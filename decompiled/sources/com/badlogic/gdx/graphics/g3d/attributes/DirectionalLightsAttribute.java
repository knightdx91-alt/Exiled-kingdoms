package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DirectionalLightsAttribute extends Attribute {
    public static final String Alias = "directionalLights";
    public static final long Type = Attribute.register(Alias);
    public final a<DirectionalLight> lights;

    public DirectionalLightsAttribute() {
        super(Type);
        this.lights = new a<>(true, 1);
    }

    public static final boolean is(long j2) {
        return (Type & j2) == j2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        int iHashCode = super.hashCode();
        a.b<DirectionalLight> it = this.lights.iterator();
        while (it.hasNext()) {
            DirectionalLight next = it.next();
            iHashCode = (iHashCode * 1229) + (next == null ? 0 : next.hashCode());
        }
        return iHashCode;
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        long j2 = this.type;
        long j3 = attribute.type;
        if (j2 != j3) {
            return j2 < j3 ? -1 : 1;
        }
        return 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public DirectionalLightsAttribute copy() {
        return new DirectionalLightsAttribute(this);
    }

    public DirectionalLightsAttribute(DirectionalLightsAttribute directionalLightsAttribute) {
        this();
        this.lights.b(directionalLightsAttribute.lights);
    }
}
