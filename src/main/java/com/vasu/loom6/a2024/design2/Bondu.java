package com.vasu.loom6.a2024.design2;

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
        String out = "z-vasu/out/6/a2024/design2/kongu.bmp";

        BufferedImage blouse = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/out/6/a2024/design2/blouse-1792-480.bmp")));
        BufferedImage pallu = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/out/6/a2024/design2/pallu-1792-3200.bmp")));
        BufferedImage brocade = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/out/6/a2024/design2/1brocade-1792-1440.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();
//
//        inputBIs.add(brocade);
        inputBIs.add(CutLayoutGenerator.get(blouse, 120).get(0));
//        inputBIs.add(pallu);
//        inputBIs.add(CutLayoutGenerator.get(blouse, blouse.getHeight() - 40).get(1));
//        inputBIs.add(CutLayoutGenerator.get(blouse, 120).get(0));

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
