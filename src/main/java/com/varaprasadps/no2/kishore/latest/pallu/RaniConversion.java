package com.varaprasadps.no2.kishore.latest.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/2/kishore/latest/p-rani-%s-%s.bmp";

        final BufferedImage pallu = ImageIO.read(new File("z-data/in/2/kishore/latest/pallu/pallu-rani.bmp"));
        int width = pallu.getWidth();
        final BufferedImage left = ImageIO.read(new File("z-data/in/2/kishore/latest/pallu/pallu-left.bmp"));
        final BufferedImage right = ImageIO.read(new File("z-data/in/2/kishore/latest/pallu/pallu-right.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 256));

        //box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0));
        //khali
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 8));

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 16));
        inputBIs.add(pallu);
        //locking
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 16));
        //right
        inputBIs.add(right);
        //box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(pallu.getWidth(), 4), 2).get(0)));
        //khali
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 8));

        inputBIs.add(EmptyGenerator.get(width, 128));

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
