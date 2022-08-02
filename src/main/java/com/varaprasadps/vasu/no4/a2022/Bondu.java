package com.varaprasadps.vasu.no4.a2022;

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
        String out = "z-vasu/out/4/a2022/pset.bmp";

        BufferedImage anni = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/out/4/a2022/anni-240-1792.bmp")));
        BufferedImage kongu = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/out/4/a2022/kongu-2-1792.bmp")));
        BufferedImage kanni = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/out/4/a2022/kongu-anni-1792-480.bmp")));
        BufferedImage pallu = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/out/4/a2022/pallu-1792-3840.bmp")));
        BufferedImage brocade = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/out/4/a2022/brocade-1792-9360.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();
//
//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 260).get(0), 240).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 270).get(0), 260).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(anni, 225).get(0), 135).get(1));
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(kanni, 470).get(0), 450).get(1));
        inputBIs.add(kongu);
        inputBIs.add(CutLayoutGenerator.get(kanni, 470).get(1));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(anni, 90).get(0));

//        inputBIs.add(CutLayoutGenerator.get(anni, 350).get(1));
//        inputBIs.add(CutLayoutGenerator.get(anni, 10).get(0));

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
