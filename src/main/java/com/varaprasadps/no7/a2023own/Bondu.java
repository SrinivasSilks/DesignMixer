package com.varaprasadps.no7.a2023own;

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
        String out = "d/7/out/design1/kongu.bmp";

        BufferedImage blouse = ImageIO.read(new File("d/7/out/design1/blouse-1824-960.bmp"));
        BufferedImage pallu = ImageIO.read(new File("d/7/out/design1/pallu-1824-3260.bmp"));
        BufferedImage brocade = ImageIO.read(new File("d/7/out/design1/1kadiyalu-broc-1824-4320.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(blouse, 200).get(0));
//        inputBIs.add(pallu);
//        inputBIs.add(CutLayoutGenerator.get(blouse, blouse.getHeight() - 140).get(1));
//        inputBIs.add(CutLayoutGenerator.get(blouse, 20).get(0));

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
