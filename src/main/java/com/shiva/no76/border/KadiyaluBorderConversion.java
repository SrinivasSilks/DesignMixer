package com.shiva.no76.border;

import com.varaprasadps.image.CutLayoutGenerator;
import com.varaprasadps.image.HorizontalFlipGenerator;
import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.shiva.no76.KadiyaluBorderPlay.*;
import static java.lang.String.format;


public class KadiyaluBorderConversion {

    public static BufferedImage get(BufferedImage left, BufferedImage right) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(left, right));
        brocades.add(jari(left, right));
        for (BufferedImage bufferedImage : brocades) {
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(HorizontalFlipGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d-shiva/76/1border-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage left = ImageIO.read(new File("d-shiva/76/left.bmp"));
        BufferedImage right = CutLayoutGenerator.get(ImageIO.read(new File("d-shiva/76/right.bmp")), 4).get(1);

        get(left, right);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
