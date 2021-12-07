package com.varaprasadps.no11.a2021.jr;

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
        String out = "z-data/out/11/a2021/jr/pset.bmp";

        BufferedImage kongu = ImageIO.read(new File("z-data/out/11/a2021/jr/kongu-2-1824.bmp"));
        BufferedImage kanni = CutLayoutGenerator.get(ImageIO.read(new File("z-data/out/11/a2021/jr/kanni-1824-600.bmp")), 200).get(0);
        BufferedImage anni = ImageIO.read(new File("z-data/out/11/a2021/jr/anni-300-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/11/a2021/jr/pallu-1824-4000.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-data/out/11/a2021/jr/brocade-1824-8100.bmp"));

        BufferedImage kaass = CutLayoutGenerator.get(anni, 100).get(0);

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(kanni, 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 20).get(1), 10).get(0));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kaass, 15).get(1), 70).get(0));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 170).get(1), 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(kanni, 190).get(1));

        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, 100).get(0));

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
