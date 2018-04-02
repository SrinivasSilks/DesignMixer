package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ArielLayoutGenerator {


    public static void main(final String[] args) throws IOException {
        String input = "z-data/in/6/JARI.bmp";
        BufferedImage inputBI = ImageIO.read(new File(input));
        int number = 109;
        BufferedImage result = get(inputBI, number);
        displayPixels(result);
        saveBMP(result);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    public static BufferedImage get(BufferedImage input, int number) {
        int width = input.getWidth();
        int sHeight = input.getHeight() / number;
        List<BufferedImage> split = new LinkedList<>();
        int y = 0;
        while (y < input.getHeight()) {
            final BufferedImage bi = new BufferedImage(width, sHeight, BufferedImage.TYPE_INT_RGB);
            int yRes = 0;
            while (yRes < bi.getHeight()) {
                copyRow(yRes, bi, y, input);
                y++;
                yRes++;
            }
            split.add(bi);
        }
        int rHeight = 0;
        List<BufferedImage> results = new LinkedList<>();
        for (int i = 0; i < split.size(); i++) {
            BufferedImage bi = split.get(i);
            results.add(bi);
            results.add(ReverseGenerator.get(StepLayoutGenerator.get(width)));
            rHeight += bi.getHeight() + 4;
        }
        return AddLayoutGenerator.get(width, rHeight, results);
    }

    private static void saveBMP(final BufferedImage output) throws IOException {
        String format = "z-data/out/ariel-layout-%s-%s.bmp";
        RenderedImage rendImage = output;
        ImageIO.write(rendImage, "bmp", new File(String.format(format, output.getWidth(), output.getHeight())));

    }

    private static void copyRow(int resultCol, BufferedImage output, int inputCol, BufferedImage input) {
        for (int x = 0; x < input.getWidth(); x++) {
            output.setRGB(x, resultCol, input.getRGB(x, inputCol));
        }
    }


}
