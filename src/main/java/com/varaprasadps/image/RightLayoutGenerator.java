package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class RightLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/8/a2020/brocade2/right-jari.bmp";

        String path = "z-data/in/8/a2020/brocade2/jari.bmp";
        BufferedImage image = ImageIO.read(new File(path));
        BufferedImage test = PlainGenerator.get(2, 20);


        BufferedImage img = get(image);
        saveBMP(img, String.format(out, image.getWidth(), image.getHeight()));
    }

    public static BufferedImage get(BufferedImage input) {
        final BufferedImage res = new BufferedImage(input.getHeight(), input.getWidth(), input.getType());
        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                int rgb = input.getRGB(x, y);
                int sizeX = input.getHeight() - 1 - y;
                int sizeY = x;
                res.setRGB(sizeX, sizeY, rgb);
            }
        }
        return res;
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage, "bmp", new File(path));
    }


}
