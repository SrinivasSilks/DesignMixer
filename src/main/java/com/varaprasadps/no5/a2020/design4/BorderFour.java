package com.varaprasadps.no5.a2020.design4;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.PlainGenerator;
import com.varaprasadps.image.ReverseGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderFour {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/5/a2020/design4/border/border-rani.bmp";

        BufferedImage mango = ImageIO.read(new File("z-data/in/5/a2020/design4/border/border-rani-mango.bmp"));
        BufferedImage peacock = ImageIO.read(new File("z-data/in/5/a2020/design4/border/border-rani-peacock.bmp"));
        BufferedImage round = ImageIO.read(new File("z-data/in/5/a2020/design4/border/border-rani-round.bmp"));
        BufferedImage middle = ImageIO.read(new File("z-data/in/5/a2020/design4/border/border-rani-middle.bmp"));

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(mango);

        inputBIs.add(CutLayoutGenerator.get(peacock, 35).get(0));
        inputBIs.add(round);
        inputBIs.add(peacock);
        inputBIs.add(ReverseGenerator.get(round));
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(peacock, 35).get(0)));
        inputBIs.add(PlainGenerator.get(peacock.getWidth(), 222));
        inputBIs.add(CutLayoutGenerator.get(peacock, 35).get(0));
        inputBIs.add(round);
        inputBIs.add(peacock);
        inputBIs.add(ReverseGenerator.get(round));
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(peacock, 35).get(0)));


        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
