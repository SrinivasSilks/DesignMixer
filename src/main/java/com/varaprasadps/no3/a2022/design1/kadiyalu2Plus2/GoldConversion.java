package com.varaprasadps.no3.a2022.design1.kadiyalu2Plus2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GoldConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/3/a2022/design1/k2/2brc-gold-%s-%s.bmp";

        BufferedImage body = HorizontalRepeatGenerator.get(12, ImageIO.read(new File("z-data/in/3/a2022/design1/brocade3/jari.bmp")));
        int width = body.getWidth();

        BufferedImage right = HorizontalRepeatGenerator.get(7, EmptyGenerator.get(240, 432));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(7, EmptyGenerator.get(240, 432)));


        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(EmptyGenerator.get(width, 12));
        inputBIs.add(PlainGenerator.get(width, 4));
        //body
        inputBIs.add(body);
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        inputBIs.add(EmptyGenerator.get(width, 12));
        //right
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

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
