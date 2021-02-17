package com.varaprasadps.no3.a2021.design5.pallu;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2021.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, rani));
        brocades.add(jari(getBorder(border), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-data/out/3/a2021/design5/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage boooof = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("z-data/in/3/a2021/design5/border/border.bmp")));
        BufferedImage border = CutLayoutGenerator.get(boooof, 2000, 0);
        BufferedImage rani = ImageIO.read(new File("z-data/in/3/a2021/design5/pallu/p-rani.bmp"));
        BufferedImage jari = ImageIO.read(new File("z-data/in/3/a2021/design5/pallu/p-jari.bmp"));
        get(border, rani, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
