package com.task.image_comparison;

public class DifImgs {

    private WriteImage writeImage1;
    private WriteImage writeImage2;
    private String[][] bordersDiff;

    public DifImgs(WriteImage startImg, WriteImage finishImg) {
        writeImage1 = startImg;
        writeImage2 = finishImg;
        bordersOfDiff();
    }

    private String[][] makedif(){
        int[][] different1 = writeImage1.getRgbArray();
        int[][] different2 = writeImage2.getRgbArray();

        String[][] result = new String[different1.length][different1[0].length];

        for (int i = 0; i < different1.length; i++) {
            for (int j = 0; j < different1[i].length; j++) {
//                System.out.print(different1[i][j] == different2[i][j] ? "." : "x");
                if (different1[i][j] != different2[i][j]) {
                    result[i][j] = "x";
                } else {
                    result[i][j] = ".";
                }
            }
//            System.out.println();
        }

        return result;
    }


    private void bordersOfDiff() {
        String[][] differences = makedif();

        for (int i = 0; i < differences.length; i++) {
            for (int j = 0; j < differences[i].length; j++) {

                if (differences[i][j].equals("x") && differences[i][j-1].equals(".")) {
                    differences[i][j-1]="1";
                }

            }
        }

        for (int i = 0; i < differences[0].length; i++) {
            for (int j = 0; j < differences.length; j++) {

                if (differences[j][i].equals("x") && differences[j-1][i].equals(".")) {
                    differences[j-1][i]="1";
                }
            }
        }
        bordersDiff = differences;
    }

    public void hightlightBorders(){
        writeImage2.setDiff(bordersDiff);
        writeImage1.setDiff(bordersDiff);
    }

}

