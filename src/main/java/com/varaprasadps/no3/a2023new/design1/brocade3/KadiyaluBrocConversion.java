package com.varaprasadps.no3.a2023new.design1.brocade3;

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
        String out = "d/3/out/design1/3ka-brc-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("d/3/out/design1/3brc-rani-900-1440.bmp");
        inputs.add("d/3/out/design1/3brc-jari-900-1440.bmp");
        inputs.add("d/3/out/design1/3brc-gold-900-1440.bmp");
        inputs.add("d/3/out/design1/3brc-silver-900-1440.bmp");

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
