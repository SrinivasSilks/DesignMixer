package com.varaprasadps.no7.se2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/7/s-jari-%s-%s.bmp";

        final BufferedImage pallu = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/7/brocade_1/JARI.bmp")));

        int width = pallu.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));

        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));

        //Achu
        inputBIs.add(EmptyGenerator.get(width, 6));

        // Locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 1)));

        inputBIs.add(CutLayoutGenerator.get(pallu, 480).get(0));
        inputBIs.add(CutLayoutGenerator.get(pallu, 480).get(0));
        inputBIs.add(CutLayoutGenerator.get(pallu, 480).get(0));
        inputBIs.add(CutLayoutGenerator.get(pallu, 20).get(0));

        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 5)));

        // Jari
        inputBIs.add(EmptyGenerator.get(width, 280));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));

        // Achu
        inputBIs.add(EmptyGenerator.get(width, 14));

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
