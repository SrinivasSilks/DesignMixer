package com.varaprasadps.no5.variety;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BrocadeAddLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/out/5/variety/BROCADE.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(ImageIO.read(new File("z-data/out/5/variety/final/broc1-174-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/5/variety/final/broc2-1275-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/5/variety/final/broc3-350-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/5/variety/final/broc4-1275-1824.bmp")));
        inputBIs.add(ImageIO.read(new File("z-data/out/5/variety/final/broc5-176-1824.bmp")));

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
