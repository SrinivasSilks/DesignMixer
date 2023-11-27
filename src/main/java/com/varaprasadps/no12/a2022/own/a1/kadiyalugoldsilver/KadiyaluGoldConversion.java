package com.varaprasadps.no12.a2022.own.a1.kadiyalugoldsilver;

import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.PlainGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no12.a2022.own.Kadiyalu4Play.*;
import static java.lang.String.format;


public class KadiyaluGoldConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage rani, BufferedImage jari, BufferedImage silver) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, leftback, rani));
        brocades.add(jari(right, left, rani));
        brocades.add(nimbu(getBorder(right), getBorder(left), jari));
        brocades.add(meena(getBorder(right), getBorder(left), silver));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-data/out/12/a2022/own/1/4kbroc-gold-silver-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/12/a2022/own/1/border/right.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/12/a2022/own/1/border/left-first.bmp"))));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/12/a2022/own/1/border/left.bmp"))));


        BufferedImage resham = PlainGenerator.get(left.getWidth(), 480);
        BufferedImage gold = HorizontalRepeatGenerator.get(6, ImageIO.read(new File("z-data/in/12/a2022/own/1/brocadesilvergold/gold.bmp")));
        BufferedImage silver = HorizontalRepeatGenerator.get(6, ImageIO.read(new File("z-data/in/12/a2022/own/1/brocadesilvergold/silver.bmp")));

        get(right, leftBack, left, resham, gold, silver);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
