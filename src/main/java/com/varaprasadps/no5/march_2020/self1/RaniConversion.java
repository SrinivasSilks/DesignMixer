package com.varaprasadps.no5.march_2020.self1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/march-2020/s-rani-%s-%s.bmp";

        List<BufferedImage> inputBIs = new LinkedList<>();
        final BufferedImage rani = ImageIO.read(new File("z-data/in/5/march-2020/brocade1/RANI.bmp"));
        final BufferedImage nimbu = ImageIO.read(new File("z-data/in/5/march-2020/brocade1/NIMBU.bmp"));

        int width = nimbu.getWidth();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //Box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //Khali
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(CutLayoutGenerator.get(nimbu, 800).get(0));
        inputBIs.add(CutLayoutGenerator.get(rani, 800).get(1));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 12), 6).get(0)));

        inputBIs.add(EmptyGenerator.get(width, 10));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
