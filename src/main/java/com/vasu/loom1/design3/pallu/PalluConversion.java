package com.vasu.loom1.design3.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom1.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, rani));
        brocades.add(jari(getBorder(left), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-vasu/out/1/design3/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightd = ImageIO.read(new File("z-vasu/in/1/design3/border/border.bmp"));
        BufferedImage leftd = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/in/1/design3/border/border.bmp")));

        BufferedImage rightf = HorizontalRepeatGenerator.get(50, rightd);
        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rightf, 180, 1), 1722, 0);

        BufferedImage leftf = HorizontalRepeatGenerator.get(50, leftd);
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(leftf, 180, 1), 1722, 0);

        BufferedImage rani = PlainGenerator.get(1722, 960);
        BufferedImage jari = ImageIO.read(new File("z-vasu/in/1/design3/pallu/pallu_jari.bmp"));
        get(right, left, rani, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
