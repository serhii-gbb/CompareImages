package com.task.image_comparison;


import java.io.File;


public class StartProg {
    public static void main(String[] args) {

        WriteImage image1 = new WriteImage((new File("src\\main\\resources\\image1.png")));
        WriteImage image2 = new WriteImage((new File("src\\main\\resources\\image2.png")));

        DifImgs defImgs = new DifImgs(image2, image1);
        defImgs.findAllChanhes();
        defImgs.hightlightBorders();


        PrintResultImg printResultImg = new PrintResultImg(image2, 1000, 800);

    }



}
