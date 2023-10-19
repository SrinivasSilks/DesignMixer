package com.vasu.loom4.design4.brocade8;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom4.TwoPlay.*;
import static java.lang.String.format;


public class SelfBrocadeConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, rani));
        brocades.add(jari(getBorder(left), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-vasu/out/4/design4/8selfbrc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightd = ImageIO.read(new File("z-vasu/in/4/design4/border/border.bmp"));
        BufferedImage leftd = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/in/4/design4/border/border.bmp")));

        BufferedImage right = HorizontalRepeatGenerator.get(7, rightd);
        BufferedImage left = HorizontalRepeatGenerator.get(7, leftd);

        BufferedImage read = RightLayoutGenerator.get(HorizontalRepeatGenerator.get(3, LeftLayoutGenerator.get(ImageIO.read(new File("z-vasu/in/4/design4/brocade8/jari.bmp")))));
        BufferedImage jari = HorizontalRepeatGenerator.get(19, read);
        BufferedImage rani = PlainGenerator.get(jari.getWidth(), 720);
        get(right, left, rani, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
