package com.varaprasadps.no3.a2020.design1.brocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ButtaConversion {

    public static void main(String[] args) throws IOException {
        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            data.add(12);
            data.add(84);
            data.add(10);
            data.add(2);
            data.add(4);
            data.add(2);
            data.add(4);
            data.add(2);
        }

        BufferedImage aaaa = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/3/a2020/design1/border/border.bmp"))));
        BufferedImage bbbb = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/3/a2020/design1/brocade/jari.bmp"))));
        BufferedImage cccc = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/3/a2020/design1/brocade/nimbu.bmp"))));
        BufferedImage dddd = LeftLayoutGenerator.get(StepLayoutGenerator.get(600, 3));

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

        saveBMP(aaaa, "z-data/in/3/a2020/design1/test/aaaa.bmp");
        saveBMP(bbbb, "z-data/in/3/a2020/design1/test/bbbb.bmp");
        saveBMP(cccc, "z-data/in/3/a2020/design1/test/cccc.bmp");
        saveBMP(dddd, "z-data/in/3/a2020/design1/test/dddd.bmp");

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

                saveBMP(border, String.format("z-data/in/3/a2020/design1/test/border-%s.bmp", i));
                saveBMP(gold, String.format("z-data/in/3/a2020/design1/test/gold-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/3/a2020/design1/test/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/3/a2020/design1/test/chuck-%s.bmp", i));
                result.add(getBrocade(border, gold, nimbu, chuck));
            } else {

                BufferedImage border = RightLayoutGenerator.get(borders.get(1));
                BufferedImage gold = RightLayoutGenerator.get(goldjaris.get(1));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(1));
                BufferedImage chuck = RightLayoutGenerator.get(chucks.get(1));

                saveBMP(border, String.format("z-data/in/3/a2020/design1/test/border-%s.bmp", i));
                saveBMP(gold, String.format("z-data/in/3/a2020/design1/test/jari-%s.bmp", i));
                saveBMP(nimbu, String.format("z-data/in/3/a2020/design1/test/nimbu-%s.bmp", i));
                saveBMP(chuck, String.format("z-data/in/3/a2020/design1/test/chuck-%s.bmp", i));
                result.add(getBrocade(border, gold, nimbu, chuck));
            }
        }
        int i = 0;
        for (BufferedImage input : result) {
            saveBMP(input, String.format("z-data/out/3/a2020/brocade-%s.bmp", i++));

        }
        BufferedImage bufferedImage = AddLayoutGenerator.get(result);
        saveBMP(bufferedImage, "z-data/out/3/a2020/design1/brocade.bmp");
    }

    private static BufferedImage getBrocade(BufferedImage border, BufferedImage gold, BufferedImage nimbu,
                                            BufferedImage chuck) throws IOException {
        if (border.getWidth() == 12 || border.getWidth() == 10 || border.getWidth() == 4) {
            return PalluMeenaConversion.get(border, nimbu, chuck);
        }
        if (border.getWidth() == 84 || border.getWidth() == 2) {
            return GoldJariBrocade.get(border, gold, nimbu, chuck);
        }
        return null;
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
