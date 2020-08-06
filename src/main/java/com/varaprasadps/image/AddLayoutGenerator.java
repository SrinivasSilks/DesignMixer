package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/1/a2020/edit/pallu-rani.bmp";

        BufferedImage middle = ImageIO.read(new File("z-data/in/1/a2020/edit/middle-rani.bmp"));
        BufferedImage side = ImageIO.read(new File("z-data/in/1/a2020/edit/side-rani.bmp"));
        BufferedImage step = ReverseGenerator.get(StepLayoutGenerator.get(600, 45, 5));


        List<BufferedImage> inputBIs = new LinkedList<>();

        List<BufferedImage> sideImages = CutLayoutGenerator.get(side, 85);

        inputBIs.add(sideImages.get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(sideImages.get(1), 100).get(1), 312).get(0));
        inputBIs.add(sideImages.get(0));

        inputBIs.add(VerticalFlipGenerator.get(PlainGenerator.get(600, 220)));
        inputBIs.add(middle);
        inputBIs.add(VerticalFlipGenerator.get(PlainGenerator.get(600, 216)));
//        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(CutLayoutGenerator.get(HorizontalFlipGenerator.get(step), 223).get(0), 6).get(1), 216).get(0));

        inputBIs.add(sideImages.get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(sideImages.get(1), 100).get(1), 312).get(0));
        inputBIs.add(sideImages.get(0));

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
