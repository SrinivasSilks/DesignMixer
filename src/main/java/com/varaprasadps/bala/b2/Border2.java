package com.varaprasadps.bala.b2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Border2 {


    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/b2/2border-%s-%s.bmp";

        final BufferedImage bugada = HorizontalRepeatGenerator.get(2, ReverseGenerator.get(ImageIO.read(new File("z-bala/in/b2/bugada.bmp"))));
        final BufferedImage rudaraska = HorizontalRepeatGenerator.get(2, ReverseGenerator.get(ImageIO.read(new File("z-bala/in/b2/rudrashi.bmp"))));

        final BufferedImage sunanda = ReverseGenerator.get(ImageIO.read(new File("z-bala/in/b2/design2/repeat.bmp")));
        final BufferedImage namali = ReverseGenerator.get(ImageIO.read(new File("z-bala/in/b2/design2/avcxb.bmp")));

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
        for (int i = 0; i < 11; i++) {
            inputBIs.add(sunanda);
        }
        inputBIs.add(rudaraska);
        inputBIs.add(namali);

        inputBIs.add(StepLayoutGenerator.get(width, 6, 5));
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
