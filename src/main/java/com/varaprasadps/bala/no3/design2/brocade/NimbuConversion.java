package com.varaprasadps.bala.no3.design2.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/3/design2/1nimbu-%s-%s.bmp";

        final BufferedImage body = ImageIO.read(new File("z-bala/in/3/design1/jari.bmp"));
        final BufferedImage right = EmptyGenerator.get(body.getWidth(), 560);
        final BufferedImage left = EmptyGenerator.get(body.getWidth(), 224);
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        inputBIs.add(EmptyGenerator.get(width, 4));

        //left
        inputBIs.add(left);
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(right);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        inputBIs.add(EmptyGenerator.get(width, 4));

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
