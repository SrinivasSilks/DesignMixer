package com.varaprasadps.giri.no28.kongu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GooduAnniConversion {


    public static void main(final String[] args) throws IOException {

        String out = "z-giri/out/28/goodu-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(2, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(2, 2)));
        inputBIs.add(EmptyGenerator.get(2, 2));
        // Achu
        inputBIs.add(EmptyGenerator.get(2, 12));

        for (int i = 0; i < 360 / 4; i++) {
            BufferedImage plain = PlainGenerator.get(2, 1);
            BufferedImage reverse = ReverseGenerator.get(plain);
            inputBIs.add(plain);
            inputBIs.add(plain);
            inputBIs.add(reverse);
            inputBIs.add(reverse);
        }

        //skirt
        inputBIs.add(EmptyGenerator.get(2, 1360));

        //locking
        inputBIs.add(EmptyGenerator.get(2, 20));

        //banaras
        inputBIs.add(EmptyGenerator.get(2, 20));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(2, 2)));
        inputBIs.add(EmptyGenerator.get(2, 2));
        // Achu
        inputBIs.add(EmptyGenerator.get(2, 12));

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
