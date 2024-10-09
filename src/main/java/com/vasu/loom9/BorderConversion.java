package com.vasu.loom9;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/in/9/out/1BORDER.bmp";

        final BufferedImage left = StepLayoutGenerator.get(60, 310 / 5, 5);
        final BufferedImage right = StepLayoutGenerator.get(60, 520 / 5, 5);

        int width = right.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 24));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));

        //border
        inputBIs.add(VerticalFlipGenerator.get(left));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 8));
        //body
        inputBIs.add(EmptyGenerator.get(width, 480));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 8));
        //border
        inputBIs.add(right);

        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 24));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
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
        BufferedImage bi = VerticalFlipGenerator.get(LeftLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
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
