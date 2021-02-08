package com.varaprasadps.no3.a2021.design4.brocade1;

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
        saveBMP(brocade, String.format("z-data/out/3/a2021/design4/brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage abc = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/3/a2021/design4/border/border.bmp")));
        BufferedImage border = CutLayoutGenerator.get(CutLayoutGenerator.get(abc, 120, 1), 240, 0);
        BufferedImage jarisd = ImageIO.read(new File("z-data/in/3/a2021/design4/brocade/jari.bmp"));
        BufferedImage jari = CutLayoutGenerator.get(jarisd, 240, 0);
        BufferedImage nimbudf = ImageIO.read(new File("z-data/in/3/a2021/design4/brocade/nimbu.bmp"));
        BufferedImage nimbu = CutLayoutGenerator.get(nimbudf, 240, 0);
        get(border, nimbu, jari);
    }

}
