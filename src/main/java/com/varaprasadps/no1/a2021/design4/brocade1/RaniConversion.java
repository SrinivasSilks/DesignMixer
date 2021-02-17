package com.varaprasadps.no1.a2021.design4.brocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/a2021/design4/1rani-%s-%s.bmp";

        final BufferedImage leftt = ImageIO.read(new File("z-data/in/1/a2021/design4/border/left.bmp"));
        final BufferedImage rightt = ImageIO.read(new File("z-data/in/1/a2021/design4/border/right.bmp"));

        BufferedImage rightf = HorizontalRepeatGenerator.get(2, rightt);
        BufferedImage leftf = HorizontalRepeatGenerator.get(2, leftt);

        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rightf, 280, 1), 400, 0);
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(leftf, 280, 1), 400, 0);

        int width = left.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        //left border
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        inputBIs.add(EmptyGenerator.get(width, 960));
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //right border
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 14));

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

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
