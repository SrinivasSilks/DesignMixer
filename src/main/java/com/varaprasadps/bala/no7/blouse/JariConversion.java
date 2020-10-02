package com.varaprasadps.bala.no7.blouse;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/7/a2020/b-jari-%s-%s.bmp";

        final BufferedImage body = HorizontalRepeatGenerator.get(12, ImageIO.read(new File("z-bala/in/7/a2020/blouse/blouse-jari.bmp")));
        final BufferedImage border = EmptyGenerator.get(body.getWidth(), 500);

        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));

        inputBIs.add(EmptyGenerator.get(width, 20));

        //left
        inputBIs.add(VerticalFlipGenerator.get(border));
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(border);

        inputBIs.add(EmptyGenerator.get(width, 20));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

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
