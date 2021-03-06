package com.task.image_comparison;

import java.util.HashMap;
import java.util.Map;

public class DifImgs {

    private WriteImage writeImage1;
    private WriteImage writeImage2;
    private String[][] bordersDiff;

    private final String BORDER_CHAR = "1";
    private final String DIF_CHAR = "x";
    private final String NONE_CHAR = ".";

    public DifImgs(WriteImage startImg, WriteImage finishImg) {
        writeImage1 = startImg;
        writeImage2 = finishImg;
        bordersOfDiff();
    }

    private String[][] makedif() {
        int[][] different1 = writeImage1.getRgbArray();
        int[][] different2 = writeImage2.getRgbArray();

        String[][] result = new String[different1.length][different1[0].length];

        for (int i = 0; i < different1.length; i++) {
            for (int j = 0; j < different1[i].length; j++) {
                if (different1[i][j] != different2[i][j]) {
                    result[i][j] = DIF_CHAR;
                } else {
                    result[i][j] = NONE_CHAR;
                }
            }
        }

        return result;
    }


    private void bordersOfDiff() {
        String[][] differences = makedif();

        for (int i = 0; i < differences.length; i++) {
            for (int j = 0; j < differences[i].length; j++) {

                if (differences[i][j].equals(DIF_CHAR) && differences[i][j - 1].equals(NONE_CHAR)) {
                    differences[i][j - 1] = BORDER_CHAR;
                }

            }
        }

        for (int i = 0; i < differences[0].length; i++) {
            for (int j = 0; j < differences.length; j++) {

                if (differences[j][i].equals(DIF_CHAR) && differences[j - 1][i].equals(NONE_CHAR)) {
                    differences[j - 1][i] = BORDER_CHAR;
                }

            }
        }

        for (int i = 0; i < differences.length; i++) {
            for (int j = differences[i].length; j > 0; j--) {

                if (differences[i][j - 1].equals(DIF_CHAR) && differences[i][j].equals(NONE_CHAR)) {
                    differences[i][j] = BORDER_CHAR;
                }

            }

        }

        for (int i = 0; i < differences[0].length; i++) {
            for (int j = differences.length; j > 0; j--) {

                if (differences[j - 1][i].equals(DIF_CHAR) && differences[j][i].equals(NONE_CHAR)) {
                    differences[j][i] = BORDER_CHAR;
                }

            }
        }

        bordersDiff = differences;

    }

    private int[][] creatingRectangleBorders() {

        Map<Integer, Integer> integerIntegerMap = searching1();

        if (integerIntegerMap.isEmpty()) {
            return new int[0][0];
        }

        int currentY = integerIntegerMap.get(1);
        int currentX = integerIntegerMap.get(2);

        int[][] coordinates = new int[bordersDiff.length][bordersDiff[0].length];

        bordersDiff[currentY][currentX] = "2";
        coordinates[currentY][currentX] = 1;

        for (int i = 0; i < 500; i++) {

            // вверх
            if (bordersDiff[currentY - 1][currentX].equals("1")) {
                currentY -= 1;
                bordersDiff[currentY][currentX] = "2";
                coordinates[currentY][currentX] = 1;
                continue;
            }

            //вверх вправо
            if (bordersDiff[currentY - 1][currentX + 1].equals("1")) {
                currentY -= 1;
                currentX += 1;
                bordersDiff[currentY][currentX] = "2";
                coordinates[currentY][currentX] = 1;
                continue;
            }

            //вправо
            if (bordersDiff[currentY][currentX + 1].equals("1")) {
                currentX += 1;
                bordersDiff[currentY][currentX] = "2";
                coordinates[currentY][currentX] = 1;
                continue;
            }

            //вниз вправо
            if (bordersDiff[currentY + 1][currentX + 1].equals("1")) {
                currentY += 1;
                currentX += 1;
                bordersDiff[currentY][currentX] = "2";
                coordinates[currentY][currentX] = 1;
                continue;
            }

            //вниз
            if (bordersDiff[currentY + 1][currentX].equals("1")) {
                currentY += 1;
                bordersDiff[currentY][currentX] = "2";
                coordinates[currentY][currentX] = 1;
                continue;
            }

            //вниз влево
            if (bordersDiff[currentY + 1][currentX - 1].equals("1")) {
                currentY += 1;
                currentX -= 1;
                bordersDiff[currentY][currentX] = "2";
                coordinates[currentY][currentX] = 1;
            }

            //назад
            if (bordersDiff[currentY][currentX - 1].equals("1")) {
                currentX -= 1;
                bordersDiff[currentY][currentX] = "2";
                coordinates[currentY][currentX] = 1;
            }

            //назад вверх
            if (bordersDiff[currentY - 1][currentX - 1].equals("1")) {
                currentY -= 1;
                currentX -= 1;
                bordersDiff[currentY][currentX] = "2";
                coordinates[currentY][currentX] = 1;
                continue;
            }
        }

        return coordinates;

    }

