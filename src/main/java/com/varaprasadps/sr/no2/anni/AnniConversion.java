package com.varaprasadps.sr.no2.anni;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-sr/out/2/anni-%s-%s.bmp";

        final BufferedImage border = ImageIO.read(new File("z-sr/in/2/BORDER.bmp"));
        final BufferedImage banaras = ImageIO.read(new File("z-sr/in/2/BANARAS.bmp"));
        final BufferedImage bugada = ImageIO.read(new File("z-sr/in/2/BUGADA.bmp"));
        final BufferedImage teega = ImageIO.read(new File("z-sr/in/2/TEEGA.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        int width = border.getWidth();
        inputBIs.add(EmptyGenerator.get(width, 192));

        // Box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(EmptyGenerator.get(width, 2));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        // Jari
        inputBIs.add(banaras);
        inputBIs.add(teega);
        inputBIs.add(bugada);

        inputBIs.add(PlainGenerator.get(width, 480));
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
        BufferedImage bi = HorizontalFlipGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
