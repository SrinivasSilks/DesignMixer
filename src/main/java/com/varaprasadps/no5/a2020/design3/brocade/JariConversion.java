package com.varaprasadps.no5.a2020.design3.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/a2020/design3/broc-jari-%s-%s.bmp";

        BufferedImage border = ReverseGenerator.get(ImageIO.read(new File("z-data/in/5/a2020/design3/border/border-jari.bmp")));
        BufferedImage body = ReverseGenerator.get(VerticalFlipGenerator.get(ImageIO.read(new File("z-data/in/5/a2020/design3/brocade/jari.bmp"))));

        List<BufferedImage> bodies = CutLayoutGenerator.get(body, 800);

        int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(bodies.get(0));
        inputBIs.add(VerticalFlipGenerator.get(CutLayoutGenerator.get(bodies.get(1), 20).get(1)));
        inputBIs.add(border);
        inputBIs.add(EmptyGenerator.get(width, 20));


        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 12), 6).get(0)));
        //khali
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
