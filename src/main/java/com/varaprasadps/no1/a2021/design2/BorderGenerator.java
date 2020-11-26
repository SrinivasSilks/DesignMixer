package com.varaprasadps.no1.a2021.design2;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.StepLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/1/a2021/design1/border/test.bmp";

        BufferedImage image = ImageIO.read(new File("z-data/in/1/a2021/border.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();


        List<BufferedImage> images = CutLayoutGenerator.get(image, 300);
        BufferedImage wholeimage = images.get(0);
        List<BufferedImage> ss = CutLayoutGenerator.get(wholeimage, 240);
        BufferedImage first = ss.get(0);
        BufferedImage bugada = ss.get(1);

        inputBIs.add(first);
        inputBIs.add(bugada);
        inputBIs.add(StepLayoutGenerator.get(bugada.getWidth(), 5, 5));
        inputBIs.add(bugada);

//
//        List<BufferedImage> images = CutLayoutGenerator.get(image, 300);
//        BufferedImage first = images.get(0);
//        BufferedImage test = images.get(1);
//
//
//
//        images = CutLayoutGenerator.get(test, 450);
//        BufferedImage second = images.get(0);
//
//        inputBIs.add(first);
//        inputBIs.add(CutLayoutGenerator.get(test, 40).get(0));
//        inputBIs.add(second);
//        inputBIs.add(CutLayoutGenerator.get(images.get(1), 40).get(0));
//        inputBIs.add(images.get(1));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
//        BufferedImage bi = CutLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs), 384).get(0);
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
