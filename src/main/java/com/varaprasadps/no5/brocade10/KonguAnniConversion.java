package com.varaprasadps.no5.brocade10;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;
import com.varaprasadps.image.KonguLayoutGenerator;
import com.varaprasadps.image.ReverseGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguAnniConversion {


    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/10/kongu-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(2, 32));
        inputBIs.add(EmptyGenerator.get(2, 16));
        inputBIs.add(KonguLayoutGenerator.get(800 / 4));
        inputBIs.add(EmptyGenerator.get(2, 960));
        inputBIs.add(EmptyGenerator.get(2, 16));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
