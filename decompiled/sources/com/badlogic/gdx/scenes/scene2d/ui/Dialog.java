package com.badlogic.gdx.scenes.scene2d.ui;

import a0.h;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.y;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Dialog extends Window {
    Table buttonTable;
    boolean cancelHide;
    Table contentTable;
    FocusListener focusListener;
    protected InputListener ignoreTouchDown;
    Actor previousKeyboardFocus;
    Actor previousScrollFocus;
    private Skin skin;
    y<Actor, Object> values;

    public Dialog(String str, Skin skin) {
        super(str, (Window.WindowStyle) skin.get(Window.WindowStyle.class));
        this.values = new y<>();
        this.ignoreTouchDown = new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Dialog.1
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                inputEvent.cancel();
                return false;
            }
        };
        setSkin(skin);
        this.skin = skin;
        initialize();
    }

    private void initialize() {
        setModal(true);
        defaults().space(6.0f);
        Table table = new Table(this.skin);
        this.contentTable = table;
        add(table).expand().fill();
        row();
        Table table2 = new Table(this.skin);
        this.buttonTable = table2;
        add(table2).fillX();
        this.contentTable.defaults().space(6.0f);
        this.buttonTable.defaults().space(6.0f);
        this.buttonTable.addListener(new ChangeListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Dialog.2
            @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                Dialog dialog;
                if (Dialog.this.values.a(actor)) {
                    while (true) {
                        Group parent = actor.getParent();
                        dialog = Dialog.this;
                        if (parent == dialog.buttonTable) {
                            break;
                        } else {
                            actor = actor.getParent();
                        }
                    }
                    dialog.result(dialog.values.d(actor));
                    Dialog dialog2 = Dialog.this;
                    if (!dialog2.cancelHide) {
                        dialog2.hide();
                    }
                    Dialog.this.cancelHide = false;
                }
            }
        });
        this.focusListener = new FocusListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Dialog.3
            private void focusChanged(FocusListener.FocusEvent focusEvent) {
                Actor relatedActor;
                Stage stage = Dialog.this.getStage();
                if (!Dialog.this.isModal || stage == null || stage.getRoot().getChildren().f1750b <= 0 || stage.getRoot().getChildren().k() != Dialog.this || (relatedActor = focusEvent.getRelatedActor()) == null || relatedActor.isDescendantOf(Dialog.this) || relatedActor.equals(Dialog.this.previousKeyboardFocus) || relatedActor.equals(Dialog.this.previousScrollFocus)) {
                    return;
                }
                focusEvent.cancel();
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.FocusListener
            public void keyboardFocusChanged(FocusListener.FocusEvent focusEvent, Actor actor, boolean z2) {
                if (z2) {
                    return;
                }
                focusChanged(focusEvent);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.FocusListener
            public void scrollFocusChanged(FocusListener.FocusEvent focusEvent, Actor actor, boolean z2) {
                if (z2) {
                    return;
                }
                focusChanged(focusEvent);
            }
        };
    }

    public Dialog button(String str) {
        return button(str, (Object) null);
    }

    public void cancel() {
        this.cancelHide = true;
    }

    public Table getButtonTable() {
        return this.buttonTable;
    }

    public Table getContentTable() {
        return this.contentTable;
    }

    public void hide(Action action) {
        Stage stage = getStage();
        if (stage != null) {
            removeListener(this.focusListener);
            Actor actor = this.previousKeyboardFocus;
            if (actor != null && actor.getStage() == null) {
                this.previousKeyboardFocus = null;
            }
            Actor keyboardFocus = stage.getKeyboardFocus();
            if (keyboardFocus == null || keyboardFocus.isDescendantOf(this)) {
                stage.setKeyboardFocus(this.previousKeyboardFocus);
            }
            Actor actor2 = this.previousScrollFocus;
            if (actor2 != null && actor2.getStage() == null) {
                this.previousScrollFocus = null;
            }
            Actor scrollFocus = stage.getScrollFocus();
            if (scrollFocus == null || scrollFocus.isDescendantOf(this)) {
                stage.setScrollFocus(this.previousScrollFocus);
            }
        }
        if (action == null) {
            remove();
        } else {
            addCaptureListener(this.ignoreTouchDown);
            addAction(Actions.sequence(action, Actions.removeListener(this.ignoreTouchDown, true), Actions.removeActor()));
        }
    }

    public Dialog key(final int i2, final Object obj) {
        addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Dialog.4
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyDown(InputEvent inputEvent, int i3) {
                if (i2 != i3) {
                    return false;
                }
                Gdx.app.postRunnable(new Runnable() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Dialog.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                        Dialog.this.result(obj);
                        Dialog dialog = Dialog.this;
                        if (!dialog.cancelHide) {
                            dialog.hide();
                        }
                        Dialog.this.cancelHide = false;
                    }
                });
                return false;
            }
        });
        return this;
    }

    protected void result(Object obj) {
    }

    public void setObject(Actor actor, Object obj) {
        this.values.j(actor, obj);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    protected void setStage(Stage stage) {
        if (stage == null) {
            addListener(this.focusListener);
        } else {
            removeListener(this.focusListener);
        }
        super.setStage(stage);
    }

    public Dialog show(Stage stage, Action action) {
        clearActions();
        removeCaptureListener(this.ignoreTouchDown);
        this.previousKeyboardFocus = null;
        Actor keyboardFocus = stage.getKeyboardFocus();
        if (keyboardFocus != null && !keyboardFocus.isDescendantOf(this)) {
            this.previousKeyboardFocus = keyboardFocus;
        }
        this.previousScrollFocus = null;
        Actor scrollFocus = stage.getScrollFocus();
        if (scrollFocus != null && !scrollFocus.isDescendantOf(this)) {
            this.previousScrollFocus = scrollFocus;
        }
        stage.addActor(this);
        pack();
        stage.cancelTouchFocus();
        stage.setKeyboardFocus(this);
        stage.setScrollFocus(this);
        if (action != null) {
            addAction(action);
        }
        return this;
    }

    public Dialog text(String str) {
        Skin skin = this.skin;
        if (skin != null) {
            return text(str, (Label.LabelStyle) skin.get(Label.LabelStyle.class));
        }
        throw new IllegalStateException("This method may only be used if the dialog was constructed with a Skin.");
    }

    public Dialog button(String str, Object obj) {
        Skin skin = this.skin;
        if (skin != null) {
            return button(str, obj, (TextButton.TextButtonStyle) skin.get(TextButton.TextButtonStyle.class));
        }
        throw new IllegalStateException("This method may only be used if the dialog was constructed with a Skin.");
    }

    public Dialog text(String str, Label.LabelStyle labelStyle) {
        return text(new Label(str, labelStyle));
    }

    public Dialog button(String str, Object obj, TextButton.TextButtonStyle textButtonStyle) {
        return button(new TextButton(str, textButtonStyle), obj);
    }

    public Dialog text(Label label) {
        this.contentTable.add(label);
        return this;
    }

    public Dialog button(Button button) {
        return button(button, (Object) null);
    }

    public Dialog(String str, Skin skin, String str2) {
        super(str, (Window.WindowStyle) skin.get(str2, Window.WindowStyle.class));
        this.values = new y<>();
        this.ignoreTouchDown = new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Dialog.1
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                inputEvent.cancel();
                return false;
            }
        };
        setSkin(skin);
        this.skin = skin;
        initialize();
    }

    public Dialog button(Button button, Object obj) {
        this.buttonTable.add(button);
        setObject(button, obj);
        return this;
    }

    public void hide() {
        hide(Actions.fadeOut(0.4f, h.f53b));
    }

    public Dialog(String str, Window.WindowStyle windowStyle) {
        super(str, windowStyle);
        this.values = new y<>();
        this.ignoreTouchDown = new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Dialog.1
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                inputEvent.cancel();
                return false;
            }
        };
        initialize();
    }

    public Dialog show(Stage stage) {
        show(stage, Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(0.4f, h.f53b)));
        setPosition(Math.round((stage.getWidth() - getWidth()) / 2.0f), Math.round((stage.getHeight() - getHeight()) / 2.0f));
        return this;
    }
}
