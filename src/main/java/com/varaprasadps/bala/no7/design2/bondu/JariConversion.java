package com.varaprasadps.bala.no7.design2.bondu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/7/a2020/design2/b-jari-%s-%s.bmp";

        final BufferedImage body = ImageIO.read(new File("z-bala/in/7/a2020/design2/bondu/bondu.bmp"));
        final BufferedImage border = EmptyGenerator.get(body.getWidth(), 500);

        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));
        inputBIs.add(EmptyGenerator.get(width, 20));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));

        //left
        inputBIs.add(VerticalFlipGenerator.get(border));
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(border);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));
        inputBIs.add(EmptyGenerator.get(width, 4));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 8)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));

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
