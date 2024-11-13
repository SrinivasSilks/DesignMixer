package com.varaprasadps.no17.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no17.ThreePlay.*;
import static java.lang.String.format;

public class BrocadeConversion {

    public static BufferedImage get(
            BufferedImage right, BufferedImage left,
            BufferedImage rani, BufferedImage jari, BufferedImage nimbu
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, rani));
        brocades.add(jari(dis(right), dis(left), nimbu));
        brocades.add(nimbu(dis(right), dis(left), jari));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/17/out/design1/brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        int width = 60;
        BufferedImage right = PlainGenerator.get(width, 580);
        BufferedImage left = PlainGenerator.get(width, 300);
        BufferedImage rani = PlainGenerator.get(width, 480);
        BufferedImage jari = PlainGenerator.get(width, 480);
        BufferedImage nimbu = PlainGenerator.get(width, 480);
        get(right, left, rani, jari, nimbu);
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

    private static BufferedImage dis(BufferedImage image) {
        return EmptyGenerator.get(image.getWidth(), image.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
