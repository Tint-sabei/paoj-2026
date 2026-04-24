package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        List<Student> students = new ArrayList<>();


        // 1. Read students from FILE_PATH
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 4) {
                    students.add(new Student(tokens[0].trim(), Integer.parseInt(tokens[1].trim()), new Adresa(tokens[2].trim(), tokens[3].trim())));
                }
            }

        }

        // 2. Read command from stdin
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] tokens = input.split(" ", 2);
        String cmd = tokens[0];

        // 3. Execute command
        if (cmd.equals("PRINT")) {
            for (Student s : students){
                System.out.println(s);
            }
        } else if (tokens.length == 2) {
            String name = tokens[1];

            for (Student s : students) {
                if (s.getNume().equalsIgnoreCase(name)) {
                    Student clona;
                    if (cmd.equals("DEEP")) {
                        clona = s.deepClone();
                    } else {
                        clona = (Student) s.clone();
                    }

                    clona.getAdresa().setOras("MODIFICAT");

                    System.out.println("Original: " + s);
                    System.out.println("Clona: " + clona);
                    return;
                }
            }
        }
    }
}
