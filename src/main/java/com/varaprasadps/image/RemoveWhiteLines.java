package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RemoveWhiteLines {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/test/test-file-final.bmp";
        String path = "z-data/test/test-file.bmp";
        BufferedImage image = ImageIO.read(new File(path));

        BufferedImage img = get(image);
        saveBMP(img, String.format(out, image.getWidth(), image.getHeight()));
    }

    public static BufferedImage get(BufferedImage input) throws IOException {
        int emptyLines = 0;
        for (int y = 0; y < input.getHeight(); y++) {
            boolean isEmptyLine = true;
            for (int x = 0; x < input.getWidth(); x++) {
                int rgb = input.getRGB(x, y);
                if (rgb != Color.WHITE.getRGB()) {
                    isEmptyLine = false;
                    break;
                }
            }
            if (isEmptyLine) {
                emptyLines++;
            }
        }

        int finalHeight = input.getHeight() - emptyLines;
        final BufferedImage res = new BufferedImage(input.getWidth(), finalHeight, input.getType());
        int resY = 0;
        for (int y = 0; y < input.getHeight(); y++) {
            boolean isEmptyLine = true;
            for (int x = 0; x < input.getWidth(); x++) {
                int rgb = input.getRGB(x, y);
                if (rgb != Color.WHITE.getRGB()) {
                    isEmptyLine = false;
                    break;
                }
            }
            if (!isEmptyLine) {
                for (int x = 0; x < input.getWidth(); x++) {
                    int rgb = input.getRGB(x, y);
                    res.setRGB(x, resY, rgb);
                }
                resY++;
            }
        }
        return res;
    }

    public static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
