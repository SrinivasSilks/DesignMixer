package com.varaprasadps.no6.kongu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/kongu-%s-%s.bmp";

        int x = 2;
        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(x, 32));

        // Locking
        inputBIs.add(KonguLayoutGenerator.get(2));
        // Achu
        inputBIs.add(EmptyGenerator.get(x, 8));

        int number = 109;
        BufferedImage input = EmptyGenerator.get(x, 1308);
        BufferedImage ariel = KonguLayoutGenerator.get();
        inputBIs.add(ArielLayoutGenerator.get(input, number, ariel));

        inputBIs.add(EmptyGenerator.get(x, 12));

        // Achu
        inputBIs.add(EmptyGenerator.get(x, 4));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(x, 2)));
        inputBIs.add(EmptyGenerator.get(x, 2));

        // locking
        inputBIs.add(KonguLayoutGenerator.get(2));

        // Achu
        inputBIs.add(EmptyGenerator.get(x, 4));

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
