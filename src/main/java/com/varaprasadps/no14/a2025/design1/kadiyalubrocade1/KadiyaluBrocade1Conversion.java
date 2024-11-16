package com.varaprasadps.no14.a2025.design1.kadiyalubrocade1;

import com.varaprasadps.image.*;
import com.varaprasadps.no14.a2024.aug17.Kadiyalu123Play;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;


public class KadiyaluBrocade1Conversion {

    public static BufferedImage kadiyalu123BPlay(
            BufferedImage right, BufferedImage leftBack, BufferedImage left,
            BufferedImage silverSilk, BufferedImage silverJari,
            BufferedImage greenSilk, BufferedImage greenJari,
            BufferedImage magentaSilk, BufferedImage magentaJari
    ) {
        BufferedImage emptyRight = EmptyGenerator.get(right.getWidth(), right.getHeight());
        BufferedImage emptyLeft = EmptyGenerator.get(left.getWidth(), left.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(Kadiyalu123Play.silverOne(right, leftBack, silverSilk, silverJari));
        brocades.add(Kadiyalu123Play.silverTwo(right, left, silverSilk, silverJari));
        brocades.add(Kadiyalu123Play.green(emptyRight, emptyLeft, greenSilk, greenJari));
        brocades.add(Kadiyalu123Play.magenta(emptyRight, emptyLeft, magentaSilk, magentaJari));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = getBrocade(brocades);
        displayPixels(brocade);
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage raniSilk = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/rani-silk.bmp")));
        BufferedImage raniJari = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/rani-jari.bmp")));
        BufferedImage greenSilk = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/green-silk.bmp")));
        BufferedImage greenJari = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/green-jari.bmp")));
        BufferedImage silverSilk = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/silver-silk.bmp")));
        BufferedImage silverJari = bodyRepeat(1, ImageIO.read(new File("d/14/in/2025/design1/brocade1/silver-jari.bmp")));

        BufferedImage left = border(1, VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2025/design1/border/left.bmp"))));
        BufferedImage right = border(1, ImageIO.read(new File("d/14/in/2025/design1/border/right.bmp")));
        BufferedImage out = kadiyalu123BPlay(right, KadiyaluLayoutGenerator.kadiyalu(left), left, silverSilk, silverJari, raniSilk, raniJari, greenSilk, greenJari);
        BufferedImage brocade = LeftLayoutGenerator.get(out);
        saveBMP(brocade, format("d/14/out/2025/design1/1kadiyalu-broc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
    }

    private static BufferedImage bodyRepeat(int times, BufferedImage input) {
        return HorizontalRepeatGenerator.get(times, ReverseGenerator.get(input));
    }

    private static BufferedImage border(int times, BufferedImage input) {
        return HorizontalRepeatGenerator.get(times, input);
    }


    public static BufferedImage body(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(7, image));
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(10, image));
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

    public static BufferedImage getBorder(BufferedImage border) {
        return EmptyGenerator.get(border.getWidth(), border.getHeight());
    }

    public static BufferedImage getBrocade(List<BufferedImage> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.printf("Brocade => Width : %s, Height : %s%n", inputs.get(0).getWidth(), inputs.get(0).getHeight());
        }
        BufferedImage bufferedImage = ColumnRepeatGenerator.get(inputs);
        System.out.printf("final Brocade => Width : %s, Height : %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());

        return bufferedImage;
    }
}
