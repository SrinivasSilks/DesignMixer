package com.varaprasadps.no3.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/2rani-%s-%s.bmp";
        final BufferedImage skirt = ImageIO.read(new File("z-data/in/6/2/SKIRT_RANI.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 32));
        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 12));

        // Locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 20));

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 480));
        inputBIs.add(skirt);

        // Jari
        inputBIs.add(HorizontalRepeatGenerator.get(12, ImageIO.read(new File("z-data/in/6/2/SUNUNDA.bmp"))));
        inputBIs.add(HorizontalRepeatGenerator.get(12, ImageIO.read(new File("z-data/in/6/2/BANARAS.bmp"))));
        inputBIs.add(HorizontalRepeatGenerator.get(12, ImageIO.read(new File("z-data/in/6/2/TEEGA.bmp"))));

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 12));

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
