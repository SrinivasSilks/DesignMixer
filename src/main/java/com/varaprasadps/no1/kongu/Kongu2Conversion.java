package com.varaprasadps.no1.kongu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Kongu2Conversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/2kongu-%s-%s.bmp";

        int width = 2;
        List<BufferedImage> inputBIs = new LinkedList<>();

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        // Khali
        inputBIs.add(EmptyGenerator.get(width, 4));
        //Achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        // Locking
        // All over
        inputBIs.add(KonguLayoutGenerator.get(125));

        // Skirt
        inputBIs.add(KonguLayoutGenerator.get(300));

        inputBIs.add(EmptyGenerator.get(width, 60));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
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
        BufferedImage bi = HorizontalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
