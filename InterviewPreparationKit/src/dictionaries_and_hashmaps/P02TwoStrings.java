package dictionaries_and_hashmaps;

import java.io.*;
import java.util.*;

public class P02TwoStrings {
    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        HashSet<Character> string1Characters = new HashSet<>();
        addAllCharactersToSet(s1, string1Characters);

        HashSet<Character> string2Characters = new HashSet<>();
        addAllCharactersToSet(s2, string2Characters);

        string1Characters.retainAll(string2Characters);

        return string1Characters.isEmpty() ? "NO" : "YES";
    }

    static void addAllCharactersToSet(String string, Set<Character> set) {
        for (int i = 0; i < string.length(); i++) {
            set.add(string.charAt(i));
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

}
