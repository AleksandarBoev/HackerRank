package arrays;

import java.io.*;
import java.util.*;

public class P01TwoDimensionalArrayDS {
    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] matrix) {
        int biggestHourglassSum = Integer.MIN_VALUE;

        for (int row = 0; row < matrix.length - 2; row++) {
            for (int col = 0; col < matrix[row].length - 2; col++) {
                int currentHourglassSum = getHourglassSum(matrix, row, col);
                if (currentHourglassSum > biggestHourglassSum) {
                    biggestHourglassSum = currentHourglassSum;
                }
            }
        }

        return biggestHourglassSum;
    }

    static int getHourglassSum(int[][] matrix, int upperLeftRow, int upperLeftCol) {
        return matrix[upperLeftRow][upperLeftCol]
                + matrix[upperLeftRow][upperLeftCol + 1]
                + matrix[upperLeftRow][upperLeftCol + 2]
                + matrix[upperLeftRow + 1][upperLeftCol + 1]
                + matrix[upperLeftRow + 2][upperLeftCol]
                + matrix[upperLeftRow + 2][upperLeftCol + 1]
                + matrix[upperLeftRow + 2][upperLeftCol + 2];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
