package com.varaprasadps.no3.a2020.design2.brocade;

import com.varaprasadps.image.EmptyGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.ReverseGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2020.ThreePlay.*;

public class BrocadeConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage jari, BufferedImage nimbu, BufferedImage chucks) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, empty(border.getWidth())));
        brocades.add(jari(getBorder(border), nimbu, chucks));
        brocades.add(nimbu(getBorder(border), jari, chucks));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-data/out/3/a2020/design2/brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage border = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/3/a2020/design2/border/border.bmp")));
        BufferedImage jari = ImageIO.read(new File("z-data/in/3/a2020/design2/brocade/jari.bmp"));
        BufferedImage nimbu = ImageIO.read(new File("z-data/in/3/a2020/design2/brocade/nimbu.bmp"));
        BufferedImage chucks = ReverseGenerator.get(EmptyGenerator.get(border.getWidth(), 12));
        get(border, nimbu, jari, chucks);
    }
}
