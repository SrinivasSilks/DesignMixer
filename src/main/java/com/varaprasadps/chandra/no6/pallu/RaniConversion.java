package com.varaprasadps.chandra.no6.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-chandra/out/6/design1/p-rani-%s-%s.bmp";

        final BufferedImage pallu = ImageIO.read(new File("z-chandra/in/6/design1/pallu/pallu-rani.bmp"));
        int width = pallu.getWidth();

        final BufferedImage rightt = ImageIO.read(new File("z-chandra/in/6/design1/border/right.bmp"));
        final BufferedImage leftt = HorizontalRepeatGenerator.get(rightt.getWidth() / 10, ImageIO.read(new File("z-chandra/in/6/design1/border/left.bmp")));

        BufferedImage rightf = HorizontalRepeatGenerator.get(9, rightt);
        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rightf, 80, 1), 1900, 0);

        BufferedImage leftf = HorizontalRepeatGenerator.get(9, leftt);
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(leftf, 80, 1), 1900, 0);


        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        inputBIs.add(left);
        inputBIs.add(pallu);
        //locking
        inputBIs.add(PlainGenerator.get(width,6));
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 14));

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
