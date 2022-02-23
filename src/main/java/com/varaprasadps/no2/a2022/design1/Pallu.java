package com.varaprasadps.no2.a2022.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Pallu {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/2/a2020/pallu/pallu-rani.bmp";

        BufferedImage middle = ImageIO.read(new File("z-data/in/2/a2020/pallu/middle-rani.bmp"));
//        BufferedImage bugada = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/2/a2020/pallu/pallu-bugada-jari.bmp")));
        BufferedImage bugada = PlainGenerator.get(1200, 430);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(bugada);
        inputBIs.add(CutLayoutGenerator.get(middle, 60).get(1));
        for (int i = 0; i < 3; i++) {
            inputBIs.add(middle);
        }
        List<BufferedImage> images = CutLayoutGenerator.get(bugada, 39);
        inputBIs.add(images.get(1));
        inputBIs.add(VerticalFlipGenerator.get(images.get(0)));

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

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
