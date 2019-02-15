package com.varaprasadps.no12.kanni;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;
import com.varaprasadps.image.PlainGenerator;
import com.varaprasadps.image.ReverseGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/12/k-jari-%s-%s.bmp";

        int width = 600;
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //Khali
        inputBIs.add(EmptyGenerator.get(width, 4));

        //Achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        // Locking
        inputBIs.add(PlainGenerator.get(width, 4));

        inputBIs.add(PlainGenerator.get(width, 480));
        inputBIs.add(PlainGenerator.get(width, 1000));

        // Jari
        inputBIs.add(EmptyGenerator.get(width, 280));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));

        // Khali
        inputBIs.add(EmptyGenerator.get(width, 4));

        // Achu
        inputBIs.add(EmptyGenerator.get(width, 8));

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
