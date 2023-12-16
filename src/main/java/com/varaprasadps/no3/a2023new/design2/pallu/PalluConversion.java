package com.varaprasadps.no3.a2023new.design2.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2023new.design2.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

    public static BufferedImage get(BufferedImage left, BufferedImage right, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(left, right, rani));
        brocades.add(jari(jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/3/out/design2/1pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage jari = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/design2/pallu/pallu-jari.bmp")));
        BufferedImage rani = PlainGenerator.get(jari.getWidth(), jari.getHeight());

        BufferedImage rboooof = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/3/in/design2/border/right.bmp")));
        BufferedImage lboooof = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/3/in/design2/border/left.bmp"))));
        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rboooof, 40, 1), jari.getWidth(), 0);
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(lboooof, 40, 1), jari.getWidth(), 0);

        get(left, right, rani, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
