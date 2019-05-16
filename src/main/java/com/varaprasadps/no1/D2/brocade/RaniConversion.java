package com.varaprasadps.no1.D2.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/D2/L1-rani-%s-%s.bmp";
        final BufferedImage skirt = ImageIO.read(new File("z-data/in/1/D2/brocade/RANI.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        // Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 4));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 8));

        // Locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 20));

        inputBIs.add(skirt);

        // Jari
        inputBIs.add(HorizontalRepeatGenerator.get(10, ImageIO.read(new File("z-data/in/1/SUNUNDA.bmp"))));
        inputBIs.add(HorizontalRepeatGenerator.get(10, ImageIO.read(new File("z-data/in/1/BANARAS.bmp"))));
        inputBIs.add(HorizontalRepeatGenerator.get(10, ImageIO.read(new File("z-data/in/1/TEEGA.bmp"))));

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        // Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 4));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 8));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
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
