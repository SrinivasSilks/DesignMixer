package com.varaprasadps.vasu.no4;

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

        String out = "z-vasu/in/4/new/4DESIGN_25_05.bmp";

        BufferedImage blouse = ImageIO.read(new File("z-vasu/in/4/output2/blouse.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-vasu/in/4/output2/pallu.bmp"));
        BufferedImage kongu = ImageIO.read(new File("z-vasu/in/4/output/kongu.bmp"));
        BufferedImage brocade = ImageIO.read(new File("z-vasu/in/4/25_05/brocade_25_05.bmp"));
        BufferedImage plain = ImageIO.read(new File("z-vasu/in/4/output2/plain.bmp"));

        List<BufferedImage> inputs = new LinkedList<>();

//        // blouse
//        inputs.add(blouse);
//        inputs.add(CutLayoutGenerator.get(blouse, 340).get(0));

//        // pallu
//        inputs.add(pallu);

//        // bondu
//        inputs.add(kongu);
//        inputs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(plain, 340).get(1), 90).get(0));
//        inputs.add(kongu);
//        inputs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(CutLayoutGenerator.get(plain, 340).get(1), 90).get(1), 10).get(0));

//
        // brocade
        inputs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(brocade, 1020).get(1), 300).get(1));
        inputs.add(brocade);
        inputs.add(brocade);
        inputs.add(brocade);
        inputs.add(brocade);
        inputs.add(CutLayoutGenerator.get(brocade, 2040).get(0));

//
//        // plain
//        inputs.add(plain);
//        inputs.add(plain);
//        inputs.add(plain);

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
