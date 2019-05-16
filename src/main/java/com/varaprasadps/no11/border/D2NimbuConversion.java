package com.varaprasadps.no11.border;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class D2NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/11/new/nimbu-%s-%s.bmp";

        final BufferedImage skirt = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/brocade1/NIMBU.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        int width = skirt.getWidth();

        inputBIs.add(EmptyGenerator.get(width, 31));

        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));

        //D1 kali
        inputBIs.add(EmptyGenerator.get(width, 12));

        // D3 kali
        inputBIs.add(EmptyGenerator.get(width, 4));

        inputBIs.add(skirt);

        //D1 kali
        inputBIs.add(EmptyGenerator.get(width, 280));
        inputBIs.add(EmptyGenerator.get(width, 16));


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
