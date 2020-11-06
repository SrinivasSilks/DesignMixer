package com.varaprasadps.no6.a2020Nov.design2.brocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/a2020nov/design2/1jari-%s-%s.bmp";

        final BufferedImage body = HorizontalRepeatGenerator.get(14, ImageIO.read(new File("z-data/in/6/a2020nov/design2/brocade/jari.bmp")));
        BufferedImage meena = EmptyGenerator.get(2100, 400);
        BufferedImage jari = EmptyGenerator.get(2100, 400);

        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(body.getWidth(), 4), 2).get(0));
        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 10));

        //jari
        inputBIs.add(jari);
        inputBIs.add(body);
        //meena
        inputBIs.add(meena);

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(body.getWidth(), 4), 2).get(0)));
        //jamudu
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
