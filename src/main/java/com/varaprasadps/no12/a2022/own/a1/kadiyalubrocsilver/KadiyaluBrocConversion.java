package com.varaprasadps.no12.a2022.own.a1.kadiyalubrocsilver;

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
        SilverConversion.main(null);
        NimbuConversion.main(null);
        JariConversion.main(null);
        RaniConversion.main(null);
        String out = "z-data/out/12/a2022/own/1/kadiyalusilver/kbroc-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-data/out/12/a2022/own/1/kadiyalusilver/kbroc-rani-3600-1824.bmp");
        inputs.add("z-data/out/12/a2022/own/1/kadiyalusilver/kbroc-jari-3600-1824.bmp");
        inputs.add("z-data/out/12/a2022/own/1/kadiyalusilver/kbroc-nimbu-3600-1824.bmp");
        inputs.add("z-data/out/12/a2022/own/1/kadiyalusilver/kbroc-silver-3600-1824.bmp");

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
