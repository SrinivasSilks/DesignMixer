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
        BufferedImage bugada = HorizontalRepeatGenerator.get(inputBI.getWidth() / 20, ImageIO.read(new File("z-data/in/8/a2020oct/bugada.bmp")));

        List<BufferedImage> images = CutLayoutGenerator.get(inputBI, 107);
        List<BufferedImage> result = new LinkedList<>();

        result.add(ReverseGenerator.get(bugada));
        result.add(images.get(1));
        BufferedImage test = ReverseGenerator.get(StepLayoutGenerator.get(inputBI.getWidth(), 1, 5));
        result.add(CutLayoutGenerator.get(test, 3).get(1));
        result.add(ReverseGenerator.get(StepLayoutGenerator.get(inputBI.getWidth(), 42, 5)));
        result.add(images.get(1));
        result.add(images.get(0));
        result.add(images.get(1));

        int x = 0;
        int y = 0;
        for (BufferedImage bi : result) {
            x = bi.getWidth();
            y += bi.getHeight();
        }
        BufferedImage output = ReverseGenerator.get(AddLayoutGenerator.get(x, y, result));
        saveBMP(output, "z-bala/in/5/border.bmp");
    }

    static void saveBMP(BufferedImage output, final String path) throws IOException {
        ImageIO.write(output, "bmp", new File(path));
    }
}
