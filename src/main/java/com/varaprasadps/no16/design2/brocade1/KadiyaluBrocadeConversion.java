package com.varaprasadps.no16.design2.brocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no16.Kadiyalu2Play.*;
import static java.lang.String.format;

public class KadiyaluBrocadeConversion {

    public static BufferedImage get(
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage jari, BufferedImage nimbu
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, jari));
        brocades.add(jari(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, jari));
        brocades.add(nimbu(dis(rightSilk), dis(rightJari), dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), nimbu));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/16/out/design2/1kadiyalu-brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightJari = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("d/16/in/design2/border/right.bmp")));
        BufferedImage teegaJari = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("d/16/in/design2/border/teega.bmp")));
        BufferedImage banarasJari = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("d/16/in/design2/border/repeat.bmp")));

        int width = rightJari.getWidth();
        BufferedImage rightSilk = PlainGenerator.get(width, 528);
        BufferedImage teegaSilk = PlainGenerator.get(width, 48);
        BufferedImage banarasSilk = PlainGenerator.get(width, 64);

        BufferedImage nimbu = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/16/in/design2/brocade1/nimbu.bmp")));
        BufferedImage jari = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/16/in/design2/brocade1/jari.bmp")));

        get(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, jari, nimbu);
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
