package com.varaprasadps.no11.kanni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/11/k-rani-%s-%s.bmp";

        BufferedImage teega = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/border3/TEEGA.bmp")));
        BufferedImage figure = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/border3/FIGURE.bmp")));
        BufferedImage banaras = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/border3/BANARAS.bmp")));
        BufferedImage bugada = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/border3/BUGADA.bmp")));


        final int width = teega.getWidth();

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
        //Allover
        inputBIs.add(PlainGenerator.get(width, 480));
        //Skirt
        inputBIs.add(PlainGenerator.get(width, 1000));

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
