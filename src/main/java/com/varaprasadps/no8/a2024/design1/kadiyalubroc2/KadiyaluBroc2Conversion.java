package com.varaprasadps.no8.a2024.design1.kadiyalubroc2;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluBroc2Conversion {

    public static void main(final String[] args) throws IOException {
        SilverConversion.main(null);
        NimbuConversion.main(null);
        JariConversion.main(null);
        RaniConversion.main(null);
        String out = "d/8/out/2024/design1/2kadiyalu-broc-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("d/8/out/2024/design1/2kadiyalu-rani-900-1824.bmp");
        inputs.add("d/8/out/2024/design1/2kadiyalu-jari-900-1824.bmp");
        inputs.add("d/8/out/2024/design1/2kadiyalu-nimbu-900-1824.bmp");
        inputs.add("d/8/out/2024/design1/2kadiyalu-silver-900-1824.bmp");

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
