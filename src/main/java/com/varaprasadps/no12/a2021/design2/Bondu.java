package com.varaprasadps.no12.a2021.design2;

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
        String out = "z-data/out/12/a2021/design2/pset.bmp";

        BufferedImage kongu = ImageIO.read(new File("z-data/out/12/a2021/design1/kongu-2-1824.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-data/out/12/a2021/design2/kanni-1824-960.bmp"));
        BufferedImage anni = ImageIO.read(new File("z-data/out/12/a2021/design2/anni-480-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/12/a2021/design2/pallu-1824-4000.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-data/out/12/a2021/design2/brocade-1824-1440.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 740).get(0), 720).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 750).get(0), 740).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 465).get(0), 375).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 950).get(0), 930).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(kanni, 950).get(1));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 80).get(1), 100).get(0));

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
