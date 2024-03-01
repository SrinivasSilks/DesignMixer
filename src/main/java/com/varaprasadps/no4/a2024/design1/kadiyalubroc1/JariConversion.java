package com.varaprasadps.no4.a2024.design1.kadiyalubroc1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/4/out/a2024/design1/1kbroc-jari-%s-%s.bmp";

        final BufferedImage right = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/4/in/a2024/design1/border/right.bmp")));
        final BufferedImage left = HorizontalRepeatGenerator.get(3, VerticalFlipGenerator.get(ImageIO.read(new File("d/4/in/a2024/design1/border/left.bmp"))));
        final BufferedImage jamudu = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/4/in/a2024/design1/border/jamudu.bmp")));

        int width = left.getWidth();
        BufferedImage body = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("d/4/in/a2024/design1/brocade1/silver.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //jamudu
        inputBIs.add(jamudu);

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //body
        inputBIs.add(CutLayoutGenerator.get(body, body.getHeight() - 4).get(1));
        inputBIs.add(body);
        inputBIs.add(CutLayoutGenerator.get(body, 4).get(0));
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        //right
        inputBIs.add(right);

        //jamudu
        inputBIs.add(jamudu);

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

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
