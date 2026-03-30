package com.pao.laboratory06.exercise1;

import com.pao.test.IOTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        IOTest.runParts("src/com/pao/laboratory06/exercise1/tests", Main::main);


        System.out.println("sth: " + angajat + salary_double);

        Scanner scanner = new Scanner(System.in);
        String emp_nr = scanner.next();
        list<angajat, salary> emp = scanner.nextInt();
        String sort_type = scanner.next();
        Angajat[] angajati = new Angajat[emp_nr];
        for (int i = 0; i < emp_nr; i++) {
            angajati[i] = Angajat.citeste(scanner);
        }

        by_salary



    }
}
