package com.varaprasadps.chandra.no3.brocade_1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-chandra/out/3/rani-%s-%s.bmp";

        final BufferedImage brocade = HorizontalRepeatGenerator.get(15, ImageIO.read(new File("z-chandra/in/3/brocade/RANI.bmp")));

        final BufferedImage border = HorizontalRepeatGenerator.get(16, ImageIO.read(new File("z-chandra/in/3/BORDER.bmp")));
        final BufferedImage temple = HorizontalRepeatGenerator.get(16, ImageIO.read(new File("z-chandra/in/3/TEMPLE.bmp")));

        int width = brocade.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(width, 128));

        // Miss etu
        inputBIs.add(AchuLayoutGenerator.get(width, 2));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 14));

        inputBIs.add(temple);
        inputBIs.add(brocade);
        // locking
        inputBIs.add(PlainGenerator.get(width, 6));

        inputBIs.add(border);

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        // Miss etu
        inputBIs.add(ReverseGenerator.get(AchuLayoutGenerator.get(width, 2)));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        inputBIs.add(EmptyGenerator.get(width, 256));

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
