package com.varaprasadps.no16;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.KadiyaluLayoutGenerator.kadiyalu;

public class Kadiyalu2Play {

    public static BufferedImage nimbu(
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage body
    ) {

        int width = rightJari.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 4));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(PlainGenerator.get(width, 2));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));

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
        inputBIs.add(EmptyGenerator.get(width, 4));
        //locking
        inputBIs.add(PlainGenerator.get(width, 2));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
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
        return AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
    }

    public static BufferedImage jari(
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage body
    ) {

        int width = rightJari.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 2)));
        inputBIs.add(PlainGenerator.get(width, 2));
        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));

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
        inputBIs.add(PlainGenerator.get(width, 4));
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 2)));
        inputBIs.add(PlainGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 4));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
    }


    public static BufferedImage rani(
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage body
    ) {

        int width = rightJari.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //jamudu
        inputBIs.add(kadiyalu(PlainGenerator.get(width, 4)));
        //locking
        inputBIs.add(PlainGenerator.get(width, 2));
        inputBIs.add(PlainGenerator.get(width, 2));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));

        //left
        inputBIs.add(kadiyalu(banarasJari));
        inputBIs.add(kadiyalu(banarasSilk));
        inputBIs.add(kadiyalu(teegaJari));
        inputBIs.add(kadiyalu(teegaSilk));
        //allover
        inputBIs.add(body);
        //right
        inputBIs.add(rightJari);
        inputBIs.add(rightSilk);

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));
        //locking
        inputBIs.add(PlainGenerator.get(width, 2));
        inputBIs.add(PlainGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
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
        return AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
    }

    public static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }


    public static BufferedImage empty(int width) {
        return EmptyGenerator.get(width, 12);
    }

    public static BufferedImage getBorder(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    public static BufferedImage getBrocade(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.printf("Brocade => Width : %s, Height : %s%n", inputs.get(0).getWidth(), inputs.get(0).getHeight());
        }
        BufferedImage bufferedImage = ColumnRepeatGenerator.get(inputs);
        System.out.printf("final Brocade => Width : %s, Height : %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());

        return bufferedImage;
    }

    public static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
