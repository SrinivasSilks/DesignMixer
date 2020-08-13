package com.varaprasadps.no6.a2020.temp;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/a2020/temp/1rani-%s-%s.bmp";

        BufferedImage border = ImageIO.read(new File("z-data/in/6/6-temp/BORDER.bmp"));

        BufferedImage banaras = HorizontalRepeatGenerator.get(border.getWidth() / 60, ImageIO.read(new File("z-data/in/6/longskirt/BANARAS.bmp")));
        BufferedImage sununda = HorizontalRepeatGenerator.get(border.getWidth() / 60, ImageIO.read(new File("z-data/in/6/longskirt/SUNUNDA.bmp")));
        BufferedImage teega = HorizontalRepeatGenerator.get(border.getWidth() / 60, ImageIO.read(new File("z-data/in/6/longskirt/TEEGA.bmp")));


        BufferedImage left = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/6/design1/border1/left-border.bmp")));

        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        // Locking
        inputBIs.add(PlainGenerator.get(width, 4));
        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        // Locking
        inputBIs.add(PlainGenerator.get(width, 20));

        // Locking
        inputBIs.add(EmptyGenerator.get(width, 480));

        //right long border
        inputBIs.add(border);

        //left long border
        inputBIs.add(sununda);
        inputBIs.add(banaras);
        inputBIs.add(teega);

        // Box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
