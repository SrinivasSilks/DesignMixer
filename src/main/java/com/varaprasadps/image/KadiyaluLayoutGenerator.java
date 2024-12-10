package com.varaprasadps.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class KadiyaluLayoutGenerator {

    public static void main(final String[] args) throws IOException {
        String out = "z-vasu/in/3/a2024/design1/border/border-first.bmp";
        String input = "z-vasu/in/3/a2024/design1/border/border.bmp";
        BufferedImage bi = kadiyalu(input);
        displayPixels(bi);
        saveBMP(bi, String.format(out, bi.getWidth(), bi.getHeight()));
    }

    public static BufferedImage kadiyalu(String input) throws IOException {
        BufferedImage inputBi = ImageIO.read(new File(input));
        return kadiyalu(inputBi);
    }

    public static BufferedImage kadiyalu(BufferedImage inputBi) {
        BufferedImage image = LeftLayoutGenerator.get(inputBi);

        List<BufferedImage> inputBIs = new LinkedList<>();

        List<BufferedImage> cc = CutLayoutGenerator.get(image, 1);

        inputBIs.add(cc.get(1));
        inputBIs.add(cc.get(0));
        return RightLayoutGenerator.get(AddLayoutGenerator.get(inputBIs));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

    static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
