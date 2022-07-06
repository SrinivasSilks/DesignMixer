package com.varaprasadps.no7.jr.brocade3;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/7/jr/design1/4rani-%s-%s.bmp";

        BufferedImage left = HorizontalRepeatGenerator.get(17, ImageIO.read(new File("z-data/in/7/jr/design1/border1/left-border.bmp")));
        BufferedImage right = HorizontalRepeatGenerator.get(17, ImageIO.read(new File("z-data/in/7/jr/design1/border1/right-border.bmp")));
        BufferedImage body = HorizontalRepeatGenerator.get(6, EmptyGenerator.get(340, 960));

        List<BufferedImage> inputBIs = new LinkedList<>();

        int width = body.getWidth();
        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 12));
        inputBIs.add(body);
        //locking
        inputBIs.add(PlainGenerator.get(width, 12));
        inputBIs.add(right);

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

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
