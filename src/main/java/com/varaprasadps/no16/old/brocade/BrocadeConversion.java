package com.varaprasadps.no16.old.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no16.ThreePlay.jari;
import static com.varaprasadps.no16.ThreePlay.nimbu;
import static com.varaprasadps.no16.ThreePlay.rani;
import static com.varaprasadps.no16.ThreePlay.*;
import static java.lang.String.format;

public class BrocadeConversion {

    public static BufferedImage get(
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage rani, BufferedImage jari, BufferedImage nimbu
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani));
        brocades.add(jari(dis(rightSilk), dis(rightJari), dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), nimbu));
        brocades.add(nimbu(dis(rightSilk), dis(rightJari), dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), jari));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/16/out/design1/brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage jari = body(3, ImageIO.read(new File("d/16/in/design1/brocade1/jari.bmp")));
        BufferedImage nimbu = body(3, ImageIO.read(new File("d/16/in/design1/brocade1/nimbu.bmp")));
        BufferedImage rani = StepLayoutGenerator.get(jari.getWidth(), 480 / 6, 6);
        int width = rani.getWidth();

        BufferedImage rightJari = border(2, ImageIO.read(new File("d/16/in/design1/border/right.bmp")));
        BufferedImage teegaJari = border(2, ImageIO.read(new File("d/16/in/design1/border/teega.bmp")));
        BufferedImage banarasJari = border(2, ImageIO.read(new File("d/16/in/design1/border/repeat.bmp")));

        BufferedImage rightSilk = PlainGenerator.get(width, 528);
        BufferedImage teegaSilk = PlainGenerator.get(width, 48);
        BufferedImage banarasSilk = PlainGenerator.get(width, 64);

        get(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani, jari, nimbu);
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
