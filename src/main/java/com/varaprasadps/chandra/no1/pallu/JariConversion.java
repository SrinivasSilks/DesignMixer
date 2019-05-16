package com.varaprasadps.chandra.no1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-chandra/out/1/p-jari-%s-%s.bmp";

        final BufferedImage up = ImageIO.read(new File("z-chandra/in/1/PALLU/PALLU_JARI_UP.bmp"));
        final BufferedImage pallu = ImageIO.read(new File("z-chandra/in/1/PALLU/PALLU_JARI.bmp"));
        final BufferedImage down = ImageIO.read(new File("z-chandra/in/1/PALLU/PALLU_JARI_DOWN.bmp"));
        final BufferedImage skirt = CutLayoutGenerator.get(ImageIO.read(new File("z-chandra/in/1/PALLU/PALLU_JARI_SKIRT.bmp")), 584).get(0);
        int width = skirt.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        inputBIs.add(EmptyGenerator.get(width, 2));
        //locking
        inputBIs.add(StepLayoutGenerator.get(width));
        //Achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(up);
        inputBIs.add(pallu);
        inputBIs.add(down);
        inputBIs.add(skirt);
        // locking
        inputBIs.add(StepLayoutGenerator.get(width, 4));

        inputBIs.add(EmptyGenerator.get(width, 360));

        // locking
        inputBIs.add(EmptyGenerator.get(width, 4));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));

        // Achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        inputBIs.add(EmptyGenerator.get(width, 128));

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
