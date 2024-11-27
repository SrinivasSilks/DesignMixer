package com.varaprasadps.no16.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no16.ThreePlay.*;
import static java.lang.String.format;

public class PalluConversion {

    private static final int SIZE = 1200;

    public static BufferedImage get(
            BufferedImage raniSilk, BufferedImage raniJari,
            BufferedImage greenSilk, BufferedImage greenJari,
            BufferedImage silverSilk, BufferedImage silverJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage rani, BufferedImage green, BufferedImage silver
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(raniSilk, raniJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani));
        brocades.add(jari(greenSilk, greenJari, dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), green));
        brocades.add(nimbu(silverSilk, silverJari, dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), silver));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/16/out/design1/pallu-testing-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage raniSilk = borderR(ImageIO.read(new File("d/16/in/design1/border/rani-silk.bmp")));
        BufferedImage raniJari = borderR(ImageIO.read(new File("d/16/in/design1/border/rani-jari.bmp")));
        BufferedImage greenSilk = borderR(ImageIO.read(new File("d/16/in/design1/border/green-silk.bmp")));
        BufferedImage greenJari = borderR(ImageIO.read(new File("d/16/in/design1/border/green-jari.bmp")));
        BufferedImage silverSilk = borderR(ImageIO.read(new File("d/16/in/design1/border/silver-silk.bmp")));
        BufferedImage silverJari = borderR(ImageIO.read(new File("d/16/in/design1/border/silver-jari.bmp")));

        BufferedImage teegaJari = borderL(ImageIO.read(new File("d/16/in/design1/border/teega.bmp")));
        BufferedImage teegaSilk = PlainGenerator.get(teegaJari.getWidth(), teegaJari.getHeight());
        BufferedImage banarasJari = borderL(ImageIO.read(new File("d/16/in/design1/border/repeat.bmp")));
        BufferedImage banarasSilk = PlainGenerator.get(banarasJari.getWidth(), banarasJari.getHeight());

        BufferedImage rani = body(ImageIO.read(new File("d/16/in/design1/testing/border.bmp")));
        BufferedImage green = body(ImageIO.read(new File("d/16/in/design1/testing/meena.bmp")));
        BufferedImage silver = body(ImageIO.read(new File("d/16/in/design1/testing/silver.bmp")));

        get(raniSilk, raniJari, greenSilk, greenJari, silverSilk, silverJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani, green, silver);
    }

    public static BufferedImage borderR(BufferedImage input) {
        List<BufferedImage> images = new LinkedList<>();
        images.add(input);
        images.add(PlainGenerator.get(input.getWidth(), 2));
        BufferedImage result = ReverseGenerator.get(AddLayoutGenerator.get(images));
        return HorizontalRepeatGenerator.get(SIZE / result.getWidth(), result);
    }

    private static BufferedImage borderL(BufferedImage input) {
        return HorizontalRepeatGenerator.get(SIZE / input.getWidth(), input);
    }

    private static BufferedImage body(BufferedImage input) {
        return HorizontalRepeatGenerator.get(SIZE / input.getWidth(), VerticalRepeatGenerator.get(480 / input.getHeight(), input));
    }

    private static BufferedImage dis(BufferedImage image) {
        return EmptyGenerator.get(image.getWidth(), image.getHeight());
    }

    private static BufferedImage body(int repeat, BufferedImage image) {
        return HorizontalRepeatGenerator.get(repeat, image);
    }

    private static BufferedImage border(int repeat, BufferedImage image) {
        return HorizontalRepeatGenerator.get(repeat, image);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
