package com.varaprasadps.no12.anni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/12/anni-%s-%s.bmp";

        BufferedImage teega = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/border2/TEEGA.bmp")));
        BufferedImage figure = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/border2/FIGURE.bmp")));
        BufferedImage banaras = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/border2/BANARAS.bmp")));
        BufferedImage bugada = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/border2/BUGADA.bmp")));

        int width = teega.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        // Locking
        inputBIs.add(PlainGenerator.get(width, 4));

        // All over
        inputBIs.add(PlainGenerator.get(width, 480));

        // Skirt
        inputBIs.add(PlainGenerator.get(width, 1000));

        inputBIs.add(bugada);
        inputBIs.add(banaras);
        inputBIs.add(figure);
        inputBIs.add(teega);

        // Box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(EmptyGenerator.get(width, 2));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 8), 4).get(0)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

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
