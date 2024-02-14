package com.varaprasadps.no3.a2024.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2024.TwoPlay.*;
import static java.lang.String.format;


public class PalluConversion {

    public static BufferedImage get(BufferedImage left, BufferedImage right, BufferedImage rani, BufferedImage jari, BufferedImage checks) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(left, right, rani, checks));
        brocades.add(jari(jari, checks));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/3/out/2024/design1/pallu-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage jari =  RightLayoutGenerator.get(HorizontalRepeatGenerator.get(2, LeftLayoutGenerator.get(ImageIO.read(new File("d/3/in/2024/design1/pallu/pallu-jari.bmp")))));
        BufferedImage rani = PlainGenerator.get(jari.getWidth(), jari.getHeight());

        BufferedImage rboooof = HorizontalRepeatGenerator.get(16, ImageIO.read(new File("d/3/in/2024/design1/border/right.bmp")));
        BufferedImage lboooof = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(16, ImageIO.read(new File("d/3/in/2024/design1/border/left.bmp"))));
        BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(rboooof, 180, 1), jari.getWidth(), 0);
        BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(lboooof, 180, 1), jari.getWidth(), 0);
        BufferedImage checks = BlackGenerator.get(right.getWidth(), 64);

        get(left, right, rani, jari, checks);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
