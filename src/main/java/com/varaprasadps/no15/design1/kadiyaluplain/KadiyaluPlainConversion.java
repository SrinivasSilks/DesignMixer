package com.varaprasadps.no15.design1.kadiyaluplain;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no15.Kadiyalu1Play.*;
import static java.lang.String.format;

public class KadiyaluPlainConversion {

    public static BufferedImage get(
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage jari
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, jari));
        brocades.add(jari(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, jari));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/15/out/design1/1kadiyalu-plain-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightJari = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("d/15/in/design1/border/right.bmp")));
        BufferedImage teegaJari = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("d/15/in/design1/border/teega.bmp")));
        BufferedImage banarasJari = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("d/15/in/design1/border/repeat.bmp")));

        int width = rightJari.getWidth();
        BufferedImage rightSilk = PlainGenerator.get(width, 528);
        BufferedImage teegaSilk = PlainGenerator.get(width, 48);
        BufferedImage banarasSilk = PlainGenerator.get(width, 64);

        BufferedImage jari = PlainGenerator.get(width,480);

        get(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, jari);
    }


    private static BufferedImage dis(BufferedImage image) {
        return EmptyGenerator.get(image.getWidth(), image.getHeight());
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
