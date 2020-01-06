package com.varaprasadps.no1.recent.brocade3;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/1/recent/3nimbu-%s-%s.bmp";


        final BufferedImage body = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/1/recent/brocade3/JARI.bmp")));

        int width = body.getWidth();
        final BufferedImage right = EmptyGenerator.get(width, 368);
        final BufferedImage left = EmptyGenerator.get(width, 368);

        List<BufferedImage> inputBIs = new LinkedList<>();

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));


        inputBIs.add(left);
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 2)));

        inputBIs.add(CutLayoutGenerator.get(body, 548).get(1));
        inputBIs.add(body);
        inputBIs.add(body);
        inputBIs.add(body);
        inputBIs.add(CutLayoutGenerator.get(body, 52).get(0));

        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 2)));
        inputBIs.add(right);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //khali
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
