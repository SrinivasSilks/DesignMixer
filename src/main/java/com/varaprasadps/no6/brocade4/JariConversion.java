package com.varaprasadps.no6.brocade4;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/4/jari-%s-%s.bmp";
        final BufferedImage skirt = ImageIO.read(new File("z-data/in/6/4/B_JARI.bmp"));

        int width = skirt.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));
        // Box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //Achu Khali
        inputBIs.add(EmptyGenerator.get(width, 12));

        // Locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(skirt.getWidth(), 5)));

        inputBIs.add(CutLayoutGenerator.get(skirt, 1668).get(0));
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(skirt.getWidth(), 3)));

        // Jari
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 60));

        // Box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //Achu Khali
        inputBIs.add(EmptyGenerator.get(width, 12));

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
