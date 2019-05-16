package com.varaprasadps.giri.no9;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-giri/out/rani-%s-%s.bmp";
        final BufferedImage skirt = ImageIO.read(new File("z-giri/in/9/SKIRT_RANI.bmp"));
        final BufferedImage body = EmptyGenerator.get(400, 360);

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 32));

        //Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 12));
        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));

        //Banaras
        inputBIs.add(StepLayoutGenerator.get(skirt.getWidth(), 5));

        inputBIs.add(HorizontalRepeatGenerator.get(3, body));
        inputBIs.add(skirt);

        //Banaras
        inputBIs.add(StepLayoutGenerator.get(skirt.getWidth(), 10));

        // Box
        inputBIs.add(EmptyGenerator.get(skirt.getWidth(), 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(skirt.getWidth(), 2)));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(skirt.getWidth(), 12));

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
