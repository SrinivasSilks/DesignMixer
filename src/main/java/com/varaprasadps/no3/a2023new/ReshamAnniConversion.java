package com.varaprasadps.no3.a2023new;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ReshamAnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/3/reshamanni-%s-%s.bmp";

        final int width = 4;

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
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 60 / 4)));
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 40 / 4)));
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 68 / 4)));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //right border PART-2
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 264/4)));
        //allover
        inputBIs.add(PlainGenerator.get(width, 480));
        //salari
        inputBIs.add(EmptyGenerator.get(width, 64));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //right border PART-1
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 368/4)));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));

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
