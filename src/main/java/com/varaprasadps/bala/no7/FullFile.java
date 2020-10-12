package com.varaprasadps.bala.no7;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.RightLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FullFile {

    public static void main(String[] args) throws IOException {

        String out = "z-bala/out/7/a2020/fullfile2.bmp";

        BufferedImage plain = ImageIO.read(new File("z-bala/in/7/a2020/fullfile/2/plain-chucks.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-bala/in/7/a2020/fullfile/2/pallu.bmp"));

        BufferedImage butta = ImageIO.read(new File("z-bala/in/7/a2020/fullfile/2/brocade-butta.bmp"));
        BufferedImage butta2 = ImageIO.read(new File("z-bala/in/7/a2020/fullfile/2/brocade-butta2.bmp"));

        BufferedImage blouse = ImageIO.read(new File("z-bala/in/7/a2020/fullfile/2/blouse.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(plain, plain.getHeight() - 40).get(0), plain.getHeight() - 40 - 150).get(1));

        inputBIs.add(pallu);

        inputBIs.add(butta);
        inputBIs.add(butta);
        inputBIs.add(butta);
        inputBIs.add(butta);

        inputBIs.add(butta2);
        inputBIs.add(butta2);
        inputBIs.add(butta2);
        inputBIs.add(butta2);
        inputBIs.add(butta2);
        inputBIs.add(butta2);

        inputBIs.add(plain);

        inputBIs.add(blouse);
        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(plain, 560 - 250).get(1), 250).get(0));


        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = RightLayoutGenerator.get(LeftLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));

    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
