package com.shiva.no76.brocade1;

import com.varaprasadps.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.shiva.no76.Kadiyalu2Play.*;
import static java.lang.String.format;


public class KadiyaluGoldConversion {

    public static BufferedImage get(BufferedImage meena, BufferedImage jariF) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(meena));
        brocades.add(jari(meena));
        brocades.add(nimbu(jariF));
        for (BufferedImage bufferedImage : brocades) {
            System.out.println(String.format("file data - %s - %s", bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        BufferedImage brocade = LeftLayoutGenerator.get(HorizontalFlipGenerator.get(getBrocade(brocades)));
        displayPixels(brocade);

        saveBMP(brocade, format("d-shiva/76/1kbroc-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {

        BufferedImage jari = HorizontalRepeatGenerator.get(1, ImageIO.read(new File("d-shiva/76/jari.bmp")));
        BufferedImage meena = PlainGenerator.get(jari.getWidth(), jari.getHeight());

        get(meena, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
