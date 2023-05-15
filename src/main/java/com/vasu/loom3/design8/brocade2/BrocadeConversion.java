package com.vasu.loom3.design8.brocade2;

import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom3.ThreePlay.*;


public class BrocadeConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage nimbu, BufferedImage jari, BufferedImage rani) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, VerticalFlipGenerator.get(border), rani));
        brocades.add(jari(border, nimbu));
        brocades.add(nimbu(border, jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-vasu/out/3/design8/2brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage borderdf = ImageIO.read(new File("z-vasu/in/3/design8/border/border.bmp"));
        BufferedImage border = HorizontalRepeatGenerator.get(7, borderdf);
        BufferedImage rani = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-vasu/in/3/design8/brocade2/rani.bmp")));
        BufferedImage jari = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-vasu/in/3/design8/brocade2/jari.bmp")));
        BufferedImage nimbu = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-vasu/in/3/design8/brocade2/nimbu.bmp")));
        get(border, nimbu, jari, rani);
    }

}
