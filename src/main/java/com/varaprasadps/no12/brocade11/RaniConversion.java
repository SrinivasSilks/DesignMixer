package com.varaprasadps.no12.brocade11;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/12/11/rani-%s-%s.bmp";

        BufferedImage teega = HorizontalRepeatGenerator.get(2, HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/TEEGA.bmp"))));
        BufferedImage figure = HorizontalRepeatGenerator.get(2, HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/FIGURE.bmp"))));
        BufferedImage banaras = HorizontalRepeatGenerator.get(2, HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/BANARAS.bmp"))));
        BufferedImage bugada = HorizontalRepeatGenerator.get(2, HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/BUGADA.bmp"))));

        final BufferedImage skirt = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/12/brocade11/RANI.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 32));

        //Khali
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 4)));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 8));

        //Locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 4));

        inputBIs.add(skirt);

        inputBIs.add(bugada);
        inputBIs.add(banaras);
        inputBIs.add(figure);
        inputBIs.add(teega);

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 4)));

        // Khali

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 4));

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 8));

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
