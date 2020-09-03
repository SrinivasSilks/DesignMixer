package com.varaprasadps.no3.a2020;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ThreePlay {

    public static BufferedImage nimbu(BufferedImage border, BufferedImage jari, BufferedImage chucks) {
        int width = border.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //border
        inputBIs.add(VerticalFlipGenerator.get(border));
        // locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 1)));
        //chucks
        inputBIs.add(chucks);
        // locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 2)));
        //body
        inputBIs.add(jari);
        //chucks
        inputBIs.add(chucks);
        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 1)));
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }


    public static BufferedImage jari(BufferedImage border, BufferedImage nimbu, BufferedImage chucks) {

        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //border
        inputBIs.add(VerticalFlipGenerator.get(border));
        // locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //chucks
        inputBIs.add(chucks);
        // locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //body
        inputBIs.add(nimbu);
        //chucks
        inputBIs.add(chucks);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }

    public static BufferedImage rani(BufferedImage border, BufferedImage chucks) {

        int width = border.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        //border
        inputBIs.add(VerticalFlipGenerator.get(border));
        // locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //chucks
        inputBIs.add(chucks);
        // locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //body
        inputBIs.add(EmptyGenerator.get(width, 600));
        //chucks
        inputBIs.add(chucks);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

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
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }


    public static BufferedImage empty(int width) {
        return EmptyGenerator.get(width, 12);
    }

    public static BufferedImage getBorder(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    public static BufferedImage getBrocade(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(String.format("Brocade => Width : %s, Height : %s", inputs.get(0).getWidth(), inputs.get(0).getHeight()));
        }
        return ColumnRepeatGenerator.get(inputs);
    }

    public static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
