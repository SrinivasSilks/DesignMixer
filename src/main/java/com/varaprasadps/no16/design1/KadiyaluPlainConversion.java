package com.varaprasadps.no16.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no16.Kadiyalu1Play.*;
import static java.lang.String.format;

public class KadiyaluPlainConversion {

    private static final int SIZE = 600;

    public static BufferedImage get(
            BufferedImage raniSilk, BufferedImage raniJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage body
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(raniSilk, raniJari, banarasSilk, banarasJari, teegaSilk, teegaJari, body));
        brocades.add(jari(raniSilk, raniJari, banarasSilk, banarasJari, teegaSilk, teegaJari, body));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/16/out/design1/design-kadiyalu-plain-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage raniJari = borderR(ImageIO.read(new File("d/16/in/design1/border-normal/jari.bmp")));
        BufferedImage raniSilk = PlainGenerator.get(raniJari.getWidth(), raniJari.getHeight());

        BufferedImage teegaJari = borderL(ImageIO.read(new File("d/16/in/design1/border/teega.bmp")));
        BufferedImage teegaSilk = PlainGenerator.get(teegaJari.getWidth(), teegaJari.getHeight());
        BufferedImage banarasJari = borderL(ImageIO.read(new File("d/16/in/design1/border/repeat.bmp")));
        BufferedImage banarasSilk = PlainGenerator.get(banarasJari.getWidth(), banarasJari.getHeight());

        BufferedImage body = PlainGenerator.get(raniJari.getWidth(), 480);

        get(raniSilk, raniJari, banarasSilk, banarasJari, teegaSilk, teegaJari, body);
    }

    public static BufferedImage borderR(BufferedImage result) {
        return HorizontalRepeatGenerator.get(SIZE / result.getWidth(), result);
    }

    private static BufferedImage borderL(BufferedImage input) {
        return HorizontalRepeatGenerator.get(SIZE / input.getWidth(), input);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
