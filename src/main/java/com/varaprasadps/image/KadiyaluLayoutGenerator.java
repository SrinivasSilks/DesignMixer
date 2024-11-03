package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "d/14/in/2024/nov04/border/left-first.bmp";
        String input = "d/14/in/2024/nov04/border/left.bmp";
        BufferedImage bi = kadiyalu(input);
        displayPixels(bi);
        saveBMP(bi, String.format(out, bi.getWidth(), bi.getHeight()));
    }

    public static BufferedImage kadiyalu(String input) throws IOException {
        BufferedImage inputBi = ImageIO.read(new File(input));
        return kadiyalu(inputBi);
    }

    public static BufferedImage kadiyalu(BufferedImage inputBi) {
        BufferedImage image = LeftLayoutGenerator.get(inputBi);

        List<BufferedImage> inputBIs = new LinkedList<>();

        List<BufferedImage> cc = CutLayoutGenerator.get(image, 1);

        inputBIs.add(cc.get(1));
        inputBIs.add(cc.get(0));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        return RightLayoutGenerator.get(get(repeatWidth, repeatHeight, inputBIs));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    public static BufferedImage get(int sizeX, int sizeY, List<BufferedImage> inputBIs) {
        final BufferedImage result = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_BYTE_BINARY);
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


    public static BufferedImage get(List<BufferedImage> inputBIs) {
        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        final BufferedImage result = new BufferedImage(repeatWidth, repeatHeight, BufferedImage.TYPE_BYTE_BINARY);
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

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
