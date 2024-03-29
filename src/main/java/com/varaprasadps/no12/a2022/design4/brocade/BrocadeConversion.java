package com.varaprasadps.no12.a2022.design4.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no12.a2022.ThreePlay.*;

public class BrocadeConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage nimbu, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left));
        brocades.add(jari(right, nimbu));
        brocades.add(nimbu(right, jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-data/out/12/a2022/design4/brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage right = getBorder("z-data/in/12/a2021/design4/border/border.bmp");
        BufferedImage left = VerticalFlipGenerator.get(right);
        BufferedImage nimbu = HorizontalRepeatGenerator.get(1, PlainGenerator.get(1280, 480));
        BufferedImage jari = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/12/a2021/design4/brocade/jari.bmp")));
        get(right, left, nimbu, jari);
    }

    private static BufferedImage getBorder(String input) throws IOException {
        BufferedImage borderdf = ImageIO.read(new File(input));
        BufferedImage abc = HorizontalRepeatGenerator.get(2, borderdf);
        BufferedImage border = CutLayoutGenerator.get(CutLayoutGenerator.get(abc, 1160, 1), 1280, 0);
        return border;
    }

}
