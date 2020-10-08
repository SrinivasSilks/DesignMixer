package com.varaprasadps.asujay;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "sujay/anni-%s-%s.bmp";

        BufferedImage border = ImageIO.read(new File("sujay/input/border.bmp"));
        int width = border.getWidth();
        BufferedImage body = PlainGenerator.get(width, 960);

        List<BufferedImage> inputBIs = new LinkedList<>();

        //////

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));
        //chucks
        inputBIs.add(EmptyGenerator.get(width, 20));
        //box
        inputBIs.add(EmptyGenerator.get(width, 8));
        //border
        inputBIs.add(HorizontalFlipGenerator.get(StepLayoutGenerator.get(width)));
        inputBIs.add(VerticalFlipGenerator.get(border));

        //body
        inputBIs.add(PlainGenerator.get(width, 960));
        //border
        inputBIs.add(border);
        inputBIs.add(HorizontalFlipGenerator.get(StepLayoutGenerator.get(width)));
        //box
        inputBIs.add(EmptyGenerator.get(width, 8));
        //kali

        inputBIs.add(EmptyGenerator.get(width, 20));
        //achhu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

        //////

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = LeftLayoutGenerator.get(HorizontalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
