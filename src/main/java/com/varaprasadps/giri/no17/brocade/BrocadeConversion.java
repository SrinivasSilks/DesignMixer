package com.varaprasadps.giri.no17.brocade;

import com.varaprasadps.image.ColumnRepeatGenerator;

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

        String out = "z-giri/out/17/brocade-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-giri/out/17/rani-1560-1440.bmp");
        inputs.add("z-giri/out/17/jari-1560-1440.bmp");
        inputs.add("z-giri/out/17/nimbu-1560-1440.bmp");

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
