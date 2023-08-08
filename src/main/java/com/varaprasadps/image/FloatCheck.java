package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class FloatCheck {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/test/design-floatcheck.bmp";
        String path = "z-data/test/design.bmp";
        BufferedImage image = ImageIO.read(new File(path));

        BufferedImage img = get(image, 6);
        saveBMP(img, String.format(out, image.getWidth(), image.getHeight()));
    }

    public static BufferedImage get(BufferedImage input, int floatCheck) {
        final BufferedImage res = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < input.getWidth(); x++) {
            int sameColorBox = 0;
            int prevRGB = 0;
            for (int y = 0; y < input.getHeight(); y++) {
                int rgb = input.getRGB(x, y);
                if (rgb != Color.WHITE.getRGB() && rgb == prevRGB) {
                    sameColorBox++;
                    if (sameColorBox >= floatCheck) {
                        res.setRGB(x, y, Color.RED.getRGB());
                    } else {
                        res.setRGB(x, y, rgb);
                    }
                } else {
                    prevRGB = rgb;
                    sameColorBox = 0;
                    res.setRGB(x, y, rgb);
                }
            }
        }
        return res;
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage, "bmp", new File(path));
    }
}
