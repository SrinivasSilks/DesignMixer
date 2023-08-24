package com.varaprasadps.no3.a2023new;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariDownConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/3/out/design1/jari-down-%s-%s.bmp";

        final int width = 2;

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //kali
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 16)));


        //left border
        inputBIs.add(EmptyGenerator.get(width, 60));
        inputBIs.add(EmptyGenerator.get(width, 40));
        inputBIs.add(EmptyGenerator.get(width, 68));
        //jamudu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));
        //locking
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));
        //right border PART-1
        inputBIs.add(EmptyGenerator.get(width, 264));
        //allover
        inputBIs.add(PlainGenerator.get(width, 480));
        //salari
        inputBIs.add(EmptyGenerator.get(width, 64));
        //locking
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));
        //right border PART-1
        inputBIs.add(EmptyGenerator.get(width, 368));

        //jamudu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));

        //kali
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 16)));


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
