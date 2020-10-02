package com.varaprasadps.bala.no7.brocade;

import com.varaprasadps.bala.no7.brocade.butta.FirstBoxConversion;
import com.varaprasadps.bala.no7.brocade.butta.SecondBoxConversion;
import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PlainPatternWithButta {

    public static void main(String[] args) throws IOException {
        BufferedImage abc = CutLayoutGenerator.get(LeftLayoutGenerator.get(ImageIO.read(new File("z-bala/in/7/a2020/brocade/butta.bmp"))), 483).get(1);
        BufferedImage butta = RightLayoutGenerator.get(CutLayoutGenerator.get(abc, 280).get(0));
        BufferedImage border = RightLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(ImageIO.read(new File("z-bala/in/7/a2020/border/border.bmp"))), 280).get(0));

        BufferedImage buttta = LeftLayoutGenerator.get(HorizontalFlipGenerator.get(get(border, butta)));
        saveBMP(buttta, "z-bala/out/7/a2020/sample-butta.bmp");
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
    private static BufferedImage getBrocade(BufferedImage border, BufferedImage gold, int index) {
        if (border.getWidth() == 4) {
            return FirstBoxConversion.get(border, gold);
        }
        if (border.getWidth() == 2 & index % 2 == 0) {
            return SecondBoxConversion.get(border, gold);
        }
        if (border.getWidth() == 2) {
            return FirstBoxConversion.get(border, gold);
        }
        return null;
    }

    public static BufferedImage get(BufferedImage border3, BufferedImage jari) {

        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            data.add(2);
            data.add(2);
            data.add(2);
            data.add(2);
            data.add(2);
            data.add(4);
        }

        BufferedImage aaaa = LeftLayoutGenerator.get(border3);
        BufferedImage bbbb = LeftLayoutGenerator.get(jari);

        List<BufferedImage> borders = new LinkedList<>();
        borders.add(aaaa);
        borders.add(aaaa);
        List<BufferedImage> goldjaris = new LinkedList<>();
        goldjaris.add(bbbb);
        goldjaris.add(bbbb);

        List<BufferedImage> result = new LinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            if (i < data.size() - 1) {
                borders = CutLayoutGenerator.get(borders.get(1), data.get(i));
                goldjaris = CutLayoutGenerator.get(goldjaris.get(1), data.get(i));
                BufferedImage border = RightLayoutGenerator.get(borders.get(0));
                BufferedImage gold = RightLayoutGenerator.get(goldjaris.get(0));
                result.add(getBrocade(border, gold, i));
            } else {

                BufferedImage border = RightLayoutGenerator.get(borders.get(1));
                BufferedImage gold = RightLayoutGenerator.get(goldjaris.get(1));
                result.add(getBrocade(border, gold, i));
            }
        }
        return AddLayoutGenerator.get(result);
    }
}
