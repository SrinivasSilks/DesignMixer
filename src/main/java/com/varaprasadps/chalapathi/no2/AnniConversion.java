package com.varaprasadps.chalapathi.no2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-chala/out/2/anni-%s-%s.bmp";

        final BufferedImage border = EmptyGenerator.get(16, 248);
        final BufferedImage body = PlainGenerator.get(border.getWidth(), 1080);
        final BufferedImage locking = PlainGenerator.get(border.getWidth(), 16);

        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 8));

        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));
        //locking
        inputBIs.add(locking);
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(border);
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        //kali
        inputBIs.add(EmptyGenerator.get(width, 68));
        //box
        inputBIs.add(EmptyGenerator.get(width, 4));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = VerticalFlipGenerator.get(LeftLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
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
