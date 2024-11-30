package com.varaprasadps.no13.a2025.design2.kadiyaluplain;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.KadiyaluLayoutGenerator.kadiyalu;
import static com.varaprasadps.no13.a2025.KadiyaluPlainPlay.*;
import static java.lang.String.format;


public class KadiyaluPlainConversion {

    public static BufferedImage get(
            BufferedImage rightMeena, BufferedImage rightJari,
            BufferedImage teegaMeena, BufferedImage teegaJari,
            BufferedImage figureMeena, BufferedImage figureJari,
            BufferedImage jari
    ) throws IOException {

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightMeena, rightJari, kadiyalu(figureMeena), kadiyalu(figureJari), kadiyalu(teegaMeena), kadiyalu(teegaJari), jari));
        brocades.add(jari(rightMeena, rightJari, figureMeena, figureJari, teegaMeena, teegaJari, jari));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2025/design2/1kadiyalu-plain-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightJari = borderss(border(HorizontalRepeatGenerator.get(6, ImageIO.read(new File("d/13/in/2025/design2/border-normal/jari.bmp")))));
        BufferedImage teega = HorizontalRepeatGenerator.get(100, ImageIO.read(new File("d/13/in/2025/design2/border/teega.bmp")));
        BufferedImage figure = HorizontalRepeatGenerator.get(375, ImageIO.read(new File("d/13/in/2025/design2/border/banaras.bmp")));

        BufferedImage rightMeena = HorizontalRepeatGenerator.get(1, PlainGenerator.get(rightJari.getWidth(), rightJari.getHeight()));
        BufferedImage teegaMeena = HorizontalRepeatGenerator.get(1, PlainGenerator.get(teega.getWidth(), teega.getHeight()));
        BufferedImage figureMeena = HorizontalRepeatGenerator.get(1, PlainGenerator.get(figure.getWidth(), figure.getHeight()));

        BufferedImage jari = bodyRepeat(PlainGenerator.get(figure.getWidth(), 480));

        get(rightMeena, rightJari, teegaMeena, teega, figureMeena, figure, jari);
    }
    public static BufferedImage borderss(BufferedImage input) {
        List<BufferedImage> images = new LinkedList<>();
        images.add(input);
        images.add(PlainGenerator.get(input.getWidth(), 2));
        return AddLayoutGenerator.get(images);
    }

    public static BufferedImage bodyRepeat(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(1, image));
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(1, image));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
