package com.varaprasadps.no4.a2024.design2.brocade2;

import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no4.a2024.TwoPlay.*;
import static java.lang.String.format;


public class SelfBrocade2Conversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage jamudu, BufferedImage meena, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, meena, jamudu));
        brocades.add(jari(getBorder(right), getBorder(left), jari));

        for (int i = 0; i < brocades.size(); i++) {
            BufferedImage bufferedImage = brocades.get(i);
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/4/out/a2024/design2/2broc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage jari = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/4/in/a2024/design2/brocade2/jari.bmp")));
        BufferedImage meena = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/4/in/a2024/design2/brocade2/meena.bmp")));

        final BufferedImage right = HorizontalRepeatGenerator.get(7, ImageIO.read(new File("d/4/in/a2024/design2/border/right.bmp")));
        final BufferedImage left = HorizontalRepeatGenerator.get(7, VerticalFlipGenerator.get(ImageIO.read(new File("d/4/in/a2024/design2/border/left.bmp"))));
        final BufferedImage jamudu = HorizontalRepeatGenerator.get(7, ImageIO.read(new File("d/4/in/a2024/design2/border/jamudu.bmp")));

        get(right, left, jamudu, meena, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
