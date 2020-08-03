package com.varaprasadps.no6.a2020;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariSplitGenerator {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/jari-anni-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        BufferedImage border = PlainGenerator.get(10, 1200);
        BufferedImage left = EmptyGenerator.get(border.getWidth(), 60);

        int width = border.getWidth();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //right Locking
        inputBIs.add(EmptyGenerator.get(width, 16));

        //left locking
        inputBIs.add(EmptyGenerator.get(width, 20));
        //body
        inputBIs.add(EmptyGenerator.get(width, 480));

        //long border
        inputBIs.add(border);
        inputBIs.add(left);

        inputBIs.add(EmptyGenerator.get(width, 16));

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
