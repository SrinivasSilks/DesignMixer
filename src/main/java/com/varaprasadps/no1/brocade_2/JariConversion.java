package com.varaprasadps.no1.brocade_2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {


    /*
    *
    * 0-10 - Empty
    * 11-200 - Reverse Image (Input)
    * 201-202 - service configuration (fixed)
    *
    * */
    //
    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/2_jari-%s-%s.bmp";
        final BufferedImage skirt = ImageIO.read(new File("z-data/in/1/2/B_JARI.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();
        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        // Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 4));
        //Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 8));

        // Locking
        inputBIs.add(HorizontalFlipGenerator.get(ReverseGenerator.get(StepLayoutGenerator.get(skirt.getWidth(), 5))));

        inputBIs.add(CutLayoutGenerator.get(skirt, 1668).get(0));
        inputBIs.add(HorizontalFlipGenerator.get(ReverseGenerator.get(StepLayoutGenerator.get(skirt.getWidth(), 3))));

        // Jari
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 60));

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        // Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 4));

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
