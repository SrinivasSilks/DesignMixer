package com.varaprasadps.no4.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/4/2nimbu-%s-%s.bmp";

        final BufferedImage skirt = CutLayoutGenerator.get(ImageIO.read(new File("z-data/in/3/B_NIMBU.bmp")), 1188).get(0);

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 32));

        // Locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 4));
        //Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 12));

        inputBIs.add(skirt);
        // Locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 12));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 176));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        // Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 12));

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
