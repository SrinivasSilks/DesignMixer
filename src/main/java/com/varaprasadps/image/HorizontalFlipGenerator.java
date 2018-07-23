package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class HorizontalFlipGenerator {
    public static void main(final String[] args) throws IOException {
        String input = "z-giri/out/PALLU_B_DOWN_JARI-120-1700.bmp";
        BufferedImage inputBI = ImageIO.read(new File(input));
        BufferedImage img = get(inputBI);
        saveBMP(img, String.format("z-giri/out/PALLU_B_UP_JARI-%s-%s.bmp", img.getWidth(), img.getHeight()));
    }

    public static BufferedImage get(BufferedImage input) {
        final BufferedImage res = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < input.getWidth(); row++) {
            int resultRow = input.getWidth() - 1 - row;
            for (int col = 0; col < input.getHeight(); col++) {
                res.setRGB(resultRow, col, input.getRGB(row, col));
            }
        }
        return res;
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage, "bmp", new File(path));
    }

}
