package com.varaprasadps.no5.a2022.design2.pallu;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.CutLayoutGenerator.get;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/a2022/design2/p-rani-%s-%s.bmp";

        final BufferedImage rightt = ReverseGenerator.get(ImageIO.read(new File("z-data/in/5/a2022/design2/border/right.bmp")));
        final BufferedImage leftt = VerticalFlipGenerator.get(ReverseGenerator.get(ImageIO.read(new File("z-data/in/5/a2022/design2/border/left.bmp"))));

        final BufferedImage rightm = HorizontalRepeatGenerator.get(10, rightt);
        final BufferedImage right = CutLayoutGenerator.get(rightm, 2000, 0);
        final BufferedImage leftm = HorizontalRepeatGenerator.get(10, leftt);
        final BufferedImage left =  CutLayoutGenerator.get(leftm, 2000, 0);

        final BufferedImage pallu = ImageIO.read(new File("z-data/in/5/a2022/design2/pallu/pallu-rani.bmp"));

        int width = right.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //Khali
        inputBIs.add(AchuLayoutGenerator.get(width, 6));

        inputBIs.add(left);
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        inputBIs.add(pallu);
        inputBIs.add(right);

        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //mispick
        inputBIs.add(ReverseGenerator.get(get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
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
