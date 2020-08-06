package com.varaprasadps.no4.a2020.design2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/4/a2020/design2/border/border.bmp";

        BufferedImage border = CutLayoutGenerator.get(ImageIO.read(new File("z-data/in/4/a2020/design2/edit/2.border.bmp")), 656).get(1);
        BufferedImage ten = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/4/a2020/design2/edit/10.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        List<BufferedImage> images = CutLayoutGenerator.get(border, 420);
        List<BufferedImage> tens = CutLayoutGenerator.get(ten, 746);

        inputBIs.add(CutLayoutGenerator.get(tens.get(0), 136).get(0));
        inputBIs.add(HorizontalFlipGenerator.get(StepLayoutGenerator.get(border.getWidth(), 53, 5)));
        inputBIs.add(CutLayoutGenerator.get(tens.get(1), 68).get(1));
        inputBIs.add(CutLayoutGenerator.get(images.get(1), 260).get(0));
        inputBIs.add(CutLayoutGenerator.get(tens.get(1),1).get(1));

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
