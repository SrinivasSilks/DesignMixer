package com.varaprasadps.no5.brocade_5;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;
import com.varaprasadps.image.ReverseGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/5/nimbu-%s-%s.bmp";
        final BufferedImage nimbu = ImageIO.read(new File("z-data/in/5/5/NIMBU.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(nimbu.getWidth(), 32));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(nimbu.getWidth(), 2)));
        inputBIs.add(EmptyGenerator.get(nimbu.getWidth(), 2));
        inputBIs.add(EmptyGenerator.get(nimbu.getWidth(), 12));
        inputBIs.add(nimbu);
        inputBIs.add(EmptyGenerator.get(nimbu.getWidth(), 16));

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
