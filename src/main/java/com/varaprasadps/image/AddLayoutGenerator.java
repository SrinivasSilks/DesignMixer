package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/8/2020-oct-new/border.bmp";

        BufferedImage peacock = ImageIO.read(new File("z-data/in/8/2020-oct-new/thcxfth.bmp"));
        BufferedImage b220 = ImageIO.read(new File("z-data/in/8/2020-oct-new/220.bmp"));
        BufferedImage middle = ImageIO.read(new File("z-data/in/8/2020-oct-new/middle.bmp"));
        BufferedImage teega = ImageIO.read(new File("z-data/in/8/2020-oct-new/teega-full.bmp"));
        BufferedImage bugada = ImageIO.read(new File("z-data/in/8/2020-oct-new/bugada-full.bmp"));
        int width = b220.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //border
        inputBIs.add(StepLayoutGenerator.get(width, 6, 5));
        inputBIs.add(bugada);

        inputBIs.add(b220);
        inputBIs.add(CutLayoutGenerator.get(peacock, 1199).get(1));
        inputBIs.add(StepLayoutGenerator.get(width, 21, 5));
        inputBIs.add(middle);
        inputBIs.add(StepLayoutGenerator.get(width, 21, 5));
        inputBIs.add(CutLayoutGenerator.get(peacock, 1199).get(1));
        inputBIs.add(b220);
        inputBIs.add(teega);


//        // right
//        inputBIs.add(bugada);
//        inputBIs.add(teega);
//        inputBIs.add(b220);
//        inputBIs.add(CutLayoutGenerator.get(peacock, 1199).get(1));
//        inputBIs.add(StepLayoutGenerator.get(width, 21, 5));
//        inputBIs.add(middle);
//        inputBIs.add(StepLayoutGenerator.get(width, 21, 5));
//        inputBIs.add(CutLayoutGenerator.get(peacock, 1199).get(1));
//        inputBIs.add(teega);
//        inputBIs.add(b220);


        // left
//        inputBIs.add(bugada);
//        inputBIs.add(teega);
//        inputBIs.add(StepLayoutGenerator.get(width, 6, 5));
//        inputBIs.add(StepLayoutGenerator.get(width, 6, 5));
//        inputBIs.add(StepLayoutGenerator.get(width, 6, 5));
//        inputBIs.add(teega);
//        inputBIs.add(b220);
//        inputBIs.add(teega);

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


    public static BufferedImage get(List<BufferedImage> inputBIs) {
        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        final BufferedImage result = new BufferedImage(repeatWidth, repeatHeight, BufferedImage.TYPE_INT_RGB);
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
