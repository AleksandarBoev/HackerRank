package warmup_challenges;

import java.io.*;
import java.util.*;

public class P02CountingValleys {
    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        int currentSeaLevel = 0;
        int valleysCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'U') {
                currentSeaLevel++;
            } else {
                if (--currentSeaLevel == -1) {
                    valleysCount++;
                }
            }
        }

        return valleysCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
