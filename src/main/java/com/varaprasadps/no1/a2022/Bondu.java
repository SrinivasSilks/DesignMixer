package com.varaprasadps.no1.a2022;

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
        String out = "z-data/out/1/a2022/pset.bmp";

        BufferedImage anni = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/1/a2022/1anni-480-2688.bmp")));
        BufferedImage pallu = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/1/a2022/pallu-2688-4000.bmp")));
        BufferedImage brocade = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/1/a2022/1brocade-2688-1440.bmp")));
        BufferedImage kanni = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/1/a2022/kanni-2688-960.bmp")));
        BufferedImage kongu = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/1/a2022/1kongu-2-2688.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);

        inputBIs.add(CutLayoutGenerator.get(kanni, 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 20).get(1), 10).get(0));


        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 15).get(1), 90).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 210).get(1), 20).get(0));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 230).get(1), 10).get(0));

        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 200).get(1), 80).get(0));


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
