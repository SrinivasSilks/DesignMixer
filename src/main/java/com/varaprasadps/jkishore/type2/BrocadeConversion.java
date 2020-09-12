package com.varaprasadps.jkishore.type2;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.HorizontalFlipGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

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

        List<String> inputs = new LinkedList<>();
        inputs.add("z-data/in/kishore/3rani-480-2048.bmp");
        inputs.add("z-data/in/kishore/3jari-480-2048.bmp");
        inputs.add("z-data/in/kishore/3nimbu-480-2048.bmp");

        List<BufferedImage> inputBIs = new LinkedList<>();
        for (String input : inputs) {
            inputBIs.add(ImageIO.read(new File(input)));
        }

        BufferedImage bi = LeftLayoutGenerator.get(HorizontalFlipGenerator.get(ColumnRepeatGenerator.get(inputBIs)));
        displayPixels(bi);
        String out = "z-data/in/kishore/3brocade-%s-%s.bmp";
        saveBMP(bi, String.format(out, bi.getWidth(), bi.getHeight()));

        BufferedImage test = LeftLayoutGenerator.get(ColumnRepeatGenerator.get(inputBIs));
        displayPixels(test);
        String out2 = "z-data/in/kishore/4brocade-%s-%s.bmp";
        saveBMP(test, String.format(out2, test.getWidth(), test.getHeight()));
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
