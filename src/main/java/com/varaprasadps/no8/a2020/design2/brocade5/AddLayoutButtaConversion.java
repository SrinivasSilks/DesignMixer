package com.varaprasadps.no8.a2020.design2.brocade5;

import com.varaprasadps.image.*;
import com.varaprasadps.no8.a2020.design2.brocade3.GoldJariBrocade;
import com.varaprasadps.no8.a2020.design2.brocade3.WhiteJariBrocade;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutButtaConversion {

    public static void main(String[] args) throws IOException {
        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 33; i++) {
            data.add(20);
            data.add(20);
        }

        BufferedImage aaaa = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/8/a2020/8design2/border/border.bmp"))));
        BufferedImage bbbb = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(33, ImageIO.read(new File("z-data/in/8/a2020/8design2/brocade5/jari.bmp"))));
        BufferedImage cccc = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(33, PlainGenerator.get(40, 480)));
        BufferedImage dddd = LeftLayoutGenerator.get(cut(HorizontalRepeatGenerator.get(6, ImageIO.read(new File("z-data/in/8/a2020/8design2/brocade3/chucks.bmp"))), 1320));

        List<BufferedImage> borders = new LinkedList<>();
        borders.add(aaaa);
        borders.add(aaaa);
        List<BufferedImage> goldjaris = new LinkedList<>();
        goldjaris.add(bbbb);
        goldjaris.add(bbbb);
        List<BufferedImage> nimbus = new LinkedList<>();
        nimbus.add(cccc);
        nimbus.add(cccc);
        List<BufferedImage> chucks = new LinkedList<>();
        chucks.add(dddd);
        chucks.add(dddd);

        saveBMP(aaaa, "z-data/in/8/a2020/8design2/test2/aaaa.bmp");
        saveBMP(bbbb, "z-data/in/8/a2020/8design2/test2/bbbb.bmp");
        saveBMP(cccc, "z-data/in/8/a2020/8design2/test2/cccc.bmp");
        saveBMP(dddd, "z-data/in/8/a2020/8design2/test2/dddd.bmp");

        List<BufferedImage> result = new LinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            if (i < data.size() - 1) {
                borders = CutLayoutGenerator.get(borders.get(1), data.get(i));
                goldjaris = CutLayoutGenerator.get(goldjaris.get(1), data.get(i));
                nimbus = CutLayoutGenerator.get(nimbus.get(1), data.get(i));
                chucks = CutLayoutGenerator.get(chucks.get(1), data.get(i));

                BufferedImage border = RightLayoutGenerator.get(borders.get(0));
                BufferedImage gold = RightLayoutGenerator.get(goldjaris.get(0));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(0));
                BufferedImage chuck = RightLayoutGenerator.get(chucks.get(0));
                saveBMP(border, String.format("z-data/in/8/a2020/8design2/test2/border-%s.bmp", i));
                saveBMP(gold, String.format("z-data/in/8/a2020/8design2/test2/gold-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/8/a2020/8design2/test2/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/8/a2020/8design2/test2/chuck-%s.bmp", i));
                result.add(getBrocade(border, gold, nimbu, chuck, i));
            } else {

                BufferedImage border = RightLayoutGenerator.get(borders.get(1));
                BufferedImage gold = RightLayoutGenerator.get(goldjaris.get(1));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(1));
                BufferedImage chuck = RightLayoutGenerator.get(chucks.get(1));

                saveBMP(border, String.format("z-data/in/8/a2020/8design2/test2/border-%s.bmp", i));
                saveBMP(gold, String.format("z-data/in/8/a2020/8design2/test2/jari-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/8/a2020/8design2/test2/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/8/a2020/8design2/test2/chuck-%s.bmp", i));
                result.add(getBrocade(border, gold, nimbu, chuck, i));
            }
        }
        int i = 0;
        for (BufferedImage input : result) {
            saveBMP(input, String.format("z-data/out/8/a2020/8design2/test2/test-brocade4-%s.bmp", i++));
        }
        BufferedImage bufferedImage = AddLayoutGenerator.get(result);
        saveBMP(bufferedImage, "z-data/out/8/a2020/8design2/brocade5.bmp");
    }

    private static BufferedImage getBrocade(BufferedImage border, BufferedImage gold, BufferedImage nimbu,
                                            BufferedImage chuck, int index) throws IOException {
        if (border.getWidth() == 20 && index % 2 == 0) {
            return WhiteJariBrocade.get(border, gold, nimbu, chuck);
        }
        if (border.getWidth() == 20 && index % 2 != 0) {
            return GoldJariBrocade.get(border, gold, nimbu, chuck);
        }
        throw new IllegalArgumentException();
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

    static BufferedImage cut(final BufferedImage bi, int cut) {
        return RightLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(bi), cut).get(0));
    }

}
