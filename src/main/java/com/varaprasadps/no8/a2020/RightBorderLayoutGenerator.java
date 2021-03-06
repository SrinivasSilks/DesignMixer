package com.varaprasadps.no8.a2020;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.StepLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RightBorderLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/8/a2020/border/right-border.bmp";

        BufferedImage bugada = HorizontalRepeatGenerator.get(15, ImageIO.read(new File("z-data/in/8/a2020/bor-edi/bugada.bmp")));
        BufferedImage teega = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("z-data/in/8/a2020/bor-edi/teega.bmp")));
        BufferedImage mango = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/8/a2020/bor-edi/mango.bmp")));
        BufferedImage border220 = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/8/a2020/bor-edi/220.bmp")));
        BufferedImage roundmango = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/8/a2020/bor-edi/round-mango.bmp")));
        BufferedImage flower = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/8/a2020/bor-edi/flower.bmp")));

        int width = bugada.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(bugada);
        inputBIs.add(mango);
        inputBIs.add(teega);
        inputBIs.add(CutLayoutGenerator.get(StepLayoutGenerator.get(width, 2, 6), 8).get(0));
        inputBIs.add(border220);
        inputBIs.add(mango);

        inputBIs.add(CutLayoutGenerator.get(StepLayoutGenerator.get(width, 135, 6), 288).get(1));
        inputBIs.add(mango);
        inputBIs.add(teega);
        inputBIs.add(CutLayoutGenerator.get(StepLayoutGenerator.get(width, 2, 6), 8).get(0));
        inputBIs.add(border220);


        inputBIs.add(mango);

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
