//package com.pao.laboratory08.exercise1;
//
//import java.io.*;
//import java.util.*;
//
//public class Main {
//    // Path to the data file — relative to the project root
//    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";
//
//    public static void main(String[] args) throws Exception {
//        // TODO: Implementează conform Readme.md
//        //
//        // 1. Read students from FILE_PATH with BufferedReader
//        // 2. Read command from stdin: PRINT, SHALLOW <name> or DEEP <name>
//        // 3. Execute command:
//        // - PRINT → print all students
//        // - SHALLOW <name> → shallow clone + change clone city to "MODIFIED" + print
//        // - DEEP <name> → deep clone + change clone city to "MODIFIED" + print
//
//        System.out.println("TODO: implementează exercițiul 1");
//    }
//}

package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        List<Student> students = new ArrayList<>();

        // 1. Read students from FILE_PATH
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Adresa adr = new Adresa(parts[2].trim(), parts[3].trim());
                    students.add(new Student(parts[0].trim(), Integer.parseInt(parts[1].trim()), adr));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // 2. Read command from stdin
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) return;
        String input = scanner.nextLine();
        String[] commandParts = input.split(" ", 2);
        String cmd = commandParts[0];

        // 3. Execute command
        switch (cmd) {
            case "PRINT":
                students.forEach(System.out::println);
                break;

            case "SHALLOW":
                processClone(students, commandParts[1], false);
                break;

            case "DEEP":
                processClone(students, commandParts[1], true);
                break;
        }
    }

    private static void processClone(List<Student> students, String name, boolean isDeep) throws CloneNotSupportedException {
        for (Student s : students) {
            if (s.getNume().equalsIgnoreCase(name)) {
                Student clona = isDeep ? s.deepClone() : (Student) s.clone();

                clona.getAdresa().setOras("MODIFICAT");

                System.out.println("Original: " + s);
                System.out.println("Clona: " + clona);
                return;
            }
        }
    }
}
