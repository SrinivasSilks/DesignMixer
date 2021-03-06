package com.varaprasadps.bala.no6.bondu;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BonduJariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/6/bondu-jari-%s-%s.bmp";

        final BufferedImage bondu = ImageIO.read(new File("z-bala/out/6/design1/b-jari-20-1792.bmp"));
        final BufferedImage first = EmptyGenerator.get(1792, 50);
        final BufferedImage second = EmptyGenerator.get(1792, 110);
        final BufferedImage third = EmptyGenerator.get(1792, 20);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(first);
        inputBIs.add(bondu);
        inputBIs.add(second);
        inputBIs.add(bondu);
        inputBIs.add(third);

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
