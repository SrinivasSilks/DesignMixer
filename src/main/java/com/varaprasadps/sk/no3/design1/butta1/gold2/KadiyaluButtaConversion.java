package com.varaprasadps.sk.no3.design1.butta1.gold2;

import com.varaprasadps.image.ColumnRepeat2By1Generator;
import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluButtaConversion {

    public static void main(final String[] args) throws IOException {
        NimbuConversion.main(null);
        JariConversion.main(null);
        RaniConversion.main(null);
        String out = "z-sk/out/3/design1/butta1/2-BUTTA.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-sk/out/3/design1/butta1/2-ka-butta-rani-35-961.bmp");
        inputs.add("z-sk/out/3/design1/butta1/2-ka-butta-jari-35-961.bmp");

        List<BufferedImage> inputBIs = new LinkedList<>();
        for (String input : inputs) {
            inputBIs.add(ImageIO.read(new File(input)));
        }
        BufferedImage kuttu = ColumnRepeatGenerator.get(inputBIs);
        saveBMP(kuttu, String.format("z-sk/out/3/design1/butta1/2-ka-butta-%s-%s.bmp", kuttu.getWidth(), kuttu.getHeight()));

        List<BufferedImage> conversion = new LinkedList<>();
        conversion.add(kuttu);
        conversion.add(ImageIO.read(new File("z-sk/out/3/design1/butta1/2-ka-butta-nimbu-70-961.bmp")));
        BufferedImage bi = LeftLayoutGenerator.get(ColumnRepeat2By1Generator.get(conversion));
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
