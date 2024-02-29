package com.varaprasadps.no4.a2024.design1.kadiyaluplain;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluPlainConversion {

    public static void main(final String[] args) throws IOException {
        JariConversion.main(null);
        RaniConversion.main(null);
        String out = "d/4/out/a2024/design1/kadiyalu-plain-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("d/4/out/a2024/design1/kplain-rani-240-1440.bmp");
        inputs.add("d/4/out/a2024/design1/kplain-jari-240-1440.bmp");

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
