package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.p;
import android.support.v7.widget.x0;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ListMenuItemView extends LinearLayout implements p.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private j f852b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ImageView f853c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private RadioButton f854d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f855e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CheckBox f856f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f857g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ImageView f858h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Drawable f859i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f860j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Context f861k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f862l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Drawable f863m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private LayoutInflater f864n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f865o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i2 = k.a.listMenuViewStyle;
        x0 x0VarT = x0.t(getContext(), attributeSet, k.j.MenuView, i2);
        this.f859i = x0VarT.f(k.j.MenuView_android_itemBackground);
        this.f860j = x0VarT.m(k.j.MenuView_android_itemTextAppearance, -1);
        this.f862l = x0VarT.a(k.j.MenuView_preserveIconSpacing, false);
        this.f861k = context;
        this.f863m = x0VarT.f(k.j.MenuView_subMenuArrow);
        x0VarT.u();
    }

    private LayoutInflater getInflater() {
        if (this.f864n == null) {
            this.f864n = LayoutInflater.from(getContext());
        }
        return this.f864n;
    }

    private void setSubMenuArrowVisible(boolean z2) {
        ImageView imageView = this.f858h;
        if (imageView != null) {
            imageView.setVisibility(z2 ? 0 : 8);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    @Override // android.support.v7.view.menu.p.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void a(j jVar) {
        int i2;
        String string;
        this.f852b = jVar;
        setVisibility(jVar.isVisible() ? 0 : 8);
        setTitle(jVar.f(this));
        setCheckable(jVar.isCheckable());
        boolean z2 = jVar.f965n.t() && jVar.e() != 0;
        jVar.e();
        if (z2) {
            j jVar2 = this.f852b;
            i2 = (!jVar2.f965n.t() || jVar2.e() == 0) ? 8 : 0;
        }
        if (i2 == 0) {
            TextView textView = this.f857g;
            char cE = this.f852b.e();
            if (cE == 0) {
                string = "";
            } else {
                StringBuilder sb = new StringBuilder((String) null);
                if (cE == '\b' || cE == '\n' || cE == ' ') {
                    sb.append((String) null);
                } else {
                    sb.append(cE);
                }
                string = sb.toString();
            }
            textView.setText(string);
        }
        if (this.f857g.getVisibility() != i2) {
            this.f857g.setVisibility(i2);
        }
        setIcon(jVar.getIcon());
        setEnabled(jVar.isEnabled());
        setSubMenuArrowVisible(jVar.hasSubMenu());
        setContentDescription(jVar.getContentDescription());
    }

    @Override // android.support.v7.view.menu.p.a
    public final boolean b() {
        return false;
    }

    @Override // android.support.v7.view.menu.p.a
    public j getItemData() {
        return this.f852b;
    }

    @Override // android.view.View
    protected final void onFinishInflate() {
        super.onFinishInflate();
        setBackground(this.f859i);
        TextView textView = (TextView) findViewById(k.f.title);
        this.f855e = textView;
        int i2 = this.f860j;
        if (i2 != -1) {
            textView.setTextAppearance(this.f861k, i2);
        }
        this.f857g = (TextView) findViewById(k.f.shortcut);
        ImageView imageView = (ImageView) findViewById(k.f.submenuarrow);
        this.f858h = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f863m);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected final void onMeasure(int i2, int i3) {
        if (this.f853c != null && this.f862l) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f853c.getLayoutParams();
            int i4 = layoutParams.height;
            if (i4 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i4;
            }
        }
        super.onMeasure(i2, i3);
    }

    public void setCheckable(boolean z2) {
        CompoundButton compoundButton;
        View view;
        if (!z2 && this.f854d == null && this.f856f == null) {
            return;
        }
        if (this.f852b.j()) {
            if (this.f854d == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(k.g.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f854d = radioButton;
                addView(radioButton);
            }
            compoundButton = this.f854d;
            view = this.f856f;
        } else {
            if (this.f856f == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(k.g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f856f = checkBox;
                addView(checkBox);
            }
            compoundButton = this.f856f;
            view = this.f854d;
        }
        if (!z2) {
            CheckBox checkBox2 = this.f856f;
            if (checkBox2 != null) {
                checkBox2.setVisibility(8);
            }
            RadioButton radioButton2 = this.f854d;
            if (radioButton2 != null) {
                radioButton2.setVisibility(8);
                return;
            }
            return;
        }
        compoundButton.setChecked(this.f852b.isChecked());
        int i2 = z2 ? 0 : 8;
        if (compoundButton.getVisibility() != i2) {
            compoundButton.setVisibility(i2);
        }
        if (view == null || view.getVisibility() == 8) {
            return;
        }
        view.setVisibility(8);
    }

    public void setChecked(boolean z2) {
        CompoundButton compoundButton;
        if (this.f852b.j()) {
            if (this.f854d == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(k.g.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f854d = radioButton;
                addView(radioButton);
            }
            compoundButton = this.f854d;
        } else {
            if (this.f856f == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(k.g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f856f = checkBox;
                addView(checkBox);
            }
            compoundButton = this.f856f;
        }
        compoundButton.setChecked(z2);
    }

    public void setForceShowIcon(boolean z2) {
        this.f865o = z2;
        this.f862l = z2;
    }

    public void setIcon(Drawable drawable) {
        this.f852b.f965n.getClass();
        boolean z2 = this.f865o;
        if (z2 || this.f862l) {
            ImageView imageView = this.f853c;
            if (imageView == null && drawable == null && !this.f862l) {
                return;
            }
            if (imageView == null) {
                ImageView imageView2 = (ImageView) getInflater().inflate(k.g.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.f853c = imageView2;
                addView(imageView2, 0);
            }
            if (drawable == null && !this.f862l) {
                this.f853c.setVisibility(8);
                return;
            }
            ImageView imageView3 = this.f853c;
            if (!z2) {
                drawable = null;
            }
            imageView3.setImageDrawable(drawable);
            if (this.f853c.getVisibility() != 0) {
                this.f853c.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f855e.getVisibility() != 8) {
                this.f855e.setVisibility(8);
            }
        } else {
            this.f855e.setText(charSequence);
            if (this.f855e.getVisibility() != 0) {
                this.f855e.setVisibility(0);
            }
        }
    }
}
