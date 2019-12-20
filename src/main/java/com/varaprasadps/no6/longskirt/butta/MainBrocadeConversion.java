package com.varaprasadps.no6.longskirt.butta;

import com.varaprasadps.image.AddLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MainBrocadeConversion {

    public static void main(final String[] args) throws IOException {
        String out = "z-data/out/6/butta/butta-brocade-%s-%s.bmp";

        com.varaprasadps.no6.longskirt.butta.brocade1.BrocadeConversion.main(args);
        com.varaprasadps.no6.longskirt.butta.brocade2.BrocadeConversion.main(args);
        com.varaprasadps.no6.longskirt.butta.brocade3.BrocadeConversion.main(args);
        com.varaprasadps.no6.longskirt.butta.brocade4.BrocadeConversion.main(args);
        com.varaprasadps.no6.longskirt.butta.brocade5.BrocadeConversion.main(args);
        com.varaprasadps.no6.longskirt.butta.brocade6.BrocadeConversion.main(args);
        com.varaprasadps.no6.longskirt.butta.brocade7.BrocadeConversion.main(args);
        com.varaprasadps.no6.longskirt.butta.brocade8.BrocadeConversion.main(args);

    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
