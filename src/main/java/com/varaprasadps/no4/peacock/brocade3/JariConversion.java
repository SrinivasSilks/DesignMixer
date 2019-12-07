package com.varaprasadps.no4.peacock.brocade3;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/4/peacock/3jari-%s-%s.bmp";

        final BufferedImage jari = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/4/peacock/brocade3/JARI.bmp")));
        int width = jari.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(width, 32));

        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 1)));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));

        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(jari);

        inputBIs.add(EmptyGenerator.get(width, 900));
        inputBIs.add(EmptyGenerator.get(width, 70));

        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 1)));

        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));

        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

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
