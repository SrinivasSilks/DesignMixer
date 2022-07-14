package com.varaprasadps.no12.a2022.design1.brocade;

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

import static com.varaprasadps.no12.a2022.ThreePlay.*;

public class BrocadeConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage nimbu, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, VerticalFlipGenerator.get(border)));
        brocades.add(jari(border, nimbu));
        brocades.add(nimbu(border, jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-data/out/12/a2022/design1/brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage borderdf = ImageIO.read(new File("z-data/in/12/a2021/design1/border/border.bmp"));
        BufferedImage abc = HorizontalRepeatGenerator.get(2, borderdf);
        BufferedImage border = CutLayoutGenerator.get(CutLayoutGenerator.get(abc, 360, 1), 480, 0);
        BufferedImage jari = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/12/a2021/design1/brocade/jari.bmp")));
        BufferedImage nimbu = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/12/a2021/design1/brocade/nimbu.bmp")));
        get(border, nimbu, jari);
    }

}
