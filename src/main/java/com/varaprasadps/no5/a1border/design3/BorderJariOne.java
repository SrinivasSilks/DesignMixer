package com.varaprasadps.no5.a1border.design3;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderJariOne {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/5/design3/pallu/p-border-jari.bmp";

        BufferedImage border = ImageIO.read(new File("z-data/in/5/design2/pallu/p-border-jari.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        List<BufferedImage> images = CutLayoutGenerator.get(border, 305);
        inputBIs.add(images.get(0));
        List<BufferedImage> images2 = CutLayoutGenerator.get(images.get(1), 240);
        inputBIs.add(CutLayoutGenerator.get(images2.get(1), 375).get(0));

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
