package com.varaprasadps.no12.a2022.own.a1.selfbrocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no12.a2022.own.TwoPlay.*;
import static java.lang.String.format;

public class SelfBrocadeConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, rani));
        brocades.add(jari(getBorder(left), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-data/out/12/a2022/own/1/selfbrocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightd = ImageIO.read(new File("z-data/in/12/a2022/own/1/border/right.bmp"));
        BufferedImage leftd = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/a2022/own/1/border/left.bmp")));

        BufferedImage right = HorizontalRepeatGenerator.get(2, rightd);
        BufferedImage left = HorizontalRepeatGenerator.get(2, leftd);

        BufferedImage rani = PlainGenerator.get(left.getWidth(), 480);
        BufferedImage jari = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/12/a2022/own/1/selfbrocade/jari.bmp")));
        get(right, left, rani, jari);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
