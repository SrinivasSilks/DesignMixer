package com.varaprasadps.sk.no3.design1.kbrocsilver;

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
        GoldOneConversion.main(null);
        GoldTwoConversion.main(null);
        JariConversion.main(null);
        RaniConversion.main(null);
        String out = "z-sk/out/3/design1/silverbutta.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-sk/out/3/design1/2ka-broc-rani-22-961.bmp");
        inputs.add("z-sk/out/3/design1/2ka-broc-jari-22-961.bmp");
        inputs.add("z-sk/out/3/design1/2ka-broc-silver1-22-961.bmp");
        inputs.add("z-sk/out/3/design1/2ka-broc-silver2-22-961.bmp");

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
