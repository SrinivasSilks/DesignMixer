package com.varaprasadps.chandra.no1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-chandra/out/1/p-rani-%s-%s.bmp";

        final BufferedImage pallu = CutLayoutGenerator.get(ImageIO.read(new File("z-chandra/in/1/PALLU_RANI.bmp")), 1124).get(0);
        final BufferedImage border = ImageIO.read(new File("z-chandra/in/1/BORDER_360-1980.bmp"));

        int width = pallu.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));


        inputBIs.add(EmptyGenerator.get(width, 2));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        inputBIs.add(pallu);
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));

        inputBIs.add(border);

        //locking
        inputBIs.add(EmptyGenerator.get(width, 4));

        // Box
        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

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
