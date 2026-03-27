package com.pao.laboratory04.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 3 — Exceptions (checked, unchecked, custom)
 *
 * Create two custom exception classes in this package (next to Main.java),
 * then demonstrate them here.
 *
 * STEP 1 — Create InvalidAgeException.java (separate file):
 *   - Extends RuntimeException (unchecked)
 *   - Constructor with String message → call super(message)
 *
 * STEP 2 — Create DuplicateEntryException.java (separate file):
 *   - Extends RuntimeException (unchecked)
 *   - Constructor with String message → call super(message)
 *
 * STEP 3 — In this Main.java, implement and demonstrate:
 *
 *   a) UNCHECKED EXCEPTIONS — NullPointerException, ArrayIndexOutOfBoundsException:
 *      - Create a method riskyMethod() that throws NullPointerException
 *      - Catch it with try-catch, display the error message
 *      - Add a finally block that always executes
 *
 *   b) CUSTOM EXCEPTIONS — InvalidAgeException, DuplicateEntryException:
 *      - Create a method validateAge(int age) that throws InvalidAgeException
 *        if age < 0 or age > 150
 *      - Create a method addToList(List<String> list, String name) that throws
 *        DuplicateEntryException if name already exists in the list
 *      - Demonstrate both with try-catch
 *
 *   c) MULTI-CATCH:
 *      - Catch InvalidAgeException | DuplicateEntryException in a single catch block
 *
 *   d) CATCH ORDERING:
 *      - Demonstrate that catching the specific exception (InvalidAgeException) must
 *        come BEFORE the general one (RuntimeException)
 *
 *   e) THROW vs THROWS:
 *      - Create a method with the signature: void process(int age) throws InvalidAgeException
 *      - Call it from main with try-catch
 *
 * Expected output:
 *
 * === a) Unchecked — NullPointerException ===
 * Caught: Cannot invoke "String.length()" because "s" is null
 * Finally always executes!
 *
 * === b) Custom exceptions ===
 * InvalidAgeException: Age -5 is invalid (0-150)
 * DuplicateEntryException: 'Ana' already exists in the list
 *
 * === c) Multi-catch ===
 * Exception caught: Age 200 is invalid (0-150)
 *
 * === d) Catch ordering (specific → general) ===
 * InvalidAgeException caught specifically: Age -1 is invalid (0-150)
 *
 * === e) Throw vs throws ===
 * process() method threw: Age 999 is invalid (0-150)
 */
public class Main {
    // uncheckd exceptions
    public static void riskyMethod(){
        String s = null;
        s.length();
    }

    public static void validateAge(int age){
        if (age < 0 || age > 150){
            throw new InvalidAgeException("Age " + age + " is invalid (0-150)");
        }
    }

    public static void addToList(List<String> list, String name) {
        if (list.contains(name)){
            throw new DuplicateEntryException("'" + name + "' already exists in the list");
        }
    }

    public static void process(int age) {
        validateAge(age);
    }

    public static void main(String[] args) {
        // TODO: implementează pașii de mai sus
        // Hint: creează mai întâi InvalidAgeException.java și DuplicateEntryException.java

        // unchecked exceptions: try-catch-finally
        System.out.println("=== try-catch-finally ===");
        try {
            riskyMethod();
        } catch (NullPointerException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally always executes!");
        }

        // custom exceptions
        System.out.println("\n=== Excepții custom ===");
        try {
            validateAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("InvalidAgeException: " + e.getMessage());
        }

        List<String> names = new ArrayList<>();
        names.add("Jessica");
        try {
            addToList(names, "Jessica");
        } catch (DuplicateEntryException e) {
                System.out.println("DuplicateEntryException: " + e.getMessage());
        }

        // multi catch
        System.out.println("\n=== Many catch ===");
        try {
            validateAge(200);
        } catch (InvalidAgeException | DuplicateEntryException e){
            System.out.println("Exception caught: " + e.getMessage());
        }

        // Catch ordering
        System.out.println("\n=== d) Catch ordering (specific → general) ===");

        try {
            validateAge(-1);
        } catch (InvalidAgeException e) {
            System.out.println("InvalidAgeException caught specifically: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("General: " + e.getMessage());
        }

        // Throw vs throws
        System.out.println("\n=== e) Throw vs throws ===");
        try {
            process(999);
        } catch (InvalidAgeException e) {
            System.out.println("process() method threw: " + e.getMessage());
        }
    }
}

