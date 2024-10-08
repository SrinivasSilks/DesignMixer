package com.varaprasadps.no3.a2024.design2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/3/out/2024/design2/anni-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/border/right.bmp")));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/border/left.bmp"))));

        final int width = right.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));

        //left border
        inputBIs.add(left);

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 8));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //right border PART-1
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(0));
        //allover
        inputBIs.add(PlainGenerator.get(width, 480));
        //salari
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 64)));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //right border PART-2
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(1));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 8));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
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
        BufferedImage bi = LeftLayoutGenerator.get(ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
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
