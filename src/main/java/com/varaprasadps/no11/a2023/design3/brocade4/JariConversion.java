package com.varaprasadps.no11.a2023.design3.brocade4;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/11/out/design3/4jari-%s-%s.bmp";

        BufferedImage brocade = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/11/in/design3/brocade4/green.bmp")));

        int width = brocade.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 4));

        //left
        inputBIs.add(EmptyGenerator.get(width, 190));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(CutLayoutGenerator.get(brocade, brocade.getHeight() - 4).get(1));

        //body
        inputBIs.add(brocade);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 4));
        //right
        inputBIs.add(EmptyGenerator.get(width, 590));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 4));
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
