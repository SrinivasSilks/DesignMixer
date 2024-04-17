package com.varaprasadps.no3.a2024.design1.kadiyalubrc2;

import com.varaprasadps.image.*;
import com.varaprasadps.no3.a2024.Kadiyalu123Play;
import com.varaprasadps.no3.a2024.Kadiyalu12Play;
import com.varaprasadps.no3.a2024.Kadiyalu13Play;
import com.varaprasadps.no3.a2024.Kadiyalu1Play;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;


public class KadiyaluConversion {


    public static BufferedImage kadiyalu13Play(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage checks, BufferedImage meena, BufferedImage gold) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(Kadiyalu13Play.rani2(right, leftback, meena, checks));
        brocades.add(Kadiyalu13Play.jari2(right, left, meena, checks));
        brocades.add(Kadiyalu13Play.silver(getBorder(right), getBorder(left), gold, checks));
        for (BufferedImage bufferedImage : brocades) {
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);
        return brocade;
    }

    public static BufferedImage kadiyalu123Play(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage checks, BufferedImage meena, BufferedImage silver, BufferedImage gold) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(Kadiyalu123Play.rani2(right, leftback, meena, checks));
        brocades.add(Kadiyalu123Play.jari2(right, left, meena, checks));
        brocades.add(Kadiyalu123Play.gold(getBorder(right), getBorder(left), silver, checks));
        brocades.add(Kadiyalu123Play.silver(getBorder(right), getBorder(left), gold, checks));
        for (BufferedImage bufferedImage : brocades) {
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);
        return brocade;
    }

    public static BufferedImage kadiyalu12Play(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage checks, BufferedImage meena, BufferedImage silver) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(Kadiyalu12Play.rani2(right, leftback, meena, checks));
        brocades.add(Kadiyalu12Play.jari2(right, left, meena, checks));
        brocades.add(Kadiyalu12Play.gold(getBorder(right), getBorder(left), silver, checks));
        for (BufferedImage bufferedImage : brocades) {
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);
        return brocade;
    }

    public static BufferedImage kadiyalu1Play(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage checks, BufferedImage meena) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(Kadiyalu1Play.rani1(right, leftback, meena, checks));
        brocades.add(Kadiyalu1Play.jari1(right, left, meena, checks));
        for (BufferedImage bufferedImage : brocades) {
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage right = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/3/in/2024/design1/border/right.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/3/in/2024/design1/border/left-first.bmp"))));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/3/in/2024/design1/border/left.bmp"))));
        BufferedImage checks = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/3/in/2024/design1/border/checks.bmp")));
        final BufferedImage meena = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/3/in/2024/design1/brocade2/meena.bmp")));
        final BufferedImage gold = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/3/in/2024/design1/brocade2/jari.bmp")));

        List<BufferedImage> rightSilces = getSlices(right);
        List<BufferedImage> leftBackSilces = getSlices(leftBack);
        List<BufferedImage> leftSilces = getSlices(left);
        List<BufferedImage> checksSilces = getSlices(checks);

        List<BufferedImage> goldSilces = getSlices(gold);
        List<BufferedImage> meenaSilces = getSlices(meena);

        List<BufferedImage> brocades = new LinkedList<>();

        if (rightSilces.size() == goldSilces.size()) {
            for (int i = 0; i < rightSilces.size(); i++) {
                boolean isGoldValid = BlackCheck.valid(goldSilces.get(i));
                if (isGoldValid) {
                    brocades.add(kadiyalu12Play(rightSilces.get(i), leftBackSilces.get(i), leftSilces.get(i), checksSilces.get(i), meenaSilces.get(i), goldSilces.get(i)));
                } else {
                    System.out.println("coming in");
                    brocades.add(kadiyalu1Play(rightSilces.get(i), leftBackSilces.get(i), leftSilces.get(i), checksSilces.get(i), meenaSilces.get(i)));
                }
            }
        }

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : brocades) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage brocade = AddLayoutGenerator.get(repeatWidth, repeatHeight, brocades);
        saveBMP(brocade, format("d/3/out/2024/design1/2-testkbroc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));

    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

    public static BufferedImage getBorder(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    private static List<BufferedImage> getSlices(BufferedImage actual) {
        int SIZE = 2;
        List<BufferedImage> images = new LinkedList<>();
        for (int i = 0; i < actual.getWidth() / SIZE; i++) {
            BufferedImage silice = new BufferedImage(SIZE, actual.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            for (int m = 0; m < SIZE; m++) {
                for (int j = 0; j < actual.getHeight(); j++) {
                    int actualRGB = actual.getRGB((i * SIZE) + m, j);
                    silice.setRGB(m, j, actualRGB);
                }
            }
            images.add(silice);
        }
        return images;
    }

    public static BufferedImage getBrocade(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(String.format("Brocade => Width : %s, Height : %s", inputs.get(0).getWidth(), inputs.get(0).getHeight()));
        }
        BufferedImage bufferedImage = ColumnRepeatGenerator.get(inputs);
        System.out.println(String.format("final Brocade => Width : %s, Height : %s", bufferedImage.getWidth(), bufferedImage.getHeight()));

        return bufferedImage;
    }
}
