package com.varaprasadps.no13.a2024;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no13.a2024.TwoPlay.*;
import static java.lang.String.format;


public class SelfBrocadeConversion {

    public static BufferedImage get(BufferedImage rightJari, BufferedImage leftFigureJari, BufferedImage leftTeegaJari, BufferedImage anni, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightJari, leftFigureJari, leftTeegaJari, anni));
        brocades.add(jari(jari));

        for (int i = 0; i < brocades.size(); i++) {
            BufferedImage bufferedImage = brocades.get(i);
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2024/design1/2-selfbrocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage jari = ImageIO.read(new File("d/13/in/2024/design1/silver.bmp"));
        BufferedImage anni = ImageIO.read(new File("d/13/in/2024/design1/meena.bmp"));
        int width = jari.getWidth();
        BufferedImage rightJari = StepLayoutGenerator.get(width, 148);
        BufferedImage leftFigureJari = StepLayoutGenerator.get(width, 16);
        BufferedImage leftTeegaJari = StepLayoutGenerator.get(width, 12);

        get(rightJari, leftFigureJari, leftTeegaJari, anni, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
