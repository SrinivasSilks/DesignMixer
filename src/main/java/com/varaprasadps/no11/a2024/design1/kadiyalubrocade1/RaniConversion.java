package com.varaprasadps.no11.a2024.design1.kadiyalubrocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/11/out/a2024/design1/1kbrc-rani-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("d/11/in/a2024/design1/border/right.bmp")));
        BufferedImage left = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("d/11/in/a2024/design1/border/left-first.bmp")));
        int width = left.getWidth();
        BufferedImage body = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("d/11/in/a2024/design1/brocade1/silver.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(ReverseGenerator.get(AchuLayoutGenerator.get(width, 8)));
        //jamudu
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(PlainGenerator.get(width, 4));
        //body
        inputBIs.add(body);
        inputBIs.add(body);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(PlainGenerator.get(width, 4));
        //right
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));

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
