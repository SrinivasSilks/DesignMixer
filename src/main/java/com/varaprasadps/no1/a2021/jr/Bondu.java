package com.varaprasadps.no1.a2021.jr;

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
        String out = "z-data/out/1/a2021/jr/pset.bmp";

        BufferedImage anni = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/1/a2021/jr/1anni-60-2688.bmp")));
        BufferedImage pallu = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/1/a2021/jr/pallu-2688-3840.bmp")));
        BufferedImage brocade = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/out/1/a2021/jr/1brocade-2688-1260.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(anni);
        inputBIs.add(pallu);
        inputBIs.add(anni);
        inputBIs.add(anni);


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
