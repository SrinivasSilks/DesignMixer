package com.varaprasadps.no3.a2023new.design1.brocade5;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/3/out/design1/5brc-rani-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(11, ImageIO.read(new File("d/3/in/design1/border/right.bmp")));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(11, ImageIO.read(new File("d/3/in/design1/border/left-first.bmp"))));

        int width = left.getWidth();

        final BufferedImage body = PlainGenerator.get(width, 480);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(ReverseGenerator.get(AchuLayoutGenerator.get(width, 8)));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));

        //left border
        inputBIs.add(left);

        //jamudu
        inputBIs.add(KadiyaluLayoutGenerator.kadiyalu(PlainGenerator.get(width, 8)));

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(PlainGenerator.get(width, 4));
        // Right part 1
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(0));
        //body
        inputBIs.add(body);
        //salari
        inputBIs.add(EmptyGenerator.get(width, 64));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(PlainGenerator.get(width, 4));
        // Right part 2
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(1));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 8));

        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));


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
