package com.varaprasadps.no12.a2024.design3.blouse;

import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.VerticalRepeatGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no12.a2024.Kadiyalu3Play.getPlain;
import static com.varaprasadps.no12.a2024.TwoPlay.getBrocade;
import static com.varaprasadps.no12.a2024.TwoPlay.jari;
import static com.varaprasadps.no12.a2024.TwoPlay.rani;
import static java.lang.String.format;


public class BlouseConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage meena, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(getPlain(right), getPlain(left), meena));
        brocades.add(jari(right, left, jari));

        for (int i = 0; i < brocades.size(); i++) {
            BufferedImage bufferedImage = brocades.get(i);
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/12/out/design3/blouse-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage left = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/12/in/design3/border/left.bmp")));
        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/12/in/design3/border/right.bmp")));
        BufferedImage jari = ImageIO.read(new File("d/12/in/design3/blouse/final-silver-body.bmp"));
        BufferedImage meena = ImageIO.read(new File("d/12/in/design3/blouse/final-rani-body.bmp"));

        get(right, left, VerticalRepeatGenerator.get(2, meena), VerticalRepeatGenerator.get(2, jari));
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
