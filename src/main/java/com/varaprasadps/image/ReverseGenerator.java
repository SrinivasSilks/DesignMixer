package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class ReverseGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/12/a2022/design1/border/right.bmp";

        String path = "z-data/in/12/a2022/design1/border/right.bmp";
        BufferedImage image = ImageIO.read(new File(path));

        BufferedImage img = get(image);
        saveBMP(img, String.format(out,image.getWidth(),image.getHeight()));
    }

    public static BufferedImage get(BufferedImage input) {
        Set<Integer> output = new TreeSet<>();
        final BufferedImage res = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                int rgb = input.getRGB(x, y);
                output.add(rgb);
                res.setRGB(x, y, opp(rgb));
            }
        }
        return res;
    }

    private static int opp(int rgb) {
        int black = Color.BLACK.getRGB();
        int white = Color.WHITE.getRGB();
        return rgb == black ? white : black;
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage, "bmp", new File(path));
    }

}
