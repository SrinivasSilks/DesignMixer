package com.varaprasadps.no8.a2020.design1.square3;

import com.varaprasadps.image.*;
import com.varaprasadps.no8.a2020.design1.sqaure.GoldJariBrocade;
import com.varaprasadps.no8.a2020.design1.sqaure.PalluMeenaConversion;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutButtaConversion {

    public static void main(String[] args) throws IOException {
        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            data.add(8);
            data.add(18);
            data.add(8);
            data.add(2);
            data.add(10);
            data.add(2);
            data.add(10);
            data.add(2);
        }

        BufferedImage aaaa = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2,ImageIO.read(new File("z-data/in/8/a2020/square3/border.bmp"))));
        BufferedImage bbbb = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/square3/brocade/goldjari.bmp")));
        BufferedImage cccc = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/square3/brocade/nimbu.bmp")));
        BufferedImage dddd = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/8/a2020/square3/brocade/chucks.bmp"))));
        BufferedImage eeee = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/square3/brocade/whitejari.bmp")));

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
        List<BufferedImage> whitejari = new LinkedList<>();
        whitejari.add(eeee);
        whitejari.add(eeee);

        saveBMP(aaaa, "z-data/in/8/a2020/square3/test/aaaa.bmp");
        saveBMP(bbbb, "z-data/in/8/a2020/square3/test/bbbb.bmp");
        saveBMP(cccc, "z-data/in/8/a2020/square3/test/cccc.bmp");
        saveBMP(dddd, "z-data/in/8/a2020/square3/test/dddd.bmp");
        saveBMP(eeee, "z-data/in/8/a2020/square3/test/eeee.bmp");

        List<BufferedImage> result = new LinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            if (i < data.size() - 1) {
                borders = CutLayoutGenerator.get(borders.get(1), data.get(i));
                goldjaris = CutLayoutGenerator.get(goldjaris.get(1), data.get(i));
                nimbus = CutLayoutGenerator.get(nimbus.get(1), data.get(i));
                chucks = CutLayoutGenerator.get(chucks.get(1), data.get(i));
                whitejari = CutLayoutGenerator.get(whitejari.get(1), data.get(i));

                BufferedImage border = RightLayoutGenerator.get(borders.get(0));
                BufferedImage gold = RightLayoutGenerator.get(goldjaris.get(0));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(0));
                BufferedImage chuck = RightLayoutGenerator.get(chucks.get(0));
                BufferedImage white = RightLayoutGenerator.get(whitejari.get(0));
                saveBMP(border, String.format("z-data/in/8/a2020/square3/test/border-%s.bmp", i));
                saveBMP(gold, String.format("z-data/in/8/a2020/square3/test/gold-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/8/a2020/square3/test/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/8/a2020/square3/test/chuck-%s.bmp", i));
                saveBMP(white, String.format("z-data/in/8/a2020/square3/test/white-%s.bmp", i));
                result.add(getBrocade(border, gold, nimbu, chuck, white, i));
            } else {

                BufferedImage border = RightLayoutGenerator.get(borders.get(1));
                BufferedImage gold = RightLayoutGenerator.get(goldjaris.get(1));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(1));
                BufferedImage chuck = RightLayoutGenerator.get(chucks.get(1));
                BufferedImage white = RightLayoutGenerator.get(whitejari.get(0));

                saveBMP(border, String.format("z-data/in/8/a2020/square/test/border-%s.bmp", i));
                saveBMP(gold, String.format("z-data/in/8/a2020/square/test/jari-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/8/a2020/square/test/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/8/a2020/square/test/chuck-%s.bmp", i));
                saveBMP(white, String.format("z-data/in/8/a2020/square3/test/white-%s.bmp", i));
                result.add(getBrocade(border, gold, nimbu, chuck, white, i));
            }
        }
        int i = 0;
        for (BufferedImage input : result) {
            saveBMP(input, String.format("z-data/out/8/a2020/test/square3-%s.bmp", i++));

        }
        BufferedImage bufferedImage = AddLayoutGenerator.get(result);
        saveBMP(bufferedImage, "z-data/out/8/a2020/square3.bmp");
    }

    private static BufferedImage getBrocade(BufferedImage border, BufferedImage gold, BufferedImage nimbu,
                                            BufferedImage chuck, BufferedImage white, int index) throws IOException {
        if (border.getWidth() == 8 || border.getWidth() == 10) {
            return PalluMeenaConversion.get(border, nimbu, chuck);
        }
        if (border.getWidth() == 2) {
            return GoldJariBrocade.get(border, gold, nimbu, chuck);
        }
        if (border.getWidth() == 18) {
            return WhiteGoldJariBrocade.get(border, gold, white, nimbu, chuck);
        }

        return null;
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
