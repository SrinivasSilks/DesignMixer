package com.varaprasadps.no1.a2023.silkfast.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/1/out/design1/p-rani-%s-%s.bmp";

        BufferedImage bordesr = ImageIO.read(new File("d/1/in/design1/border/border.bmp"));
        BufferedImage borderrr = HorizontalRepeatGenerator.get(8, bordesr);
        BufferedImage border = CutLayoutGenerator.get(CutLayoutGenerator.get(borderrr, 180, 1), 1700, 0);
        BufferedImage body = PlainGenerator.get(border.getWidth(), 240);

        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        //left
        inputBIs.add(VerticalFlipGenerator.get(border));
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));

        inputBIs.add(CutLayoutGenerator.get(body, body.getHeight() - 156).get(1));
        inputBIs.add(body);
        inputBIs.add(body);
        inputBIs.add(body);
        inputBIs.add(body);
        inputBIs.add(body);
        inputBIs.add(body);

        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //right
        inputBIs.add(border);

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 2));
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
