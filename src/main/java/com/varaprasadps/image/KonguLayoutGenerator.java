package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        int times = 1760 / 4;
        BufferedImage img = get(times);
        saveBMP(img, String.format("z-data/out/kongu-%s-%s.bmp", 2, 4 * times));
    }

    public static BufferedImage get() {
        return get(1);
    }

    public static BufferedImage get(int times) {
        List<BufferedImage> inputBIs = new LinkedList<>();
        BufferedImage plain = PlainGenerator.get(2, 1);
        BufferedImage reverse = ReverseGenerator.get(plain);
        for (int i = 0; i < times; i++) {
            inputBIs.add(plain);
            inputBIs.add(plain);
            inputBIs.add(reverse);
            inputBIs.add(reverse);
        }
        return AddLayoutGenerator.get(2, times * 4, inputBIs);
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage, "bmp", new File(path));
    }

}
