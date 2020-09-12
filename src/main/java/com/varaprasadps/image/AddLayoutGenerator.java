package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/test/left/left.bmp";

        BufferedImage peacock = ImageIO.read(new File("z-data/in/test/peacock.bmp"));

        BufferedImage lbugada = HorizontalRepeatGenerator.get(peacock.getWidth() / 20, ImageIO.read(new File("z-data/in/test/left/bugada.bmp")));
        BufferedImage lteega = HorizontalRepeatGenerator.get(peacock.getWidth() / 30, ImageIO.read(new File("z-data/in/test/left/teega.bmp")));
        BufferedImage bugada = ImageIO.read(new File("z-data/in/test/bugada.bmp"));
        BufferedImage rudra = ImageIO.read(new File("z-data/in/test/rudra.bmp"));
        BufferedImage line = ImageIO.read(new File("z-data/in/test/line.bmp"));
        BufferedImage esig = ImageIO.read(new File("z-data/in/test/esig.bmp"));

        BufferedImage repeat = StepLayoutGenerator.get(bugada.getWidth(), 4, 6);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(lbugada);

        inputBIs.add(line);
        inputBIs.add(StepLayoutGenerator.get(bugada.getWidth(), 1, 5));
        inputBIs.add(lteega);
        inputBIs.add(VerticalFlipGenerator.get(StepLayoutGenerator.get(bugada.getWidth(), 1, 5)));
        inputBIs.add(line);

        inputBIs.add(repeat);

        inputBIs.add(CutLayoutGenerator.get(repeat, 15).get(0));

        inputBIs.add(line);
        inputBIs.add(StepLayoutGenerator.get(bugada.getWidth(), 1, 5));
        inputBIs.add(lteega);
        inputBIs.add(VerticalFlipGenerator.get(StepLayoutGenerator.get(bugada.getWidth(), 1, 5)));
        inputBIs.add(line);

        inputBIs.add(peacock);

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
