package com.varaprasadps.no5.a1border;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderMeenaOne {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/5/border/border-meena1.bmp";

        //40 X 40
        BufferedImage bugada = HorizontalRepeatGenerator.get(15, ImageIO.read(new File("z-data/in/5/test/bugada-meena.bmp")));
        //5 X 15
        BufferedImage angle = PlainGenerator.get(600, 15);
        //6 X 6
        BufferedImage banaras = HorizontalRepeatGenerator.get(100, PlainGenerator.get(6, 6));
        //40 X 30
        BufferedImage mango = HorizontalRepeatGenerator.get(15, ImageIO.read(new File("z-data/in/5/test/mango-rani.bmp")));
        //20 X 30
        BufferedImage rudrakshi = HorizontalRepeatGenerator.get(30, PlainGenerator.get(20, 30));
        //12 X 24
        BufferedImage onky = HorizontalRepeatGenerator.get(50, ImageIO.read(new File("z-data/in/5/test/vinky-rani.bmp")));
        // 600 X 2
        BufferedImage empty = PlainGenerator.get(600, 2);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(PlainGenerator.get(600, 16));
        inputBIs.add(bugada);

        inputBIs.add(PlainGenerator.get(600, 2 + 15 + 2 + 30 + 2 + 15 + 2));

        for (int i = 0; i < 5; i++) {
            inputBIs.add(onky);
        }

        inputBIs.add(PlainGenerator.get(600, 2 + 15 + 2 + 30 + 2 + 15 + 2));

        inputBIs.add(mango);

        inputBIs.add(PlainGenerator.get(600, 2 + 15 + 2 + 30 + 2 + 15 + 2));

        for (int i = 0; i < 26; i++) {
            inputBIs.add(banaras);
        }

        inputBIs.add(PlainGenerator.get(600, 2 + 15 + 2 + 30 + 2 + 15 + 2));

        inputBIs.add(mango);

        inputBIs.add(PlainGenerator.get(600, 2 + 15 + 2 + 30 + 2 + 15 + 2));

        for (int i = 0; i < 5; i++) {
            inputBIs.add(onky);
        }

        inputBIs.add(PlainGenerator.get(600, 2 + 15 + 2 + 30 + 2 + 15 + 2));

        inputBIs.add(PlainGenerator.get(600, 40));

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

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
