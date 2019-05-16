package com.varaprasadps.chandra.no1.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-chandra/out/1/rani-%s-%s.bmp";
        final BufferedImage skirt = CutLayoutGenerator.get(ImageIO.read(new File("z-chandra/in/1/B_RANI.bmp")), 1124).get(0);
        final BufferedImage border = ImageIO.read(new File("z-chandra/in/1/BORDER_360-12500.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 128));


        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        //locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 4));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 10));

        inputBIs.add(skirt);
        //locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 16));

        inputBIs.add(border);

        //locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 4));

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 12));

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 128));

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

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
