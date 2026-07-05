package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.g3d.attributes.DirectionalLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.PointLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.SpotLightsAttribute;
import com.badlogic.gdx.graphics.g3d.environment.BaseLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.environment.ShadowMap;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Environment extends Attributes {
    public ShadowMap shadowMap;

    public Environment add(BaseLight... baseLightArr) {
        for (BaseLight baseLight : baseLightArr) {
            add(baseLight);
        }
        return this;
    }

    public Environment remove(BaseLight... baseLightArr) {
        for (BaseLight baseLight : baseLightArr) {
            remove(baseLight);
        }
        return this;
    }

    public Environment add(a<BaseLight> aVar) {
        a.b<BaseLight> it = aVar.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return this;
    }

    public Environment remove(a<BaseLight> aVar) {
        a.b<BaseLight> it = aVar.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
        return this;
    }

    public Environment add(BaseLight baseLight) {
        if (baseLight instanceof DirectionalLight) {
            add((DirectionalLight) baseLight);
        } else if (baseLight instanceof PointLight) {
            add((PointLight) baseLight);
        } else if (baseLight instanceof SpotLight) {
            add((SpotLight) baseLight);
        } else {
            throw new m("Unknown light type");
        }
        return this;
    }

    public Environment remove(BaseLight baseLight) {
        if (baseLight instanceof DirectionalLight) {
            remove((DirectionalLight) baseLight);
        } else if (baseLight instanceof PointLight) {
            remove((PointLight) baseLight);
        } else if (baseLight instanceof SpotLight) {
            remove((SpotLight) baseLight);
        } else {
            throw new m("Unknown light type");
        }
        return this;
    }

    public Environment add(DirectionalLight directionalLight) {
        DirectionalLightsAttribute directionalLightsAttribute = (DirectionalLightsAttribute) get(DirectionalLightsAttribute.Type);
        if (directionalLightsAttribute == null) {
            directionalLightsAttribute = new DirectionalLightsAttribute();
            set(directionalLightsAttribute);
        }
        directionalLightsAttribute.lights.a(directionalLight);
        return this;
    }

    public Environment remove(DirectionalLight directionalLight) {
        long j2 = DirectionalLightsAttribute.Type;
        if (has(j2)) {
            DirectionalLightsAttribute directionalLightsAttribute = (DirectionalLightsAttribute) get(j2);
            directionalLightsAttribute.lights.q(directionalLight, false);
            if (directionalLightsAttribute.lights.f1750b == 0) {
                remove(j2);
            }
        }
        return this;
    }

    public Environment add(PointLight pointLight) {
        PointLightsAttribute pointLightsAttribute = (PointLightsAttribute) get(PointLightsAttribute.Type);
        if (pointLightsAttribute == null) {
            pointLightsAttribute = new PointLightsAttribute();
            set(pointLightsAttribute);
        }
        pointLightsAttribute.lights.a(pointLight);
        return this;
    }

    public Environment remove(PointLight pointLight) {
        long j2 = PointLightsAttribute.Type;
        if (has(j2)) {
            PointLightsAttribute pointLightsAttribute = (PointLightsAttribute) get(j2);
            pointLightsAttribute.lights.q(pointLight, false);
            if (pointLightsAttribute.lights.f1750b == 0) {
                remove(j2);
            }
        }
        return this;
    }

    public Environment add(SpotLight spotLight) {
        SpotLightsAttribute spotLightsAttribute = (SpotLightsAttribute) get(SpotLightsAttribute.Type);
        if (spotLightsAttribute == null) {
            spotLightsAttribute = new SpotLightsAttribute();
            set(spotLightsAttribute);
        }
        spotLightsAttribute.lights.a(spotLight);
        return this;
    }

    public Environment remove(SpotLight spotLight) {
        long j2 = SpotLightsAttribute.Type;
        if (has(j2)) {
            SpotLightsAttribute spotLightsAttribute = (SpotLightsAttribute) get(j2);
            spotLightsAttribute.lights.q(spotLight, false);
            if (spotLightsAttribute.lights.f1750b == 0) {
                remove(j2);
            }
        }
        return this;
    }
}
