package com.varaprasadps.no7.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/7/p-rani-%s-%s.bmp";

        BufferedImage teega = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/7/border4/TEEGA.bmp"))));
        BufferedImage figure = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/7/border4/FIGURE.bmp"))));
        BufferedImage banaras = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/7/border4/BANARAS.bmp"))));
        BufferedImage bugada = HorizontalFlipGenerator.get(HorizontalRepeatGenerator.get(3, ImageIO.read(new File("z-data/in/7/border4/BUGADA.bmp"))));

        final BufferedImage pallu = CutLayoutGenerator.get(HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/7/pallu1/PALLU_RANI.bmp"))), 1460).get(0);
        int width = pallu.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));

        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 6));

        // Locking
        inputBIs.add(PlainGenerator.get(width, 4));

        inputBIs.add(pallu);

        // Locking
        inputBIs.add(PlainGenerator.get(width, 20));

        inputBIs.add(bugada);
        inputBIs.add(banaras);
        inputBIs.add(figure);
        inputBIs.add(teega);

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 14));

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
