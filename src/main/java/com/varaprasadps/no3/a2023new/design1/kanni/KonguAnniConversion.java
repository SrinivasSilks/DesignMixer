package com.varaprasadps.no3.a2023new.design1.kanni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2023new.design1.TwoPlay.*;
import static java.lang.String.format;


public class KonguAnniConversion {

    public static BufferedImage get(BufferedImage left, BufferedImage right, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(left, right, rani));
        brocades.add(jari(jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/3/out/design1/kanni-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/design1/border/right.bmp")));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/design1/border/left.bmp"))));

        int width = right.getWidth();
        BufferedImage rani = PlainGenerator.get(width, 480);
        BufferedImage jari = PlainGenerator.get(width, 480);
        get(left, right, rani, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
