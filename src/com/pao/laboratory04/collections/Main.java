package com.pao.laboratory04.collections;
import java.util.Map;
import java.util.*;

/**
 * Exercise 1 — Collections: HashMap and TreeMap
 *
 * Create in this main:
 *
 * PART A — HashMap (word frequency)
 * 1. Declare an array of Strings:
 *    String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};
 * 2. Create a HashMap<String, Integer> that counts how many times each word appears.
 *    - Iterate through the array and use put() + getOrDefault() to increment the counter.
 * 3. Display the map.
 * 4. Check if the key "rust" exists using containsKey().
 * 5. Display ONLY the keys (keySet()), then ONLY the values (values()).
 * 6. Iterate through the map using entrySet() and display "key -> value" for each entry.
 *
 * PART B — TreeMap (automatic sorting)
 * 7. Create a TreeMap<String, Integer> from the same HashMap (constructor with argument).
 * 8. Display the TreeMap — observe the alphabetical order of the keys.
 * 9. Use firstKey() and lastKey() to display the first and last key.
 *
 * PART C — Map with objects
 * 10. Create a HashMap<String, List<String>> that associates subjects with lists of students.
 *     Example: "PAOJ" -> ["Ana", "Mihai", "Ion"], "BD" -> ["Ana", "Elena"]
 * 11. Display all students for the subject "PAOJ".
 * 12. Add a new student to "BD" and display the updated list.
 *
 * Expected output (illustrative — HashMap order may vary):
 *
 * === PART A: HashMap — word frequency ===
 * Frequency: {python=2, c++=2, java=3, rust=1, go=1}
 * Contains 'rust'? true
 * Keys: [python, c++, java, rust, go]
 * Values: [2, 2, 3, 1, 1]
 * python -> 2
 * c++ -> 2
 * java -> 3
 * rust -> 1
 * go -> 1
 *
 * === PART B: TreeMap — automatic sorting ===
 * Sorted: {c++=2, go=1, java=3, python=2, rust=1}
 * First key: c++
 * Last key: rust
 *
 * === PART C: Map with objects ===
 * Students in PAOJ: [Ana, Mihai, Ion]
 * Students in BD (updated): [Ana, Elena, George]
 */

public class Main {
    public static void main(String[] args) {
        // TODO: implement the 3 parts above

        // Part A
        System.out.println("=== PART A: HashMap — word frequency ===");
        String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};

        Map<String, Integer> wordCnt = new HashMap<>();

        for (String w : words){
            wordCnt.put(w, wordCnt.getOrDefault(w, 0) + 1);
        }

        System.out.println("Frequency: " + wordCnt);
        System.out.println("Contains 'rust'? " + wordCnt.containsKey("rust"));


        System.out.println("Keys: " + wordCnt.keySet());
        System.out.println("Values: " + wordCnt.values());

        for (Map.Entry entry : wordCnt.entrySet()) {System.out.println(entry.getKey() + " " + entry.getValue());}

        for (Map.Entry<String, Integer> entry : wordCnt.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Part B
        System.out.println("\n=== PART B: TreeMap — automatic sorting ===");
        TreeMap<String, Integer> wordOrder = new TreeMap(wordCnt);

        System.out.println("Alphabetical Order of words: " + wordOrder);
        System.out.println("First key is: " + wordOrder.firstKey());
        System.out.println("Last key is: " + wordOrder.lastKey());

        // Part C
        System.out.println("\n=== PART C: Map with objects ===\n");
        Map<String, List<String>> subjects = new HashMap<>();
        subjects.put("PAOJ", new ArrayList<>(Arrays.asList("Ana", "Mihai", "Ion")));
        subjects.put("BD", new ArrayList<>(Arrays.asList("Ana", "Mihai", "Ion")));
        System.out.println("Students in PAOJ: " + subjects.get("PAOJ"));

        if (subjects.containsKey("BD")) {
            subjects.get("BD").add("John");
        }
        System.out.println("Students in BD (updated): " + subjects.get("BD"));

        }
}

