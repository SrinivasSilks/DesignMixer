package com.varaprasadps.no1.a2021.design2.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/a2021/design2/p-rani-%s-%s.bmp";

        final BufferedImage pallu = ImageIO.read(new File("z-data/in/1/a2021/design2/pallu/pallu-rani.bmp"));

        final BufferedImage leftt = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/in/1/a2021/design2/border/left.bmp")));
        final BufferedImage rightt = ImageIO.read(new File("z-data/in/1/a2021/design2/border/right.bmp"));

        BufferedImage right = CutLayoutGenerator.get(HorizontalRepeatGenerator.get(5, rightt), 1900, 0);
        BufferedImage left = CutLayoutGenerator.get(HorizontalRepeatGenerator.get(5, leftt), 1900, 0);

        int width = pallu.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        inputBIs.add(pallu);
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //right
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 14));

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