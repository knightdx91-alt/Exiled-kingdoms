package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TextTooltip extends Tooltip<Label> {

    public static class TextTooltipStyle {
        public Drawable background;
        public Label.LabelStyle label;
        public float wrapWidth;

        public TextTooltipStyle() {
        }

        public TextTooltipStyle(Label.LabelStyle labelStyle, Drawable drawable) {
            this.label = labelStyle;
            this.background = drawable;
        }

        public TextTooltipStyle(TextTooltipStyle textTooltipStyle) {
            this.label = new Label.LabelStyle(textTooltipStyle.label);
            this.background = textTooltipStyle.background;
            this.wrapWidth = textTooltipStyle.wrapWidth;
        }
    }

    public TextTooltip(String str, Skin skin) {
        this(str, TooltipManager.getInstance(), (TextTooltipStyle) skin.get(TextTooltipStyle.class));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void setStyle(TextTooltipStyle textTooltipStyle) {
        if (textTooltipStyle == null) {
            throw new NullPointerException("style cannot be null");
        }
        ((Label) this.container.getActor()).setStyle(textTooltipStyle.label);
        this.container.setBackground(textTooltipStyle.background);
        this.container.maxWidth(textTooltipStyle.wrapWidth);
    }

    public TextTooltip(String str, Skin skin, String str2) {
        this(str, TooltipManager.getInstance(), (TextTooltipStyle) skin.get(str2, TextTooltipStyle.class));
    }

    public TextTooltip(String str, TextTooltipStyle textTooltipStyle) {
        this(str, TooltipManager.getInstance(), textTooltipStyle);
    }

    public TextTooltip(String str, TooltipManager tooltipManager, Skin skin) {
        this(str, tooltipManager, (TextTooltipStyle) skin.get(TextTooltipStyle.class));
    }

    public TextTooltip(String str, TooltipManager tooltipManager, Skin skin, String str2) {
        this(str, tooltipManager, (TextTooltipStyle) skin.get(str2, TextTooltipStyle.class));
    }

    public TextTooltip(String str, final TooltipManager tooltipManager, TextTooltipStyle textTooltipStyle) {
        super(null, tooltipManager);
        final Label label = new Label(str, textTooltipStyle.label);
        label.setWrap(true);
        this.container.setActor(label);
        this.container.width(new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.TextTooltip.1
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(Actor actor) {
                return Math.min(tooltipManager.maxWidth, label.getGlyphLayout().width);
            }
        });
        setStyle(textTooltipStyle);
    }
}
