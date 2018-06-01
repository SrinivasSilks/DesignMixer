package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class StepLayoutGenerator {


    public static void main(final String[] args) throws IOException {
        int x = 16;
        BufferedImage img = get(x,5);
        saveBMP(img, String.format("z-giri/out/step-%s-%s.bmp", x, 20));
    }

    public static BufferedImage get(int sizeX, int times) {
        List<BufferedImage> inputs = new LinkedList<>();
        for (int i = 0; i < times; i++) {
            inputs.add(get(sizeX));
        }
        return AddLayoutGenerator.get(sizeX, 4 * times, inputs);
    }

    public static BufferedImage get(int sizeX) {
        final BufferedImage res = new BufferedImage(sizeX, 4, BufferedImage.TYPE_INT_RGB);
        int position = 3;

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < 4; y++) {
                if (y == position) {
                    res.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    res.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
            position--;
            if (position == -1) {
                position = 3;
            }
        }
        return res;
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage, "bmp", new File(path));
    }

}
