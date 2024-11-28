package com.varaprasadps.no16.design2.blouse;

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
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage rani, BufferedImage jari
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani));
        brocades.add(jari(dis(rightSilk), dis(rightJari), dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), jari));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/16/out/design2/blouse-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightJari = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/16/in/design2/border/right.bmp")));
        BufferedImage teegaJari = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/16/in/design2/border/teega.bmp")));
        BufferedImage banarasJari = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/16/in/design2/border/repeat.bmp")));

        int width = rightJari.getWidth();
        BufferedImage rightSilk = PlainGenerator.get(width, 528);
        BufferedImage teegaSilk = PlainGenerator.get(width, 48);
        BufferedImage banarasSilk = PlainGenerator.get(width, 64);

        BufferedImage rani = blouse(ImageIO.read(new File("d/16/in/design2/blouse/jari.bmp")));
        BufferedImage jari = blouse(ImageIO.read(new File("d/16/in/design2/blouse/rani.bmp")));

        get(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani, jari);
    }

    private static BufferedImage dis(BufferedImage image) {
        return EmptyGenerator.get(image.getWidth(), image.getHeight());
    }

    private static BufferedImage blouse(BufferedImage image) {
        return HorizontalRepeatGenerator.get(18, VerticalRepeatGenerator.get(2, image));
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
