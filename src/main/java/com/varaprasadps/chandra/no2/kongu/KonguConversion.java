package com.varaprasadps.chandra.no2.kongu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-chandra/out/2/2kongu-%s-%s.bmp";

        int width = 2;
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));

        // Achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        inputBIs.add(CutLayoutGenerator.get(KonguLayoutGenerator.get(324), 1294).get(0));

        inputBIs.add(EmptyGenerator.get(width, 210));

        //Box khali
        inputBIs.add(EmptyGenerator.get(width, 4));

        //Achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        inputBIs.add(EmptyGenerator.get(width, 128));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = HorizontalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
