package com.varaprasadps.no16.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no16.TwoPlay.*;
import static java.lang.String.format;

public class PalluConversion {

    public static BufferedImage get(
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage rani, BufferedImage jari
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani));
        brocades.add(jari(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, jari));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/16/out/design1/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rani = cuts(ImageIO.read(new File("d/16/in/design1/pallu/pallu-rani.bmp")));
        BufferedImage jari = cuts(ImageIO.read(new File("d/16/in/design1/pallu/pallu-jari.bmp")));
        int width = rani.getWidth();

        BufferedImage rightJari = cut(width, HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/15/in/design1/border/right.bmp"))));
        BufferedImage teegaJari = cut(width, HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/15/in/design1/border/teega.bmp"))));
        BufferedImage banarasJari = cut(width, HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/15/in/design1/border/repeat.bmp"))));

        BufferedImage rightSilk = PlainGenerator.get(width, 528);
        BufferedImage teegaSilk = PlainGenerator.get(width, 48);
        BufferedImage banarasSilk = PlainGenerator.get(width, 64);

        get(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani, jari);
    }

    private static BufferedImage cut(int width, BufferedImage image) {
        return CutLayoutGenerator.get(image, width, 0);
    }

    private static BufferedImage cuts(BufferedImage image) {
        return CutLayoutGenerator.get(image, 480).get(0);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
