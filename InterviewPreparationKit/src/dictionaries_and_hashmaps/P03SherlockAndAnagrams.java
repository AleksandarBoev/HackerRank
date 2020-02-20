package dictionaries_and_hashmaps;

import java.io.*;
import java.util.*;

public class P03SherlockAndAnagrams {
    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int counter = 0;

        for (int stringLength = 1; stringLength < s.length(); stringLength++) {
            for (int i = 0; i <= s.length() - stringLength; i++) {
                String firstString = s.substring(i, stringLength + i);

                for (int j = i + 1; j <= s.length() - stringLength; j++) {
                    String secondString = s.substring(j, stringLength + j);

                    if (areAnagramEqual(firstString, secondString)) {
                        counter++;
                    }
                }
            }
        }

        return counter;
    }

    static boolean areAnagramEqual(String s1, String s2) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        fillCharacterMap(s1, map1);

        HashMap<Character, Integer> map2 = new HashMap<>();
        fillCharacterMap(s2, map2);

        return mapsAreEqual(map1, map2);
    }

    static void fillCharacterMap(String string, Map<Character, Integer> map) {
        for (int i = 0; i < string.length(); i++) {
            if (!map.containsKey(string.charAt(i))) {
                map.put(string.charAt(i), 1);
            } else {
                map.put(string.charAt(i), map.get(string.charAt(i)) + 1);
            }
        }
    }

    static <T1, T2> boolean mapsAreEqual(Map<T1, T2> map1, Map<T1, T2> map2) {
        if (!map1.keySet().containsAll(map2.keySet())) {
            return false;
        } else {
            for (Map.Entry<T1, T2> entry : map1.entrySet()) {
                if (!entry.getValue().equals(map2.get(entry.getKey()))) {
                    return false;
                }
            }
        }

        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
