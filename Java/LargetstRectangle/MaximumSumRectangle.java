package Java.LargetstRectangle;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-26
 * Description: https://www.geeksforgeeks.org/maximum-sum-rectangle-in-a-2d-matrix-dp-27/
 */
public class MaximumSumRectangle {

    public static void main(String args[]) {
        int matrix[][] = {{1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}};

        int[] points = maximumSumRectangle(matrix);
        //(top,left) to (bottom,right)
        int sum = 0;
        for (int row = points[0]; row <= points[2]; row++) {
            System.out.println();
            for (int col = points[1]; col <= points[3]; col++) {
                System.out.print(matrix[row][col] + " ");
                sum += matrix[row][col];
            }
        }
        System.out.println("Max sum " + sum);

    }

    //(top,left) to (bottom,right)
    private static int[] maximumSumRectangle(int[][] matrix) {

        int[] points = new int[4];

        int rows = matrix.length;
        int cols = matrix[0].length;

        int temp[] = new int[rows];
        Arrays.fill(temp, 0);

        int left = -1, right = -1, top = -1, bottom = -1;
        int maxSum = Integer.MIN_VALUE;

        for (int leftCol = 0; leftCol < cols; leftCol++) {
            Arrays.fill(temp, 0);

            for (int rightCol = leftCol; rightCol < cols; rightCol++) {

                for (int i = 0; i < rows; i++)
                    temp[i] += matrix[i][rightCol];

                int currentRowMax[] = kadens1D(temp);

                if (maxSum < currentRowMax[0]) {
                    maxSum = currentRowMax[0];
                    left = leftCol;
                    right = rightCol;
                    top = currentRowMax[1];
                    bottom = currentRowMax[2];
                }
            }

        }
        //(top,left) to (bottom,right)
        points[0] = top;
        points[1] = left;
        points[2] = bottom;
        points[3] = right;

        return points;

    }

    private static int[] kadens1D(int[] temp) {
        int max[] = {Integer.MIN_VALUE, 0, -1}; // maxSum, left, right

        int currentSum = 0;
        int maxSum = 0;
        int start = 0;
        int maxNeg = Integer.MIN_VALUE;
        int maxNegIndex = 0;

        for (int i = 0; i < temp.length; i++) {

            currentSum += temp[i];

            if (currentSum > maxSum) {
                maxSum = currentSum;
                max[0] = maxSum;
                max[1] = start;
                max[2] = i;
            }

            if (currentSum < 0) {
                currentSum = 0;
                start = i+1 ;
            }

            if (maxNeg < temp[i] && temp[i] < 0) {
                maxNeg = temp[i];
                maxNegIndex = i;
            }


        }

        //Means all the element in the array are neg.
        if (max[2] == -1) {

            max[0] = maxNeg;
            max[1] = maxNegIndex;
            max[2] = maxNegIndex;
        }

        return max;
    }
}
