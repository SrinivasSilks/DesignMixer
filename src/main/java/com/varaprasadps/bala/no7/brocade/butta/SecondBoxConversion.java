package com.varaprasadps.bala.no7.brocade.butta;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SecondBoxConversion {


    private static BufferedImage box2nimbu;
    private static BufferedImage box2jari;

    static {
        try {
            box2nimbu = ImageIO.read(new File("z-bala/in/7/a2020/box/nimbu/2nimbu.bmp"));
            box2jari = ImageIO.read(new File("z-bala/in/7/a2020/box/nimbu/2jari.bmp"));
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
        inputBIs.add(box2jari);

        //left
        inputBIs.add(VerticalFlipGenerator.get(border));
        //body
        inputBIs.add(jari);
        //right
        inputBIs.add(border);

        //box
        inputBIs.add(box2jari);

        inputBIs.add(EmptyGenerator.get(width, 4));
        inputBIs.add(box2jari);
        inputBIs.add(box2jari);


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
        inputBIs.add(box2nimbu);

        //left
        inputBIs.add(VerticalFlipGenerator.get(border));
        //body
        inputBIs.add(PlainGenerator.get(width, 960));
        //right
        inputBIs.add(border);

        //box
        inputBIs.add(box2nimbu);

        inputBIs.add(EmptyGenerator.get(width, 4));
        inputBIs.add(box2nimbu);
        inputBIs.add(box2nimbu);

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
