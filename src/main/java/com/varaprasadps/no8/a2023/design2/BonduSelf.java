package com.varaprasadps.no8.a2023.design2;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BonduSelf {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/out/8/a2023/design2/s-pset.bmp";

        BufferedImage anni = ImageIO.read(new File("z-data/out/8/a2023/design2/anni-300-1824.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/8/a2023/design2/s-pallu-1824-3412.bmp"));
//        BufferedImage brocade = ImageIO.read(new File("z-data/out/8/a2022/own/design1/kadiyalu/kadiyalu-broc-1824-1440.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(anni, 40).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, anni.getHeight() - 54).get(1));
        inputBIs.add(CutLayoutGenerator.get(anni, 26).get(0));


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
