package com.varaprasadps.vasu.no4.jr.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/4/jr/design1/p-jari-%s-%s.bmp";

        final BufferedImage pallu = ImageIO.read(new File("z-vasu/in/4/jr/design1/pallu/pallu-jari.bmp"));
        int width = pallu.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0));

        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(pallu, 120).get(0));
        //left
        inputBIs.add(EmptyGenerator.get(width, 774));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(EmptyGenerator.get(width, 256));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = LeftLayoutGenerator.get(HorizontalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
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
