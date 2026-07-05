package android.support.v7.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.drive.MetadataChangeSet;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class AlertController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f680a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final android.support.v7.app.c f681b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Window f682c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private CharSequence f683d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    RecycleListView f684e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Button f685f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Button f686g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    Button f687h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    NestedScrollView f688i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Drawable f689j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private ImageView f690k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private TextView f691l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private TextView f692m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private View f693n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    ListAdapter f694o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f695p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    int f696q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    int f697r;
    private boolean s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    Handler f698t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final View.OnClickListener f699u = new a();

    public static class RecycleListView extends ListView {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f700b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f701c;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.j.RecycleListView);
            this.f701c = typedArrayObtainStyledAttributes.getDimensionPixelOffset(k.j.RecycleListView_paddingBottomNoButtons, -1);
            this.f700b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(k.j.RecycleListView_paddingTopNoTitle, -1);
        }

        public final void a(boolean z2, boolean z3) {
            if (z3 && z2) {
                return;
            }
            setPadding(getPaddingLeft(), z2 ? getPaddingTop() : this.f700b, getPaddingRight(), z3 ? getPaddingBottom() : this.f701c);
        }
    }

    final class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            AlertController alertController = AlertController.this;
            Button button = alertController.f685f;
            alertController.f698t.obtainMessage(1, alertController.f681b).sendToTarget();
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ContextThemeWrapper f703a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final LayoutInflater f704b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Drawable f705c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f706d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f707e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public DialogInterface.OnKeyListener f708f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ListAdapter f709g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public DialogInterface.OnClickListener f710h;

        public b(ContextThemeWrapper contextThemeWrapper) {
            this.f703a = contextThemeWrapper;
            this.f704b = (LayoutInflater) contextThemeWrapper.getSystemService("layout_inflater");
        }
    }

    private static final class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<DialogInterface> f711a;

        public c(o oVar) {
            this.f711a = new WeakReference<>(oVar);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == -3 || i2 == -2 || i2 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.f711a.get(), message.what);
            } else {
                if (i2 != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class d extends ArrayAdapter<CharSequence> {
        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public final long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public final boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, android.support.v7.app.c cVar, Window window) {
        this.f680a = context;
        this.f681b = cVar;
        this.f682c = window;
        this.f698t = new c(cVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, k.j.AlertDialog, k.a.alertDialogStyle, 0);
        this.f695p = typedArrayObtainStyledAttributes.getResourceId(k.j.AlertDialog_android_layout, 0);
        typedArrayObtainStyledAttributes.getResourceId(k.j.AlertDialog_buttonPanelSideLayout, 0);
        this.f696q = typedArrayObtainStyledAttributes.getResourceId(k.j.AlertDialog_listLayout, 0);
        typedArrayObtainStyledAttributes.getResourceId(k.j.AlertDialog_multiChoiceItemLayout, 0);
        typedArrayObtainStyledAttributes.getResourceId(k.j.AlertDialog_singleChoiceItemLayout, 0);
        this.f697r = typedArrayObtainStyledAttributes.getResourceId(k.j.AlertDialog_listItemLayout, 0);
        this.s = typedArrayObtainStyledAttributes.getBoolean(k.j.AlertDialog_showTitle, true);
        typedArrayObtainStyledAttributes.recycle();
        cVar.a().o(1);
    }

    private static ViewGroup b(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void a() {
        int i2;
        ListAdapter listAdapter;
        View viewFindViewById;
        this.f681b.setContentView(this.f695p);
        int i3 = k.f.parentPanel;
        Window window = this.f682c;
        View viewFindViewById2 = window.findViewById(i3);
        View viewFindViewById3 = viewFindViewById2.findViewById(k.f.topPanel);
        View viewFindViewById4 = viewFindViewById2.findViewById(k.f.contentPanel);
        View viewFindViewById5 = viewFindViewById2.findViewById(k.f.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById2.findViewById(k.f.customPanel);
        window.setFlags(MetadataChangeSet.INDEXABLE_TEXT_SIZE_LIMIT_BYTES, MetadataChangeSet.INDEXABLE_TEXT_SIZE_LIMIT_BYTES);
        viewGroup.setVisibility(8);
        View viewFindViewById6 = viewGroup.findViewById(k.f.topPanel);
        View viewFindViewById7 = viewGroup.findViewById(k.f.contentPanel);
        View viewFindViewById8 = viewGroup.findViewById(k.f.buttonPanel);
        ViewGroup viewGroupB = b(viewFindViewById6, viewFindViewById3);
        ViewGroup viewGroupB2 = b(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupB3 = b(viewFindViewById8, viewFindViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) window.findViewById(k.f.scrollView);
        this.f688i = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.f688i.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroupB2.findViewById(R.id.message);
        this.f692m = textView;
        if (textView != null) {
            textView.setVisibility(8);
            this.f688i.removeView(this.f692m);
            if (this.f684e != null) {
                ViewGroup viewGroup2 = (ViewGroup) this.f688i.getParent();
                int iIndexOfChild = viewGroup2.indexOfChild(this.f688i);
                viewGroup2.removeViewAt(iIndexOfChild);
                viewGroup2.addView(this.f684e, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
            } else {
                viewGroupB2.setVisibility(8);
            }
        }
        Button button = (Button) viewGroupB3.findViewById(R.id.button1);
        this.f685f = button;
        View.OnClickListener onClickListener = this.f699u;
        button.setOnClickListener(onClickListener);
        if (TextUtils.isEmpty(null)) {
            this.f685f.setVisibility(8);
            i2 = 0;
        } else {
            this.f685f.setText((CharSequence) null);
            this.f685f.setVisibility(0);
            i2 = 1;
        }
        Button button2 = (Button) viewGroupB3.findViewById(R.id.button2);
        this.f686g = button2;
        button2.setOnClickListener(onClickListener);
        if (TextUtils.isEmpty(null)) {
            this.f686g.setVisibility(8);
        } else {
            this.f686g.setText((CharSequence) null);
            this.f686g.setVisibility(0);
            i2 |= 2;
        }
        Button button3 = (Button) viewGroupB3.findViewById(R.id.button3);
        this.f687h = button3;
        button3.setOnClickListener(onClickListener);
        if (TextUtils.isEmpty(null)) {
            this.f687h.setVisibility(8);
        } else {
            this.f687h.setText((CharSequence) null);
            this.f687h.setVisibility(0);
            i2 |= 4;
        }
        TypedValue typedValue = new TypedValue();
        this.f680a.getTheme().resolveAttribute(k.a.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i2 == 1) {
                Button button4 = this.f685f;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button4.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.weight = 0.5f;
                button4.setLayoutParams(layoutParams);
            } else if (i2 == 2) {
                Button button5 = this.f686g;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                layoutParams2.gravity = 1;
                layoutParams2.weight = 0.5f;
                button5.setLayoutParams(layoutParams2);
            } else if (i2 == 4) {
                Button button6 = this.f687h;
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button6.getLayoutParams();
                layoutParams3.gravity = 1;
                layoutParams3.weight = 0.5f;
                button6.setLayoutParams(layoutParams3);
            }
        }
        if (i2 == 0) {
            viewGroupB3.setVisibility(8);
        }
        if (this.f693n != null) {
            viewGroupB.addView(this.f693n, 0, new ViewGroup.LayoutParams(-1, -2));
            window.findViewById(k.f.title_template).setVisibility(8);
        } else {
            this.f690k = (ImageView) window.findViewById(R.id.icon);
            if (TextUtils.isEmpty(this.f683d) || !this.s) {
                window.findViewById(k.f.title_template).setVisibility(8);
                this.f690k.setVisibility(8);
                viewGroupB.setVisibility(8);
            } else {
                TextView textView2 = (TextView) window.findViewById(k.f.alertTitle);
                this.f691l = textView2;
                textView2.setText(this.f683d);
                Drawable drawable = this.f689j;
                if (drawable != null) {
                    this.f690k.setImageDrawable(drawable);
                } else {
                    this.f691l.setPadding(this.f690k.getPaddingLeft(), this.f690k.getPaddingTop(), this.f690k.getPaddingRight(), this.f690k.getPaddingBottom());
                    this.f690k.setVisibility(8);
                }
            }
        }
        boolean z2 = viewGroup.getVisibility() != 8;
        boolean z3 = (viewGroupB == null || viewGroupB.getVisibility() == 8) ? 0 : 1;
        boolean z4 = viewGroupB3.getVisibility() != 8;
        if (!z4 && (viewFindViewById = viewGroupB2.findViewById(k.f.textSpacerNoButtons)) != null) {
            viewFindViewById.setVisibility(0);
        }
        if (z3 != 0) {
            NestedScrollView nestedScrollView2 = this.f688i;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View viewFindViewById9 = this.f684e != null ? viewGroupB.findViewById(k.f.titleDividerNoCustom) : null;
            if (viewFindViewById9 != null) {
                viewFindViewById9.setVisibility(0);
            }
        } else {
            View viewFindViewById10 = viewGroupB2.findViewById(k.f.textSpacerNoTitle);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        }
        RecycleListView recycleListView = this.f684e;
        if (recycleListView != null) {
            recycleListView.a(z3, z4);
        }
        if (!z2) {
            View view = this.f684e;
            if (view == null) {
                view = this.f688i;
            }
            if (view != null) {
                int i4 = z4 ? 2 : 0;
                View viewFindViewById11 = window.findViewById(k.f.scrollIndicatorUp);
                View viewFindViewById12 = window.findViewById(k.f.scrollIndicatorDown);
                view.setScrollIndicators(z3 | i4, 3);
                if (viewFindViewById11 != null) {
                    viewGroupB2.removeView(viewFindViewById11);
                }
                if (viewFindViewById12 != null) {
                    viewGroupB2.removeView(viewFindViewById12);
                }
            }
        }
        RecycleListView recycleListView2 = this.f684e;
        if (recycleListView2 == null || (listAdapter = this.f694o) == null) {
            return;
        }
        recycleListView2.setAdapter(listAdapter);
    }

    public final void c(View view) {
        this.f693n = view;
    }

    public final void d(Drawable drawable) {
        this.f689j = drawable;
        ImageView imageView = this.f690k;
        if (imageView != null) {
            if (drawable == null) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.f690k.setImageDrawable(drawable);
            }
        }
    }

    public final void e(CharSequence charSequence) {
        this.f683d = charSequence;
        TextView textView = this.f691l;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
