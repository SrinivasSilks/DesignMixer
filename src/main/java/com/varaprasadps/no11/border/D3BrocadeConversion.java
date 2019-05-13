package com.varaprasadps.no11.border;

import com.varaprasadps.image.ColumnRepeatGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class D3BrocadeConversion {

    public static void main(final String[] args) throws IOException {
        D3JariConversion.main(null);
        D3NimbuConversion.main(null);
        D3RaniConversion.main(null);

        String out = "z-data/out/11/new/d3brocade-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-data/out/11/new/d3rani-600-1824.bmp");
        inputs.add("z-data/out/11/new/d3jari-600-1824.bmp");
        inputs.add("z-data/out/11/new/d3nimbu-600-1824.bmp");


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
