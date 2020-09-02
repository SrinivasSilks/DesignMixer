package com.varaprasadps.no6.a2020sept.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/a2020sept/design1/p-jari-%s-%s.bmp";

        BufferedImage v = HorizontalRepeatGenerator.get(17, ReverseGenerator.get(ImageIO.read(new File("z-data/in/6/a2020sept/border2/border-jari.bmp"))));
        final BufferedImage border = RightLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(v), 2000).get(0));
        BufferedImage raw = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/in/6/a2020sept/pallu2/pallu-jari.bmp")));
        final BufferedImage brocade = ReverseGenerator.get(raw);
        BufferedImage mispick = CutLayoutGenerator.get(AchuLayoutGenerator.get(border.getWidth(), 4), 2).get(0);        //jamudu

//        BufferedImage mispick = HorizontalRepeatGenerator.get(border.getWidth() / 4, ImageIO.read(new File("z-data/in/6/a2020sept/config/jari-mispick.bmp")));
        BufferedImage box = HorizontalRepeatGenerator.get(border.getWidth() / 4, ImageIO.read(new File("z-data/in/6/a2020sept/config/jari-box.bmp")));


        List<BufferedImage> images = CutLayoutGenerator.get(border, 400);
        BufferedImage right = images.get(1);
        images = CutLayoutGenerator.get(images.get(0), 180);
        BufferedImage a180 = images.get(0);
        BufferedImage left = images.get(1);

        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(mispick);
        //jamudu
        inputBIs.add(StepLayoutGenerator.get(width, 2, 5));

        inputBIs.add(VerticalFlipGenerator.get(left));
        inputBIs.add(VerticalFlipGenerator.get(a180));
        inputBIs.add(brocade);
        inputBIs.add(brocade);
        inputBIs.add(a180);
        inputBIs.add(right);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(mispick));
        //jamudu
        inputBIs.add(StepLayoutGenerator.get(width, 2, 5));

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
