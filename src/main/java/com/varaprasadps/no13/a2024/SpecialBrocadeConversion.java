package com.varaprasadps.no13.a2024;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no13.a2024.SpecialThreePlay.*;
import static java.lang.String.format;


public class SpecialBrocadeConversion {

    public static BufferedImage get(BufferedImage rightMeenaRed, BufferedImage rightJariRed, BufferedImage rightMeenaSilver, BufferedImage rightJariSilver, BufferedImage rightMeenaGreen, BufferedImage rightJariGreen,
                                    BufferedImage leftFigureJari, BufferedImage leftTeegaJari, BufferedImage anni, BufferedImage jari, BufferedImage nimbu) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightMeenaRed, rightJariRed, leftFigureJari, leftTeegaJari, anni));
        brocades.add(jari(rightMeenaGreen, rightJariGreen, nimbu));
        brocades.add(nimbu(rightMeenaSilver, rightJariSilver, jari));

        for (int i = 0; i < brocades.size(); i++) {
            BufferedImage bufferedImage = brocades.get(i);
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2024/design1/special-brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage nimbu = HorizontalRepeatGenerator.get(21, ImageIO.read(new File("d/13/in/2024/design1/brocade1/nimbu.bmp")));
        BufferedImage jari = HorizontalRepeatGenerator.get(21, ImageIO.read(new File("d/13/in/2024/design1/brocade1/silver.bmp")));
        BufferedImage anni = HorizontalRepeatGenerator.get(21, ImageIO.read(new File("d/13/in/2024/design1/brocade1/rani.bmp")));

        BufferedImage rightJariRed = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("d/13/in/2024/design1/sborder/jari-red.bmp")));
        BufferedImage rightJariSilver = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("d/13/in/2024/design1/sborder/jari-silver.bmp")));
        BufferedImage rightJariGreen = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("d/13/in/2024/design1/sborder/jari-green.bmp")));
        BufferedImage rightMeenaRed = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("d/13/in/2024/design1/sborder/meena-red.bmp")));
        BufferedImage rightMeenaSilver = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("d/13/in/2024/design1/sborder/meena-silver.bmp")));
        BufferedImage rightMeenaGreen = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("d/13/in/2024/design1/sborder/meena-green.bmp")));


        int width = rightJariRed.getWidth();
        BufferedImage teegaInput = ImageIO.read(new File("d/13/in/2024/design1/border/teega.bmp"));
        BufferedImage banarasInput = ImageIO.read(new File("d/13/in/2024/design1/border/banaras.bmp"));
        BufferedImage teega = CutLayoutGenerator.get(HorizontalRepeatGenerator.get(50, teegaInput), width, 0);
        BufferedImage figure = CutLayoutGenerator.get(HorizontalRepeatGenerator.get(50, banarasInput), width, 0);

        get(rightMeenaRed, rightJariRed, rightMeenaSilver, rightJariSilver, rightMeenaGreen, rightJariGreen, figure, teega, anni, jari, nimbu);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
