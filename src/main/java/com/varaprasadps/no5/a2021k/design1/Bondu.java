package com.varaprasadps.no5.a2021k.design1;

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
        String out = "z-data/out/5/a2021k/design1/set.bmp";

        BufferedImage kongu = ImageIO.read(new File("z-data/out/5/a2021k/design1/kongu-2-1824.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-data/out/5/a2021k/design1/kanni-1824-720.bmp"));
        BufferedImage anni = ImageIO.read(new File("z-data/out/5/a2021k/design1/anni-360-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/5/a2021k/design1/pallu-1824-3960.bmp"));
//        BufferedImage brocade = ImageIO.read(new File("z-data/out/5/a2021k/design1/1brocade-1824-5400.bmp"));

        BufferedImage annii = CutLayoutGenerator.get(anni, 180).get(0);
        BufferedImage kannii = CutLayoutGenerator.get(kanni, 360).get(0);

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(kannii, 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(annii, 90).get(0), 10).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kannii, 180).get(1), 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(annii, 100).get(1));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 180).get(1), 100).get(0));


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
