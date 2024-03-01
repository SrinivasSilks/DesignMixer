package com.varaprasadps.no12.a2024.design2.blouse;

import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.VerticalRepeatGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no12.a2024.Kadiyalu3Play.getBorder;
import static com.varaprasadps.no12.a2024.TwoPlay.*;
import static java.lang.String.format;


public class BlouseConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage meena, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, meena));
        brocades.add(jari(getBorder(right), getBorder(left), jari));

        for (int i = 0; i < brocades.size(); i++) {
            BufferedImage bufferedImage = brocades.get(i);
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/12/out/design2/blouse-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage left = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/12/in/design2/border/left.bmp")));
        BufferedImage right = ImageIO.read(new File("d/12/in/design2/border/right.bmp"));
        BufferedImage meena = HorizontalRepeatGenerator.get(left.getWidth() / 20, ImageIO.read(new File("d/12/in/design2/blouse/jari.bmp")));
        BufferedImage jari = HorizontalRepeatGenerator.get(left.getWidth() / 20, ImageIO.read(new File("d/12/in/design2/blouse/rani.bmp")));

        get(right, left, VerticalRepeatGenerator.get(4, meena), VerticalRepeatGenerator.get(4, jari));
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
