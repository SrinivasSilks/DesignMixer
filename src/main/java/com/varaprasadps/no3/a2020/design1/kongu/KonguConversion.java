package com.varaprasadps.no3.a2020.design1.kongu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/3/a2020/design1/kongu-%s-%s.bmp";

        int width = 2;

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //border
        inputBIs.add(EmptyGenerator.get(width, 368));
        // locking
        inputBIs.add(KonguLayoutGenerator.get(1));
        //chucks
        inputBIs.add(EmptyGenerator.get(width, 12));
        // locking
        inputBIs.add(KonguLayoutGenerator.get(2));

        //allover
        inputBIs.add(KonguLayoutGenerator.get(150));

        //chucks
        inputBIs.add(EmptyGenerator.get(width, 12));
        // locking
        inputBIs.add(KonguLayoutGenerator.get(1));
        //border
        inputBIs.add(EmptyGenerator.get(width, 368));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));


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
