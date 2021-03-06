package com.varaprasadps.no3.skirtjari.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/3/2-skirt/nimbu-%s-%s.bmp";

        final BufferedImage skirt =  HorizontalRepeatGenerator.get(3, HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/3/2-skirt/NIMBU.bmp"))));
        int width = skirt.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 32));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));

        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(skirt.getWidth(), 4), 2).get(0));

        //Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 10));

        inputBIs.add(CutLayoutGenerator.get(skirt, 1184).get(0));
        inputBIs.add(ReverseGenerator.get(ReverseGenerator.get(PlainGenerator.get(width, 16))));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 176));

        // Locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(skirt.getWidth(), 4)));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(skirt.getWidth(), 4), 2).get(0)));
        // Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 10));

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
