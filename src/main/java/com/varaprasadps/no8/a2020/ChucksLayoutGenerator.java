package com.varaprasadps.no8.a2020;

import com.varaprasadps.image.CutLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChucksLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/8/a2020/chucks-design.bmp";
        BufferedImage bugada = ImageIO.read(new File("z-data/out/8/a2020/jari-edit.bmp"));

        List<BufferedImage> inputBIs = new ArrayList<>();

        List<BufferedImage> images = CutLayoutGenerator.get(bugada, 16);

        while (images.get(1).getHeight() >= 16) {
            inputBIs.add(CutLayoutGenerator.get(images.get(0), 2).get(0));
            if (images.get(1).getHeight() == 16) {
                inputBIs.add(CutLayoutGenerator.get(images.get(1), 2).get(0));
                break;
            }
            images = CutLayoutGenerator.get(images.get(1), 16);
        }

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
