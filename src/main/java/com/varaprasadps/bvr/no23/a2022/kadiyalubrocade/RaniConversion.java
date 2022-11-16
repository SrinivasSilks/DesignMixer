package com.varaprasadps.bvr.no23.a2022.kadiyalubrocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bvr/out/23/a2022/k-broc-rani-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(1, EmptyGenerator.get(100, 320));
        BufferedImage left = HorizontalRepeatGenerator.get(1, EmptyGenerator.get(100, 136));

        int width = left.getWidth();

        final BufferedImage body = HorizontalRepeatGenerator.get(1, PlainGenerator.get(width, 480));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 1));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(ReverseGenerator.get(AchuLayoutGenerator.get(width, 6)));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));

        //left
        inputBIs.add(left);
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //locking
        inputBIs.add(PlainGenerator.get(width, 2));
        //kadiyalu
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
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
