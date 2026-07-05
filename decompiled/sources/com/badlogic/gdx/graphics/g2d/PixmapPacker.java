package com.badlogic.gdx.graphics.g2d;

import a0.p;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.a0;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PixmapPacker implements i {
    static Pattern indexPattern = Pattern.compile("(.+)_(\\d+)$");
    int alphaThreshold;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Color f1689c;
    boolean disposed;
    boolean duplicateBorder;
    PackStrategy packStrategy;
    boolean packToTexture;
    int padding;
    Pixmap.Format pageFormat;
    int pageHeight;
    int pageWidth;
    final a<Page> pages;
    boolean stripWhitespaceX;
    boolean stripWhitespaceY;
    Color transparentColor;

    public static class GuillotineStrategy implements PackStrategy {
        Comparator<Pixmap> comparator;

        static class GuillotinePage extends Page {
            Node root;

            public GuillotinePage(PixmapPacker pixmapPacker) {
                super(pixmapPacker);
                Node node = new Node();
                this.root = node;
                p pVar = node.rect;
                int i2 = pixmapPacker.padding;
                pVar.f89x = i2;
                pVar.f90y = i2;
                pVar.width = pixmapPacker.pageWidth - (i2 * 2);
                pVar.height = pixmapPacker.pageHeight - (i2 * 2);
            }
        }

        static final class Node {
            public boolean full;
            public Node leftChild;
            public final p rect = new p();
            public Node rightChild;

            Node() {
            }
        }

        private Node insert(Node node, p pVar) {
            Node node2;
            boolean z2 = node.full;
            if (!z2 && (node2 = node.leftChild) != null && node.rightChild != null) {
                Node nodeInsert = insert(node2, pVar);
                return nodeInsert == null ? insert(node.rightChild, pVar) : nodeInsert;
            }
            if (z2) {
                return null;
            }
            p pVar2 = node.rect;
            float f2 = pVar2.width;
            float f3 = pVar.width;
            if (f2 == f3 && pVar2.height == pVar.height) {
                return node;
            }
            if (f2 < f3 || pVar2.height < pVar.height) {
                return null;
            }
            node.leftChild = new Node();
            Node node3 = new Node();
            node.rightChild = node3;
            p pVar3 = node.rect;
            float f4 = pVar3.width;
            float f5 = pVar.width;
            int i2 = ((int) f4) - ((int) f5);
            float f6 = pVar3.height;
            float f7 = pVar.height;
            if (i2 > ((int) f6) - ((int) f7)) {
                p pVar4 = node.leftChild.rect;
                pVar4.f89x = pVar3.f89x;
                pVar4.f90y = pVar3.f90y;
                pVar4.width = f5;
                pVar4.height = f6;
                p pVar5 = node3.rect;
                float f8 = pVar3.f89x;
                float f9 = pVar.width;
                pVar5.f89x = f8 + f9;
                pVar5.f90y = pVar3.f90y;
                pVar5.width = pVar3.width - f9;
                pVar5.height = pVar3.height;
            } else {
                p pVar6 = node.leftChild.rect;
                pVar6.f89x = pVar3.f89x;
                pVar6.f90y = pVar3.f90y;
                pVar6.width = f4;
                pVar6.height = f7;
                p pVar7 = node3.rect;
                pVar7.f89x = pVar3.f89x;
                float f10 = pVar3.f90y;
                float f11 = pVar.height;
                pVar7.f90y = f10 + f11;
                pVar7.width = pVar3.width;
                pVar7.height = pVar3.height - f11;
            }
            return insert(node.leftChild, pVar);
        }

        @Override // com.badlogic.gdx.graphics.g2d.PixmapPacker.PackStrategy
        public Page pack(PixmapPacker pixmapPacker, String str, p pVar) {
            GuillotinePage guillotinePage;
            a<Page> aVar = pixmapPacker.pages;
            if (aVar.f1750b == 0) {
                guillotinePage = new GuillotinePage(pixmapPacker);
                pixmapPacker.pages.a(guillotinePage);
            } else {
                guillotinePage = (GuillotinePage) aVar.k();
            }
            float f2 = pixmapPacker.padding;
            pVar.width += f2;
            pVar.height += f2;
            Node nodeInsert = insert(guillotinePage.root, pVar);
            if (nodeInsert == null) {
                guillotinePage = new GuillotinePage(pixmapPacker);
                pixmapPacker.pages.a(guillotinePage);
                nodeInsert = insert(guillotinePage.root, pVar);
            }
            nodeInsert.full = true;
            p pVar2 = nodeInsert.rect;
            pVar.set(pVar2.f89x, pVar2.f90y, pVar2.width - f2, pVar2.height - f2);
            return guillotinePage;
        }

        @Override // com.badlogic.gdx.graphics.g2d.PixmapPacker.PackStrategy
        public void sort(a<Pixmap> aVar) {
            if (this.comparator == null) {
                this.comparator = new Comparator<Pixmap>() { // from class: com.badlogic.gdx.graphics.g2d.PixmapPacker.GuillotineStrategy.1
                    @Override // java.util.Comparator
                    public int compare(Pixmap pixmap, Pixmap pixmap2) {
                        return Math.max(pixmap.getWidth(), pixmap.getHeight()) - Math.max(pixmap2.getWidth(), pixmap2.getHeight());
                    }
                };
            }
            aVar.sort(this.comparator);
        }
    }

    public interface PackStrategy {
        Page pack(PixmapPacker pixmapPacker, String str, p pVar);

        void sort(a<Pixmap> aVar);
    }

    public static class Page {
        boolean dirty;
        Pixmap image;
        Texture texture;
        a0<String, PixmapPackerRectangle> rects = new a0<>();
        final a<String> addedRects = new a<>();

        public Page(PixmapPacker pixmapPacker) {
            Pixmap pixmap = new Pixmap(pixmapPacker.pageWidth, pixmapPacker.pageHeight, pixmapPacker.pageFormat);
            this.image = pixmap;
            pixmap.setBlending(Pixmap.Blending.None);
            this.image.setColor(pixmapPacker.getTransparentColor());
            this.image.fill();
        }

        public Pixmap getPixmap() {
            return this.image;
        }

        public a0<String, PixmapPackerRectangle> getRects() {
            return this.rects;
        }

        public Texture getTexture() {
            return this.texture;
        }

        public boolean updateTexture(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z2) {
            Texture texture = this.texture;
            if (texture == null) {
                Pixmap pixmap = this.image;
                Texture texture2 = new Texture(new PixmapTextureData(pixmap, pixmap.getFormat(), z2, false, true)) { // from class: com.badlogic.gdx.graphics.g2d.PixmapPacker.Page.1
                    @Override // com.badlogic.gdx.graphics.Texture, com.badlogic.gdx.graphics.GLTexture, com.badlogic.gdx.utils.i
                    public void dispose() {
                        super.dispose();
                        Page.this.image.dispose();
                    }
                };
                this.texture = texture2;
                texture2.setFilter(textureFilter, textureFilter2);
            } else {
                if (!this.dirty) {
                    return false;
                }
                texture.load(texture.getTextureData());
            }
            this.dirty = false;
            return true;
        }
    }

    public static class SkylineStrategy implements PackStrategy {
        Comparator<Pixmap> comparator;

        static class SkylinePage extends Page {
            a<Row> rows;

            static class Row {
                int height;

                /* JADX INFO: renamed from: x, reason: collision with root package name */
                int f1690x;

                /* JADX INFO: renamed from: y, reason: collision with root package name */
                int f1691y;

                Row() {
                }
            }

            public SkylinePage(PixmapPacker pixmapPacker) {
                super(pixmapPacker);
                this.rows = new a<>();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x008d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x009b A[SYNTHETIC] */
        @Override // com.badlogic.gdx.graphics.g2d.PixmapPacker.PackStrategy
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Page pack(PixmapPacker pixmapPacker, String str, p pVar) {
            int i2;
            int i3 = pixmapPacker.padding;
            int i4 = i3 * 2;
            int i5 = pixmapPacker.pageWidth - i4;
            int i6 = pixmapPacker.pageHeight - i4;
            int i7 = ((int) pVar.width) + i3;
            int i8 = ((int) pVar.height) + i3;
            int i9 = pixmapPacker.pages.f1750b;
            for (int i10 = 0; i10 < i9; i10++) {
                SkylinePage skylinePage = (SkylinePage) pixmapPacker.pages.get(i10);
                int i11 = skylinePage.rows.f1750b - 1;
                SkylinePage.Row row = null;
                for (int i12 = 0; i12 < i11; i12++) {
                    SkylinePage.Row row2 = skylinePage.rows.get(i12);
                    if (row2.f1690x + i7 < i5 && row2.f1691y + i8 < i6 && i8 <= (i2 = row2.height) && (row == null || i2 < row.height)) {
                        row = row2;
                    }
                }
                if (row == null) {
                    SkylinePage.Row rowK = skylinePage.rows.k();
                    int i13 = rowK.f1691y;
                    if (i13 + i8 < i6) {
                        if (rowK.f1690x + i7 < i5) {
                            rowK.height = Math.max(rowK.height, i8);
                            row = rowK;
                        } else if (i13 + rowK.height + i8 < i6) {
                            row = new SkylinePage.Row();
                            row.f1691y = rowK.f1691y + rowK.height;
                            row.height = i8;
                            skylinePage.rows.a(row);
                        }
                        if (row == null) {
                            int i14 = row.f1690x;
                            pVar.f89x = i14;
                            pVar.f90y = row.f1691y;
                            row.f1690x = i14 + i7;
                            return skylinePage;
                        }
                    } else {
                        continue;
                    }
                } else if (row == null) {
                }
            }
            SkylinePage skylinePage2 = new SkylinePage(pixmapPacker);
            pixmapPacker.pages.a(skylinePage2);
            SkylinePage.Row row3 = new SkylinePage.Row();
            row3.f1690x = i7 + i3;
            row3.f1691y = i3;
            row3.height = i8;
            skylinePage2.rows.a(row3);
            float f2 = i3;
            pVar.f89x = f2;
            pVar.f90y = f2;
            return skylinePage2;
        }

        @Override // com.badlogic.gdx.graphics.g2d.PixmapPacker.PackStrategy
        public void sort(a<Pixmap> aVar) {
            if (this.comparator == null) {
                this.comparator = new Comparator<Pixmap>() { // from class: com.badlogic.gdx.graphics.g2d.PixmapPacker.SkylineStrategy.1
                    @Override // java.util.Comparator
                    public int compare(Pixmap pixmap, Pixmap pixmap2) {
                        return pixmap.getHeight() - pixmap2.getHeight();
                    }
                };
            }
            aVar.sort(this.comparator);
        }
    }

    public PixmapPacker(int i2, int i3, Pixmap.Format format, int i4, boolean z2) {
        this(i2, i3, format, i4, z2, false, false, new GuillotineStrategy());
    }

    private int[] getPads(Pixmap pixmap, int[] iArr) {
        int width;
        int height = pixmap.getHeight() - 1;
        int width2 = pixmap.getWidth() - 1;
        int splitPoint = getSplitPoint(pixmap, 1, height, true, true);
        int splitPoint2 = getSplitPoint(pixmap, width2, 1, true, false);
        int splitPoint3 = splitPoint != 0 ? getSplitPoint(pixmap, splitPoint + 1, height, false, true) : 0;
        int splitPoint4 = splitPoint2 != 0 ? getSplitPoint(pixmap, width2, splitPoint2 + 1, false, false) : 0;
        getSplitPoint(pixmap, splitPoint3 + 1, height, true, true);
        getSplitPoint(pixmap, width2, splitPoint4 + 1, true, false);
        if (splitPoint == 0 && splitPoint3 == 0 && splitPoint2 == 0 && splitPoint4 == 0) {
            return null;
        }
        int height2 = -1;
        if (splitPoint == 0 && splitPoint3 == 0) {
            width = -1;
            splitPoint = -1;
        } else if (splitPoint > 0) {
            splitPoint--;
            width = (pixmap.getWidth() - 2) - (splitPoint3 - 1);
        } else {
            width = pixmap.getWidth() - 2;
        }
        if (splitPoint2 == 0 && splitPoint4 == 0) {
            splitPoint2 = -1;
        } else if (splitPoint2 > 0) {
            splitPoint2--;
            height2 = (pixmap.getHeight() - 2) - (splitPoint4 - 1);
        } else {
            height2 = pixmap.getHeight() - 2;
        }
        int[] iArr2 = {splitPoint, width, splitPoint2, height2};
        if (iArr == null || !Arrays.equals(iArr2, iArr)) {
            return iArr2;
        }
        return null;
    }

    private int getSplitPoint(Pixmap pixmap, int i2, int i3, boolean z2, boolean z3) {
        int width = z3 ? pixmap.getWidth() : pixmap.getHeight();
        int i4 = z2 ? 255 : 0;
        for (int i5 = z3 ? i2 : i3; i5 != width; i5++) {
            if (z3) {
                i2 = i5;
            } else {
                i3 = i5;
            }
            this.f1689c.set(pixmap.getPixel(i2, i3));
            Color color = this.f1689c;
            int[] iArr = {(int) (color.f1680r * 255.0f), (int) (color.f1679g * 255.0f), (int) (color.f1678b * 255.0f), (int) (color.f1677a * 255.0f)};
            int i6 = iArr[3];
            if (i6 == i4) {
                return i5;
            }
            if (!z2 && (iArr[0] != 0 || iArr[1] != 0 || iArr[2] != 0 || i6 != 255)) {
                System.out.println(i2 + "  " + i3 + " " + iArr + " ");
            }
        }
        return 0;
    }

    private int[] getSplits(Pixmap pixmap) {
        int width;
        int height;
        int splitPoint = getSplitPoint(pixmap, 1, 0, true, true);
        int splitPoint2 = getSplitPoint(pixmap, splitPoint, 0, false, true);
        int splitPoint3 = getSplitPoint(pixmap, 0, 1, true, false);
        int splitPoint4 = getSplitPoint(pixmap, 0, splitPoint3, false, false);
        getSplitPoint(pixmap, splitPoint2 + 1, 0, true, true);
        getSplitPoint(pixmap, 0, splitPoint4 + 1, true, false);
        if (splitPoint == 0 && splitPoint2 == 0 && splitPoint3 == 0 && splitPoint4 == 0) {
            return null;
        }
        if (splitPoint != 0) {
            splitPoint--;
            width = (pixmap.getWidth() - 2) - (splitPoint2 - 1);
        } else {
            width = pixmap.getWidth() - 2;
        }
        if (splitPoint3 != 0) {
            splitPoint3--;
            height = (pixmap.getHeight() - 2) - (splitPoint4 - 1);
        } else {
            height = pixmap.getHeight() - 2;
        }
        return new int[]{splitPoint, width, splitPoint3, height};
    }

    @Override // com.badlogic.gdx.utils.i
    public synchronized void dispose() {
        try {
            a.b<Page> it = this.pages.iterator();
            while (it.hasNext()) {
                Page next = it.next();
                if (next.texture == null) {
                    next.image.dispose();
                }
            }
            this.disposed = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized TextureAtlas generateTextureAtlas(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z2) {
        TextureAtlas textureAtlas;
        textureAtlas = new TextureAtlas();
        updateTextureAtlas(textureAtlas, textureFilter, textureFilter2, z2);
        return textureAtlas;
    }

    public boolean getDuplicateBorder() {
        return this.duplicateBorder;
    }

    public boolean getPackToTexture() {
        return this.packToTexture;
    }

    public int getPadding() {
        return this.padding;
    }

    public synchronized Page getPage(String str) {
        a.b<Page> it = this.pages.iterator();
        while (it.hasNext()) {
            Page next = it.next();
            if (next.rects.d(str) != null) {
                return next;
            }
        }
        return null;
    }

    public Pixmap.Format getPageFormat() {
        return this.pageFormat;
    }

    public int getPageHeight() {
        return this.pageHeight;
    }

    public synchronized int getPageIndex(String str) {
        int i2 = 0;
        while (true) {
            a<Page> aVar = this.pages;
            if (i2 >= aVar.f1750b) {
                return -1;
            }
            if (aVar.get(i2).rects.d(str) != null) {
                return i2;
            }
            i2++;
        }
    }

    public int getPageWidth() {
        return this.pageWidth;
    }

    public a<Page> getPages() {
        return this.pages;
    }

    public synchronized p getRect(String str) {
        a.b<Page> it = this.pages.iterator();
        while (it.hasNext()) {
            PixmapPackerRectangle pixmapPackerRectangleD = it.next().rects.d(str);
            if (pixmapPackerRectangleD != null) {
                return pixmapPackerRectangleD;
            }
        }
        return null;
    }

    public Color getTransparentColor() {
        return this.transparentColor;
    }

    public synchronized p pack(Pixmap pixmap) {
        return pack(null, pixmap);
    }

    public void setDuplicateBorder(boolean z2) {
        this.duplicateBorder = z2;
    }

    public void setPackToTexture(boolean z2) {
        this.packToTexture = z2;
    }

    public void setPadding(int i2) {
        this.padding = i2;
    }

    public void setPageFormat(Pixmap.Format format) {
        this.pageFormat = format;
    }

    public void setPageHeight(int i2) {
        this.pageHeight = i2;
    }

    public void setPageWidth(int i2) {
        this.pageWidth = i2;
    }

    public void setTransparentColor(Color color) {
        this.transparentColor.set(color);
    }

    public void sort(a<Pixmap> aVar) {
        this.packStrategy.sort(aVar);
    }

    public synchronized void updatePageTextures(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z2) {
        a.b<Page> it = this.pages.iterator();
        while (it.hasNext()) {
            it.next().updateTexture(textureFilter, textureFilter2, z2);
        }
    }

    public synchronized void updateTextureAtlas(TextureAtlas textureAtlas, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z2) {
        updateTextureAtlas(textureAtlas, textureFilter, textureFilter2, z2, true);
    }

    public synchronized void updateTextureRegions(a<TextureRegion> aVar, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z2) {
        updatePageTextures(textureFilter, textureFilter2, z2);
        while (true) {
            int i2 = aVar.f1750b;
            a<Page> aVar2 = this.pages;
            if (i2 < aVar2.f1750b) {
                aVar.a(new TextureRegion(aVar2.get(i2).texture));
            }
        }
    }

    public PixmapPacker(int i2, int i3, Pixmap.Format format, int i4, boolean z2, PackStrategy packStrategy) {
        this(i2, i3, format, i4, z2, false, false, packStrategy);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01e7 A[Catch: all -> 0x0023, TryCatch #0 {all -> 0x0023, blocks: (B:4:0x0009, B:10:0x0012, B:13:0x0019, B:14:0x0022, B:19:0x002a, B:21:0x0032, B:80:0x0175, B:82:0x0180, B:85:0x018d, B:87:0x0195, B:88:0x019f, B:90:0x01af, B:92:0x01b3, B:94:0x01b7, B:96:0x01bb, B:98:0x01de, B:100:0x01e7, B:102:0x02a3, B:97:0x01dc, B:106:0x02aa, B:107:0x02b1, B:108:0x02b2, B:109:0x02c8, B:23:0x008c, B:25:0x0090, B:28:0x0095, B:29:0x00a5, B:32:0x00b7, B:35:0x00be, B:37:0x00c4, B:40:0x00cf, B:41:0x00d2, B:42:0x00d7, B:43:0x00db, B:46:0x00e0, B:48:0x00e6, B:51:0x00f1, B:52:0x00f4, B:55:0x00fc, B:58:0x0105, B:62:0x010f, B:65:0x011a, B:66:0x011d, B:67:0x0122, B:68:0x0126, B:72:0x012e, B:75:0x0139, B:76:0x013c, B:79:0x0144), top: B:112:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02a3 A[Catch: all -> 0x0023, TRY_LEAVE, TryCatch #0 {all -> 0x0023, blocks: (B:4:0x0009, B:10:0x0012, B:13:0x0019, B:14:0x0022, B:19:0x002a, B:21:0x0032, B:80:0x0175, B:82:0x0180, B:85:0x018d, B:87:0x0195, B:88:0x019f, B:90:0x01af, B:92:0x01b3, B:94:0x01b7, B:96:0x01bb, B:98:0x01de, B:100:0x01e7, B:102:0x02a3, B:97:0x01dc, B:106:0x02aa, B:107:0x02b1, B:108:0x02b2, B:109:0x02c8, B:23:0x008c, B:25:0x0090, B:28:0x0095, B:29:0x00a5, B:32:0x00b7, B:35:0x00be, B:37:0x00c4, B:40:0x00cf, B:41:0x00d2, B:42:0x00d7, B:43:0x00db, B:46:0x00e0, B:48:0x00e6, B:51:0x00f1, B:52:0x00f4, B:55:0x00fc, B:58:0x0105, B:62:0x010f, B:65:0x011a, B:66:0x011d, B:67:0x0122, B:68:0x0126, B:72:0x012e, B:75:0x0139, B:76:0x013c, B:79:0x0144), top: B:112:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02aa A[Catch: all -> 0x0023, TRY_ENTER, TryCatch #0 {all -> 0x0023, blocks: (B:4:0x0009, B:10:0x0012, B:13:0x0019, B:14:0x0022, B:19:0x002a, B:21:0x0032, B:80:0x0175, B:82:0x0180, B:85:0x018d, B:87:0x0195, B:88:0x019f, B:90:0x01af, B:92:0x01b3, B:94:0x01b7, B:96:0x01bb, B:98:0x01de, B:100:0x01e7, B:102:0x02a3, B:97:0x01dc, B:106:0x02aa, B:107:0x02b1, B:108:0x02b2, B:109:0x02c8, B:23:0x008c, B:25:0x0090, B:28:0x0095, B:29:0x00a5, B:32:0x00b7, B:35:0x00be, B:37:0x00c4, B:40:0x00cf, B:41:0x00d2, B:42:0x00d7, B:43:0x00db, B:46:0x00e0, B:48:0x00e6, B:51:0x00f1, B:52:0x00f4, B:55:0x00fc, B:58:0x0105, B:62:0x010f, B:65:0x011a, B:66:0x011d, B:67:0x0122, B:68:0x0126, B:72:0x012e, B:75:0x0139, B:76:0x013c, B:79:0x0144), top: B:112:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02b2 A[Catch: all -> 0x0023, TryCatch #0 {all -> 0x0023, blocks: (B:4:0x0009, B:10:0x0012, B:13:0x0019, B:14:0x0022, B:19:0x002a, B:21:0x0032, B:80:0x0175, B:82:0x0180, B:85:0x018d, B:87:0x0195, B:88:0x019f, B:90:0x01af, B:92:0x01b3, B:94:0x01b7, B:96:0x01bb, B:98:0x01de, B:100:0x01e7, B:102:0x02a3, B:97:0x01dc, B:106:0x02aa, B:107:0x02b1, B:108:0x02b2, B:109:0x02c8, B:23:0x008c, B:25:0x0090, B:28:0x0095, B:29:0x00a5, B:32:0x00b7, B:35:0x00be, B:37:0x00c4, B:40:0x00cf, B:41:0x00d2, B:42:0x00d7, B:43:0x00db, B:46:0x00e0, B:48:0x00e6, B:51:0x00f1, B:52:0x00f4, B:55:0x00fc, B:58:0x0105, B:62:0x010f, B:65:0x011a, B:66:0x011d, B:67:0x0122, B:68:0x0126, B:72:0x012e, B:75:0x0139, B:76:0x013c, B:79:0x0144), top: B:112:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0195 A[Catch: all -> 0x0023, TryCatch #0 {all -> 0x0023, blocks: (B:4:0x0009, B:10:0x0012, B:13:0x0019, B:14:0x0022, B:19:0x002a, B:21:0x0032, B:80:0x0175, B:82:0x0180, B:85:0x018d, B:87:0x0195, B:88:0x019f, B:90:0x01af, B:92:0x01b3, B:94:0x01b7, B:96:0x01bb, B:98:0x01de, B:100:0x01e7, B:102:0x02a3, B:97:0x01dc, B:106:0x02aa, B:107:0x02b1, B:108:0x02b2, B:109:0x02c8, B:23:0x008c, B:25:0x0090, B:28:0x0095, B:29:0x00a5, B:32:0x00b7, B:35:0x00be, B:37:0x00c4, B:40:0x00cf, B:41:0x00d2, B:42:0x00d7, B:43:0x00db, B:46:0x00e0, B:48:0x00e6, B:51:0x00f1, B:52:0x00f4, B:55:0x00fc, B:58:0x0105, B:62:0x010f, B:65:0x011a, B:66:0x011d, B:67:0x0122, B:68:0x0126, B:72:0x012e, B:75:0x0139, B:76:0x013c, B:79:0x0144), top: B:112:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01dc A[Catch: all -> 0x0023, TryCatch #0 {all -> 0x0023, blocks: (B:4:0x0009, B:10:0x0012, B:13:0x0019, B:14:0x0022, B:19:0x002a, B:21:0x0032, B:80:0x0175, B:82:0x0180, B:85:0x018d, B:87:0x0195, B:88:0x019f, B:90:0x01af, B:92:0x01b3, B:94:0x01b7, B:96:0x01bb, B:98:0x01de, B:100:0x01e7, B:102:0x02a3, B:97:0x01dc, B:106:0x02aa, B:107:0x02b1, B:108:0x02b2, B:109:0x02c8, B:23:0x008c, B:25:0x0090, B:28:0x0095, B:29:0x00a5, B:32:0x00b7, B:35:0x00be, B:37:0x00c4, B:40:0x00cf, B:41:0x00d2, B:42:0x00d7, B:43:0x00db, B:46:0x00e0, B:48:0x00e6, B:51:0x00f1, B:52:0x00f4, B:55:0x00fc, B:58:0x0105, B:62:0x010f, B:65:0x011a, B:66:0x011d, B:67:0x0122, B:68:0x0126, B:72:0x012e, B:75:0x0139, B:76:0x013c, B:79:0x0144), top: B:112:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized p pack(String str, Pixmap pixmap) {
        int i2;
        int i3;
        PixmapPackerRectangle pixmapPackerRectangle;
        Page pagePack;
        Texture texture;
        String str2 = str;
        Pixmap pixmap2 = pixmap;
        synchronized (this) {
            try {
                Pixmap pixmap3 = null;
                if (this.disposed) {
                    return null;
                }
                if (str2 != null && getRect(str) != null) {
                    throw new m("Pixmap has already been packed with name: ".concat(str2));
                }
                if (str2 == null || !str2.endsWith(".9")) {
                    if (!this.stripWhitespaceX && !this.stripWhitespaceY) {
                        pixmapPackerRectangle = new PixmapPackerRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
                        if (pixmapPackerRectangle.getWidth() <= this.pageWidth && pixmapPackerRectangle.getHeight() <= this.pageHeight) {
                            pagePack = this.packStrategy.pack(this, str2, pixmapPackerRectangle);
                            if (str2 != null) {
                                pagePack.rects.j(str2, pixmapPackerRectangle);
                                pagePack.addedRects.a(str2);
                            }
                            int i4 = (int) pixmapPackerRectangle.f89x;
                            int i5 = (int) pixmapPackerRectangle.f90y;
                            int i6 = (int) pixmapPackerRectangle.width;
                            int i7 = (int) pixmapPackerRectangle.height;
                            if (this.packToTexture || this.duplicateBorder || (texture = pagePack.texture) == null || pagePack.dirty) {
                                pagePack.dirty = true;
                            } else {
                                texture.bind();
                                Gdx.gl.glTexSubImage2D(pagePack.texture.glTarget, 0, i4, i5, i6, i7, pixmap2.getGLFormat(), pixmap2.getGLType(), pixmap2.getPixels());
                            }
                            pagePack.image.drawPixmap(pixmap2, i4, i5);
                            if (this.duplicateBorder) {
                                int width = pixmap2.getWidth();
                                int height = pixmap2.getHeight();
                                int i8 = i4 - 1;
                                int i9 = i5 - 1;
                                pagePack.image.drawPixmap(pixmap2, 0, 0, 1, 1, i8, i9, 1, 1);
                                int i10 = width - 1;
                                int i11 = i4 + i6;
                                pagePack.image.drawPixmap(pixmap2, i10, 0, 1, 1, i11, i9, 1, 1);
                                int i12 = height - 1;
                                int i13 = i5 + i7;
                                pagePack.image.drawPixmap(pixmap2, 0, i12, 1, 1, i8, i13, 1, 1);
                                pagePack.image.drawPixmap(pixmap2, i10, i12, 1, 1, i11, i13, 1, 1);
                                pagePack.image.drawPixmap(pixmap2, 0, 0, width, 1, i4, i9, i6, 1);
                                pagePack.image.drawPixmap(pixmap2, 0, i12, width, 1, i4, i13, i6, 1);
                                pagePack.image.drawPixmap(pixmap2, 0, 0, 1, height, i8, i5, 1, i7);
                                pagePack.image.drawPixmap(pixmap2, i10, 0, 1, height, i11, i5, 1, i7);
                            }
                            if (pixmap3 != null) {
                                pixmap3.dispose();
                            }
                            return pixmapPackerRectangle;
                        }
                        if (str2 != null) {
                            throw new m("Page size too small for pixmap.");
                        }
                        throw new m("Page size too small for pixmap: " + str2);
                    }
                    int width2 = pixmap.getWidth();
                    int height2 = pixmap.getHeight();
                    int height3 = pixmap.getHeight();
                    if (this.stripWhitespaceY) {
                        int i14 = 0;
                        loop0: for (int i15 = 0; i15 < pixmap.getHeight(); i15++) {
                            for (int i16 = 0; i16 < pixmap.getWidth(); i16++) {
                                if ((pixmap2.getPixel(i16, i15) & 255) > this.alphaThreshold) {
                                    break loop0;
                                }
                            }
                            i14++;
                        }
                        int height4 = pixmap.getHeight();
                        loop2: while (true) {
                            height4--;
                            if (height4 < i14) {
                                break;
                            }
                            for (int i17 = 0; i17 < pixmap.getWidth(); i17++) {
                                if ((pixmap2.getPixel(i17, height4) & 255) > this.alphaThreshold) {
                                    break loop2;
                                }
                            }
                            height3--;
                        }
                        i2 = i14;
                    } else {
                        i2 = 0;
                    }
                    int width3 = pixmap.getWidth();
                    if (this.stripWhitespaceX) {
                        int i18 = 0;
                        loop4: for (int i19 = 0; i19 < pixmap.getWidth(); i19++) {
                            for (int i20 = i2; i20 < height3; i20++) {
                                if ((pixmap2.getPixel(i19, i20) & 255) > this.alphaThreshold) {
                                    break loop4;
                                }
                            }
                            i18++;
                        }
                        int width4 = pixmap.getWidth();
                        loop6: while (true) {
                            width4--;
                            if (width4 < i18) {
                                break;
                            }
                            for (int i21 = i2; i21 < height3; i21++) {
                                if ((pixmap2.getPixel(width4, i21) & 255) > this.alphaThreshold) {
                                    break loop6;
                                }
                            }
                            width3--;
                        }
                        i3 = i18;
                    } else {
                        i3 = 0;
                    }
                    int i22 = width3 - i3;
                    int i23 = height3 - i2;
                    Pixmap pixmap4 = new Pixmap(i22, i23, pixmap.getFormat());
                    pixmap4.setBlending(Pixmap.Blending.None);
                    pixmap4.drawPixmap(pixmap, 0, 0, i3, i2, i22, i23);
                    pixmapPackerRectangle = new PixmapPackerRectangle(0, 0, i22, i23, i3, i2, width2, height2);
                    pixmap2 = pixmap4;
                } else {
                    PixmapPackerRectangle pixmapPackerRectangle2 = new PixmapPackerRectangle(0, 0, pixmap.getWidth() - 2, pixmap.getHeight() - 2);
                    Pixmap pixmap5 = new Pixmap(pixmap.getWidth() - 2, pixmap.getHeight() - 2, pixmap.getFormat());
                    pixmap5.setBlending(Pixmap.Blending.None);
                    int[] splits = getSplits(pixmap2);
                    pixmapPackerRectangle2.splits = splits;
                    pixmapPackerRectangle2.pads = getPads(pixmap2, splits);
                    pixmap5.drawPixmap(pixmap, 0, 0, 1, 1, pixmap.getWidth() - 1, pixmap.getHeight() - 1);
                    str2 = str2.split("\\.")[0];
                    pixmapPackerRectangle = pixmapPackerRectangle2;
                    pixmap2 = pixmap5;
                }
                pixmap3 = pixmap2;
                if (pixmapPackerRectangle.getWidth() <= this.pageWidth) {
                    pagePack = this.packStrategy.pack(this, str2, pixmapPackerRectangle);
                    if (str2 != null) {
                    }
                    int i42 = (int) pixmapPackerRectangle.f89x;
                    int i52 = (int) pixmapPackerRectangle.f90y;
                    int i62 = (int) pixmapPackerRectangle.width;
                    int i72 = (int) pixmapPackerRectangle.height;
                    if (this.packToTexture) {
                        pagePack.dirty = true;
                    }
                    pagePack.image.drawPixmap(pixmap2, i42, i52);
                    if (this.duplicateBorder) {
                    }
                    if (pixmap3 != null) {
                    }
                    return pixmapPackerRectangle;
                }
                if (str2 != null) {
                }
            } finally {
            }
        }
    }

    public PixmapPacker(int i2, int i3, Pixmap.Format format, int i4, boolean z2, boolean z3, boolean z4, PackStrategy packStrategy) {
        this.transparentColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        this.pages = new a<>();
        this.f1689c = new Color();
        this.pageWidth = i2;
        this.pageHeight = i3;
        this.pageFormat = format;
        this.padding = i4;
        this.duplicateBorder = z2;
        this.stripWhitespaceX = z3;
        this.stripWhitespaceY = z4;
        this.packStrategy = packStrategy;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void updateTextureAtlas(TextureAtlas textureAtlas, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z2, boolean z3) {
        int i2;
        try {
            updatePageTextures(textureFilter, textureFilter2, z2);
            a.b<Page> it = this.pages.iterator();
            while (it.hasNext()) {
                Page next = it.next();
                a<String> aVar = next.addedRects;
                if (aVar.f1750b > 0) {
                    a.b<String> it2 = aVar.iterator();
                    while (it2.hasNext()) {
                        String next2 = it2.next();
                        PixmapPackerRectangle pixmapPackerRectangleD = next.rects.d(next2);
                        TextureAtlas.AtlasRegion atlasRegion = new TextureAtlas.AtlasRegion(next.texture, (int) pixmapPackerRectangleD.f89x, (int) pixmapPackerRectangleD.f90y, (int) pixmapPackerRectangleD.width, (int) pixmapPackerRectangleD.height);
                        int[] iArr = pixmapPackerRectangleD.splits;
                        if (iArr != null) {
                            atlasRegion.splits = iArr;
                            atlasRegion.pads = pixmapPackerRectangleD.pads;
                        }
                        if (z3) {
                            Matcher matcher = indexPattern.matcher(next2);
                            if (matcher.matches()) {
                                next2 = matcher.group(1);
                                i2 = Integer.parseInt(matcher.group(2));
                            } else {
                                i2 = -1;
                            }
                        }
                        atlasRegion.name = next2;
                        atlasRegion.index = i2;
                        atlasRegion.offsetX = pixmapPackerRectangleD.offsetX;
                        int i3 = pixmapPackerRectangleD.originalHeight;
                        atlasRegion.offsetY = (int) ((i3 - pixmapPackerRectangleD.height) - pixmapPackerRectangleD.offsetY);
                        atlasRegion.originalWidth = pixmapPackerRectangleD.originalWidth;
                        atlasRegion.originalHeight = i3;
                        textureAtlas.getRegions().a(atlasRegion);
                    }
                    next.addedRects.clear();
                    textureAtlas.getTextures().add(next.texture);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static class PixmapPackerRectangle extends p {
        int offsetX;
        int offsetY;
        int originalHeight;
        int originalWidth;
        int[] pads;
        int[] splits;

        PixmapPackerRectangle(int i2, int i3, int i4, int i5) {
            super(i2, i3, i4, i5);
            this.offsetX = 0;
            this.offsetY = 0;
            this.originalWidth = i4;
            this.originalHeight = i5;
        }

        PixmapPackerRectangle(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            super(i2, i3, i4, i5);
            this.offsetX = i6;
            this.offsetY = i7;
            this.originalWidth = i8;
            this.originalHeight = i9;
        }
    }
}
