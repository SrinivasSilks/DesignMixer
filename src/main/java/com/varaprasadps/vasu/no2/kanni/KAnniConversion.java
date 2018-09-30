package com.varaprasadps.vasu.no2.kanni;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.HorizontalFlipGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KAnniConversion {

    public static void main(final String[] args) throws IOException {
        JariConversion.main(null);
        String out = "z-vasu/out/2/second_kanni.bmp";

        List<BufferedImage> inputs = new LinkedList<>();
        inputs.add(HorizontalFlipGenerator.get(ImageIO.read(new File("z-vasu/in/2/NORMAL_PLAIN_3.bmp"))));
        inputs.add(ImageIO.read(new File("z-vasu/out/2/k-jari-10-1792.bmp")));

        BufferedImage bi = HorizontalFlipGenerator.get(ColumnRepeatGenerator.get(inputs));
        displayPixels(bi);
        saveBMP(bi, String.format(out, bi.getWidth(), bi.getHeight()));
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
