package com.varaprasadps.no14.a2024;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ReshamUpConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/14/out/2024/design1/resham-up-%s-%s.bmp";

        BufferedImage left = EmptyGenerator.get(20, 304);
        BufferedImage right = EmptyGenerator.get(20, 520);

        int width = left.getWidth();
        BufferedImage jariBody = EmptyGenerator.get(width, 480);
        BufferedImage reshamBody = ReverseGenerator.get(EmptyGenerator.get(width, 480));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(BlackGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(BlackGenerator.get(width, 8));

        //jamudu
        inputBIs.add(BlackGenerator.get(width, 24));
        inputBIs.add(left);
        //locking
        inputBIs.add(BlackGenerator.get(width, 8));

        //body
        inputBIs.add(jariBody);
        inputBIs.add(reshamBody);

        //locking
        inputBIs.add(BlackGenerator.get(width, 8));
        inputBIs.add(right);
        //jamudu
        inputBIs.add(BlackGenerator.get(width, 24));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 40));
        //mispick
        inputBIs.add(BlackGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(BlackGenerator.get(width, 12));

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
