package com.varaprasadps.no13.a2022.test.butta;

import com.varaprasadps.image.PlainGenerator;
import com.varaprasadps.image.ReverseGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ButtaConversion {

    public static BufferedImage buttaConversion() throws IOException {
        final BufferedImage jariBody = ImageIO.read(new File("z-data/in/13/a2022/design1/brocade/jari.bmp"));
        final int width = jariBody.getWidth();
        final BufferedImage checks = ReverseGenerator.get(ImageIO.read(new File("z-data/in/13/a2022/checks.bmp")));
        final BufferedImage raniBody = PlainGenerator.get(width, 480);



        return null;
    }


}
