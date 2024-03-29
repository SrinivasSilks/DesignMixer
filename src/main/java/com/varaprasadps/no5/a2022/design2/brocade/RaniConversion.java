package com.varaprasadps.no5.a2022.design2.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/a2022/design2/1rani-%s-%s.bmp";

        BufferedImage imageRightt = ReverseGenerator.get(ImageIO.read(new File("z-data/in/5/a2022/design2/border/right.bmp")));
        BufferedImage imageLeftt = VerticalFlipGenerator.get(ReverseGenerator.get(ImageIO.read(new File("z-data/in/5/a2022/design2/border/left.bmp"))));

        final BufferedImage right = HorizontalRepeatGenerator.get(2, chop(imageRightt, 160));
        final BufferedImage left = HorizontalRepeatGenerator.get(2, chop(imageLeftt, 160));
        final BufferedImage body = ImageIO.read(new File("z-data/in/5/a2022/design2/brocade/rani.bmp"));
        int width = right.getWidth();

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

    private static BufferedImage chop(BufferedImage input, int chop) {
        return CutLayoutGenerator.get(CutLayoutGenerator.get(HorizontalRepeatGenerator.get(2, input), input.getWidth()- chop, 1), input.getWidth(), 0);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
