package com.seetharama.no13.a2022.test;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderDownConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/13/a2022/design1/1BRDOWN.bmp";

        int width = 10;

        List<BufferedImage> inputBIs = new LinkedList<>();

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 12)));

        //border
        inputBIs.add(EmptyGenerator.get(width, 180));
        //sununda
        inputBIs.add(EmptyGenerator.get(width, 80));
        //bugada
        inputBIs.add(EmptyGenerator.get(width, 60));

        inputBIs.add(EmptyGenerator.get(width, 32));
        inputBIs.add(EmptyGenerator.get(width, 32));
        inputBIs.add(EmptyGenerator.get(width, 32));

        //locking
        inputBIs.add(EmptyGenerator.get(width, 480));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 16));

        //border
        inputBIs.add(EmptyGenerator.get(width, 592));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 10)));

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
