package com.varaprasadps.no14.a2024.aug17;

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
        String out = "d/14/out/2024/aug17/pset.bmp";

        BufferedImage blouse = ImageIO.read(new File("d/14/out/2024/aug17/blouse-1952-2240.bmp"));
        BufferedImage pallu = ImageIO.read(new File("d/14/out/2024/aug17/1pallu-1952-3320.bmp"));
        BufferedImage kanni = ImageIO.read(new File("d/14/out/2024/aug17/1kanni-1952-356.bmp"));
        BufferedImage kongu = ImageIO.read(new File("d/14/out/2024/aug17/kongu-2-1952.bmp"));
        BufferedImage brocade = ImageIO.read(new File("d/14/out/2024/aug17/1kadiyalu-broc-1952-12600.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(brocade);
        inputBIs.add(kongu);
        inputBIs.add(kanni);
        inputBIs.add(kongu);
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(blouse, 158).get(1), 80).get(0));


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
