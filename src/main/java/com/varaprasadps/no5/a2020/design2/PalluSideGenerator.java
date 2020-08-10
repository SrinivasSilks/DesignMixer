package com.varaprasadps.no5.a2020.design2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PalluSideGenerator {


    public static void main(final String[] args) throws IOException {

//        String out = "z-data/in/5/a2020/design2/pallu/pallu-side-jari.bmp";
//        BufferedImage side = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/5/a2020/design2/pallu/65.bmp")));
//        BufferedImage extraEdit = HorizontalFlipGenerator.get(ReverseGenerator.get(StepLayoutGenerator.get(side.getWidth(), 145, 5)));
//        BufferedImage extra = CutLayoutGenerator.get(CutLayoutGenerator.get(extraEdit, 1).get(1), 720).get(0);

        String out = "z-data/in/5/a2020/design2/pallu/pallu-side-rani.bmp";
        BufferedImage side = HorizontalRepeatGenerator.get(2, ImageIO.read(new File("z-data/in/5/a2020/design2/pallu/66.bmp")));
        BufferedImage extra = PlainGenerator.get(side.getWidth(), 720);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(side);
        inputBIs.add(extra);
        inputBIs.add(VerticalFlipGenerator.get(side));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = RightLayoutGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
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
