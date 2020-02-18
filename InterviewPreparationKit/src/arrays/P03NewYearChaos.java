package arrays;

import java.util.*;

public class P03NewYearChaos {
    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        for (int i = 0; i < q.length; i++) {
            if (q[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
        }

        int bribesCounter = 0;

        boolean isSwapped;
        int size = q.length;
        for (int i = 0; i < size - 1; i++) {
            isSwapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                //check the adjacent elements
                if (q[j] > q[j + 1]) {
                    //swap the elements
                    int temp = q[j];
                    q[j] = q[j + 1];
                    q[j + 1] = temp;
                    isSwapped = true;
                    bribesCounter++;
                }
            }
            //check if any swapping occurred in last iteration
            //if yes then break the loop, array is sorted at this point
            if (isSwapped == false)
                break;
        }

        System.out.println(bribesCounter);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
