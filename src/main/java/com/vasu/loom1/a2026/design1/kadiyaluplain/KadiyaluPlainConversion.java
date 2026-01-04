package com.vasu.loom1.a2026.design1.kadiyaluplain;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom2.a2024.KadiyaluAnniPlay.getBrocade;
import static com.vasu.loom2.a2024.KadiyaluAnniPlay.jari;
import static com.vasu.loom2.a2024.KadiyaluAnniPlay.rani;
import static java.lang.String.format;


public class KadiyaluPlainConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage leftback, BufferedImage left, BufferedImage meena) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, leftback, meena));
        brocades.add(jari(right, left, meena));
        for (int i = 0; i < brocades.size(); i++) {
            BufferedImage bufferedImage = brocades.get(i);
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-vasu/out/1/2026/design1/A1KPLN.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage right = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("z-vasu/in/1/2026/design1/border/border.bmp")));
        BufferedImage leftBack = VerticalFlipGenerator.get(KadiyaluLayoutGenerator.kadiyalu(right));
        BufferedImage left = VerticalFlipGenerator.get(right);

        BufferedImage meena = PlainGenerator.get(right.getWidth(),960);

        get(right, leftBack, left, meena);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
