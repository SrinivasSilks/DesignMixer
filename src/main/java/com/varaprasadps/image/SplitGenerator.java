package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SplitGenerator {

    public static void main(final String[] args) throws IOException {

        List<String> out = new LinkedList<>();
        out.add("z-data/out/split-layout-1-%s-%s.bmp");
        out.add("z-data/out/split-layout-2-%s-%s.bmp");

        String input = "z-data/out/brocade-3600-1824.bmp";
        BufferedImage inputBI = ImageIO.read(new File(input));
        int width = inputBI.getWidth();
        int height = inputBI.getHeight() / 2;

        List<BufferedImage> outputs = get(inputBI);
        displayPixels(outputs);
        saveBMP(outputs, out, width, height);
    }

    private static void displayPixels(List<BufferedImage> files) {
        files.forEach(fileOne ->
                System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()))
        );
    }

    public static List<BufferedImage> get(BufferedImage input) {
        List<BufferedImage> results = new LinkedList<>();
        int width = input.getWidth();
        int height = input.getHeight() / 2;
        int y = 0;
        while (y < input.getHeight()) {
            final BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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


    private static void saveBMP(final List<BufferedImage> outputBIs, final List<String> paths, int width, int height) throws IOException {
        for (int i = 0; i < outputBIs.size(); i++) {
            BufferedImage bi = outputBIs.get(i);
            String path = String.format(paths.get(i), width, height);
            ImageIO.write(bi, "bmp", new File(path));
        }
    }

    private static void copyRow(int resultCol, BufferedImage output, int inputCol, BufferedImage input) {
        for (int x = 0; x < input.getWidth(); x++) {
            output.setRGB(x, resultCol, input.getRGB(x, inputCol));
        }
    }

}
