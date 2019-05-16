package com.varaprasadps.chandra.no3.anni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-chandra/out/3/anni-%s-%s.bmp";

        final BufferedImage border = ImageIO.read(new File("z-chandra/in/3/BORDER.bmp"));
        final BufferedImage temple = ImageIO.read(new File("z-chandra/in/3/TEMPLE.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        int width = border.getWidth();

        inputBIs.add(EmptyGenerator.get(width, 128));

        // Miss etu
        inputBIs.add(AchuLayoutGenerator.get(width, 2));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 14));

        inputBIs.add(temple);
        // body
        inputBIs.add(PlainGenerator.get(width, 600));
        // locking
        inputBIs.add(PlainGenerator.get(width, 6));

        inputBIs.add(border);

        // Box
        inputBIs.add(EmptyGenerator.get(width, 4));

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
