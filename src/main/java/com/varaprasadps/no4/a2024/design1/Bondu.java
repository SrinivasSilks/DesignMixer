package com.varaprasadps.no4.a2024.design1;

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
        String out = "d/4/out/a2024/design1/pset.bmp";

        BufferedImage blouse = ImageIO.read(new File("d/4/out/a2024/design1/blouse-1440-480.bmp"));
        BufferedImage pallu = ImageIO.read(new File("d/4/out/a2024/design1/pallu-1440-3200.bmp"));
        BufferedImage brocade = ImageIO.read(new File("d/4/out/a2024/design1/1kadiyalu-brocade-1440-2160.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
//        inputBIs.add(CutLayoutGenerator.get(blouse, 120).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(blouse, blouse.getHeight() - 40).get(1));
        inputBIs.add(CutLayoutGenerator.get(blouse, 80).get(0));
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
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
