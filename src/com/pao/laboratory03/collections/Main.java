//package com.pao.laboratory03.collections;
//
///**
// * Exercise 1 — Collections: HashMap and TreeMap
// *
// * Create in this main:
// *
// * PART A — HashMap (word frequency)
// * 1. Declare an array of Strings:
// *    String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};
// * 2. Create a HashMap<String, Integer> that counts how many times each word appears.
// *    - Iterate through the array and use put() + getOrDefault() to increment the counter.
// * 3. Display the map.
// * 4. Check if the key "rust" exists using containsKey().
// * 5. Display ONLY the keys (keySet()), then ONLY the values (values()).
// * 6. Iterate through the map with entrySet() and display "key -> value" for each entry.
// *
// * PART B — TreeMap (automatic sorting)
// * 7. Create a TreeMap<String, Integer> from the same HashMap (constructor with argument).
// * 8. Display the TreeMap — observe the alphabetical order of the keys.
// * 9. Use firstKey() and lastKey() to display the first and last key.
// *
// * PART C — Map with objects
// * 10. Create a HashMap<String, List<String>> that associates subjects with lists of students.
// *     Example: "PAOJ" -> ["Ana", "Mihai", "Ion"], "DB" -> ["Ana", "Elena"]
// * 11. Display all students for the subject "PAOJ".
// * 12. Add a new student to "DB" and display the updated list.
// *
// * Expected output (approximate — HashMap order may vary):
// *
// * === PART A: HashMap — word frequency ===
// * Frequency: {python=2, c++=2, java=3, rust=1, go=1}
// * Contains 'rust'? true
// * Keys: [python, c++, java, rust, go]
// * Values: [2, 2, 3, 1, 1]
// * python -> 2
// * c++ -> 2
// * java -> 3
// * rust -> 1
// * go -> 1
// *
// * === PART B: TreeMap — automatic sorting ===
// * Sorted: {c++=2, go=1, java=3, python=2, rust=1}
// * First key: c++
// * Last key: rust
// *
// * === PART C: Map with objects ===
// * Students at PAOJ: [Ana, Mihai, Ion]
// * Students at DB (updated): [Ana, Elena, George]
// */
//public class Main {
//    public static void main(String[] args) {
//        // TODO: implement the 3 parts above
//        String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};
//
//        HashMap<String, Integer> cntWords = new HashMap<>();
//
//        for (HashMap.Entry<String, Integer> set : words.entrySet()) {
//            System.out.println(set.getKey() + " = " + set.getValue());
//
//            put() + getOrDefault()
//            containsKey().rust
//
//        }
//
//    }
//}

package com.pao.laboratory03.collections;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 1. Declare the array
        String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};

        // 2. Create the HashMap
        HashMap<String, Integer> wordCounts = new HashMap<>();

        // Iterate through the array (standard for-each loop)
        for (String word : words) {
            // Use getOrDefault to fetch current count (or 0) and add 1
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        // 3. Display the map
        System.out.println("Frequency: " + wordCounts);

        // 4. Check if "rust" exists
        System.out.println("Contains 'rust'? " + wordCounts.containsKey("rust"));

        // 5. Display keys and values
        System.out.println("Keys: " + wordCounts.keySet());
        System.out.println("Values: " + wordCounts.values());

        // 6. Iterate through entrySet
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
