package com.varaprasadps.no4.a2023.design3.kadiyalubroc;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/4/out/design3/1kbroc-rani-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(30, ImageIO.read(new File("d/4/in/design3/border/right.bmp")));
        BufferedImage left = HorizontalRepeatGenerator.get(30, ImageIO.read(new File("d/4/in/design3/border/left-first.bmp")));

        int width = left.getWidth();

        BufferedImage body = HorizontalRepeatGenerator.get(17, ImageIO.read(new File("d/4/in/design3/brocade1/silver.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //achu
        inputBIs.add(ReverseGenerator.get(AchuLayoutGenerator.get(width, 8)));
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));

        //left
        inputBIs.add(VerticalFlipGenerator.get(left));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(PlainGenerator.get(width, 4));
        //body
        inputBIs.add(body);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(PlainGenerator.get(width, 4));
        //right
        inputBIs.add(right);

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));


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
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
