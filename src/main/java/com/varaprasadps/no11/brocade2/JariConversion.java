package com.varaprasadps.no11.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/11/2/jari-%s-%s.bmp";

        final BufferedImage skirt = CutLayoutGenerator.get(HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/brocade2/JARI.bmp"))), 1480).get(0);

        int width = skirt.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));

        //Achu Khali
        inputBIs.add(EmptyGenerator.get(width, 8));

        // Locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(skirt.getWidth(), 1)));

        inputBIs.add(skirt);

        // Jari
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 280));

        //Achu Khali
        inputBIs.add(EmptyGenerator.get(width, 16));

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
