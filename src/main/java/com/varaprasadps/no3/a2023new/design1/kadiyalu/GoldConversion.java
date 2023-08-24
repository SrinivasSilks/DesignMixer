package com.varaprasadps.no3.a2023new.design1.kadiyalu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GoldConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/3/out/design1/1brc-gold-%s-%s.bmp";

        final BufferedImage body = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("d/3/in/design1/brocade/jari.bmp")));
        int width = body.getWidth();

        BufferedImage right = HorizontalRepeatGenerator.get(4, EmptyGenerator.get(300, 632));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(4, EmptyGenerator.get(300, 168)));


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
        inputBIs.add(EmptyGenerator.get(width, 8));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));

        //left
        inputBIs.add(left);

        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 6));
        inputBIs.add(PlainGenerator.get(width, 2));
        //right part1
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(0));
        //body
        inputBIs.add(body);
        //salari
        inputBIs.add(EmptyGenerator.get(width, 64));
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 6));
        //right part1
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(1));

        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

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
