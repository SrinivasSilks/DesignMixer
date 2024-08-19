package com.varaprasadps.no14.a2024.aug17;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no14.a2024.TwoPlay.*;
import static java.lang.String.format;

public class KanniConversion {

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

        saveBMP(brocade, format("d/14/out/2024/aug17/1kanni-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage bodyReshamJ = ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/kongu/kongu-silver-silk.bmp")));
        BufferedImage bodyJariJ = ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/kongu/kongu-silver-jari.bmp")));

        BufferedImage bodyReshamR = ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/kongu/kongu-rani-silk.bmp")));
        BufferedImage bodyJariR = ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/kongu/kongu-rani-jari.bmp")));

        int width = bodyJariJ.getWidth();
        BufferedImage leftt = HorizontalRepeatGenerator.get(1, VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/border/left.bmp"))));
        BufferedImage rightt = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/14/in/2024/aug17/border/right.bmp")));

        BufferedImage left = CutLayoutGenerator.get(leftt, 178, 0);
        BufferedImage right = CutLayoutGenerator.get(rightt, 178, 0);

        get(right, left, bodyReshamR, bodyJariR, bodyReshamJ, bodyJariJ);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
