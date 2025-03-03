package com.varaprasadps.no16.design3.blouse;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no16.TwoPlay.*;
import static java.lang.String.format;

public class BlouseConversion {

    public static BufferedImage get(
            BufferedImage raniSilk, BufferedImage raniJari,
            BufferedImage silverSilk, BufferedImage silverJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage rani, BufferedImage jari
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        System.out.println("BROCADE");
        brocades.add(rani(raniSilk, raniJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani));
        brocades.add(jari(silverSilk, silverJari, dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), jari));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/16/out/design3/self-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage raniSilk = borderR(ImageIO.read(new File("d/16/in/design3/border/rani-silk.bmp")));
        BufferedImage raniJari = borderR(ImageIO.read(new File("d/16/in/design3/border/rani-jari.bmp")));
        BufferedImage silverSilk = borderR(ImageIO.read(new File("d/16/in/design3/border/silver-silk.bmp")));
        BufferedImage silverJari = borderR(ImageIO.read(new File("d/16/in/design3/border/silver-jari.bmp")));

        BufferedImage teegaJari = borderL(ImageIO.read(new File("d/16/in/design3/border/teega.bmp")));
        BufferedImage teegaSilk = PlainGenerator.get(teegaJari.getWidth(), teegaJari.getHeight());
        BufferedImage banarasJari = borderL(ImageIO.read(new File("d/16/in/design3/border/repeat.bmp")));
        BufferedImage banarasSilk = PlainGenerator.get(banarasJari.getWidth(), banarasJari.getHeight());


        BufferedImage rani = blouse(ImageIO.read(new File("d/16/in/design3/brocade1/meena.bmp")));
        BufferedImage jari = blouse(ImageIO.read(new File("d/16/in/design3/brocade1/jari.bmp")));

        get(raniSilk, raniJari, silverSilk, silverJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani, jari);
    }

    public static int SIZE = 2400;
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

    private static BufferedImage dis(BufferedImage image) {
        return EmptyGenerator.get(image.getWidth(), image.getHeight());
    }

    private static BufferedImage blouse(BufferedImage input) {
        return abc(HorizontalRepeatGenerator.get((SIZE / input.getWidth()) + 1, input));
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
