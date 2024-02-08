package com.varaprasadps.no4.a2023.design1.selfbroc1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/4/out/design1/1self-rani-%s-%s.bmp";
        final BufferedImage right = HorizontalRepeatGenerator.get(7, ImageIO.read(new File("d/4/in/design1/border/right.bmp")));
        final BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(7, ImageIO.read(new File("d/4/in/design1/border/left.bmp"))));

        int width = right.getWidth();

        final BufferedImage body =  HorizontalRepeatGenerator.get(18, ImageIO.read(new File("d/4/in/design1/selfbroc1/meena.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //body
        inputBIs.add(body);
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        inputBIs.add(right);

        //jamudu
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

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

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

    private static BufferedImage abc(BufferedImage read) {
        return CutLayoutGenerator.get(read, 360, 0);
    }

    private static BufferedImage cut(BufferedImage read) {
        return CutLayoutGenerator.get(HorizontalRepeatGenerator.get(6, read), 2000, 0);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
