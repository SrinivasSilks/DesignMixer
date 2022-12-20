package com.vasu.loom4.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom3.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, rani));
        brocades.add(jari(getBorder(left), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-vasu/out/4/design1/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightd = ImageIO.read(new File("z-vasu/in/4/design1/border/border.bmp"));
        BufferedImage leftd = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/in/4/design1/border/border.bmp")));

        BufferedImage rightf = HorizontalRepeatGenerator.get(7, rightd);
        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rightf, 180, 1), 1800, 0);

        BufferedImage leftf = HorizontalRepeatGenerator.get(7, leftd);
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(leftf, 180, 1), 1800, 0);

        BufferedImage rani = PlainGenerator.get(1800, 720);
        BufferedImage jari = ImageIO.read(new File("z-vasu/in/4/design1/pallu/pallu_jari.bmp"));
        get(right, left, rani, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
