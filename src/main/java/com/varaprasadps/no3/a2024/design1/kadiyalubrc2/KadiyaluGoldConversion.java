package com.varaprasadps.no3.a2024.design1.kadiyalubrc2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2024.Kadiyalu1Play.jari1;
import static com.varaprasadps.no3.a2024.Kadiyalu1Play.rani1;
import static com.varaprasadps.no3.a2024.Kadiyalu2Play.*;
import static java.lang.String.format;


public class KadiyaluGoldConversion {

    public static BufferedImage kadiyalu2Play(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage checks, BufferedImage meena, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani2(right, leftback, meena, checks));
        brocades.add(jari2(right, left, meena, checks));
        brocades.add(nimbu2(getBorder(right), getBorder(left), jari, checks));
        for (BufferedImage bufferedImage : brocades) {
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);
        return brocade;
    }

    public static BufferedImage kadiyalu1Play(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage checks, BufferedImage meena) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani1(right, leftback, meena, checks));
        brocades.add(jari1(right, left, meena, checks));
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
        final BufferedImage jari = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/3/in/2024/design1/brocade2/jari.bmp")));

        List<BufferedImage> brocades = new LinkedList<>();

        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            data.add(50);
            data.add(8);
            for (int j = 0; j < 5; j++) {
                data.add(2);
                data.add(8);
            }
            data.add(102);
            data.add(8);
            for (int j = 0; j < 5; j++) {
                data.add(2);
                data.add(8);
            }
            data.add(52);
        }

        BufferedImage rightR = right;
        BufferedImage leftBackR = leftBack;
        BufferedImage leftR = left;
        BufferedImage checksR = checks;
        BufferedImage meenaR = meena;
        BufferedImage jariR = jari;

        for (int i = 0; i < data.size() - 1; i++) {
            Integer value = data.get(i);
            Pair pRightL = CutLayoutGenerator.getPair(rightR, value);
            Pair pLeftBackL = CutLayoutGenerator.getPair(leftBackR, value);
            Pair pLeftL = CutLayoutGenerator.getPair(leftR, value);
            Pair pChecksL = CutLayoutGenerator.getPair(checksR, value);
            Pair pMeenaL = CutLayoutGenerator.getPair(meenaR, value);
            Pair pJariL = CutLayoutGenerator.getPair(jariR, value);

            rightR = pRightL.bottom;
            leftBackR = pLeftBackL.bottom;
            leftR = pLeftL.bottom;
            checksR = pChecksL.bottom;
            meenaR = pMeenaL.bottom;
            jariR = pJariL.bottom;

            if (value != 8) {
                brocades.add(kadiyalu2Play(pRightL.top, pLeftBackL.top, pLeftL.top, pChecksL.top, pMeenaL.top, pJariL.top));
            } else {
                brocades.add(kadiyalu1Play(pRightL.top, pLeftBackL.top, pLeftL.top, pChecksL.top, pMeenaL.top));
            }
        }
        brocades.add(kadiyalu2Play(rightR, leftBackR, leftR, checksR, meenaR, jariR));


        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : brocades) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage brocade = AddLayoutGenerator.get(repeatWidth, repeatHeight, brocades);
        saveBMP(brocade, format("d/3/out/2024/design1/2kbroc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));

    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
