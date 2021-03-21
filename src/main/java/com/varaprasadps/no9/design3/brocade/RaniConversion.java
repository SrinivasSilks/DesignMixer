package com.varaprasadps.no9.design3.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/9/a2021/design3/1rani-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(6, ImageIO.read(new File("z-data/in/9/a2021/design3/border/right.bmp")));
        BufferedImage left = HorizontalRepeatGenerator.get(6, ImageIO.read(new File("z-data/in/9/a2021/design3/border/left.bmp")));
        BufferedImage body = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/9/a2021/design3/brocade/rani.bmp")));
        int width = right.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        //left
        inputBIs.add(VerticalFlipGenerator.get(left));
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //body
        inputBIs.add(body);
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //right
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 14));

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
