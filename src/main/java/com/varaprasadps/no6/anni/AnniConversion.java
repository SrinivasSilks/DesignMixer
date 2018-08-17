package com.varaprasadps.no6.anni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/anni-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(60, 32));
        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(60, 2)));
        inputBIs.add(EmptyGenerator.get(60, 2));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(60, 12));


        // Locking
        inputBIs.add(PlainGenerator.get(60, 20));
        // All over
        inputBIs.add(PlainGenerator.get(60, 480));
        // Skirt
        inputBIs.add(PlainGenerator.get(60, 1200));

        inputBIs.add(ImageIO.read(new File("z-data/in/1/SUNUNDA.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/in/1/BANARAS.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/in/1/TEEGA.bmp")));


        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(60, 2)));
        inputBIs.add(EmptyGenerator.get(60, 2));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(60, 12));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
