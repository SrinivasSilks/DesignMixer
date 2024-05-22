package com.seetharama.no13.a2022.design1;

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
        String out = "z-data/out/13/a2022/design1/1BONDU.bmp";

        BufferedImage kongu = ImageIO.read(new File("z-data/out/13/a2022/design1/1KONGU.bmp"));
        BufferedImage kanni = ImageIO.read(new File("z-data/out/13/a2022/design1/1KANNI.bmp"));
        BufferedImage anni = ImageIO.read(new File("z-data/out/13/a2022/design1/1BLOUSE.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(CutLayoutGenerator.get(kanni, 10).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(kanni, 10).get(1));
        for (int i = 0; i < 7; i++) {
            inputBIs.add(anni);
        }
        inputBIs.add(CutLayoutGenerator.get(kanni, 10).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(kanni, 10).get(1));
        for (int i = 0; i < 8; i++) {
            inputBIs.add(anni);
        }

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
