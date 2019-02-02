package com.varaprasadps.sr.no1.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-sr/out/1/p-rani-%s-%s.bmp";

        final BufferedImage brocade = ImageIO.read(new File("z-sr/in/1/pallu-edited/1/PALLU_RANI.bmp"));
        final BufferedImage border = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-sr/in/1/BORDER.bmp")));
        final BufferedImage banaras = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-sr/in/1/BANARAS.bmp")));
        final BufferedImage bugada = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-sr/in/1/BUGADA.bmp")));
        final BufferedImage teega = HorizontalRepeatGenerator.get(5, ImageIO.read(new File("z-sr/in/1/TEEGA.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(brocade.getWidth(), 192));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(brocade.getWidth(), 12));

        // Jari
        inputBIs.add(banaras);
        inputBIs.add(teega);
        inputBIs.add(bugada);

        inputBIs.add(brocade);
        inputBIs.add(border);

        // Box
        inputBIs.add(EmptyGenerator.get(brocade.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(brocade.getWidth(), 2)));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(brocade.getWidth(), 8));

        inputBIs.add(EmptyGenerator.get(brocade.getWidth(), 192));

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
