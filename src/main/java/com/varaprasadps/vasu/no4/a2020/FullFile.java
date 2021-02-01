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

        String out = "z-vasu/in/4/a2020/fullfile/44fullfile.bmp";

        BufferedImage plain = ImageIO.read(new File("z-vasu/in/4/a2020/fullfile/plain.bmp"));
        BufferedImage pallu = ImageIO.read(new File("z-vasu/in/4/a2020/fullfile/pallu.bmp"));
//        BufferedImage broc = ImageIO.read(new File("z-vasu/out/4/a2020/2brocade-1792-39600.bmp"));
        BufferedImage broc4 = ImageIO.read(new File("z-vasu/out/4/a2020/4brocade-1792-13860.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(plain);
        inputBIs.add(plain);
        inputBIs.add(plain);
        inputBIs.add(CutLayoutGenerator.get(plain, 600).get(0));
        inputBIs.add(pallu);
        inputBIs.add(broc4);
        inputBIs.add(CutLayoutGenerator.get(broc4, 12870).get(0));
        inputBIs.add(CutLayoutGenerator.get(plain, 330).get(1));
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
