package com.vasu.loom6.a2024.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/6/a2024/design1/pallu-jari-%s-%s.bmp";

        final BufferedImage pallu = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/6/a2024/design1/pallu/pallu-jari.bmp")));

        BufferedImage leftt = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-vasu/in/6/a2024/design1/border/left.bmp")));
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(leftt, 60, 1), pallu.getWidth(), 0);

        BufferedImage rightt = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-vasu/in/6/a2024/design1/border/right.bmp")));
        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rightt, 60, 1), pallu.getWidth(), 0);

        int width = right.getWidth();


        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        //all over
        inputBIs.add(pallu);
        //locking
        inputBIs.add(StepLayoutGenerator.get(width, 1));

        //left border
        inputBIs.add(EmptyGenerator.get(left.getWidth(), left.getHeight()));
        //right border
        inputBIs.add(EmptyGenerator.get(right.getWidth(), right.getHeight()));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 14));

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
