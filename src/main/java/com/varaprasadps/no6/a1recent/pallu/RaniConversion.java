package com.varaprasadps.no6.a1recent.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/a1recent/p-rani-%s-%s.bmp";

        final BufferedImage pallu = ImageIO.read(new File("z-data/in/6/a1recent/PALLU_RANI.bmp"));
        final BufferedImage border = ImageIO.read(new File("z-data/in/6/a1recent/pallu/PALLU_BORDER.bmp"));

        final BufferedImage sunandda = ImageIO.read(new File("z-data/in/6/a1recent/pallu/PALLU_SUNUNDA.bmp"));
        final BufferedImage banaras = ImageIO.read(new File("z-data/in/6/a1recent/pallu/PALLU_BANARAS.bmp"));
        final BufferedImage teega = ImageIO.read(new File("z-data/in/6/a1recent/pallu/PALLU_TEEGA.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 32));
        // Box
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 4));
        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 10));

        // Locking
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 20));
        inputBIs.add(pallu);
        inputBIs.add(border);


        // Jari
        inputBIs.add(sunandda);
        inputBIs.add(banaras);
        inputBIs.add(teega);

        // Box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 2)));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 10));

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
