package com.varaprasadps.no6.a2020Nov.design3.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/a2021/design1/p-jari-%s-%s.bmp";

        final BufferedImage pallu = ImageIO.read(new File("z-data/in/6/a2020nov/design1/pallu/pallu-jari.bmp"));
        BufferedImage meenainput = HorizontalRepeatGenerator.get(20, ImageIO.read(new File("z-data/in/6/a2021/design1/border/jari/meena.bmp")));
        BufferedImage jariinput = HorizontalRepeatGenerator.get(20, ImageIO.read(new File("z-data/in/6/a2021/design1/border/jari/jari.bmp")));
        final BufferedImage meena = ReverseGenerator.get(RightLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(meenainput), 2000).get(0)));
        final BufferedImage jari = ReverseGenerator.get(RightLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(jariinput), 2000).get(0)));


        int width = pallu.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //locking
        inputBIs.add(StepLayoutGenerator.get(width));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //jamudu
        inputBIs.add(EmptyGenerator.get(width, 10));

        //jari
        inputBIs.add(jari);
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        //meena
        inputBIs.add(meena);

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
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
