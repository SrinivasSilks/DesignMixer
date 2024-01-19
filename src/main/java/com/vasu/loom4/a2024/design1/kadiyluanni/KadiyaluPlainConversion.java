package com.vasu.loom4.a2024.design1.kadiyluanni;

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

import static com.vasu.loom4.a2024.KadiyaluAnniPlay.*;
import static java.lang.String.format;


public class KadiyaluPlainConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage leftBack, BufferedImage left, BufferedImage rani) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, leftBack, rani));
        brocades.add(jari(right, left, rani));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-vasu/out/4/a2024/design1/kadiyalu-plain-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/3/a2024/design1/border/right.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/3/a2024/design1/border/left-first.bmp"))));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/3/a2024/design1/border/left.bmp"))));

        BufferedImage rani = PlainGenerator.get(right.getWidth(), 720);
        get(right, leftBack, left, rani);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
