package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class Value {
    public static final Fixed zero = new Fixed(0.0f);
    public static Value minWidth = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getMinWidth();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getWidth();
        }
    };
    public static Value minHeight = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.2
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getMinHeight();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getHeight();
        }
    };
    public static Value prefWidth = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.3
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getPrefWidth();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getWidth();
        }
    };
    public static Value prefHeight = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.4
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getPrefHeight();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getHeight();
        }
    };
    public static Value maxWidth = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.5
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getMaxWidth();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getWidth();
        }
    };
    public static Value maxHeight = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.6
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getMaxHeight();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getHeight();
        }
    };

    public static class Fixed extends Value {
        static final Fixed[] cache = new Fixed[111];
        private final float value;

        public Fixed(float f2) {
            this.value = f2;
        }

        public static Fixed valueOf(float f2) {
            if (f2 == 0.0f) {
                return Value.zero;
            }
            if (f2 >= -10.0f && f2 <= 100.0f) {
                int i2 = (int) f2;
                if (f2 == i2) {
                    Fixed[] fixedArr = cache;
                    int i3 = i2 + 10;
                    Fixed fixed = fixedArr[i3];
                    if (fixed != null) {
                        return fixed;
                    }
                    Fixed fixed2 = new Fixed(f2);
                    fixedArr[i3] = fixed2;
                    return fixed2;
                }
            }
            return new Fixed(f2);
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            return this.value;
        }

        public String toString() {
            return Float.toString(this.value);
        }
    }

    public static Value percentHeight(final float f2) {
        return new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.8
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(Actor actor) {
                return actor.getHeight() * f2;
            }
        };
    }

    public static Value percentWidth(final float f2) {
        return new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.7
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(Actor actor) {
                return actor.getWidth() * f2;
            }
        };
    }

    public float get() {
        return get(null);
    }

    public abstract float get(Actor actor);

    public static Value percentHeight(final float f2, final Actor actor) {
        if (actor != null) {
            return new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.10
                @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
                public float get(Actor actor2) {
                    return actor.getHeight() * f2;
                }
            };
        }
        throw new IllegalArgumentException("actor cannot be null.");
    }

    public static Value percentWidth(final float f2, final Actor actor) {
        if (actor != null) {
            return new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Value.9
                @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
                public float get(Actor actor2) {
                    return actor.getWidth() * f2;
                }
            };
        }
        throw new IllegalArgumentException("actor cannot be null.");
    }
}
