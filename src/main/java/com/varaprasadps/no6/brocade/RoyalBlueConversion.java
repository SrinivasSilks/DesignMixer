package com.varaprasadps.no6.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RoyalBlueConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/blue-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(5200, 32));

        // Locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(5200, 8)));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(5200, 8));

        int number = 109;
        BufferedImage input = ReverseGenerator.get(ImageIO.read(new File("z-data/in/6/new/r.blue.bmp")));
        BufferedImage ariel = StepLayoutGenerator.get(input.getWidth());
        inputBIs.add(ArielLayoutGenerator.get(input, number, ariel));

        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(5200)));
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(5200)));
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(5200)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(5200, 4));

        // Box
        inputBIs.add(EmptyGenerator.get(5200, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(5200, 2)));

        // Locking
        inputBIs.add(PlainGenerator.get(5200, 8));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(5200, 4));

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
