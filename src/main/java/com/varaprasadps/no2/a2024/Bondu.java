package com.varaprasadps.no2.a2024;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Bondu {

    public static void main(final String[] args) throws IOException {
        String out = "d/2/out/design1/pset.bmp";

        BufferedImage anni = VerticalFlipGenerator.get(ImageIO.read(new File("d/2/out/design1/anni-450-2688.bmp")));
        BufferedImage kongu = VerticalFlipGenerator.get(ImageIO.read(new File("d/2/out/design1/kongu-2-2688.bmp")));
        BufferedImage kanni = VerticalFlipGenerator.get(ImageIO.read(new File("d/2/out/design1/kanni-2688-900.bmp")));
        BufferedImage pallu = VerticalFlipGenerator.get(ImageIO.read(new File("d/2/out/design1/pallu-2688-3400.bmp")));
        BufferedImage brocade = VerticalFlipGenerator.get(ImageIO.read(new File("d/2/out/design1/1kadiyalu-brocade-2688-10800.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();
//
//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(kanni, 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 30).get(0), 20).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 85).get(0), 15).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 190).get(0), 170).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 200).get(0), 190).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 160).get(0), 60).get(1));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, anni.getHeight() - 390).get(1), 80).get(0));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = VerticalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
