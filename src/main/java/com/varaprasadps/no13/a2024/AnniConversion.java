package com.varaprasadps.no13.a2024;

import com.varaprasadps.bala.b1.Plain;
import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/13/out/2024/design1/anni-%s-%s.bmp";

        BufferedImage rightJari = StepLayoutGenerator.get(20, 148);
        BufferedImage rightMeena = PlainGenerator.get(20, 592);
        BufferedImage leftFigureJari = StepLayoutGenerator.get(20, 16);
        BufferedImage leftFigureMeena = PlainGenerator.get(20, 64);
        BufferedImage leftTeegaJari = StepLayoutGenerator.get(20, 12);
        BufferedImage leftTeegaMeena = PlainGenerator.get(20, 48);

        int width = rightJari.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 4));

        //left Figure
        inputBIs.add(VerticalFlipGenerator.get(leftFigureJari));
        inputBIs.add(VerticalFlipGenerator.get(leftFigureMeena));
        //left Teega
        inputBIs.add(VerticalFlipGenerator.get(leftTeegaJari));
        inputBIs.add(VerticalFlipGenerator.get(leftTeegaMeena));

        //allover
        inputBIs.add(PlainGenerator.get(width, 480));

        //Right Border
        inputBIs.add(rightJari);
        inputBIs.add(rightMeena);

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = LeftLayoutGenerator.get(ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
