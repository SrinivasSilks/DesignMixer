package com.varaprasadps.no3.a2020k;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/3/a2020k/design1/border.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/3/a2020k/design1/sdf.bmp")));

        List<BufferedImage> images = CutLayoutGenerator.get(right, 32);
        BufferedImage bugada = images.get(0);
        List<BufferedImage> imagestwo = CutLayoutGenerator.get(images.get(1), 32);
        BufferedImage teega = imagestwo.get(0);

        imagestwo = CutLayoutGenerator.get(imagestwo.get(1), 280);
        BufferedImage figure = imagestwo.get(0);


        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(bugada);
        inputBIs.add(StepLayoutGenerator.get(bugada.getWidth(), 4, 6));
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
