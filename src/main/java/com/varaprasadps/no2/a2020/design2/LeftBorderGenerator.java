package com.varaprasadps.no2.a2020.design2;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalFlipGenerator;
import com.varaprasadps.image.StepLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LeftBorderGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/2/a2020/design2/border/left.bmp";

        BufferedImage right = ImageIO.read(new File("z-data/in/2/a2020/design2/ee/41.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        List<BufferedImage> images = CutLayoutGenerator.get(right, 32);
        BufferedImage bugada = images.get(0);
        images = CutLayoutGenerator.get(images.get(1), 280);
        BufferedImage figure = images.get(0);
        images = CutLayoutGenerator.get(images.get(1), 32);
        BufferedImage teega = images.get(0);

        inputBIs.add(bugada);
        inputBIs.add(teega);
        inputBIs.add(CutLayoutGenerator.get(StepLayoutGenerator.get(right.getWidth(), 44, 5), 20).get(0));
        inputBIs.add(figure);
        inputBIs.add(CutLayoutGenerator.get(StepLayoutGenerator.get(right.getWidth(), 44, 5), 20).get(0));
        inputBIs.add(teega);

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = HorizontalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
