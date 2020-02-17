package warmup_challenges;

import java.io.*;
import java.util.*;

public class P04RepeatedString {
    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        int countOfAsInString = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                countOfAsInString++;
            }
        }

        long stringRepeatedCount = n / s.length();
        long charactersLeft = n % s.length();

        int additionalAs = 0;
        for (int i = 0; i < charactersLeft; i++) {
            if (s.charAt(i) == 'a') {
                additionalAs++;
            }
        }

        return stringRepeatedCount * countOfAsInString + additionalAs;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
