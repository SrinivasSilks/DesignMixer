package com.varaprasadps.no3.a2020;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AllDown {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/3/a2020/alldown-%s-%s.bmp";

        final BufferedImage left = ReverseGenerator.get(EmptyGenerator.get(2, 368));
        final BufferedImage right = ReverseGenerator.get(EmptyGenerator.get(2, 368));

        final int width = 2;

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));


        //border
        inputBIs.add(left);
        // locking
        inputBIs.add(EmptyGenerator.get(width, 4));
        //chucks
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 12)));
        //body
        inputBIs.add(EmptyGenerator.get(width, 600));
        // locking
        inputBIs.add(EmptyGenerator.get(width, 8));
        //chucks
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 12)));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 4));
        //border
        inputBIs.add(right);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        // Achu
        inputBIs.add(EmptyGenerator.get(width, 8));

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
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
