package com.varaprasadps.giri.no30;

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
        String out = "z-giri/out/30/set.bmp";

        BufferedImage kongu = ImageIO.read(new File("z-giri/out/30/kongu-2-1824.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-giri/out/30/kanni-1824-1800.bmp"));
        BufferedImage anni = ImageIO.read(new File("z-giri/out/30/anni-900-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-giri/out/30/pallu-1824-3600.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-giri/out/30/1brocade-1824-2700.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(kanni, 16).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 16).get(1), 16).get(0));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 16).get(1), 108).get(0));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 248).get(1), 16).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 264).get(1), 16).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 140).get(1), 100).get(0));


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
