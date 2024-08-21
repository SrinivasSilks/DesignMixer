package com.varaprasadps.no14.a2024.aug17;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no14.a2024.KadiyaluPlainPlay.*;
import static java.lang.String.format;


public class KadiyaluPlainConversion {

    public static BufferedImage get(
            BufferedImage right, BufferedImage leftBack, BufferedImage left,
            BufferedImage bodyReshamR, BufferedImage bodyJariR
    ) throws IOException {

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, leftBack, bodyReshamR, bodyJariR));
        brocades.add(jari(right, left, bodyReshamR, bodyJariR));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/14/out/2024/aug17/1kadiyalu-plain-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage leftBack = border(VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/border/left-first.bmp"))));
        BufferedImage left = border(VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/border/left.bmp"))));
        BufferedImage right = border(ImageIO.read(new File("d/14/in/2024/aug17/border/right.bmp")));

        BufferedImage bodyReshamR = PlainGenerator.get(right.getWidth(), 480);
        BufferedImage bodyJariR = StepLayoutGenerator.get(right.getWidth(), 120);

        get(right, leftBack, left, bodyReshamR, bodyJariR);
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(1, image));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
