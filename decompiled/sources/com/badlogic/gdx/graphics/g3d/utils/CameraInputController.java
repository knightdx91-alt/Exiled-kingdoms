package com.badlogic.gdx.graphics.g3d.utils;

import a0.j;
import a0.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.input.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CameraInputController extends a {
    public int activateKey;
    protected boolean activatePressed;
    public boolean alwaysScroll;
    public boolean autoUpdate;
    public int backwardKey;
    protected boolean backwardPressed;
    protected int button;
    public Camera camera;
    public int forwardButton;
    public int forwardKey;
    protected boolean forwardPressed;
    public boolean forwardTarget;
    protected final CameraGestureListener gestureListener;
    private boolean multiTouch;
    public float pinchZoomFactor;
    public float rotateAngle;
    public int rotateButton;
    public int rotateLeftKey;
    protected boolean rotateLeftPressed;
    public int rotateRightKey;
    protected boolean rotateRightPressed;
    public float scrollFactor;
    public boolean scrollTarget;
    private float startX;
    private float startY;
    public com.badlogic.gdx.math.a target;
    private final com.badlogic.gdx.math.a tmpV1;
    private final com.badlogic.gdx.math.a tmpV2;
    private int touched;
    public int translateButton;
    public boolean translateTarget;
    public float translateUnits;

    protected static class CameraGestureListener extends a.b {
        public CameraInputController controller;
        private float previousZoom;

        protected CameraGestureListener() {
        }

        @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
        public boolean fling(float f2, float f3, int i2) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
        public boolean longPress(float f2, float f3) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
        public boolean pan(float f2, float f3, float f4, float f5) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
        public boolean pinch(q qVar, q qVar2, q qVar3, q qVar4) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
        public boolean tap(float f2, float f3, int i2, int i3) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
        public boolean touchDown(float f2, float f3, int i2, int i3) {
            this.previousZoom = 0.0f;
            return false;
        }

        @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
        public boolean zoom(float f2, float f3) {
            float f4 = f3 - f2;
            float f5 = f4 - this.previousZoom;
            this.previousZoom = f4;
            float width = Gdx.graphics.getWidth();
            float height = Gdx.graphics.getHeight();
            CameraInputController cameraInputController = this.controller;
            if (width > height) {
                width = height;
            }
            return cameraInputController.pinchZoom(f5 / width);
        }
    }

    protected CameraInputController(CameraGestureListener cameraGestureListener, Camera camera) {
        super(cameraGestureListener);
        this.rotateButton = 0;
        this.rotateAngle = 360.0f;
        this.translateButton = 1;
        this.translateUnits = 10.0f;
        this.forwardButton = 2;
        this.activateKey = 0;
        this.alwaysScroll = true;
        this.scrollFactor = -0.1f;
        this.pinchZoomFactor = 10.0f;
        this.autoUpdate = true;
        this.target = new com.badlogic.gdx.math.a();
        this.translateTarget = true;
        this.forwardTarget = true;
        this.scrollTarget = false;
        this.forwardKey = 51;
        this.backwardKey = 47;
        this.rotateRightKey = 29;
        this.rotateLeftKey = 32;
        this.button = -1;
        this.tmpV1 = new com.badlogic.gdx.math.a();
        this.tmpV2 = new com.badlogic.gdx.math.a();
        this.gestureListener = cameraGestureListener;
        cameraGestureListener.controller = this;
        this.camera = camera;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean keyDown(int i2) {
        if (i2 == this.activateKey) {
            this.activatePressed = true;
        }
        if (i2 == this.forwardKey) {
            this.forwardPressed = true;
            return false;
        }
        if (i2 == this.backwardKey) {
            this.backwardPressed = true;
            return false;
        }
        if (i2 == this.rotateRightKey) {
            this.rotateRightPressed = true;
            return false;
        }
        if (i2 != this.rotateLeftKey) {
            return false;
        }
        this.rotateLeftPressed = true;
        return false;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean keyUp(int i2) {
        if (i2 == this.activateKey) {
            this.activatePressed = false;
            this.button = -1;
        }
        if (i2 == this.forwardKey) {
            this.forwardPressed = false;
        } else if (i2 == this.backwardKey) {
            this.backwardPressed = false;
        } else if (i2 == this.rotateRightKey) {
            this.rotateRightPressed = false;
        } else if (i2 == this.rotateLeftKey) {
            this.rotateLeftPressed = false;
        }
        return false;
    }

    protected boolean pinchZoom(float f2) {
        return zoom(this.pinchZoomFactor * f2);
    }

    protected boolean process(float f2, float f3, int i2) {
        if (i2 == this.rotateButton) {
            com.badlogic.gdx.math.a aVar = this.tmpV1;
            aVar.u(this.camera.direction);
            aVar.d(this.camera.up);
            aVar.f1730b = 0.0f;
            Camera camera = this.camera;
            com.badlogic.gdx.math.a aVar2 = this.target;
            com.badlogic.gdx.math.a aVar3 = this.tmpV1;
            aVar3.n();
            camera.rotateAround(aVar2, aVar3, f3 * this.rotateAngle);
            this.camera.rotateAround(this.target, com.badlogic.gdx.math.a.f1726e, f2 * (-this.rotateAngle));
        } else if (i2 == this.translateButton) {
            Camera camera2 = this.camera;
            com.badlogic.gdx.math.a aVar4 = this.tmpV1;
            aVar4.u(camera2.direction);
            aVar4.d(this.camera.up);
            aVar4.n();
            aVar4.s((-f2) * this.translateUnits);
            camera2.translate(aVar4);
            Camera camera3 = this.camera;
            com.badlogic.gdx.math.a aVar5 = this.tmpV2;
            aVar5.u(camera3.up);
            aVar5.s((-f3) * this.translateUnits);
            camera3.translate(aVar5);
            if (this.translateTarget) {
                com.badlogic.gdx.math.a aVar6 = this.target;
                aVar6.b(this.tmpV1);
                aVar6.b(this.tmpV2);
            }
        } else if (i2 == this.forwardButton) {
            Camera camera4 = this.camera;
            com.badlogic.gdx.math.a aVar7 = this.tmpV1;
            aVar7.u(camera4.direction);
            aVar7.s(f3 * this.translateUnits);
            camera4.translate(aVar7);
            if (this.forwardTarget) {
                this.target.b(this.tmpV1);
            }
        }
        if (!this.autoUpdate) {
            return true;
        }
        this.camera.update();
        return true;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean scrolled(float f2, float f3) {
        return zoom(f3 * this.scrollFactor * this.translateUnits);
    }

    @Override // com.badlogic.gdx.input.a, com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchDown(int i2, int i3, int i4, int i5) {
        int i6 = this.touched | (1 << i4);
        this.touched = i6;
        boolean zE = j.e(i6);
        this.multiTouch = !zE;
        if (!zE) {
            this.button = -1;
        } else if (this.button < 0 && (this.activateKey == 0 || this.activatePressed)) {
            this.startX = i2;
            this.startY = i3;
            this.button = i5;
        }
        return super.touchDown(i2, i3, i4, i5) || this.activateKey == 0 || this.activatePressed;
    }

    @Override // com.badlogic.gdx.input.a, com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchDragged(int i2, int i3, int i4) {
        boolean z2 = super.touchDragged(i2, i3, i4);
        if (z2 || this.button < 0) {
            return z2;
        }
        float f2 = i2;
        float width = (f2 - this.startX) / Gdx.graphics.getWidth();
        float f3 = i3;
        float height = (this.startY - f3) / Gdx.graphics.getHeight();
        this.startX = f2;
        this.startY = f3;
        return process(width, height, this.button);
    }

    @Override // com.badlogic.gdx.input.a, com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchUp(int i2, int i3, int i4, int i5) {
        this.touched = this.touched & ((1 << i4) ^ (-1));
        this.multiTouch = !j.e(r0);
        if (i5 == this.button) {
            this.button = -1;
        }
        return super.touchUp(i2, i3, i4, i5) || this.activatePressed;
    }

    public void update() {
        if (this.rotateRightPressed || this.rotateLeftPressed || this.forwardPressed || this.backwardPressed) {
            float deltaTime = Gdx.graphics.getDeltaTime();
            if (this.rotateRightPressed) {
                Camera camera = this.camera;
                camera.rotate(camera.up, (-deltaTime) * this.rotateAngle);
            }
            if (this.rotateLeftPressed) {
                Camera camera2 = this.camera;
                camera2.rotate(camera2.up, this.rotateAngle * deltaTime);
            }
            if (this.forwardPressed) {
                Camera camera3 = this.camera;
                com.badlogic.gdx.math.a aVar = this.tmpV1;
                aVar.u(camera3.direction);
                aVar.s(this.translateUnits * deltaTime);
                camera3.translate(aVar);
                if (this.forwardTarget) {
                    this.target.b(this.tmpV1);
                }
            }
            if (this.backwardPressed) {
                Camera camera4 = this.camera;
                com.badlogic.gdx.math.a aVar2 = this.tmpV1;
                aVar2.u(camera4.direction);
                aVar2.s((-deltaTime) * this.translateUnits);
                camera4.translate(aVar2);
                if (this.forwardTarget) {
                    this.target.b(this.tmpV1);
                }
            }
            if (this.autoUpdate) {
                this.camera.update();
            }
        }
    }

    public boolean zoom(float f2) {
        if (!this.alwaysScroll && this.activateKey != 0 && !this.activatePressed) {
            return false;
        }
        Camera camera = this.camera;
        com.badlogic.gdx.math.a aVar = this.tmpV1;
        aVar.u(camera.direction);
        aVar.s(f2);
        camera.translate(aVar);
        if (this.scrollTarget) {
            this.target.b(this.tmpV1);
        }
        if (!this.autoUpdate) {
            return true;
        }
        this.camera.update();
        return true;
    }

    public CameraInputController(Camera camera) {
        this(new CameraGestureListener(), camera);
    }
}
