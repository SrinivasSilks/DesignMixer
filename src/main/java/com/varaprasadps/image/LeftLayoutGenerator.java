package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class LeftLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/8/a2020/brocade2/left-jari.bmp";

        String path = "z-data/in/8/a2020/brocade2/jari.bmp";
        BufferedImage image = ImageIO.read(new File(path));
        BufferedImage test = PlainGenerator.get(2, 20);


        BufferedImage img = get(image);
        saveBMP(img, String.format(out, image.getWidth(), image.getHeight()));
    }

    public static BufferedImage get(BufferedImage input) {
        final BufferedImage res = new BufferedImage(input.getHeight(), input.getWidth(), BufferedImage.TYPE_BYTE_BINARY);
        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                int rgb = input.getRGB(x, y);
                int sizeX =  y;
                int sizeY = input.getWidth() - 1 - x;
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
