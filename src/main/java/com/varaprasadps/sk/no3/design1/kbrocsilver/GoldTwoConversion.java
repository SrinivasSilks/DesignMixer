package com.varaprasadps.sk.no3.design1.kbrocsilver;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GoldTwoConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-sk/out/3/design1/2ka-broc-silver2-%s-%s.bmp";

        final BufferedImage body = ImageIO.read(new File("z-sk/in/3/design1/butta/silverjari.bmp"));

        int width = body.getWidth();
        final BufferedImage left = EmptyGenerator.get(width, 132);
        final BufferedImage right = EmptyGenerator.get(width, 308);

        List<BufferedImage> inputBIs = new LinkedList<>();

        //box
        inputBIs.add(BlackGenerator.get(width, 2));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 6));
        //dunno
        inputBIs.add(EmptyGenerator.get(width, 4));

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //body
        inputBIs.add(body);
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        //right
        inputBIs.add(right);

        //dunno
        inputBIs.add(EmptyGenerator.get(width, 4));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));

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
