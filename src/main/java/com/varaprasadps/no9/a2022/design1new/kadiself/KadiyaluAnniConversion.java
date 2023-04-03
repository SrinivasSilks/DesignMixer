package com.varaprasadps.no9.a2022.design1new.kadiself;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.no9.a2022.design1new.kadiyalu3.NimbuConversion;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluAnniConversion {

    public static void main(final String[] args) throws IOException {
        NimbuConversion.main(null);
        JariConversion.main(null);
        RaniConversion.main(null);
        String out = "z-data/out/9/a2022/design1new/3kadiyalu-broc-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-data/out/9/a2022/design1new/3kadiyalu-broc-rani-360-1824.bmp");
        inputs.add("z-data/out/9/a2022/design1new/3kadiyalu-broc-jari-360-1824.bmp");

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
