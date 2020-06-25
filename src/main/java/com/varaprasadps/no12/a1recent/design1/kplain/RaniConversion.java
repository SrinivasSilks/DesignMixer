package com.varaprasadps.no12.a1recent.design1.kplain;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/12/a1recent/design1/plain-rani-%s-%s.bmp";

        BufferedImage left = VerticalFlipGenerator.get(ReverseGenerator.get(ImageIO.read(new File("z-data/in/12/a1recent/design1/border1/border.bmp"))));
        BufferedImage right = ReverseGenerator.get(ImageIO.read(new File("z-data/in/12/a1recent/design1/border1/border.bmp")));
        int width = left.getWidth();
        final BufferedImage body = PlainGenerator.get(width, 480);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));

        inputBIs.add(body);

        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 8)));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        inputBIs.add(right);

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //achu
        inputBIs.add(ReverseGenerator.get(AchuLayoutGenerator.get(width, 8)));

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
