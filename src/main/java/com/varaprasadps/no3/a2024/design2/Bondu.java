package com.varaprasadps.no3.a2024.design2;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Bondu {

    public static void main(final String[] args) throws IOException {
        String out = "d/3/out/2024/design2/pset.bmp";

        BufferedImage anni = ImageIO.read(new File("d/3/out/2024/design2/anni-240-1440.bmp"));
        BufferedImage kongu = ImageIO.read(new File("d/3/out/2024/design2/kongu-2-1440.bmp"));
        BufferedImage pallu = ImageIO.read(new File("d/3/out/2024/design2/pallu-1440-3320.bmp"));
        BufferedImage kanni1 = ImageIO.read(new File("d/3/out/2024/design2/konguanni1-1440-480.bmp"));
        BufferedImage kanni2 = ImageIO.read(new File("d/3/out/2024/design2/konguanni2-1440-480.bmp"));
        BufferedImage brocade = ImageIO.read(new File("d/3/out/2024/design2/1kplain-1440-480.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);

        inputBIs.add(CutLayoutGenerator.get(kanni2, 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni2, 20).get(1), 20).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 20).get(1), 70).get(0));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni1, 180).get(1), 10).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni1, 190).get(1), 10).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 100).get(1), 80).get(0));

        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, 40).get(1));

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

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
