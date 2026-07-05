package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PointLightsAttribute extends Attribute {
    public static final String Alias = "pointLights";
    public static final long Type = Attribute.register(Alias);
    public final a<PointLight> lights;

    public PointLightsAttribute() {
        super(Type);
        this.lights = new a<>(true, 1);
    }

    public static final boolean is(long j2) {
        return (Type & j2) == j2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        int iHashCode = super.hashCode();
        a.b<PointLight> it = this.lights.iterator();
        while (it.hasNext()) {
            PointLight next = it.next();
            iHashCode = (iHashCode * 1231) + (next == null ? 0 : next.hashCode());
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
    public PointLightsAttribute copy() {
        return new PointLightsAttribute(this);
    }

    public PointLightsAttribute(PointLightsAttribute pointLightsAttribute) {
        this();
        this.lights.b(pointLightsAttribute.lights);
    }
}
