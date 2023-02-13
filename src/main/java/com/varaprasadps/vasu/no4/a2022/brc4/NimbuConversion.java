package com.varaprasadps.vasu.no4.a2022.brc4;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/4/a2022/4broc-nimbu-%s-%s.bmp";

        final BufferedImage border = HorizontalRepeatGenerator.get(1, EmptyGenerator.get(240, 400));

        int width = border.getWidth();
        final BufferedImage brocade = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-vasu/in/4/a2022/brocade4/jari.bmp")));


        List<BufferedImage> inputBIs = new LinkedList<>();
        // Board Khali
        inputBIs.add(EmptyGenerator.get(width, 128));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        inputBIs.add(VerticalFlipGenerator.get(border));
        inputBIs.add(brocade);
        // Locking
        inputBIs.add(StepLayoutGenerator.get(width, 4));
        inputBIs.add(border);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 80)));

        inputBIs.add(EmptyGenerator.get(width, 256));


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
