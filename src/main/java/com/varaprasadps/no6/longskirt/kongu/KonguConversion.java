package com.varaprasadps.no6.longskirt.kongu;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;
import com.varaprasadps.image.KonguLayoutGenerator;
import com.varaprasadps.image.ReverseGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/kongu-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(2, 32));
        // Box
        inputBIs.add(KonguLayoutGenerator.get(1));
        // Achu
        inputBIs.add(EmptyGenerator.get(2, 12));

        // Locking
        inputBIs.add(KonguLayoutGenerator.get(5));
        // All over
        inputBIs.add(KonguLayoutGenerator.get(120));
        // Skirt
        inputBIs.add(EmptyGenerator.get(2, 1200));
        inputBIs.add(EmptyGenerator.get(2, 60));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(2, 2)));
        inputBIs.add(EmptyGenerator.get(2, 2));
        // Achu
        inputBIs.add(EmptyGenerator.get(2, 12));

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
