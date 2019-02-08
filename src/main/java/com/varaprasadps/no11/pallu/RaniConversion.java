package com.varaprasadps.no11.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/11/p-rani-%s-%s.bmp";

        BufferedImage teega = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/11/TEEGA.bmp"))));
        BufferedImage figure = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/11/FIGURE.bmp"))));
        BufferedImage banaras = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/11/BANARAS.bmp"))));
        BufferedImage bugada = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/11/BUGADA.bmp"))));

        final BufferedImage pallu = CutLayoutGenerator.get(HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/PALLU_RANI.bmp"))), 1460).get(0);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(pallu.getWidth(), 32));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(pallu.getWidth(), 4)));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 8));

        // Locking
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 4));

        inputBIs.add(pallu);

        // Locking
        inputBIs.add(PlainGenerator.get(pallu.getWidth(), 20));

        inputBIs.add(bugada);
        inputBIs.add(banaras);
        inputBIs.add(figure);
        inputBIs.add(teega);

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(pallu.getWidth(), 16));

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
