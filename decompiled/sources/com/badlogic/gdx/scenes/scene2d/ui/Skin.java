package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.h0;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.t;
import com.badlogic.gdx.utils.y;
import h0.a;
import h0.d;
import h0.e;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Skin implements i {
    private static final Class[] defaultTagClasses = {BitmapFont.class, Color.class, TintedDrawable.class, NinePatchDrawable.class, SpriteDrawable.class, TextureRegionDrawable.class, TiledDrawable.class, Button.ButtonStyle.class, CheckBox.CheckBoxStyle.class, ImageButton.ImageButtonStyle.class, ImageTextButton.ImageTextButtonStyle.class, Label.LabelStyle.class, List.ListStyle.class, ProgressBar.ProgressBarStyle.class, ScrollPane.ScrollPaneStyle.class, SelectBox.SelectBoxStyle.class, Slider.SliderStyle.class, SplitPane.SplitPaneStyle.class, TextButton.TextButtonStyle.class, TextField.TextFieldStyle.class, TextTooltip.TextTooltipStyle.class, Touchpad.TouchpadStyle.class, Tree.TreeStyle.class, Window.WindowStyle.class};
    TextureAtlas atlas;
    private final y<String, Class> jsonClassTags;
    y<Class, y<String, Object>> resources = new y<>();
    float scale = 1.0f;

    public static class TintedDrawable {
        public Color color;
        public String name;
    }

    public Skin() {
        Class[] clsArr = defaultTagClasses;
        this.jsonClassTags = new y<>(clsArr.length, 0);
        for (Class cls : clsArr) {
            this.jsonClassTags.j(cls.getSimpleName(), cls);
        }
    }

    private static d findMethod(Class cls, String str) {
        for (d dVar : a.e(cls)) {
            if (dVar.a().equals(str)) {
                return dVar;
            }
        }
        return null;
    }

    public void add(String str, Object obj) {
        add(str, obj, obj.getClass());
    }

    public void addRegions(TextureAtlas textureAtlas) {
        com.badlogic.gdx.utils.a<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();
        int i2 = regions.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            TextureAtlas.AtlasRegion atlasRegion = regions.get(i3);
            String string = atlasRegion.name;
            if (atlasRegion.index != -1) {
                StringBuilder sbT = a.a.t(string, "_");
                sbT.append(atlasRegion.index);
                string = sbT.toString();
            }
            add(string, atlasRegion, TextureRegion.class);
        }
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        TextureAtlas textureAtlas = this.atlas;
        if (textureAtlas != null) {
            textureAtlas.dispose();
        }
        y.e<y<String, Object>> eVarN = this.resources.n();
        eVarN.getClass();
        while (eVarN.hasNext()) {
            y.e<Object> eVarN2 = eVarN.next().n();
            eVarN2.getClass();
            while (eVarN2.hasNext()) {
                Object next = eVarN2.next();
                if (next instanceof i) {
                    ((i) next).dispose();
                }
            }
        }
    }

    public String find(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        y<String, Object> yVarD = this.resources.d(obj.getClass());
        if (yVarD == null) {
            return null;
        }
        return (String) yVarD.c(obj);
    }

    public <T> T get(Class<T> cls) {
        return (T) get("default", cls);
    }

    public <T> y<String, T> getAll(Class<T> cls) {
        return (y) this.resources.d(cls);
    }

    public TextureAtlas getAtlas() {
        return this.atlas;
    }

    public Color getColor(String str) {
        return (Color) get(str, Color.class);
    }

    public Drawable getDrawable(String str) {
        Drawable spriteDrawable;
        Drawable spriteDrawable2;
        Drawable drawable = (Drawable) optional(str, Drawable.class);
        if (drawable != null) {
            return drawable;
        }
        try {
            TextureRegion region = getRegion(str);
            if (region instanceof TextureAtlas.AtlasRegion) {
                TextureAtlas.AtlasRegion atlasRegion = (TextureAtlas.AtlasRegion) region;
                if (atlasRegion.splits != null) {
                    spriteDrawable2 = new NinePatchDrawable(getPatch(str));
                } else if (atlasRegion.rotate || atlasRegion.packedWidth != atlasRegion.originalWidth || atlasRegion.packedHeight != atlasRegion.originalHeight) {
                    spriteDrawable2 = new SpriteDrawable(getSprite(str));
                }
                drawable = spriteDrawable2;
            }
            if (drawable == null) {
                Drawable textureRegionDrawable = new TextureRegionDrawable(region);
                try {
                    if (this.scale != 1.0f) {
                        scale(textureRegionDrawable);
                    }
                } catch (m unused) {
                }
                drawable = textureRegionDrawable;
            }
        } catch (m unused2) {
        }
        if (drawable == null) {
            NinePatch ninePatch = (NinePatch) optional(str, NinePatch.class);
            if (ninePatch != null) {
                spriteDrawable = new NinePatchDrawable(ninePatch);
            } else {
                Sprite sprite = (Sprite) optional(str, Sprite.class);
                if (sprite == null) {
                    throw new m(a.a.A("No Drawable, NinePatch, TextureRegion, Texture, or Sprite registered with name: ", str));
                }
                spriteDrawable = new SpriteDrawable(sprite);
            }
            drawable = spriteDrawable;
        }
        if (drawable instanceof BaseDrawable) {
            ((BaseDrawable) drawable).setName(str);
        }
        add(str, drawable, Drawable.class);
        return drawable;
    }

    public BitmapFont getFont(String str) {
        return (BitmapFont) get(str, BitmapFont.class);
    }

    public y<String, Class> getJsonClassTags() {
        return this.jsonClassTags;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected Json getJsonLoader(final com.badlogic.gdx.files.a aVar) {
        Json json = new Json() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.1
            private static final String parentFieldName = "parent";

            @Override // com.badlogic.gdx.utils.Json
            protected boolean ignoreUnknownField(Class cls, String str) {
                return str.equals(parentFieldName);
            }

            @Override // com.badlogic.gdx.utils.Json
            public void readFields(Object obj, t tVar) {
                if (tVar.k(parentFieldName) != null) {
                    String str = (String) readValue(parentFieldName, String.class, tVar);
                    Class<?> superclass = obj.getClass();
                    do {
                        try {
                            copyFields(Skin.this.get(str, superclass), obj);
                        } catch (m unused) {
                            superclass = superclass.getSuperclass();
                        }
                    } while (superclass != Object.class);
                    h0 h0Var = new h0(a.a.A("Unable to find parent resource with name: ", str));
                    h0Var.a(tVar.f1958f.B());
                    throw h0Var;
                }
                super.readFields(obj, tVar);
            }

            @Override // com.badlogic.gdx.utils.Json
            public <T> T readValue(Class<T> cls, Class cls2, t tVar) {
                return (tVar == null || !tVar.u() || CharSequence.class.isAssignableFrom(cls)) ? (T) super.readValue(cls, cls2, tVar) : (T) Skin.this.get(tVar.j(), cls);
            }
        };
        json.setTypeName(null);
        json.setUsePrototypes(false);
        json.setSerializer(Skin.class, new Json.ReadOnlySerializer<Skin>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.2
            private void readNamedObjects(Json json2, Class cls, t tVar) {
                Class cls2 = cls == TintedDrawable.class ? Drawable.class : cls;
                for (t tVar2 = tVar.f1958f; tVar2 != null; tVar2 = tVar2.f1960h) {
                    Object value = json2.readValue(cls, tVar2);
                    if (value != null) {
                        try {
                            Skin.this.add(tVar2.f1957e, value, cls2);
                            if (cls2 != Drawable.class && Drawable.class.isAssignableFrom(cls2)) {
                                Skin.this.add(tVar2.f1957e, value, Drawable.class);
                            }
                        } catch (Exception e2) {
                            throw new h0("Error reading " + cls.getSimpleName() + ": " + tVar2.f1957e, e2);
                        }
                    }
                }
            }

            @Override // com.badlogic.gdx.utils.Json.ReadOnlySerializer, com.badlogic.gdx.utils.Json.c
            public Skin read(Json json2, t tVar, Class cls) {
                for (t tVar2 = tVar.f1958f; tVar2 != null; tVar2 = tVar2.f1960h) {
                    try {
                        Class clsA = json2.getClass(tVar2.f1957e);
                        if (clsA == null) {
                            clsA = a.a(tVar2.f1957e);
                        }
                        readNamedObjects(json2, clsA, tVar2);
                    } catch (e e2) {
                        throw new h0(e2);
                    }
                }
                return this;
            }
        });
        json.setSerializer(BitmapFont.class, new Json.ReadOnlySerializer<BitmapFont>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.3
            @Override // com.badlogic.gdx.utils.Json.ReadOnlySerializer, com.badlogic.gdx.utils.Json.c
            public BitmapFont read(Json json2, t tVar, Class cls) {
                BitmapFont bitmapFont;
                String str = (String) json2.readValue("file", String.class, tVar);
                int iIntValue = ((Integer) json2.readValue("scaledSize", (Class<int>) Integer.TYPE, -1, tVar)).intValue();
                Boolean bool = Boolean.FALSE;
                Boolean bool2 = (Boolean) json2.readValue("flip", (Class<Boolean>) Boolean.class, bool, tVar);
                Boolean bool3 = (Boolean) json2.readValue("markupEnabled", (Class<Boolean>) Boolean.class, bool, tVar);
                com.badlogic.gdx.files.a aVarChild = aVar.parent().child(str);
                if (!aVarChild.exists()) {
                    aVarChild = Gdx.files.internal(str);
                }
                if (!aVarChild.exists()) {
                    throw new h0(a.a.i(aVarChild, "Font file not found: "));
                }
                String strNameWithoutExtension = aVarChild.nameWithoutExtension();
                try {
                    com.badlogic.gdx.utils.a<TextureRegion> regions = this.getRegions(strNameWithoutExtension);
                    if (regions != null) {
                        bitmapFont = new BitmapFont(new BitmapFont.BitmapFontData(aVarChild, bool2.booleanValue()), regions, true);
                    } else {
                        TextureRegion textureRegion = (TextureRegion) this.optional(strNameWithoutExtension, TextureRegion.class);
                        if (textureRegion != null) {
                            bitmapFont = new BitmapFont(aVarChild, textureRegion, bool2.booleanValue());
                        } else {
                            com.badlogic.gdx.files.a aVarChild2 = aVarChild.parent().child(strNameWithoutExtension + ".png");
                            bitmapFont = aVarChild2.exists() ? new BitmapFont(aVarChild, aVarChild2, bool2.booleanValue()) : new BitmapFont(aVarChild, bool2.booleanValue());
                        }
                    }
                    bitmapFont.getData().markupEnabled = bool3.booleanValue();
                    if (iIntValue != -1) {
                        bitmapFont.getData().setScale(iIntValue / bitmapFont.getCapHeight());
                    }
                    return bitmapFont;
                } catch (RuntimeException e2) {
                    throw new h0(a.a.i(aVarChild, "Error loading bitmap font: "), e2);
                }
            }
        });
        json.setSerializer(Color.class, new Json.ReadOnlySerializer<Color>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.4
            @Override // com.badlogic.gdx.utils.Json.ReadOnlySerializer, com.badlogic.gdx.utils.Json.c
            public Color read(Json json2, t tVar, Class cls) {
                if (tVar.u()) {
                    return (Color) Skin.this.get(tVar.j(), Color.class);
                }
                String str = (String) json2.readValue("hex", (Class<Object>) String.class, (Object) null, tVar);
                if (str != null) {
                    return Color.valueOf(str);
                }
                Class cls2 = Float.TYPE;
                return new Color(((Float) json2.readValue("r", (Class<Float>) cls2, Float.valueOf(0.0f), tVar)).floatValue(), ((Float) json2.readValue("g", (Class<Float>) cls2, Float.valueOf(0.0f), tVar)).floatValue(), ((Float) json2.readValue("b", (Class<Float>) cls2, Float.valueOf(0.0f), tVar)).floatValue(), ((Float) json2.readValue("a", (Class<Float>) cls2, Float.valueOf(1.0f), tVar)).floatValue());
            }
        });
        json.setSerializer(TintedDrawable.class, new Json.ReadOnlySerializer() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.5
            @Override // com.badlogic.gdx.utils.Json.ReadOnlySerializer, com.badlogic.gdx.utils.Json.c
            public Object read(Json json2, t tVar, Class cls) {
                String str = (String) json2.readValue("name", String.class, tVar);
                Color color = (Color) json2.readValue("color", Color.class, tVar);
                if (color == null) {
                    throw new h0("TintedDrawable missing color: " + tVar);
                }
                Drawable drawableNewDrawable = Skin.this.newDrawable(str, color);
                if (drawableNewDrawable instanceof BaseDrawable) {
                    ((BaseDrawable) drawableNewDrawable).setName(tVar.f1957e + " (" + str + ", " + color + ")");
                }
                return drawableNewDrawable;
            }
        });
        y.a<String, Class> it = this.jsonClassTags.iterator();
        while (it.hasNext()) {
            y.b next = it.next();
            json.addClassTag((String) next.f2057a, (Class) next.f2058b);
        }
        return json;
    }

    public NinePatch getPatch(String str) {
        int[] iArr;
        NinePatch ninePatch = (NinePatch) optional(str, NinePatch.class);
        if (ninePatch != null) {
            return ninePatch;
        }
        try {
            TextureRegion region = getRegion(str);
            if ((region instanceof TextureAtlas.AtlasRegion) && (iArr = ((TextureAtlas.AtlasRegion) region).splits) != null) {
                ninePatch = new NinePatch(region, iArr[0], iArr[1], iArr[2], iArr[3]);
                if (((TextureAtlas.AtlasRegion) region).pads != null) {
                    ninePatch.setPadding(r2[0], r2[1], r2[2], r2[3]);
                }
            }
            if (ninePatch == null) {
                ninePatch = new NinePatch(region);
            }
            float f2 = this.scale;
            if (f2 != 1.0f) {
                ninePatch.scale(f2, f2);
            }
            add(str, ninePatch, NinePatch.class);
            return ninePatch;
        } catch (m unused) {
            throw new m(a.a.A("No NinePatch, TextureRegion, or Texture registered with name: ", str));
        }
    }

    public TextureRegion getRegion(String str) {
        TextureRegion textureRegion = (TextureRegion) optional(str, TextureRegion.class);
        if (textureRegion != null) {
            return textureRegion;
        }
        Texture texture = (Texture) optional(str, Texture.class);
        if (texture == null) {
            throw new m(a.a.A("No TextureRegion or Texture registered with name: ", str));
        }
        TextureRegion textureRegion2 = new TextureRegion(texture);
        add(str, textureRegion2, TextureRegion.class);
        return textureRegion2;
    }

    public com.badlogic.gdx.utils.a<TextureRegion> getRegions(String str) {
        TextureRegion textureRegion = (TextureRegion) optional(str + "_0", TextureRegion.class);
        if (textureRegion == null) {
            return null;
        }
        com.badlogic.gdx.utils.a<TextureRegion> aVar = new com.badlogic.gdx.utils.a<>();
        int i2 = 1;
        while (textureRegion != null) {
            aVar.a(textureRegion);
            textureRegion = (TextureRegion) optional(str + "_" + i2, TextureRegion.class);
            i2++;
        }
        return aVar;
    }

    public Sprite getSprite(String str) {
        Sprite sprite = (Sprite) optional(str, Sprite.class);
        if (sprite != null) {
            return sprite;
        }
        try {
            TextureRegion region = getRegion(str);
            if (region instanceof TextureAtlas.AtlasRegion) {
                TextureAtlas.AtlasRegion atlasRegion = (TextureAtlas.AtlasRegion) region;
                if (atlasRegion.rotate || atlasRegion.packedWidth != atlasRegion.originalWidth || atlasRegion.packedHeight != atlasRegion.originalHeight) {
                    sprite = new TextureAtlas.AtlasSprite(atlasRegion);
                }
            }
            if (sprite == null) {
                sprite = new Sprite(region);
            }
            if (this.scale != 1.0f) {
                sprite.setSize(sprite.getWidth() * this.scale, sprite.getHeight() * this.scale);
            }
            add(str, sprite, Sprite.class);
            return sprite;
        } catch (m unused) {
            throw new m(a.a.A("No NinePatch, TextureRegion, or Texture registered with name: ", str));
        }
    }

    public TiledDrawable getTiledDrawable(String str) {
        TiledDrawable tiledDrawable = (TiledDrawable) optional(str, TiledDrawable.class);
        if (tiledDrawable != null) {
            return tiledDrawable;
        }
        TiledDrawable tiledDrawable2 = new TiledDrawable(getRegion(str));
        tiledDrawable2.setName(str);
        if (this.scale != 1.0f) {
            scale(tiledDrawable2);
            tiledDrawable2.setScale(this.scale);
        }
        add(str, tiledDrawable2, TiledDrawable.class);
        return tiledDrawable2;
    }

    public boolean has(String str, Class cls) {
        y<String, Object> yVarD = this.resources.d(cls);
        if (yVarD == null) {
            return false;
        }
        return yVarD.a(str);
    }

    public void load(com.badlogic.gdx.files.a aVar) {
        try {
            getJsonLoader(aVar).fromJson(Skin.class, aVar);
        } catch (h0 e2) {
            throw new h0(a.a.i(aVar, "Error reading file: "), e2);
        }
    }

    public Drawable newDrawable(String str) {
        return newDrawable(getDrawable(str));
    }

    public <T> T optional(String str, Class<T> cls) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        y<String, Object> yVarD = this.resources.d(cls);
        if (yVarD == null) {
            return null;
        }
        return (T) yVarD.d(str);
    }

    public void remove(String str, Class cls) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        this.resources.d(cls).k(str);
    }

    public void scale(Drawable drawable) {
        drawable.setLeftWidth(drawable.getLeftWidth() * this.scale);
        drawable.setRightWidth(drawable.getRightWidth() * this.scale);
        drawable.setBottomHeight(drawable.getBottomHeight() * this.scale);
        drawable.setTopHeight(drawable.getTopHeight() * this.scale);
        drawable.setMinWidth(drawable.getMinWidth() * this.scale);
        drawable.setMinHeight(drawable.getMinHeight() * this.scale);
    }

    public void setEnabled(Actor actor, boolean z2) {
        d dVarFindMethod = findMethod(actor.getClass(), "getStyle");
        if (dVarFindMethod == null) {
            return;
        }
        try {
            Object objB = dVarFindMethod.b(actor, new Object[0]);
            String strFind = find(objB);
            if (strFind == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(strFind.replace("-disabled", ""));
            sb.append(z2 ? "" : "-disabled");
            Object obj = get(sb.toString(), objB.getClass());
            d dVarFindMethod2 = findMethod(actor.getClass(), "setStyle");
            if (dVarFindMethod2 == null) {
                return;
            }
            dVarFindMethod2.b(actor, obj);
        } catch (Exception unused) {
        }
    }

    public void setScale(float f2) {
        this.scale = f2;
    }

    public void add(String str, Object obj, Class cls) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if (obj == null) {
            throw new IllegalArgumentException("resource cannot be null.");
        }
        y<String, Object> yVarD = this.resources.d(cls);
        if (yVarD == null) {
            yVarD = new y<>((cls == TextureRegion.class || cls == Drawable.class || cls == Sprite.class) ? 256 : 64, 0);
            this.resources.j(cls, yVarD);
        }
        yVarD.j(str, obj);
    }

    public <T> T get(String str, Class<T> cls) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (cls == Drawable.class) {
            return (T) getDrawable(str);
        }
        if (cls == TextureRegion.class) {
            return (T) getRegion(str);
        }
        if (cls == NinePatch.class) {
            return (T) getPatch(str);
        }
        if (cls == Sprite.class) {
            return (T) getSprite(str);
        }
        y<String, Object> yVarD = this.resources.d(cls);
        if (yVarD == null) {
            throw new m("No " + cls.getName() + " registered with name: " + str);
        }
        T t2 = (T) yVarD.d(str);
        if (t2 != null) {
            return t2;
        }
        throw new m("No " + cls.getName() + " registered with name: " + str);
    }

    public Drawable newDrawable(String str, float f2, float f3, float f4, float f5) {
        return newDrawable(getDrawable(str), new Color(f2, f3, f4, f5));
    }

    public Drawable newDrawable(String str, Color color) {
        return newDrawable(getDrawable(str), color);
    }

    public Drawable newDrawable(Drawable drawable) {
        if (drawable instanceof TiledDrawable) {
            return new TiledDrawable((TiledDrawable) drawable);
        }
        if (drawable instanceof TextureRegionDrawable) {
            return new TextureRegionDrawable((TextureRegionDrawable) drawable);
        }
        if (drawable instanceof NinePatchDrawable) {
            return new NinePatchDrawable((NinePatchDrawable) drawable);
        }
        if (drawable instanceof SpriteDrawable) {
            return new SpriteDrawable((SpriteDrawable) drawable);
        }
        throw new m("Unable to copy, unknown drawable type: " + drawable.getClass());
    }

    public Skin(com.badlogic.gdx.files.a aVar) {
        Class[] clsArr = defaultTagClasses;
        this.jsonClassTags = new y<>(clsArr.length, 0);
        for (Class cls : clsArr) {
            this.jsonClassTags.j(cls.getSimpleName(), cls);
        }
        com.badlogic.gdx.files.a aVarSibling = aVar.sibling(aVar.nameWithoutExtension() + ".atlas");
        if (aVarSibling.exists()) {
            TextureAtlas textureAtlas = new TextureAtlas(aVarSibling);
            this.atlas = textureAtlas;
            addRegions(textureAtlas);
        }
        load(aVar);
    }

    public Drawable newDrawable(Drawable drawable, float f2, float f3, float f4, float f5) {
        return newDrawable(drawable, new Color(f2, f3, f4, f5));
    }

    public Drawable newDrawable(Drawable drawable, Color color) {
        Drawable drawableTint;
        if (drawable instanceof TextureRegionDrawable) {
            drawableTint = ((TextureRegionDrawable) drawable).tint(color);
        } else if (drawable instanceof NinePatchDrawable) {
            drawableTint = ((NinePatchDrawable) drawable).tint(color);
        } else if (drawable instanceof SpriteDrawable) {
            drawableTint = ((SpriteDrawable) drawable).tint(color);
        } else {
            throw new m("Unable to copy, unknown drawable type: " + drawable.getClass());
        }
        if (drawableTint instanceof BaseDrawable) {
            BaseDrawable baseDrawable = (BaseDrawable) drawableTint;
            if (drawable instanceof BaseDrawable) {
                baseDrawable.setName(((BaseDrawable) drawable).getName() + " (" + color + ")");
            } else {
                baseDrawable.setName(" (" + color + ")");
            }
        }
        return drawableTint;
    }

    public Skin(com.badlogic.gdx.files.a aVar, TextureAtlas textureAtlas) {
        Class[] clsArr = defaultTagClasses;
        this.jsonClassTags = new y<>(clsArr.length, 0);
        for (Class cls : clsArr) {
            this.jsonClassTags.j(cls.getSimpleName(), cls);
        }
        this.atlas = textureAtlas;
        addRegions(textureAtlas);
        load(aVar);
    }

    public Skin(TextureAtlas textureAtlas) {
        Class[] clsArr = defaultTagClasses;
        this.jsonClassTags = new y<>(clsArr.length, 0);
        for (Class cls : clsArr) {
            this.jsonClassTags.j(cls.getSimpleName(), cls);
        }
        this.atlas = textureAtlas;
        addRegions(textureAtlas);
    }
}
