package com.varaprasadps.no2.a2021.design1.brocade2;

import com.varaprasadps.image.*;
import com.varaprasadps.no2.a2021.design1.generic.ThreePlayConversion;
import com.varaprasadps.no2.a2021.design1.generic.TwoPlayConversion;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutButtaConversion {

    public static String basePath = "z-data/out/2/a2021/design1/";

    public static void main(String[] args) throws IOException {
        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            data.add(48);
            data.add(496);
            data.add(104);
            data.add(496);
            data.add(56);
        }

        BufferedImage aaaa = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/2/a2021/design1/border/right.bmp"))));
        BufferedImage bbbb = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/2/a2021/design1/border/left.bmp"))));
        BufferedImage cccc = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/2/a2021/design1/brocade2/nimbu.bmp"))));
        BufferedImage dddd = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/2/a2021/design1/brocade2/jari.bmp"))));

        List<BufferedImage> rights = new LinkedList<>();
        rights.add(aaaa);
        rights.add(aaaa);
        List<BufferedImage> lefts = new LinkedList<>();
        lefts.add(bbbb);
        lefts.add(bbbb);
        List<BufferedImage> nimbus = new LinkedList<>();
        nimbus.add(cccc);
        nimbus.add(cccc);
        List<BufferedImage> jaris = new LinkedList<>();
        jaris.add(dddd);
        jaris.add(dddd);

        List<BufferedImage> result = new LinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            if (i < data.size() - 1) {
                rights = CutLayoutGenerator.get(rights.get(1), data.get(i));
                lefts = CutLayoutGenerator.get(lefts.get(1), data.get(i));
                nimbus = CutLayoutGenerator.get(nimbus.get(1), data.get(i));
                jaris = CutLayoutGenerator.get(jaris.get(1), data.get(i));

                BufferedImage right = RightLayoutGenerator.get(rights.get(0));
                BufferedImage left = RightLayoutGenerator.get(lefts.get(0));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(0));
                BufferedImage jari = RightLayoutGenerator.get(jaris.get(0));
                result.add(getBrocade(right, left, nimbu, jari));
            } else {

                BufferedImage right = RightLayoutGenerator.get(rights.get(1));
                BufferedImage left = RightLayoutGenerator.get(lefts.get(1));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(1));
                BufferedImage jari = RightLayoutGenerator.get(jaris.get(1));
                result.add(getBrocade(right, left, nimbu, jari));
            }
        }
        int i = 0;
        for (BufferedImage input : result) {
            saveBMP(input, String.format(basePath + "test-%s.bmp", i++));

        }
        BufferedImage bufferedImage = AddLayoutGenerator.get(result);
        saveBMP(bufferedImage, basePath + "brocade2.bmp");
    }

    private static BufferedImage getBrocade(BufferedImage right, BufferedImage left, BufferedImage nimbu,
                                            BufferedImage jari) throws IOException {
        if (Arrays.asList(48, 56, 104).contains(right.getWidth())) {
            return TwoPlayConversion.get(right, left, EmptyGenerator.get(right.getWidth(), 1200), nimbu);
        }
        if (Arrays.asList(496).contains(right.getWidth())) {
            return ThreePlayConversion.get(right, left, EmptyGenerator.get(right.getWidth(), 1200), nimbu, jari);
        }
        return null;
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
