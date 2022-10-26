package com.varaprasadps.no3.a2022.design1.kadiyalu3Plus2;

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
        JariConversion.main(null);
        RaniConversion.main(null);
        GoldConversion.main(null);
        SilverConversion.main(null);
        String out = "z-data/out/3/a2022/design1/k3/2-3k-brc-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-data/out/3/a2022/design1/k3/2brc-rani-2640-1440.bmp");
        inputs.add("z-data/out/3/a2022/design1/k3/2brc-jari-2640-1440.bmp");
        inputs.add("z-data/out/3/a2022/design1/k3/2brc-gold-2640-1440.bmp");
        inputs.add("z-data/out/3/a2022/design1/k3/2brc-silver-2640-1440.bmp");

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
