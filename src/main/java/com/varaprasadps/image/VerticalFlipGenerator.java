package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class VerticalFlipGenerator {
    public static void main(final String[] args) throws IOException {
        String input = "z-data/in/3/P_JARI_3.bmp";
        BufferedImage inputBI = ImageIO.read(new File(input));
        BufferedImage img = get(inputBI);
        saveBMP(img, String.format("z-data/in/3/P_JARI_3_%s_%s.bmp", img.getWidth(), img.getHeight()));
    }

    public static BufferedImage get(BufferedImage input) {
        final BufferedImage res = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        for (int row = 0; row < input.getWidth(); row++) {
            for (int col = 0; col < input.getHeight(); col++) {
                int resultCol = input.getHeight() - 1 - col;
                res.setRGB(row, resultCol, input.getRGB(row, col));
            }
        }
        return res;
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage, "bmp", new File(path));
    }

}