    private void defineSidesOfRectangle() {

        int[][] ints = creatingRectangleBorders();

        if (ints.length == 0) return;

        int leftSideX = 0, rightsideX = 0, bottomX = 0, headerX = 0;
        int leftSideY = 0, rightsideY = 0, bottomY = 0, headerY = 0;

        for (int k = 0; k < ints.length; k++) {
            for (int n = 0; n < ints[0].length; n++) {
                if (ints[k][n] == 1) {
                    leftSideX = n;
                    leftSideY = k;
                    rightsideX = n;
                    rightsideY = k;
                    headerX = n;
                    headerY = k;
                    bottomX = n;
                    bottomY = k;
                    break;
                }
            }
        }

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {


                if (ints[i][j] == 1 && j < leftSideX) {
                    leftSideY = i;
                    leftSideX = j;
                }
                if (ints[i][j] == 1 && j > rightsideX) {
                    rightsideX = j;
                    rightsideY = i;
                }
                if (ints[i][j] == 1 && i < headerY) {
                    headerX = j;
                    headerY = i;
                }
                if (ints[i][j] == 1 && i > bottomY) {
                    bottomX = j;
                    bottomY = i;
                }

            }
        }

        bordersDiff[leftSideY][leftSideX] = "Z";
        bordersDiff[rightsideY][rightsideX] = "Z";
        bordersDiff[headerY][headerX] = "Z";
        bordersDiff[bottomY][bottomX] = "Z";


        int     pointA_x = leftSideX, pointA_y = headerY,
                pointB_x = rightsideX, pointB_y = headerY,
                pointC_x = rightsideX - 1, pointC_y = bottomY,
                pointD_x = leftSideX - 1, pointD_y = bottomY;

        for (int i = 0; i < rightsideX - leftSideX + 1; i++) {

            bordersDiff[pointA_y][leftSideX + i] = "-";

        }

        for (int i = 0; i < bottomY - headerY + 1; i++) {

            bordersDiff[pointB_y + i][pointB_x] = "|";

        }

        for (int i = 0; i < rightsideX - leftSideX + 1; i++) {

            bordersDiff[pointC_y][pointC_x - i] = "-";

        }

        for (int i = 0; i < pointD_y - pointA_y + 1; i++) {

            bordersDiff[pointD_y - i][pointD_x] = "|";

        }


    }

    private void deleteNotNeededMarks() {


        for (int i = 0; i < bordersDiff.length; i++) {
            for (int j = 0; j < bordersDiff[i].length; j++) {


                if (bordersDiff[i][j].equals("|")) {
                    ++j;
                    for (; j < bordersDiff[i].length - 1; j++) {

                        if (bordersDiff[i][j].equals("1") || bordersDiff[i][j].equals("2")) {
                            bordersDiff[i][j] = ".";
                        }

                        if (bordersDiff[i][j].equals("|")) break;
                    }
                }
            }
        }

    }


    private Map<Integer, Integer> searching1() {

        Map<Integer, Integer> startPoint = new HashMap<>();

        for (int i = 0; i < bordersDiff.length; i++) {
            for (int j = 0; j < bordersDiff[i].length; j++) {
                if (bordersDiff[i][j].equals("1")) {
                    startPoint.put(1, i);
                    startPoint.put(2, j);
                    return startPoint;
                }
            }
        }
        return new HashMap<>();
    }

    private void printArray(String[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

    }

    public void findAllChanhes() {

        for (int i = 0; i < 100; i++) {
            defineSidesOfRectangle();
            deleteNotNeededMarks();
        }
    }

    public void hightlightBorders() {
        writeImage2.setDiff(bordersDiff);
        writeImage1.setDiff(bordersDiff);
    }

}

