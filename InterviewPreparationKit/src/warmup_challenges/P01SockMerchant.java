package warmup_challenges;

import java.io.*;
import java.util.*;

public class P01SockMerchant {
    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        Map<Integer, Integer> colorCount = new HashMap<>();

        for (int i = 0; i < ar.length; i++) {
            if (!colorCount.containsKey(ar[i])) {
                colorCount.put(ar[i], 1);
            } else {
                colorCount.put(ar[i], colorCount.get(ar[i]) + 1);
            }
        }

        int pairsCount = 0;
        for (Integer value : colorCount.values()) {
            pairsCount += value / 2;
        }

        return pairsCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
