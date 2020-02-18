package arrays;

import java.io.*;
import java.util.*;

public class P05ArrayManipulation {
    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long biggestElement = Long.MIN_VALUE;
        long[] array = new long[n];

        for (int commandIndex = 0; commandIndex < queries.length; commandIndex++) {
            for (int i = queries[commandIndex][0] - 1; i <= queries[commandIndex][1] - 1; i++) {
                array[i] += queries[commandIndex][2];

                if (array[i] > biggestElement) {
                    biggestElement = array[i];
                }
            }
        }

        return biggestElement;
    }

    static class Interval implements Comparable<Interval> {
        int startIndex;
        int endIndex;

        public Interval(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public boolean isInsideInterval(int index) {
            return startIndex <= index && index <= endIndex;
        }

        @Override
        public int compareTo(Interval other) {
            int comparisonResult = Integer.compare(startIndex, other.startIndex);
            if (comparisonResult == 0) {
                comparisonResult = Integer.compare(endIndex, other.endIndex);
            }

            return comparisonResult;
        }

        @Override
        public boolean equals(Object other) {
            Interval otherInterval = (Interval) other;

            return this.startIndex == otherInterval.startIndex && this.endIndex == otherInterval.endIndex;
        }

        @Override
        public int hashCode() {
            return startIndex + endIndex;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] sizeAndCommands = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] query = new int[sizeAndCommands[1]][3];
        for (int i = 0; i < sizeAndCommands[1]; i++) {
            query[i] =  Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        consoleReader.close();

        long result = arrayManipulation(sizeAndCommands[0], query);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
