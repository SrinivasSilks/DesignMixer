package com.varaprasadps.no6.pallu2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/2/p-rani-%s-%s.bmp";

        final BufferedImage pallu = ImageIO.read(new File("z-data/in/6/2/PALLU_RANI.bmp"));
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 32));
        // Box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 2)));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 12));

        // Locking
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 20));
        inputBIs.add(CutLayoutGenerator.get(pallu,260).get(1));
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        // Locking
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 20));

        // Jari
        inputBIs.add(HorizontalRepeatGenerator.get(32, ImageIO.read(new File("z-data/in/6/2/SUNUNDA.bmp"))));
        inputBIs.add(HorizontalRepeatGenerator.get(32, ImageIO.read(new File("z-data/in/6/2/BANARAS.bmp"))));
        inputBIs.add(HorizontalRepeatGenerator.get(32, ImageIO.read(new File("z-data/in/6/2/TEEGA.bmp"))));

        // Box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 2)));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 12));

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
