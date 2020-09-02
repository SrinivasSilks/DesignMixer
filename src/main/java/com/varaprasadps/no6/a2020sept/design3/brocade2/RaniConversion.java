package com.varaprasadps.no6.a2020sept.design3.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/a2020sept/design3/1rani-%s-%s.bmp";

        final BufferedImage border = HorizontalRepeatGenerator.get(3, ReverseGenerator.get(ImageIO.read(new File("z-data/in/6/a2020sept/design3/border/border-meena.bmp"))));
        BufferedImage raw = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/in/6/a2020sept/design3/brocade/nimbu.bmp")));
        final BufferedImage brocade = HorizontalRepeatGenerator.get(7, ReverseGenerator.get(raw));
        BufferedImage mispick = HorizontalRepeatGenerator.get(border.getWidth() / 4, ImageIO.read(new File("z-data/in/6/a2020sept/config/rani-mispick.bmp")));
        BufferedImage box = HorizontalRepeatGenerator.get(border.getWidth() / 4, ImageIO.read(new File("z-data/in/6/a2020sept/config/rani-box.bmp")));


        List<BufferedImage> images = CutLayoutGenerator.get(border, 400);
        BufferedImage right = images.get(1);
        images = CutLayoutGenerator.get(images.get(0), 180);
        BufferedImage a180 = images.get(0);
        BufferedImage left = images.get(1);

        final int width = border.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(border.getWidth(), 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));        //jamudu
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 10));

        inputBIs.add(VerticalFlipGenerator.get(left));
        inputBIs.add(VerticalFlipGenerator.get(a180));
        inputBIs.add(brocade);
        inputBIs.add(a180);
        inputBIs.add(right);

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 10));

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
