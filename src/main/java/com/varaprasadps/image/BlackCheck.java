package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class BlackCheck {

    public static void main(final String[] args) throws IOException {
        boolean valid = valid(ImageIO.read(new File("d/3/out/2024/design1/test/gold-file-0.bmp")));
        boolean notValid = valid(ImageIO.read(new File("d/3/out/2024/design1/test/gold-file-1.bmp")));

    }

    public static boolean valid(BufferedImage input) {
        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                int rgb = input.getRGB(x, y);
                if (rgb != Color.WHITE.getRGB()) {
                    return true;
                }
            }
        }
        return false;
    }

}
