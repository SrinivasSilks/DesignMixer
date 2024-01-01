package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VerticalRepeatGenerator {


    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/BANARAS-%s-%s.bmp";
        int times = 33;

        String path = "z-data/in/1/BANARAS.bmp";
        BufferedImage image = ImageIO.read(new File(path));

        int width = image.getWidth() * times;
        int height = image.getHeight();

        BufferedImage bi = map(width, height, image);
        displayPixels(bi);
        saveBMP(bi, String.format(out, width, height));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    public static BufferedImage get(int times, BufferedImage input) {
        return LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(times, RightLayoutGenerator.get(input)));
    }

    private static BufferedImage map(int sizeX, int sizeY, BufferedImage input) {
        final BufferedImage result = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_BYTE_BINARY);
        int inputWidth = input.getWidth();

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                result.setRGB(x, y, input.getRGB(x % inputWidth, y));
            }
        }
        return result;
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
