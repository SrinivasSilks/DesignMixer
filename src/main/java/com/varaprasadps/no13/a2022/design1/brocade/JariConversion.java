package com.varaprasadps.no13.a2022.design1.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.CutLayoutGenerator.get;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/13/a2022/design1/test-jari-%s-%s.bmp";

        final BufferedImage body = ImageIO.read(new File("z-data/in/13/a2022/design1/brocade/jari.bmp"));
        int width = body.getWidth();
        final BufferedImage checks = ReverseGenerator.get(ImageIO.read(new File("z-data/in/13/a2022/checks.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        //mispick
        inputBIs.add(get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        //left border
        inputBIs.add(EmptyGenerator.get(width, 320));

        //checks
        inputBIs.add(ReverseGenerator.get(checks));
        inputBIs.add(ReverseGenerator.get(checks));
        inputBIs.add(ReverseGenerator.get(checks));
        //body
        inputBIs.add(ReverseGenerator.get(body));
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));

        //border
        inputBIs.add(EmptyGenerator.get(width, 592));


        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //mispick
        inputBIs.add(ReverseGenerator.get(get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 7));

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
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
