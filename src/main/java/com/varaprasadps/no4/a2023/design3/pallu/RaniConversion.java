package com.varaprasadps.no4.a2023.design3.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/4/out/design3/pallu-rani-%s-%s.bmp";
        final BufferedImage body = ImageIO.read(new File("d/4/in/design3/pallu/pallu-anni.bmp"));
        int width = body.getWidth();

        final BufferedImage rightt = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/4/in/design3/border/right.bmp")));
        final BufferedImage leftt = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/4/in/design3/border/left.bmp"))));

        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rightt, 60, 1), width, 0);
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(leftt, 60, 1), width, 0);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));

        //body
        inputBIs.add(CutLayoutGenerator.get(body, body.getHeight() - 4).get(1));
        inputBIs.add(body);
        inputBIs.add(CutLayoutGenerator.get(body, 4).get(0));

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(right);

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));
        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));

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

    private static BufferedImage abc(BufferedImage read) {
        return CutLayoutGenerator.get(read, 360, 0);
    }

    private static BufferedImage cut(BufferedImage read) {
        return CutLayoutGenerator.get(HorizontalRepeatGenerator.get(6, read), 2000, 0);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
