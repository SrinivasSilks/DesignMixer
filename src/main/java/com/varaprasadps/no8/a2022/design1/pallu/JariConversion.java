package com.varaprasadps.no8.a2022.design1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/8/a2022/design1/p-jari-%s-%s.bmp";

        final BufferedImage pallu = ImageIO.read(new File("z-data/in/8/a2022/design1/pallu/p-jari.bmp"));
        int width = pallu.getWidth();
        BufferedImage border = EmptyGenerator.get(width, 1120);
        BufferedImage chucks = ReverseGenerator.get(EmptyGenerator.get(width, 60));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 1)));

        //brocade
        inputBIs.add(pallu);
        //mango
        inputBIs.add(CutLayoutGenerator.get(pallu, 96).get(0));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //chucks
        inputBIs.add(chucks);
        //border
        inputBIs.add(border);

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 1).get(0)));
        //chakram
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
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
