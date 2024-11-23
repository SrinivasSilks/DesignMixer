package com.varaprasadps.no15.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no15.TwoPlay.*;
import static java.lang.String.format;

public class PalluConversion {

    public static BufferedImage get(
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage anni, BufferedImage jari
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, anni));
        brocades.add(jari(dis(rightSilk), dis(rightJari), dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), jari));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/15/out/design1/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage jari = CutLayoutGenerator.get(ImageIO.read(new File("d/15/in/design1/pallu/pallu-jari.bmp")), 480).get(0);
        BufferedImage anni = CutLayoutGenerator.get(ImageIO.read(new File("d/15/in/design1/pallu/pallu-rani.bmp")), 480).get(0);
        int width = jari.getWidth();

        BufferedImage right = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/15/in/design1/border/right.bmp")));
        BufferedImage teega = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/15/in/design1/border/teega.bmp")));
        BufferedImage figure = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/15/in/design1/border/repeat.bmp")));

        BufferedImage rightJari = CutLayoutGenerator.get(CutLayoutGenerator.get(right, 60, 1), width, 0);
        BufferedImage rightSilk = PlainGenerator.get(rightJari.getWidth(), rightJari.getHeight());

        BufferedImage teegaJari = CutLayoutGenerator.get(CutLayoutGenerator.get(teega, 60, 1), width, 0);
        BufferedImage teegaSilk = PlainGenerator.get(teegaJari.getWidth(), teegaJari.getHeight());

        BufferedImage banarasJari = CutLayoutGenerator.get(CutLayoutGenerator.get(figure, 60, 1), width, 0);
        BufferedImage banarasSilk = PlainGenerator.get(banarasJari.getWidth(), banarasJari.getHeight());

        get(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, anni, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

    private static BufferedImage dis(BufferedImage image) {
        return EmptyGenerator.get(image.getWidth(), image.getHeight());
    }

}
