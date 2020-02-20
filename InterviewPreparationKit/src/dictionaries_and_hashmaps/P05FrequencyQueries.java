package dictionaries_and_hashmaps;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class P05FrequencyQueries {
    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<>();

        FreqQueryDataStructure fqds = new FreqQueryDataStructure();

        for (List<Integer> currentCommand : queries) {
            int command = currentCommand.get(0);
            int value = currentCommand.get(1);

            if (command == 1) {
                fqds.addNumber(value);
            } else if (command == 2) {
                fqds.removeNumber(value);
            } else if (command == 3) {
                if (fqds.occurencesFound(value)) {
                    result.add(1);
                } else {
                    result.add(0);
                }
            }
        }

        return result;
    }

    static class FreqQueryDataStructure {
        HashMap<Integer, Integer> numberOccurence;
        HashMap<Integer, Integer> occurenceCount;

        FreqQueryDataStructure() {
            numberOccurence = new HashMap<>();
            occurenceCount = new HashMap<>();
        }

        void addNumber(Integer number) {
            Integer occurences = numberOccurence.get(number);

            if (occurences == null) {
                occurences = 1;
                addOrIncrement(occurenceCount, 1);
            } else {
                removeOrDecrement(occurenceCount, occurences);
                occurences++;
                addOrIncrement(occurenceCount, occurences);
            }

            numberOccurence.put(number, occurences);
        }

        void removeNumber(Integer number) {
            Integer occurences = numberOccurence.get(number);
            removeOrDecrement(occurenceCount, occurences);
            removeOrDecrement(numberOccurence, number);
        }

        boolean occurencesFound(Integer countOfOccurences) {
            return occurenceCount.containsKey(countOfOccurences);
        }

        void addOrIncrement(HashMap<Integer, Integer> map, Integer key) {
            Integer count = map.get(key);

            if (count == null) {
                map.put(key, 1);
            } else {
                map.put(key, count + 1);
            }
        }

        void removeOrDecrement(HashMap<Integer, Integer> map, Integer key) {
            Integer count = map.get(key);

            if (count == null) {
                return;
            } else {
                count--;
                if (count <= 0) {
                    map.remove(key);
                } else {
                    map.put(key, count);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
