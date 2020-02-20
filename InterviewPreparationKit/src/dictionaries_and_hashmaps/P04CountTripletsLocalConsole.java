package dictionaries_and_hashmaps;

import java.util.*;

public class P04CountTripletsLocalConsole {
    public static void main(String[] args) {
//        int commonRation = 3;
//        List<Long> input = new ArrayList<>(Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L));
//        int commonRation = 2;
//        List<Long> input = new ArrayList<>(Arrays.asList(1L, 2L, 2L, 4L));
        int commonRation = 5;
        List<Long> input = new ArrayList<>(Arrays.asList(1L, 5L, 5L, 25L, 125L));

        solution4(input, commonRation);
    }

    static void solution4(List<Long> arr, int r) {
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

        System.out.println(counter);
    }

    /*
    Returns a map with key --> the number and value --> SortedSet of indices in which the number is seen
    SortedSet is used, because a subset of bigger elements can be extracted. Example:
    I have index 5. And I want to get all bigger indices. SortedSet.tailSet(5 + 1) will return the wanted values.

    Idea is: when I have value 5 and want to find the other two values, which are part of the geometric sequence,
    I can do something like HashMap.get(5 * r) --> will return SortedSet of indices of second element of geometric sequence.
    But I also must know if those indices are AFTER the index of value "5". "SortedSet.tailSet" will do the trick.
    Finally for the third element, just repeat the above actions, but instead of using the first element, use the second one.
     */
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

    static void solution3(List<Long> arr, int r) {
        LinkedHashMap<Long, Long> allElement = getValueCountLinked(arr);

        LinkedHashMap<Long, Long> unusedElements = new LinkedHashMap<>(allElement);
        long counter = 0L;
        for (Map.Entry<Long, Long> entry : allElement.entrySet()) {
            Long secondGeometricElementCount = unusedElements.get(entry.getKey() * r);

            if (secondGeometricElementCount == null) {
                continue;
            }

            Long thirdGeometricElementCount = unusedElements.get(entry.getKey() * r * r);

            if (thirdGeometricElementCount == null) {
                continue;
            }

            counter += entry.getValue() * secondGeometricElementCount * thirdGeometricElementCount;
            unusedElements.remove(entry.getKey());
        }

        System.out.println(counter);
    }

    static void solution2(List<Long> arr, int r) {
        HashMap<Long, Long> valueCount = getValueCount(arr);

        long counter = 0;
        for (Map.Entry<Long, Long> entry : valueCount.entrySet()) {
            Long secondGeometricElementCount = valueCount.get(entry.getKey() * r);

            if (secondGeometricElementCount == null) {
                continue;
            }

            Long thirdGeometricElementCount = valueCount.get(entry.getKey() * r * r);

            if (thirdGeometricElementCount == null) {
                continue;
            }

            counter += entry.getValue() * secondGeometricElementCount * thirdGeometricElementCount;
        }

        System.out.println(counter);
    }

    static LinkedHashMap<Long, Long> getValueCountLinked(List<Long> elements) {
        LinkedHashMap<Long, Long> valueCount = new LinkedHashMap<>();

        for (int i = 0; i < elements.size(); i++) {
            Long count = valueCount.get(elements.get(i));

            if (count == null) {
                valueCount.put(elements.get(i), 1L);
            } else {
                valueCount.put(elements.get(i), count + 1L);
            }
        }

        return valueCount;
    }

    static HashMap<Long, Long> getValueCount(List<Long> elements) {
        HashMap<Long, Long> valueCount = new HashMap<>();

        for (int i = 0; i < elements.size(); i++) {
            Long count = valueCount.get(elements.get(i));

            if (count == null) {
                valueCount.put(elements.get(i), 1L);
            } else {
                valueCount.put(elements.get(i), count + 1L);
            }
        }

        return valueCount;
    }

    static void solution1(List<Integer> elements, int commonRation) {
        int counter = 0;

        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                int multiplication = elements.get(i) * commonRation;
                if (multiplication != elements.get(j)) {
                    continue;
                }

                for (int k = j + 1; k < elements.size(); k++) {
                    if (multiplication * commonRation != elements.get(k)) {
                        continue;
                    }

                    counter++;
                    System.out.printf("[%d] = %d | [%d] = %d | [%d] = %d%n",
                            i, elements.get(i), j, elements.get(j), k, elements.get(k));
                }
            }
        }
    }
}
