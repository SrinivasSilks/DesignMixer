package com.varaprasadps.bala.no7.design2.bondu;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BonduRaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/7/a2020/design2/bondu-rani-%s-%s.bmp";

        final BufferedImage bondu = ImageIO.read(new File("z-bala/out/7/a2020/design2/b-rani-20-2048.bmp"));
        final BufferedImage anni = ImageIO.read(new File("z-bala/out/7/a2020/design2/anni-12-2048.bmp"));
        final BufferedImage first = EmptyGenerator.get(2048, 50);
        final BufferedImage second = EmptyGenerator.get(2048, 110);
        final BufferedImage third = EmptyGenerator.get(2048, 20);

        List<BufferedImage> inputBIs = new LinkedList<>();

        for (int i = 0; i < 4; i++) {
            inputBIs.add(anni);
        }
        inputBIs.add(CutLayoutGenerator.get(anni, 2).get(0));
        inputBIs.add(bondu);
        for (int i = 0; i < 9; i++) {
            inputBIs.add(anni);
        }
        inputBIs.add(CutLayoutGenerator.get(anni, 2).get(0));
        inputBIs.add(bondu);
        inputBIs.add(anni);
        inputBIs.add(CutLayoutGenerator.get(anni, 8).get(0));

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
