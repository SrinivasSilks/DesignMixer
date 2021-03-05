package com.varaprasadps.no8.a2021.design2.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/8/a2021/design2/1rani-%s-%s.bmp";

        BufferedImage ag = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/8/a2021/design2/border/border.bmp")));
        BufferedImage border = CutLayoutGenerator.get(CutLayoutGenerator.get(ag, 360, 1), 480, 0);
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
        inputBIs.add(EmptyGenerator.get(width, 60));
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 1).get(0)));
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
