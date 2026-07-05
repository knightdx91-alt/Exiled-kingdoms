package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.IFragmentWrapper;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
@SuppressLint({"NewApi"})
public final class FragmentWrapper extends IFragmentWrapper.Stub {
    private Fragment zzhy;

    private FragmentWrapper(Fragment fragment) {
        this.zzhy = fragment;
    }

    @KeepForSdk
    public static FragmentWrapper wrap(Fragment fragment) {
        if (fragment != null) {
            return new FragmentWrapper(fragment);
        }
        return null;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final Bundle getArguments() {
        return this.zzhy.getArguments();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final int getId() {
        return this.zzhy.getId();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean getRetainInstance() {
        return this.zzhy.getRetainInstance();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final String getTag() {
        return this.zzhy.getTag();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final int getTargetRequestCode() {
        return this.zzhy.getTargetRequestCode();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean getUserVisibleHint() {
        return this.zzhy.getUserVisibleHint();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean isAdded() {
        return this.zzhy.isAdded();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean isDetached() {
        return this.zzhy.isDetached();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean isHidden() {
        return this.zzhy.isHidden();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean isInLayout() {
        return this.zzhy.isInLayout();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean isRemoving() {
        return this.zzhy.isRemoving();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean isResumed() {
        return this.zzhy.isResumed();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean isVisible() {
        return this.zzhy.isVisible();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void setHasOptionsMenu(boolean z2) {
        this.zzhy.setHasOptionsMenu(z2);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void setMenuVisibility(boolean z2) {
        this.zzhy.setMenuVisibility(z2);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void setRetainInstance(boolean z2) {
        this.zzhy.setRetainInstance(z2);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void setUserVisibleHint(boolean z2) {
        this.zzhy.setUserVisibleHint(z2);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void startActivity(Intent intent) {
        this.zzhy.startActivity(intent);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void startActivityForResult(Intent intent, int i2) {
        this.zzhy.startActivityForResult(intent, i2);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zza(IObjectWrapper iObjectWrapper) {
        this.zzhy.registerForContextMenu((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final IObjectWrapper zzad() {
        return ObjectWrapper.wrap(this.zzhy.getActivity());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final IFragmentWrapper zzae() {
        return wrap(this.zzhy.getParentFragment());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final IObjectWrapper zzaf() {
        return ObjectWrapper.wrap(this.zzhy.getResources());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final IFragmentWrapper zzag() {
        return wrap(this.zzhy.getTargetFragment());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final IObjectWrapper zzah() {
        return ObjectWrapper.wrap(this.zzhy.getView());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzb(IObjectWrapper iObjectWrapper) {
        this.zzhy.unregisterForContextMenu((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
