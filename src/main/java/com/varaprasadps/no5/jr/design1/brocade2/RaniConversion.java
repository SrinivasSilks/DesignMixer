package com.varaprasadps.no5.jr.design1.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/jr/design1/2rani-%s-%s.bmp";

        BufferedImage imageRightt = ImageIO.read(new File("z-data/in/5/jr/design1/border/right.bmp"));
        BufferedImage imageLeftt = VerticalFlipGenerator.get(ImageIO.read(new File("z-data/in/5/jr/design1/border/left.bmp")));
        final BufferedImage right = HorizontalRepeatGenerator.get(1, chop(imageRightt, 160));
        final BufferedImage left = HorizontalRepeatGenerator.get(1, chop(imageLeftt, 160));
        int width = right.getWidth();
        final BufferedImage brocade = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/5/jr/design1/brocade2/rani.bmp")));

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
        inputBIs.add(brocade);
        inputBIs.add(brocade);
        inputBIs.add(brocade);
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

    private static BufferedImage chop(BufferedImage input, int chop) {
        return CutLayoutGenerator.get(CutLayoutGenerator.get(HorizontalRepeatGenerator.get(2, input), input.getWidth() - chop, 1), input.getWidth(), 0);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}