package com.varaprasadps.bala.no6.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {
        String out = "z-bala/out/6/design1/1jari-%s-%s.bmp";

        final BufferedImage body = PlainGenerator.get(160, 960);

        final BufferedImage right = EmptyGenerator.get(body.getWidth(), 500);
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 32));

        //body
        inputBIs.add(body);
        //right
        inputBIs.add(right);

        //box
        inputBIs.add(EmptyGenerator.get(width, 6));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 6)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));




        inputBIs.add(EmptyGenerator.get(width, 128));

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
