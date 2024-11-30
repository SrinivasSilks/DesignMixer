package com.varaprasadps.no13.a2025.design2.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no13.a2025.ThreePlay.*;
import static java.lang.String.format;


public class PalluConversion {

    private static final int SIZE = 1640;

    public static BufferedImage get(
            BufferedImage raniSilk, BufferedImage raniJari,
            BufferedImage greenSilk, BufferedImage greenJari,
            BufferedImage silverSilk, BufferedImage silverJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage banarsSilk, BufferedImage banrasJari,
            BufferedImage rani, BufferedImage green, BufferedImage silver
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(raniSilk, raniJari, banarsSilk, banrasJari, teegaSilk, teegaJari, rani));
        brocades.add(jari(greenSilk, greenJari, dis(banarsSilk), dis(banrasJari), dis(teegaSilk), dis(teegaJari), green));
        brocades.add(nimbu(silverSilk, silverJari, dis(banarsSilk), dis(banrasJari), dis(teegaSilk), dis(teegaJari), silver));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2025/design2/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage raniSilk = borderR(ImageIO.read(new File("d/13/in/2025/design2/border/rani-silk.bmp")));
        BufferedImage raniJari = borderR(ImageIO.read(new File("d/13/in/2025/design2/border/rani-jari.bmp")));
        BufferedImage greenSilk = borderR(ImageIO.read(new File("d/13/in/2025/design2/border/green-silk.bmp")));
        BufferedImage greenJari = borderR(ImageIO.read(new File("d/13/in/2025/design2/border/green-jari.bmp")));
        BufferedImage silverSilk = borderR(ImageIO.read(new File("d/13/in/2025/design2/border/silver-silk.bmp")));
        BufferedImage silverJari = borderR(ImageIO.read(new File("d/13/in/2025/design2/border/silver-jari.bmp")));

        BufferedImage teegaJari = borderL(ImageIO.read(new File("d/13/in/2025/design2/border/teega.bmp")));
        BufferedImage teegaSilk = PlainGenerator.get(teegaJari.getWidth(), teegaJari.getHeight());
        BufferedImage banarasJari = borderL(ImageIO.read(new File("d/13/in/2025/design2/border/banaras.bmp")));
        BufferedImage banarasSilk = PlainGenerator.get(banarasJari.getWidth(), banarasJari.getHeight());

        BufferedImage rani = body(ImageIO.read(new File("d/13/in/2025/design2/pallu/pallu-rani.bmp")));
        BufferedImage green = body(ImageIO.read(new File("d/13/in/2025/design2/pallu/pallu-green.bmp")));
        BufferedImage silver = body(ImageIO.read(new File("d/13/in/2025/design2/pallu/pallu-silver.bmp")));

        get(raniSilk, raniJari, greenSilk, greenJari, silverSilk, silverJari, teegaSilk, teegaJari, banarasSilk, banarasJari, rani, green, silver);
    }

    public static BufferedImage borderR(BufferedImage input) {
        List<BufferedImage> images = new LinkedList<>();
        images.add(input);
        images.add(PlainGenerator.get(input.getWidth(), 2));
        BufferedImage result = ReverseGenerator.get(AddLayoutGenerator.get(images));
        return abc(HorizontalRepeatGenerator.get((SIZE / result.getWidth()) + 1, result));
    }

    private static BufferedImage borderL(BufferedImage input) {
        return abc(HorizontalRepeatGenerator.get((SIZE / input.getWidth()) + 1, input));
    }

    private static BufferedImage abc(BufferedImage input) {
        return CutLayoutGenerator.get(input, SIZE, 0);
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
