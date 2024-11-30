package com.varaprasadps.no13.a2025.design2.blouse;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no13.a2025.TwoPlay.*;
import static java.lang.String.format;


public class BlouseConversion {

    public static BufferedImage get(BufferedImage rightJari, BufferedImage leftFigureJari, BufferedImage leftTeegaJari, BufferedImage anni, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightJari, leftFigureJari, leftTeegaJari, anni));
        brocades.add(jari(jari));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2025/design2/blouse-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = border(HorizontalRepeatGenerator.get(6, ImageIO.read(new File("d/13/in/2025/design2/border-normal/jari.bmp"))));
        BufferedImage teega = HorizontalRepeatGenerator.get(100, ImageIO.read(new File("d/13/in/2025/design2/border/teega.bmp")));
        BufferedImage figure = HorizontalRepeatGenerator.get(375, ImageIO.read(new File("d/13/in/2025/design2/border/banaras.bmp")));

        BufferedImage meena = HorizontalRepeatGenerator.get(right.getWidth() / 20, ImageIO.read(new File("d/13/in/2025/design1/blouse/jari.bmp")));
        BufferedImage jari = HorizontalRepeatGenerator.get(right.getWidth() / 20, ImageIO.read(new File("d/13/in/2025/design1/blouse/rani.bmp")));

        get(right, figure, teega, VerticalRepeatGenerator.get(2, meena), VerticalRepeatGenerator.get(2, jari));
    }

    public static BufferedImage border(BufferedImage input) {
        List<BufferedImage> images = new LinkedList<>();
        images.add(input);
        images.add(PlainGenerator.get(input.getWidth(), 2));
        return AddLayoutGenerator.get(images);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
