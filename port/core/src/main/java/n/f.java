package n;

import android.content.Context;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.q;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import java.util.ArrayList;
import n.b;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f2466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f2467b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f2468a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f2469b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f2470c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final android.support.v4.util.i<Menu, Menu> f2471d = new android.support.v4.util.i<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f2469b = context;
            this.f2468a = callback;
        }

        @Override // n.b.a
        public final boolean a(b bVar, j jVar) {
            return this.f2468a.onActionItemClicked(d(bVar), q.b(this.f2469b, jVar));
        }

        @Override // n.b.a
        public final boolean b(b bVar, android.support.v7.view.menu.h hVar) {
            f fVarD = d(bVar);
            android.support.v4.util.i<Menu, Menu> iVar = this.f2471d;
            Menu menuA = iVar.get(hVar);
            if (menuA == null) {
                menuA = q.a(this.f2469b, hVar);
                iVar.put(hVar, menuA);
            }
            return this.f2468a.onPrepareActionMode(fVarD, menuA);
        }

        @Override // n.b.a
        public final void c(b bVar) {
            this.f2468a.onDestroyActionMode(d(bVar));
        }

        public final f d(b bVar) {
            ArrayList<f> arrayList = this.f2470c;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                f fVar = arrayList.get(i2);
                if (fVar != null && fVar.f2467b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f2469b, bVar);
            arrayList.add(fVar2);
            return fVar2;
        }

        public final boolean e(b bVar, android.support.v7.view.menu.h hVar) {
            f fVarD = d(bVar);
            android.support.v4.util.i<Menu, Menu> iVar = this.f2471d;
            Menu menuA = iVar.get(hVar);
            if (menuA == null) {
                menuA = q.a(this.f2469b, hVar);
                iVar.put(hVar, menuA);
            }
            return this.f2468a.onCreateActionMode(fVarD, menuA);
        }
    }

    public f(Context context, b bVar) {
        this.f2466a = context;
        this.f2467b = bVar;
    }

    @Override // android.view.ActionMode
    public final void finish() {
        this.f2467b.c();
    }

    @Override // android.view.ActionMode
    public final View getCustomView() {
        return this.f2467b.d();
    }

    @Override // android.view.ActionMode
    public final Menu getMenu() {
        return q.a(this.f2466a, this.f2467b.e());
    }

    @Override // android.view.ActionMode
    public final MenuInflater getMenuInflater() {
        return this.f2467b.f();
    }

    @Override // android.view.ActionMode
    public final CharSequence getSubtitle() {
        return this.f2467b.g();
    }

    @Override // android.view.ActionMode
    public final Object getTag() {
        return this.f2467b.h();
    }

    @Override // android.view.ActionMode
    public final CharSequence getTitle() {
        return this.f2467b.i();
    }

    @Override // android.view.ActionMode
    public final boolean getTitleOptionalHint() {
        return this.f2467b.j();
    }

    @Override // android.view.ActionMode
    public final void invalidate() {
        this.f2467b.k();
    }

    @Override // android.view.ActionMode
    public final boolean isTitleOptional() {
        return this.f2467b.l();
    }

    @Override // android.view.ActionMode
    public final void setCustomView(View view) {
        this.f2467b.m(view);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(CharSequence charSequence) {
        this.f2467b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTag(Object obj) {
        this.f2467b.p(obj);
    }

    @Override // android.view.ActionMode
    public final void setTitle(CharSequence charSequence) {
        this.f2467b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTitleOptionalHint(boolean z2) {
        this.f2467b.s(z2);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(int i2) {
        this.f2467b.n(i2);
    }

    @Override // android.view.ActionMode
    public final void setTitle(int i2) {
        this.f2467b.q(i2);
    }
}
