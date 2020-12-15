package com.varaprasadps.bala.no3.design1.bondu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/6/design1/b-rani-%s-%s.bmp";

        final BufferedImage body = PlainGenerator.get(20, 960);
        final BufferedImage right = EmptyGenerator.get(body.getWidth(), 496);
        final BufferedImage left = EmptyGenerator.get(body.getWidth(), 496);
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));
        inputBIs.add(AchuLayoutGenerator.get(width, 20));
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

        //left
        inputBIs.add(left);
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(right);
        inputBIs.add(AchuLayoutGenerator.get(width, 20));
        inputBIs.add(AchuLayoutGenerator.get(width, 2));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 10)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

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
