package com.varaprasadps.no11.a2023.design3.old.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/11/out/design3/1rani-%s-%s.bmp";

        BufferedImage left = HorizontalRepeatGenerator.get(7, ImageIO.read(new File("d/11/in/design3/border/left.bmp")));
        BufferedImage right = HorizontalRepeatGenerator.get(7, ImageIO.read(new File("d/11/in/design3/border/right.bmp")));
        BufferedImage brocade = HorizontalRepeatGenerator.get(10, ImageIO.read(new File("d/11/in/design3/brocade/majenta.bmp")));

        int width = left.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(CutLayoutGenerator.get(brocade, brocade.getHeight() - 8).get(1));
        //body
        inputBIs.add(brocade);
        inputBIs.add(brocade);
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //right
        inputBIs.add(right);


        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //jamudu
        inputBIs.add(PlainGenerator.get(width, 4));

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
