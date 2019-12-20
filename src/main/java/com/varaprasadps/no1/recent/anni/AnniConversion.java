package com.varaprasadps.no1.recent.anni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/recent/1anni-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(60, 2)));
        inputBIs.add(EmptyGenerator.get(60, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(60, 4), 2).get(0));
        //khali
        inputBIs.add(EmptyGenerator.get(60, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(60, 8));

        //left
        inputBIs.add(PlainGenerator.get(60, 368));
        //skirt
        inputBIs.add(PlainGenerator.get(60, 660));
        //body
        inputBIs.add(PlainGenerator.get(60, 600));
        //skirt
        inputBIs.add(PlainGenerator.get(60, 660));
        //left
        inputBIs.add(PlainGenerator.get(60, 368));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(60, 2)));
        inputBIs.add(EmptyGenerator.get(60, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(60, 4), 2).get(0)));
        //khali
        inputBIs.add(EmptyGenerator.get(60, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(60, 8));

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
