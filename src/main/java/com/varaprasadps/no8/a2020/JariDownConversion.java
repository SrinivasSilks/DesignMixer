package com.varaprasadps.no8.a2020;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariDownConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/8/a2020/down-%s-%s.bmp";

        BufferedImage border = EmptyGenerator.get(100, 1120);
        BufferedImage chucks = EmptyGenerator.get(100, 60);
        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //locking
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
//z-data/out/8/a2020/jari-edit.bmp
        //brocade
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 480)));
        //mango
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 96)));
        //locking
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //chucks
        inputBIs.add(chucks);
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
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
