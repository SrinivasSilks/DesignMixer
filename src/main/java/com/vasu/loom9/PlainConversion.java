package com.vasu.loom9;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PlainConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/in/9/out/1PLAIN.bmp";

        final BufferedImage plain = PlainGenerator.get(60, 480);

        int width = plain.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kaduiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 24));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));

        //left
        inputBIs.add(EmptyGenerator.get(width, 310));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //body
        inputBIs.add(plain);
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //right
        inputBIs.add(EmptyGenerator.get(width, 520));
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 24));
        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kaduiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //config
        inputBIs.add(EmptyGenerator.get(width, 1));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = LeftLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
