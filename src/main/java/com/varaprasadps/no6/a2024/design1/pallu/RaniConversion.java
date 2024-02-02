package com.varaprasadps.no6.a2024.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/6/out/design1/p-rani-%s-%s.bmp";

        final BufferedImage pallu = PlainGenerator.get(1620, 240);

        BufferedImage rightt = HorizontalRepeatGenerator.get(16, ImageIO.read(new File("d/6/in/design1/border/right.bmp")));
        BufferedImage leftt = HorizontalRepeatGenerator.get(16, ImageIO.read(new File("d/6/in/design1/border/left.bmp")));
        BufferedImage right = RightLayoutGenerator.get(CutLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(rightt), 180).get(1), pallu.getWidth()).get(0));
        BufferedImage left = RightLayoutGenerator.get(CutLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(leftt), 180).get(1), pallu.getWidth()).get(0));

        List<BufferedImage> inputBIs = new LinkedList<>();

        int width = pallu.getWidth();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        inputBIs.add(left);
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        inputBIs.add(right);

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //box
        inputBIs.add(EmptyGenerator.get(width, 1));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 6));

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
