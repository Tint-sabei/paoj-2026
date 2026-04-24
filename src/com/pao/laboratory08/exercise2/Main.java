package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Adresa;
import com.pao.laboratory08.exercise1.Student;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";
    private static final String OUTPUT_FILE = "rezultate.txt";

    public static void main(String[] args) throws Exception {
        List<Student> students = new ArrayList<>();

        // 1. Read students
        BufferedReader fin = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = fin.readLine()) != null) {
            String[] tokens = line.split(",");
            if (tokens.length == 4) {
                Adresa address = new Adresa(tokens[2].trim(), tokens[3].trim());
                students.add(new Student(tokens[0].trim(), Integer.parseInt(tokens[1].trim()), address));
            }
        }
        fin.close();

        // 2. Write students
        Scanner scanner = new Scanner(System.in);
        int threshold = scanner.nextInt();

        List<Student> filteredStudents = new ArrayList<>();
        BufferedWriter fout = new BufferedWriter(new FileWriter(OUTPUT_FILE));

        for (Student s : students){
            if (s.getVarsta() >= threshold){
                filteredStudents.add(s);
                fout.write(s.toString());
                fout.newLine();
            }
        }
        fout.close();

        System.out.println("Filtru: varsta >= " + threshold);
        System.out.println("Rezultate: " + filteredStudents.size() + " studenti");
        System.out.println();

        for (Student s : filteredStudents){
            System.out.println(s);
        }

        System.out.println();
        System.out.println("Scris in: " + OUTPUT_FILE);
    }
}