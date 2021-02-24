package com.varaprasadps.bala.b2.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {
        String out = "z-bala/out/b2/1rani-%s-%s.bmp";


        final BufferedImage body = EmptyGenerator.get(240, 480);

        final BufferedImage bugada = EmptyGenerator.get(body.getWidth(), 34);
        final BufferedImage namali = EmptyGenerator.get(body.getWidth(), 900);
        final BufferedImage sunanda = EmptyGenerator.get(body.getWidth(), 30);
        final BufferedImage rudaraska = EmptyGenerator.get(body.getWidth(), 60);

        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));

        //body
        inputBIs.add(body);
        //border
        inputBIs.add(bugada);
        inputBIs.add(namali);
        inputBIs.add(sunanda);
        inputBIs.add(rudaraska);
        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));

        inputBIs.add(PlainGenerator.get(width, 128));


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
