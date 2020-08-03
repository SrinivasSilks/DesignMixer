package com.varaprasadps.no4.a2020;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/4/a2020/border.bmp";

        BufferedImage five = ReverseGenerator.get(ImageIO.read(new File("z-data/in/4/a2020/edit/5.bmp")));
        BufferedImage eleven = ImageIO.read(new File("z-data/in/4/a2020/edit/11.bmp"));
        BufferedImage peacock = HorizontalRepeatGenerator.get(2, CutLayoutGenerator.get(CutLayoutGenerator.get(eleven, 535).get(1), 198).get(0));

        List<BufferedImage> inputBIs = new LinkedList<>();

        List<BufferedImage> images = CutLayoutGenerator.get(five, 512);
        inputBIs.add(images.get(0));
        inputBIs.add(peacock);
        inputBIs.add(CutLayoutGenerator.get(VerticalFlipGenerator.get(StepLayoutGenerator.get(400, 2, 4)), 7).get(0));
        inputBIs.add(CutLayoutGenerator.get(peacock, 197).get(1));

        inputBIs.add(CutLayoutGenerator.get(images.get(1), 206).get(1));


        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
