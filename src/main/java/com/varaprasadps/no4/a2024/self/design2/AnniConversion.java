package com.varaprasadps.no4.a2024.self.design2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "d/4/out/a2024/design2/anni-%s-%s.bmp";
        final BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/4/in/a2024/design2/border/right.bmp")));
        final BufferedImage left = HorizontalRepeatGenerator.get(1, VerticalFlipGenerator.get(ImageIO.read(new File("d/4/in/a2024/design2/border/left.bmp"))));
        final BufferedImage jamudu = ImageIO.read(new File("d/4/in/a2024/design2/border/jamudu.bmp"));
        final int width = right.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));
        //jamudu
        inputBIs.add(jamudu);

        //left
        inputBIs.add(left);
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        //body
        inputBIs.add(PlainGenerator.get(width, 480));
        //locking
        inputBIs.add(PlainGenerator.get(width, 8));
        inputBIs.add(right);

        //jamudu
        inputBIs.add(jamudu);
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //wheel
        inputBIs.add(EmptyGenerator.get(width, 1));

        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 8));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = LeftLayoutGenerator.get(ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs)));
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
