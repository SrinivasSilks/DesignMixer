package com.varaprasadps.no12.a2021.design2.brocade;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no12.a2021.ThreePlay.*;

public class BrocadeConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage nimbu, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left));
        brocades.add(jari(right, nimbu));
        brocades.add(nimbu(right, jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-data/out/12/a2021/design2/brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage right = getBorder("z-data/in/12/a2021/design2/border/right.bmp");
        BufferedImage left = getBorder("z-data/in/12/a2021/design2/border/left.bmp");
        BufferedImage jari = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/12/a2021/design2/brocade/jari.bmp")));
        BufferedImage nimbu = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/12/a2021/design2/brocade/nimbu.bmp")));
        get(right, left, nimbu, jari);
    }

    private static BufferedImage getBorder(String input) throws IOException {
        BufferedImage borderdf = ImageIO.read(new File(input));
        BufferedImage abc = HorizontalRepeatGenerator.get(2, borderdf);
        BufferedImage border = CutLayoutGenerator.get(CutLayoutGenerator.get(abc, 360, 1), 480, 0);
        return border;
    }

}
