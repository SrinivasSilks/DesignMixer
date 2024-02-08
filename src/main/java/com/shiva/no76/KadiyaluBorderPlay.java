package com.shiva.no76;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluBorderPlay {

    public static BufferedImage jari(BufferedImage left, BufferedImage right) {

        int width = left.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //kali
        inputBIs.add(EmptyGenerator.get(width, 96));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 4));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));

        //left
        inputBIs.add(VerticalFlipGenerator.get(left));
        //body
        inputBIs.add(EmptyGenerator.get(width, 480));
        //right
        inputBIs.add(right);

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 2));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 4));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 96));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
    }

    public static BufferedImage rani(BufferedImage left, BufferedImage right) {

        int width = left.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //kali
        inputBIs.add(EmptyGenerator.get(width, 96));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 4));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));

        //left
        inputBIs.add(VerticalFlipGenerator.get(KadiyaluLayoutGenerator.kadiyalu(left)));
        //body
        inputBIs.add(EmptyGenerator.get(width, 480));
        //right
        inputBIs.add(right);

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 2));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 4));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 96));

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
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }


    public static BufferedImage empty(int width) {
        return EmptyGenerator.get(width, 12);
    }

    public static BufferedImage getBorder(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    public static BufferedImage getBrocade(List<BufferedImage> inputs) {
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
