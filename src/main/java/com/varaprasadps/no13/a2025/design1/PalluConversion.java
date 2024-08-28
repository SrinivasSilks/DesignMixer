package com.varaprasadps.no13.a2025.design1;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no13.a2025.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

    public static BufferedImage get(BufferedImage rightJari, BufferedImage leftFigureJari, BufferedImage leftTeegaJari, BufferedImage anni, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightJari, leftFigureJari, leftTeegaJari, anni));
        brocades.add(jari(jari));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2025/design1/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage jari = CutLayoutGenerator.get(ImageIO.read(new File("d/13/in/2025/design1/pallu/pallu-jari.bmp")), 480).get(0);
        BufferedImage anni = CutLayoutGenerator.get(ImageIO.read(new File("d/13/in/2025/design1/pallu/pallu-rani.bmp")), 480).get(0);
        int width = jari.getWidth();

        BufferedImage right = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/13/in/2025/design1/border/right.bmp")));
        BufferedImage teega = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/13/in/2025/design1/border/teega.bmp")));
        BufferedImage figure = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/13/in/2025/design1/border/banaras.bmp")));

        BufferedImage rightJari = CutLayoutGenerator.get(CutLayoutGenerator.get(right, 60, 1), width, 0);
        BufferedImage leftTeegaJari = CutLayoutGenerator.get(CutLayoutGenerator.get(teega, 60, 1), width, 0);
        BufferedImage leftFigureJari = CutLayoutGenerator.get(CutLayoutGenerator.get(figure, 60, 1), width, 0);

        get(rightJari, leftFigureJari, leftTeegaJari, anni, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
