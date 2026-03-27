package com.pao.laboratory03.exercise;
import com.pao.laboratory03.exercise.model.Subject;
import com.pao.laboratory03.exercise.service.StudentService;
import java.util.*;

/**
 * Exercise 4 (Integrator) — Student + Grade Management System
 *
 * Combines Map, Enum and Custom Exceptions into a mini-interactive system.
 * Students create ALL classes from scratch.
 *
 * ═══════════════════════════════════════════════════════════════
 *  CLASSES TO CREATE (separate files in corresponding subpackages):
 * ═══════════════════════════════════════════════════════════════
 *
 * 1. model/Subject.java — ENUM
 *    - Constants: PAOJ, BD, SO, RC (or other subjects)
 *    - Fields: String fullName, int credits
 *    - Private constructor, getters
 *    - toString() → "PAOJ (Advanced Object-Oriented Programming, 6 credits)"
 *
 * 2. model/Student.java — CLASS
 *    - Private fields: String name, int age, Map<Subject, Double> grades
 *    - Constructor: Student(String name, int age)
 *      → initializes grades as empty HashMap
 *      → validation: if age < 18 or age > 60, throw InvalidStudentException
 *    - Methods: getName(), getAge(), getGrades()
 *    - addGrade(Subject subject, double grade)
 *      → if grade < 1 or grade > 10, throw InvalidGradeException
 *      → puts the grade in the map (overwrites if subject already exists)
 *    - double getAverage()
 *      → calculates the arithmetic mean of grades (returns 0 if no grades)
 *    - toString() → "Student{name='Ana', age=20, avg=8.50}"
 *
 * 3. exception/InvalidStudentException.java — CUSTOM EXCEPTION
 *    - extends RuntimeException
 *    - Constructor with String message → super(message)
 *
 * 4. exception/InvalidGradeException.java — CUSTOM EXCEPTION
 *    - extends RuntimeException
 *    - Constructor with String message → super(message)
 *
 * 5. exception/StudentNotFoundException.java — CUSTOM EXCEPTION
 *    - extends RuntimeException
 *    - Constructor with String message → super(message)
 *
 * 6. service/StudentService.java — SERVICE (Singleton)
 *    - Field: List<Student> students (ArrayList)
 *    - Singleton pattern (private constructor, getInstance())
 *    - Methods:
 *      a) void addStudent(String name, int age)
 *         → creates Student and adds to list
 *         → if a student with the same name already exists, throw RuntimeException
 *      b) Student findByName(String name)
 *         → searches the list, throws StudentNotFoundException if not found
 *      c) void addGrade(String studentName, Subject subject, double grade)
 *         → finds the student (findByName) and adds the grade
 *      d) void printAllStudents()
 *         → displays all students with their grades
 *      e) void printTopStudents()
 *         → sorts students in descending order by average and displays them
 *      f) Map<Subject, Double> getAveragePerSubject()
 *         → calculates the average per subject (from all students who have a grade)
 *
 * ═══════════════════════════════════════════════════════════════
 *  MENU (implemented below — DO NOT modify the switch structure)
 * ═══════════════════════════════════════════════════════════════
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // TODO: obtain instance of StudentService (Singleton)
        StudentService service = StudentService.getInstance();

        System.out.println("=== Sistem Gestiune Studenți ===");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add student");
            System.out.println("2. Add notes");
            System.out.println("3. Show all students");
            System.out.println("4. Top students (by average)");
            System.out.println("5. Average per subject");
            System.out.println("0. Exit");
            System.out.print("Options: ");

            String option = scanner.nextLine().trim();

            try {
                switch (option) {
                    case "1":
                        System.out.print("Nume: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Vârsta: ");
                        int age = Integer.parseInt(scanner.nextLine().trim());
                        // TODO: call service.addStudent(name, age)
                        service.addStudent(name, age);
                        System.out.println("Student added successfully!");
                        break;

                    case "2":
                        System.out.print("Student name:  ");
                        String studentName = scanner.nextLine().trim();
                        // TODO: display Subject.values()
                        System.out.print("Subject (" + Arrays.toString(Subject.values()) + "): ");
                        String subjectStr = scanner.nextLine().trim().toUpperCase();
                        System.out.print("Note (1-10): ");
                        double grade = Double.parseDouble(scanner.nextLine().trim());
                        // TODO: convert subjectStr to Subject with valueOf()
                        Subject subject = Subject.valueOf(subjectStr);
                        // TODO: call service.addGrade(studentName, subject, grade)
                        service.addGrade(studentName, subject, grade);
                        System.out.println("Note added!");
                        break;

                    case "3":
                        // TODO: call service.printAllStudents()
                        service.printAllStudents();
                        break;

                    case "4":
                        System.out.println("=== Top students ===");
                        // TODO: call service.printTopStudents()
                        service.printTopStudents();
                        break;

                    case "5":
                        System.out.println("=== Average per subject ===");
                        // TODO: call service.getAveragePerSubject() and show
                        Map<Subject, Double> averages = service.getAveragePerSubject();
                        for (Map.Entry<Subject, Double> entry : averages.entrySet()) {
                            System.out.println(entry.getKey().name() + ": " + entry.getValue());
                        }
                        break;

                    case "0":
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid Option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Eroare: Introdu un număr valid.");
            } catch (IllegalArgumentException e) {
                System.out.println("Eroare: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Eroare: " + e.getMessage());
            }
        }

        scanner.close();
    }
}


//
//=== Student Management System ===
//
//        --- Menu ---
//        1. Add student
//2. Add grade
//3. Display all students
//4. Top students (by average)
//5. Average per subject
//0. Exit
//Option: 1
//Name: Ana
//Age: 20
//Student added successfully!
//
//Option: 2
//Student name: Ana
//Subject (PAOJ, BD, SO, RC): PAOJ
//Grade (1-10): 9.5
//Grade added!
//
//Option: 3
//        1. Student{name='Ana', age=20, avg=8.75}
//PAOJ = 9.5
//BD = 8.0
//
//Option: 4
//        === Top students ===
//        1. Ana — average: 8.75
//        2. Mihai — average: 7.00
//
//Option: 5
//        === Average per subject ===
//PAOJ: 8.25
//BD: 8.00
//
//Option: 1
//Name: Invalid
//Age: -5
//Error: Age -5 is invalid (18-60)
//
//Option: 0
//Goodbye!
