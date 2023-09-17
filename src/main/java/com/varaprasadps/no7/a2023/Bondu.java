package com.varaprasadps.no7.a2023;

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
        String out = "z-data/out/7/a2023/design1/pset.bmp";

        BufferedImage blouse = ImageIO.read(new File("z-data/out/7/a2023/design1/blouse-1824-1440.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/7/a2023/design1/pallu-1824-3216.bmp"));
//        BufferedImage brocade = ImageIO.read(new File("z-data/out/7/jr/design1/4brocade-1824-6120.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
//        inputBIs.add(CutLayoutGenerator.get(blouse, 200).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get((CutLayoutGenerator.get(blouse, blouse.getHeight() - (92*2)).get(1)), 160).get(0));


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
