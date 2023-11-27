package com.varaprasadps.no12.a2022.own.a2.kadiyalusilvergold4;

import com.varaprasadps.image.*;
import com.varaprasadps.no12.a2022.own.Kadiyalu3Play;
import com.varaprasadps.no12.a2022.own.Kadiyalu4Play;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;


public class KadiyaluGoldConversion {

    public static BufferedImage get3(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage rani, BufferedImage gold) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(Kadiyalu3Play.rani(right, leftback, rani));
        brocades.add(Kadiyalu3Play.jari(right, left, rani));
        brocades.add(Kadiyalu3Play.nimbu(Kadiyalu3Play.getBorder(right), Kadiyalu3Play.getBorder(left), gold));
        BufferedImage brocade = LeftLayoutGenerator.get(Kadiyalu3Play.getBrocade(brocades));
        displayPixels(brocade);
        return brocade;
    }

    public static BufferedImage get4(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage rani, BufferedImage gold, BufferedImage silver) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(Kadiyalu4Play.rani(right, leftback, rani));
        brocades.add(Kadiyalu4Play.jari(right, left, rani));
        brocades.add(Kadiyalu4Play.nimbu(Kadiyalu4Play.getBorder(right), Kadiyalu4Play.getBorder(left), gold));
        brocades.add(Kadiyalu4Play.meena(Kadiyalu4Play.getBorder(right), Kadiyalu4Play.getBorder(left), silver));
        BufferedImage brocade = LeftLayoutGenerator.get(Kadiyalu4Play.getBrocade(brocades));
        displayPixels(brocade);
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/12/a2022/own/2/border/right.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/12/a2022/own/2/border/left-first.bmp"))));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-data/in/12/a2022/own/2/border/left.bmp"))));

        BufferedImage resham = PlainGenerator.get(left.getWidth(), 480);
        BufferedImage gold = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/12/a2022/own/2/brocadesilver/gold.bmp"))));
        BufferedImage silver = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/12/a2022/own/2/brocadesilver/silver.bmp"))));

        List<Integer> cutList = Arrays.asList(
                4, 14, 6, 14, 6, 14,
                6, 14, 6, 14, 6, 14,
                6, 14, 6, 14, 6, 14,
                6, 14, 6, 14, 6, 14, 2
        );


        Integer reduce = cutList.stream().reduce(0, Integer::sum);
        System.out.println("reduced " + reduce);
        BufferedImage rightC = right;
        BufferedImage leftC = left;
        BufferedImage leftBackC = leftBack;
        BufferedImage reshamC = resham;
        BufferedImage goldC = gold;
        BufferedImage silverC = silver;

        List<BufferedImage> resulted = new LinkedList<>();

        for (int i = 0; i < cutList.size() - 1; i++) {
            Integer cutSize = cutList.get(i);
            if (cutSize == 2) {
                resulted.add(get3(rightC, leftBackC, leftC, reshamC, goldC));
            } else {
                PairD rightP = cut(rightC, cutSize);
                PairD leftP = cut(leftC, cutSize);
                PairD leftBackP = cut(leftBackC, cutSize);
                PairD reshamP = cut(reshamC, cutSize);
                PairD goldP = cut(goldC, cutSize);
                PairD silverP = cut(silverC, cutSize);
                System.out.println("cutsize - "+ cutSize);
                if (cutSize == 4 || cutSize == 6) {
                    resulted.add(get3(rightP.first, leftBackP.first, leftP.first, reshamP.first, goldP.first));
                    System.out.println("3 play - ");
                }

                if (cutSize == 14) {
                    resulted.add(get4(rightP.first, leftBackP.first, leftP.first, reshamP.first, goldP.first, silverP.first));
                    System.out.println("4 play - ");

                }
                rightC = rightP.second;
                leftC = leftP.second;
                leftBackC = leftBackP.second;
                reshamC = reshamP.second;
                goldC = goldP.second;
                silverC = silverP.second;
            }

        }

        BufferedImage finalImage = AddLayoutGenerator.get(resulted);
        saveBMP(finalImage, format("z-data/out/12/a2022/own/2/3kbroc-silver-gold-%s-%s.bmp", finalImage.getWidth(), finalImage.getHeight()));

    }


    private static void displayPixels(BufferedImage fileOne) {
//        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }


    private static PairD cut(BufferedImage input, int size) {
        List<BufferedImage> bufferedImages = CutLayoutGenerator.get(LeftLayoutGenerator.get(input), size);
        return new PairD(RightLayoutGenerator.get(bufferedImages.get(0)), RightLayoutGenerator.get(bufferedImages.get(1)));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}

class PairD {
    BufferedImage first;
    BufferedImage second;

    PairD(BufferedImage first, BufferedImage second) {
        this.first = first;
        this.second = second;
    }
}