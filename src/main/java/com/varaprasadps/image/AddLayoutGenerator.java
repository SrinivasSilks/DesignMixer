package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/out/BROCADE_RANI.bmp";
        BufferedImage body = HorizontalRepeatGenerator.get(9, ImageIO.read(new File("z-data/in/6/2/BODY_NIMBU.bmp")));
        BufferedImage skirt = ImageIO.read(new File("z-data/in/6/2/SKIRT_RANI.bmp"));

        BufferedImage emptyJari = EmptyGenerator.get(skirt.getWidth(), 60);

        BufferedImage sununda = HorizontalRepeatGenerator.get(12, ImageIO.read(new File("z-data/in/6/2/SUNUNDA.bmp")));
        BufferedImage banaras = HorizontalRepeatGenerator.get(12, ImageIO.read(new File("z-data/in/6/2/BANARAS.bmp")));
        BufferedImage teega = HorizontalRepeatGenerator.get(12, ImageIO.read(new File("z-data/in/6/2/TEEGA.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 480));
        inputBIs.add(skirt);
        inputBIs.add(sununda);
        inputBIs.add(banaras);
        inputBIs.add(teega);

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
