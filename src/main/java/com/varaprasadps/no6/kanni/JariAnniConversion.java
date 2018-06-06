package com.varaprasadps.no6.kanni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariAnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/k-jari-%s-%s.bmp";
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(12, 32));

        // Locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(12, 8)));

        // Achu Khali
        inputBIs.add(EmptyGenerator.get(12, 8));

        int number = 109;
        BufferedImage input = EmptyGenerator.get(12, 1308);
        BufferedImage ariel = PlainGenerator.get(input.getWidth(), 4);
        ArielLayoutGenerator.get(input, number, ariel);
        inputBIs.add(ArielLayoutGenerator.get(input, number, ariel));

        inputBIs.add(EmptyGenerator.get(12, 12));

        // Achu Khali
        inputBIs.add(EmptyGenerator.get(12, 4));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(12, 2)));
        inputBIs.add(EmptyGenerator.get(12, 2));

        // locking
        inputBIs.add(PlainGenerator.get(12, 8));

        // Achu Khali
        inputBIs.add(EmptyGenerator.get(12, 4));

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
