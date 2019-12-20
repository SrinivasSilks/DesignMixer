package com.varaprasadps.no6.longskirt.butta;

import com.varaprasadps.image.AddLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BrocadeLayout {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/out/6/butta/2butta-brocade.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(ImageIO.read(new File("z-data/out/6/butta/conversion/1-brocade-22-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/6/butta/conversion/2-brocade-57-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/6/butta/conversion/3-brocade-22-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/6/butta/conversion/4-brocade-57-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/6/butta/conversion/5-brocade-22-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/6/butta/conversion/6-brocade-57-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/6/butta/conversion/7-brocade-22-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/6/butta/conversion/8-brocade-57-1824.bmp")));

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
