package com.varaprasadps.no1.a2020k.design2;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.ReverseGenerator;
import com.varaprasadps.image.StepLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/1/a2020k/design2/border/border.bmp";

        BufferedImage right = ReverseGenerator.get(ImageIO.read(new File("z-data/in/1/a2020k/design2/border/e.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        List<BufferedImage> images = CutLayoutGenerator.get(right, 32);
        BufferedImage bugada = images.get(0);
        images = CutLayoutGenerator.get(images.get(1), 32);
        BufferedImage teega = images.get(0);
        images = CutLayoutGenerator.get(images.get(1), 24);
        BufferedImage figure = images.get(1);

        inputBIs.add(bugada);
        inputBIs.add(StepLayoutGenerator.get(right.getWidth(),4, 6));
        inputBIs.add(figure);
        inputBIs.add(teega);

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
