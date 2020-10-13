package com.varaprasadps.vasu.no4.a2020;

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

        String out = "z-vasu/in/4/a2020/fullfile/fullfile.bmp";

        BufferedImage plain = ImageIO.read(new File("z-vasu/in/4/a2020/fullfile/plain.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-vasu/in/4/a2020/fullfile/pallu.bmp"));
        BufferedImage broc = ImageIO.read(new File("z-vasu/in/4/a2020/fullfile/broc.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(plain);
        inputBIs.add(plain);
        inputBIs.add(plain);
        inputBIs.add(CutLayoutGenerator.get(plain, 600).get(0));
        inputBIs.add(pallu);
        inputBIs.add(CutLayoutGenerator.get(broc, 19500).get(0));
        inputBIs.add(CutLayoutGenerator.get(plain, 510).get(1));
        inputBIs.add(plain);
        inputBIs.add(plain);

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
