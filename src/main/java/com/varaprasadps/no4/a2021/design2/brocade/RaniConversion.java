package com.varaprasadps.no4.a2021.design2.brocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RaniConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-data/out/4/a2021/design2/1rani-%s-%s.bmp";

        final BufferedImage border = cut(ImageIO.read(new File("z-data/in/4/a2021/design2/border/right.bmp")));
        final BufferedImage bugada = cut(ImageIO.read(new File("z-data/in/4/a2021/design2/border/bugada.bmp")));
        final BufferedImage teega = cut(ImageIO.read(new File("z-data/in/4/a2021/design2/border/teega.bmp")));
        final BufferedImage banaras = cut(ImageIO.read(new File("z-data/in/4/a2021/design2/border/banaras.bmp")));
        final BufferedImage sunanda = cut(ImageIO.read(new File("z-data/in/4/a2021/design2/border/sunanda.bmp")));
        final int width = border.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(EmptyGenerator.get(width, 32));

        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 4)));
        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //Achu
        inputBIs.add(AchuLayoutGenerator.get(width, 10));

        //body
        inputBIs.add(EmptyGenerator.get(width, 400));

        //right border
        inputBIs.add(border);

        //left border
        inputBIs.add(bugada);
        inputBIs.add(sunanda);
        inputBIs.add(teega);
        inputBIs.add(banaras);
        //locking
        inputBIs.add(PlainGenerator.get(width, 4));
        //kali
        inputBIs.add(EmptyGenerator.get(width, 2));


        //box
        inputBIs.add(EmptyGenerator.get(width, 2));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));

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

    private static BufferedImage cut(BufferedImage read) {
        BufferedImage abc = HorizontalRepeatGenerator.get(2, read);
        return CutLayoutGenerator.get(CutLayoutGenerator.get(abc, 360, 1), 480, 0);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
