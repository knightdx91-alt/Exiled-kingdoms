package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.y;
import java.io.Writer;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PixmapPackerIO {

    /* JADX INFO: renamed from: com.badlogic.gdx.graphics.g2d.PixmapPackerIO$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$graphics$g2d$PixmapPackerIO$ImageFormat;

        static {
            int[] iArr = new int[ImageFormat.values().length];
            $SwitchMap$com$badlogic$gdx$graphics$g2d$PixmapPackerIO$ImageFormat = iArr;
            try {
                iArr[ImageFormat.CIM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$PixmapPackerIO$ImageFormat[ImageFormat.PNG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum ImageFormat {
        CIM(".cim"),
        PNG(".png");

        private final String extension;

        ImageFormat(String str) {
            this.extension = str;
        }

        public String getExtension() {
            return this.extension;
        }
    }

    public static class SaveParameters {
        public ImageFormat format = ImageFormat.PNG;
        public Texture.TextureFilter magFilter;
        public Texture.TextureFilter minFilter;
        public boolean useIndexes;

        public SaveParameters() {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
            this.minFilter = textureFilter;
            this.magFilter = textureFilter;
        }
    }

    public void save(a aVar, PixmapPacker pixmapPacker) throws Throwable {
        save(aVar, pixmapPacker, new SaveParameters());
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x011e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void save(a aVar, PixmapPacker pixmapPacker, SaveParameters saveParameters) throws Throwable {
        int i2;
        String strGroup;
        int i3;
        int i4;
        Writer writer = aVar.writer(false);
        a.b<PixmapPacker.Page> it = pixmapPacker.pages.iterator();
        int i5 = 0;
        while (it.hasNext()) {
            PixmapPacker.Page next = it.next();
            if (next.rects.f2043a > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(aVar.nameWithoutExtension());
                sb.append("_");
                i5++;
                sb.append(i5);
                sb.append(saveParameters.format.getExtension());
                com.badlogic.gdx.files.a aVarSibling = aVar.sibling(sb.toString());
                int i6 = AnonymousClass1.$SwitchMap$com$badlogic$gdx$graphics$g2d$PixmapPackerIO$ImageFormat[saveParameters.format.ordinal()];
                int i7 = 1;
                int i8 = 2;
                if (i6 == 1) {
                    PixmapIO.writeCIM(aVarSibling, next.image);
                } else if (i6 == 2) {
                    PixmapIO.writePNG(aVarSibling, next.image);
                }
                writer.write("\n");
                writer.write(aVarSibling.name() + "\n");
                writer.write("size: " + next.image.getWidth() + "," + next.image.getHeight() + "\n");
                StringBuilder sb2 = new StringBuilder("format: ");
                sb2.append(pixmapPacker.pageFormat.name());
                sb2.append("\n");
                writer.write(sb2.toString());
                writer.write("filter: " + saveParameters.minFilter.name() + "," + saveParameters.magFilter.name() + "\n");
                writer.write("repeat: none\n");
                y.c<String> cVarG = next.rects.g();
                while (cVarG.hasNext()) {
                    String next2 = cVarG.next();
                    if (saveParameters.useIndexes) {
                        Matcher matcher = PixmapPacker.indexPattern.matcher(next2);
                        if (matcher.matches()) {
                            strGroup = matcher.group(i7);
                            i2 = Integer.parseInt(matcher.group(i8));
                        } else {
                            i2 = -1;
                            strGroup = next2;
                        }
                    }
                    writer.write(strGroup + "\n");
                    PixmapPacker.PixmapPackerRectangle pixmapPackerRectangleD = next.rects.d(next2);
                    writer.write("  rotate: false\n");
                    writer.write("  xy: " + ((int) pixmapPackerRectangleD.f89x) + "," + ((int) pixmapPackerRectangleD.f90y) + "\n");
                    writer.write("  size: " + ((int) pixmapPackerRectangleD.width) + "," + ((int) pixmapPackerRectangleD.height) + "\n");
                    if (pixmapPackerRectangleD.splits != null) {
                        writer.write("  split: " + pixmapPackerRectangleD.splits[0] + ", " + pixmapPackerRectangleD.splits[1] + ", " + pixmapPackerRectangleD.splits[2] + ", " + pixmapPackerRectangleD.splits[3] + "\n");
                        if (pixmapPackerRectangleD.pads != null) {
                            StringBuilder sb3 = new StringBuilder("  pad: ");
                            sb3.append(pixmapPackerRectangleD.pads[0]);
                            sb3.append(", ");
                            i3 = 1;
                            sb3.append(pixmapPackerRectangleD.pads[1]);
                            sb3.append(", ");
                            i4 = 2;
                            sb3.append(pixmapPackerRectangleD.pads[2]);
                            sb3.append(", ");
                            sb3.append(pixmapPackerRectangleD.pads[3]);
                            sb3.append("\n");
                            writer.write(sb3.toString());
                        } else {
                            i4 = 2;
                            i3 = 1;
                        }
                    } else {
                        i3 = i7;
                        i4 = 2;
                    }
                    writer.write("  orig: " + pixmapPackerRectangleD.originalWidth + ", " + pixmapPackerRectangleD.originalHeight + "\n");
                    writer.write("  offset: " + pixmapPackerRectangleD.offsetX + ", " + ((int) ((((float) pixmapPackerRectangleD.originalHeight) - pixmapPackerRectangleD.height) - ((float) pixmapPackerRectangleD.offsetY))) + "\n");
                    StringBuilder sb4 = new StringBuilder("  index: ");
                    sb4.append(i2);
                    sb4.append("\n");
                    writer.write(sb4.toString());
                    i8 = i4;
                    i7 = i3;
                }
            }
        }
        writer.close();
    }
}
