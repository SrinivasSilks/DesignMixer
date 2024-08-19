package com.varaprasadps.no14.a2024.aug17;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KonguConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/14/out/2024/aug17/kongu-%s-%s.bmp";

        int width = 2;
        BufferedImage left = EmptyGenerator.get(width, 304);
        BufferedImage right = EmptyGenerator.get(width, 520);

        BufferedImage jariBody = EmptyGenerator.get(width, 480);
        BufferedImage reshamBody = KonguLayoutGenerator.get(120);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 24));
        inputBIs.add(left);
        //locking
        inputBIs.add(KonguLayoutGenerator.get(2));

        //body
        inputBIs.add(jariBody);
        inputBIs.add(reshamBody);

        //locking
        inputBIs.add(KonguLayoutGenerator.get(2));
        inputBIs.add(right);
        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 24));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 40));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
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
