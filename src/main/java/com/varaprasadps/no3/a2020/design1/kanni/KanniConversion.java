package com.varaprasadps.no3.a2020.design1.kanni;

import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.PlainGenerator;
import com.varaprasadps.image.StepLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2020.TwoPlay.*;
import static java.lang.String.format;


public class KanniConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage rani, BufferedImage jari, BufferedImage chucks) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, rani, chucks));
        brocades.add(jari(getBorder(border), jari, chucks));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-data/out/3/a2020/design1/kanni-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage border = ImageIO.read(new File("z-data/in/3/a2020/design1/border/border.bmp"));
        BufferedImage rani = PlainGenerator.get(border.getWidth(), 600);
        BufferedImage jari = PlainGenerator.get(border.getWidth(), 600);
        BufferedImage chucks = StepLayoutGenerator.get(border.getWidth(), 3);
        get(border, rani, jari, chucks);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
