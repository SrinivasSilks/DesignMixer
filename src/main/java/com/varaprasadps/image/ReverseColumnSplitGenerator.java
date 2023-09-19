package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReverseColumnSplitGenerator {

    public static final int FILES = 4;

    public static void main(final String[] args) throws IOException {
        String out = "z-data/test/2/";

        String input = "z-data/test/2.bmp";
        BufferedImage inputImage = ImageIO.read(new File(input));

        int repeatWidth = inputImage.getWidth();
        int repeatHeight = inputImage.getHeight() / FILES;

        List<BufferedImage> bis = get(inputImage);

        displayPixels(bis.get(0));
        displayPixels(bis.get(1));
        if (FILES == 3) {
            displayPixels(bis.get(2));
            saveBMP(bis.get(2), String.format(out + "file-%s-%s-%s.bmp", 3, repeatWidth, repeatHeight));
        }
        if (FILES == 4) {
            displayPixels(bis.get(2));
            displayPixels(bis.get(3));
            saveBMP(bis.get(2), String.format(out + "file-%s-%s-%s.bmp", 3, repeatWidth, repeatHeight));
            saveBMP(bis.get(3), String.format(out + "file-%s-%s-%s.bmp", 4, repeatWidth, repeatHeight));
        }

        saveBMP(bis.get(0), String.format(out + "file-%s-%s-%s.bmp", 1, repeatWidth, repeatHeight));
        saveBMP(bis.get(1), String.format(out + "file-%s-%s-%s.bmp", 2, repeatWidth, repeatHeight));
    }

    private static List<BufferedImage> get(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();


        BufferedImage one = getOne(width, height, input);
        BufferedImage two = getTwo(width, height, input);
        if (FILES == 3) {
            BufferedImage three = getThree(width, height, input);
            return Arrays.asList(one, two, three);
        }

        if (FILES == 4) {
            BufferedImage three = getThree(width, height, input);
            BufferedImage four = getFour(width, height, input);
            return Arrays.asList(one, two, three, four);
        }
        return Arrays.asList(one, two);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(left(bi), "bmp", new File(path));
    }

    private static BufferedImage getOne(int width, int height, BufferedImage input) {
        int oneHeight = height / FILES;
        BufferedImage one = new BufferedImage(width, oneHeight, BufferedImage.TYPE_INT_RGB);

        int x = 0;
        int y = 0;
        for (int oneX = 0; oneX < width && x < width; oneX++) {
            y = 0;
            for (int oneY = 0; oneY < oneHeight; oneY++) {
                one.setRGB(oneX, oneY, input.getRGB(x, y));
                y = y + FILES;
            }
            x++;
        }
        return one;
    }


    private static BufferedImage getTwo(int width, int height, BufferedImage input) {
        int oneHeight = height / FILES;
        BufferedImage two = new BufferedImage(width, oneHeight, BufferedImage.TYPE_INT_RGB);

        int x = 0;
        int y = 1;
        for (int oneX = 0; oneX < width && x < width; oneX++) {
            y = 1;
            for (int oneY = 0; oneY < oneHeight; oneY++) {
                two.setRGB(oneX, oneY, input.getRGB(x, y));
                y = y + FILES;
            }
            x++;
        }
        return two;
    }

    private static BufferedImage getThree(int width, int height, BufferedImage input) {
        int oneHeight = height / FILES;
        BufferedImage two = new BufferedImage(width, oneHeight,  BufferedImage.TYPE_INT_RGB);

        int x = 0;
        int y = 2;
        for (int oneX = 0; oneX < width && x < width; oneX++) {
            y = 2;
            for (int oneY = 0; oneY < oneHeight; oneY++) {
                two.setRGB(oneX, oneY, input.getRGB(x, y));
                y = y + FILES;
            }
            x++;
        }
        return two;
    }
    private static BufferedImage getFour(int width, int height, BufferedImage input) {
        int oneHeight = height / FILES;
        BufferedImage two = new BufferedImage(width, oneHeight,  BufferedImage.TYPE_INT_RGB);

        int x = 0;
        int y = 3;
        for (int oneX = 0; oneX < width && x < width; oneX++) {
            y = 3;
            for (int oneY = 0; oneY < oneHeight; oneY++) {
                two.setRGB(oneX, oneY, input.getRGB(x, y));
                y = y + FILES;
            }
            x++;
        }
        return two;
    }

    public static BufferedImage left(BufferedImage input) {
        final BufferedImage res = new BufferedImage(input.getHeight(), input.getWidth(),  BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                int rgb = input.getRGB(x, y);
                int sizeX = y;
                int sizeY = input.getWidth() - 1 - x;
                res.setRGB(sizeX, sizeY, rgb);
            }
        }
        return res;
    }

}
