package com.varaprasadps.bala.no7.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BrocadeConversion {

    public static void main(String[] args) throws IOException {
        BufferedImage border = ImageIO.read(new File("z-bala/in/7/a2020/border/border-equal.bmp"));
        BufferedImage butta = ImageIO.read(new File("z-bala/in/7/a2020/brocade/broc-butta-equal.bmp"));
        BufferedImage brocadebutta = VerticalFlipGenerator.get(get(border, butta));
        saveBMP(brocadebutta, "z-bala/out/7/a2020/brocade-butta.bmp");
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

    private static BufferedImage getBrocade(BufferedImage border, BufferedImage gold, int index) {
        if (border.getWidth() == 70 || border.getWidth() == 140) {
            return PlainPattern.get(border);
        }
        if (border.getWidth() == 280) {
            return PlainPatternWithButta.get(border, gold);
        }
        return null;
    }

    public static BufferedImage get(BufferedImage border3, BufferedImage jari) throws IOException {

        List<Integer> data = new LinkedList<>();
//        for (int i = 0; i < 2; i++) {
        data.add(70);
        data.add(280);
        data.add(140);
        data.add(280);
        data.add(140);
        data.add(280);
        data.add(140);
        data.add(280);
        data.add(70);
//        }

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
                saveBMP(border, "z-bala/in/7/a2020/border/border-" + i + ".bmp");
            } else {
                BufferedImage border = RightLayoutGenerator.get(borders.get(1));
                BufferedImage gold = RightLayoutGenerator.get(goldjaris.get(1));
                result.add(getBrocade(border, gold, i));
                saveBMP(border, "z-bala/in/7/a2020/border/border-" + i + ".bmp");
            }
        }
        return AddLayoutGenerator.get(result);
    }


}
