package ekvr.desktop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/** Saves the current back buffer as a PNG (test screenshots). */
public class Shot {
    public static void save(String path) {
        int w = DeskBackend.width, h = DeskBackend.height;
        ByteBuffer buf = BufferUtils.createByteBuffer(w * h * 4);
        GL11.glReadPixels(0, 0, w, h, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                int i = ((h - 1 - y) * w + x) * 4;
                img.setRGB(x, y, ((buf.get(i) & 255) << 16) | ((buf.get(i + 1) & 255) << 8) | (buf.get(i + 2) & 255));
            }
        try {
            javax.imageio.ImageIO.write(img, "png", new File(path));
            System.out.println("[shot] " + path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
