package com.varaprasadps.no9.a2022.design1.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/9/a2022/design1/1nimbu-%s-%s.bmp";

        BufferedImage body = HorizontalRepeatGenerator.get(15, ImageIO.read(new File("z-data/in/9/a2022/design1/brocade/jari.bmp")));
        BufferedImage right = EmptyGenerator.get(body.getWidth(), 480);
        BufferedImage left = EmptyGenerator.get(body.getWidth(), 288);
        int width = body.getWidth();


        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));
        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        //left
        inputBIs.add(VerticalFlipGenerator.get(left));
        //locking
//        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 4)));
        inputBIs.add(EmptyGenerator.get(width, 14));
        inputBIs.add(PlainGenerator.get(width, 2));

        //body
        inputBIs.add(body);
        inputBIs.add(body);
        //locking
//        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 4)));
        inputBIs.add(PlainGenerator.get(width, 2));
        //locking
        inputBIs.add(EmptyGenerator.get(width, 14));
        //right
        inputBIs.add(right);

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 14));

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
