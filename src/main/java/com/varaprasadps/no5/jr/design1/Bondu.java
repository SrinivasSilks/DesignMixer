package com.varaprasadps.no5.jr.design1;

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
        String out = "z-data/out/5/jr/design1/pset.bmp";

        BufferedImage kongu = ImageIO.read(new File("z-data/out/5/jr/design1/kongu-2-1824.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-data/out/5/jr/design1/kanni-1824-800.bmp"));
        BufferedImage anni = ImageIO.read(new File("z-data/out/5/jr/design1/anni-400-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/5/jr/design1/pallu-1824-4000.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-data/out/5/jr/design1/1brocade-1824-3600.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 480).get(1), 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 510).get(1), 10).get(0));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 240).get(1), 50).get(0));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 610).get(1), 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 630).get(1), 10).get(0));

        inputBIs.add(CutLayoutGenerator.get(anni, 320).get(1));


        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, 160).get(0));


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
