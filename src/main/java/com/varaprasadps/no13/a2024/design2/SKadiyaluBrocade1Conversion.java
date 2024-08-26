package com.varaprasadps.no13.a2024.design2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.KadiyaluLayoutGenerator.kadiyalu;
import static com.varaprasadps.no13.a2024.SKadiyalu3Play.*;
import static java.lang.String.format;


public class SKadiyaluBrocade1Conversion {

    public static BufferedImage get(
            BufferedImage raniSilk, BufferedImage raniJari,
            BufferedImage silverSilk, BufferedImage silverJari,
            BufferedImage greenSilk, BufferedImage greenJari,
            BufferedImage teegaMeena, BufferedImage teegaJari,
            BufferedImage figureMeena, BufferedImage figureJari,
            BufferedImage rani, BufferedImage silver, BufferedImage rexona) throws IOException {

        BufferedImage emptyRight = EmptyGenerator.get(raniSilk.getWidth(), raniSilk.getHeight());
        BufferedImage emptyTeega = EmptyGenerator.get(teegaMeena.getWidth(), teegaMeena.getHeight());
        BufferedImage emptyFigure = EmptyGenerator.get(figureMeena.getWidth(), figureMeena.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(red(raniSilk, raniJari, figureMeena, figureJari, teegaMeena, teegaJari, rani));
        brocades.add(silver(silverSilk, silverJari, emptyFigure, emptyFigure, emptyTeega, emptyTeega, silver));
        brocades.add(greenOne(greenSilk, greenJari, emptyFigure, emptyFigure, emptyTeega, emptyTeega, rexona));
        brocades.add(greenTwo(greenSilk, greenJari, emptyFigure, emptyFigure, emptyTeega, emptyTeega, rexona));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2024/design2/1kadiyalu-broc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage raniSilk = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border/rani-silk.bmp")));
        BufferedImage raniJari = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border/rani-jari.bmp")));
        BufferedImage silverSilk = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border/silver-silk.bmp")));
        BufferedImage silverJari = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border/silver-jari.bmp")));
        BufferedImage greenSilk = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border/nimbu-silk.bmp")));
        BufferedImage greenJari = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border/nimbu-jari.bmp")));

        BufferedImage teegaJari = border(ImageIO.read(new File("d/13/in/2024/design2/border/teega.bmp")));
        BufferedImage teegaMeena = PlainGenerator.get(teegaJari.getWidth(), teegaJari.getHeight());
        BufferedImage figureJari = border(ImageIO.read(new File("d/13/in/2024/design2/border/banaras.bmp")));
        BufferedImage figureMeena = PlainGenerator.get(figureJari.getWidth(), figureJari.getHeight());

        BufferedImage rani = repeat(ImageIO.read(new File("d/13/in/2024/design2/brocade1/rani.bmp")));
        BufferedImage silver = repeat(ImageIO.read(new File("d/13/in/2024/design2/brocade1/silver.bmp")));
        BufferedImage rexona = repeat(ImageIO.read(new File("d/13/in/2024/design2/brocade1/rexona.bmp")));

        get(raniSilk, raniJari, silverSilk, silverJari, greenSilk, greenJari, teegaMeena, teegaJari, figureMeena, figureJari, rani, silver, rexona);
    }


    public static BufferedImage repeat(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(6, image));
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, ReverseGenerator.get(HorizontalRepeatGenerator.get(5, image)));
    }

    public static BufferedImage borderWithLocking(BufferedImage input) {
        List<BufferedImage> result = new LinkedList<>();
        result.add(PlainGenerator.get(input.getWidth(), 2));
        result.add(input);
        result.add(PlainGenerator.get(input.getWidth(), 4));
        BufferedImage border = ReverseGenerator.get(AddLayoutGenerator.get(result));
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(5, border));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
