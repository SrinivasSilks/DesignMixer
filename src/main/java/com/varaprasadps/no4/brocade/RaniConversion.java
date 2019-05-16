package com.varaprasadps.no4.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/4/rani-%s-%s.bmp";

        final BufferedImage skirt = CutLayoutGenerator.get(ImageIO.read(new File("z-data/in/4/B_RANI.bmp")), 1188).get(0);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 32));

        // Locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 4));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 12));

        inputBIs.add(skirt);
        // Locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 12));
        inputBIs.add(HorizontalRepeatGenerator.get(33, ImageIO.read(new File("z-data/in/4/BORDER.bmp"))));

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 12));

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
