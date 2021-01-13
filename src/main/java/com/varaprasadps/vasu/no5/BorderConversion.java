package com.varaprasadps.vasu.no5;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-vasu/out/5/VBORDER.bmp";

        final BufferedImage a = HorizontalFlipGenerator.get(ImageIO.read(new File("z-vasu/in/5/border.bmp")));
        final BufferedImage b = ReverseGenerator.get(StepLayoutGenerator.get(a.getWidth(), 104, 5));

        int width = b.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 14));

        //body
        inputBIs.add(EmptyGenerator.get(width, 480));

        inputBIs.add(PlainGenerator.get(width, 24));
        inputBIs.add(ReverseGenerator.get(b));
        inputBIs.add(ReverseGenerator.get(a));

        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        //mispick
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(AchuLayoutGenerator.get(width, 12));

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
