//package com.pao.laboratory08.exercise2;
//
//import java.io.*;
//import java.util.*;
//
//public class Main {
//    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";
//
//    public static void main(String[] args) throws Exception {
//        // TODO: Implementează conform Readme.md
//        //
//        // 1. Citește studenții din FILE_PATH cu BufferedReader
//        // 2. Citește pragul de vârstă din stdin cu Scanner
//        // 3. Filtrează studenții cu varsta >= prag
//        // 4. Scrie filtrații în "rezultate.txt" cu BufferedWriter
//        // 5. Afișează sumarul la consolă
//
//        System.out.println("TODO: implementează exercițiul 2");
//    }
//}
//


package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Adresa;
import com.pao.laboratory08.exercise1.Student;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";
    private static final String OUTPUT_PATH = "rezultate.txt";

    public static void main(String[] args) {
        List<Student> allStudents = new ArrayList<>();

        // 1. Read students from FILE_PATH with BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Adresa adr = new Adresa(parts[2].trim(), parts[3].trim());
                    allStudents.add(new Student(parts[0].trim(), Integer.parseInt(parts[1].trim()), adr));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // 2. Read age threshold from stdin
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int threshold = scanner.nextInt();

        // 3. Filter students (using Java 8+ Streams for elegance)
        List<Student> filteredStudents = allStudents.stream()
                .filter(s -> s.getVarsta() >= threshold)
                .collect(Collectors.toList());

        // 4. Write to "rezultate.txt" with BufferedWriter
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PATH))) {
            for (Student s : filteredStudents) {
                bw.write(s.toString());
                bw.newLine(); // Efficient way to handle platform-specific line separators
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        // 5. Display summary to console
        System.out.println("Filtru: varsta >= " + threshold);
        System.out.println("Rezultate: " + filteredStudents.size() + " studenti");
        System.out.println();

        filteredStudents.forEach(System.out::println);

        System.out.println();
        System.out.println("Scris in: " + OUTPUT_PATH);
    }
}