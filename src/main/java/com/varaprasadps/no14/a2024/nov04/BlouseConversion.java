package com.varaprasadps.no14.a2024.nov04;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no14.TwoPlay.*;
import static java.lang.String.format;

public class BlouseConversion {

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

        saveBMP(brocade, format("d/14/out/2024/nov04/blouse-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage bodyReshamJ = HorizontalRepeatGenerator.get(7, ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/nov04/blouse/silver-silk.bmp"))));
        BufferedImage bodyJariJ = HorizontalRepeatGenerator.get(7, ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/nov04/blouse/silver-jari.bmp"))));

        BufferedImage bodyReshamR = HorizontalRepeatGenerator.get(7, ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/nov04/blouse/rani-silk.bmp"))));
        BufferedImage bodyJariR = HorizontalRepeatGenerator.get(7, ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/nov04/blouse/rani-jari.bmp"))));

        int width = bodyJariJ.getWidth();
        BufferedImage left = HorizontalRepeatGenerator.get(4, VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/nov04/border/left.bmp"))));
        BufferedImage right = HorizontalRepeatGenerator.get(4, ImageIO.read(new File("d/14/in/2024/nov04/border/right.bmp")));


        get(right, left, bodyReshamR, bodyJariR, bodyReshamJ, bodyJariJ);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
