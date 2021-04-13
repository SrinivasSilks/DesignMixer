package com.varaprasadps.no2.a2021.design3;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Bondu {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/out/2/a2021/design3/pset.bmp";

        BufferedImage anni = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/2/a2021/design3/anni-400-2688.bmp")));
        BufferedImage kongu = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/2/a2021/design1/kongu-2-2688.bmp")));
        BufferedImage kanni = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/2/a2021/design3/kanni-2688-800.bmp")));
        BufferedImage pallu = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/2/a2021/design3/pallu-2688-3840.bmp")));
        BufferedImage brocade = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/2/a2021/design3/1brocade-2688-3600.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 580).get(0), 560).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 590).get(0), 580).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 385).get(0), 295).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 790).get(0), 770).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(kanni, 790).get(1));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, 320).get(1));
        inputBIs.add(CutLayoutGenerator.get(anni, 100).get(0));
//        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 200).get(1), 120).get(0));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = VerticalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
