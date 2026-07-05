package q0;

import android.util.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.google.android.gms.games.quest.Quests;
import n0.k1;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.android.MainActivity;

/* JADX INFO: compiled from: BackupWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a extends Window implements r {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static float f3784o = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Label f3785a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextButton f3786b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextButton f3787c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TextButton f3788d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextButton f3789e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextButton f3790f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Label f3791g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Label f3792h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Label f3793i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Label f3794j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private TextButton f3795k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private com.badlogic.gdx.e f3796l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Stage f3797m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private z f3798n;

    /* JADX INFO: renamed from: q0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BackupWindow.java */
    final class C0046a extends ChangeListener {

        /* JADX INFO: renamed from: q0.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: BackupWindow.java */
        final class C0047a extends n0.j {

            /* JADX INFO: renamed from: q0.a$a$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: BackupWindow.java */
            final class C0048a extends k1 {
            }

            C0047a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    C0046a c0046a = C0046a.this;
                    a.this.f3798n.a();
                    a aVar = a.this;
                    aVar.f3798n.toFront();
                    Serializer.c(false);
                    new C0048a(a.e("BACKUP_DONE"), 0).show(aVar.f3797m);
                    aVar.f3798n.setVisible(false);
                }
            }
        }

        C0046a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            C0047a c0047a = new C0047a(a.e("BACKUP_INFO"));
            a aVar = a.this;
            c0047a.show(aVar.f3797m);
            aVar.setVisible(false);
        }
    }

    /* JADX INFO: compiled from: BackupWindow.java */
    final class b extends n0.j {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ byte[] f3801c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f3802d;

        /* JADX INFO: renamed from: q0.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: BackupWindow.java */
        final class C0049a extends k1 {
        }

        /* JADX INFO: renamed from: q0.a$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: BackupWindow.java */
        final class C0050b extends k1 {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, byte[] bArr, String str2) {
            super(str);
            this.f3801c = bArr;
            this.f3802d = str2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            a aVar = a.this;
            if (!zBooleanValue) {
                new C0050b("Operation aborted by the user", 0).show(aVar.f3797m);
                return;
            }
            com.badlogic.gdx.files.a aVar2 = Serializer.f3225b;
            Gdx.files.external("EK_GPGS.bak").writeBytes(this.f3801c, false);
            Serializer.x(true);
            Gdx.files.external("EK_GPGS.bak").delete();
            new C0049a(this.f3802d, 0).show(aVar.f3797m);
            if (ExiledKingdoms.f3387q) {
                ((MainActivity) ExiledKingdoms.f()).n();
            }
            Serializer.G();
            ExiledKingdoms.i();
            l0.e.u();
        }
    }

    /* JADX INFO: compiled from: BackupWindow.java */
    final class c extends ChangeListener {

        /* JADX INFO: renamed from: q0.a$c$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: BackupWindow.java */
        final class C0051a extends n0.j {

            /* JADX INFO: renamed from: q0.a$c$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: BackupWindow.java */
            final class C0052a extends k1 {
            }

            C0051a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    c cVar = c.this;
                    a.this.f3798n.a();
                    a aVar = a.this;
                    aVar.f3798n.toFront();
                    Serializer.x(false);
                    l0.e.u();
                    new C0052a(a.e("BACKUP_RESTORED"), 0).show(aVar.f3797m);
                    Serializer.G();
                    aVar.f3798n.setVisible(false);
                }
            }
        }

        /* JADX INFO: compiled from: BackupWindow.java */
        final class b extends k1 {
        }

        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            boolean zD = Serializer.d();
            a aVar = a.this;
            if (zD) {
                new C0051a(a.e("BACKUP_RESTORE_CONFIRM")).show(aVar.f3797m);
            } else {
                new b(a.e("BACKUP_RESTORE_NOFILE"), 0).show(aVar.f3797m);
            }
            Serializer.G();
            aVar.setVisible(false);
        }
    }

    /* JADX INFO: compiled from: BackupWindow.java */
    final class d extends ChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f3806a;

        d(a aVar) {
            this.f3806a = aVar;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            a aVar = a.this;
            aVar.f3798n.a();
            aVar.f3798n.toFront();
            Serializer.c(true);
            byte[] bytes = Gdx.files.external("EK_GPGS.bak").readBytes();
            Gdx.files.external("EK_GPGS.bak").delete();
            if (bytes == null || bytes.length < 100) {
                aVar.f(k.f3813c, Quests.SELECT_COMPLETED_UNCLAIMED);
                return;
            }
            ((MainActivity) ExiledKingdoms.f()).b(this.f3806a, bytes);
        }
    }

    /* JADX INFO: compiled from: BackupWindow.java */
    final class e extends ChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f3808a;

        e(a aVar) {
            this.f3808a = aVar;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            a aVar = a.this;
            aVar.f3798n.a();
            aVar.f3798n.toFront();
            ((MainActivity) ExiledKingdoms.f()).a(this.f3808a);
        }
    }

    /* JADX INFO: compiled from: BackupWindow.java */
    final class f extends ChangeListener {
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            l0.e.t();
        }
    }

    /* JADX INFO: compiled from: BackupWindow.java */
    final class g extends ClickListener {
        g() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            ExiledKingdoms.i();
            a.this.setVisible(false);
        }
    }

    /* JADX INFO: compiled from: BackupWindow.java */
    final class h extends k1 {
    }

    /* JADX INFO: compiled from: BackupWindow.java */
    final class i extends k1 {
    }

    /* JADX INFO: compiled from: BackupWindow.java */
    final class j extends k1 {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: BackupWindow.java */
    public static final class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final k f3811a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final k f3812b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final k f3813c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final k f3814d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final /* synthetic */ k[] f3815e;

        static {
            k kVar = new k("CLOUD_UPLOAD_OK", 0);
            f3811a = kVar;
            k kVar2 = new k("CLOUD_DOWNLOAD_OK", 1);
            f3812b = kVar2;
            k kVar3 = new k("CLOUD_UPLOAD_ERROR", 2);
            f3813c = kVar3;
            k kVar4 = new k("CLOUD_DOWNLOAD_ERROR", 3);
            f3814d = kVar4;
            f3815e = new k[]{kVar, kVar2, kVar3, kVar4};
        }

        private k() {
            throw null;
        }

        public static k valueOf(String str) {
            return (k) Enum.valueOf(k.class, str);
        }

        public static k[] values() {
            return (k[]) f3815e.clone();
        }
    }

    public a(com.badlogic.gdx.e eVar, z zVar) {
        super("", Assets.g());
        Label label = new Label(GameString.b("BACKUP_TITLE", false), Assets.g(), "menuLabelStrongLargeStyle");
        this.f3785a = label;
        TextButton textButton = new TextButton(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f3786b = textButton;
        TextButton textButton2 = new TextButton(GameString.b("BACKUP_CLOUD", false), Assets.g(), "menuButton");
        this.f3787c = textButton2;
        TextButton textButton3 = new TextButton(GameString.b("RESTORE_BACKUP_CLOUD", false), Assets.g(), "menuButton");
        this.f3788d = textButton3;
        TextButton textButton4 = new TextButton("", Assets.g(), "menuButton");
        this.f3789e = textButton4;
        TextButton textButton5 = new TextButton("", Assets.g(), "menuButton");
        this.f3790f = textButton5;
        Label label2 = new Label(GameString.b("BACKUP_INFO_TEXT", false), GameAssets.S);
        this.f3791g = label2;
        Label label3 = new Label(a.a.o("BACKUP_INFO_FILE_TEXT_2", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.S);
        this.f3792h = label3;
        Label label4 = new Label(a.a.o("BACKUP_INFO_CLOUD_TEXT", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.S);
        this.f3793i = label4;
        Label label5 = new Label(GameString.b("DISCONNECTED_GPLAY_CLOUD", false), GameAssets.S);
        this.f3794j = label5;
        TextButton textButton6 = new TextButton(GameString.b("CONNECT", false), Assets.g(), "menuButton");
        this.f3795k = textButton6;
        Image image = new Image(Assets.a("gpgs"));
        Image image2 = new Image();
        Image image3 = new Image();
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(Gdx.graphics.getWidth() * 0.9f);
        setHeight(Gdx.graphics.getHeight() * 0.95f);
        if (getHeight() * 1.3f > getWidth()) {
            setHeight(Gdx.graphics.getHeight() * 0.75f);
        }
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.8f);
        float f2 = f3784o;
        label5.setFontScale(f2);
        label5.setWrap(true);
        label2.setFontScale(f2);
        label2.setWrap(true);
        label3.setFontScale(f2);
        label3.setWrap(true);
        label4.setFontScale(f2);
        label4.setWrap(true);
        this.f3796l = eVar;
        this.f3798n = zVar;
        image2.setDrawable(new NinePatchDrawable(GameAssets.P));
        image3.setDrawable(new NinePatchDrawable(GameAssets.P));
        label.setFontScale(f2);
        center();
        float f3 = 12.0f * f2;
        row().height(35.0f * f2).space(f3).colspan(2);
        add(label).center();
        float f4 = 15.0f * f2;
        row().space(f4).colspan(2);
        add(label2).center().width(getWidth() * 0.96f);
        float f5 = 6.0f * f2;
        row().colspan(2).left().pad(0.0f).height(2.0f).width(getWidth() - f5);
        add(image2).space(f3);
        float f6 = 10.0f * f2;
        row().space(f6).colspan(2);
        add(label3).center().width(getWidth() * 0.96f);
        float f7 = 13.0f * f2;
        row().height(52.0f * f2).space(f7);
        float f8 = 570.0f * f2;
        add(textButton4).width(f8);
        add(textButton5).width(f8);
        row().colspan(2).left().pad(0.0f).height(2.0f).width(getWidth() - f5);
        add(image3).space(f3);
        row().space(f6).colspan(2);
        add(label4).center().width(getWidth() * 0.96f);
        row().height(50.0f * f2).space(f7);
        add(textButton2).width(f8);
        add(textButton3).width(f8);
        textButton4.addListener(new C0046a());
        textButton5.addListener(new c());
        textButton2.addListener(new d(this));
        textButton3.addListener(new e(this));
        textButton6.addListener(new f());
        float f9 = 130.0f * f2;
        row().colspan(2).height(f9);
        Table table = new Table();
        float f10 = 90.0f * f2;
        Cell cellHeight = table.add(image).width(f10).height(f10);
        float f11 = 5.0f * f2;
        cellHeight.pad(f11);
        table.add(label5).width(540.0f * f2).pad(f11);
        table.add(textButton6).width(200.0f * f2).pad(f11);
        add(table);
        row().colspan(2).height(55.0f * f2).top();
        add(textButton).width(f9).height(f2 * 48.0f).space(f4);
        textButton.clearListeners();
        textButton.addListener(new g());
        setVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(String str) {
        if (ExiledKingdoms.f3388r) {
            return GameString.b(str, false).replace("##BACKUP##", "EK.bak");
        }
        if (ExiledKingdoms.f3387q) {
            return GameString.b(str, false).replace("##BACKUP##", "android/data/net.fdgames.ek.android/files/EK.bak");
        }
        return GameString.b(str, false).replace("##BACKUP##", Gdx.files.getExternalStoragePath() + "EK.bak");
    }

    private static String i(k kVar, int i2, String str) {
        int iOrdinal = kVar.ordinal();
        if (iOrdinal == 0) {
            return GameString.b("CLOUD_UPLOAD_OK", false);
        }
        if (iOrdinal == 1) {
            return GameString.b("CLOUD_DOWNLOAD_OK", false);
        }
        if (iOrdinal == 2) {
            return GameString.b("CLOUD_ERROR", false).replace("{e}", " " + i2 + " - " + str);
        }
        if (iOrdinal != 3) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(GameString.b("CLOUD_ERROR", false).replace("{e}", " " + i2));
        return a.a.m(" - ", str, sb);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        boolean zL = ((MainActivity) ExiledKingdoms.f()).l();
        TextButton textButton = this.f3788d;
        TextButton textButton2 = this.f3787c;
        TextButton textButton3 = this.f3795k;
        Label label = this.f3794j;
        if (zL) {
            if (ExiledKingdoms.f3388r) {
                label.setText(GameString.b("CONNECTED_GPLAY_CLOUD_IOS", false));
            } else {
                label.setText(GameString.b("CONNECTED_GPLAY_CLOUD", false));
            }
            textButton3.getLabel().setText(GameString.b("DISCONNECT", false));
            if (ExiledKingdoms.f3383m) {
                textButton2.setDisabled(false);
            }
            textButton.setDisabled(false);
        } else {
            if (ExiledKingdoms.f3388r) {
                label.setText(GameString.b("DISCONNECTED_GPLAY_CLOUD_IOS", false));
            } else {
                label.setText(GameString.b("DISCONNECTED_GPLAY_CLOUD", false));
            }
            textButton3.getLabel().setText(GameString.b("CONNECT", false));
            textButton2.setDisabled(true);
            textButton.setDisabled(true);
        }
        super.draw(batch, f2);
    }

    public final void f(k kVar, int i2) {
        this.f3798n.setVisible(false);
        new h(i(kVar, i2, ""), 0).show(this.f3797m);
        if (ExiledKingdoms.f3387q) {
            ((MainActivity) ExiledKingdoms.f()).n();
        }
    }

    public final void g(k kVar, int i2) {
        h(kVar, i2, null, "");
    }

    public final void h(k kVar, int i2, byte[] bArr, String str) {
        this.f3798n.setVisible(false);
        String strI = i(kVar, i2, str);
        if (i2 != 0 || (bArr != null && bArr.length >= 200)) {
            if (i2 > 0) {
                new j(strI, 0).show(this.f3797m);
                return;
            } else {
                new b(a.a.l("This is the data available:\n\r[BLUE]", str, "[].\n\rRestore it overwriting your current data?"), bArr, strI).show(this.f3797m);
                return;
            }
        }
        int length = bArr.length;
        k kVar2 = k.f3814d;
        new i(a.a.m(i(kVar2, 14, str), " (No valid save found)", new StringBuilder()), 0).show(this.f3797m);
    }

    @Override // q0.r
    public final void show(Stage stage) {
        boolean z2 = ExiledKingdoms.f3387q;
        com.badlogic.gdx.e eVar = this.f3796l;
        if (z2) {
            if (((MainActivity) ExiledKingdoms.f()).checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                MainActivity mainActivity = (MainActivity) ExiledKingdoms.f();
                if (mainActivity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                    Log.v("EK", "Permission is granted");
                } else {
                    Log.v("EK", "Permission is revoked");
                    mainActivity.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                }
            }
        }
        this.f3797m = stage;
        if (ExiledKingdoms.f3387q) {
            ((MainActivity) ExiledKingdoms.f()).n();
        }
        if (ExiledKingdoms.f3387q) {
            if (!((MainActivity) ExiledKingdoms.f()).m()) {
                ((MainActivity) ExiledKingdoms.f()).h();
            }
        }
        ExiledKingdoms.i();
        this.f3785a.setText(GameString.b("BACKUP_TITLE", false));
        this.f3786b.setText(GameString.b("EXIT", false));
        TextButton textButton = this.f3789e;
        textButton.setText(e("BACKUP"));
        this.f3790f.setText(GameString.b("RESTORE_BACKUP", false));
        TextButton textButton2 = this.f3787c;
        textButton2.setText(GameString.b("BACKUP_CLOUD", false));
        this.f3788d.setText(GameString.b("RESTORE_BACKUP_CLOUD", false));
        boolean z3 = ExiledKingdoms.f3388r;
        Label label = this.f3792h;
        if (z3) {
            a.a.x("BACKUP_INFO_FILE_TEXT_IOS", false, new StringBuilder("[BLACK]"), "[]", label);
        } else if (ExiledKingdoms.f3387q) {
            a.a.x("BACKUP_INFO_FILE_TEXT_ANDROID", false, new StringBuilder("[BLACK]"), "[]", label);
        } else {
            a.a.x("BACKUP_INFO_FILE_TEXT", false, new StringBuilder("[BLACK]"), "[]", label);
        }
        a.a.x("BACKUP_INFO_TEXT", false, new StringBuilder("[BLACK]"), "[]", this.f3791g);
        this.f3793i.setText("[BLACK]" + GameString.b("BACKUP_INFO_CLOUD_TEXT", false) + "[]");
        if (Serializer.E()) {
            textButton.setDisabled(false);
        } else {
            textButton.setDisabled(true);
            textButton2.setDisabled(true);
        }
        setVisible(true);
    }
}
