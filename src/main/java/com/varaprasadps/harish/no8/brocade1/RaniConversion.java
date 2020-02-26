package com.varaprasadps.harish.no8.brocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-harish/out/8/1rani-%s-%s.bmp";

        final BufferedImage left = HorizontalRepeatGenerator.get(9, ImageIO.read(new File("z-harish/in/8/border/left.bmp")));
        final BufferedImage right = HorizontalRepeatGenerator.get(9, ImageIO.read(new File("z-harish/in/8/border/1right.bmp")));
        final BufferedImage body = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-harish/in/8/border/meena.bmp")));

        int width = left.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //kali
        inputBIs.add(EmptyGenerator.get(width, 128));

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));
        //left border
        inputBIs.add(left);
        inputBIs.add(body);
        //right border
        inputBIs.add(right);
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //achu
        inputBIs.add(ReverseGenerator.get(AchuLayoutGenerator.get(width, 8)));

        //kali
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
