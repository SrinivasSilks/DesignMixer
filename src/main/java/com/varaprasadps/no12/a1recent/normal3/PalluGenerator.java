package com.varaprasadps.no12.a1recent.normal3;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.ReverseGenerator;
import com.varaprasadps.image.StepLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PalluGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/12/a1recent/normal3/pallu/pallu-jari.bmp";

        BufferedImage lower = ImageIO.read(new File("z-data/in/12/a1recent/normal3/edit/lower-jari.bmp"));
        BufferedImage upper = ImageIO.read(new File("z-data/in/12/a1recent/normal3/edit/upper-jari.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(upper);
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(StepLayoutGenerator.get(480, 170, 5), 641).get(0)));
        inputBIs.add(lower);
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
