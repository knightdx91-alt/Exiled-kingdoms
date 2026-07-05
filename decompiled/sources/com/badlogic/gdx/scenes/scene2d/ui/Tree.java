package com.badlogic.gdx.scenes.scene2d.ui;

import a0.p;
import a0.q;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Tree<N extends Node, V> extends WidgetGroup {
    private static final q tmp = new q();
    private ClickListener clickListener;
    private N foundNode;
    float iconSpacingLeft;
    float iconSpacingRight;
    float indentSpacing;
    private N overNode;
    float paddingLeft;
    float paddingRight;
    private float prefHeight;
    private float prefWidth;
    N rangeStart;
    final a<N> rootNodes;
    final Selection<N> selection;
    private boolean sizeInvalid;
    TreeStyle style;
    float ySpacing;

    public static class TreeStyle {
        public Drawable background;
        public Drawable minus;
        public Drawable minusOver;
        public Drawable over;
        public Drawable plus;
        public Drawable plusOver;
        public Drawable selection;

        public TreeStyle() {
        }

        public TreeStyle(Drawable drawable, Drawable drawable2, Drawable drawable3) {
            this.plus = drawable;
            this.minus = drawable2;
            this.selection = drawable3;
        }

        public TreeStyle(TreeStyle treeStyle) {
            this.plus = treeStyle.plus;
            this.minus = treeStyle.minus;
            this.plusOver = treeStyle.plusOver;
            this.minusOver = treeStyle.minusOver;
            this.over = treeStyle.over;
            this.selection = treeStyle.selection;
            this.background = treeStyle.background;
        }
    }

    public Tree(Skin skin) {
        this((TreeStyle) skin.get(TreeStyle.class));
    }

    private void computeSize() {
        this.sizeInvalid = false;
        float fPlusMinusWidth = plusMinusWidth();
        this.prefWidth = fPlusMinusWidth;
        this.prefHeight = 0.0f;
        computeSize(this.rootNodes, 0.0f, fPlusMinusWidth);
        this.prefWidth = this.paddingLeft + this.paddingRight + this.prefWidth;
    }

    private void initialize() {
        ClickListener clickListener = new ClickListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Tree.2
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
            @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f2, float f3) {
                N n2 = (N) Tree.this.getNodeAt(f3);
                if (n2 != null && n2 == Tree.this.getNodeAt(getTouchDownY())) {
                    if (Tree.this.selection.getMultiple() && Tree.this.selection.notEmpty() && UIUtils.shift()) {
                        Tree tree = Tree.this;
                        if (tree.rangeStart == null) {
                            tree.rangeStart = n2;
                        }
                        N n3 = tree.rangeStart;
                        if (!UIUtils.ctrl()) {
                            Tree.this.selection.clear();
                        }
                        float y2 = n3.actor.getY();
                        float y3 = n2.actor.getY();
                        if (y2 > y3) {
                            Tree tree2 = Tree.this;
                            tree2.selectNodes(tree2.rootNodes, y3, y2);
                        } else {
                            Tree tree3 = Tree.this;
                            tree3.selectNodes(tree3.rootNodes, y2, y3);
                            Tree.this.selection.items().l().s();
                        }
                        Tree.this.selection.fireChangeEvent();
                        Tree.this.rangeStart = n3;
                        return;
                    }
                    if (n2.children.f1750b > 0 && (!Tree.this.selection.getMultiple() || !UIUtils.ctrl())) {
                        float x2 = n2.actor.getX();
                        Drawable drawable = n2.icon;
                        if (drawable != null) {
                            x2 -= drawable.getMinWidth() + Tree.this.iconSpacingRight;
                        }
                        if (f2 < x2) {
                            n2.setExpanded(!n2.expanded);
                            return;
                        }
                    }
                    if (n2.isSelectable()) {
                        Tree.this.selection.choose(n2);
                        if (Tree.this.selection.isEmpty()) {
                            return;
                        }
                        Tree.this.rangeStart = n2;
                    }
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
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
            @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
                super.enter(inputEvent, f2, f3, i2, actor);
                Tree tree = Tree.this;
                tree.setOverNode(tree.getNodeAt(f3));
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
                super.exit(inputEvent, f2, f3, i2, actor);
                if (actor == null || !actor.isDescendantOf(Tree.this)) {
                    Tree.this.setOverNode(null);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
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
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f2, float f3) {
                Tree tree = Tree.this;
                tree.setOverNode(tree.getNodeAt(f3));
                return false;
            }
        };
        this.clickListener = clickListener;
        addListener(clickListener);
    }

    private float plusMinusWidth() {
        float fMax = Math.max(this.style.plus.getMinWidth(), this.style.minus.getMinWidth());
        Drawable drawable = this.style.plusOver;
        if (drawable != null) {
            fMax = Math.max(fMax, drawable.getMinWidth());
        }
        Drawable drawable2 = this.style.minusOver;
        return drawable2 != null ? Math.max(fMax, drawable2.getMinWidth()) : fMax;
    }

    public void add(N n2) {
        insert(this.rootNodes.f1750b, n2);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void clearChildren() {
        super.clearChildren();
        setOverNode(null);
        this.rootNodes.clear();
        this.selection.clear();
    }

    public void collapseAll() {
        collapseAll(this.rootNodes);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        drawBackground(batch, f2);
        Color color = getColor();
        batch.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
        draw(batch, this.rootNodes, this.paddingLeft, plusMinusWidth());
        super.draw(batch, f2);
    }

    protected void drawBackground(Batch batch, float f2) {
        if (this.style.background != null) {
            Color color = getColor();
            batch.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
            this.style.background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    protected void drawExpandIcon(N n2, Drawable drawable, Batch batch, float f2, float f3) {
        drawable.draw(batch, f2, f3, drawable.getMinWidth(), drawable.getMinHeight());
    }

    protected void drawIcon(N n2, Drawable drawable, Batch batch, float f2, float f3) {
        drawable.draw(batch, f2, f3, drawable.getMinWidth(), drawable.getMinHeight());
    }

    protected void drawOver(N n2, Drawable drawable, Batch batch, float f2, float f3, float f4, float f5) {
        drawable.draw(batch, f2, f3, f4, f5);
    }

    protected void drawSelection(N n2, Drawable drawable, Batch batch, float f2, float f3, float f4, float f5) {
        drawable.draw(batch, f2, f3, f4, f5);
    }

    public void expandAll() {
        expandAll(this.rootNodes);
    }

    public void findExpandedValues(a<V> aVar) {
        findExpandedValues(this.rootNodes, aVar);
    }

    public N findNode(V v2) {
        if (v2 != null) {
            return (N) findNode(this.rootNodes, v2);
        }
        throw new IllegalArgumentException("value cannot be null.");
    }

    public ClickListener getClickListener() {
        return this.clickListener;
    }

    protected Drawable getExpandIcon(N n2, float f2) {
        if (n2 == this.overNode && Gdx.app.getType() == Application.a.f1564b && (!this.selection.getMultiple() || (!UIUtils.ctrl() && !UIUtils.shift()))) {
            q qVar = tmp;
            qVar.f91a = Gdx.input.getX();
            qVar.f92b = 0.0f;
            float f3 = screenToLocalCoordinates(qVar).f91a;
            if (f3 >= 0.0f && f3 < f2) {
                Drawable drawable = n2.expanded ? this.style.minusOver : this.style.plusOver;
                if (drawable != null) {
                    return drawable;
                }
            }
        }
        return n2.expanded ? this.style.minus : this.style.plus;
    }

    public float getIndentSpacing() {
        return this.indentSpacing;
    }

    public N getNodeAt(float f2) {
        this.foundNode = null;
        getNodeAt(this.rootNodes, f2, getHeight());
        return this.foundNode;
    }

    public a<N> getNodes() {
        return this.rootNodes;
    }

    public N getOverNode() {
        return this.overNode;
    }

    public V getOverValue() {
        N n2 = this.overNode;
        if (n2 == null) {
            return null;
        }
        return (V) n2.getValue();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefWidth;
    }

    public a<N> getRootNodes() {
        return this.rootNodes;
    }

    public N getSelectedNode() {
        return this.selection.first();
    }

    public V getSelectedValue() {
        N nFirst = this.selection.first();
        if (nFirst == null) {
            return null;
        }
        return (V) nFirst.getValue();
    }

    public Selection<N> getSelection() {
        return this.selection;
    }

    public TreeStyle getStyle() {
        return this.style;
    }

    public float getYSpacing() {
        return this.ySpacing;
    }

    public void insert(int i2, N n2) {
        int iCountActors;
        N n3 = n2.parent;
        if (n3 != null) {
            n3.remove(n2);
            n2.parent = null;
        } else {
            int iH = this.rootNodes.h(n2, true);
            if (iH != -1) {
                if (iH == i2) {
                    return;
                }
                if (iH < i2) {
                    i2--;
                }
                this.rootNodes.o(iH);
                int zIndex = n2.actor.getZIndex();
                if (zIndex != -1) {
                    n2.removeFromTree(this, zIndex);
                }
            }
        }
        this.rootNodes.i(i2, n2);
        if (i2 == 0) {
            iCountActors = 0;
        } else {
            a<N> aVar = this.rootNodes;
            if (i2 < aVar.f1750b - 1) {
                iCountActors = aVar.get(i2 + 1).actor.getZIndex();
            } else {
                N n4 = aVar.get(i2 - 1);
                iCountActors = n4.countActors() + n4.actor.getZIndex();
            }
        }
        n2.addToTree(this, iCountActors);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        if (this.sizeInvalid) {
            computeSize();
        }
        layout(this.rootNodes, this.paddingLeft, getHeight() - (this.ySpacing / 2.0f), plusMinusWidth());
    }

    public void remove(N n2) {
        int zIndex;
        N n3 = n2.parent;
        if (n3 != null) {
            n3.remove(n2);
        } else if (this.rootNodes.q(n2, true) && (zIndex = n2.actor.getZIndex()) != -1) {
            n2.removeFromTree(this, zIndex);
        }
    }

    public void restoreExpandedValues(a<V> aVar) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Node nodeFindNode = findNode(aVar.get(i3));
            if (nodeFindNode != null) {
                nodeFindNode.setExpanded(true);
                nodeFindNode.expandTo();
            }
        }
    }

    void selectNodes(a<N> aVar, float f2, float f3) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            N n2 = aVar.get(i3);
            if (n2.actor.getY() < f2) {
                return;
            }
            if (n2.isSelectable()) {
                if (n2.actor.getY() <= f3) {
                    this.selection.add(n2);
                }
                if (n2.expanded) {
                    selectNodes(n2.children, f2, f3);
                }
            }
        }
    }

    public void setIconSpacing(float f2, float f3) {
        this.iconSpacingLeft = f2;
        this.iconSpacingRight = f3;
    }

    public void setIndentSpacing(float f2) {
        this.indentSpacing = f2;
    }

    public void setOverNode(N n2) {
        this.overNode = n2;
    }

    public void setPadding(float f2) {
        this.paddingLeft = f2;
        this.paddingRight = f2;
    }

    public void setStyle(TreeStyle treeStyle) {
        this.style = treeStyle;
        if (this.indentSpacing == 0.0f) {
            this.indentSpacing = plusMinusWidth();
        }
    }

    public void setYSpacing(float f2) {
        this.ySpacing = f2;
    }

    public void updateRootNodes() {
        int i2 = this.rootNodes.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            N n2 = this.rootNodes.get(i3);
            int zIndex = n2.actor.getZIndex();
            if (zIndex != -1) {
                n2.removeFromTree(this, zIndex);
            }
        }
        int i4 = this.rootNodes.f1750b;
        int iAddToTree = 0;
        for (int i5 = 0; i5 < i4; i5++) {
            iAddToTree += this.rootNodes.get(i5).addToTree(this, iAddToTree);
        }
    }

    public Tree(Skin skin, String str) {
        this((TreeStyle) skin.get(str, TreeStyle.class));
    }

    static void collapseAll(a<? extends Node> aVar) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Node node = aVar.get(i3);
            node.setExpanded(false);
            collapseAll(node.children);
        }
    }

    static void expandAll(a<? extends Node> aVar) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            aVar.get(i3).expandAll();
        }
    }

    static boolean findExpandedValues(a<? extends Node> aVar, a aVar2) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Node node = aVar.get(i3);
            if (node.expanded && !findExpandedValues(node.children, aVar2)) {
                aVar2.a(node.value);
            }
        }
        return false;
    }

    public Tree(TreeStyle treeStyle) {
        this.rootNodes = new a<>();
        this.ySpacing = 4.0f;
        this.iconSpacingLeft = 2.0f;
        this.iconSpacingRight = 2.0f;
        this.sizeInvalid = true;
        Selection<N> selection = (Selection<N>) new Selection<N>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Tree.1
            @Override // com.badlogic.gdx.scenes.scene2d.utils.Selection
            protected void changed() {
                int size = size();
                if (size == 0) {
                    Tree.this.rangeStart = null;
                } else {
                    if (size != 1) {
                        return;
                    }
                    Tree.this.rangeStart = (N) first();
                }
            }
        };
        this.selection = selection;
        selection.setActor(this);
        selection.setMultiple(true);
        setStyle(treeStyle);
        initialize();
    }

    static Node findNode(a<? extends Node> aVar, Object obj) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Node node = aVar.get(i3);
            if (obj.equals(node.value)) {
                return node;
            }
        }
        int i4 = aVar.f1750b;
        for (int i5 = 0; i5 < i4; i5++) {
            Node nodeFindNode = findNode(aVar.get(i5).children, obj);
            if (nodeFindNode != null) {
                return nodeFindNode;
            }
        }
        return null;
    }

    private float layout(a<N> aVar, float f2, float f3, float f4) {
        float f5 = this.ySpacing;
        float f6 = this.iconSpacingLeft;
        float f7 = this.iconSpacingRight + f6;
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            N n2 = aVar.get(i3);
            float f8 = f2 + f4;
            Drawable drawable = n2.icon;
            float minWidth = drawable != null ? drawable.getMinWidth() + f7 + f8 : f8 + f6;
            A a2 = n2.actor;
            if (a2 instanceof Layout) {
                ((Layout) a2).pack();
            }
            float height = f3 - n2.getHeight();
            n2.actor.setPosition(minWidth, height);
            f3 = height - f5;
            if (n2.expanded) {
                f3 = layout(n2.children, this.indentSpacing + f2, f3, f4);
            }
        }
        return f3;
    }

    public void setPadding(float f2, float f3) {
        this.paddingLeft = f2;
        this.paddingRight = f3;
    }

    public static abstract class Node<N extends Node, V, A extends Actor> {
        A actor;
        boolean expanded;
        float height;
        Drawable icon;
        N parent;
        V value;
        final a<N> children = new a<>(true, 0);
        boolean selectable = true;

        public Node(A a2) {
            if (a2 == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.actor = a2;
        }

        public void add(N n2) {
            insert(this.children.f1750b, n2);
        }

        public void addAll(a<N> aVar) {
            int i2 = aVar.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                insert(this.children.f1750b, aVar.get(i3));
            }
        }

        protected int addToTree(Tree<N, V> tree, int i2) {
            tree.addActorAt(i2, this.actor);
            if (!this.expanded) {
                return 1;
            }
            int iAddToTree = i2 + 1;
            a<N> aVar = this.children;
            N[] nArr = aVar.f1749a;
            int i3 = aVar.f1750b;
            for (int i4 = 0; i4 < i3; i4++) {
                iAddToTree += nArr[i4].addToTree(tree, iAddToTree);
            }
            return iAddToTree - i2;
        }

        public void clearChildren() {
            Tree<N, V> tree;
            if (this.expanded && (tree = getTree()) != null) {
                int zIndex = this.actor.getZIndex() + 1;
                a<N> aVar = this.children;
                N[] nArr = aVar.f1749a;
                int i2 = aVar.f1750b;
                for (int i3 = 0; i3 < i2; i3++) {
                    nArr[i3].removeFromTree(tree, zIndex);
                }
            }
            this.children.clear();
        }

        public void collapseAll() {
            setExpanded(false);
            Tree.collapseAll(this.children);
        }

        int countActors() {
            int iCountActors = 1;
            if (!this.expanded) {
                return 1;
            }
            a<N> aVar = this.children;
            N[] nArr = aVar.f1749a;
            int i2 = aVar.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                iCountActors += nArr[i3].countActors();
            }
            return iCountActors;
        }

        public void expandAll() {
            setExpanded(true);
            a<N> aVar = this.children;
            if (aVar.f1750b > 0) {
                Tree.expandAll(aVar);
            }
        }

        public void expandTo() {
            for (N n2 = this.parent; n2 != null; n2 = n2.parent) {
                n2.setExpanded(true);
            }
        }

        public void findExpandedValues(a<V> aVar) {
            if (!this.expanded || Tree.findExpandedValues(this.children, aVar)) {
                return;
            }
            aVar.a(this.value);
        }

        public N findNode(V v2) {
            if (v2 != null) {
                return v2.equals(this.value) ? this : (N) Tree.findNode(this.children, v2);
            }
            throw new IllegalArgumentException("value cannot be null.");
        }

        public A getActor() {
            return this.actor;
        }

        public a<N> getChildren() {
            return this.children;
        }

        public float getHeight() {
            return this.height;
        }

        public Drawable getIcon() {
            return this.icon;
        }

        public int getLevel() {
            int i2 = 0;
            Node<N, V, A> parent = this;
            do {
                i2++;
                parent = parent.getParent();
            } while (parent != null);
            return i2;
        }

        public N getParent() {
            return this.parent;
        }

        public Tree<N, V> getTree() {
            Group parent = this.actor.getParent();
            if (parent instanceof Tree) {
                return (Tree) parent;
            }
            return null;
        }

        public V getValue() {
            return this.value;
        }

        public boolean hasChildren() {
            return this.children.f1750b > 0;
        }

        public void insert(int i2, N n2) {
            Tree<N, V> tree;
            int iCountActors;
            n2.parent = this;
            this.children.i(i2, n2);
            if (this.expanded && (tree = getTree()) != null) {
                if (i2 == 0) {
                    iCountActors = this.actor.getZIndex() + 1;
                } else {
                    a<N> aVar = this.children;
                    if (i2 < aVar.f1750b - 1) {
                        iCountActors = aVar.get(i2 + 1).actor.getZIndex();
                    } else {
                        N n3 = aVar.get(i2 - 1);
                        iCountActors = n3.countActors() + n3.actor.getZIndex();
                    }
                }
                n2.addToTree(tree, iCountActors);
            }
        }

        public boolean isAscendantOf(N n2) {
            if (n2 == null) {
                throw new IllegalArgumentException("node cannot be null.");
            }
            while (n2 != this) {
                n2 = n2.parent;
                if (n2 == null) {
                    return false;
                }
            }
            return true;
        }

        public boolean isDescendantOf(N n2) {
            if (n2 == null) {
                throw new IllegalArgumentException("node cannot be null.");
            }
            N n3 = this;
            while (n3 != n2) {
                n3 = n3.parent;
                if (n3 == null) {
                    return false;
                }
            }
            return true;
        }

        public boolean isExpanded() {
            return this.expanded;
        }

        public boolean isSelectable() {
            return this.selectable;
        }

        public void remove() {
            Tree<N, V> tree = getTree();
            if (tree != null) {
                tree.remove(this);
                return;
            }
            N n2 = this.parent;
            if (n2 != null) {
                n2.remove(this);
            }
        }

        protected void removeFromTree(Tree<N, V> tree, int i2) {
            tree.removeActorAt(i2, true);
            if (this.expanded) {
                a<N> aVar = this.children;
                N[] nArr = aVar.f1749a;
                int i3 = aVar.f1750b;
                for (int i4 = 0; i4 < i3; i4++) {
                    nArr[i4].removeFromTree(tree, i2);
                }
            }
        }

        public void restoreExpandedValues(a<V> aVar) {
            int i2 = aVar.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                Node nodeFindNode = findNode(aVar.get(i3));
                if (nodeFindNode != null) {
                    nodeFindNode.setExpanded(true);
                    nodeFindNode.expandTo();
                }
            }
        }

        public void setActor(A a2) {
            Tree<N, V> tree;
            if (this.actor != null && (tree = getTree()) != null) {
                int zIndex = this.actor.getZIndex();
                tree.removeActorAt(zIndex, true);
                tree.addActorAt(zIndex, a2);
            }
            this.actor = a2;
        }

        public void setExpanded(boolean z2) {
            Tree<N, V> tree;
            if (z2 == this.expanded) {
                return;
            }
            this.expanded = z2;
            if (this.children.f1750b == 0 || (tree = getTree()) == null) {
                return;
            }
            N[] nArr = this.children.f1749a;
            int zIndex = this.actor.getZIndex() + 1;
            int i2 = 0;
            if (z2) {
                int i3 = this.children.f1750b;
                while (i2 < i3) {
                    zIndex += nArr[i2].addToTree(tree, zIndex);
                    i2++;
                }
                return;
            }
            int i4 = this.children.f1750b;
            while (i2 < i4) {
                nArr[i2].removeFromTree(tree, zIndex);
                i2++;
            }
        }

        public void setIcon(Drawable drawable) {
            this.icon = drawable;
        }

        public void setSelectable(boolean z2) {
            this.selectable = z2;
        }

        public void setValue(V v2) {
            this.value = v2;
        }

        public void updateChildren() {
            Tree<N, V> tree;
            if (this.expanded && (tree = getTree()) != null) {
                a<N> aVar = this.children;
                N[] nArr = aVar.f1749a;
                int i2 = aVar.f1750b;
                int zIndex = this.actor.getZIndex() + 1;
                for (int i3 = 0; i3 < i2; i3++) {
                    nArr[i3].removeFromTree(tree, zIndex);
                }
                for (int i4 = 0; i4 < i2; i4++) {
                    zIndex += nArr[i4].addToTree(tree, zIndex);
                }
            }
        }

        public void remove(N n2) {
            Tree<N, V> tree;
            if (this.children.q(n2, true) && this.expanded && (tree = getTree()) != null) {
                n2.removeFromTree(tree, n2.actor.getZIndex());
            }
        }

        public Node() {
        }
    }

    private float getNodeAt(a<N> aVar, float f2, float f3) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            N n2 = aVar.get(i3);
            float f4 = n2.height;
            float height = f3 - (n2.getHeight() - f4);
            float f5 = this.ySpacing;
            if (f2 >= (height - f4) - f5 && f2 < height) {
                this.foundNode = n2;
                return -1.0f;
            }
            f3 = height - (f4 + f5);
            if (n2.expanded) {
                f3 = getNodeAt(n2.children, f2, f3);
                if (f3 == -1.0f) {
                    return -1.0f;
                }
            }
        }
        return f3;
    }

    private void computeSize(a<N> aVar, float f2, float f3) {
        float width;
        float f4 = this.ySpacing;
        float f5 = this.iconSpacingLeft + this.iconSpacingRight;
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            N n2 = aVar.get(i3);
            float f6 = f2 + f3;
            A a2 = n2.actor;
            if (a2 instanceof Layout) {
                Layout layout = (Layout) a2;
                width = layout.getPrefWidth() + f6;
                n2.height = layout.getPrefHeight();
            } else {
                width = a2.getWidth() + f6;
                n2.height = a2.getHeight();
            }
            Drawable drawable = n2.icon;
            if (drawable != null) {
                width += drawable.getMinWidth() + f5;
                n2.height = Math.max(n2.height, n2.icon.getMinHeight());
            }
            this.prefWidth = Math.max(this.prefWidth, width);
            this.prefHeight = n2.height + f4 + this.prefHeight;
            if (n2.expanded) {
                computeSize(n2.children, this.indentSpacing + f2, f3);
            }
        }
    }

    private void draw(Batch batch, a<N> aVar, float f2, float f3) {
        float f4;
        float f5;
        float f6;
        Actor actor;
        N n2;
        int i2;
        int i3;
        float f7;
        Drawable drawable;
        float f8;
        Drawable drawable2;
        a<N> aVar2 = aVar;
        p cullingArea = getCullingArea();
        if (cullingArea != null) {
            float f9 = cullingArea.f90y;
            f4 = f9;
            f5 = cullingArea.height + f9;
        } else {
            f4 = 0.0f;
            f5 = 0.0f;
        }
        TreeStyle treeStyle = this.style;
        float x2 = getX();
        float y2 = getY();
        float f10 = x2 + f2;
        float f11 = f10 + f3 + this.iconSpacingLeft;
        int i4 = 0;
        for (int i5 = aVar2.f1750b; i4 < i5; i5 = i3) {
            N n3 = aVar2.get(i4);
            Actor actor2 = n3.actor;
            float y3 = actor2.getY();
            float f12 = n3.height;
            if (cullingArea == null || (y3 + f12 >= f4 && y3 <= f5)) {
                if (this.selection.contains(n3) && (drawable2 = treeStyle.selection) != null) {
                    f6 = f12;
                    actor = actor2;
                    n2 = n3;
                    i2 = i4;
                    i3 = i5;
                    f7 = f11;
                    drawSelection(n3, drawable2, batch, x2, (y2 + y3) - (this.ySpacing / 2.0f), getWidth(), f12 + this.ySpacing);
                } else {
                    f6 = f12;
                    actor = actor2;
                    n2 = n3;
                    i2 = i4;
                    i3 = i5;
                    f7 = f11;
                    if (n2 == this.overNode && (drawable = treeStyle.over) != null) {
                        drawOver(n2, drawable, batch, x2, (y2 + y3) - (this.ySpacing / 2.0f), getWidth(), f6 + this.ySpacing);
                    }
                }
                if (n2.icon != null) {
                    batch.setColor(actor.getColor());
                    drawIcon(n2, n2.icon, batch, f7, y2 + y3 + Math.round((f6 - r0.getMinHeight()) / 2.0f));
                    batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                }
                f8 = f7;
                if (n2.children.f1750b > 0) {
                    drawExpandIcon(n2, getExpandIcon(n2, f8), batch, f10, y2 + y3 + Math.round((f6 - r2.getMinHeight()) / 2.0f));
                }
            } else {
                if (y3 < f4) {
                    return;
                }
                n2 = n3;
                i2 = i4;
                i3 = i5;
                f8 = f11;
            }
            if (n2.expanded) {
                a<N> aVar3 = n2.children;
                if (aVar3.f1750b > 0) {
                    draw(batch, aVar3, f2 + this.indentSpacing, f3);
                }
            }
            i4 = i2 + 1;
            aVar2 = aVar;
            f11 = f8;
        }
    }
}
