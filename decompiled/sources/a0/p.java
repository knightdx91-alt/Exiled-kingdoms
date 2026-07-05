package a0;

import java.io.Serializable;

/* JADX INFO: compiled from: Rectangle.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class p implements Serializable {
    private static final long serialVersionUID = 5733252015138115702L;
    public static final p tmp = new p();
    public static final p tmp2 = new p();
    public float height;
    public float width;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f89x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f90y;

    public p() {
    }

    public float area() {
        return this.width * this.height;
    }

    public boolean contains(float f2, float f3) {
        float f4 = this.f89x;
        if (f4 <= f2 && f4 + this.width >= f2) {
            float f5 = this.f90y;
            if (f5 <= f3 && f5 + this.height >= f3) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        p pVar = (p) obj;
        return Float.floatToRawIntBits(this.height) == Float.floatToRawIntBits(pVar.height) && Float.floatToRawIntBits(this.width) == Float.floatToRawIntBits(pVar.width) && Float.floatToRawIntBits(this.f89x) == Float.floatToRawIntBits(pVar.f89x) && Float.floatToRawIntBits(this.f90y) == Float.floatToRawIntBits(pVar.f90y);
    }

    public p fitInside(p pVar) {
        float aspectRatio = getAspectRatio();
        if (aspectRatio < pVar.getAspectRatio()) {
            float f2 = pVar.height;
            setSize(aspectRatio * f2, f2);
        } else {
            float f3 = pVar.width;
            setSize(f3, f3 / aspectRatio);
        }
        setPosition(((pVar.width / 2.0f) + pVar.f89x) - (this.width / 2.0f), ((pVar.height / 2.0f) + pVar.f90y) - (this.height / 2.0f));
        return this;
    }

    public p fitOutside(p pVar) {
        float aspectRatio = getAspectRatio();
        if (aspectRatio > pVar.getAspectRatio()) {
            float f2 = pVar.height;
            setSize(aspectRatio * f2, f2);
        } else {
            float f3 = pVar.width;
            setSize(f3, f3 / aspectRatio);
        }
        setPosition(((pVar.width / 2.0f) + pVar.f89x) - (this.width / 2.0f), ((pVar.height / 2.0f) + pVar.f90y) - (this.height / 2.0f));
        return this;
    }

    public p fromString(String str) {
        int iIndexOf = str.indexOf(44, 1);
        int i2 = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(44, i2);
        int i3 = iIndexOf2 + 1;
        int iIndexOf3 = str.indexOf(44, i3);
        if (iIndexOf != -1 && iIndexOf2 != -1 && iIndexOf3 != -1 && str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']') {
            try {
                return set(Float.parseFloat(str.substring(1, iIndexOf)), Float.parseFloat(str.substring(i2, iIndexOf2)), Float.parseFloat(str.substring(i3, iIndexOf3)), Float.parseFloat(str.substring(iIndexOf3 + 1, str.length() - 1)));
            } catch (NumberFormatException unused) {
            }
        }
        throw new com.badlogic.gdx.utils.m("Malformed Rectangle: ".concat(str));
    }

    public float getAspectRatio() {
        float f2 = this.height;
        if (f2 == 0.0f) {
            return Float.NaN;
        }
        return this.width / f2;
    }

    public q getCenter(q qVar) {
        qVar.f91a = (this.width / 2.0f) + this.f89x;
        qVar.f92b = (this.height / 2.0f) + this.f90y;
        return qVar;
    }

    public float getHeight() {
        return this.height;
    }

    public q getPosition(q qVar) {
        float f2 = this.f89x;
        float f3 = this.f90y;
        qVar.f91a = f2;
        qVar.f92b = f3;
        return qVar;
    }

    public q getSize(q qVar) {
        float f2 = this.width;
        float f3 = this.height;
        qVar.f91a = f2;
        qVar.f92b = f3;
        return qVar;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.f89x;
    }

    public float getY() {
        return this.f90y;
    }

    public int hashCode() {
        return Float.floatToRawIntBits(this.f90y) + ((Float.floatToRawIntBits(this.f89x) + ((Float.floatToRawIntBits(this.width) + ((Float.floatToRawIntBits(this.height) + 31) * 31)) * 31)) * 31);
    }

    public p merge(p pVar) {
        float fMin = Math.min(this.f89x, pVar.f89x);
        float fMax = Math.max(this.f89x + this.width, pVar.f89x + pVar.width);
        this.f89x = fMin;
        this.width = fMax - fMin;
        float fMin2 = Math.min(this.f90y, pVar.f90y);
        float fMax2 = Math.max(this.f90y + this.height, pVar.f90y + pVar.height);
        this.f90y = fMin2;
        this.height = fMax2 - fMin2;
        return this;
    }

    public boolean overlaps(p pVar) {
        float f2 = this.f89x;
        float f3 = pVar.f89x;
        if (f2 < pVar.width + f3 && f2 + this.width > f3) {
            float f4 = this.f90y;
            float f5 = pVar.f90y;
            if (f4 < pVar.height + f5 && f4 + this.height > f5) {
                return true;
            }
        }
        return false;
    }

    public float perimeter() {
        return (this.width + this.height) * 2.0f;
    }

    public p set(float f2, float f3, float f4, float f5) {
        this.f89x = f2;
        this.f90y = f3;
        this.width = f4;
        this.height = f5;
        return this;
    }

    public p setCenter(float f2, float f3) {
        setPosition(f2 - (this.width / 2.0f), f3 - (this.height / 2.0f));
        return this;
    }

    public p setHeight(float f2) {
        this.height = f2;
        return this;
    }

    public p setPosition(q qVar) {
        this.f89x = qVar.f91a;
        this.f90y = qVar.f92b;
        return this;
    }

    public p setSize(float f2, float f3) {
        this.width = f2;
        this.height = f3;
        return this;
    }

    public p setWidth(float f2) {
        this.width = f2;
        return this;
    }

    public p setX(float f2) {
        this.f89x = f2;
        return this;
    }

    public p setY(float f2) {
        this.f90y = f2;
        return this;
    }

    public String toString() {
        return "[" + this.f89x + "," + this.f90y + "," + this.width + "," + this.height + "]";
    }

    public p(float f2, float f3, float f4, float f5) {
        this.f89x = f2;
        this.f90y = f3;
        this.width = f4;
        this.height = f5;
    }

    public boolean contains(q qVar) {
        return contains(qVar.f91a, qVar.f92b);
    }

    public p setCenter(q qVar) {
        setPosition(qVar.f91a - (this.width / 2.0f), qVar.f92b - (this.height / 2.0f));
        return this;
    }

    public boolean contains(b bVar) {
        float f2 = bVar.f31a;
        float f3 = bVar.f33c;
        float f4 = f2 - f3;
        float f5 = this.f89x;
        if (f4 >= f5 && f2 + f3 <= f5 + this.width) {
            float f6 = bVar.f32b;
            float f7 = f6 - f3;
            float f8 = this.f90y;
            if (f7 >= f8 && f6 + f3 <= f8 + this.height) {
                return true;
            }
        }
        return false;
    }

    public p setPosition(float f2, float f3) {
        this.f89x = f2;
        this.f90y = f3;
        return this;
    }

    public p setSize(float f2) {
        this.width = f2;
        this.height = f2;
        return this;
    }

    public boolean contains(p pVar) {
        float f2 = pVar.f89x;
        float f3 = pVar.width + f2;
        float f4 = pVar.f90y;
        float f5 = pVar.height + f4;
        float f6 = this.f89x;
        if (f2 > f6) {
            float f7 = this.width;
            if (f2 < f6 + f7 && f3 > f6 && f3 < f6 + f7) {
                float f8 = this.f90y;
                if (f4 > f8) {
                    float f9 = this.height;
                    if (f4 < f8 + f9 && f5 > f8 && f5 < f8 + f9) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public p set(p pVar) {
        this.f89x = pVar.f89x;
        this.f90y = pVar.f90y;
        this.width = pVar.width;
        this.height = pVar.height;
        return this;
    }

    public p(p pVar) {
        this.f89x = pVar.f89x;
        this.f90y = pVar.f90y;
        this.width = pVar.width;
        this.height = pVar.height;
    }

    public p merge(float f2, float f3) {
        float fMin = Math.min(this.f89x, f2);
        float fMax = Math.max(this.f89x + this.width, f2);
        this.f89x = fMin;
        this.width = fMax - fMin;
        float fMin2 = Math.min(this.f90y, f3);
        float fMax2 = Math.max(this.f90y + this.height, f3);
        this.f90y = fMin2;
        this.height = fMax2 - fMin2;
        return this;
    }

    public p merge(q qVar) {
        return merge(qVar.f91a, qVar.f92b);
    }

    public p merge(q[] qVarArr) {
        float fMin = this.f89x;
        float fMax = this.width + fMin;
        float fMin2 = this.f90y;
        float fMax2 = this.height + fMin2;
        for (q qVar : qVarArr) {
            fMin = Math.min(fMin, qVar.f91a);
            fMax = Math.max(fMax, qVar.f91a);
            fMin2 = Math.min(fMin2, qVar.f92b);
            fMax2 = Math.max(fMax2, qVar.f92b);
        }
        this.f89x = fMin;
        this.width = fMax - fMin;
        this.f90y = fMin2;
        this.height = fMax2 - fMin2;
        return this;
    }
}
