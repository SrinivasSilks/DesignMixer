package com.varaprasadps.no9.jr.design1.kadiyalu3;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluBrocConversion {

    public static void main(final String[] args) throws IOException {
        NimbuConversion.main(null);
        JariConversion.main(null);
        RaniConversion.main(null);
        String out = "z-data/out/9/a2021/jr/kadiyalubroc/kbroc-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-data/out/9/a2021/jr/kadiyalubroc/kbroc-rani-400-1824.bmp");
        inputs.add("z-data/out/9/a2021/jr/kadiyalubroc/kbroc-jari-400-1824.bmp");
        inputs.add("z-data/out/9/a2021/jr/kadiyalubroc/kbroc-nimbu-400-1824.bmp");

        List<BufferedImage> inputBIs = new LinkedList<>();
        for (String input : inputs) {
            inputBIs.add(ImageIO.read(new File(input)));
        }

        BufferedImage bi = LeftLayoutGenerator.get(ColumnRepeatGenerator.get(inputBIs));
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
