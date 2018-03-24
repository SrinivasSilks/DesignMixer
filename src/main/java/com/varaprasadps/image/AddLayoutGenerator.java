package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/out/layout-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();

        inputs.add("z-data/out/plain-50-10.bmp");
        inputs.add("z-data/out/empty-50-10.bmp");
        inputs.add("z-data/out/plain-50-10.bmp");
        inputs.add("z-data/out/empty-50-10.bmp");
        inputs.add("z-data/out/plain-50-10.bmp");

        List<BufferedImage> inputBIs = new LinkedList<>();
        for (String input : inputs) {
            inputBIs.add(ImageIO.read(new File(input)));
        }

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = map(repeatWidth, repeatHeight, inputBIs);
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static BufferedImage map(int sizeX, int sizeY, List<BufferedImage> inputBIs) {
        final BufferedImage result = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
        int yRes = 0;
        int index = 0;
        while (yRes < result.getHeight()) {
            BufferedImage bi = inputBIs.get(index);
            int y = 0;
            while (y < bi.getHeight()) {
                copyRow(yRes, result, y, bi);
                y++;
                yRes++;
            }
            index++;

        }
        return result;
    }

    private static void copyRow(int resultCol, BufferedImage result, int inputCol, BufferedImage fileOne) {
        for (int x = 0; x < fileOne.getWidth(); x++) {
            result.setRGB(x, resultCol, fileOne.getRGB(x, inputCol));
        }
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
