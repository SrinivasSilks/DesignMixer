package com.varaprasadps.no14.a2025.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniReverseConversion {

    public static void main(final String[] args) throws IOException {
        String out = "d/14/out/2025/design1/anni-reverse-%s-%s.bmp";

        BufferedImage left = ReverseGenerator.get(HorizontalRepeatGenerator.get(11, VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2025/design1/border/left.bmp")))));
        BufferedImage right = ReverseGenerator.get(HorizontalRepeatGenerator.get(11, ImageIO.read(new File("d/14/in/2025/design1/border/right.bmp"))));
        BufferedImage jariBody = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("d/14/in/2025/design1/blouse/gold.bmp")));
        int width = left.getWidth();
        BufferedImage reshamBody = PlainGenerator.get(width, 480);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 24));
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));

        //body
        inputBIs.add(jariBody);
        inputBIs.add(reshamBody);

        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        inputBIs.add(right);
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 24));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 40));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
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
        BufferedImage bi = LeftLayoutGenerator.get(ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
