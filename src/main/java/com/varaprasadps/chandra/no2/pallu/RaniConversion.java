package com.varaprasadps.chandra.no2.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-chandra/out/2/p-rani-%s-%s.bmp";

        final BufferedImage pallu = ImageIO.read(new File("z-chandra/in/2/PALLU_RANI.bmp"));
        final BufferedImage skirt = ImageIO.read(new File("z-chandra/in/2/PALLU_RANI_SKIRT.bmp"));
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 128));

        // Box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 2)));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 12));

        inputBIs.add(pallu);
        inputBIs.add(skirt);

        // Box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 2)));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 12));

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 128));

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
