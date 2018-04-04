package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AchuLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        int x = 50;
        int y = 8;
        BufferedImage img = get(x, y);
        saveBMP(img, String.format("z-data/out/achu-%s-%s.bmp", x, y));
    }

    public static BufferedImage get(int sizeX, int sizeY) {

        int repeat = sizeY / 2;
        List<BufferedImage> images = new LinkedList<>();
        BufferedImage first = PlainGenerator.get(sizeX, 1);
        BufferedImage second = ReverseGenerator.get(first);

        for (int i = 0; i < repeat; i++) {
            images.add(first);
        }

        for (int i = 0; i < repeat; i++) {
            images.add(second);
        }

        return AddLayoutGenerator.get(sizeX, sizeY, images);
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage, "bmp", new File(path));
    }

}
