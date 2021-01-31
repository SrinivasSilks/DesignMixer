package com.varaprasadps.no5.a2021.design4;

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
        String out = "z-data/out/5/a2021/design4/pset.bmp";

        BufferedImage anni = ImageIO.read(new File("z-data/out/5/a2021/design4/anni-240-1824.bmp"));
        BufferedImage kongu = ImageIO.read(new File("z-data/out/5/a2021/design1/kongu-2-1824.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-data/out/5/a2021/design4/kanni-1824-480.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/5/a2021/design4/pallu-1824-3960.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-data/out/5/a2021/design4/1brocade-1824-16560.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(kanni, 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 10).get(1), 110).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 240).get(1), 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(anni, 130).get(1));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, 180).get(1));
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
