package com.varaprasadps.bvr.no27.a2022.kadiyalubrocade;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.CutLayoutGenerator.get;

public class NimbuConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-bvr/out/27/a2022/k-broc-nimbu-%s-%s.bmp";

        final BufferedImage body = StepLayoutGenerator.get(40, 480 / 5, 5);
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));

        //mispick
        inputBIs.add(get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //locking
        inputBIs.add(PlainGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //plain
        inputBIs.add(PlainGenerator.get(width, 3));

        //left
        inputBIs.add(EmptyGenerator.get(width, 296));
        //body
        inputBIs.add(body);
        //right
        inputBIs.add(EmptyGenerator.get(width, 600));

        //mispick
        inputBIs.add( ReverseGenerator.get(get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //kadiyalu
        inputBIs.add(EmptyGenerator.get(width, 1));
        //locking
        inputBIs.add(ReverseGenerator.get(PlainGenerator.get(width, 2)));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 8));
        //kadiyalu
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));

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
