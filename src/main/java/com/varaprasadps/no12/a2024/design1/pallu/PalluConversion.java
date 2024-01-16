package com.varaprasadps.no12.a2024.design1.pallu;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no12.a2024.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

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

        saveBMP(brocade, format("d/12/out/design1/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage meena = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/12/in/design1/pallu/pallu-rani.bmp")));
        BufferedImage jari = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/12/in/design1/pallu/pallu-jari.bmp")));

        BufferedImage rightd = ImageIO.read(new File("d/12/in/design1/border/right.bmp"));
        BufferedImage leftd = ImageIO.read(new File("d/12/in/design1/border/left.bmp"));

        BufferedImage rightf = HorizontalRepeatGenerator.get(8, rightd);
        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rightf, 60, 1), meena.getWidth(), 0);

        BufferedImage leftf = HorizontalRepeatGenerator.get(8, leftd);
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(leftf, 60, 1), meena.getWidth(), 0);

        get(right, left, meena, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
