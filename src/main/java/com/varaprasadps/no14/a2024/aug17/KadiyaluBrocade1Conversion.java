package com.varaprasadps.no14.a2024.aug17;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no14.a2024.Kadiyalu3Play.*;
import static java.lang.String.format;


public class KadiyaluBrocade1Conversion {

    public static BufferedImage get(
            BufferedImage right, BufferedImage leftBack, BufferedImage left,
            BufferedImage bodyReshamR, BufferedImage bodyJariR,
            BufferedImage bodyReshamJ, BufferedImage bodyJariJ
    ) throws IOException {

        BufferedImage emptyRight = EmptyGenerator.get(right.getWidth(), right.getHeight());
        BufferedImage emptyLeft = EmptyGenerator.get(left.getWidth(), left.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, leftBack, bodyReshamJ, bodyJariJ));
        brocades.add(jari(right, left, bodyReshamJ, bodyJariJ));
        brocades.add(nimbu(emptyRight, emptyLeft, bodyReshamR, bodyJariR));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/14/out/2024/aug17/1kadiyalu-broc-jari-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage bodyReshamJ = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade1/silver-silk.bmp"))));
        BufferedImage bodyJariJ = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade1/silver-jari.bmp"))));

        BufferedImage bodyReshamR = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade1/rani-silk.bmp"))));
        BufferedImage bodyJariR = repeat(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade1/rani-jari.bmp"))));

        BufferedImage leftBack = border(VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/border/left-first.bmp"))));
        BufferedImage left = border(VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/border/left.bmp"))));
        BufferedImage right = border(ImageIO.read(new File("d/14/in/2024/aug17/border/right.bmp")));

        get(right, leftBack, left, bodyReshamR, bodyJariR, bodyReshamJ, bodyJariJ);
    }


    public static BufferedImage repeat(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(28, image));
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(15, image));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
