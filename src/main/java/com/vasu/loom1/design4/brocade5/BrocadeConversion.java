package com.vasu.loom1.design4.brocade5;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom1.ThreePlay.*;


public class BrocadeConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage nimbu, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, VerticalFlipGenerator.get(border)));
        brocades.add(jari(border, nimbu));
        brocades.add(nimbu(border, jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-vasu/out/1/design4/5brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage borderdf = ImageIO.read(new File("z-vasu/in/1/design4/border/border.bmp"));
        BufferedImage border = HorizontalRepeatGenerator.get(20, borderdf);
        BufferedImage read = ImageIO.read(new File("z-vasu/in/1/design4/brocade3/jari4.bmp"));
        BufferedImage jari = HorizontalRepeatGenerator.get(1, RightLayoutGenerator.get(HorizontalRepeatGenerator.get(2, LeftLayoutGenerator.get(read))));
        BufferedImage nimbu = HorizontalRepeatGenerator.get(1, PlainGenerator.get(jari.getWidth(), jari.getHeight()));
        get(border, nimbu, jari);
    }

}
