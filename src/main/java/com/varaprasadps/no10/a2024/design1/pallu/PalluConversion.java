package com.varaprasadps.no10.a2024.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no10.a2022.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, VerticalFlipGenerator.get(border), rani));
        brocades.add(jari(getBorder(border), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/10/out/2024/design1/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage palluJari = VerticalRepeatGenerator.get(2, ImageIO.read(new File("d/10/in/2024/design1/pallu/pallu-jari.bmp")));
        BufferedImage palluRani = PlainGenerator.get(palluJari.getWidth(), palluJari.getHeight());

        BufferedImage rightt = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/10/in/2024/design1/border/left.bmp")));
        BufferedImage border = CutLayoutGenerator.get(CutLayoutGenerator.get(rightt, 180, 1), palluJari.getWidth(), 0);

        get(border, palluRani, palluJari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
