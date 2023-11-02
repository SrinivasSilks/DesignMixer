package com.vasu.loom2.design5.brocade9;

import com.varaprasadps.image.HorizontalRepeatGenerator;
import com.varaprasadps.image.LeftLayoutGenerator;
import com.varaprasadps.image.RightLayoutGenerator;
import com.varaprasadps.image.VerticalFlipGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.vasu.loom2.TwoPlay.*;
import static java.lang.String.format;


public class SelfBrocadeConversion {

    public static BufferedImage get(BufferedImage right, BufferedImage left, BufferedImage rani, BufferedImage jari) throws IOException {
        List<BufferedImage> brocades = new LinkedList<>();
        brocades.add(rani(right, left, rani));
        brocades.add(jari(getBorder(left), jari));
        BufferedImage brocade = LeftLayoutGenerator.get(getBrocade(brocades));
        displayPixels(brocade);

        saveBMP(brocade, format("z-vasu/out/2/design5/9selfbrocade-%s-%s.bmp", brocade.getWidth(), brocade.getHeight()));
        return brocade;
    }

    public static void main(final String[] args) throws IOException {
        BufferedImage rightd = ImageIO.read(new File("z-vasu/in/2/design5/border/border.bmp"));
        BufferedImage leftd = VerticalFlipGenerator.get(ImageIO.read(new File("z-vasu/in/2/design5/border/border.bmp")));

        BufferedImage right = HorizontalRepeatGenerator.get(11, rightd);
        BufferedImage left = HorizontalRepeatGenerator.get(11, leftd);

        BufferedImage image = ImageIO.read(new File("z-vasu/in/2/design5/brocade9/jari.bmp"));
        BufferedImage read = RightLayoutGenerator.get(HorizontalRepeatGenerator.get(2, LeftLayoutGenerator.get(image)));
        BufferedImage jari = HorizontalRepeatGenerator.get(12, read);

        BufferedImage image2 = ImageIO.read(new File("z-vasu/in/2/design5/brocade9/nimbu.bmp"));
        BufferedImage read2 = RightLayoutGenerator.get(HorizontalRepeatGenerator.get(2, LeftLayoutGenerator.get(image2)));
        BufferedImage rani = HorizontalRepeatGenerator.get(12, read2);

        get(right, left, rani, jari);
    }


    private static void displayPixels(BufferedImage fileOne) {
        System.out.println(format("Width : %s, Height : %s", fileOne.getWidth(), fileOne.getHeight()));
    }

    private static void saveBMP(final BufferedImage bi, final String path) throws IOException {
        ImageIO.write(bi, "bmp", new File(path));
    }


}
