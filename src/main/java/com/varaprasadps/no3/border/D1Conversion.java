package com.varaprasadps.no3.border;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class D1Conversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/3/new/D1-%s-%s.bmp";
        final BufferedImage borderOne = CutLayoutGenerator.get(ImageIO.read(new File("z-data/in/3/B_JARI.bmp")), 400).get(0);
        final BufferedImage borderTwo = HorizontalRepeatGenerator.get(50, ImageIO.read(new File("z-data/in/3/BORDER.bmp")));
        final int width = borderTwo.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        inputBIs.add(EmptyGenerator.get(width, 16));

        // border 1
        inputBIs.add(borderOne);

        // Body
        inputBIs.add(EmptyGenerator.get(width, 800));

        // border 2
        inputBIs.add(borderTwo);
        inputBIs.add(PlainGenerator.get(width, 4));


        inputBIs.add(EmptyGenerator.get(width, 12));

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
