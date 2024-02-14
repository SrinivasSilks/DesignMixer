package com.varaprasadps.no3.a2024;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Kadiyalu1Play {

    public static BufferedImage jari1(BufferedImage right, BufferedImage left, BufferedImage body, BufferedImage checks) {
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();


        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));

        //left
        inputBIs.add(left);
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 8));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(PlainGenerator.get(width, 4));
        //right part1
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(0));

        //body
        inputBIs.add(body);
        //salari
        inputBIs.add(checks);

        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        //right part2
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(1));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 8));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }

    public static BufferedImage rani1(BufferedImage right, BufferedImage left, BufferedImage body, BufferedImage checks) {

        int width = right.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(ReverseGenerator.get(AchuLayoutGenerator.get(width, 8)));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));

        //left border
        inputBIs.add(left);

        //jamudu
        inputBIs.add(KadiyaluLayoutGenerator.kadiyalu(PlainGenerator.get(width, 8)));

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(PlainGenerator.get(width, 4));
        // Right part 1
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(0));
        //body
        inputBIs.add(body);
        //salari
        inputBIs.add(checks);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        inputBIs.add(PlainGenerator.get(width, 4));
        // Right part 2
        inputBIs.add(CutLayoutGenerator.get(right, 264).get(1));

        //jamudu
        inputBIs.add(PlainGenerator.get(width, 8));

        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));


        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
    }

    public static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }


    public static BufferedImage empty(int width) {
        return EmptyGenerator.get(width, 12);
    }

    public static BufferedImage getBorder1(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    public static BufferedImage getBrocade1(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(String.format("Brocade => Width : %s, Height : %s", inputs.get(0).getWidth(), inputs.get(0).getHeight()));
        }
        BufferedImage bufferedImage = ColumnRepeatGenerator.get(inputs);
        System.out.println(String.format("final Brocade => Width : %s, Height : %s", bufferedImage.getWidth(), bufferedImage.getHeight()));

        return bufferedImage;
    }

    public static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
