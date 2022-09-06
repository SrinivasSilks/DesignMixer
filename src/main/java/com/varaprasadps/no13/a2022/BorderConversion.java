package com.varaprasadps.no13.a2022;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/13/AlloverBorder.bmp";


        int width = 10;

        List<BufferedImage> inputBIs = new LinkedList<>();

        //kali
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 16)));
        //border
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 180)));
        //sununda
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 80)));
        //bugada
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 60)));

        inputBIs.add(EmptyGenerator.get(width, 32));
        inputBIs.add(EmptyGenerator.get(width, 32));
        inputBIs.add(EmptyGenerator.get(width, 32));

        //locking
        inputBIs.add(EmptyGenerator.get(width, 480));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 16));
        //border
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 592)));
        //kali
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 16)));

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
