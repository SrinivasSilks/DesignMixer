package com.varaprasadps.no3.a2021.design3.brocade1;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2021.ThreePlay.*;

public class BrocadeConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage nimbu, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border));
        brocades.add(jari(border, nimbu));
        brocades.add(nimbu(border, jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-data/out/3/a2021/design3/brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage abc = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/3/a2021/design3/border/border.bmp")));
        BufferedImage border2 = CutLayoutGenerator.get(CutLayoutGenerator.get(abc, 350, 1), 480, 0);
        BufferedImage border = HorizontalRepeatGenerator.get(1, border2);
        BufferedImage jari = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/3/a2021/design3/brocade/jari.bmp")));
        BufferedImage nimbu = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/3/a2021/design3/brocade/nimbu.bmp")));
        get(border, nimbu, jari);
    }

}
