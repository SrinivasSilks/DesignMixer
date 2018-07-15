package com.varaprasadps.giri.no28.anni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-giri/out/28/anni-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(12, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(12, 2)));
        inputBIs.add(EmptyGenerator.get(12, 2));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(12, 12));

        inputBIs.add(PlainGenerator.get(12, 360));
        inputBIs.add(PlainGenerator.get(12, 1360));

        //locking
        inputBIs.add(PlainGenerator.get(12, 20));

        //banaras
        inputBIs.add(StepLayoutGenerator.get(12, 5));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(12, 2)));
        inputBIs.add(EmptyGenerator.get(12, 2));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(12, 12));

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
