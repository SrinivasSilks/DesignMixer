package com.varaprasadps.vasu.no2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/2/a2021/border-%s-%s.bmp";

        final BufferedImage border = ImageIO.read(new File("z-vasu/in/2/BORDER.bmp"));
        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        // Board
        inputBIs.add(EmptyGenerator.get(width, 128));

        // Khali
        inputBIs.add(EmptyGenerator.get(width, 16));

        //Achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        inputBIs.add(EmptyGenerator.get(width, 400));
        inputBIs.add(border);

        // Locking
        inputBIs.add(EmptyGenerator.get(width, 16));

        // Khali
        inputBIs.add(EmptyGenerator.get(width, 16));

        // Board Khali
        inputBIs.add(EmptyGenerator.get(width, 256));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = LeftLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
