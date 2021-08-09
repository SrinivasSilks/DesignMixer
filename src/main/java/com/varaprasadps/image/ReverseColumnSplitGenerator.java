package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReverseColumnSplitGenerator {

    public static void main(final String[] args) throws IOException {
        String outOne = "z-giri/in/4/out/pallu02a-1-%s-%s.bmp";
        String outTwo = "z-giri/in/4/out/pallu02a-2-%s-%s.bmp";

        String input = "z-giri/in/4/pallu02a.bmp";
        BufferedImage inputImage = ImageIO.read(new File(input));

        int repeatWidth = inputImage.getWidth();
        int repeatHeight = inputImage.getHeight() / 2;

        List<BufferedImage> bis = get(inputImage);

        displayPixels(bis.get(0));
        displayPixels(bis.get(1));

        saveBMP(bis.get(0), String.format(outOne, repeatWidth, repeatHeight));
        saveBMP(bis.get(1), String.format(outTwo, repeatWidth, repeatHeight));
    }

    private static List<BufferedImage> get(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();


        BufferedImage one = getOne(width, height, input);
        BufferedImage two = getTwo(width, height, input);
        return Arrays.asList(one, two);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

    private static BufferedImage getOne(int width, int height, BufferedImage input) {
        int oneHeight = height / 2;
        BufferedImage one = new BufferedImage(width, height / 2, BufferedImage.TYPE_BYTE_BINARY);

        int x = 0;
        int y = 0;
        for (int oneX = 0; oneX < width && x < width; oneX++) {
            y = 0;
            for (int oneY = 0; oneY < oneHeight; oneY++) {
                one.setRGB(oneX, oneY, input.getRGB(x, y));
                y = y + 2;
            }
            x++;
        }
        return one;
    }


    private static BufferedImage getTwo(int width, int height, BufferedImage input) {
        int oneHeight = height / 2;
        BufferedImage two = new BufferedImage(width, height / 2, BufferedImage.TYPE_BYTE_BINARY);

        int x = 0;
        int y = 1;
        for (int oneX = 0; oneX < width && x < width; oneX++) {
            y = 1;
            for (int oneY = 0; oneY < oneHeight; oneY++) {
                two.setRGB(oneX, oneY, input.getRGB(x, y));
                y = y + 2;
            }
            x++;
        }
        return two;
    }
}
