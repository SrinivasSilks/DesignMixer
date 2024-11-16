package com.varaprasadps.no14.a2025.design1.kadiyaluplain;

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

        saveBMP(brocade, format("d/14/out/2025/design1/1kadiyalu-plain-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage left = border(VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2025/design1/border/left.bmp"))));
        BufferedImage leftBack = KadiyaluLayoutGenerator.kadiyalu(left);
        BufferedImage right = border(ImageIO.read(new File("d/14/in/2025/design1/border/right.bmp")));

        BufferedImage jariBody = ReverseGenerator.get(HorizontalRepeatGenerator.get(4, ImageIO.read(new File("d/14/in/2025/design1/blouse/gold.bmp"))));
        int width = jariBody.getWidth();
        BufferedImage reshamBody = PlainGenerator.get(width, 480);


        get(right, leftBack, left, reshamBody, jariBody);
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(11, image));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
