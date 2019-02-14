package com.varaprasadps.sr.no2.brocade_1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-sr/out/2/rani-%s-%s.bmp";

        final BufferedImage brocade = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("z-sr/in/2/brocade/RANI.bmp")));
        final BufferedImage border = HorizontalRepeatGenerator.get(11, ImageIO.read(new File("z-sr/in/2/BORDER.bmp")));
        final BufferedImage banaras = HorizontalRepeatGenerator.get(11, ImageIO.read(new File("z-sr/in/2/BANARAS.bmp")));
        final BufferedImage bugada = HorizontalRepeatGenerator.get(11, ImageIO.read(new File("z-sr/in/2/BUGADA.bmp")));
        final BufferedImage teega = HorizontalRepeatGenerator.get(11, ImageIO.read(new File("z-sr/in/2/TEEGA.bmp")));

        int width = brocade.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 192));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 4)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        // Jari
        inputBIs.add(banaras);
        inputBIs.add(teega);
        inputBIs.add(bugada);

        inputBIs.add(brocade);
        inputBIs.add(border);

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

        inputBIs.add(EmptyGenerator.get(width, 192));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
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
