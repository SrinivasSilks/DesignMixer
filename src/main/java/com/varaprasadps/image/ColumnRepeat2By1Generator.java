package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ColumnRepeat2By1Generator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/out/repeat-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-data/out/plain-16-1824.bmp");
        inputs.add("z-data/out/empty-16-1824.bmp");

        List<BufferedImage> inputBIs = new LinkedList<>();
        for (String input : inputs) {
            inputBIs.add(ImageIO.read(new File(input)));
        }

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth += bi.getWidth();
            repeatHeight = bi.getHeight();
        }
        BufferedImage bi = map(repeatWidth, repeatHeight, inputBIs);
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    public static BufferedImage get(List<BufferedImage> inputBIs) {
        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth += bi.getWidth();
            repeatHeight = bi.getHeight();
        }
        return map(repeatWidth, repeatHeight, inputBIs);
    }

    private static BufferedImage map(int sizeX, int sizeY, List<BufferedImage> inputBIs) {
        int key = inputBIs.size();
        final BufferedImage result = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_BYTE_BINARY);
        for (int rowId = 0; rowId < sizeX; rowId++) {
            int dividend = rowId % 4;
            int x = rowId / key;
            System.out.println("x : " + x);
            if (dividend == 0) {
                copyCol(rowId, x, result, inputBIs.get(0));
            } else if (dividend == 1) {
                copyCol(rowId, x + 1, result, inputBIs.get(0));
            } else if (dividend == 2) {
                copyCol(rowId, x - 1, result, inputBIs.get(1));
            } else if (dividend == 3) {
                copyCol(rowId, x, result, inputBIs.get(1));
            }
        }
        return result;
    }

    private static void copyCol(int rowId, int x, BufferedImage result, BufferedImage fileOne) {
        for (int y = 0; y < fileOne.getHeight(); y++) {
            result.setRGB(rowId, y, fileOne.getRGB(x, y));
        }
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
