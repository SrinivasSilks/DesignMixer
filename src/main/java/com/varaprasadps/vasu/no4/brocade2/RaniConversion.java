package com.varaprasadps.vasu.no4.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/4/test/2rani-%s-%s.bmp";
        BufferedImage test = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-vasu/in/4/B_RANI.bmp"))));
        final BufferedImage skirtTest = RightLayoutGenerator.get(CutLayoutGenerator.get(CutLayoutGenerator.get(test, 1020).get(1), 2040).get(0));
        final BufferedImage skirt = HorizontalRepeatGenerator.get(4 , skirtTest);

        List<BufferedImage> inputBIs = new LinkedList<>();

        // Board Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 128));

        // khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(skirt.getWidth(), 4), 2).get(0));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 12));

        inputBIs.add(skirt);
        // Locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(skirt.getWidth(), 16)));

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(skirt.getWidth(), 4), 2).get(0)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 10));

        // Board Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 256));

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
