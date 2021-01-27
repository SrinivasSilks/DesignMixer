package com.varaprasadps.bala.no5;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Border {

    public static void main(final String[] args) throws IOException {
        BufferedImage inputBI = ImageIO.read(new File("z-bala/in/5/adf.bmp"));
        BufferedImage asgfgfdg = ImageIO.read(new File("z-bala/in/5/asgfgfdg.bmp"));
        BufferedImage pek = ImageIO.read(new File("z-bala/in/5/pek.bmp"));
        BufferedImage bugada = HorizontalRepeatGenerator.get(inputBI.getWidth() / 20, ImageIO.read(new File("z-data/in/8/a2020oct/bugada.bmp")));
        BufferedImage test = ReverseGenerator.get(StepLayoutGenerator.get(inputBI.getWidth(), 1, 5));

        List<BufferedImage> images = CutLayoutGenerator.get(inputBI, 107);
        List<BufferedImage> images2 = CutLayoutGenerator.get(asgfgfdg, 48);
        List<BufferedImage> peks = CutLayoutGenerator.get(pek, 87);

        List<BufferedImage> result = new LinkedList<>();

        result.add(images2.get(1));
        result.add(asgfgfdg);
        result.add(peks.get(0));
        result.add(images2.get(1));
        result.add(asgfgfdg);

        result.add(CutLayoutGenerator.get(test, 3).get(1));
        result.add(VerticalFlipGenerator.get(CutLayoutGenerator.get(images.get(1), images.get(1).getHeight() - 37).get(0)));
        result.add(images.get(0));

        result.add(images.get(1));
        for (int i = 0; i < 11; i++) {
            result.add(test);
        }
        result.add(CutLayoutGenerator.get(test, 2).get(0));


        int x = 0;
        int y = 0;
        for (BufferedImage bi : result) {
            x = bi.getWidth();
            y += bi.getHeight();
        }
        BufferedImage output = ReverseGenerator.get(AddLayoutGenerator.get(x, y, result));
        saveBMP(output, "z-bala/in/5/right.bmp");
    }

    static void saveBMP(BufferedImage output, final String path) throws IOException {
        ImageIO.write(output, "bmp", new File(path));
    }
}
