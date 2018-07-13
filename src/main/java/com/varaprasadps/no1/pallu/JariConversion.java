package com.varaprasadps.no1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/p-jari-%s-%s.bmp";

        final BufferedImage pallu = HorizontalRepeatGenerator.get(6, ImageIO.read(new File("z-data/in/1/PALLU_JARI.bmp")));
        int width = pallu.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();
        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        // Khali
        inputBIs.add(EmptyGenerator.get(width, 4));
        //Achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        // Locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 5)));

        inputBIs.add(pallu);
        inputBIs.add(pallu);

        inputBIs.add(pallu);
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        inputBIs.add(pallu);

        // Jari
        inputBIs.add(EmptyGenerator.get(width, 60));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));

        // Khali
        inputBIs.add(EmptyGenerator.get(width, 4));

        // Achu
        inputBIs.add(EmptyGenerator.get(width, 8));


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
