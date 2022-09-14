package com.varaprasadps.no13.a2022.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BlouseConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/13/a2022/design1/1BLOUSE.bmp";

        int width = 10;
        List<BufferedImage> inputBIs = new LinkedList<>();
        //kali
        inputBIs.add(EmptyGenerator.get(width, 16));

        //left border
        inputBIs.add(EmptyGenerator.get(width, 320));

        inputBIs.add(EmptyGenerator.get(width, 5));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 7)));
        inputBIs.add(EmptyGenerator.get(width, 3));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 7)));
        inputBIs.add(EmptyGenerator.get(width, 3));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 7)));

        inputBIs.add(EmptyGenerator.get(width, 5));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 7)));
        inputBIs.add(EmptyGenerator.get(width, 3));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 7)));
        inputBIs.add(EmptyGenerator.get(width, 3));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 7)));

        inputBIs.add(EmptyGenerator.get(width, 5));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 7)));
        inputBIs.add(EmptyGenerator.get(width, 3));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 7)));
        inputBIs.add(EmptyGenerator.get(width, 3));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 7)));

        //body
        inputBIs.add(PlainGenerator.get(width, 480));
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //border
        inputBIs.add(EmptyGenerator.get(width, 592));

        //kali
        inputBIs.add(EmptyGenerator.get(width, 16));

        inputBIs.add(EmptyGenerator.get(width, 1));
        inputBIs.add(EmptyGenerator.get(width, 7));

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
