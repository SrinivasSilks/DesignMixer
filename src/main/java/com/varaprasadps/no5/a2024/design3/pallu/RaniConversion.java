package com.varaprasadps.no5.a2024.design3.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.CutLayoutGenerator.get;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/5/out/2024/design3/p-rani-%s-%s.bmp";

        final BufferedImage rightf = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/5/in/2024/design3/border/right.bmp")));
        final BufferedImage leftf = HorizontalRepeatGenerator.get(10, VerticalFlipGenerator.get(ImageIO.read(new File("d/5/in/2024/design3/border/left.bmp"))));

        final BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rightf, 180, 1), 1600, 0);
        final BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(leftf, 180, 1), 1600, 0);
        int width = right.getWidth();

        final BufferedImage body = PlainGenerator.get(width, 720);


        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //Khali
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        inputBIs.add(left);
        inputBIs.add(body);
        inputBIs.add(right);

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(ReverseGenerator.get(get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kali
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
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
