package com.varaprasadps.sk.no3.design1.butta1;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Butta {

    public static void main(final String[] args) throws IOException {
        String out = "z-sk/out/3/design1/butta1/5KBUTTA1.bmp";

        BufferedImage kplain = ImageIO.read(new File("z-sk/out/3/design1/5KPLAIN.bmp"));
        BufferedImage gold1 = ImageIO.read(new File("z-sk/out/3/design1/butta1/1-BUTTA.bmp"));
        BufferedImage gold2 = ImageIO.read(new File("z-sk/out/3/design1/butta1/2-BUTTA.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(gold2);
        inputBIs.add(CutLayoutGenerator.get(kplain, 140).get(0));
        inputBIs.add(gold1);
        inputBIs.add(CutLayoutGenerator.get(kplain, 140).get(0));

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
