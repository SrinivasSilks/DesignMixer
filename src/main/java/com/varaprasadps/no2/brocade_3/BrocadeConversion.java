package com.varaprasadps.no2.brocade_3;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.HorizontalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BrocadeConversion {

    public static void main(final String[] args) throws IOException {

        JariConversion.main(null);
        NimbuConversion.main(null);
        RaniConversion.main(null);

        String out = "z-data/out/2/3_1brocade-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-data/out/2/3_rani-900-1792.bmp");
        inputs.add("z-data/out/2/3_jari-900-1792.bmp");
        inputs.add("z-data/out/2/3_nimbu-900-1792.bmp");

        List<BufferedImage> inputBIs = new LinkedList<>();
        for (String input : inputs) {
            inputBIs.add(ImageIO.read(new File(input)));
        }

        BufferedImage bi = HorizontalFlipGenerator.get(ColumnRepeatGenerator.get(inputBIs));
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
