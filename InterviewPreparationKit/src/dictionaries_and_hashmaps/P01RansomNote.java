package dictionaries_and_hashmaps;

import java.util.*;

public class P01RansomNote {
    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        HashMap<String, Integer> availableWordsCount = new HashMap<>();
        HashMap<String, Integer> neededWordsCount = new HashMap<>();

        fillElementCountMap(magazine, availableWordsCount);
        fillElementCountMap(note, neededWordsCount);

        for (Map.Entry<String, Integer> wordCount : neededWordsCount.entrySet()) {
            if (!availableWordsCount.containsKey(wordCount.getKey())
                    || availableWordsCount.get(wordCount.getKey()) < wordCount.getValue()) {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }

    static <T> void fillElementCountMap(T[] array, Map<T, Integer> map) {
        for (int i = 0; i < array.length; i++) {
            if (!map.containsKey(array[i])) {
                map.put(array[i], 1);
            } else {
                map.put(array[i], map.get(array[i]) + 1);
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
