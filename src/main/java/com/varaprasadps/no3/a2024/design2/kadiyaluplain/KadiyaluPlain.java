package com.varaprasadps.no3.a2024.design2.kadiyaluplain;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no3.a2024.Kadiyalu12Play.getBrocade;
import static com.varaprasadps.no3.a2024.Kadiyalu1Play.jari1;
import static com.varaprasadps.no3.a2024.Kadiyalu1Play.rani1;
import static java.lang.String.format;

public class KadiyaluPlain {
    public static BufferedImage kadiyalu1Play(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage checks, BufferedImage meena) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani1(right, leftback, meena, checks));
        brocades.add(jari1(right, left, meena, checks));
        for (BufferedImage bufferedImage : brocades) {
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/border/right.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/border/left-first.bmp"))));
        BufferedImage left = VerticalFlipGenerator.get(HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d/3/in/2024/design2/border/left.bmp"))));
        BufferedImage checks = BlackGenerator.get(right.getWidth(), 64);
        final BufferedImage meena = PlainGenerator.get(right.getWidth(), 480);

        BufferedImage brocade = kadiyalu1Play(right, leftBack, left, checks, meena);
        displayPixels(brocade);
        saveBMP(brocade, format("d/3/out/2024/design2/1kplain-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));

    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
