package com.varaprasadps.no8.a2020.design1.varietybrocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AddLayoutButtaConversion {

    public static void main(String[] args) throws IOException {

        List<BufferedImage> result = new LinkedList<>();

        List<Integer> data = new LinkedList<>();
        data.add(78);
        data.add(148);
        data.add(78);
        data.add(146);
        data.add(78);
        data.add(148);
        data.add(78);
        data.add(146);

        BufferedImage aaaa = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/8/a2020/border/border.bmp"))));
        BufferedImage bbbb = LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/8/a2020/brocade2/jari.bmp"))));
        BufferedImage cccc = LeftLayoutGenerator.get(StepLayoutGenerator.get(900, 15));

        List<BufferedImage> borders = new LinkedList<>();
        borders.add(aaaa);
        borders.add(aaaa);
        List<BufferedImage> jaris = new LinkedList<>();
        jaris.add(bbbb);
        jaris.add(bbbb);

        List<BufferedImage> chucks = new LinkedList<>();
        chucks.add(cccc);
        chucks.add(cccc);

        saveBMP(aaaa, "z-data/in/8/a2020/test/aaaa.bmp");
        saveBMP(bbbb, "z-data/in/8/a2020/test/bbbb.bmp");

        for (int i = 0; i < data.size() - 1; i++) {
            borders = CutLayoutGenerator.get(borders.get(1), data.get(i));
            jaris = CutLayoutGenerator.get(jaris.get(1), data.get(i));
            chucks = CutLayoutGenerator.get(chucks.get(1), data.get(i));
            BufferedImage border = RightLayoutGenerator.get(borders.get(0));
            BufferedImage jari = RightLayoutGenerator.get(jaris.get(0));
            BufferedImage chuck = RightLayoutGenerator.get(chucks.get(0));
            saveBMP(border, String.format("z-data/in/8/a2020/test/border-%s.bmp", i));
            saveBMP(jari, String.format("z-data/in/8/a2020/test/jari-%s.bmp", i));
            saveBMP(chuck, String.format("z-data/in/8/a2020/test/chuck-%s.bmp", i));
            List<BufferedImage> brocades = new LinkedList<>();
            if (i % 2 != 0) {
                brocades.add(BrocadeStampConversion.rani(border, empty(border.getWidth())));
                brocades.add(BrocadeStampConversion.jari(getBorder(border), chuck));
                brocades.add(BrocadeStampConversion.nimbu(getBorder(border),chuck, jari));
                BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
                saveBMP(brocade, String.format("z-data/in/8/a2020/test/%s-brocade.bmp", i));
                result.add(brocade);
            } else {
                brocades.add(PalluStampConversion.rani(border, empty(border.getWidth())));
                brocades.add(PalluStampConversion.jari(PlainGenerator.get(border.getWidth(), 480), chuck));
                BufferedImage butta = LeftLayoutGenerator.get(getBrocade(brocades));
                saveBMP(butta, String.format("z-data/in/8/a2020/test/%s-butta.bmp", i));

                result.add(butta);
            }
        }

        saveBMP(borders.get(1), String.format("z-data/in/8/a2020/test/border-%s.bmp", data.size() - 1));
        saveBMP(jaris.get(1), String.format("z-data/in/8/a2020/test/jari-%s.bmp", data.size() - 1));
        saveBMP(chucks.get(1), String.format("z-data/in/8/a2020/test/chuck-%s.bmp", data.size() - 1));

        BufferedImage border = RightLayoutGenerator.get(borders.get(1));
        BufferedImage jari = RightLayoutGenerator.get(jaris.get(1));
        BufferedImage chuck = RightLayoutGenerator.get(chucks.get(1));

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(BrocadeStampConversion.rani(border, empty(border.getWidth())));
        brocades.add(BrocadeStampConversion.jari(getBorder(border), chuck));
        brocades.add(BrocadeStampConversion.nimbu(getBorder(border), chuck, jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        saveBMP(brocade, String.format("z-data/in/8/a2020/test/%s-brocade.bmp", data.size() -1));
        result.add(brocade);


        BufferedImage bufferedImage = AddLayoutGenerator.get(result);
        saveBMP(bufferedImage, "z-data/out/8/a2020/varietty-brocade.bmp");
    }

    private static BufferedImage empty(int width) {
        return EmptyGenerator.get(width, 60);
    }

    private static BufferedImage getBorder(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    private static BufferedImage getBrocade(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(String.format("Brocade => Width : %s, Height : %s", inputs.get(0).getWidth(), inputs.get(0).getHeight()));
        }
        return ColumnRepeatGenerator.get(inputs);
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
