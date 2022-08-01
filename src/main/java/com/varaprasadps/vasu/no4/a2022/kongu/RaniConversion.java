package com.varaprasadps.vasu.no4.a2022.kongu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/4/a2022/p-rani-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/4/a2022/border/border.bmp")));
        BufferedImage left = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/4/a2022/border/border.bmp")));
        int width = left.getWidth();
        final BufferedImage pallu = PlainGenerator.get(width, 480);

        List<BufferedImage> inputBIs = new LinkedList<>();

        // Board Khali
        inputBIs.add(EmptyGenerator.get(width, 128));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

        inputBIs.add(VerticalFlipGenerator.get(left));
        inputBIs.add(pallu);
        //locking right
        inputBIs.add(PlainGenerator.get(width, 16));
        inputBIs.add(right);

        //Box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 80)));

        // Board Khali
        inputBIs.add(EmptyGenerator.get(width, 256));


        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
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
