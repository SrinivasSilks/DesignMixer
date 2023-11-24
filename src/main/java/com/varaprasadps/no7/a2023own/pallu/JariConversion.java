package com.varaprasadps.no7.a2023own.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/7/out/design1/pallu-jari-%s-%s.bmp";

        BufferedImage pallu = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/7/in/design1/pallu/pallu-jari.bmp")));

        int width = pallu.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 1));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //left
        inputBIs.add(EmptyGenerator.get(width, 186));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(CutLayoutGenerator.get(pallu, pallu.getHeight() - 12).get(1));
        //body
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        //locking
        inputBIs.add(CutLayoutGenerator.get(pallu, 12).get(0));
        inputBIs.add(PlainGenerator.get(width, 4));
        //right
        inputBIs.add(EmptyGenerator.get(width, 584));

        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));

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
