package com.varaprasadps.no8.a2020oct.kongu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/8/a2020oct/design1/kongu-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(2, 32));

        //achu
        inputBIs.add(EmptyGenerator.get(2, 12));
        //locking
        inputBIs.add(KonguLayoutGenerator.get(1));

        //all over
        inputBIs.add(KonguLayoutGenerator.get(120));
        //mango
        inputBIs.add(KonguLayoutGenerator.get(24));
        //locking
        inputBIs.add(KonguLayoutGenerator.get(1));
        //chucks
        inputBIs.add(EmptyGenerator.get(2, 60));
        //border
        inputBIs.add(EmptyGenerator.get(2, 1120));

        //box
        inputBIs.add(EmptyGenerator.get(2, 4));
        //achu
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
