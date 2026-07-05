package com.badlogic.gdx.scenes.scene2d.ui;

import a0.q;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Window extends Table {
    private static final int MOVE = 32;
    private static final q tmpPosition = new q();
    private static final q tmpSize = new q();
    protected boolean dragging;
    boolean drawTitleTable;
    protected int edge;
    boolean isModal;
    boolean isMovable;
    boolean isResizable;
    boolean keepWithinStage;
    int resizeBorder;
    private WindowStyle style;
    Label titleLabel;
    Table titleTable;

    public Window(String str, Skin skin) {
        this(str, (WindowStyle) skin.get(WindowStyle.class));
        setSkin(skin);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        Stage stage = getStage();
        if (stage != null) {
            if (stage.getKeyboardFocus() == null) {
                stage.setKeyboardFocus(this);
            }
            keepWithinStage();
            if (this.style.stageBackground != null) {
                q qVar = tmpPosition;
                qVar.f91a = 0.0f;
                qVar.f92b = 0.0f;
                stageToLocalCoordinates(qVar);
                q qVar2 = tmpSize;
                float width = stage.getWidth();
                float height = stage.getHeight();
                qVar2.f91a = width;
                qVar2.f92b = height;
                stageToLocalCoordinates(qVar2);
                drawStageBackground(batch, f2, getX() + qVar.f91a, getY() + qVar.f92b, getX() + qVar2.f91a, getY() + qVar2.f92b);
            }
        }
        super.draw(batch, f2);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table
    protected void drawBackground(Batch batch, float f2, float f3, float f4) {
        super.drawBackground(batch, f2, f3, f4);
        this.titleTable.getColor().f1677a = getColor().f1677a;
        float padTop = getPadTop();
        float padLeft = getPadLeft();
        this.titleTable.setSize((getWidth() - padLeft) - getPadRight(), padTop);
        this.titleTable.setPosition(padLeft, getHeight() - padTop);
        this.drawTitleTable = true;
        this.titleTable.draw(batch, f2);
        this.drawTitleTable = false;
    }

    protected void drawStageBackground(Batch batch, float f2, float f3, float f4, float f5, float f6) {
        Color color = getColor();
        batch.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
        this.style.stageBackground.draw(batch, f3, f4, f5, f6);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        return Math.max(super.getPrefWidth(), getPadRight() + getPadLeft() + this.titleTable.getPrefWidth());
    }

    public WindowStyle getStyle() {
        return this.style;
    }

    public Label getTitleLabel() {
        return this.titleLabel;
    }

    public Table getTitleTable() {
        return this.titleTable;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public Actor hit(float f2, float f3, boolean z2) {
        if (!isVisible()) {
            return null;
        }
        Actor actorHit = super.hit(f2, f3, z2);
        if (actorHit == null && this.isModal && (!z2 || getTouchable() == Touchable.enabled)) {
            return this;
        }
        float height = getHeight();
        if (actorHit != null && actorHit != this && f3 <= height && f3 >= height - getPadTop() && f2 >= 0.0f && f2 <= getWidth()) {
            Actor parent = actorHit;
            while (parent.getParent() != this) {
                parent = parent.getParent();
            }
            if (getCell(parent) != null) {
                return this;
            }
        }
        return actorHit;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public boolean isModal() {
        return this.isModal;
    }

    public boolean isMovable() {
        return this.isMovable;
    }

    public boolean isResizable() {
        return this.isResizable;
    }

    public void keepWithinStage() {
        Stage stage;
        if (this.keepWithinStage && (stage = getStage()) != null) {
            Camera camera = stage.getCamera();
            if (!(camera instanceof OrthographicCamera)) {
                if (getParent() == stage.getRoot()) {
                    float width = stage.getWidth();
                    float height = stage.getHeight();
                    if (getX() < 0.0f) {
                        setX(0.0f);
                    }
                    if (getRight() > width) {
                        setX(width - getWidth());
                    }
                    if (getY() < 0.0f) {
                        setY(0.0f);
                    }
                    if (getTop() > height) {
                        setY(height - getHeight());
                        return;
                    }
                    return;
                }
                return;
            }
            OrthographicCamera orthographicCamera = (OrthographicCamera) camera;
            float width2 = stage.getWidth();
            float height2 = stage.getHeight();
            float x2 = getX(16);
            float f2 = camera.position.f1729a;
            float f3 = x2 - f2;
            float f4 = width2 / 2.0f;
            float f5 = orthographicCamera.zoom;
            if (f3 > f4 / f5) {
                setPosition((f4 / f5) + f2, getY(16), 16);
            }
            float x3 = getX(8);
            float f6 = camera.position.f1729a;
            float f7 = x3 - f6;
            float f8 = orthographicCamera.zoom;
            if (f7 < ((-width2) / 2.0f) / f8) {
                setPosition(f6 - (f4 / f8), getY(8), 8);
            }
            float f9 = height2 / 2.0f;
            if (getY(2) - camera.position.f1730b > f9 / orthographicCamera.zoom) {
                setPosition(getX(2), (f9 / orthographicCamera.zoom) + camera.position.f1730b, 2);
            }
            if (getY(4) - camera.position.f1730b < ((-height2) / 2.0f) / orthographicCamera.zoom) {
                setPosition(getX(4), camera.position.f1730b - (f9 / orthographicCamera.zoom), 4);
            }
        }
    }

    public void setKeepWithinStage(boolean z2) {
        this.keepWithinStage = z2;
    }

    public void setModal(boolean z2) {
        this.isModal = z2;
    }

    public void setMovable(boolean z2) {
        this.isMovable = z2;
    }

    public void setResizable(boolean z2) {
        this.isResizable = z2;
    }

    public void setResizeBorder(int i2) {
        this.resizeBorder = i2;
    }

    public void setStyle(WindowStyle windowStyle) {
        if (windowStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = windowStyle;
        setBackground(windowStyle.background);
        this.titleLabel.setStyle(new Label.LabelStyle(windowStyle.titleFont, windowStyle.titleFontColor));
        invalidateHierarchy();
    }

    public static class WindowStyle {
        public Drawable background;
        public Drawable stageBackground;
        public BitmapFont titleFont;
        public Color titleFontColor;

        public WindowStyle() {
            this.titleFontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        }

        public WindowStyle(BitmapFont bitmapFont, Color color, Drawable drawable) {
            Color color2 = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.titleFontColor = color2;
            this.titleFont = bitmapFont;
            color2.set(color);
            this.background = drawable;
        }

        public WindowStyle(WindowStyle windowStyle) {
            this.titleFontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.background = windowStyle.background;
            this.titleFont = windowStyle.titleFont;
            if (windowStyle.titleFontColor != null) {
                this.titleFontColor = new Color(windowStyle.titleFontColor);
            }
            this.background = windowStyle.background;
        }
    }

    public Window(String str, Skin skin, String str2) {
        this(str, (WindowStyle) skin.get(str2, WindowStyle.class));
        setSkin(skin);
    }

    public Window(String str, WindowStyle windowStyle) {
        this.isMovable = true;
        this.resizeBorder = 8;
        this.keepWithinStage = true;
        if (str != null) {
            setTouchable(Touchable.enabled);
            setClip(true);
            Label label = new Label(str, new Label.LabelStyle(windowStyle.titleFont, windowStyle.titleFontColor));
            this.titleLabel = label;
            label.setEllipsis(true);
            Table table = new Table() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Window.1
                @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
                public void draw(Batch batch, float f2) {
                    if (Window.this.drawTitleTable) {
                        super.draw(batch, f2);
                    }
                }
            };
            this.titleTable = table;
            table.add(this.titleLabel).expandX().fillX().minWidth(0.0f);
            addActor(this.titleTable);
            setStyle(windowStyle);
            setWidth(150.0f);
            setHeight(150.0f);
            addCaptureListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Window.2
                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                    Window.this.toFront();
                    return false;
                }
            });
            addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Window.3
                float lastX;
                float lastY;
                float startX;
                float startY;

                private void updateEdge(float f2, float f3) {
                    float f4 = r0.resizeBorder / 2.0f;
                    float width = Window.this.getWidth();
                    float height = Window.this.getHeight();
                    float padTop = Window.this.getPadTop();
                    float padLeft = Window.this.getPadLeft();
                    float padBottom = Window.this.getPadBottom();
                    float padRight = width - Window.this.getPadRight();
                    Window window = Window.this;
                    window.edge = 0;
                    if (window.isResizable && f2 >= padLeft - f4 && f2 <= padRight + f4 && f3 >= padBottom - f4) {
                        if (f2 < padLeft + f4) {
                            window.edge = 8;
                        }
                        if (f2 > padRight - f4) {
                            window.edge |= 16;
                        }
                        if (f3 < padBottom + f4) {
                            window.edge |= 4;
                        }
                        int i2 = window.edge;
                        if (i2 != 0) {
                            f4 += 25.0f;
                        }
                        if (f2 < padLeft + f4) {
                            window.edge = i2 | 8;
                        }
                        if (f2 > padRight - f4) {
                            window.edge |= 16;
                        }
                        if (f3 < padBottom + f4) {
                            window.edge |= 4;
                        }
                    }
                    if (!window.isMovable || window.edge != 0 || f3 > height || f3 < height - padTop || f2 < padLeft || f2 > padRight) {
                        return;
                    }
                    window.edge = 32;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean keyDown(InputEvent inputEvent, int i2) {
                    return Window.this.isModal;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean keyTyped(InputEvent inputEvent, char c2) {
                    return Window.this.isModal;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean keyUp(InputEvent inputEvent, int i2) {
                    return Window.this.isModal;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean mouseMoved(InputEvent inputEvent, float f2, float f3) {
                    updateEdge(f2, f3);
                    return Window.this.isModal;
                }

                public boolean scrolled(InputEvent inputEvent, float f2, float f3, int i2) {
                    return Window.this.isModal;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                    if (i3 == 0) {
                        updateEdge(f2, f3);
                        Window window = Window.this;
                        window.dragging = window.edge != 0;
                        this.startX = f2;
                        this.startY = f3;
                        this.lastX = f2 - window.getWidth();
                        this.lastY = f3 - Window.this.getHeight();
                    }
                    Window window2 = Window.this;
                    return window2.edge != 0 || window2.isModal;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public void touchDragged(InputEvent inputEvent, float f2, float f3, int i2) {
                    Window window = Window.this;
                    if (window.dragging) {
                        float width = window.getWidth();
                        float height = Window.this.getHeight();
                        float x2 = Window.this.getX();
                        float y2 = Window.this.getY();
                        float minWidth = Window.this.getMinWidth();
                        Window.this.getMaxWidth();
                        float minHeight = Window.this.getMinHeight();
                        Window.this.getMaxHeight();
                        Stage stage = Window.this.getStage();
                        Window window2 = Window.this;
                        boolean z2 = window2.keepWithinStage && stage != null && window2.getParent() == stage.getRoot();
                        int i3 = Window.this.edge;
                        if ((i3 & 32) != 0) {
                            x2 += f2 - this.startX;
                            y2 += f3 - this.startY;
                        }
                        if ((i3 & 8) != 0) {
                            float f4 = f2 - this.startX;
                            if (width - f4 < minWidth) {
                                f4 = -(minWidth - width);
                            }
                            if (z2 && x2 + f4 < 0.0f) {
                                f4 = -x2;
                            }
                            width -= f4;
                            x2 += f4;
                        }
                        if ((i3 & 4) != 0) {
                            float f5 = f3 - this.startY;
                            if (height - f5 < minHeight) {
                                f5 = -(minHeight - height);
                            }
                            if (z2 && y2 + f5 < 0.0f) {
                                f5 = -y2;
                            }
                            height -= f5;
                            y2 += f5;
                        }
                        if ((i3 & 16) != 0) {
                            float width2 = (f2 - this.lastX) - width;
                            if (width + width2 < minWidth) {
                                width2 = minWidth - width;
                            }
                            if (z2 && x2 + width + width2 > stage.getWidth()) {
                                width2 = (stage.getWidth() - x2) - width;
                            }
                            width += width2;
                        }
                        if ((Window.this.edge & 2) != 0) {
                            float height2 = (f3 - this.lastY) - height;
                            if (height + height2 < minHeight) {
                                height2 = minHeight - height;
                            }
                            if (z2 && y2 + height + height2 > stage.getHeight()) {
                                height2 = (stage.getHeight() - y2) - height;
                            }
                            height += height2;
                        }
                        Window.this.setBounds(Math.round(x2), Math.round(y2), Math.round(width), Math.round(height));
                    }
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                    Window.this.dragging = false;
                }
            });
            return;
        }
        throw new IllegalArgumentException("title cannot be null.");
    }
}
