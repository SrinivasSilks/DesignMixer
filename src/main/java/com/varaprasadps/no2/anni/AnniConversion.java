package com.varaprasadps.no2.anni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/2/2anni-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(60, 2)));
        inputBIs.add(EmptyGenerator.get(60, 2));

        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(60, 4), 2).get(0));

        //Khali
        inputBIs.add(EmptyGenerator.get(60, 2));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(60, 8));

        // Locking
        inputBIs.add(PlainGenerator.get(60, 20));
        // All over
        inputBIs.add(PlainGenerator.get(60, 480));
        // Skirt
        inputBIs.add(PlainGenerator.get(60, 1200));

        inputBIs.add(ImageIO.read(new File("z-data/in/2/SUNUNDA.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/in/2/BANARAS.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/in/2/TEEGA.bmp")));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(60, 2)));
        inputBIs.add(EmptyGenerator.get(60, 2));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(60, 4), 2).get(0)));

        //Khali
        inputBIs.add(EmptyGenerator.get(60, 2));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(60, 8));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = HorizontalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
