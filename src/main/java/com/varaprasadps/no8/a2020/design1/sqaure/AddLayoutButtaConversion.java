package com.varaprasadps.no8.a2020.design1.sqaure;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.RightLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutButtaConversion {

    public static void main(String[] args) throws IOException {
        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            data.add(4);
            data.add(24);
            data.add(2);
            data.add(8);
            data.add(2);
            data.add(8);
            data.add(2);
        }

        BufferedImage aaaa = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/square/border.bmp")));
        BufferedImage bbbb = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/square/brocade/jari.bmp")));
        BufferedImage cccc = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/square/brocade/nimbu.bmp")));
        BufferedImage dddd = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/square/chucks.bmp")));

        List<BufferedImage> borders = new LinkedList<>();
        borders.add(aaaa);
        borders.add(aaaa);
        List<BufferedImage> jaris = new LinkedList<>();
        jaris.add(bbbb);
        jaris.add(bbbb);
        List<BufferedImage> nimbus = new LinkedList<>();
        nimbus.add(cccc);
        nimbus.add(cccc);
        List<BufferedImage> chucks = new LinkedList<>();
        chucks.add(dddd);
        chucks.add(dddd);

        saveBMP(aaaa, "z-data/in/8/a2020/square/test/aaaa.bmp");
        saveBMP(bbbb, "z-data/in/8/a2020/square/test/bbbb.bmp");
        saveBMP(cccc, "z-data/in/8/a2020/square/test/cccc.bmp");
        saveBMP(dddd, "z-data/in/8/a2020/square/test/dddd.bmp");

        List<BufferedImage> result = new LinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            if (i < data.size() - 1) {
                borders = CutLayoutGenerator.get(borders.get(1), data.get(i));
                jaris = CutLayoutGenerator.get(jaris.get(1), data.get(i));
                nimbus = CutLayoutGenerator.get(nimbus.get(1), data.get(i));
                chucks = CutLayoutGenerator.get(chucks.get(1), data.get(i));
                BufferedImage border = RightLayoutGenerator.get(borders.get(0));
                BufferedImage jari = RightLayoutGenerator.get(jaris.get(0));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(0));
                BufferedImage chuck = RightLayoutGenerator.get(chucks.get(0));

                saveBMP(border, String.format("z-data/in/8/a2020/square/test/border-%s.bmp", i));
                saveBMP(jari, String.format("z-data/in/8/a2020/square/test/jari-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/8/a2020/square/test/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/8/a2020/square/test/chuck-%s.bmp", i));
                result.add(getBrocade(border, jari, nimbu, chuck));
            } else {

                BufferedImage border = RightLayoutGenerator.get(borders.get(1));
                BufferedImage jari = RightLayoutGenerator.get(jaris.get(1));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(1));
                BufferedImage chuck = RightLayoutGenerator.get(chucks.get(1));

                saveBMP(border, String.format("z-data/in/8/a2020/square/test/border-%s.bmp", i));
                saveBMP(jari, String.format("z-data/in/8/a2020/square/test/jari-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/8/a2020/square/test/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/8/a2020/square/test/chuck-%s.bmp", i));

                result.add(getBrocade(border, jari, nimbu, chuck));
            }
        }
        int i = 0;
        for (BufferedImage input : result) {
            saveBMP(input, String.format("z-data/out/8/a2020/test/square-%s.bmp", i++));

        }
        BufferedImage bufferedImage = AddLayoutGenerator.get(result);
        saveBMP(bufferedImage, "z-data/out/8/a2020/square.bmp");
    }

    private static BufferedImage getBrocade(BufferedImage border, BufferedImage jari, BufferedImage nimbu, BufferedImage chuck) throws IOException {
        if (border.getWidth() == 2) {
            return GoldJariBrocade.get(border, jari, nimbu, chuck);
        }
        if (border.getWidth() == 24) {
            return WhiteJariBrocade.get(border, jari, nimbu, chuck);
        }
        if (border.getWidth() == 8 || border.getWidth() == 4) {
            return PalluMeenaConversion.get(border, nimbu, chuck);
        }
        return null;
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }



}
