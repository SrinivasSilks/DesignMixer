package com.varaprasadps.giri.no28.joint;

import com.varaprasadps.image.AddLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JointConversion {

    public static void main(String[] args) throws IOException {
        String out = "z-giri/out/28/joint-%s-%s.bmp";

        final BufferedImage jari = ImageIO.read(new File("z-giri/in/28/PROCESSED/jari-anni-4-1824.bmp"));
        final BufferedImage resham = ImageIO.read(new File("z-giri/in/28/PROCESSED/resham-4-1824.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(jari);
        inputBIs.add(resham);

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
