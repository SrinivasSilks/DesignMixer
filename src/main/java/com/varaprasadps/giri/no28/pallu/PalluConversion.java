package com.varaprasadps.giri.no28.pallu;

import com.varaprasadps.image.AddLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PalluConversion {

    public static void main(String[] args) throws IOException {
        String out = "z-giri/out/28/pallu-%s-%s.bmp";

        final BufferedImage buggadiCentre = ImageIO.read(new File("z-giri/in/28/PROCESSED/pallu-b-centre-720-1824.bmp"));
        final BufferedImage bugguadiDown = ImageIO.read(new File("z-giri/in/28/PROCESSED/pallu-buggadi-left-240-1824.bmp"));
        final BufferedImage bugguadiUp = ImageIO.read(new File("z-giri/in/28/PROCESSED/pallu-buggadi-right-240-1824.bmp"));
        final BufferedImage centre = ImageIO.read(new File("z-giri/in/28/PROCESSED/pallu-centre-240-1824.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(bugguadiUp);
        inputBIs.add(buggadiCentre);
        inputBIs.add(bugguadiDown);

        inputBIs.add(centre);
        inputBIs.add(centre);
        inputBIs.add(centre);
        inputBIs.add(centre);
        inputBIs.add(centre);
        inputBIs.add(centre);

        inputBIs.add(bugguadiUp);
        inputBIs.add(buggadiCentre);
        inputBIs.add(bugguadiDown);

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

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
