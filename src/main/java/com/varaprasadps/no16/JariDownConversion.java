package com.varaprasadps.no16;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariDownConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/16/out/design1/jari-down-%s-%s.bmp";

        int width = 30;
        
        BufferedImage rightSilk = BlackGenerator.get(width, 528);
        BufferedImage rightJari = EmptyGenerator.get(width, 528);
        BufferedImage teegaSilk = BlackGenerator.get(width, 48);
        BufferedImage teegaJari = EmptyGenerator.get(width, 48);
        BufferedImage banarasSilk = BlackGenerator.get(width, 64);
        BufferedImage banarasJari = EmptyGenerator.get(width, 64);
        BufferedImage body = EmptyGenerator.get(width, 480);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //jamudu
        inputBIs.add(BlackGenerator.get(width, 4));
        //locking
        inputBIs.add(BlackGenerator.get(width, 4));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(BlackGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));

        //left
        inputBIs.add(banarasJari);
        inputBIs.add(banarasSilk);
        inputBIs.add(teegaJari);
        inputBIs.add(teegaSilk);
        //allover
        inputBIs.add(body);
        //right
        inputBIs.add(rightJari);
        inputBIs.add(rightSilk);

        //jamudu
        inputBIs.add(BlackGenerator.get(width, 4));
        //locking
        inputBIs.add(BlackGenerator.get(width, 4));
        //mispick
        inputBIs.add(BlackGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 4));

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
