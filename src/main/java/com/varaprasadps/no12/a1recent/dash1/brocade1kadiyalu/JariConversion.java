package com.varaprasadps.no12.a1recent.dash1.brocade1kadiyalu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/12/a1recent/dash1/jari-%s-%s.bmp";

        final BufferedImage body = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/12/a1recent/brocade1/NIMBU.BMP")));
        final BufferedImage left = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/a1recent/border/border.bmp")));
        final BufferedImage right = ImageIO.read(new File("z-data/in/12/a1recent/border/border.bmp"));

        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 8)));

        inputBIs.add(body);
        inputBIs.add(body);
        inputBIs.add(body);

        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 8)));
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 8)));
        inputBIs.add(right);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
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
