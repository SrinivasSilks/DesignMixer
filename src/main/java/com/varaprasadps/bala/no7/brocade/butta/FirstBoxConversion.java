package com.varaprasadps.bala.no7.brocade.butta;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FirstBoxConversion {


    private static BufferedImage box2rani;
    private static BufferedImage box2jari;
    private static BufferedImage box4rani;
    private static BufferedImage box4jari;

    static {
        try {
            box2rani = ImageIO.read(new File("z-bala/in/7/a2020/box/rani/2rani.bmp"));
            box2jari = ImageIO.read(new File("z-bala/in/7/a2020/box/rani/2jari.bmp"));
            box4rani = ImageIO.read(new File("z-bala/in/7/a2020/box/rani/4rani.bmp"));
            box4jari = ImageIO.read(new File("z-bala/in/7/a2020/box/rani/4jari.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage get(BufferedImage border, BufferedImage jari) {
        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(rani(border));
        inputBIs.add(jari(jari));
        BufferedImage bi = LeftLayoutGenerator.get(ColumnRepeatGenerator.get(inputBIs));
        displayPixels(bi);
        return bi;
    }

    private static BufferedImage jari(BufferedImage jari) {
        int width = jari.getWidth();
        BufferedImage border = EmptyGenerator.get(width, 500);
        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));

        inputBIs.add(EmptyGenerator.get(width, 20));

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));

        //left
        inputBIs.add(VerticalFlipGenerator.get(border));
        //body
        inputBIs.add(jari);
        //right
        inputBIs.add(border);

        //box
        if(width == 2) {
            inputBIs.add(box2jari);
        } else {
            inputBIs.add(box4jari);
        }

        inputBIs.add(EmptyGenerator.get(width, 4));

        //box
        if(width == 2) {
            inputBIs.add(box2jari);
        } else {
            inputBIs.add(box4jari);
        }

        //box
        if(width == 2) {
            inputBIs.add(box2jari);
        } else {
            inputBIs.add(box4jari);
        }

        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
        displayPixels(bi);
        return bi;
    }

    private static BufferedImage rani(BufferedImage border) {
        int width = border.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

        inputBIs.add(EmptyGenerator.get(width, 20));
        //box
        if(width == 2) {
            inputBIs.add(box2rani);
        } else {
            inputBIs.add(box4rani);
        }

        //left
        inputBIs.add(VerticalFlipGenerator.get(border));
        //body
        inputBIs.add(PlainGenerator.get(width, 960));
        //right
        inputBIs.add(border);

        //box
        if(width == 2) {
            inputBIs.add(box2rani);
        } else {
            inputBIs.add(box4rani);
        }

        inputBIs.add(EmptyGenerator.get(width, 4));

        //box
        if(width == 2) {
            inputBIs.add(box2rani);
        } else {
            inputBIs.add(box4rani);
        }

        //box
        if(width == 2) {
            inputBIs.add(box2rani);
        } else {
            inputBIs.add(box4rani);
        }

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
        displayPixels(bi);
        return bi;
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

}
