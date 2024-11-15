package com.varaprasadps.no14.a2024.aug17;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no14.TwoPlay.*;
import static java.lang.String.format;


public class KBrocOct25Conversion {


    public static BufferedImage get(
            BufferedImage right, BufferedImage left,
            BufferedImage bodyReshamR, BufferedImage bodyJariR,
            BufferedImage bodyReshamJ, BufferedImage bodyJariJ
    ) throws IOException {

        BufferedImage emptyRight = EmptyGenerator.get(right.getWidth(), right.getHeight());
        BufferedImage emptyLeft = EmptyGenerator.get(left.getWidth(), left.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, bodyReshamR, bodyJariR));
        brocades.add(jari(emptyRight, emptyLeft, bodyReshamJ, bodyJariJ));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);
        saveBMP(brocade, format("d/14/out/2024/aug17/G-6-SELF-25-OCT-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage raniSilkR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade2/rani-silk.bmp"))));
        BufferedImage raniJariR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade2/rani-jari.bmp"))));
        BufferedImage silverSilkR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade2/silver-silk.bmp"))));
        BufferedImage silverJariR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade2/silver-jari.bmp"))));
        BufferedImage left = border(VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/border/left.bmp"))));
        BufferedImage right = border(ImageIO.read(new File("d/14/in/2024/aug17/border/right.bmp")));
        get(right, left, raniSilkR, raniJariR, silverSilkR, silverJariR);
    }

    public static BufferedImage body(BufferedImage image) {
        return VerticalRepeatGenerator.get(2, HorizontalRepeatGenerator.get(14, image));
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(5, image));
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

    public static BufferedImage getBorder(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    public static BufferedImage getBrocade(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.printf("Brocade => Width : %s, Height : %s%n", inputs.get(0).getWidth(), inputs.get(0).getHeight());
        }
        BufferedImage bufferedImage = ColumnRepeatGenerator.get(inputs);
        System.out.printf("final Brocade => Width : %s, Height : %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());

        return bufferedImage;
    }
}
