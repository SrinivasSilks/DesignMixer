package com.vasu.loom1.a2026.design1.kbrocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom1.a2024.Kadiyalu2Play.getBorder;
import static com.vasu.loom1.a2024.Kadiyalu2Play.getBrocade;
import static com.vasu.loom1.a2024.Kadiyalu2Play.jari;
import static com.vasu.loom1.a2024.Kadiyalu2Play.nimbu;
import static com.vasu.loom1.a2024.Kadiyalu2Play.rani;
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

        saveBMP(brocade, format("z-vasu/out/1/2026/design1/A1KBRC1.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-vasu/in/1/2026/design1/border/border.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(KadiyaluLayoutGenerator.kadiyalu(right));
        BufferedImage left = VerticalFlipGenerator.get(right);

        BufferedImage jari = VerticalRepeatGenerator.get(4, HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-vasu/in/1/2026/design1/brocade1/jari.bmp"))));
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
