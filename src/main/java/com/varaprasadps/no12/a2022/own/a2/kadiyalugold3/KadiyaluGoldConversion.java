package com.varaprasadps.no12.a2022.own.a2.kadiyalugold3;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no12.a2022.own.Kadiyalu3Play.*;
import static java.lang.String.format;


public class KadiyaluGoldConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, leftback, rani));
        brocades.add(jari(right, left, rani));
        brocades.add(nimbu(getBorder(right), getBorder(left), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-data/out/12/a2022/own/2/test-2kbroc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/12/a2022/own/2/border/right.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/12/a2022/own/2/border/left-first.bmp"))));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/12/a2022/own/2/border/left.bmp"))));


        BufferedImage resham = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/12/a2022/own/2/brocade/anni.bmp")));
        BufferedImage gold = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/12/a2022/own/2/brocade/jari.bmp")));

        get(right, leftBack, left, resham, gold);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
