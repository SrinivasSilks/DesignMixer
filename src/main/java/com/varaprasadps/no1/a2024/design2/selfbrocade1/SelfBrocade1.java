package com.varaprasadps.no1.a2024.design2.selfbrocade1;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.HorizontalFlipGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SelfBrocade1 {

    public static void main(final String[] args) throws IOException {
        JariConversion.main(null);
        RaniConversion.main(null);
        String out = "d/1/out/2024/design2/1selfbrocade-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("d/1/out/2024/design2/1self-rani-3400-2688.bmp");
        inputs.add("d/1/out/2024/design2/1self-jari-3400-2688.bmp");

        List<BufferedImage> inputBIs = new LinkedList<>();
        for (String input : inputs) {
            inputBIs.add(ImageIO.read(new File(input)));
        }

        BufferedImage bi = LeftLayoutGenerator.get(HorizontalFlipGenerator.get(ColumnRepeatGenerator.get(inputBIs)));
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
