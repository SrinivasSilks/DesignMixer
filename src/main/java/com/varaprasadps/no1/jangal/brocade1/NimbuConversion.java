package com.varaprasadps.no1.jangal.brocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/j_1_nimbu-%s-%s.bmp";
        final BufferedImage skirt = ImageIO.read(new File("z-data/in/1/jangla/1/NIMBU.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));

        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(skirt.getWidth(), 4), 2).get(0));

        // Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        //Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 8));

        // Locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(skirt.getWidth(), 20)));

        inputBIs.add(skirt);

        // Jari
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 60));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(skirt.getWidth(), 4), 2).get(0)));

        // Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));

        // Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 8));


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
