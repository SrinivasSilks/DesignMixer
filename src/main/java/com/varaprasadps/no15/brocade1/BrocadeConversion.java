package com.varaprasadps.no15.brocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.no15.ThreePlay.*;
import static java.lang.String.format;

public class BrocadeConversion {

    public static BufferedImage get(
            BufferedImage rightSilk, BufferedImage rightJari,
            BufferedImage banarasSilk, BufferedImage banarasJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage rani, BufferedImage jari, BufferedImage nimbu
    ) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani));
        brocades.add(jari(dis(rightSilk), dis(rightJari), dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), nimbu));
        brocades.add(nimbu(dis(rightSilk), dis(rightJari), dis(banarasSilk), dis(banarasJari), dis(teegaSilk), dis(teegaJari), jari));
        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = ReverseGenerator.get(LeftLayoutGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d/15/out/design1/brocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        int width = 60;
        BufferedImage rightSilk = PlainGenerator.get(width, 528);
        BufferedImage rightJari = PlainGenerator.get(width, 528);
        BufferedImage teegaSilk = PlainGenerator.get(width, 48);
        BufferedImage teegaJari = PlainGenerator.get(width, 48);
        BufferedImage banarasSilk = PlainGenerator.get(width, 64);
        BufferedImage banarasJari = PlainGenerator.get(width, 64);
        BufferedImage rani = StepLayoutGenerator.get(width, 480 / 5, 5);
        BufferedImage nimbu = PlainGenerator.get(width, 480);
        BufferedImage jari = ReverseGenerator.get(StepLayoutGenerator.get(width, 480 / 5, 5));
        get(rightSilk, rightJari, banarasSilk, banarasJari, teegaSilk, teegaJari, rani, nimbu, jari);
    }


    private static BufferedImage dis(BufferedImage image) {
        return EmptyGenerator.get(image.getWidth(), image.getHeight());
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }
}
