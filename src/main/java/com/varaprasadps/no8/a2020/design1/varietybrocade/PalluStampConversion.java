package com.varaprasadps.no8.a2020.design1.varietybrocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PalluStampConversion {

    static BufferedImage jari(BufferedImage body, BufferedImage chucks) {
        int width = body.getWidth();
        BufferedImage border = EmptyGenerator.get(width, 1120);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 1)));

        //brocade
        inputBIs.add(body);
        //mango
        inputBIs.add(PlainGenerator.get(width, 62));
        inputBIs.add(PlainGenerator.get(width, 18));
        inputBIs.add(EmptyGenerator.get(width, 16));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 4));

        //chucks
        inputBIs.add(chucks);
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
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

    static BufferedImage rani(BufferedImage border, BufferedImage chucks) {
        BufferedImage body = EmptyGenerator.get(border.getWidth(), 480);
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //brocade
        inputBIs.add(body);
        //mango
        inputBIs.add(EmptyGenerator.get(width, 62));
        //locking
        inputBIs.add(PlainGenerator.get(width, 18));
        inputBIs.add(PlainGenerator.get(width, 16));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //chucks
        inputBIs.add(chucks);
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
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
        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
