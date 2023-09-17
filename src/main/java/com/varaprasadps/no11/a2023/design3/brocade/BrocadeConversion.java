package com.varaprasadps.no11.a2023.design3.brocade;

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
        String out = "d/11/out/design3/1brocade-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("d/11/out/design3/1rani-3360-1824.bmp");
        inputs.add("d/11/out/design3/1gold-3360-1824.bmp");
        inputs.add("d/11/out/design3/1jari-3360-1824.bmp");
        inputs.add("d/11/out/design3/1nimbu-3360-1824.bmp");

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
