package com.varaprasadps.bala.no7.brocade.chucks;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FirstBoxConversion {


    private static BufferedImage box2rani;
    private static BufferedImage box4rani;

    static {
        try {
            box2rani = ImageIO.read(new File("z-bala/in/7/a2020/box/plain/2rani.bmp"));
            box4rani = ImageIO.read(new File("z-bala/in/7/a2020/box/plain/4rani.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage get(BufferedImage border) {
        BufferedImage bi = LeftLayoutGenerator.get(rani(border));
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
