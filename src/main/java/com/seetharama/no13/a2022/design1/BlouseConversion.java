package com.seetharama.no13.a2022.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.CutLayoutGenerator.get;

public class BlouseConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/13/a2022/design1/1BLOUSE.bmp";

        int width = 10;
        List<BufferedImage> inputBIs = new LinkedList<>();

        //mispick
        inputBIs.add(get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        //left border
        inputBIs.add(EmptyGenerator.get(width, 320));

        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 32)));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 32)));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 32)));

        //body
        inputBIs.add(PlainGenerator.get(width, 480));
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //border
        inputBIs.add(EmptyGenerator.get(width, 592));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //mispick
        inputBIs.add(ReverseGenerator.get(get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(EmptyGenerator.get(width, 1));
        inputBIs.add(EmptyGenerator.get(width, 7));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = LeftLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
