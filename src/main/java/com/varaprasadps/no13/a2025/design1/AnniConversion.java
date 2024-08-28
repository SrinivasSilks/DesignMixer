package com.varaprasadps.no13.a2025.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/13/out/2025/design1/anni-%s-%s.bmp";

        BufferedImage rightJari = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/13/in/2025/design1/border/right.bmp")));
        BufferedImage teega = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/13/in/2025/design1/border/teega.bmp")));
        BufferedImage figure = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/13/in/2025/design1/border/banaras.bmp")));

        BufferedImage rightMeena = HorizontalRepeatGenerator.get(1, PlainGenerator.get(rightJari.getWidth(), rightJari.getHeight()));
        BufferedImage teegaMeena = HorizontalRepeatGenerator.get(1, PlainGenerator.get(teega.getWidth(), teega.getHeight()));
        BufferedImage figureMeena = HorizontalRepeatGenerator.get(1, PlainGenerator.get(figure.getWidth(), figure.getHeight()));

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
        inputBIs.add(VerticalFlipGenerator.get(figure));
        inputBIs.add(VerticalFlipGenerator.get(figureMeena));
        //left Teega
        inputBIs.add(VerticalFlipGenerator.get(teega));
        inputBIs.add(VerticalFlipGenerator.get(teegaMeena));

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
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
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
