package com.varaprasadps.no7.a2023.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/7/a2023/design1/pallu-rani-%s-%s.bmp";

        BufferedImage lefte = HorizontalRepeatGenerator.get(15, ImageIO.read(new File("z-data/in/7/a2023/design1/border/left.bmp")));
        BufferedImage righte = HorizontalRepeatGenerator.get(15, ImageIO.read(new File("z-data/in/7/a2023/design1/border/right.bmp")));
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(lefte, 100, 1), 1608, 0);
        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(righte, 100, 1), 1608, 0);
        BufferedImage pallu = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/7/a2023/design1/pallu/pallu-rani.bmp")));

        int width = left.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //left-border
        inputBIs.add(left);

        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //body
        inputBIs.add(pallu);
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));

        //right-border
        inputBIs.add(right);

        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

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
