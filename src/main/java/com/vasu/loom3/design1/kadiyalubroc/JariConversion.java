package com.vasu.loom3.design1.kadiyalubroc;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/3/design1/kbroc-jari-%s-%s.bmp";

        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/3/design1/border/border.bmp")));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/3/design1/border/border.bmp"))));

        int width = left.getWidth();

        BufferedImage body = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/3/design1/brocade/nimbu.bmp")));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        //left
        inputBIs.add(left);

        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 12)));
        inputBIs.add(PlainGenerator.get(width, 4));

        //body
        inputBIs.add(body);
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        inputBIs.add(PlainGenerator.get(width, 12));

        //right
        inputBIs.add(right);

        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

        //kali
        inputBIs.add(EmptyGenerator.get(width, 256));

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
