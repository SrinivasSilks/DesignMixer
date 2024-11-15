package com.varaprasadps.no14.a2024.design1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no14.ThreePlay.*;
import static com.varaprasadps.no14.ThreePlay.getBrocade;
import static java.lang.String.format;

public class BlouseOldConversion {

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

        saveBMP(brocade, format("d/14/out/2024/design1/1blouse-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage bodyReshamN = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/14/in/2024/design1/blouse/nimbu-silk.bmp")));
        BufferedImage bodyJariN = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/14/in/2024/design1/blouse/nimbu-jari.bmp")));

        BufferedImage bodyReshamJ = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/14/in/2024/design1/blouse/silver-silk.bmp")));
        BufferedImage bodyJariJ = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/14/in/2024/design1/blouse/silver-jari.bmp")));

        BufferedImage bodyReshamR = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/14/in/2024/design1/blouse/rani-silk.bmp")));
        BufferedImage bodyJariR = HorizontalRepeatGenerator.get(3, ImageIO.read(new File("d/14/in/2024/design1/blouse/rani-jari.bmp")));

        BufferedImage left = VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/design1/border/left.bmp")));
        BufferedImage right = ImageIO.read(new File("d/14/in/2024/design1/border/right.bmp"));

        get(right, left, bodyReshamR, bodyJariR, bodyReshamJ, bodyJariJ, bodyReshamN, bodyJariN);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
