package com.varaprasadps.no12.brocade11;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/12/11/nimbu-%s-%s.bmp";

        final BufferedImage skirt = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/brocade11/NIMBU.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 32));


        // Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 4));

        //Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 8));

        // Locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(skirt.getWidth(), 4)));

        inputBIs.add(skirt);

        // Jari
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 280));

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));

        // Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 4));

        // Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 8));


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
