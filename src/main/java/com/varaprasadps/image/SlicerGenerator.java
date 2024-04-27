package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SlicerGenerator {

    public static void main(String[] args) throws IOException {
        BufferedImage right = ImageIO.read(new File("d/1a-test/right.bmp"));
        LinkedList<BufferedImage> rightSilces = get(right, 2);
        BufferedImage rightCollection = attachX(rightSilces);
        saveBMP(rightCollection, "d/1a-test/right-clone.bmp");
    }

    public static LinkedList<BufferedImage> get(BufferedImage actual, int size) {
        LinkedList<BufferedImage> images = new LinkedList<>();
        BufferedImage silice = new BufferedImage(size, actual.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        for (int i = 0; i < actual.getWidth(); i++) {
            if (i % size == 0 && i != 0) {
                images.add(silice);
                silice = new BufferedImage(size, actual.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            }
            for (int j = 0; j < actual.getHeight(); j++) {
                int actualRGB = actual.getRGB(i, j);
                silice.setRGB(i % size, j, actualRGB);
            }
        }
        images.add(silice);
        return images;
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

    public static BufferedImage attachX(LinkedList<BufferedImage> inputBIs) throws IOException {
        int width = 0;
        int height = 0;

        for (int i = 0; i < inputBIs.size(); i++) {
            width += inputBIs.get(i).getWidth();
            height = inputBIs.get(i).getHeight();
        }

        final BufferedImage resultR = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        int resultWidth = 0;

        for (int i = 0; i < inputBIs.size(); i++) {
            BufferedImage image = inputBIs.get(i);
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    resultR.setRGB(resultWidth + x, y, image.getRGB(x, y));
                }
            }
            resultWidth = resultWidth + image.getWidth();
        }
        return resultR;
    }

    public static BufferedImage attachY(LinkedList<BufferedImage> inputBIs) throws IOException {
        int width = 0;
        int height = 0;

        for (int i = 0; i < inputBIs.size(); i++) {
            width = inputBIs.get(i).getWidth();
            height += inputBIs.get(i).getHeight();
        }

        final BufferedImage resultR = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        int resultHeight = 0;

        for (int i = 0; i < inputBIs.size(); i++) {
            BufferedImage image = inputBIs.get(i);
            saveBMP(image, String.format("d/1a-test/right-clone-%s.bmp", i + 1));
            System.out.println(String.format(" Image %s - width - %s , height - %s", i + 1, image.getWidth(), image.getHeight()));
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    System.out.println("test - " + (resultHeight + y));
                    resultR.setRGB(x, resultHeight + y, image.getRGB(x, y));
                }
            }
            resultHeight = resultHeight + image.getHeight();
        }
        return resultR;
    }

    private static void copyRow(int resultCol, BufferedImage result, int inputCol, BufferedImage fileOne) {
        for (int x = 0; x < fileOne.getWidth(); x++) {
            result.setRGB(x, resultCol, fileOne.getRGB(x, inputCol));
        }
    }


}
