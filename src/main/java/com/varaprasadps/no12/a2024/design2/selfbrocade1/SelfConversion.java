package com.varaprasadps.no12.a2024.design2.selfbrocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no12.a2024.TwoPlay.getBorder;
import static com.varaprasadps.no12.a2024.TwoPlay.getBrocade;
import static com.varaprasadps.no12.a2024.TwoPlay.jari;
import static com.varaprasadps.no12.a2024.TwoPlay.rani;
import static java.lang.String.format;


public class SelfConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage meena, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, meena));
        brocades.add(jari(getBorder(right), getBorder(left), jari));

        for (int i = 0; i < brocades.size(); i++) {
            BufferedImage bufferedImage = brocades.get(i);
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/12/out/design2/1self-brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage jari = VerticalRepeatGenerator.get(2, HorizontalRepeatGenerator.get(12, ImageIO.read(new File("d/12/in/design2/brocade5/gold.bmp"))));
        BufferedImage meena = PlainGenerator.get(jari.getWidth(), jari.getHeight());

        BufferedImage right = HorizontalRepeatGenerator.get(11, ImageIO.read(new File("d/12/in/design2/border/right.bmp")));
        BufferedImage left = HorizontalRepeatGenerator.get(11, ImageIO.read(new File("d/12/in/design2/border/left.bmp")));

        get(right, left, meena, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
