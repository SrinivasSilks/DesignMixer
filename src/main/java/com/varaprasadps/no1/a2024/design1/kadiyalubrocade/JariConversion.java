package com.varaprasadps.no1.a2024.design1.kadiyalubrocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/1/out/2024/design1/kadiyalu/1jari-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/1/in/2024/design1/border/border.bmp")));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/1/in/2024/design1/border/border.bmp"))));
        BufferedImage body = HorizontalRepeatGenerator.get(1, PlainGenerator.get(240, 960));

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

        //temple
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(body, 80).get(1), 152).get(0));
        //body
        inputBIs.add(body);
        //skirt
        inputBIs.add(CutLayoutGenerator.get(body, 480).get(0));

        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        inputBIs.add(PlainGenerator.get(width, 16));
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
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
