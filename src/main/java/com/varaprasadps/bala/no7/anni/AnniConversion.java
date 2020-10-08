package com.varaprasadps.bala.no7.anni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/7/a2020/anni-%s-%s.bmp";

        BufferedImage right = ImageIO.read(new File("z-bala/in/7/a2020/border/border.bmp"));
        final BufferedImage left = VerticalFlipGenerator.get(right);
        int width = right.getWidth();
        BufferedImage body = PlainGenerator.get(width, 960);

        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

        inputBIs.add(EmptyGenerator.get(width, 20));

        //box
        inputBIs.add(EmptyGenerator.get(width, 8));

        //left
        inputBIs.add(left);
        inputBIs.add(EmptyGenerator.get(width, 4));
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(EmptyGenerator.get(width, 4));
        inputBIs.add(right);

        //box
        inputBIs.add(EmptyGenerator.get(width, 8));

        inputBIs.add(EmptyGenerator.get(width, 20));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

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
