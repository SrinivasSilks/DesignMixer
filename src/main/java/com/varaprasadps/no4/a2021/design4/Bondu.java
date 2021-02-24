package com.varaprasadps.no4.a2021.design4;

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
        String out = "z-data/out/4/a2021/design4/pset.bmp";

        BufferedImage kongu = ImageIO.read(new File("z-data/out/4/a2021/design4/kongu-2-1440.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-data/out/4/a2021/design4/kanni-1440-720.bmp"));
        BufferedImage anni = ImageIO.read(new File("z-data/out/4/a2021/design4/anni-360-1440.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/4/a2021/design4/pallu-1440-4000.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-data/out/4/a2021/design4/1brocade-1440-1080.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 500).get(0), 480).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 510).get(0), 500).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 345).get(0), 255).get(1));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 710).get(0), 690).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(kanni, 710).get(1));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 200).get(1), 140).get(0));

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
