package com.varaprasadps.no5.a2020.design4.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/a2020/design4/broc-jari-%s-%s.bmp";

        BufferedImage border = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/5/a2020/design4/border-jari.bmp")));
        BufferedImage body = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/5/a2020/design4/brocade2/55.bmp")));


        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(body);
        inputBIs.add(body);
        inputBIs.add(CutLayoutGenerator.get(border, 940).get(0));
        inputBIs.add(EmptyGenerator.get(width, 20));


        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 12), 6).get(0)));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 10));
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
