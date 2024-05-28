package com.varaprasadps.no13.a2024;

import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.StepLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no13.a2024.ThreePlay.*;
import static java.lang.String.format;


public class BrocadeConversion {

    public static BufferedImage get(BufferedImage rightJari, BufferedImage leftFigureJari, BufferedImage leftTeegaJari, BufferedImage anni, BufferedImage jari, BufferedImage nimbu) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightJari, leftFigureJari, leftTeegaJari, anni));
        brocades.add(jari(nimbu));
        brocades.add(nimbu(jari));

        for (int i = 0; i < brocades.size(); i++) {
            BufferedImage bufferedImage = brocades.get(i);
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2024/design1/1-brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage nimbu = HorizontalRepeatGenerator.get(9, ImageIO.read(new File("d/13/in/2024/design1/brocade1/nimbu.bmp")));
        BufferedImage jari = HorizontalRepeatGenerator.get(9, ImageIO.read(new File("d/13/in/2024/design1/brocade1/silver.bmp")));
        BufferedImage anni = HorizontalRepeatGenerator.get(9, ImageIO.read(new File("d/13/in/2024/design1/brocade1/rani.bmp")));

        BufferedImage rightJari = HorizontalRepeatGenerator.get(20, ImageIO.read(new File("d/13/in/2024/design1/border/right.bmp")));
        BufferedImage teega = HorizontalRepeatGenerator.get(20, ImageIO.read(new File("d/13/in/2024/design1/border/teega.bmp")));
        BufferedImage figure = HorizontalRepeatGenerator.get(20, ImageIO.read(new File("d/13/in/2024/design1/border/banaras.bmp")));

        get(rightJari, figure, teega, anni, jari, nimbu);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
