package com.varaprasadps.no3.a2022.design1.kadiyalu3;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/3/a2022/design1/k3/1brc-rani-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/3/a2022/design1/border/border.bmp")));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/3/a2022/design1/border/border-first.bmp"))));

        int width = left.getWidth();

        final BufferedImage body = PlainGenerator.get(width, 480);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(ReverseGenerator.get(AchuLayoutGenerator.get(width, 8)));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));

        //left border
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 14));
        inputBIs.add(PlainGenerator.get(width, 2));
        //body
        inputBIs.add(body);
        //locking
        inputBIs.add(PlainGenerator.get(width, 2));
        inputBIs.add(PlainGenerator.get(width, 14));
        //right
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
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
