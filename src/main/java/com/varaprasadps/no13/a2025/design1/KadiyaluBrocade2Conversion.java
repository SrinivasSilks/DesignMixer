package com.varaprasadps.no13.a2025.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.KadiyaluLayoutGenerator.kadiyalu;
import static com.varaprasadps.no13.a2025.Kadiyalu2Play.*;
import static java.lang.String.format;


public class KadiyaluBrocade2Conversion {

    public static BufferedImage get(
            BufferedImage rightMeena, BufferedImage rightJari,
            BufferedImage teegaMeena, BufferedImage teegaJari,
            BufferedImage figureMeena, BufferedImage figureJari,
            BufferedImage meena, BufferedImage jari
    ) throws IOException {

        BufferedImage emptyRight = EmptyGenerator.get(rightMeena.getWidth(), rightMeena.getHeight());
        BufferedImage emptyTeega = EmptyGenerator.get(teegaMeena.getWidth(), teegaMeena.getHeight());
        BufferedImage emptyFigure = EmptyGenerator.get(figureMeena.getWidth(), figureMeena.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightMeena, rightJari, kadiyalu(figureMeena), kadiyalu(figureJari), kadiyalu(teegaMeena), kadiyalu(teegaJari), jari));
        brocades.add(jari(rightMeena, rightJari, figureMeena, figureJari, teegaMeena, teegaJari, jari));
        brocades.add(nimbu(emptyRight, emptyRight, emptyFigure, emptyFigure, emptyTeega, emptyTeega, meena));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2025/design1/2kadiyalu-broc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage rightJari = border(ImageIO.read(new File("d/13/in/2025/design1/border/right.bmp")));
        BufferedImage rightMeena = PlainGenerator.get(rightJari.getWidth(), rightJari.getHeight());

        BufferedImage teegaJari = border(ImageIO.read(new File("d/13/in/2025/design1/border/teega.bmp")));
        BufferedImage teegaMeena = PlainGenerator.get(teegaJari.getWidth(), teegaJari.getHeight());
        BufferedImage figureJari = border(ImageIO.read(new File("d/13/in/2025/design1/border/banaras.bmp")));
        BufferedImage figureMeena = PlainGenerator.get(figureJari.getWidth(), figureJari.getHeight());

        BufferedImage jari = bodyRepeat(ImageIO.read(new File("d/13/in/2025/design1/brocade2/silver.bmp")));
        BufferedImage meena = bodyRepeat(ImageIO.read(new File("d/13/in/2025/design1/brocade2/meena.bmp")));

        get(rightMeena, rightJari, teegaMeena, teegaJari, figureMeena, figureJari, meena, jari);
    }


    public static BufferedImage bodyRepeat(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(6, image));
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(5, image));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
