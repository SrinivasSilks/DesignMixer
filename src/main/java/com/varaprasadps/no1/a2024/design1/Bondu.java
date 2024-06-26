package com.varaprasadps.no1.a2024.design1;

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
        String out = "d/1/out/2024/design1/pset.bmp";

        BufferedImage anni = VerticalFlipGenerator.get(ImageIO.read(new File("d/1/out/2024/design1/1anni-240-2688.bmp")));
        BufferedImage pallu = VerticalFlipGenerator.get(ImageIO.read(new File("d/1/out/2024/design1/pallu-2688-3600.bmp")));
        BufferedImage brocade = VerticalFlipGenerator.get(ImageIO.read(new File("d/1/out/2024/design1/1selfbrocade-2688-2400.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(anni, 60).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, 180).get(1));


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
