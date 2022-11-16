package com.varaprasadps.sk.no3.design1.anni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PlainConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-sk/out/3/design1/1PLAIN.bmp";

        final BufferedImage left = EmptyGenerator.get(120, 132);
        final BufferedImage right = EmptyGenerator.get(120, 308);

        final int width = right.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 6));
        //dunno
        inputBIs.add(EmptyGenerator.get(width, 4));

        //left
        inputBIs.add(VerticalFlipGenerator.get(left));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //body
        inputBIs.add(PlainGenerator.get(width, 480));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //right
        inputBIs.add(right);

        //dunno
        inputBIs.add(EmptyGenerator.get(width, 4));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        inputBIs.add(BlackGenerator.get(width, 1));

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
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
