package com.varaprasadps.vasu.no4.a2020.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/4/a2020/p-rani-%s-%s.bmp";


        BufferedImage bo = ImageIO.read(new File("z-vasu/in/4/a2020/border.bmp"));
        final BufferedImage border = RightLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(4, bo)), 2040).get(0));
        final BufferedImage pallu = HorizontalFlipGenerator.get(ImageIO.read(new File("z-vasu/in/4/a2020/pallu-rani.bmp")));
        List<BufferedImage> inputBIs = new LinkedList<>();

        // Board Khali
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 128));

        // Box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 12));

        inputBIs.add(CutLayoutGenerator.get(pallu, 400).get(0));
        inputBIs.add(border);

        // Locking
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 16));

        // Box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 2)));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 10));

        // Board Khali
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 256));

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
