package com.varaprasadps.no8.a2020.design2.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/8/a2020/8design2/2jari-%s-%s.bmp";

        final BufferedImage brocade = HorizontalRepeatGenerator.get(11, ImageIO.read(new File("z-data/in/8/a2020/8design2/brocade2/nimbu.bmp")));

        int width = brocade.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //brocade
        inputBIs.add(brocade);
        inputBIs.add(brocade);
        //mango
        inputBIs.add(CutLayoutGenerator.get(brocade, 62).get(0));
        //locking
        inputBIs.add(PlainGenerator.get(width, 18));
        inputBIs.add(EmptyGenerator.get(width, 16));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 4));
        //chucks
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 60)));
        //border
        inputBIs.add(EmptyGenerator.get(width, 1120));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 1).get(0)));
        //chakram
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));


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
