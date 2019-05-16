package com.varaprasadps.chandra.no1;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FullFileGenerator {

    public static void main(final String[] args) throws IOException {

        String out = "z-chandra/in/1/1DESIGN2.bmp";


        BufferedImage plain = ImageIO.read(new File("z-chandra/in/1/output/blouse.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-chandra/in/1/output/pallu.bmp"));
        BufferedImage kongu = ImageIO.read(new File("z-chandra/in/1/output/kongu.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-chandra/in/1/output/brocade.bmp"));
        final List<BufferedImage> abc = CutLayoutGenerator.get(CutLayoutGenerator.get(plain, 180).get(1), 90);

        List<BufferedImage> inputs = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            inputs.add(plain);
        }
        inputs.add(pallu);
        inputs.add(abc.get(0));
        inputs.add(kongu);
        inputs.add(abc.get(1));
        inputs.add(kongu);
        inputs.add(brocade);
        inputs.add(CutLayoutGenerator.get(plain,260).get(1));
        for (int i = 0; i < 4; i++) {
            inputs.add(plain);
        }

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputs);
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
