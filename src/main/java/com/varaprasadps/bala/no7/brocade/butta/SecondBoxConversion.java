package com.varaprasadps.bala.no7.brocade.butta;

import com.varaprasadps.image.*;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class SecondBoxConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage jari) {
        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(rani(border));
        inputBIs.add(jari(jari));
        BufferedImage bi = ColumnRepeatGenerator.get(inputBIs);
        displayPixels(bi);
        return bi;
    }

    private static BufferedImage jari(BufferedImage jari) {
        int width = jari.getWidth();
        BufferedImage border = EmptyGenerator.get(width, 500);
        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));
        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));

        inputBIs.add(EmptyGenerator.get(width, 20));

        //left
        inputBIs.add(VerticalFlipGenerator.get(border));
        //body
        inputBIs.add(jari);
        //right
        inputBIs.add(border);

        inputBIs.add(EmptyGenerator.get(width, 20));
        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
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

    private static BufferedImage rani(BufferedImage border) {
        int width = border.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));
        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));

        inputBIs.add(EmptyGenerator.get(width, 20));

        //left
        inputBIs.add(VerticalFlipGenerator.get(border));
        inputBIs.add(EmptyGenerator.get(width, 4));
        //body
        inputBIs.add(PlainGenerator.get(width, 960));
        //right
        inputBIs.add(EmptyGenerator.get(width, 4));
        inputBIs.add(border);

        inputBIs.add(EmptyGenerator.get(width, 20));

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));
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
