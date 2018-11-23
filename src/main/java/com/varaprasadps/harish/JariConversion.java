package com.varaprasadps.harish;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {
        String out = "z-harish/jari-%s-%s.bmp";
        final BufferedImage skirt = EmptyGenerator.get(100, 960);
        final BufferedImage body = StepLayoutGenerator.get(100, 100);

        List<BufferedImage> inputBIs = new LinkedList<>();

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        //Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 12));

        // Locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(skirt.getWidth(), 4)));

        inputBIs.add(body);
        inputBIs.add(skirt);

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        //Achu
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 12));


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
