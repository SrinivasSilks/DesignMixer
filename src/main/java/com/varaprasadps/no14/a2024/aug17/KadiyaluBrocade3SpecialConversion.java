package com.varaprasadps.no14.a2024.aug17;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;


public class KadiyaluBrocade3SpecialConversion {


    public static BufferedImage kadiyalu12Play(
            BufferedImage right, BufferedImage leftBack, BufferedImage left,
            BufferedImage silverSilk, BufferedImage silverJari,
            BufferedImage greenSilk, BufferedImage greenJari
    ) {
        BufferedImage emptyRight = EmptyGenerator.get(right.getWidth(), right.getHeight());
        BufferedImage emptyLeft = EmptyGenerator.get(left.getWidth(), left.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(Kadiyalu12Play.silverOne(right, leftBack, silverSilk, silverJari));
        brocades.add(Kadiyalu12Play.silverTwo(right, left, silverSilk, silverJari));
        brocades.add(Kadiyalu12Play.green(emptyRight, emptyLeft, greenSilk, greenJari));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = getBrocade(brocades);
        displayPixels(brocade);
        return brocade;
    }

    public static BufferedImage kadiyalu123Play(
            BufferedImage right, BufferedImage leftBack, BufferedImage left,
            BufferedImage silverSilk, BufferedImage silverJari,
            BufferedImage greenSilk, BufferedImage greenJari,
            BufferedImage orangeSilk, BufferedImage orangeJari
    ) {
        BufferedImage emptyRight = EmptyGenerator.get(right.getWidth(), right.getHeight());
        BufferedImage emptyLeft = EmptyGenerator.get(left.getWidth(), left.getHeight());

        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(Kadiyalu123Play.silverOne(right, leftBack, silverSilk, silverJari));
        brocades.add(Kadiyalu123Play.silverTwo(right, left, silverSilk, silverJari));
        brocades.add(Kadiyalu123Play.green(emptyRight, emptyLeft, greenSilk, greenJari));
        brocades.add(Kadiyalu123Play.orange(emptyRight, emptyLeft, orangeSilk, orangeJari));

        for (BufferedImage bufferedImage : brocades) {
            System.out.printf("file data - %s - %s%n", bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        BufferedImage brocade = getBrocade(brocades);
        displayPixels(brocade);
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage silverSilkR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade3/silver-silk.bmp"))));
        BufferedImage silverJariR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade3/silver-jari.bmp"))));
        BufferedImage greenSilkR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade3/green-silk.bmp"))));
        BufferedImage greenJariR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade3/green-jari.bmp"))));
        BufferedImage orangeSilkR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade3/orange-silk.bmp"))));
        BufferedImage orangeJariR = body(ReverseGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/brocade3/orange-jari.bmp"))));
        BufferedImage leftBack = border(VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/border/left-first.bmp"))));
        BufferedImage left = border(VerticalFlipGenerator.get(ImageIO.read(new File("d/14/in/2024/aug17/border/left.bmp"))));
        BufferedImage right = border(ImageIO.read(new File("d/14/in/2024/aug17/border/right.bmp")));

        List<BufferedImage> rightSilces = SlicerGenerator.get(right, 2);
        List<BufferedImage> leftBackSilces = SlicerGenerator.get(leftBack, 2);
        List<BufferedImage> leftSilces = SlicerGenerator.get(left, 2);

        List<BufferedImage> silverSilkSilces = SlicerGenerator.get(silverSilkR, 2);
        List<BufferedImage> silverJariSilces = SlicerGenerator.get(silverJariR, 2);
        List<BufferedImage> greenSilkSilces = SlicerGenerator.get(greenSilkR, 2);
        List<BufferedImage> greenJariSilces = SlicerGenerator.get(greenJariR, 2);
        List<BufferedImage> orangeSilkSilces = SlicerGenerator.get(orangeSilkR, 2);
        List<BufferedImage> orangeJariSilces = SlicerGenerator.get(orangeJariR, 2);

        LinkedList<BufferedImage> brocades = new LinkedList<>();

        if (rightSilces.size() == silverSilkSilces.size()) {
            for (int i = 0; i < rightSilces.size(); i++) {
                boolean isOrangeSilkValid = BlackCheck.valid(orangeSilkSilces.get(i));
                if (isOrangeSilkValid) {
                    brocades.add(kadiyalu123Play(
                                    rightSilces.get(i), leftBackSilces.get(i), leftSilces.get(i),
                                    silverSilkSilces.get(i), silverJariSilces.get(i),
                                    greenSilkSilces.get(i), greenJariSilces.get(i),
                                    orangeSilkSilces.get(i), orangeJariSilces.get(i)
                            )
                    );
                } else {
                    brocades.add(kadiyalu12Play(
                                    rightSilces.get(i), leftBackSilces.get(i), leftSilces.get(i),
                                    silverSilkSilces.get(i), silverJariSilces.get(i),
                                    greenSilkSilces.get(i), greenJariSilces.get(i)
                            )
                    );
                }
            }
        }
        BufferedImage brocade = LeftLayoutGenerator.get(SlicerGenerator.attachX(brocades));
        saveBMP(brocade, format("d/14/out/2024/aug17/3kadiyalu-broc-special-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
    }

    public static BufferedImage body(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(7, image));
    }

    public static BufferedImage border(BufferedImage image) {
        return VerticalRepeatGenerator.get(1, HorizontalRepeatGenerator.get(12, image));
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
