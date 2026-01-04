package com.vasu.loom1.a2026.design1;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.VerticalRepeatGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Bondu {

    public static void main(final String[] args) throws IOException {
        String out = "z-vasu/out/1/2026/design1/A1PSET.bmp";

        BufferedImage anni = VerticalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/out/1/2026/design1/A1ANNI.bmp")));
        BufferedImage pallu = ImageIO.read(new File("z-vasu/out/1/2026/design1/pallu-1824-3240.bmp"));
        BufferedImage kanni = VerticalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/out/1/2026/design1/kanni-1824-480.bmp")));
        BufferedImage kongu = ImageIO.read(new File("z-vasu/out/1/2026/design1/kongu-2-1824.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(anni);
        inputBIs.add(CutLayoutGenerator.get(kanni, 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 20).get(1), 10).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 15).get(1), 70).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 170).get(1), 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 190).get(1), 10).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 100).get(1), 80).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 120).get(1), 80).get(0));

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
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
