package com.vasu.loom3.a2024.kbrocade6;

import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.PlainGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

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
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-vasu/out/3/a2024/design1/6kbroc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(11, ImageIO.read(new File("z-vasu/in/3/a2024/design1/border/border.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(11, ImageIO.read(new File("z-vasu/in/3/a2024/design1/border/border-first.bmp"))));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(11, ImageIO.read(new File("z-vasu/in/3/a2024/design1/border/border.bmp"))));

        BufferedImage jari = HorizontalRepeatGenerator.get(9, ImageIO.read(new File("z-vasu/in/3/a2024/design1/brocade6/jari.bmp")));
        BufferedImage meena = PlainGenerator.get(jari.getWidth(), jari.getHeight());

        get(right, leftBack, left, meena, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
