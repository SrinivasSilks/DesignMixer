package com.varaprasadps.no17;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.KadiyaluLayoutGenerator.kadiyalu;
import static com.varaprasadps.no17.Kadiyalu2Play.*;
import static java.lang.String.format;

public class KadiyaluBrocadeConversion {

    private static final int SIZE = 1200;

    public static BufferedImage get(
            BufferedImage right, BufferedImage left,
            BufferedImage silver, BufferedImage meena
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, kadiyalu(left), silver));
        brocades.add(jari(right, left, silver));
        brocades.add(nimbu(dis(right), dis(left), meena));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/17/out/design1/1kadiyalu-brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = borderR(ImageIO.read(new File("d/17/in/design1/border/right.bmp")));
        BufferedImage left = borderR(ImageIO.read(new File("d/17/in/design1/border/left.bmp")));
        BufferedImage silver = body(ImageIO.read(new File("d/17/in/design1/brocade1/silver.bmp")));
        BufferedImage meena = body(ImageIO.read(new File("d/16/in/design1/brocade1/meena.bmp")));
        get(right, left, silver, meena);
    }

    public static BufferedImage borderR(BufferedImage result) {
        return HorizontalRepeatGenerator.get(SIZE / result.getWidth(), result);
    }

    private static BufferedImage body(BufferedImage input) {
        return HorizontalRepeatGenerator.get(SIZE / input.getWidth(), input);
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
