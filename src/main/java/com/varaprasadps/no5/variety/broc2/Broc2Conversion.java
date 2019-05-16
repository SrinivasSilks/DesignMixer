package com.varaprasadps.no5.variety.broc2;

import com.varaprasadps.image.ColumnRepeatGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Broc2Conversion {


    public static void main(final String[] args) throws IOException {

        JariConversion.main(null);
        WhiteConversion.main(null);
        RaniConversion.main(null);

        String out = "z-data/out/5/variety/broc2-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-data/out/5/variety/b2-rani-425-1824.bmp");
        inputs.add("z-data/out/5/variety/b2-jari-425-1824.bmp");
        inputs.add("z-data/out/5/variety/b2-white-425-1824.bmp");

        List<BufferedImage> inputBIs = new LinkedList<>();
        for (String input : inputs) {
            inputBIs.add(ImageIO.read(new File(input)));
        }

        BufferedImage bi = ColumnRepeatGenerator.get(inputBIs);
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
