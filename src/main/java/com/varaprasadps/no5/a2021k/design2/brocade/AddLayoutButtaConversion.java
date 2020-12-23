package com.varaprasadps.no5.a2021k.design2.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutButtaConversion {

    public static void main(String[] args) throws IOException {
        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            data.add(14);
            data.add(30);
            data.add(16);
            data.add(74);
            data.add(32);
            data.add(14);
            data.add(2);
            data.add(72);
            data.add(30);
            data.add(16);
            data.add(74);
            data.add(32);
            data.add(14);
            data.add(2);
            data.add(58);
        }

        BufferedImage rania = ImageIO.read(new File("z-data/in/5/a2021k/design2/rani.bmp"));
        BufferedImage nimbua = ImageIO.read(new File("z-data/in/5/a2021k/design2/nimbu.bmp"));
        BufferedImage gJari = ImageIO.read(new File("z-data/in/5/a2021k/design2/g-jari.bmp"));
        BufferedImage wJari = ImageIO.read(new File("z-data/in/5/a2021k/design2/w-jari.bmp"));

        BufferedImage aaaa = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/5/a2021/design1/border/left.bmp"))));
        BufferedImage bbbb = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/5/a2021/design1/border/right.bmp"))));
        BufferedImage cccc = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(3, rania));
        BufferedImage dddd = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(3, nimbua));
        BufferedImage eeee = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(3, gJari));
        BufferedImage ffff = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(3, wJari));

        List<BufferedImage> lefts = new LinkedList<>();
        lefts.add(aaaa);
        lefts.add(aaaa);
        List<BufferedImage> rights = new LinkedList<>();
        rights.add(bbbb);
        rights.add(bbbb);
        List<BufferedImage> ranis = new LinkedList<>();
        ranis.add(cccc);
        ranis.add(cccc);
        List<BufferedImage> nimbus = new LinkedList<>();
        nimbus.add(dddd);
        nimbus.add(dddd);
        List<BufferedImage> golds = new LinkedList<>();
        golds.add(eeee);
        golds.add(eeee);
        List<BufferedImage> whites = new LinkedList<>();
        whites.add(ffff);
        whites.add(ffff);

        List<BufferedImage> result = new LinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            if (i < data.size() - 1) {
                lefts = CutLayoutGenerator.get(lefts.get(1), data.get(i));
                rights = CutLayoutGenerator.get(rights.get(1), data.get(i));
                ranis = CutLayoutGenerator.get(ranis.get(1), data.get(i));
                nimbus = CutLayoutGenerator.get(nimbus.get(1), data.get(i));
                golds = CutLayoutGenerator.get(golds.get(1), data.get(i));
                whites = CutLayoutGenerator.get(whites.get(1), data.get(i));

                BufferedImage left = RightLayoutGenerator.get(lefts.get(0));
                BufferedImage right = RightLayoutGenerator.get(rights.get(0));
                BufferedImage rani = RightLayoutGenerator.get(ranis.get(0));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(0));
                BufferedImage gold = RightLayoutGenerator.get(golds.get(0));
                BufferedImage white = RightLayoutGenerator.get(whites.get(0));
                result.add(getBrocade(left, right, rani, nimbu, gold, white, i));
            } else {

                BufferedImage left = RightLayoutGenerator.get(lefts.get(1));
                BufferedImage right = RightLayoutGenerator.get(rights.get(1));
                BufferedImage rani = RightLayoutGenerator.get(ranis.get(1));
                BufferedImage nimbu = RightLayoutGenerator.get(nimbus.get(1));
                BufferedImage gold = RightLayoutGenerator.get(golds.get(1));
                BufferedImage white = RightLayoutGenerator.get(whites.get(1));
                result.add(getBrocade(left, right, rani, nimbu, gold, white, i));
            }
        }
        int i = 0;
        for (BufferedImage input : result) {
            saveBMP(input, String.format("z-data/out/5/a2021k/design2/kishore-%s.bmp", i++));

        }
        BufferedImage bufferedImage = AddLayoutGenerator.get(result);
        saveBMP(bufferedImage, "z-data/out/5/a2021k/design2/kishore.bmp");
    }

    private static BufferedImage getBrocade(BufferedImage left, BufferedImage right,
                                            BufferedImage rani, BufferedImage nimbu,
                                            BufferedImage gold, BufferedImage white,
                                            int index) throws IOException {
        if (Arrays.asList(2, 32, 30).contains(left.getWidth())) {
            return PalluMeenaConversion.get(left, right, rani, nimbu);
        }
        if (Arrays.asList(74).contains(left.getWidth()) || (left.getWidth() == 14 && ((index + 1) % 15) != 0)) {
            return GoldJariBrocade.get(left, right, rani, gold, nimbu);
        }
        if (Arrays.asList(58, 16, 72).contains(left.getWidth()) || (left.getWidth() == 14 && ((index + 1) % 15) == 0)) {
            return WhiteJariBrocade.get(left, right, rani, white, nimbu);
        }
        System.out.println("nulllllllllllllllllllllllllllll     " + left.getWidth());
        return null;
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
