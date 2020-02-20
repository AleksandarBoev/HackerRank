package dictionaries_and_hashmaps;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
public class P04CountTriplets {
        // Complete the countTriplets function below.
        static long countTriplets(List<Long> arr, long r) {
            HashMap<Long, SortedSet<Integer>> valueIndices = getValueIndices(arr);

            long counter = 0;

            // GS = Geometric Sequence
            for (Map.Entry<Long, SortedSet<Integer>> entry : valueIndices.entrySet()) {
                SortedSet<Integer> indicesOfSecondGSElement = valueIndices.get(entry.getKey() * r);

                if (indicesOfSecondGSElement == null) { // there are no SecondGSElements
                    continue;
                }

                SortedSet<Integer> indicesOfThirdGSElement = valueIndices.get(entry.getKey() * r * r);

                if (indicesOfThirdGSElement == null) { // there are no ThirdGSElements
                    continue;
                }

                //start checking if the indices of the found GS elements are in correct order
                for (Integer firstGSElementIndex : entry.getValue()) {
                    SortedSet<Integer> indicesOfSecondGSElementAfterFirstGSElement
                            = indicesOfSecondGSElement.tailSet(firstGSElementIndex + 1);

                    if (indicesOfSecondGSElementAfterFirstGSElement.isEmpty()) {
                        break; // breaking because the iteration is over sorted ascending indices. And if there are no valid indices NOW, then there won't be any after
                    }

                    for (Integer secondGSElementCurrentIndex : indicesOfSecondGSElementAfterFirstGSElement) {
                        SortedSet<Integer> indicesOfThirdGSElementAfterSecondGSElement
                                = indicesOfThirdGSElement.tailSet(secondGSElementCurrentIndex + 1);

                        if (indicesOfThirdGSElementAfterSecondGSElement.isEmpty()) {
                            break;
                        }

                        counter += indicesOfThirdGSElementAfterSecondGSElement.size();
                    }
                }
            }

            return counter;
        }

        static HashMap<Long, SortedSet<Integer>> getValueIndices(List<Long> arr) {
            HashMap<Long, SortedSet<Integer>> valueIndices = new HashMap<>();

            for (int i = 0; i < arr.size(); i++) {
                SortedSet<Integer> indicesFound = valueIndices.get(arr.get(i));

                if (indicesFound == null) {
                    indicesFound = new TreeSet<>();
                    valueIndices.put(arr.get(i), indicesFound);
                }

                indicesFound.add(i);
            }

            return valueIndices;
        }

        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(nr[0]);

            long r = Long.parseLong(nr[1]);

            List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Long::parseLong)
                    .collect(toList());

            long ans = countTriplets(arr, r);

            bufferedWriter.write(String.valueOf(ans));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }

}
