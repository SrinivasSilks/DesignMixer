package com.vasu.loom2.design8.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom2.ThreePlay.*;


public class BrocadeConversion {

    public static BufferedImage get(BufferedImage border, BufferedImage nimbu, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(border, VerticalFlipGenerator.get(border)));
        brocades.add(jari(border, nimbu));
        brocades.add(nimbu(border, jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-vasu/out/2/design8/2brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage borderdf = ImageIO.read(new File("z-vasu/in/1/design8/border/border.bmp"));
        BufferedImage border = HorizontalRepeatGenerator.get(4, borderdf);
        BufferedImage jariffff = RightLayoutGenerator.get(HorizontalRepeatGenerator.get(2, LeftLayoutGenerator.get(ImageIO.read(new File("z-vasu/in/1/design8/brocade2/jari.bmp")))));
        BufferedImage jari = HorizontalRepeatGenerator.get(9, jariffff);
        BufferedImage nimbu = PlainGenerator.get(jari.getWidth(), 960);
        get(border, nimbu, jari);
    }

}
