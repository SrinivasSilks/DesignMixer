package com.varaprasadps.no4.a2020;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PalluGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/4/a2020/pallu/pallu_rani.bmp";

        BufferedImage middle = ImageIO.read(new File("z-data/in/3/PALLU/P_RANI_1.bmp"));
        BufferedImage bugadaFigure = ImageIO.read(new File("z-data/in/3/PALLU/P_RANI_2.bmp"));
        BufferedImage ending = ImageIO.read(new File("z-data/in/3/PALLU/P_RANI_3.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(VerticalFlipGenerator.get(ending));
        inputBIs.add(bugadaFigure);

        for (int i = 0; i < 6; i++) {
            inputBIs.add(middle);
        }
        inputBIs.add(CutLayoutGenerator.get(middle, 80).get(0));

        inputBIs.add(bugadaFigure);
        inputBIs.add(ending);
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
