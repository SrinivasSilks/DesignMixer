package com.varaprasadps.no1.recent.kongu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/recent/kongu-%s-%s.bmp";

        int width = 2;
        List<BufferedImage> inputBIs = new LinkedList<>();

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 4));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //left
        inputBIs.add(EmptyGenerator.get(width, 368));

        //body
        inputBIs.add(KonguLayoutGenerator.get(660 / 4));
        inputBIs.add(KonguLayoutGenerator.get(600 / 4));
        inputBIs.add(KonguLayoutGenerator.get(660 / 4));

        //right
        inputBIs.add(EmptyGenerator.get(width, 368));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 4));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = HorizontalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
