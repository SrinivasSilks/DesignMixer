package com.vasu.loom4;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ThreePlay {

    public static BufferedImage nimbu(BufferedImage border, BufferedImage jari) {
        int width = border.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        //border
        inputBIs.add(EmptyGenerator.get(border.getWidth(), border.getHeight()));

        //locking
        inputBIs.add(StepLayoutGenerator.get(width, 4));
        for (int i = 0; i < 720 / jari.getHeight(); i++) {
            inputBIs.add(jari);
        }
        //locking
        inputBIs.add(StepLayoutGenerator.get(width, 4));

        //border
        inputBIs.add(EmptyGenerator.get(border.getWidth(), border.getHeight()));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //kali
        inputBIs.add(EmptyGenerator.get(width, 256));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }


    public static BufferedImage jari(BufferedImage border, BufferedImage nimbu) {

        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        //border
        inputBIs.add(EmptyGenerator.get(border.getWidth(), border.getHeight()));
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        for (int i = 0; i < 720 / nimbu.getHeight(); i++) {
            inputBIs.add(nimbu);
        }
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //border
        inputBIs.add(EmptyGenerator.get(border.getWidth(), border.getHeight()));

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //kali
        inputBIs.add(EmptyGenerator.get(width, 256));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }

    public static BufferedImage rani(BufferedImage right, BufferedImage left) {
        return rani(right, left, EmptyGenerator.get(right.getWidth(), 720));
    }

    public static BufferedImage rani(BufferedImage right, BufferedImage left, BufferedImage rani) {

        int width = right.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

        //border
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        inputBIs.add(rani);
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //border
        inputBIs.add(right);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        //kali
        inputBIs.add(EmptyGenerator.get(width, 256));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }

    public static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }


    public static BufferedImage empty(int width) {
        return EmptyGenerator.get(width, 12);
    }

    public static BufferedImage getBorder(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    public static BufferedImage getBrocade(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.printf("Brocade => Width : %s, Height : %s%n", inputs.get(i).getWidth(), inputs.get(i).getHeight());
        }
        return ColumnRepeatGenerator.get(inputs);
    }

    public static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
