package ekvr;

/** Column-major 4x4 float matrices (OpenGL layout), without the APK's obfuscated Matrix4 API. */
public final class Mat {
    public static float[] id() { float[] m = new float[16]; m[0] = m[5] = m[10] = m[15] = 1; return m; }

    public static float[] mul(float[] a, float[] b) {
        float[] r = new float[16];
        for (int c = 0; c < 4; c++)
            for (int rI = 0; rI < 4; rI++) {
                float s = 0;
                for (int k = 0; k < 4; k++) s += a[k * 4 + rI] * b[c * 4 + k];
                r[c * 4 + rI] = s;
            }
        return r;
    }

    public static float[] perspective(float fovyDeg, float aspect, float near, float far) {
        float f = (float) (1 / Math.tan(Math.toRadians(fovyDeg) / 2));
        float[] m = new float[16];
        m[0] = f / aspect; m[5] = f;
        m[10] = (far + near) / (near - far); m[11] = -1;
        m[14] = 2 * far * near / (near - far);
        return m;
    }

    /** Asymmetric frustum from OpenXR-style half-angle tangents. */
    public static float[] frustum(float tanL, float tanR, float tanU, float tanD, float near, float far) {
        float w = tanR - tanL, h = tanU - tanD;
        float[] m = new float[16];
        m[0] = 2 / w; m[5] = 2 / h;
        m[8] = (tanR + tanL) / w; m[9] = (tanU + tanD) / h;
        m[10] = -(far + near) / (far - near); m[11] = -1;
        m[14] = -2 * far * near / (far - near);
        return m;
    }

    public static float[] lookAt(float ex, float ey, float ez, float cx, float cy, float cz) {
        float fx = cx - ex, fy = cy - ey, fz = cz - ez;
        float fl = (float) Math.sqrt(fx * fx + fy * fy + fz * fz);
        fx /= fl; fy /= fl; fz /= fl;
        float sx = fy * 0 - fz * 1, sy = fz * 0 - fx * 0, sz = fx * 1 - fy * 0;   // f x up(0,1,0)
        float sl = (float) Math.sqrt(sx * sx + sy * sy + sz * sz);
        sx /= sl; sy /= sl; sz /= sl;
        float ux = sy * fz - sz * fy, uy = sz * fx - sx * fz, uz = sx * fy - sy * fx;
        float[] m = id();
        m[0] = sx; m[4] = sy; m[8] = sz;
        m[1] = ux; m[5] = uy; m[9] = uz;
        m[2] = -fx; m[6] = -fy; m[10] = -fz;
        m[12] = -(sx * ex + sy * ey + sz * ez);
        m[13] = -(ux * ex + uy * ey + uz * ez);
        m[14] = fx * ex + fy * ey + fz * ez;
        return m;
    }

    /** Rigid pose (quaternion x,y,z,w + position) -> matrix. */
    public static float[] pose(float qx, float qy, float qz, float qw, float px, float py, float pz) {
        float[] m = id();
        m[0] = 1 - 2 * (qy * qy + qz * qz); m[1] = 2 * (qx * qy + qz * qw); m[2] = 2 * (qx * qz - qy * qw);
        m[4] = 2 * (qx * qy - qz * qw); m[5] = 1 - 2 * (qx * qx + qz * qz); m[6] = 2 * (qy * qz + qx * qw);
        m[8] = 2 * (qx * qz + qy * qw); m[9] = 2 * (qy * qz - qx * qw); m[10] = 1 - 2 * (qx * qx + qy * qy);
        m[12] = px; m[13] = py; m[14] = pz;
        return m;
    }

    /** Inverse of a rigid (rotation + translation) matrix. */
    public static float[] rigidInverse(float[] m) {
        float[] r = id();
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) r[i * 4 + j] = m[j * 4 + i];
        for (int i = 0; i < 3; i++) r[12 + i] = -(r[i] * m[12] + r[4 + i] * m[13] + r[8 + i] * m[14]);
        return r;
    }

    public static float[] translate(float x, float y, float z) { float[] m = id(); m[12] = x; m[13] = y; m[14] = z; return m; }
    public static float[] scale(float s) { float[] m = id(); m[0] = m[5] = m[10] = s; return m; }
    public static float[] rotY(float rad) {
        float[] m = id(); float c = (float) Math.cos(rad), s = (float) Math.sin(rad);
        m[0] = c; m[2] = -s; m[8] = s; m[10] = c; return m;
    }
    public static float[] xform(float[] m, float x, float y, float z) {
        return new float[]{m[0] * x + m[4] * y + m[8] * z + m[12], m[1] * x + m[5] * y + m[9] * z + m[13], m[2] * x + m[6] * y + m[10] * z + m[14]};
    }
}
