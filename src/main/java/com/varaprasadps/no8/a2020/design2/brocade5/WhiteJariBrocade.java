package com.varaprasadps.no8.a2020.design2.brocade5;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WhiteJariBrocade {

    static int i = 0;

    public static BufferedImage get(BufferedImage border, BufferedImage jari, BufferedImage nimbu, BufferedImage chucks) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, empty(border.getWidth())));
        brocades.add(jari(getBorder(border), nimbu, chucks));
        brocades.add(nimbu(getBorder(border), jari, chucks));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-data/out/8/a2020/8design2/test2/w-%s-brocade.bmp", i++));
        return brocade;
    }

    private static BufferedImage empty(int width) {
        return EmptyGenerator.get(width, 60);
    }

    private static BufferedImage getBorder(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    private static BufferedImage getBrocade(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(String.format("Brocade => Width : %s, Height : %s", inputs.get(0).getWidth(), inputs.get(0).getHeight()));
        }
        return ColumnRepeatGenerator.get(inputs);
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


    private static BufferedImage nimbu(BufferedImage border, BufferedImage jari, BufferedImage chucks) {
        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 1)));

        //jari
        inputBIs.add(jari);
        //mango
        inputBIs.add(CutLayoutGenerator.get(jari, 96).get(0));
        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 1)));
        //chucks
        inputBIs.add(chucks);
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 1).get(0)));
        //chakram
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }


    private static BufferedImage jari(BufferedImage border, BufferedImage nimbu, BufferedImage chucks) {

        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //brocade
        inputBIs.add(nimbu);
        //mango
        inputBIs.add(CutLayoutGenerator.get(nimbu, 96).get(0));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //chucks
        inputBIs.add(chucks);
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 1).get(0)));
        //chakram
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }

    private static BufferedImage rani(BufferedImage border, BufferedImage chucks) {
        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //brocade
        inputBIs.add(EmptyGenerator.get(width, 480));
        //mango
        inputBIs.add(EmptyGenerator.get(width, 96));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //chucks
        inputBIs.add(chucks);
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 1).get(0)));
        //chakram
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

}
