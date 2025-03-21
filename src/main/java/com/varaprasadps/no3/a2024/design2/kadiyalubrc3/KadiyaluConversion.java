package com.varaprasadps.no3.a2024.design2.kadiyalubrc3;

import com.varaprasadps.image.*;
import com.varaprasadps.no3.a2024.Kadiyalu123Play;
import com.varaprasadps.no3.a2024.Kadiyalu12Play;
import com.varaprasadps.no3.a2024.Kadiyalu13Play;
import com.varaprasadps.no3.a2024.Kadiyalu1Play;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        BufferedImage brocade = getBrocade(brocades);
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
        BufferedImage brocade = getBrocade(brocades);
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
        BufferedImage brocade = getBrocade(brocades);
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
        BufferedImage brocade = getBrocade(brocades);
        displayPixels(brocade);
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/border/right.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/border/left-first.bmp"))));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/border/left.bmp"))));
        BufferedImage checksnormal = ImageIO.read(new File("d/3/in/2024/design2/brocade3/checks.bmp"));
        final BufferedImage finalchecks = AddLayoutGenerator.get(new LinkedList() {
            {
                add(EmptyGenerator.get(checksnormal.getWidth(), 4));
                add(checksnormal);
            }
        });
        BufferedImage checks = HorizontalRepeatGenerator.get(1, finalchecks);
        final BufferedImage meena = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/brocade3/anni.bmp")));
        final BufferedImage gold = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/brocade3/goldjari.bmp")));
        final BufferedImage silver = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/brocade3/silverjari.bmp")));

        List<BufferedImage> rightSilces = SlicerGenerator.get(right, 2);
        List<BufferedImage> leftBackSilces = SlicerGenerator.get(leftBack, 2);
        List<BufferedImage> leftSilces = SlicerGenerator.get(left, 2);
        List<BufferedImage> checksSilces = SlicerGenerator.get(checks, 2);

        List<BufferedImage> silverSilces = SlicerGenerator.get(silver, 2);
        List<BufferedImage> goldSilces = SlicerGenerator.get(gold, 2);
        List<BufferedImage> meenaSilces = SlicerGenerator.get(meena, 2);

        LinkedList<BufferedImage> brocades = new LinkedList<>();

        if (rightSilces.size() == silverSilces.size()) {
            for (int i = 0; i < rightSilces.size(); i++) {
                boolean isSilverValid = BlackCheck.valid(silverSilces.get(i));
                boolean isGoldValid = BlackCheck.valid(goldSilces.get(i));
                if (isSilverValid && isGoldValid) {
                    brocades.add(kadiyalu123Play(rightSilces.get(i), leftBackSilces.get(i), leftSilces.get(i), checksSilces.get(i), meenaSilces.get(i), silverSilces.get(i), goldSilces.get(i)));
                } else if (!isSilverValid && isGoldValid) {
                    brocades.add(kadiyalu13Play(rightSilces.get(i), leftBackSilces.get(i), leftSilces.get(i), checksSilces.get(i), meenaSilces.get(i), goldSilces.get(i)));
                } else if (isSilverValid) {
                    brocades.add(kadiyalu12Play(rightSilces.get(i), leftBackSilces.get(i), leftSilces.get(i), checksSilces.get(i), meenaSilces.get(i), silverSilces.get(i)));
                } else {
                    brocades.add(kadiyalu1Play(rightSilces.get(i), leftBackSilces.get(i), leftSilces.get(i), checksSilces.get(i), meenaSilces.get(i)));
                }
            }
        }
        BufferedImage brocade = LeftLayoutGenerator.get(SlicerGenerator.attachX(brocades));
        saveBMP(brocade, format("d/3/out/2024/design2/3kbroc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
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

    public static BufferedImage getBrocade(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(String.format("Brocade => Width : %s, Height : %s", inputs.get(0).getWidth(), inputs.get(0).getHeight()));
        }
        BufferedImage bufferedImage = ColumnRepeatGenerator.get(inputs);
        System.out.println(String.format("final Brocade => Width : %s, Height : %s", bufferedImage.getWidth(), bufferedImage.getHeight()));

        return bufferedImage;
    }
}
