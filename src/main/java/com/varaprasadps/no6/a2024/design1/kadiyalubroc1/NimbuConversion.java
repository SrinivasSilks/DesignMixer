package com.varaprasadps.no6.a2024.design1.kadiyalubroc1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.CutLayoutGenerator.get;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/6/out/design1/kbroc-nimbu-%s-%s.bmp";

        final BufferedImage body = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/6/in/design1/brocade1/jari.bmp")));
        int width = body.getWidth();

        BufferedImage right = EmptyGenerator.get(width, 400);
        BufferedImage left = EmptyGenerator.get(width, 400);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));


        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //left
        inputBIs.add(left);
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(right);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //mispick
        inputBIs.add(ReverseGenerator.get(get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 6));


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
