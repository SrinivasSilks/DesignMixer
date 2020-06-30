package com.varaprasadps.no5.a1border.korvai.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderJariOne {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/5/design1/border-jari1.bmp";

        //40 X 40
        BufferedImage bugada = HorizontalRepeatGenerator.get(15, ImageIO.read(new File("z-data/in/5/test/bugada-jari.bmp")));
        //5 X 15
        BufferedImage angle = HorizontalRepeatGenerator.get(120, ImageIO.read(new File("z-data/in/5/test/angle-jari.bmp")));
        //6 X 6
        BufferedImage banaras = HorizontalRepeatGenerator.get(100, ImageIO.read(new File("z-data/in/5/test/banaras-6-6-jari.bmp")));
        //40 X 30
        BufferedImage mango = HorizontalRepeatGenerator.get(15, ImageIO.read(new File("z-data/in/5/test/mango.bmp")));
        //20 X 30
        BufferedImage rudrakshi = HorizontalRepeatGenerator.get(30, ImageIO.read(new File("z-data/in/5/test/rudrakshi-jari.bmp")));
        //12 X 24
        BufferedImage onky = HorizontalRepeatGenerator.get(50, ImageIO.read(new File("z-data/in/5/test/vinky-jari.bmp")));
        //30 X 60
        BufferedImage square = HorizontalRepeatGenerator.get(20, ImageIO.read(new File("z-data/in/5/test/square-jari.bmp")));
        // 600 X 2
        BufferedImage empty = EmptyGenerator.get(600, 2);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(600, 4)));
        inputBIs.add(bugada);

        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);
        inputBIs.add(rudrakshi);
        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);
        for (int i = 0; i < 5; i++) {
            inputBIs.add(onky);
        }

        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);
        inputBIs.add(rudrakshi);
        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);

        inputBIs.add(mango);

        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);
        inputBIs.add(rudrakshi);
        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);

        for (int i = 0; i < 26; i++) {
            inputBIs.add(banaras);
        }

        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);
        inputBIs.add(rudrakshi);
        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);

        inputBIs.add(mango);

        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);
        inputBIs.add(rudrakshi);
        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);

        for (int i = 0; i < 5; i++) {
            inputBIs.add(onky);
        }

        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);
        inputBIs.add(rudrakshi);
        inputBIs.add(empty);
        inputBIs.add(angle);
        inputBIs.add(empty);
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(600, 8, 5 )));

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
