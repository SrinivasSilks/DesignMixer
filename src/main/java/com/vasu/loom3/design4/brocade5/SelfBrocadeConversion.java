package com.vasu.loom3.design4.brocade5;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom3.TwoPlay.*;
import static java.lang.String.format;


public class SelfBrocadeConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, rani));
        brocades.add(jari(getBorder(left), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-vasu/out/3/design4/5self-brc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightd = ImageIO.read(new File("z-vasu/in/3/design4/border/border.bmp"));
        BufferedImage leftd = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/in/3/design4/border/border.bmp")));

        BufferedImage right = HorizontalRepeatGenerator.get(2, rightd);
        BufferedImage left = HorizontalRepeatGenerator.get(2, leftd);

        BufferedImage jari = HorizontalRepeatGenerator.get(19, ImageIO.read(new File("z-vasu/in/3/design4/brocade5/jari.bmp")));
        BufferedImage rani = PlainGenerator.get(jari.getWidth(), 720);
        get(right, left, rani, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
