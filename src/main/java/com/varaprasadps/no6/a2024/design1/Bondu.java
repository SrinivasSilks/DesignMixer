package com.varaprasadps.no6.a2024.design1;

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
        String out = "d/6/out/design1/pset.bmp";

        BufferedImage kongu = ImageIO.read(new File("d/6/out/design1/kongu-2-1824.bmp"));
        BufferedImage kanni = ImageIO.read(new File("d/6/out/design1/kongu-anni-1824-480.bmp"));
        BufferedImage anni = ImageIO.read(new File("d/6/out/design1/anni-240-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("d/6/out/design1/pallu-1824-3240.bmp"));
        BufferedImage brocade = ImageIO.read(new File("d/6/out/design1/kadiyalu-plain-1824-480.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(kanni, 10).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 10).get(1), 10).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 10).get(1), 80).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 180).get(1), 10).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 190).get(1), 10).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 100).get(1), 80).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 120).get(1), 60).get(0));


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
