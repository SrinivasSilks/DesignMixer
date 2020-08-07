package com.varaprasadps.no8.a2020.design1.varietybrocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutButtaConversion {

    public static void main(String[] args) throws IOException {
        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 25; i++) {
            data.add(10);
            data.add(2);
        }

        BufferedImage aaaa = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/border/border.bmp")));
        BufferedImage bbbb = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/4play/jari.bmp")));
        BufferedImage cccc = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/4play/nimbu.bmp")));
        BufferedImage dddd = LeftLayoutGenerator.get(StepLayoutGenerator.get(300, 15));

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

        saveBMP(aaaa, "z-data/in/8/a2020/4play/test/aaaa.bmp");
        saveBMP(bbbb, "z-data/in/8/a2020/4play/test/bbbb.bmp");
        saveBMP(cccc, "z-data/in/8/a2020/4play/test/cccc.bmp");
        saveBMP(dddd, "z-data/in/8/a2020/4play/test/dddd.bmp");

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

                saveBMP(border, String.format("z-data/in/8/a2020/4play/test/border-%s.bmp", i));
                saveBMP(jari, String.format("z-data/in/8/a2020/4play/test/jari-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/8/a2020/4play/test/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/8/a2020/4play/test/chuck-%s.bmp", i));
                result.add(getBrocade(i, border, jari, nimbu, chuck));
            } else {

                BufferedImage border = RightLayoutGenerator.get(borders.get(1));
                BufferedImage jari = RightLayoutGenerator.get(jaris.get(1));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(1));
                BufferedImage chuck = RightLayoutGenerator.get(chucks.get(1));

                saveBMP(border, String.format("z-data/in/8/a2020/4play/test/border-%s.bmp", i));
                saveBMP(jari, String.format("z-data/in/8/a2020/4play/test/jari-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/8/a2020/4play/test/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/8/a2020/4play/test/chuck-%s.bmp", i));

                result.add(getBrocade(i, border, jari, nimbu, chuck));

            }
        }
        BufferedImage bufferedImage = AddLayoutGenerator.get(result);
        saveBMP(bufferedImage, "z-data/out/8/a2020/variety-brocade2.bmp");
    }

    private static BufferedImage getBrocade(int i, BufferedImage border, BufferedImage jari, BufferedImage nimbu, BufferedImage chucks) throws IOException {
        if (i % 2 == 0) {
            return WhiteJariBrocade.get(border, jari, nimbu, chucks);
        } else {
            return GoldJariBrocade.get(border, jari, nimbu, chucks);
        }
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
