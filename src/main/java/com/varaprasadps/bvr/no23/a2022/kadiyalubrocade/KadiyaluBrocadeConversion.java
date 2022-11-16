package com.varaprasadps.bvr.no23.a2022.kadiyalubrocade;

import com.varaprasadps.image.ColumnRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluBrocadeConversion {

    public static void main(final String[] args) throws IOException {
        NimbuConversion.main(null);
        JariConversion.main(null);
        RaniConversion.main(null);
        String out = "z-bvr/out/23/a2022/kadiyalu-brocade-%s-%s.bmp";

        List<String> inputs = new LinkedList<>();
        inputs.add("z-bvr/out/23/a2022/k-broc-rani-100-992.bmp");
        inputs.add("z-bvr/out/23/a2022/k-broc-jari-100-992.bmp");
        inputs.add("z-bvr/out/23/a2022/k-broc-nimbu-100-992.bmp");

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
