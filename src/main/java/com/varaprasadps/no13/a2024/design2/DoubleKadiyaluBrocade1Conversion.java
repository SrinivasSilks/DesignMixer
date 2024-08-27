package com.varaprasadps.no13.a2024.design2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.KadiyaluLayoutGenerator.kadiyalu;
import static com.varaprasadps.no13.a2024.DoubleKadiyalu3Play.*;
import static java.lang.String.format;


public class DoubleKadiyaluBrocade1Conversion {

    public static BufferedImage get(
            BufferedImage raniSilk, BufferedImage raniJari,
            BufferedImage silverSilk, BufferedImage silverJari,
            BufferedImage greenSilk, BufferedImage greenJari,
            BufferedImage teegaMeena, BufferedImage teegaJari,
            BufferedImage figureMeena, BufferedImage figureJari,
            BufferedImage rani, BufferedImage silver, BufferedImage rexona) throws IOException {

        BufferedImage emptyTeega = EmptyGenerator.get(teegaMeena.getWidth(), teegaMeena.getHeight());
        BufferedImage emptyFigure = EmptyGenerator.get(figureMeena.getWidth(), figureMeena.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(redOne(raniSilk, raniJari, kadiyalu(figureMeena), kadiyalu(figureJari), kadiyalu(teegaMeena), kadiyalu(teegaJari), rani));
        brocades.add(redTwo(raniSilk, raniJari, figureMeena, figureJari, teegaMeena, teegaJari, rani));
        brocades.add(greenOne(greenSilk, greenJari, emptyFigure, emptyFigure, emptyTeega, emptyTeega, rexona));
        brocades.add(greenTwo(greenSilk, greenJari, emptyFigure, emptyFigure, emptyTeega, emptyTeega, rexona));
        brocades.add(silver(silverSilk, silverJari, emptyFigure, emptyFigure, emptyTeega, emptyTeega, silver));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2024/design2/double-k-broc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage raniSilk = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border1/rani-silk.bmp")));
        BufferedImage raniJari = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border1/rani-jari.bmp")));
        BufferedImage silverSilk = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border1/silver-silk.bmp")));
        BufferedImage silverJari = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border1/silver-jari.bmp")));
        BufferedImage greenSilk = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border1/nimbu-silk.bmp")));
        BufferedImage greenJari = borderWithLocking(ImageIO.read(new File("d/13/in/2024/design2/border1/nimbu-jari.bmp")));

        BufferedImage teegaJari = border(ImageIO.read(new File("d/13/in/2024/design2/border1/teega.bmp")));
        BufferedImage teegaMeena = PlainGenerator.get(teegaJari.getWidth(), teegaJari.getHeight());
        BufferedImage figureJari = border(ImageIO.read(new File("d/13/in/2024/design2/border1/banaras.bmp")));
        BufferedImage figureMeena = PlainGenerator.get(figureJari.getWidth(), figureJari.getHeight());

        BufferedImage rani = bodyRepeat(ImageIO.read(new File("d/13/in/2024/design2/brocade2/meena.bmp")));
        BufferedImage silver = bodyRepeat(ImageIO.read(new File("d/13/in/2024/design2/brocade2/silver.bmp")));
        BufferedImage rexona = EmptyGenerator.get(silver.getWidth(), silver.getHeight());

        get(raniSilk, raniJari, silverSilk, silverJari, greenSilk, greenJari, teegaMeena, teegaJari, figureMeena, figureJari, rani, silver, rexona);
    }


    public static BufferedImage bodyRepeat(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(2, image));
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, ReverseGenerator.get(HorizontalRepeatGenerator.get(3, image)));
    }

    public static BufferedImage borderWithLocking(BufferedImage input) {
        List<BufferedImage> result = new LinkedList<>();
        result.add(PlainGenerator.get(input.getWidth(), 2));
        result.add(input);
        result.add(PlainGenerator.get(input.getWidth(), 4));
        BufferedImage border = ReverseGenerator.get(AddLayoutGenerator.get(result));
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(3, border));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
