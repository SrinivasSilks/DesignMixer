package com.varaprasadps.no11.a2023.design3.old.brocade4;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BrocadeConversion {

    public static void main(final String[] args) throws IOException {
        NimbuConversion.main(null);
        JariConversion.main(null);
        GoldConversion.main(null);
        RaniConversion.main(null);
        String out = "d/11/out/design3/4brocade-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("d/11/out/design3/4rani-5280-1824.bmp");
        inputs.add("d/11/out/design3/4gold-5280-1824.bmp");
        inputs.add("d/11/out/design3/4jari-5280-1824.bmp");
        inputs.add("d/11/out/design3/4nimbu-5280-1824.bmp");

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
