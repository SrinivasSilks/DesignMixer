package com.varaprasadps.no8.a2023.design3;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BonduK {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/out/8/a2023/design3/b-pset.bmp";

        BufferedImage anni = ImageIO.read(new File("z-data/out/8/a2023/design3/anni-340-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/8/a2023/design3/b-pallu-1824-3412.bmp"));
        BufferedImage kongu = ImageIO.read(new File("z-data/out/8/a2023/design3/kongu-2-1824.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-data/out/8/a2023/design3/kanni-1824-680.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-data/out/8/a2023/design3/1kadiyalu-broc-1824-10200.bmp"));


        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(kanni, 10).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 10).get(1), 10).get(0));

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 10).get(1), 80).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 180).get(1), 10).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 190).get(1), 10).get(0));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 100).get(1), 80).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, anni.getHeight() - 154).get(1), 80).get(0));


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
