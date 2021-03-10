package com.varaprasadps.bala.no3.design2;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bala/out/3/design2/border-%s-%s.bmp";

        final BufferedImage right = ImageIO.read(new File("z-bala/in/3/design1/right.bmp"));
        final BufferedImage left = VerticalFlipGenerator.get(ImageIO.read(new File("z-bala/in/3/design1/left.bmp")));
        final BufferedImage body = EmptyGenerator.get(left.getWidth(), 960);

        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));
        //box
        inputBIs.add(EmptyGenerator.get(width, 8));

        //left
        inputBIs.add(left);
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(right);

        //box
        inputBIs.add(EmptyGenerator.get(width, 8));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 16));

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
