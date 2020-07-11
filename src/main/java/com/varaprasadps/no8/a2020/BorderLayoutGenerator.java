package com.varaprasadps.no8.a2020;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/8/a2020/border/border.bmp";

        BufferedImage bugada = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/8/a2020/bor-edi/bugada.bmp")));
        BufferedImage teega = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/8/a2020/bor-edi/teega.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(StepLayoutGenerator.get(60, 5, 6));
        inputBIs.add(bugada);

        inputBIs.add(CutLayoutGenerator.get(StepLayoutGenerator.get(60, 135, 6), 4).get(1));

        inputBIs.add(CutLayoutGenerator.get(StepLayoutGenerator.get(60, 37, 6), 220).get(0));

        inputBIs.add(StepLayoutGenerator.get(60, 1, 6));
        inputBIs.add(teega);
        inputBIs.add(HorizontalFlipGenerator.get(StepLayoutGenerator.get(60, 1, 6)));


        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = get(repeatWidth, repeatHeight, inputBIs);
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    public static BufferedImage get(int sizeX, int sizeY, List<BufferedImage> inputBIs) {
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

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
