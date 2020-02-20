package dictionaries_and_hashmaps;

import java.util.HashMap;
import java.util.Map;

public class P03SherlockAndAnagramsLocalConsole {
    public static void main(String[] args) {
        String wat = "abba";
        int counter = 0;

        for (int stringLength = 1; stringLength < wat.length(); stringLength++) { // max string length will be with 1 less than given string
            for (int i = 0; i <= wat.length() - stringLength; i++) { // from which position to form a string
                String firstString = wat.substring(i, stringLength + i);

                for (int j = i + 1; j <= wat.length() - stringLength; j++) { // on the next position start forming a string
                    String secondString = wat.substring(j, stringLength + j);

                    if (areAnagramEqual(firstString, secondString)) {
                        System.out.println(firstString + " and " + secondString);
                        counter++;
                    }
                }
            }
        }

        System.out.println(counter);
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

    static<T1, T2> boolean mapsAreEqual(Map<T1, T2> map1, Map<T1, T2> map2) {
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
}
