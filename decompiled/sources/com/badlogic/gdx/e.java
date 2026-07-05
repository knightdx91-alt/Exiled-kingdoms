package com.badlogic.gdx;

/* JADX INFO: compiled from: Game.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class e implements a {
    protected n screen;

    public final void a() {
        n nVar = this.screen;
        if (nVar != null) {
            nVar.c(Gdx.graphics.getDeltaTime());
        }
    }

    @Override // com.badlogic.gdx.a
    public void b(int i2, int i3) {
        n nVar = this.screen;
        if (nVar != null) {
            nVar.b(i2, i3);
        }
    }

    public final void c(n nVar) {
        this.screen = nVar;
        nVar.a();
        this.screen.b(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override // com.badlogic.gdx.a
    public void dispose() {
    }

    @Override // com.badlogic.gdx.a
    public void pause() {
    }

    @Override // com.badlogic.gdx.a
    public void resume() {
        n nVar = this.screen;
        if (nVar != null) {
            nVar.resume();
        }
    }
}
