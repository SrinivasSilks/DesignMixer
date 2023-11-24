package com.varaprasadps.no7.a2023own.blouse;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/7/out/design1/blouse-rani-%s-%s.bmp";

        BufferedImage left = ImageIO.read(new File("d/7/in/design1/border/left.bmp"));
        BufferedImage right = ImageIO.read(new File("d/7/in/design1/border/right.bmp"));
        BufferedImage blouse = HorizontalRepeatGenerator.get(right.getWidth() / 20, ImageIO.read(new File("d/7/in/design1/blouse/jari.bmp")));

        int width = left.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        //left-border
        inputBIs.add(left);
        //locking
        inputBIs.add(CutLayoutGenerator.get(blouse, blouse.getHeight() - 16).get(1));
        //body
        inputBIs.add(blouse);
        inputBIs.add(blouse);
        inputBIs.add(blouse);
        inputBIs.add(blouse);

        //locking
        inputBIs.add(CutLayoutGenerator.get(blouse, 16).get(0));
        //right-border
        inputBIs.add(right);

        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

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
