package com.badlogic.gdx.utils;

/* JADX INFO: compiled from: FlushablePool.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class k<T> extends c0<T> {
    protected a<T> obtained;

    public k() {
        this.obtained = new a<>();
    }

    public void flush() {
        super.freeAll(this.obtained);
        this.obtained.clear();
    }

    @Override // com.badlogic.gdx.utils.c0
    public void free(T t2) {
        this.obtained.q(t2, true);
        super.free(t2);
    }

    @Override // com.badlogic.gdx.utils.c0
    public void freeAll(a<T> aVar) {
        this.obtained.n(aVar);
        super.freeAll(aVar);
    }

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
    @Override // com.badlogic.gdx.utils.c0
    public T obtain() {
        T t2 = (T) super.obtain();
        this.obtained.a(t2);
        return t2;
    }

    public k(int i2) {
        super(i2);
        this.obtained = new a<>();
    }

    public k(int i2, int i3) {
        super(i2, i3);
        this.obtained = new a<>();
    }
}
