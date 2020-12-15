package com.varaprasadps.bala.b2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Border {


    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/b2/border-%s-%s.bmp";

        final BufferedImage bugada = ReverseGenerator.get(ImageIO.read(new File("z-bala/in/b2/bugada.bmp")));
        final BufferedImage namali = ReverseGenerator.get(ImageIO.read(new File("z-bala/in/b2/figure.bmp")));
        final BufferedImage sunanda = ReverseGenerator.get(ImageIO.read(new File("z-bala/in/b2/sunanda.bmp")));
        final BufferedImage rudaraska = ReverseGenerator.get(ImageIO.read(new File("z-bala/in/b2/rudrashi.bmp")));

        int width = bugada.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

        //old
        inputBIs.add(EmptyGenerator.get(width, 480));
        //border
        inputBIs.add(bugada);
        inputBIs.add(StepLayoutGenerator.get(width, 2, 6));
        inputBIs.add(namali);
        inputBIs.add(rudaraska);
        for (int i = 0; i < 5; i++) {
            inputBIs.add(sunanda);
        }
        inputBIs.add(rudaraska);
        inputBIs.add(namali);
        inputBIs.add(rudaraska);
        for (int i = 0; i < 4; i++) {
            inputBIs.add(sunanda);
        }
        inputBIs.add(CutLayoutGenerator.get(sunanda, 21).get(0));
        inputBIs.add(rudaraska);
        inputBIs.add(namali);
        inputBIs.add(sunanda);
        inputBIs.add(rudaraska);
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

        inputBIs.add(EmptyGenerator.get(width, 128));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = VerticalFlipGenerator.get(LeftLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
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
