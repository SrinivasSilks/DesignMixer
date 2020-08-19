package com.varaprasadps.no8.a2020.design2;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BorderLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/in/8/a2020/8design2/border/border.bmp";

        BufferedImage a220 = ImageIO.read(new File("z-data/in/8/a2020/8design2/border/1/220.bmp"));
        BufferedImage a806 = ImageIO.read(new File("z-data/in/8/a2020/8design2/border/1/806.bmp"));
        BufferedImage aaa = ReverseGenerator.get(ImageIO.read(new File("z-data/in/8/a2020/8design2/border/mango.bmp")));
        BufferedImage bugada = RightLayoutGenerator.get(CutLayoutGenerator.get(LeftLayoutGenerator.get(HorizontalRepeatGenerator.get(2, aaa)), 330).get(0));
        BufferedImage horse = ImageIO.read(new File("z-data/in/8/a2020/8design2/border/horse.bmp"));
        BufferedImage peacock = ImageIO.read(new File("z-data/in/8/a2020/8design2/border/peacock.bmp"));
        BufferedImage repeat = ImageIO.read(new File("z-data/in/8/a2020/8design2/border/repeat.bmp"));
        BufferedImage rudraska = ImageIO.read(new File("z-data/in/8/a2020/8design2/border/1/rudraska1.bmp"));
        BufferedImage up = ImageIO.read(new File("z-data/in/8/a2020/8design2/border/up.bmp"));

        int width = bugada.getWidth();
        List<BufferedImage> inputBIs = new LinkedList<>();


        inputBIs.add(HorizontalFlipGenerator.get(ReverseGenerator.get(StepLayoutGenerator.get(bugada.getWidth(), 6, 5))));
        inputBIs.add(bugada);
        inputBIs.add(a806);
        inputBIs.add(a220);
        inputBIs.add(rudraska);

//        inputBIs.add(bugada);
//        inputBIs.add(rudraska);
//        inputBIs.add(HorizontalFlipGenerator.get(ReverseGenerator.get(StepLayoutGenerator.get(bugada.getWidth(), 90 / 5, 5))));
//        inputBIs.add(rudraska);

//        inputBIs.add(up);
//        inputBIs.add(peacock);
//        inputBIs.add(up);
//        inputBIs.add(rudraska);

//        for (int i = 0; i < 10; i++) {
//            inputBIs.add(repeat);
//        }


//        inputBIs.add(HorizontalFlipGenerator.get(ReverseGenerator.get(StepLayoutGenerator.get(bugada.getWidth(), 6, 5))));
//        inputBIs.add(bugada);
//        inputBIs.add(rudraska);

//        ////////////// 806
//        inputBIs.add(CutLayoutGenerator.get(repeat, 1).get(1));
//        inputBIs.add(repeat);
//        inputBIs.add(up);
//        inputBIs.add(horse);
//        inputBIs.add(up);
//        inputBIs.add(repeat);
//        inputBIs.add(CutLayoutGenerator.get(repeat, 11).get(0));
//        inputBIs.add(rudraska);
////        inputBIs.add(up);
////        inputBIs.add(rudraska);
////        inputBIs.add(up);
////        inputBIs.add(rudraska);
//        for (int i = 0; i < 11; i++) {
//            inputBIs.add(repeat);
//        }
//        inputBIs.add(CutLayoutGenerator.get(repeat, 2).get(0));
//
//        inputBIs.add(up);
//        inputBIs.add(peacock);
//        inputBIs.add(up);
//        for (int i = 0; i < 11; i++) {
//            inputBIs.add(repeat);
//        }
//        inputBIs.add(CutLayoutGenerator.get(repeat, 2).get(0));
//
//        inputBIs.add(rudraska);
//        ///////////

//
//        /////////// 206
//        inputBIs.add(CutLayoutGenerator.get(repeat, 1).get(1));
//        inputBIs.add(repeat);
//        inputBIs.add(up);
//        inputBIs.add(horse);
//        inputBIs.add(up);
//        inputBIs.add(repeat);
//        inputBIs.add(CutLayoutGenerator.get(repeat, 11).get(0));
//        ///////////
//        inputBIs.add(rudraska);

//        for (int i = 0; i < 10; i++) {
//            inputBIs.add(repeat);
//        }
//        inputBIs.add(up);
//
//        inputBIs.add(rudraska);
//        inputBIs.add(a220);
//        inputBIs.add(rudraska);

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }

        BufferedImage bi = ReverseGenerator.get(get(repeatWidth, repeatHeight, inputBIs));
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    public static BufferedImage get(int sizeX, int sizeY, List<BufferedImage> inputBIs) {
        final BufferedImage result = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
        int yRes = 0;
        int index = 0;
        while (yRes < result.getHeight()) {
            BufferedImage bi = inputBIs.get(index);
            int y = 0;
            while (y < bi.getHeight()) {
                copyRow(yRes, result, y, bi);
                y++;
                yRes++;
            }
            index++;

        }
        return result;
    }

    private static void copyRow(int resultCol, BufferedImage result, int inputCol, BufferedImage fileOne) {
        for (int x = 0; x < fileOne.getWidth(); x++) {
            result.setRGB(x, resultCol, fileOne.getRGB(x, inputCol));
        }
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
