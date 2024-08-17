package com.varaprasadps.no14.a2024.aug15;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no14.a2024.ThreePlay.*;
import static java.lang.String.format;


public class Brocade1Conversion {

    public static BufferedImage get(
            BufferedImage right, BufferedImage left,
            BufferedImage bodyReshamR, BufferedImage bodyJariR,
            BufferedImage bodyReshamJ, BufferedImage bodyJariJ,
            BufferedImage bodyReshamN, BufferedImage bodyJariN
    ) throws IOException {

        BufferedImage emptyRight = EmptyGenerator.get(right.getWidth(), right.getHeight());
        BufferedImage emptyLeft = EmptyGenerator.get(left.getWidth(), left.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, bodyReshamR, bodyJariR));
        brocades.add(jari(emptyRight, emptyLeft, bodyReshamN, bodyJariN));
        brocades.add(nimbu(emptyRight, emptyLeft, bodyReshamJ, bodyJariJ));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/14/out/2024/aug15/1brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage bodyReshamN = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug15/brocade1/green-silk.bmp"))));
        BufferedImage bodyJariN = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug15/brocade1/green-jari.bmp"))));

        BufferedImage bodyReshamJ = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug15/brocade1/silver-silk.bmp"))));
        BufferedImage bodyJariJ = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug15/brocade1/silver-jari.bmp"))));

        BufferedImage bodyReshamR = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug15/brocade1/rani-silk.bmp"))));
        BufferedImage bodyJariR = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug15/brocade1/rani-jari.bmp"))));

        BufferedImage left = VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/aug15/border/left.bmp")));
        BufferedImage right = ImageIO.read(new File("d/14/in/2024/aug15/border/right.bmp"));

        get(right, left, bodyReshamR, bodyJariR, bodyReshamJ, bodyJariJ, bodyReshamN, bodyJariN);
    }


    public static BufferedImage repeat(BufferedImage image) {
        return VerticalRepeatGenerator.get(2, HorizontalRepeatGenerator.get(3, image));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
