package com.varaprasadps.no13.a2022.test.conversions;

import com.varaprasadps.image.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Play13 {

    public static BufferedImage get(BufferedImage raniBody, BufferedImage rChecks,
                                    BufferedImage jariBody, BufferedImage jChecks) throws IOException {
        List<BufferedImage> inputBIs = new LinkedList<>();
        inputBIs.add(rani(raniBody, rChecks));
        inputBIs.add(jari(jariBody, jChecks));
        return LeftLayoutGenerator.get(ColumnRepeatGenerator.get(inputBIs));
    }

    public static BufferedImage jari(BufferedImage body, BufferedImage checks) {
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        //left border
        inputBIs.add(EmptyGenerator.get(width, 320));

        //checks
        inputBIs.add(checks);
        inputBIs.add(checks);
        inputBIs.add(checks);
        //body
        inputBIs.add(body);
        //locking
        inputBIs.add(PlainGenerator.get(width, 16));

        //border
        inputBIs.add(EmptyGenerator.get(width, 592));

        //box
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 1));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        inputBIs.add(EmptyGenerator.get(width, 7));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
    }

    public static BufferedImage rani(BufferedImage body, BufferedImage checks) {
        int width = body.getWidth();

        List<BufferedImage> inputBIs = new LinkedList<>();

        //mispick
        inputBIs.add(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 12));

        //left border
        inputBIs.add(EmptyGenerator.get(width, 320));

        //checks
        inputBIs.add(checks);
        inputBIs.add(checks);
        inputBIs.add(checks);
        //body
        inputBIs.add(body);

        //locking
        inputBIs.add(PlainGenerator.get(width, 16));
        //border
        inputBIs.add(EmptyGenerator.get(width, 592));

        //box
        inputBIs.add(EmptyGenerator.get(width, 1));
        inputBIs.add(ReverseGenerator.get(EmptyGenerator.get(width, 1)));
        //mispick
        inputBIs.add(ReverseGenerator.get(CutLayoutGenerator.get(AchuLayoutGenerator.get(width, 4), 2).get(0)));
        //kadiyalu kali
        inputBIs.add(EmptyGenerator.get(width, 2));
        //achu
        inputBIs.add(EmptyGenerator.get(width, 10));

        inputBIs.add(EmptyGenerator.get(width, 1));
        inputBIs.add(EmptyGenerator.get(width, 7));

        int repeatWidth = 0;
        int repeatHeight = 0;

        for (BufferedImage bi : inputBIs) {
            displayPixels(bi);
            repeatWidth = bi.getWidth();
            repeatHeight += bi.getHeight();
        }
        return AddLayoutGenerator.get(repeatWidth, repeatHeight, inputBIs);
    }

    private static void displayPixels(BufferedImage fileOne) {
        System.out.printf("Width : %s, Height : %s%n", fileOne.getWidth(), fileOne.getHeight());
    }

}
