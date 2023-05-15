package com.varaprasadps.no1.a2023.d.kadiyalubrocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/1/out/design1/kadiyalu/1jari-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/1/in/design1/border/border.bmp")));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/1/in/design1/border/border.bmp"))));
        BufferedImage body = HorizontalRepeatGenerator.get(1, PlainGenerator.get(720, 960));

        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(width, 128));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 12)));
        inputBIs.add(PlainGenerator.get(width, 4));


        inputBIs.add(CutLayoutGenerator.get(body, body.getHeight() - 156 - 480).get(1));
        inputBIs.add(body);

        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        inputBIs.add(PlainGenerator.get(width, 12));
        //right
        inputBIs.add(right);

        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

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
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
