package com.varaprasadps.no11.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/11/2/rani-%s-%s.bmp";

        BufferedImage teega = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/TEEGA.bmp")));
        BufferedImage figure = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/FIGURE.bmp")));
        BufferedImage banaras = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/BANARAS.bmp")));
        BufferedImage bugada = HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/BUGADA.bmp")));

        final BufferedImage skirt = CutLayoutGenerator.get(HorizontalFlipGenerator.get(ImageIO.read(new File("z-data/in/11/brocade2/RANI.bmp"))), 1480).get(0);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 32));

        // Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 4)));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 8));

        // Locking
        inputBIs.add(PlainGenerator.get(skirt.getWidth(), 4));

        inputBIs.add(skirt);

        inputBIs.add(bugada);
        inputBIs.add(banaras);
        inputBIs.add(figure);
        inputBIs.add(teega);

        // Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 16));

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
