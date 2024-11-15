package com.varaprasadps.no14.a2025;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no14.ThreePlay.*;
import static java.lang.String.format;

public class BrocadeTestingConversion {

    public static BufferedImage get(
            BufferedImage right, BufferedImage left,
            BufferedImage raniSilk, BufferedImage raniJari,
            BufferedImage greenSilk, BufferedImage greenJari,
            BufferedImage silverSilk, BufferedImage silverJari
    ) throws IOException {

        BufferedImage emptyRight = EmptyGenerator.get(right.getWidth(), right.getHeight());
        BufferedImage emptyLeft = EmptyGenerator.get(left.getWidth(), left.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, raniSilk, raniJari));
        brocades.add(jari(emptyRight, emptyLeft, greenSilk, greenJari));
        brocades.add(nimbu(emptyRight, emptyLeft, silverSilk, silverJari));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/14/out/2025/design1/brocade-888-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage raniSilk = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/rani-silk.bmp")));
        BufferedImage raniJari = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/rani-jari.bmp")));
        BufferedImage greenSilk = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/rani-silk.bmp")));
        BufferedImage greenJari = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/rani-jari.bmp")));
        BufferedImage silverSilk = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/silver-silk.bmp")));
        BufferedImage silverJari = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/silver-jari.bmp")));

        BufferedImage left = border(1, ImageIO.read(new File("d/14/in/2025/design1/border/left.bmp")));
        BufferedImage right = border(1, ImageIO.read(new File("d/14/in/2025/design1/border/right.bmp")));

        get(right, left, raniSilk, raniJari, greenSilk, greenJari, silverSilk, silverJari);
    }

    private static BufferedImage bodyRepeat(int times, BufferedImage input) {
        List<BufferedImage> images = new LinkedList<>();
        images.add(CutLayoutGenerator.get(input, 240).get(0));
        images.add(ReverseGenerator.get(CutLayoutGenerator.get(input, 240).get(1)));
        return HorizontalRepeatGenerator.get(times, AddLayoutGenerator.get(images));
    }

    private static BufferedImage border(int times, BufferedImage input) {
        return HorizontalRepeatGenerator.get(times, ReverseGenerator.get(input));
    }

//    private static BufferedImage bodyRepeat(int times, BufferedImage input) {
//        return HorizontalRepeatGenerator.get(times, input);
//    }

//    private static BufferedImage border(int times, BufferedImage input) {
//        return HorizontalRepeatGenerator.get(times, ReverseGenerator.get(input));
//    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
