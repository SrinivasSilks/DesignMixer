package com.varaprasadps.no5.jr.design1.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/jr/design1/2nimbu-%s-%s.bmp";

        final BufferedImage brocade = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/5/jr/design1/brocade2/jari.bmp")));

        final BufferedImage right = EmptyGenerator.get(brocade.getWidth(), 720);
        final BufferedImage left = EmptyGenerator.get(brocade.getWidth(), 320);

        int width = brocade.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 6));

        inputBIs.add(left);
        inputBIs.add(brocade);
        inputBIs.add(brocade);
        inputBIs.add(brocade);
        inputBIs.add(right);

        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

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
