package com.varaprasadps.no6.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/nimbu-%s-%s.bmp";
        final BufferedImage in = ImageIO.read(new File("z-data/in/6/new2/nimbu2.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(in.getWidth(), 32));

        // Locking
        inputBIs.add(PlainGenerator.get(in.getWidth(), 8));

        //Achu Khali
        inputBIs.add(EmptyGenerator.get(in.getWidth(), 8));

        int number = 109;
        BufferedImage input = ReverseGenerator.get(in);
        BufferedImage ariel = StepLayoutGenerator.get(input.getWidth());
        inputBIs.add(ArielLayoutGenerator.get(input, number, ariel));

        inputBIs.add(EmptyGenerator.get(in.getWidth(), 12));
        // Achu Khali
        inputBIs.add(EmptyGenerator.get(in.getWidth(), 4));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(in.getWidth(), 2)));
        inputBIs.add(EmptyGenerator.get(in.getWidth(), 2));

        // locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(in.getWidth(), 8)));

        // Box Khali
        inputBIs.add(EmptyGenerator.get(in.getWidth(), 4));

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
