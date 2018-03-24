package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class BlackGenerator {

    public static void main(final String[] args) throws IOException {
        int x = 1824;
        int y = 16;
        BufferedImage img = get(x, y);
        saveBMP(img, String.format("z-data/out/empty-%s-%s.bmp", x, y));
    }

    public static BufferedImage get(int sizeX, int sizeY) {
        final BufferedImage res = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < sizeY; x++) {
            for (int y = 0; y < sizeX; y++) {
                res.setRGB(y, x, Color.BLACK.getRGB());
            }
        }
        return res;
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage, "bmp", new File(path));
    }
}
