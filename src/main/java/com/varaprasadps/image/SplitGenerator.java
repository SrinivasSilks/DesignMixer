package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SplitGenerator {

    public static void main(final String[] args) throws IOException {
        String input = "z-data/in/6/ROYAL_BLUE.bmp";
        BufferedImage inputBI = ImageIO.read(new File(input));
        int number = 109;

        List<BufferedImage> outputs = get(inputBI, number);
        displayPixels(outputs);

        int width = inputBI.getWidth();
        int height = inputBI.getHeight() / number;
        saveBMP(outputs, width, height);
    }

    private static void displayPixels(List<BufferedImage> files) {
        files.forEach(fileOne ->
                System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()))
        );
    }

    public static List<BufferedImage> get(BufferedImage input, int number) {
        List<BufferedImage> results = new LinkedList<>();
        int width = input.getWidth();
        int height = input.getHeight() / number;
        int y = 0;
        while (y < input.getHeight()) {
            final BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
            int yRes = 0;
            while (yRes < bi.getHeight()) {
                copyRow(yRes, bi, y, input);
                y++;
                yRes++;
            }
            results.add(bi);
        }
        return results;
    }


    private static void saveBMP(final List<BufferedImage> outputBIs, int width, int height) throws IOException {
        for (int i = 0; i < outputBIs.size(); i++) {
            BufferedImage bi = outputBIs.get(i);
            String path = String.format("z-data/out/6/split/split-layout-%s-%s-%s.bmp", i + 1, width, height);
            ImageIO.write(bi, "bmp", new File(path));
        }
    }

    private static void copyRow(int resultCol, BufferedImage output, int inputCol, BufferedImage input) {
        for (int x = 0; x < input.getWidth(); x++) {
            output.setRGB(x, resultCol, input.getRGB(x, inputCol));
        }
    }

}
