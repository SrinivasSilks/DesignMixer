package com.vasu.loom6.a2024.design2.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/6/a2024/design2/pallu-rani-%s-%s.bmp";

        final BufferedImage pallu = PlainGenerator.get(1600, 960);

        BufferedImage leftt = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("z-vasu/in/6/a2024/design2/border/left.bmp")));
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(leftt, 60, 1), pallu.getWidth(), 0);

        BufferedImage rightt = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("z-vasu/in/6/a2024/design2/border/right.bmp")));
        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rightt, 60, 1), pallu.getWidth(), 0);

        int width = right.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //kali
        inputBIs.add(EmptyGenerator.get(width, 128));

        //box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0));
        //achu
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 10));

        //all over
        inputBIs.add(pallu);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //left border
        inputBIs.add(left);
        //right border
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0)));
        //achu
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 14));

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
