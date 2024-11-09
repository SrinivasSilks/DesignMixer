package com.vasu.loom3.a2024;

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
        String out = "z-vasu/out/3/a2024/design1/pset.bmp";

        BufferedImage kongu = ImageIO.read(new File("z-vasu/out/3/a2024/design1/kongu-2-1824.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-vasu/out/3/a2024/design1/kanni-1824-720.bmp"));
        BufferedImage anni = ImageIO.read(new File("z-vasu/out/3/a2024/design1/anni-360-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-vasu/out/3/a2024/design1/pallu-1824-3224.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-vasu/out/3/a2024/design1/1kadiyalu-anni-1824-720.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(kanni, 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 20).get(1), 10).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 15).get(1), 70).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 170).get(1), 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 190).get(1), 10).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 100).get(1), 80).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, 352).get(1));
        inputBIs.add(CutLayoutGenerator.get(anni, 52).get(0));

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
