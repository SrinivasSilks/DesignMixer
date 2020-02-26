package com.varaprasadps.harish.no8.brocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-harish/out/8/1nimbu-%s-%s.bmp";

        final BufferedImage body = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-harish/in/8/border/jari.bmp")));
        int width = body.getWidth();

        final BufferedImage right = EmptyGenerator.get(width, 736);
        final BufferedImage left = EmptyGenerator.get(width, 48);

        List<BufferedImage> inputBIs = new LinkedList<>();

        //kali
        inputBIs.add(EmptyGenerator.get(width, 128));

        //locking
        inputBIs.add(StepLayoutGenerator.get(width, 1));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(left);
        inputBIs.add(body);
        inputBIs.add(right);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //locking
        inputBIs.add(StepLayoutGenerator.get(width, 1));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

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
