package com.varaprasadps.dinesh;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;
import com.varaprasadps.image.PlainGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {
        String out = "z-dinesh/jari-%s-%s.bmp";

        final BufferedImage body = EmptyGenerator.get(100, 960);
        final BufferedImage border = PlainGenerator.get(100, 670);

        List<BufferedImage> inputBIs = new LinkedList<>();

        //Achu
        inputBIs.add(EmptyGenerator.get(body.getWidth(), 16));

        // Box
        inputBIs.add(EmptyGenerator.get(body.getWidth(), 5));
        inputBIs.add(EmptyGenerator.get(body.getWidth(), 5));

        inputBIs.add(EmptyGenerator.get(body.getWidth(), 120));

        inputBIs.add(body);
        inputBIs.add(border);

        //Achu
        inputBIs.add(EmptyGenerator.get(body.getWidth(), 16));


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
