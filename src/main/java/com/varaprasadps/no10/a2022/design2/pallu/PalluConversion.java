package com.varaprasadps.no10.a2022.design2.pallu;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no10.a2022.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, VerticalFlipGenerator.get(border), rani));
        brocades.add(jari(getBorder(border), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-data/out/10/a2022/design2/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage big = ImageIO.read(new File("z-data/in/10/a2022/design2/border/right.bmp"));

        BufferedImage boooof = HorizontalRepeatGenerator.get(8, big);
        BufferedImage border = CutLayoutGenerator.get(boooof, 1800, 0);
        BufferedImage rani = ImageIO.read(new File("z-data/in/10/a2022/design2/pallu/pallu-rani.bmp"));
        BufferedImage jari = ImageIO.read(new File("z-data/in/10/a2022/design2/pallu/pallu-jari.bmp"));
        get(border, rani, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
