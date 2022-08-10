package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CutLayoutGenerator {
    public static void main(final String[] args) throws IOException {
        String side = "z-data/in/5/a2022/jr/pallu/pallu-jari.bmp";
        String out = "z-data/in/5/a2022/jr/pallu/pallu-jari1.bmp";
        BufferedImage sidee = LeftLayoutGenerator.get(ImageIO.read(new File(side)));

        List<BufferedImage> result = new LinkedList<>();

        List<BufferedImage> images = CutLayoutGenerator.get(sidee, 306);
        result.add(images.get(0));
        result.add(CutLayoutGenerator.get(images.get(1), 200).get(1));

        int x = 0;
        int y = 0;
        for (BufferedImage bi : result) {
            x = bi.getWidth();
            y += bi.getHeight();
        }
        BufferedImage output = RightLayoutGenerator.get(AddLayoutGenerator.get(x, y, result));
        AddLayoutGenerator.saveBMP(output, out);
    }

    public static BufferedImage get(BufferedImage input, int sizeX, int index) {
        return RightLayoutGenerator.get(get(LeftLayoutGenerator.get(input), sizeX).get(index));
    }

    public static List<BufferedImage> get(BufferedImage inputBI, int sizeY) {
        final List<BufferedImage> res = new LinkedList<>();
        final List<BufferedImage> images = SplitGenerator.get(inputBI, inputBI.getHeight());

        List<BufferedImage> layoutsOne = new LinkedList<>();
        final int first = sizeY;
        for (int i = 0; i < first; i++) {
            layoutsOne.add(images.get(i));
        }
        res.add(AddLayoutGenerator.get(inputBI.getWidth(), sizeY, layoutsOne));
        List<BufferedImage> layoutsTwo = new LinkedList<>();
        for (int i = first; i < inputBI.getHeight(); i++) {
            layoutsTwo.add(images.get(i));
        }
        res.add(AddLayoutGenerator.get(inputBI.getWidth(), inputBI.getHeight() - sizeY, layoutsTwo));
        return res;
    }


    static void saveBMP(List<BufferedImage> outputBIs, final String path) throws IOException {
        for (int i = 0; i < outputBIs.size(); i++) {
            BufferedImage bi = outputBIs.get(i);
            String pathSave = String.format(path, i + 1, bi.getWidth(), bi.getHeight());
            ImageIO.write(bi, "bmp", new File(pathSave));
        }
    }

}
