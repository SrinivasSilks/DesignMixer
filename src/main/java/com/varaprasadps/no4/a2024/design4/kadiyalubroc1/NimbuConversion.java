package com.varaprasadps.no4.a2024.design4.kadiyalubroc1;

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

        String out = "d/4/out/a2024/design3/1kbroc-nimbu-%s-%s.bmp";

        BufferedImage body = HorizontalRepeatGenerator.get(12, PlainGenerator.get(340, 480));
        int width = body.getWidth();
        BufferedImage left = HorizontalRepeatGenerator.get(1, EmptyGenerator.get(width, 396));
        BufferedImage right = HorizontalRepeatGenerator.get(1, EmptyGenerator.get(width, 480));
        BufferedImage jamudu = EmptyGenerator.get(width, 4);


        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //jamudu
        inputBIs.add(jamudu);


        //left
        inputBIs.add(VerticalFlipGenerator.get(left));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //body
        inputBIs.add(CutLayoutGenerator.get(body, body.getHeight() - 4).get(1));
        inputBIs.add(body);
        inputBIs.add(CutLayoutGenerator.get(body, 4).get(0));

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //right
        inputBIs.add(right);

        //jamudu
        inputBIs.add(jamudu);
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));


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
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
