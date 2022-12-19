package com.varaprasadps.no1.a2023.kadiyalubrocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/a2023/design1/kadiyalu/1nimbu-%s-%s.bmp";

        BufferedImage body = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/1/a2023/design1/brocade/jari.bmp")));
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //Achu
        inputBIs.add(EmptyGenerator.get(width, 8));


        //left
        inputBIs.add(EmptyGenerator.get(width, 450));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 12));
        inputBIs.add(PlainGenerator.get(width, 4));

        //temple
        inputBIs.add(CutLayoutGenerator.get(body, 464).get(1));
        //body
        inputBIs.add(body);
        inputBIs.add(body);
        //skirt
        inputBIs.add(body);
        inputBIs.add(CutLayoutGenerator.get(body, 140).get(0));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(EmptyGenerator.get(width, 12));
        //right
        inputBIs.add(EmptyGenerator.get(width, 450));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));


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
