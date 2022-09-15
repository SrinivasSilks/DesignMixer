package com.varaprasadps.no5.a2022.own;

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
        String out = "z-data/out/5/a2022/own/pset.bmp";

        BufferedImage kongu = ImageIO.read(new File("z-data/out/5/a2022/own/kongu-2-1824.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-data/out/5/a2022/own/kanni-1824-720.bmp"));
        BufferedImage anni = ImageIO.read(new File("z-data/out/5/a2022/own/anni-360-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/5/a2022/own/pallu-1824-3600.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-data/out/5/a2022/own/kadiyalu-brocade-1824-7560.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);

        inputBIs.add(CutLayoutGenerator.get(kanni, 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 30).get(0), 20).get(1));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 95).get(0), 15).get(1));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 210).get(0), 190).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 220).get(0), 210).get(1));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 200).get(0), 110).get(1));

        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 200).get(1), 120).get(0));


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
