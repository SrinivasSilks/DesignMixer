package com.varaprasadps.no8.a2023.design2.kbutta;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.PlainGenerator;
import com.varaprasadps.no8.a2023.design2.kplain.KadiyaluBrocade;
import com.varaprasadps.no8.a2023.design2.kplain.KadiyaluPlain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluButta {


    public static void main(String[] args) throws IOException {
        test("z-data/out/8/a2023/design2/butta-%s-%s.bmp");
    }

    public static void test(String path) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("z-data/in/8/a2023/design2/border/right.bmp")));
        BufferedImage left = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("z-data/in/8/a2023/design2/border/left.bmp")));
        BufferedImage leftFirst = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("z-data/in/8/a2023/design2/border/left-first.bmp")));
        BufferedImage butta = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-data/in/8/a2023/design2/butta/jari.bmp")));

        List<Integer> test = new LinkedList<>();
        test.add(94);
        test.add(50);
        test.add(190);
        test.add(50);

        test.add(190);
        test.add(50);
        test.add(190);
        test.add(50);

        test.add(190);
        test.add(50);
        test.add(190);
        test.add(50);

        test.add(190);
        test.add(50);
        test.add(190);
        test.add(50);

        test.add(190);
        test.add(50);
        test.add(190);
        test.add(50);
        test.add(96);

        int prev = 0;
        List<BufferedImage> result = new LinkedList<>();
        for (int i = 0; i < test.size(); i++) {
            int slicePixel = test.get(i);
            BufferedImage leftFirstSliced = getImage(leftFirst, prev, slicePixel);
            BufferedImage leftSliced = getImage(left, prev, slicePixel);
            BufferedImage rightSliced = getImage(right, prev, slicePixel);
            BufferedImage buttaSliced = getImage(butta, prev, slicePixel);
            boolean isThreeCard = slicePixel == 50;
            if(isThreeCard){
                saveBMP(buttaSliced, String.format("z-data/out/8/a2023/design2/test/image-%s-%s.bmp",i, prev));
            }
            System.out.println(String.format(" i = %s, silcePixel = %s, isThreeCard = %s", i, slicePixel, isThreeCard));

            result.add(get(slicePixel, leftFirstSliced, leftSliced, rightSliced, buttaSliced, isThreeCard));
            prev += slicePixel;
        }

        BufferedImage outputImage = AddLayoutGenerator.get(result);
        saveBMP(outputImage, String.format(path, outputImage.getWidth(), outputImage.getHeight()));
    }

    private static BufferedImage getImage(BufferedImage leftFirst, int prev, int slicePixel) {
        BufferedImage firstImage = getFirstImage(leftFirst, prev);
        if (firstImage.getWidth() == slicePixel) {
            return firstImage;
        }
        return CutLayoutGenerator.get(firstImage, slicePixel, 0);
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

    private static BufferedImage get(int slicePixel, BufferedImage leftFirstSliced, BufferedImage leftSliced, BufferedImage rightSliced, BufferedImage buttaSliced, boolean isThreeCard) {
        if (isThreeCard) {
            return KadiyaluBrocade.merge(leftFirstSliced, leftSliced, rightSliced, PlainGenerator.get(slicePixel, 960), buttaSliced);
        } else {
            return KadiyaluPlain.merge(leftFirstSliced, leftSliced, rightSliced);
        }
    }

    private static BufferedImage getFirstImage(BufferedImage leftFirst, int prev) {
        if (prev == 0) {
            return leftFirst;
        }
        return CutLayoutGenerator.get(leftFirst, prev, 1);
    }

}
