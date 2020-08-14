package com.varaprasadps.vasu.no4.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/4/test/jari-%s-%s.bmp";
        final BufferedImage skirt = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-vasu/in/4/B_NIMBU.bmp")));
        final BufferedImage body = HorizontalRepeatGenerator.get(17, ImageIO.read(new File("z-vasu/in/4/brocade/1.bmp")));


        List<BufferedImage> inputBIs = new LinkedList<>();

        // Board Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 128));
        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(skirt.getWidth(), 4), 2).get(0));
        //Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 12));

        inputBIs.add(body);
        inputBIs.add(CutLayoutGenerator.get(skirt,400 ).get(1));

        // Locking
        inputBIs.add(HorizontalFlipGenerator.get(ReverseGenerator.get(StepLayoutGenerator.get(skirt.getWidth(), 4))));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(skirt.getWidth(), 4), 2).get(0)));

        // Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 10));

        // Board Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 256));

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
