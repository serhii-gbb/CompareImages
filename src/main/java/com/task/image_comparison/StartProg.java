package com.task.image_comparison;


import java.io.File;


public class StartProg {
    public static void main(String[] args) {

        WriteImage image1 = new WriteImage((new File("D:\\GoJava#6\\JavaEE\\ImageComparison\\src\\main\\resources\\image1.png")));
        WriteImage image2 = new WriteImage((new File("D:\\GoJava#6\\JavaEE\\ImageComparison\\src\\main\\resources\\image2.png")));

        DifImgs defImgs = new DifImgs(image2, image1);
        defImgs.hightlightBorders();


        PrintResultImg printResultImg = new PrintResultImg(image2, 1000, 800);

    }


    private static void printArray(String[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

    }
}
