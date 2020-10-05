package com.varaprasadps.no3.a2020k.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2020.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage rani, BufferedImage jari, BufferedImage chucks) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, rani, chucks));
        brocades.add(jari(getBorder(border), jari, chucks));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-data/out/3/a2020k/design1/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage test = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/3/a2020k/design1/border/border.bmp")));
        BufferedImage border = RightLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(test), 2000).get(0));
        BufferedImage rani = CutLayoutGenerator.get(ImageIO.read(new File("z-data/in/3/a2020k/design1/pallu/pallu-rani.bmp")),600).get(0);
        BufferedImage jari =  CutLayoutGenerator.get(ImageIO.read(new File("z-data/in/3/a2020k/design1/pallu/pallu-jari.bmp")), 600).get(0);
        BufferedImage chucks = ReverseGenerator.get(EmptyGenerator.get(border.getWidth(), 12));
        get(border, rani, jari, chucks);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
