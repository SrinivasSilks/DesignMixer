package com.varaprasadps.no9.a2022.design1new;

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
        String out = "z-data/out/9/a2022/design1new/pset.bmp";

        BufferedImage anni = ImageIO.read(new File("z-data/out/9/a2022/design1new/blouse-1824-720.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-data/out/9/a2022/design1new/pallu-1824-3224.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-data/out/9/a2022/design1new/kadiyalubroc/kbroc-1824-2160.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
//        inputBIs.add(CutLayoutGenerator.get(anni, 200).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, 720 - 176).get(1));


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
