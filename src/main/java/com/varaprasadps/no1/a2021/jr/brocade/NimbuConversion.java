package com.varaprasadps.no1.a2021.jr.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/a2021/jr/1nimbu-%s-%s.bmp";

        BufferedImage body = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/1/a2021/jr/brocade/gold-jari.bmp")));
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //Achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        //left border
        inputBIs.add(EmptyGenerator.get(width, 384));

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(CutLayoutGenerator.get(body, body.getHeight() - 12).get(1));

        inputBIs.add(body);
        inputBIs.add(body);

        //locking
        inputBIs.add(CutLayoutGenerator.get(body, 12).get(0));
        inputBIs.add(PlainGenerator.get(width, 4));

        //right border
        inputBIs.add(EmptyGenerator.get(width, 1280));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 14));


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
