package com.varaprasadps.no12.a1recent.normal3.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/12/a1recent/normal3/p-rani-%s-%s.bmp";

        BufferedImage body = PlainGenerator.get(1920, 480);
        BufferedImage border = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/12/a1recent/normal3/border/border.bmp")));

        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));


        inputBIs.add(VerticalFlipGenerator.get(border));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        inputBIs.add(body);
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        inputBIs.add(border);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

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
