package com.varaprasadps.no13.a2025.design2.kadiyalubrc1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.varaprasadps.image.KadiyaluLayoutGenerator.kadiyalu;
import static com.varaprasadps.no13.a2025.Kadiyalu3Play.*;
import static java.lang.String.format;


public class KadiyaluBrocadeConversion {

    public static BufferedImage get(
            BufferedImage raniSilk, BufferedImage raniJari,
            BufferedImage greenSilk, BufferedImage greenJari,
            BufferedImage silverSilk, BufferedImage silverJari,
            BufferedImage teegaSilk, BufferedImage teegaJari,
            BufferedImage banarsSilk, BufferedImage banrasJari,
            BufferedImage lavendar, BufferedImage green, BufferedImage silver
    ) throws IOException {

        BufferedImage emptyRight = EmptyGenerator.get(raniSilk.getWidth(), raniSilk.getHeight());
        BufferedImage emptyTeega = EmptyGenerator.get(teegaSilk.getWidth(), teegaSilk.getHeight());
        BufferedImage emptyFigure = EmptyGenerator.get(banarsSilk.getWidth(), banarsSilk.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(laveOne(raniSilk, raniJari, kadiyalu(banarsSilk), kadiyalu(banrasJari), kadiyalu(teegaSilk), kadiyalu(teegaJari), lavendar));
        brocades.add(laveTwo(raniSilk, raniJari, banarsSilk, banrasJari, teegaSilk, teegaJari, lavendar));
        brocades.add(green(greenSilk, greenJari, emptyFigure, emptyFigure, emptyTeega, emptyTeega, green));
        brocades.add(silver(silverSilk, silverJari, emptyFigure, emptyFigure, emptyTeega, emptyTeega, silver));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("d/13/out/2025/design2/1kadiyalu-broc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage raniSilk = border(ImageIO.read(new File("d/13/in/2025/design2/border/rani-silk.bmp")));
        BufferedImage raniJari = border(ImageIO.read(new File("d/13/in/2025/design2/border/rani-jari.bmp")));
        BufferedImage greenSilk = border(ImageIO.read(new File("d/13/in/2025/design2/border/green-silk.bmp")));
        BufferedImage greenJari = border(ImageIO.read(new File("d/13/in/2025/design2/border/green-jari.bmp")));
        BufferedImage silverSilk = border(ImageIO.read(new File("d/13/in/2025/design2/border/silver-silk.bmp")));
        BufferedImage silverJari = border(ImageIO.read(new File("d/13/in/2025/design2/border/silver-jari.bmp")));

        BufferedImage teegaJari = teega(ImageIO.read(new File("d/13/in/2025/design2/border/teega.bmp")));
        BufferedImage teegaSilk = PlainGenerator.get(teegaJari.getWidth(), teegaJari.getHeight());
        BufferedImage banarasJari = banaaras(ImageIO.read(new File("d/13/in/2025/design2/border/banaras.bmp")));
        BufferedImage banarasSilk = PlainGenerator.get(banarasJari.getWidth(), banarasJari.getHeight());

        BufferedImage silver = body(ImageIO.read(new File("d/13/in/2025/design2/brocade1/silver.bmp")));
        BufferedImage green = body(ImageIO.read(new File("d/13/in/2025/design2/brocade1/green.bmp")));
        BufferedImage lavendar = body(ImageIO.read(new File("d/13/in/2025/design2/brocade1/meena.bmp")));

        get(raniSilk, raniJari, greenSilk, greenJari, silverSilk, silverJari, teegaSilk, teegaJari, banarasSilk, banarasJari, lavendar, green, silver);
    }


    public static BufferedImage body(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(10, image));
    }

    public static BufferedImage border(BufferedImage input) {
        List<BufferedImage> images = new LinkedList<>();
        images.add(input);
        images.add(PlainGenerator.get(input.getWidth(), 2));
        BufferedImage result = ReverseGenerator.get(AddLayoutGenerator.get(images));
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(6, result));
    }

    public static BufferedImage banaaras(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(375, image));
    }

    public static BufferedImage teega(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(100, image));
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
