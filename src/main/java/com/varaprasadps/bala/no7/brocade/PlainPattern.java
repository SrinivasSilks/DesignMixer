package com.varaprasadps.bala.no7.brocade;

import com.varaprasadps.bala.no7.brocade.chucks.FirstBoxConversion;
import com.varaprasadps.bala.no7.brocade.chucks.SecondBoxConversion;
import com.varaprasadps.image.*;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class PlainPattern {

    private static BufferedImage getBrocade(BufferedImage border, int index) {
        if (border.getWidth() == 4) {
            return FirstBoxConversion.get(border);
        }
        if (border.getWidth() == 2 & index % 2 == 0) {
            return SecondBoxConversion.get(border);
        }
        if (border.getWidth() == 2) {
            return FirstBoxConversion.get(border);
        }
        return null;
    }

    public static BufferedImage get(BufferedImage border3) {

        List<Integer> data = new LinkedList<>();
        for (int i = 0; i < border3.getWidth() / 14; i++) {
            data.add(2);
            data.add(2);
            data.add(2);
            data.add(2);
            data.add(2);
            data.add(4);
        }

        BufferedImage aaaa = LeftLayoutGenerator.get(border3);

        List<BufferedImage> borders = new LinkedList<>();
        borders.add(aaaa);
        borders.add(aaaa);

        List<BufferedImage> result = new LinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            if (i < data.size() - 1) {
                borders = CutLayoutGenerator.get(borders.get(1), data.get(i));
                BufferedImage border = RightLayoutGenerator.get(borders.get(0));
                result.add(getBrocade(border, i));
            } else {
                BufferedImage border = RightLayoutGenerator.get(borders.get(1));
                result.add(getBrocade(border, i));
            }
        }
        return AddLayoutGenerator.get(result);
    }

}
