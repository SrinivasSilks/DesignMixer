package com.varaprasadps.no5.a1border.korvai.design2.brocade2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/5/a1recent/korvai/design2/2jari-%s-%s.bmp";
        final BufferedImage jari = ImageIO.read(new File("z-data/in/5/design2/border/border-jari.bmp"));
        final BufferedImage brocade = CutLayoutGenerator.get(ImageIO.read(new File("z-data/in/5/design2/brocade2/jari.bmp")), 800).get(0);

        List<BufferedImage> inputBIs = new LinkedList<>();
        int width = jari.getWidth();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //box
        inputBIs.add(EmptyGenerator.get(width, 4));
        // mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        // Khali
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(brocade);
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 4)));
        inputBIs.add(CutLayoutGenerator.get(jari, 944).get(0));

        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 12), 6).get(0)));
        //khali
        inputBIs.add(EmptyGenerator.get(width, 10));


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
