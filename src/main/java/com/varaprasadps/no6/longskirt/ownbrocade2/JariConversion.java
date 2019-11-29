package com.varaprasadps.no6.longskirt.ownbrocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/6/o2jari-%s-%s.bmp";
        final BufferedImage body = HorizontalRepeatGenerator.get(27, ImageIO.read(new File("z-data/in/6/longskirt/2/NIMBU.bmp")));

        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(body.getWidth(), 1)));
        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(body.getWidth(), 4), 2).get(0));
        //Achu Khali
        inputBIs.add(EmptyGenerator.get(width, 10));

        //locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(body.getWidth(), 5)));

        inputBIs.add(body);

        //right border
        inputBIs.add(EmptyGenerator.get(width, 1200));

        //left border
        inputBIs.add(EmptyGenerator.get(body.getWidth(), 60));

        // Box
        inputBIs.add(EmptyGenerator.get(width, 4));

        // mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(body.getWidth(), 4), 2).get(0)));

        //Achu Khali
        inputBIs.add(EmptyGenerator.get(width, 10));

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
