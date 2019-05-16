package com.varaprasadps.giri.no9;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-giri/out/jari-%s-%s.bmp";
        final BufferedImage body = ImageIO.read(new File("z-giri/in/9/BODY_JARI.bmp"));
        final BufferedImage skirt = ImageIO.read(new File("z-giri/in/9/SKIRT_JARI.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 32));

        //Achu Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 12));
        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));

        //Banaras Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 20));

        inputBIs.add(HorizontalRepeatGenerator.get(3, body));
        inputBIs.add(skirt);

        //Banaras Khali
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 40));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        //Achu Khali
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
