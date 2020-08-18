package com.varaprasadps.no5.a2020.design4;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PalluGenerator {


    public static void main(final String[] args) throws IOException {

//        String out = "z-data/in/5/a2020/design4/pallu/pallu-jari.bmp";
//        BufferedImage side = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/5/a2020/design4/pallu/75.bmp")));
//        BufferedImage middleEdit = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/5/a2020/design2/pallu/59.bmp")));
//        BufferedImage step = ReverseGenerator.get(StepLayoutGenerator.get(630, 33, 5));
//        BufferedImage estep = CutLayoutGenerator.get(CutLayoutGenerator.get(step, 1).get(1), 160).get(0);
//        List<BufferedImage> middles = Arrays.asList(estep, middleEdit, estep);
//        BufferedImage middle = RightLayoutGenerator.get(AddLayoutGenerator.get(middles));
//        BufferedImage extraEdit = HorizontalFlipGenerator.get(ReverseGenerator.get(StepLayoutGenerator.get(side.getWidth(), 50, 5)));
//        BufferedImage extra = CutLayoutGenerator.get(CutLayoutGenerator.get(extraEdit, 1).get(1), 150).get(0);


        String out = "z-data/in/5/a2020/design4/pallu/pallu-rani.bmp";
        BufferedImage side = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("z-data/in/5/a2020/design4/pallu/76.bmp")));
        BufferedImage middleEdit = LeftLayoutGenerator.get(ImageIO.read(new File("z-data/in/5/a2020/design2/pallu/60.bmp")));
        BufferedImage estep = PlainGenerator.get(630, 160);
        List<BufferedImage> middles = Arrays.asList(estep, middleEdit, estep);
        BufferedImage middle = RightLayoutGenerator.get(AddLayoutGenerator.get(middles));
        BufferedImage extra = PlainGenerator.get(side.getWidth(),150);

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(CutLayoutGenerator.get(side, 494).get(0));
        inputBIs.add(ReverseGenerator.get(extra));
        inputBIs.add(middle);
        inputBIs.add(ReverseGenerator.get(extra));
        BufferedImage abcs = CutLayoutGenerator.get(side, 38).get(1);
        BufferedImage aasfgfg = CutLayoutGenerator.get(abcs, 304).get(0);

        inputBIs.add(CutLayoutGenerator.get(CutLayoutGenerator.get(side, 342).get(1), 154).get(0));
        inputBIs.add(CutLayoutGenerator.get(aasfgfg, 62).get(1));
        inputBIs.add(CutLayoutGenerator.get(aasfgfg, 62).get(0));
        inputBIs.add(VerticalFlipGenerator.get(CutLayoutGenerator.get(side, 38).get(0)));

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
