package com.varaprasadps.bala.b1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Plain {


    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/b1/plain-%s-%s.bmp";

        int width = 12;

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));

        //left
        inputBIs.add(EmptyGenerator.get(width, 100));
        //body
        inputBIs.add(PlainGenerator.get(width, 720));
        //right
        inputBIs.add(EmptyGenerator.get(width, 680));

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));
        inputBIs.add(EmptyGenerator.get(width, 128));


        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = VerticalFlipGenerator.get(LeftLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
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
