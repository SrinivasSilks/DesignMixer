package com.varaprasadps.bala.no1.a2022.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {
        String out = "z-bala/out/1/2022/1rani-%s-%s.bmp";

        final BufferedImage body = ImageIO.read(new File("z-bala/in/1/2022/brocade/red.bmp"));

        final BufferedImage right = EmptyGenerator.get(body.getWidth(), 560);
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //kali
        inputBIs.add(EmptyGenerator.get(width, 272));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 16)));
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

        //left
        inputBIs.add(right);
        //body
        inputBIs.add(body);
        inputBIs.add(body);
        //right
        inputBIs.add(right);

        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 16));

        //kali
        inputBIs.add(EmptyGenerator.get(width, 160));

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
