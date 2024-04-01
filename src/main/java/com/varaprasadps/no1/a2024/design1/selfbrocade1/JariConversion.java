package com.varaprasadps.no1.a2024.design1.selfbrocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/1/out/2024/design1/1self-jari-%s-%s.bmp";

        final BufferedImage body = HorizontalRepeatGenerator.get(8, ImageIO.read(new File("d/1/in/2024/design1/brocade1/jari.bmp")));
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 128));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(body.getWidth(), 4), 2).get(0));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));

        //left
        inputBIs.add(VerticalFlipGenerator.get(EmptyGenerator.get(width, 450)));
        inputBIs.add(EmptyGenerator.get(width, 12));
        //locking
        inputBIs.add(StepLayoutGenerator.get(width, 1));


        inputBIs.add(CutLayoutGenerator.get(body, body.getHeight() - 156 - 480).get(1));
        inputBIs.add(body);

        //locking
        inputBIs.add(StepLayoutGenerator.get(width, 1));
        inputBIs.add(EmptyGenerator.get(width, 12));
        //right
        inputBIs.add(EmptyGenerator.get(width, 450));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(body.getWidth(), 4), 2).get(0)));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

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
