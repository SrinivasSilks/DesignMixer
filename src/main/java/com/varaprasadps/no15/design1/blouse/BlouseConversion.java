package com.varaprasadps.no15.design1.blouse;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no15.TwoPlay.*;
import static java.lang.String.format;

public class BlouseConversion {

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

        saveBMP(brocade, format("d/15/out/design1/blouse-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/15/in/design1/border/right.bmp")));
        BufferedImage teega = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/15/in/design1/border/teega.bmp")));
        BufferedImage banaras = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/15/in/design1/border/repeat.bmp")));

        BufferedImage rightSilk = PlainGenerator.get(right.getWidth(), right.getHeight());
        BufferedImage teegaSilk = PlainGenerator.get(teega.getWidth(), teega.getHeight());
        BufferedImage banarasSilk = PlainGenerator.get(banaras.getWidth(), banaras.getHeight());

        BufferedImage meena = HorizontalRepeatGenerator.get(right.getWidth() / 20, VerticalRepeatGenerator.get(2, ImageIO.read(new File("d/15/in/design1/blouse/jari.bmp"))));
        BufferedImage jari = HorizontalRepeatGenerator.get(right.getWidth() / 20, VerticalRepeatGenerator.get(2, ImageIO.read(new File("d/15/in/design1/blouse/rani.bmp"))));

        get(rightSilk, right, banarasSilk, banaras, teegaSilk, teega, meena, jari);
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
