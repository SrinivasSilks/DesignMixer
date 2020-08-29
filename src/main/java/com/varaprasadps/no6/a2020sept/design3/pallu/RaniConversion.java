package com.varaprasadps.no6.a2020sept.design3.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/a2020sept/design3/p-rani-%s-%s.bmp";

        BufferedImage v = HorizontalRepeatGenerator.get(8, ReverseGenerator.get(ImageIO.read(new File("z-data/in/6/a2020sept/design3/border/border-meena.bmp"))));
        final BufferedImage border = RightLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(v), 2000).get(0));
        BufferedImage raw = ImageIO.read(new File("z-data/in/6/a2020sept/design3/pallu/pallu-rani.bmp"));
        final BufferedImage brocade = ReverseGenerator.get(raw);
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
        inputBIs.add(box);
        //mispick
        inputBIs.add(mispick);
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 10));

        inputBIs.add(VerticalFlipGenerator.get(left));
        inputBIs.add(VerticalFlipGenerator.get(a180));
        inputBIs.add(brocade);
        inputBIs.add(a180);
        inputBIs.add(right);

        //box
        inputBIs.add(box);
        //mispick
        inputBIs.add(ReverseGenerator.get(mispick));
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
