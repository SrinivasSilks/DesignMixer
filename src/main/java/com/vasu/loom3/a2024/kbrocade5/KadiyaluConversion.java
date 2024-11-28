package com.vasu.loom3.a2024.kbrocade5;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom3.a2024.Kadiyalu3Play.*;
import static java.lang.String.format;


public class KadiyaluConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage meena, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, leftback, meena));
        brocades.add(jari(right, left, meena));
        brocades.add(nimbu(getBorder(right), getBorder(left), jari));
        for (int i = 0; i < brocades.size(); i++) {
            BufferedImage bufferedImage = brocades.get(i);
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-vasu/out/3/a2024/design1/5kbroc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(17, ImageIO.read(new File("z-vasu/in/3/a2024/design1/border/border.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(17, ImageIO.read(new File("z-vasu/in/3/a2024/design1/border/border-first.bmp"))));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(17, ImageIO.read(new File("z-vasu/in/3/a2024/design1/border/border.bmp"))));

        BufferedImage jari = VerticalRepeatGenerator.get(3, HorizontalRepeatGenerator.get(18, ImageIO.read(new File("z-vasu/in/3/a2024/design1/brocade5/jari.bmp"))));
        BufferedImage meena = PlainGenerator.get(jari.getWidth(), jari.getHeight());

        get(right, leftBack, left, meena, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}