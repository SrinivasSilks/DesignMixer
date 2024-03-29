package com.varaprasadps.no11.a2021.jr;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/11/a2021/jr/kongu-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(2, 32));

        //box
        inputBIs.add(EmptyGenerator.get(2, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(2, 2)));
        //achu
        inputBIs.add(EmptyGenerator.get(2, 8));
        //locking
        inputBIs.add(KonguLayoutGenerator.get(1));

        //all over
        inputBIs.add(KonguLayoutGenerator.get(240));
        inputBIs.add(EmptyGenerator.get(2, 800));

        //locking
        inputBIs.add(KonguLayoutGenerator.get(1));
        //achu and mispick
        inputBIs.add(EmptyGenerator.get(2, 12));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = LeftLayoutGenerator.get(ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
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
