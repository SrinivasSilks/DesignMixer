package com.vasu.loom2.design5.brocade6;

import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.RightLayoutGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

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
        saveBMP(brocade, String.format("z-vasu/out/2/design5/66brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage borderdf = ImageIO.read(new File("z-vasu/in/2/design5/border/border.bmp"));
        BufferedImage border = HorizontalRepeatGenerator.get(5, borderdf);

        BufferedImage read = ImageIO.read(new File("z-vasu/in/2/design5/brocade6/jari.bmp"));
        BufferedImage readnimbu = ImageIO.read(new File("z-vasu/in/2/design5/brocade6/nimbu.bmp"));

        BufferedImage jari = HorizontalRepeatGenerator.get(4, RightLayoutGenerator.get(HorizontalRepeatGenerator.get(2, LeftLayoutGenerator.get(read))));
        BufferedImage nimbu = HorizontalRepeatGenerator.get(4, RightLayoutGenerator.get(HorizontalRepeatGenerator.get(2, LeftLayoutGenerator.get(readnimbu))));
        get(border, nimbu, jari);
    }

}
