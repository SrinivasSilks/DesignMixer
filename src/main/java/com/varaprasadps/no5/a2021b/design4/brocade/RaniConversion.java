package com.varaprasadps.no5.a2021b.design4.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/a2021b/design4/1rani-%s-%s.bmp";

        BufferedImage imageRightt = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/5/a2021b/design4/border/right.bmp")));
        BufferedImage imageLeftt = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/5/a2021b/design4/border/left.bmp")));
        final BufferedImage right = CutLayoutGenerator.get(CutLayoutGenerator.get(imageRightt, 360, 1), 480, 0);
        final BufferedImage left = CutLayoutGenerator.get(CutLayoutGenerator.get(imageLeftt, 360, 1), 480, 0);

        int width = right.getWidth();
        final BufferedImage body = EmptyGenerator.get(width, 720);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 6));

        inputBIs.add(left);
        inputBIs.add(body);
        inputBIs.add(right);

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

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
