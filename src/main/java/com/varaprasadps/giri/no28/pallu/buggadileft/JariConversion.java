package com.varaprasadps.giri.no28.pallu.buggadileft;

import com.varaprasadps.image.AddLayoutGenerator;
import com.varaprasadps.image.EmptyGenerator;
import com.varaprasadps.image.ReverseGenerator;
import com.varaprasadps.image.StepLayoutGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JariConversion {

    public static void main(final String[] args) throws IOException {

        String out = "z-giri/out/28/p-b-l-jari-%s-%s.bmp";
        final BufferedImage centre = ImageIO.read(new File("z-giri/in/28/PALLU/BUGGUDI_JARI.bmp"));
        final BufferedImage centreOne = ImageIO.read(new File("z-giri/in/28/PALLU/BUGGUDI_JARI_1.bmp"));
        final BufferedImage centreTwo = ImageIO.read(new File("z-giri/in/28/PALLU/BUGGUDI_JARI_2.bmp"));
        int width = centre.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        inputBIs.add(EmptyGenerator.get(width, 32));
        //Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //Achu Kali
        inputBIs.add(EmptyGenerator.get(width, 12));

        //Allover
        inputBIs.add(centreTwo);
        inputBIs.add(centreOne);

        //Skirt
        inputBIs.add(centreTwo);
        inputBIs.add(centre);
        inputBIs.add(centre);
        inputBIs.add(centre);
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 5)));

        //Locking
        inputBIs.add(ReverseGenerator.get(StepLayoutGenerator.get(width, 5)));
        //Banaras
        inputBIs.add(EmptyGenerator.get(width, 20));

        //Box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 2)));
        inputBIs.add(EmptyGenerator.get(width, 2));
        //Achu Kali
        inputBIs.add(EmptyGenerator.get(width, 12));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        BufferedImage bi = ReverseGenerator.get(AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs));
        displayPixels(bi);
        saveBMP(bi, String.format(out, repeatWidth, repeatHeight));
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(String.format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }

}
